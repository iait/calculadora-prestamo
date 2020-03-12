package com.iait.prestamo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.iait.entities.PrestamoEntity;
import com.iait.interfaces.PrestamoCuota;
import com.iait.services.PrestamoCuotaService;
import com.iait.task.Task;

@Component
@Scope("prototype")
public class PrestamoCuotasAltaTask implements Task<Void> {

    @Autowired private PrestamoCuotaService cuotaService;
    
    private PrestamoEntity prestamoEntity;
    private List<PrestamoCuota> cuotas;
    
    public PrestamoCuotasAltaTask setPrestamoEntity(PrestamoEntity prestamoEntity) {
        this.prestamoEntity = prestamoEntity;
        return this;
    }
    
    public PrestamoCuotasAltaTask setCuotas(List<PrestamoCuota> cuotas) {
        this.cuotas = cuotas;
        return this;
    }
    
    @Override
    public Void execute() {
        cuotas.forEach(cuota -> cuotaService.nueva(prestamoEntity, cuota));
        return null;
    }

}
