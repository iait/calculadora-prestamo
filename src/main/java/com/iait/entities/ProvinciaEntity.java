package com.iait.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iait.converters.RegionConverter;
import com.iait.enums.RegionEnum;

@Entity
@Table(name = "provincias")
public class ProvinciaEntity {
    
    @Id
    @Column(name = "id_provincia", nullable = false)
    private Long id;
    
    @Column(name = "nombre", unique = true, length = 400, nullable = false)
    private String nombre;
    
    @Convert(converter = RegionConverter.class)
    @Column(name = "region", length = 3, nullable = false)
    private RegionEnum region;
    
    public ProvinciaEntity() {}
    
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
    
    public RegionEnum getRegion() {
        return region;
    }
    
    public void setRegion(RegionEnum region) {
        this.region = region;
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
        if (!(obj instanceof ProvinciaEntity)) {
            return false;
        }
        ProvinciaEntity other = (ProvinciaEntity) obj;
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
        return "Provincia [id=" + id + ", nombre=" + nombre + ", region=" + region + "]";
    }
}
