package com.iait.prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.iait.entities.PrestamoEntity;
import com.iait.enums.UnidadAmortizacionEnum;
import com.iait.prestamo.PrestamoDesarrolloBase.PrestamoTotalesCuotas;

public class PrestamoDesarrolloFrancesTest {

    @Test
    public void givenPrestamoEntity_thenTblAmort12Cuotas() {
        
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        
        prestamoEntity.setId(0L);
        prestamoEntity.setPersona(null);
        prestamoEntity.setLinea(null);
        prestamoEntity.setFechaAlta(LocalDate.now());
        prestamoEntity.setFechaPrimerVto(LocalDate.now().plusDays(30));
        prestamoEntity.setTasaEfectiva(BigDecimal.valueOf(35));
        prestamoEntity.setTasaModulo(365);
        prestamoEntity.setAmortizacionPeriodo(30);
        prestamoEntity.setAmortizacionUnidad(UnidadAmortizacionEnum.DIA);
        prestamoEntity.setCapitalPrestado(BigDecimal.valueOf(100_000));
        prestamoEntity.setTotalCuotas(12);
        
        PrestamoDesarrolloFrances desarrollo = new PrestamoDesarrolloFrances();
        PrestamoTotalesCuotas prestamoTotalesCuotas = desarrollo.calcular(prestamoEntity);
        
        Assertions.assertThat(prestamoTotalesCuotas.getCuotas()).hasSize(12);
        
        prestamoTotalesCuotas.getCuotas().forEach(e -> System.out.println(e));
    }
}
