package com.escuela.proyectopersistenciaobjetos;

import com.escuela.proyectopersistenciaobjetos.entites.*;
import com.escuela.proyectopersistenciaobjetos.persistencia.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.escuela_ProyectoPersistenciaObjetos_jar_1.0-SNAPSHOTPU");
        TbInstrumentosJpaController instrumentoController = new TbInstrumentosJpaController (emf);
        ventaJpaController ventaController = new ventaJpaController (emf);
        ClienteJpaController clienteController = new ClienteJpaController (emf);
        
        Cliente cliente1 = new Cliente("2450932237", "Alejandro Flores", "alejandroflores@gmail.com", "0987654321");
        Cliente cliente2 = new Cliente("0987654321", "Pepe Flores", "pepemartinez@gmail.com", "0988654321");
        Cliente cliente3 = new Cliente("0987754322", "Felipe Aguilar", "felipeaguilar@gmail.com", "0977654321");
        Cliente cliente4 = new Cliente("0987754322", "Juan Ortiz", "juanortiz@gmail.com", "0922654321");
        TbInstrumentos guitarra = new TbInstrumentos(1,"Guitarra",50,250);
        TbInstrumentos bateria = new TbInstrumentos(2,"Bateria",50,500);
        TbInstrumentos piano = new TbInstrumentos(3, "Piano", 50, 200);
        Venta venta1 = new Venta(LocalDateTime.now(), "guitarra", cliente1, guitarra, 4,1000,"Efectivo");
        Venta venta2 = new Venta(LocalDateTime.now(), "bateria", cliente1, bateria,1,500,"Tarjeta");
        Venta venta3 = new Venta(LocalDateTime.now(), "piano", cliente1, piano,3,600,"Tarjeta");
        Venta venta4 = new Venta(LocalDateTime.now(), "guitarra", cliente2, guitarra, 3,750,"Efectivo");
        Venta venta5 = new Venta(LocalDateTime.now(), "piano", cliente3, piano, 1,200,"Efectivo");
        Venta venta6 = new Venta(LocalDateTime.now(), "bateria", cliente3, bateria, 4,2000,"Tarjeta");
        Venta venta7 = new Venta(LocalDateTime.now(), "guitarra", cliente4, guitarra, 3,750,"Efectivo");
        Venta venta8 = new Venta(LocalDateTime.now(), "piano", cliente4, piano, 1,200,"Efectivo");
        Venta venta9 = new Venta(LocalDateTime.now(), "bateria", cliente4, bateria, 4,2000,"Tarjeta");

        try {
            clienteController.create(cliente1);
            clienteController.create(cliente2);
            clienteController.create(cliente3);
            clienteController.create(cliente4);
            instrumentoController.create(guitarra);
            instrumentoController.create(bateria);
            instrumentoController.create(piano);
            ventaController.create(venta1);
            ventaController.create(venta2);
            ventaController.create(venta3);
            ventaController.create(venta4);
            ventaController.create(venta5);
            ventaController.create(venta6);
            ventaController.create(venta7);
            ventaController.create(venta8);
            ventaController.create(venta9);
            System.out.println("------------------- Consultas -------------------");
            EntityManager em = emf.createEntityManager();

            //Consulta 'consultaClientes' sin Filtros y Ordenamientos
            String consultaClientes1 = "SELECT cliente FROM Cliente cliente";
            TypedQuery<Cliente> query1 = em.createQuery(consultaClientes1, Cliente.class);
            List<Cliente> clientes1 = query1.getResultList();
            System.out.println("\n-------------Clientes / Sin Filtro / Sin Ordenamiento-------------");
            for (Cliente cliente : clientes1) {
                System.out.println(cliente);
            }

            //Consulta 'consultaClientes' con Filtros y Ordenamientos
            String consultaClientes2 = "SELECT cliente FROM Cliente cliente WHERE SIZE(cliente.ventas) > 1 ORDER BY cliente.nombre_cliente ASC";
            TypedQuery<Cliente> query2 = em.createQuery(consultaClientes2, Cliente.class);
            List<Cliente> clientes2 = query2.getResultList();
            System.out.println("\n-------------Clientes / Filtro / Ordenamiento-------------");
            for (Cliente cliente : clientes2) {
                System.out.println(cliente);
            }

            //Consulta 'consultaClienteVentas' sin Ordenamientos y Paginacion
            String consultaClienteVentas1 = "SELECT cliente FROM Cliente cliente";
            TypedQuery<Cliente> queryCliente1 = em.createQuery(consultaClienteVentas1, Cliente.class);
            List<Cliente> resultados1 =  queryCliente1.getResultList();

            System.out.println("\n-------------Clientes y sus Ventas / Sin Ordenamiento / Sin Paginacion-------------");
            for (Cliente cliente : resultados1) {
                System.out.println("Cliente: " + cliente.getNombre_cliente());
                System.out.println(cliente.getVentas());
            }

            //Consulta 'consultaClienteVentas' con Ordenamientos y Paginacion
            String consultaClienteVentas2 = "SELECT cliente FROM Cliente cliente ORDER BY cliente.nombre_cliente ASC";
            TypedQuery<Cliente> queryCliente2 = em.createQuery(consultaClienteVentas2, Cliente.class);
            queryCliente2.setFirstResult(0);
            queryCliente2.setMaxResults(2);
            List<Cliente> resultados2 =  queryCliente2.getResultList();

            System.out.println("\n-------------Clientes y sus Ventas / Ordenamiento / Paginacion-------------");
            for (Cliente cliente : resultados2) {
                System.out.println("Cliente: " + cliente.getNombre_cliente());
                System.out.println(cliente.getVentas());
            }

            //Consulta 'consultaInstrumentos' sin Ordenamiento y Paginacion
            String consultaInstrumentos1 = "SELECT instrumento FROM TbInstrumentos instrumento";
            TypedQuery<TbInstrumentos> queryInstrumentos1 = em.createQuery(consultaInstrumentos1, TbInstrumentos.class);
            List<TbInstrumentos> instrumentos1 = queryInstrumentos1.getResultList();
            System.out.println("\n-------------Instrumentos / Sin Ordenamiento / Sin Paginación-------------");
            for (TbInstrumentos instrumento : instrumentos1) {
                System.out.println(instrumento);
            }

            //Consulta 'consultaInstrumentos' con Ordenamiento y Paginacion
            String consultaInstrumentos2 = "SELECT instrumento FROM TbInstrumentos instrumento ORDER BY instrumento.nombrePr ASC";
            TypedQuery<TbInstrumentos> queryInstrumentos2 = em.createQuery(consultaInstrumentos2, TbInstrumentos.class);
            queryInstrumentos2.setFirstResult(0);
            queryInstrumentos2.setMaxResults(2);
            List<TbInstrumentos> instrumentos2 = queryInstrumentos2.getResultList();
            System.out.println("\n-------------Instrumentos / Ordenamiento / Paginacion-------------");
            for (TbInstrumentos instrumento : instrumentos2) {
                System.out.println(instrumento);
            }

            em.close();

        } catch (Exception e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}