package com.iait.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.entities.LineaEntity;
import com.iait.entities.PersonaEntity;
import com.iait.entities.PersonaPkEntity;
import com.iait.entities.PrestamoEntity;
import com.iait.entities.QPrestamoEntity;
import com.iait.entities.UsuarioEntity;
import com.iait.enums.TasaTipoEnum;
import com.iait.enums.TipoTasaFinancieraEnum;
import com.iait.enums.UnidadAmortizacionEnum;
import com.iait.exceptions.DataIntegrityViolationServiceException;
import com.iait.interfaces.Prestamo;
import com.iait.math.ctf.ConversorTasaFinanciera;
import com.iait.math.ctf.TasaFinanciera;
import com.iait.repositories.LineaRepository;
import com.iait.repositories.PersonaRepository;
import com.iait.repositories.PrestamoRepository;
import com.iait.utiles.ExceptionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class PrestamoService {

    @Autowired private LineaRepository lineaRepository;
    @Autowired private PersonaRepository personaRepository;
    @Autowired private PrestamoRepository prestamoRepository;

    @Transactional(readOnly = true)
    public Optional<PrestamoEntity> findById(Long id) {
        return prestamoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<PrestamoEntity> findAll(Function<QPrestamoEntity, BooleanExpression> function) {
        
        QPrestamoEntity prestamoQuery = QPrestamoEntity.prestamoEntity;
        BooleanExpression exp = function.apply(prestamoQuery);
        
        return (List<PrestamoEntity>) prestamoRepository.findAll(exp);
    }
    
    @Transactional
    public PrestamoEntity nuevo(Prestamo prestamo, UsuarioEntity usuarioEntity) {
        
        final long id = prestamoRepository.getMax().orElse(0L) +  1L;
        
        final LineaEntity linea = lineaRepository.findById(prestamo.getLineaId()).orElseThrow(
                ExceptionUtils.notFoundExceptionSupplier(
                        "NO EXISTE UNA LINEA CON ID %s", prestamo.getLineaId()));

        final PersonaEntity persona = personaRepository.findById(
                        new PersonaPkEntity(
                                prestamo.getDocumentoTipoId(), prestamo.getNumeroDocumento()))
                .orElseThrow(
                        ExceptionUtils.notFoundExceptionSupplier(
                                "NO EXISTE UNA LINEA CON ID %s", prestamo.getLineaId()));
        
        // *** VALIDACIONES RESPECTO DE LA LINEA DE PRESTAMO
        
        // VALIDACION: LA TASA DEL PRESTAMO ESTA EN EL RANGO ADMITIDO DE LA LINEA
        
        BigDecimal tasa = prestamo.getTasaEfectiva();
        long modulo = prestamo.getTasaModulo();
        long dias = prestamo.getAmortizacionUnidad().equals(UnidadAmortizacionEnum.DIA) 
                ? modulo : modulo * 30;
        
        if (linea.getTasaTipo().equals(TasaTipoEnum.NOMINAL)) {
            tasa = convertirTasaEfectivaEnNominal(tasa, modulo, dias);
        }
        
        if (tasa.compareTo(linea.getTasaMinima()) == -1) {
            throw new DataIntegrityViolationServiceException(
                    "LA TASA [%s] ES MENOR A LA ADMITIDA EN LA LINEA: %s %s", 
                    tasa,
                    linea.getTasaMinima(),
                    linea);
        }

        if (tasa.compareTo(linea.getTasaMaxima()) == 1) {
            throw new DataIntegrityViolationServiceException(
                    "LA TASA [%s] ES MAYOR A LA ADMITIDA EN LA LINEA: %s %s", 
                    tasa,
                    linea.getTasaMaxima(),
                    linea);
        }

        // VALIDACION: EL TOTAL DE CUOTAS DEL PRESTAMO ESTA EN EL RANGO ADMITIDO DE LA LINEA
        
        if (prestamo.getTotalCuotas().compareTo(linea.getCuotasMinimas()) < 0) {
            throw new DataIntegrityViolationServiceException(
                    "LA CANTIDAD TOTAL DE CUOTA [%s] ES MENOR A LA ADMITIDA EN LA LINEA: %s %s", 
                    prestamo.getTotalCuotas(),
                    linea.getCuotasMinimas(),
                    linea);
        }

        if (prestamo.getTotalCuotas().compareTo(linea.getCuotasMaximas()) > 0) {
            throw new DataIntegrityViolationServiceException(
                    "LA CANTIDAD TOTAL DE CUOTA [%s] ES MAYOR A LA ADMITIDA EN LA LINEA: %s %s", 
                    prestamo.getTotalCuotas(),
                    linea.getCuotasMaximas(),
                    linea);
        }

        // VALIDACION: EL TOTAL DE CAPITAL DEL PRESTAMO ESTA EN EL RANGO ADMITIDO DE LA LINEA
        
        if (prestamo.getCapitalPrestado().compareTo(linea.getCapitalMinimo()) == -1) {
            throw new DataIntegrityViolationServiceException(
                    "EL CAPITAL [%s] ES MENOR AL ADMITIDO EN LA LINEA: %s %s", 
                    prestamo.getCapitalPrestado(),
                    linea.getCapitalMinimo(),
                    linea);
        }

        if (prestamo.getCapitalPrestado().compareTo(linea.getCapitalMaximo()) == 1) {
            throw new DataIntegrityViolationServiceException(
                    "EL CAPITAL [%s] ES MAYOR AL ADMITIDO EN LA LINEA: %s %s", 
                    prestamo.getCapitalPrestado(),
                    linea.getCapitalMaximo(),
                    linea);
        }
        
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        
        prestamoEntity.setId(id);
        prestamoEntity.setPersona(persona);
        prestamoEntity.setLinea(linea);
        prestamoEntity.setSistemaAmortizacion(linea.getSistemaAmortizacion());
        prestamoEntity.setFechaAlta(LocalDate.now());
        prestamoEntity.setFechaPrimerVto(prestamo.getFechaPrimerVto());
        prestamoEntity.setTasaEfectiva(prestamo.getTasaEfectiva());
        prestamoEntity.setTasaModulo(prestamo.getTasaModulo());
        prestamoEntity.setAmortizacionPeriodo(prestamo.getAmortizacionPeriodo());
        prestamoEntity.setAmortizacionUnidad(prestamo.getAmortizacionUnidad());
        prestamoEntity.setCapitalPrestado(prestamo.getCapitalPrestado());
        prestamoEntity.setTotalIntereses(BigDecimal.ZERO);
        prestamoEntity.setTotalCuotas(prestamo.getTotalCuotas());
        prestamoEntity.setUsuario(usuarioEntity);
                
        prestamoRepository.save(prestamoEntity);
        
        return prestamoEntity;
    }
    
    @Transactional
    public void borrar(Long prestamoId) {
        
        PrestamoEntity prestamoEntity = prestamoRepository.findById(prestamoId).orElseThrow(
                ExceptionUtils.notFoundExceptionSupplier(
                        "NO EXISTE UN PRESTAMO CON ID %s", prestamoId));
        
        prestamoRepository.delete(prestamoEntity);
    }
    
    private BigDecimal convertirTasaEfectivaEnNominal(BigDecimal tasa, long modulo, long dias) {
        
        ConversorTasaFinanciera conversor = new ConversorTasaFinanciera();
        
        Optional<TasaFinanciera> resultado = conversor
                .datosIniciales(tf -> {
                    tf.setModulo(modulo);
                    tf.setTipo(TipoTasaFinancieraEnum.TE);
                    tf.setValor(tasa);
                }).convertir(TipoTasaFinancieraEnum.TNV, modulo, dias)
                .getResultado();
        
        return resultado.get().getValor();
    }
}
