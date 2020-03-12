package com.iait.prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.iait.entities.PrestamoEntity;
import com.iait.enums.UnidadAmortizacionEnum;
import com.iait.interfaces.PrestamoCuota;

public abstract class PrestamoDesarrolloBase {
    
    public abstract PrestamoTotalesCuotas calcular(PrestamoEntity prestamoEntity);
    
    protected static final int ESCALA = 6;

    protected int prdAmortDias(PrestamoEntity prestamo) {

        int factorPrdAmort = prestamo.getAmortizacionUnidad()
                .equals(UnidadAmortizacionEnum.DIA) ? 1 : 30;
        int prdAmort = factorPrdAmort * prestamo.getAmortizacionPeriodo();
        
        return prdAmort;
    }
    
    public class PrestamoTotalesCuotas {
        
        private BigDecimal totalIntereses;
        private List<PrestamoCuota> cuotas;
        
        public BigDecimal getTotalIntereses() {
            return totalIntereses;
        }
        
        public void setTotalIntereses(BigDecimal totalIntereses) {
            this.totalIntereses = totalIntereses;
        }
        
        public List<PrestamoCuota> getCuotas() {
            return cuotas;
        }
        
        public void setCuotas(List<PrestamoCuota> cuotas) {
            this.cuotas = cuotas;
        }
    }
    
    protected class PrestamoAmortizacionDto implements PrestamoCuota {

        private Long nroCuota;
        private LocalDate fechaVencimiento;
        private BigDecimal capital;
        private BigDecimal interes;
        private BigDecimal saldoCapital;
        
        @Override
        public Long getNroCuota() {
            return nroCuota;
        }
        
        public void setNroCuota(Long nroCuota) {
            this.nroCuota = nroCuota;
        }
        
        @Override
        public LocalDate getFechaVencimiento() {
            return fechaVencimiento;
        }

        public void setFechaVencimiento(LocalDate fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }

        @Override
        public BigDecimal getCapital() {
            return capital;
        }
        
        public void setCapital(BigDecimal capital) {
            this.capital = capital;
        }
        
        @Override
        public BigDecimal getInteres() {
            return interes;
        }
        
        public void setInteres(BigDecimal interes) {
            this.interes = interes;
        }

        @Override
        public BigDecimal getSaldoCapital() {
            return saldoCapital;
        }

        public void setSaldoCapital(BigDecimal saldoCapital) {
            this.saldoCapital = saldoCapital;
        }

        @Override
        public String toString() {
            return "PrestamoAmortizacionDto [cuota=" + nroCuota 
                    + ", fechaVencimiento=" + fechaVencimiento + ", capital="
                    + capital + ", interes=" + interes + ", saldoCapital=" + saldoCapital + "]";
        }
        
    }
}
