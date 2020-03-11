package com.iait.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.iait.enums.UnidadAmortizacionEnum;

public interface Prestamo {
    
    public Long getDocumentoTipoId();
    
    public Long getNumeroDocumento();
    
    public Long getLineaId();

    public LocalDate getFechaPrimerVto();

    public BigDecimal getTasaEfectiva();

    public Integer getTasaModulo();

    public Integer getAmortizacionPeriodo();

    public UnidadAmortizacionEnum getAmortizacionUnidad();

    public BigDecimal getCapitalPrestado();

    public Integer getTotalCuotas();

}
