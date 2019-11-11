package com.iait.enums;

public enum SistemaAmortizacionEnum {
    
    SISTEMA_FRANCES('F'),
    SISTEMA_ALEMAN('A'),
    ;
    
    private Character value;
    
    private SistemaAmortizacionEnum(Character value) {
        this.value = value;
    }
    
    public Character getValue() {
        return value;
    }
    
    public static SistemaAmortizacionEnum of(Character value) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "El tipo de sistema de amortización no puede ser nulo");
        }
        if (value.equals('F')) {
            return SISTEMA_FRANCES;
        } else if (value.equals('A')) {
            return SISTEMA_ALEMAN;
        } else {
            throw new IllegalArgumentException(String.format(
                    "No se reconoce el tipo de sistema de amortizacion %s", value));
        }
    }
}
