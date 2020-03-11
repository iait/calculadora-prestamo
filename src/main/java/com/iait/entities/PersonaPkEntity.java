package com.iait.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonaPkEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "id_tipodocumento")
    private Long tipoDocumentoId;
    
    @Column(name = "numero_documento")
    private Long numeroDocumento;
    
    public PersonaPkEntity() {
    }
    
    public PersonaPkEntity(Long tipoDocumentoId, Long numeroDocumento) {
        this.tipoDocumentoId = tipoDocumentoId;
        this.numeroDocumento = numeroDocumento;
    }
    
    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }
    
    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
    
    public Long getNumeroDocumento() {
        return numeroDocumento;
    }
    
    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
        result = prime * result + ((tipoDocumentoId == null) ? 0 : tipoDocumentoId.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonaPkEntity)) {
            return false;
        }
        PersonaPkEntity other = (PersonaPkEntity) obj;
        if (numeroDocumento == null) {
            if (other.numeroDocumento != null) {
                return false;
            }
        } else if (!numeroDocumento.equals(other.numeroDocumento)) {
            return false;
        }
        if (tipoDocumentoId == null) {
            if (other.tipoDocumentoId != null) {
                return false;
            }
        } else if (!tipoDocumentoId.equals(other.tipoDocumentoId)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "PersonaPkEntity [tipoDocumentoId=" + tipoDocumentoId + ", numeroDocumento=" 
                + numeroDocumento + "]";
    }
}
