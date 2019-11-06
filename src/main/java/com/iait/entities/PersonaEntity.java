package com.iait.entities;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iait.enums.GeneroEnum;

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
    private LocalDateTime fechaNacimiento;
    
    @Column(name = "genero", nullable = false, length = 1)
    private GeneroEnum genero;
    
    @Column(name = "es_argentino", nullable = false)
    private Boolean esArgentino;
    
    @Column(name = "correo_electronico", length = 300)
    private String correoElectronico;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "foto_cara")
    private byte[] fotoCara;
    
//    private Loca
}
