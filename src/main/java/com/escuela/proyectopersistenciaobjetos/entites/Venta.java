package com.escuela.proyectopersistenciaobjetos.entites;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private int id_venta;
    
    @Column(name="fecha_venta")
    private LocalDateTime fecha_venta;
    
    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name="id_instrumento", referencedColumnName = "id_pr")
    private TbInstrumentos instrumento;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="cantidad_pr")
    private int cantidad_pr;
    
    @Column(name="total_venta")
    private int total_venta;
    
    @Column(name="metodo_pago")
    private String metodo_pago;

    public Venta() {
    }

    public Venta(LocalDateTime fecha_venta,String descripcion, Cliente cliente, TbInstrumentos instrumento, int cantidad_pr, int total_venta, String metodo_pago) {
        this.fecha_venta = fecha_venta;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.instrumento = instrumento;
        this.cantidad_pr = cantidad_pr;
        this.total_venta = total_venta;
        this.metodo_pago = metodo_pago;
    }

    public int getId_venta() { return id_venta; }
    public void setId_venta(int id_venta) { this.id_venta = id_venta; }

    public LocalDateTime getFecha_venta() { return fecha_venta; }
    public void setFecha_venta(LocalDateTime fecha_venta) { this.fecha_venta = fecha_venta; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public TbInstrumentos getInstrumento() { return instrumento; }
    public void setInstrumento(TbInstrumentos instrumento) { this.instrumento = instrumento; }

    public int getCantidad_pr() { return cantidad_pr; }
    public void setCantidad_pr(int cantidad_pr) { this.cantidad_pr = cantidad_pr; }

    public int getTotal_venta() { return total_venta; }
    public void setTotal_venta(int total_venta) { this.total_venta = total_venta; }

    public String getMetodo_pago() { return metodo_pago; }
    public void setMetodo_pago(String metodo_pago) { this.metodo_pago = metodo_pago; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id_venta=" + id_venta + ", " +
                "fecha_venta=" + fecha_venta + ", " +
                "cliente=" + cliente.getId_cliente() + ", " +
                "instrumento=" + instrumento.getNombrePr() + ", " +
                "id_instrumento=" + instrumento.getIdPr() + ", " +
                "cantidad_pr=" + cantidad_pr + ", " +
                "total_venta=" + total_venta + ", " +
                "metodo_pago=" + metodo_pago +
                "}";
    }
    
    
}