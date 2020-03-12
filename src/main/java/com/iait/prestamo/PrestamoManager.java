package com.iait.prestamo;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iait.entities.PrestamoEntity;
import com.iait.entities.UsuarioEntity;
import com.iait.executor.TaskExecutor;
import com.iait.interfaces.Prestamo;
import com.iait.prestamo.PrestamoDesarrolloBase.PrestamoTotalesCuotas;

@Component
public class PrestamoManager {
    
    @Autowired private Provider<PrestamoAltaTask> altaTaskProvider;
    @Autowired private Provider<PrestamoAmortizacionTask> amortizacionTaskProvider;
    @Autowired private Provider<PrestamoCuotasAltaTask> cuotasAltaTaskProvider;
    @Autowired private Provider<PrestamoTotalesTask> totalesTaskProvider;
    
    @Transactional
    public PrestamoEntity nuevo(Prestamo prestamo, UsuarioEntity usuario) {
        
        final TaskExecutor executor = new TaskExecutor();
        
        PrestamoAltaTask altaTask = altaTaskProvider.get()
                .setPrestamo(prestamo)
                .setUsuario(usuario);
        PrestamoEntity prestamoEntity = executor.run(altaTask);
        
        PrestamoAmortizacionTask amortizacionTask = amortizacionTaskProvider.get()
                .setPrestamoEntity(prestamoEntity);
        PrestamoTotalesCuotas prestamoTotalesCuotas = executor.run(amortizacionTask);
        
        PrestamoCuotasAltaTask cuotasTask = cuotasAltaTaskProvider.get()
                .setPrestamoEntity(prestamoEntity)
                .setCuotas(prestamoTotalesCuotas.getCuotas());
        executor.run(cuotasTask);
        
        PrestamoTotalesTask totalesTask = totalesTaskProvider.get()
                .setPrestamoEntity(prestamoEntity)
                .setTotalIntereses(prestamoTotalesCuotas.getTotalIntereses());
        executor.run(totalesTask);
        
        return prestamoEntity;
    }

}
