package com.iait.entities;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iait.converters.GeneroConverter;
import com.iait.enums.GeneroEnum;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@Entity
@Table(name = "personas")
public class PersonaEntity {
    
    @EmbeddedId
    private PersonaPkEntity pk;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipodocumento", referencedColumnName = "id_tipodocumento", 
            nullable = false, insertable = false, updatable = false)
    private TipoDocumentoEntity tipoDocumento;
    
    @Column(name = "numero_documento", nullable = false, insertable = false, updatable = false)
    private Long numeroDocumento;
    
    @Column(name = "nombre_apellido", nullable = false, length = 400, unique = true)
    private String nombreApellido;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Convert(converter = GeneroConverter.class)
    @Column(name = "genero", nullable = false, length = 1)
    private GeneroEnum genero;
    
    @Column(name = "es_argentino", nullable = false)
    private Boolean esArgentino;
    
    @Column(name = "correo_electronico", length = 300)
    private String correoElectronico;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "foto_cara")
    private byte[] fotoCara;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad", nullable = false)
    private LocalidadEntity localidad;
    
    @QueryType(PropertyType.NONE)
    @Column(name = "codigo_postal", nullable = false, length = 10)
    private String codigoPostal;
    
    public PersonaEntity() {
        this.pk = new PersonaPkEntity();
    }
    
    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }
    
    public void setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        pk.setTipoDocumentoId(tipoDocumento.getId());
    }
    
    public Long getNumeroDocumento() {
        return numeroDocumento;
    }
    
    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        pk.setNumeroDocumento(numeroDocumento);
    }
    
    public String getNombreApellido() {
        return nombreApellido;
    }
    
    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public GeneroEnum getGenero() {
        return genero;
    }
    
    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }
    
    public Boolean getEsArgentino() {
        return esArgentino;
    }
    
    public void setEsArgentino(Boolean esArgentino) {
        this.esArgentino = esArgentino;
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public byte[] getFotoCara() {
        return fotoCara;
    }
    
    public void setFotoCara(byte[] fotoCara) {
        this.fotoCara = fotoCara;
    }
    
    public LocalidadEntity getLocalidad() {
        return localidad;
    }
    
    public void setLocalidad(LocalidadEntity localidad) {
        this.localidad = localidad;
        this.codigoPostal = localidad.getCodigoPostal();
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
        if (!(obj instanceof PersonaEntity)) {
            return false;
        }
        PersonaEntity other = (PersonaEntity) obj;
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
        return "PersonaEntity [numeroDocumento=" + numeroDocumento + ", nombreApellido=" 
                + nombreApellido + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero 
                + "]";
    }
}
