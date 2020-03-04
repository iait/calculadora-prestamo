package com.iait.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prestamos_cuotas")
public class PrestamoCuotaEntity {
    
    @EmbeddedId
    private PrestamoCuotaPkEntity pk;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestamo_id", referencedColumnName = "prestamo_id",
            nullable = false, insertable = false, updatable = false)
    private PrestamoEntity prestamo;
    
    @Column(name = "nro_cuota", nullable = false, insertable = false, updatable = false)
    private Long nroCuota;
    
    @Column(name = "importe_capital")
    private BigDecimal importeCapital;
    
    @Column(name = "importe_interes")
    private BigDecimal importeInteres;
    
    @Column(name = "importe_total")
    private BigDecimal importeTotal;
    
    public PrestamoCuotaEntity() {
    }
    
    public PrestamoEntity getPrestamo() {
        return prestamo;
    }
    
    public void setPrestamo(PrestamoEntity prestamo) {
        this.prestamo = prestamo;
        pk.setPrestamoId(prestamo.getId());
    }
    
    public Long getNroCuota() {
        return nroCuota;
    }
    
    public void setNroCuota(Long nroCuota) {
        this.nroCuota = nroCuota;
        pk.setNroCuota(nroCuota);
    }
    
    public BigDecimal getImporteCapital() {
        return importeCapital;
    }
    
    public void setImporteCapital(BigDecimal importeCapital) {
        this.importeCapital = importeCapital;
    }
    
    public BigDecimal getImporteInteres() {
        return importeInteres;
    }
    
    public void setImporteInteres(BigDecimal importeInteres) {
        this.importeInteres = importeInteres;
    }
    
    public BigDecimal getImporteTotal() {
        return importeTotal;
    }
    
    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrestamoCuotaEntity)) {
            return false;
        }
        PrestamoCuotaEntity other = (PrestamoCuotaEntity) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "PrestamoCuotaEntity [nroCuota=" + nroCuota + ", importeCapital=" + importeCapital 
                + ", importeInteres=" + importeInteres + ", importeTotal=" + importeTotal + "]";
    }
}
