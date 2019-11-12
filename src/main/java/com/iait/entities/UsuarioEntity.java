package com.iait.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    
    @EmbeddedId
    private PersonaPkEntity pk;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("pk")
    @JoinColumns({ 
            @JoinColumn(name = "id_tipodocumento", referencedColumnName = "id_tipodocumento"),
            @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento")
    })
    private PersonaEntity persona;
    
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "hashed_pwd", nullable = false, length = 200)
    private String hashedPwd;
    
    public UsuarioEntity() {}
    
    public PersonaEntity getPersona() {
        return persona;
    }
    
    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
        pk.setTipoDocumentoId(persona.getTipoDocumento().getId());
        pk.setNumeroDocumento(persona.getNumeroDocumento());
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getHashedPwd() {
        return hashedPwd;
    }
    
    public void setHashedPwd(String hashedPwd) {
        this.hashedPwd = hashedPwd;
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
        if (!(obj instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) obj;
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
        return "UsuarioEntity [nombre=" + nombre + "]";
    }
}
