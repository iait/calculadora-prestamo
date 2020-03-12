package com.iait.prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iait.enums.SistemaAmortizacionEnum;

@Service
public class PrestamoDesarrolloFactory {

    private static PrestamoDesarrolloAleman ALEMAN_BEAN;
    private static PrestamoDesarrolloFrances FRANCES_BEAN;
    
    @Autowired
    public PrestamoDesarrolloFactory(
            PrestamoDesarrolloAleman prestamoDesarrolloAlemanBean,
            PrestamoDesarrolloFrances prestamoDesarrolloFrancesBean) {
        
        PrestamoDesarrolloFactory.ALEMAN_BEAN = prestamoDesarrolloAlemanBean;
        PrestamoDesarrolloFactory.FRANCES_BEAN = prestamoDesarrolloFrancesBean;
    }
    
    public static PrestamoDesarrolloBase create(SistemaAmortizacionEnum sistemaAmortizacion) {
        switch (sistemaAmortizacion) {
            case SISTEMA_ALEMAN:
                return ALEMAN_BEAN;
            case SISTEMA_FRANCES:
                return FRANCES_BEAN;
            default:
                throw new IllegalArgumentException(
                        String.format("EL SISTEMA NO ESTA IMPLMENTADO: %s", sistemaAmortizacion));
        }
    }
}
