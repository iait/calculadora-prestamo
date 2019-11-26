package com.iait.dtos;

import java.math.BigDecimal;

import com.iait.enums.SistemaAmortizacionEnum;

public interface LineaDto {
    
    String getNombre();
    
    SistemaAmortizacionEnum getSistemaAmortizacion();
    
    BigDecimal getTasaMinima();
    
    BigDecimal getTasaMaxima();
    
    Long getCuotasMinimas();
    
    Long getCuotasMaximas();
    
    BigDecimal getCapitalMinimo();
    
    BigDecimal getCapitalMaximo();
}
