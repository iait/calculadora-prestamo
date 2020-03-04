package com.iait.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrestamoCuotaPkEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "prestamo_id")
    private Long prestamoId;
    
    @Column(name = "nro_cuota")
    private Long nroCuota;
    
    public PrestamoCuotaPkEntity() {
    }
    
    public Long getPrestamoId() {
        return prestamoId;
    }
    
    public void setPrestamoId(Long prestamoId) {
        this.prestamoId = prestamoId;
    }
    
    public Long getNroCuota() {
        return nroCuota;
    }
    
    public void setNroCuota(Long nroCuota) {
        this.nroCuota = nroCuota;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nroCuota == null) ? 0 : nroCuota.hashCode());
        result = prime * result + ((prestamoId == null) ? 0 : prestamoId.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrestamoCuotaPkEntity)) {
            return false;
        }
        PrestamoCuotaPkEntity other = (PrestamoCuotaPkEntity) obj;
        if (nroCuota == null) {
            if (other.nroCuota != null) {
                return false;
            }
        } else if (!nroCuota.equals(other.nroCuota)) {
            return false;
        }
        if (prestamoId == null) {
            if (other.prestamoId != null) {
                return false;
            }
        } else if (!prestamoId.equals(other.prestamoId)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "PrestamoCuotaPkEntity [prestamoId=" + prestamoId + ", nroCuota=" + nroCuota + "]";
    }
}
