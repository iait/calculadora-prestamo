package com.iait.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "localidades")
public class LocalidadEntity {
    
    @Id @Column(name = "id_localidad")
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 300)
    private String nombre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia", nullable = false)
    private ProvinciaEntity provincia;
    
    @Column(name = "codigo_postal", nullable = false, length = 10)
    private String codigoPostal;
    
    public LocalidadEntity() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ProvinciaEntity getProvincia() {
        return provincia;
    }
    
    public void setProvincia(ProvinciaEntity provincia) {
        this.provincia = provincia;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalidadEntity)) {
            return false;
        }
        LocalidadEntity other = (LocalidadEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "LocalidadEntity [id=" + id + ", nombre=" + nombre + ", codigoPostal=" + codigoPostal
                + "]";
    }
}
