package com.iait.math.ctf;

import com.iait.enums.TipoTasaFinancieraEnum;

public class CalculadoraRazonFactory {

    public static CalculadoraRazon create(TipoTasaFinancieraEnum tipoTasaEnum) {
        switch (tipoTasaEnum) {
            case TE:
                return new CalculadoraRazonDesdeTe();
            case TNV:
                return new CalculadoraRazonDesdeTnv();
            case TNA:
                return new CalculadoraRazonDesdeTna();
            default:
                throw new IllegalArgumentException(
                        "La calculadora para el tipo de tasa solicitada no existe!!");
        }
    }
}
