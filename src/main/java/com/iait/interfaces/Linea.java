package com.iait.interfaces;

import java.math.BigDecimal;

import com.iait.enums.SistemaAmortizacionEnum;

public interface Linea {
    
    String getNombre();
    
    SistemaAmortizacionEnum getSistemaAmortizacion();
    
    BigDecimal getTasaMinima();
    
    BigDecimal getTasaMaxima();
    
    Integer getCuotasMinimas();
    
    Integer getCuotasMaximas();
    
    BigDecimal getCapitalMinimo();
    
    BigDecimal getCapitalMaximo();
}
