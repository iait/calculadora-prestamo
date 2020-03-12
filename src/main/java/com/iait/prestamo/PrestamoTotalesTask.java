package com.iait.prestamo;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.iait.entities.PrestamoEntity;
import com.iait.task.Task;

@Component
public class PrestamoTotalesTask implements Task<Void> {

    private PrestamoEntity prestamoEntity;
    
    private BigDecimal totalIntereses;
    
    public PrestamoTotalesTask setPrestamoEntity(PrestamoEntity prestamoEntity) {
        this.prestamoEntity = prestamoEntity;
        return this;
    }
    
    public PrestamoTotalesTask setTotalIntereses(BigDecimal totalIntereses) {
        this.totalIntereses = totalIntereses;
        return this;
    }
    
    @Override
    public Void execute() {
        prestamoEntity.setTotalIntereses(totalIntereses);
        return null;
    }

}
