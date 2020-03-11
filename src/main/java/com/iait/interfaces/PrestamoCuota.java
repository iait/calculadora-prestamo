package com.iait.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PrestamoCuota {

    public Long getNroCuota();
    
    public LocalDate getFechaVencimiento();

    public BigDecimal getCapital();

    public BigDecimal getInteres();
    
    public BigDecimal getSaldoCapital();
}
