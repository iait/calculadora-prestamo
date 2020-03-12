package com.iait.prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iait.enums.SistemaAmortizacionEnum;

@Service
public class PrestamoDesarrolloFactory {

    private static PrestamoDesarrolloAleman DESARROLLO_ALEMAN;
    private static PrestamoDesarrolloFrances DESARROLLO_FRANCES;
    
    @Autowired
    public PrestamoDesarrolloFactory(
            PrestamoDesarrolloAleman prestamoDesarrolloAleman,
            PrestamoDesarrolloFrances prestamoDesarrolloFrances) {
        
        PrestamoDesarrolloFactory.DESARROLLO_ALEMAN = prestamoDesarrolloAleman;
        PrestamoDesarrolloFactory.DESARROLLO_FRANCES = prestamoDesarrolloFrances;
    }
    
    public static PrestamoDesarrolloBase create(SistemaAmortizacionEnum sistemaAmortizacion) {
        switch (sistemaAmortizacion) {
            case SISTEMA_ALEMAN:
                return DESARROLLO_ALEMAN;
            case SISTEMA_FRANCES:
                return DESARROLLO_FRANCES;
            default:
                throw new IllegalArgumentException(
                        String.format("EL SISTEMA NO ESTA IMPLMENTADO: %s", sistemaAmortizacion));
        }
    }
}
