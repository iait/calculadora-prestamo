package com.iait.prestamo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iait.entities.PrestamoEntity;
import com.iait.enums.TipoTasaFinancieraEnum;
import com.iait.interfaces.PrestamoCuota;
import com.iait.math.ctf.ConversorTasaFinanciera;

@Service
public class PrestamoDesarrolloFrances extends PrestamoDesarrolloBase {
    
    @Override
    public PrestamoTotalesCuotas calcular(PrestamoEntity prestamoEntity) {
        
        final long prdAmort = prdAmortDias(prestamoEntity);
        final List<PrestamoCuota> prestamoCuotas = new ArrayList<PrestamoCuota>();
 
        ConversorTasaFinanciera conversor = new ConversorTasaFinanciera();
        
        Optional<BigDecimal> optional = conversor.datosIniciales(
                tf -> {
                    tf.setModulo((long) prestamoEntity.getTasaModulo());
                    tf.setTipo(TipoTasaFinancieraEnum.TE);
                    tf.setValor(prestamoEntity.getTasaEfectiva());
                })
                .convertir(
                        TipoTasaFinancieraEnum.TNV, (long) prestamoEntity.getTasaModulo(), prdAmort)
                .getRazon();
        
        final BigDecimal razon = optional.get();
        final BigDecimal vc = calculoCuota(
                prestamoEntity.getCapitalPrestado(), razon, prestamoEntity.getTotalCuotas());
        
        // DESARROLLO CUOTAS - PRIMER CUOTA

        LocalDate fechaVencimiento = prestamoEntity.getFechaPrimerVto();
        BigDecimal saldoCapital = prestamoEntity.getCapitalPrestado();
        BigDecimal interes = saldoCapital.multiply(razon).setScale(2, RoundingMode.HALF_UP);
        BigDecimal capital = vc.subtract(interes);
        BigDecimal nuevoSaldoCapital = saldoCapital.subtract(capital);
        
        PrestamoAmortizacionDto prestamoPrimeraCuota = new PrestamoAmortizacionDto();
        
        prestamoPrimeraCuota.setNroCuota(1L);
        prestamoPrimeraCuota.setFechaVencimiento(fechaVencimiento);
        prestamoPrimeraCuota.setCapital(capital);
        prestamoPrimeraCuota.setInteres(interes);
        prestamoPrimeraCuota.setSaldoCapital(nuevoSaldoCapital);
        
        prestamoCuotas.add(prestamoPrimeraCuota);
        
        BigDecimal totalIntereses = BigDecimal.ZERO;
        for (int i = 1; i < prestamoEntity.getTotalCuotas(); i++) {

            fechaVencimiento = fechaVencimiento.plusDays(prdAmort);
            saldoCapital = nuevoSaldoCapital;
            interes = saldoCapital.multiply(razon).setScale(2, RoundingMode.HALF_UP);
            capital = vc.subtract(interes);
            nuevoSaldoCapital = saldoCapital.subtract(capital);

            PrestamoAmortizacionDto prestamoCuota = new PrestamoAmortizacionDto();
            
            prestamoCuota.setNroCuota(i + 1L);
            prestamoCuota.setFechaVencimiento(fechaVencimiento);
            prestamoCuota.setCapital(capital);
            prestamoCuota.setInteres(interes);
            totalIntereses = totalIntereses.add(interes);
            prestamoCuota.setSaldoCapital(nuevoSaldoCapital);
            
            prestamoCuotas.add(prestamoCuota);
        }
        
        PrestamoTotalesCuotas prestamoTotalesCuotas = new PrestamoTotalesCuotas();
        prestamoTotalesCuotas.setCuotas(prestamoCuotas);
        prestamoTotalesCuotas.setTotalIntereses(totalIntereses);
        return prestamoTotalesCuotas;
    }

    protected BigDecimal calculoCuota(BigDecimal capital, BigDecimal razon, Integer cuotas) {

        BigDecimal factor = razon.add(BigDecimal.ONE).pow(cuotas)
                .setScale(ESCALA, RoundingMode.HALF_UP);

        BigDecimal resultado = capital.multiply(razon).multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), ESCALA, RoundingMode.HALF_UP);

        return resultado.setScale(2, RoundingMode.HALF_UP);
    }    
}
