package com.iait.persona;

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

import com.iait.entities.PersonaEntity;
import com.iait.enums.GeneroEnum;
import com.iait.interfaces.Persona;
import com.iait.testutils.ITestCfg;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersonaManagerIT.TestCfg.class)
public class PersonaManagerIT {
    
    @ComponentScan(basePackages = {
            "com.iait.services", "com.iait.persona", "com.iait.converters"
    })
    public static class TestCfg extends ITestCfg {
        
        @Bean
        public DataSource getDataSource() {
            JdbcDataSource ds = new JdbcDataSource();
            ds.setUrl("jdbc:h2:mem:testdb"
                    + ";INIT=runscript from 'src/test/resources/test-persona-manager.sql'");
            ds.setUser("sa");
            return ds;
        }
    }    

    @Autowired private PersonaManager personaManager;
    
    @Test
    public void test() {
        
        final Persona persona = new Persona() {
            
            @Override
            public Long getTipoDocumentoId() {
                return 1L;
            }
            
            @Override
            public Long getNumeroDocumento() {
                return 12345678L;
            }
            
            @Override
            public String getNombreApellido() {
                return "juan perez";
            }
            
            @Override
            public Long getLocalidadId() {
                return 1L;
            }
            
            @Override
            public GeneroEnum getGenero() {
                return GeneroEnum.MASCULINO;
            }
            
            @Override
            public byte[] getFotoCara() {
                return new byte[]{1, 2, 3};
            }
            
            @Override
            public LocalDate getFechaNacimiento() {
                return LocalDate.of(2020, 1, 1);
            }
            
            @Override
            public Boolean getEsArgentino() {
                return true;
            }
            
            @Override
            public String getCorreoElectronico() {
                return "juan.perez@correo.com";
            }
        };
        
        PersonaEntity personaEntity = personaManager.nuevo(persona);
        
        Assertions.assertThat(personaEntity).isNotNull();
        
        System.out.println(personaEntity);
    }
}
