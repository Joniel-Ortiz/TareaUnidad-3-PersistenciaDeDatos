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
        TbInstrumentos guitarra = new TbInstrumentos(1,"Guitarra",50,250);
        Venta venta1 = new Venta(LocalDateTime.now(), cliente1, guitarra, 2,500,"Efectivo");

        try {
            clienteController.create(cliente1);
            instrumentoController.create(guitarra);
            ventaController.create(venta1);
            System.out.println("--- Datos guardados con exito ---");

            EntityManager em = emf.createEntityManager();

            // Consulta: Seleccionar ventas cuyo total sea mayor a 200
            String jpql = "SELECT v FROM Venta v WHERE v.total_venta > 200";
            TypedQuery<Venta> query = em.createQuery(jpql, Venta.class);
            List<Venta> resultados = query.getResultList();

            System.out.println("\n--- Resultados de consulta JPQL (Ventas > 200) ---");
            for (Venta v : resultados) {
                System.out.println("Venta ID: " + v.getId_venta() + " | Total: $" + v.getTotal_venta() + " | Cliente: " + v.getCliente().getNombre_cliente());
            }
            em.close();
            // --- FIN CONSULTA JPQL ---
        } catch (Exception e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}