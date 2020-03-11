package com.iait.math.ctf;

import java.math.BigDecimal;

public interface CalculadoraTasa {

    public BigDecimal calcular(BigDecimal razon, long modulo, long dias);
}
