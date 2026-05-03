package com.escuela.proyectopersistenciaobjetos.entites;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_instrumentos")
@NamedQueries({
    @NamedQuery(name = "TbInstrumentos.findAll", query = "SELECT t FROM TbInstrumentos t"),
    @NamedQuery(name = "TbInstrumentos.findByIdPr", query = "SELECT t FROM TbInstrumentos t WHERE t.idPr = :idPr"),
    @NamedQuery(name = "TbInstrumentos.findByNombrePr", query = "SELECT t FROM TbInstrumentos t WHERE t.nombrePr = :nombrePr"),
    @NamedQuery(name = "TbInstrumentos.findByPrecioPr", query = "SELECT t FROM TbInstrumentos t WHERE t.precioPr = :precioPr")
})
public class TbInstrumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pr")
    private Integer idPr;
    
    @Column(name = "id_cat")
    private Integer idCat;
    
    @Column(name = "nombre_pr")
    private String nombrePr;
    
    @Column(name = "precio_pr")
    private Integer precioPr;
    
    @Column(name = "cantidad_pr") 
    private Integer cantidadPr;

    @Lob
    @Column(name = "foto_pr") 
    private byte[] fotoPr;

    public TbInstrumentos() {
    }

    public TbInstrumentos(Integer idCat, String nombrePr, Integer cantidadPr, Integer precioPr) {
        this.idCat = idCat;
        this.nombrePr = nombrePr;
        this.cantidadPr = cantidadPr;
        this.precioPr = precioPr;
    }

    public Integer getIdPr() { return idPr; }
    public void setIdPr(Integer idPr) { this.idPr = idPr; }

    public Integer getIdCat() { return idCat; }
    public void setIdCat(Integer idCat) { this.idCat = idCat; }

    public String getNombrePr() { return nombrePr; }
    public void setNombrePr(String nombrePr) { this.nombrePr = nombrePr; }

    public Integer getPrecioPr() { return precioPr; }
    public void setPrecioPr(Integer precioPr) { this.precioPr = precioPr; }

    public Integer getCantidadPr() { return cantidadPr; }
    public void setCantidadPr(Integer cantidadPr) { this.cantidadPr = cantidadPr; }

    public byte[] getFotoPr() { return fotoPr; }
    public void setFotoPr(byte[] fotoPr) { this.fotoPr = fotoPr; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPr != null ? idPr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TbInstrumentos)) return false;
        TbInstrumentos other = (TbInstrumentos) object;
        if ((this.idPr == null && other.idPr != null) || (this.idPr != null && !this.idPr.equals(other.idPr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombrePr + " (ID: " + idPr + ")";
    }
}
