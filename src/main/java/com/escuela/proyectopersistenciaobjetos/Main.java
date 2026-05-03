package com.escuela.proyectopersistenciaobjetos;

import com.escuela.proyectopersistenciaobjetos.entites.*;
import com.escuela.proyectopersistenciaobjetos.persistencia.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;

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
            System.out.println("Cliente guardado con exito!");
            System.out.println("Instrumento guardado con exito!");
            System.out.println("venta guardado con exito!");
        } catch (Exception e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
        System.out.println("Total clientes: " + clienteController.getClienteCount());
        System.out.println("Total instrumentos: " + instrumentoController.getTbInstrumentosCount());
        System.out.println("Total ventas: " + ventaController.getventaCount());
        emf.close();
    }
}