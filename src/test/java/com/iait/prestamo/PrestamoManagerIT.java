package com.iait.prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iait.entities.PersonaPkEntity;
import com.iait.entities.PrestamoEntity;
import com.iait.entities.UsuarioEntity;
import com.iait.enums.UnidadAmortizacionEnum;
import com.iait.interfaces.Prestamo;
import com.iait.repositories.UsuarioRepository;
import com.iait.testutils.ITestCfg;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PrestamoManagerIT.TestCfg.class)
public class PrestamoManagerIT {
    
    @ComponentScan(basePackages = {"com.iait.services", "com.iait.prestamo", "com.iait.converters"})
    public static class TestCfg extends ITestCfg {
        
        @Bean
        public DataSource getDataSource() {
            JdbcDataSource ds = new JdbcDataSource();
            ds.setUrl("jdbc:h2:mem:testdb"
                    + ";INIT=runscript from 'src/test/resources/test-prestamo-manager.sql'");
            ds.setUser("sa");
            return ds;
        }
    }    

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PrestamoManager prestamoManager;
    
    @Test
    public void test() {
        
        final Prestamo prestamo = new Prestamo() {
            
            @Override
            public Long getDocumentoTipoId() {
                return 1L;
            }
            
            @Override
            public Long getNumeroDocumento() {
                return 1L;
            }
            
            @Override
            public Long getLineaId() {
                return 1L;
            }
            
            @Override
            public LocalDate getFechaPrimerVto() {
                return LocalDate.now().plusDays(30L);
            }
            
            @Override
            public BigDecimal getTasaEfectiva() {
                return BigDecimal.valueOf(35);
            }
            
            @Override
            public Integer getTasaModulo() {
                return 365;
            }
            
            @Override
            public Integer getAmortizacionPeriodo() {
                return 30;
            }
            
            @Override
            public UnidadAmortizacionEnum getAmortizacionUnidad() {
                return UnidadAmortizacionEnum.DIA;
            }
            
            @Override
            public BigDecimal getCapitalPrestado() {
                return BigDecimal.valueOf(100000);
            }
            
            @Override
            public Integer getTotalCuotas() {
                return 12;
            }
        };
        
        final UsuarioEntity usuarioEntity = usuarioRepository
                .findById(new PersonaPkEntity(1L, 1L))
                .get();
        
        PrestamoEntity prestamoEntity = prestamoManager.nuevo(prestamo, usuarioEntity);
        Assertions.assertThat(prestamoEntity).isNotNull();
        
        System.out.println(prestamoEntity);
    }

}
