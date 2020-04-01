package com.iait.interfaces;

import java.time.LocalDate;

import com.iait.enums.GeneroEnum;

public interface Persona {

    public Long getTipoDocumentoId();
    
    public Long getNumeroDocumento();
    
    public String getNombreApellido();
    
    public LocalDate getFechaNacimiento();
    
    public GeneroEnum getGenero();
    
    public Boolean getEsArgentino();
    
    public String getCorreoElectronico();

    public byte[] getFotoCara();
    
    public Long getLocalidadId();

}
