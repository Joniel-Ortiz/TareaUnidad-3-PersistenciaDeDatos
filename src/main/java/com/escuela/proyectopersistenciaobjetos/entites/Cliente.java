package com.escuela.proyectopersistenciaobjetos.entites;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="cliente")
public class
Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private int id_cliente;
    
    @Column(name="ced_cliente")
    private String ced_cliente;
    
    @Column(name="nombre_cliente")
    private String nombre_cliente;
    
    @Column(name="email_cliente")
    private String email_cliente;
    
    @Column(name="tlf_cliente")
    private String tlf_cliente;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Venta> ventas;
    
    public Cliente() {
    }

    public Cliente(String ced_cliente, String nombre_cliente, String email_cliente, String tlf_cliente) {
        this.ced_cliente = ced_cliente;
        this.nombre_cliente = nombre_cliente;
        this.email_cliente = email_cliente;
        this.tlf_cliente = tlf_cliente;
    }

    public int getId_cliente() { return id_cliente; }
    public void setId_cliente(int id_cliente) { this.id_cliente = id_cliente; }

    public String getCed_cliente() { return ced_cliente; }
    public void setCed_cliente(String ced_cliente) { this.ced_cliente = ced_cliente; }

    public String getNombre_cliente() { return nombre_cliente; }
    public void setNombre_cliente(String nombre_cliente) { this.nombre_cliente = nombre_cliente; }

    public String getEmail_cliente() { return email_cliente; }
    public void setEmail_cliente(String email_cliente) { this.email_cliente = email_cliente; }

    public String getTlf_cliente() { return tlf_cliente; }
    public void setTlf_cliente(String tlf_cliente) { this.tlf_cliente = tlf_cliente; }

    public List<Venta> getVentas() { return ventas; }
    public void setVentas(List<Venta> ventas) { this.ventas = ventas; }
}
