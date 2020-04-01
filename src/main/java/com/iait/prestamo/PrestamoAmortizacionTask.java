package com.iait.prestamo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.iait.entities.PrestamoEntity;
import com.iait.prestamo.PrestamoDesarrolloBase.PrestamoTotalesCuotas;
import com.iait.tasks.PrestamoTask;

@Component
@Scope("prototype")
public class PrestamoAmortizacionTask implements PrestamoTask<PrestamoTotalesCuotas> {
    
    private PrestamoEntity prestamoEntity;
    
    public PrestamoAmortizacionTask setPrestamoEntity(PrestamoEntity prestamoEntity) {
        this.prestamoEntity = prestamoEntity;
        return this;
    }
    
    @Override
    public PrestamoTotalesCuotas execute() {
        PrestamoDesarrolloBase prestamoDesarrollo = PrestamoDesarrolloFactory.create(
                prestamoEntity.getSistemaAmortizacion());
        return prestamoDesarrollo.calcular(prestamoEntity);
    }

}
