package com.iait.enums;

public enum GeneroEnum {
    
    MASCULINO("M"),
    FEMENINO("F"),
    ;
    
    private String genero;
    
    private GeneroEnum(String genero) {
        this.genero = genero;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public static GeneroEnum of(String genero) {
        if (genero == null) {
            throw new IllegalArgumentException("El género no puede ser nulo");
        } else if (genero.trim().equalsIgnoreCase("M")) {
            return GeneroEnum.MASCULINO;
        } else if (genero.trim().equalsIgnoreCase("F")) {
            return GeneroEnum.FEMENINO;
        } else {
            throw new IllegalArgumentException(String.format("No se reconoce %s como un género", 
                    genero));
        }
    }
}
