package com.iait.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.entities.PrestamoCuotaEntity;
import com.iait.entities.PrestamoCuotaPkEntity;
import com.iait.entities.PrestamoEntity;
import com.iait.entities.QPrestamoCuotaEntity;
import com.iait.interfaces.PrestamoCuota;
import com.iait.repositories.PrestamoCuotaRepository;
import com.iait.utiles.ExceptionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class PrestamoCuotaService {

    @Autowired private PrestamoCuotaRepository prestamoCuotaRepository;

    @Transactional(readOnly = true)
    public Optional<PrestamoCuotaEntity> findById(Consumer<PrestamoCuotaPkEntity> id) {
        PrestamoCuotaPkEntity pk = new PrestamoCuotaPkEntity();
        id.accept(pk);
        return prestamoCuotaRepository.findById(pk);
    }

    @Transactional(readOnly = true)
    public List<PrestamoCuotaEntity> findAll(
            Function<QPrestamoCuotaEntity, BooleanExpression> function) {
        
        QPrestamoCuotaEntity prestamoCuotaQuery = QPrestamoCuotaEntity.prestamoCuotaEntity;
        BooleanExpression exp = function.apply(prestamoCuotaQuery);
        
        return (List<PrestamoCuotaEntity>) prestamoCuotaRepository.findAll(exp);
    }

    @Transactional
    public PrestamoCuotaEntity nueva(PrestamoEntity prestamoEntity, PrestamoCuota prestamoCuota) {
        
        PrestamoCuotaEntity prestamoCuotaEntity = new PrestamoCuotaEntity();
        
        prestamoCuotaEntity.setPrestamo(prestamoEntity);
        prestamoCuotaEntity.setNroCuota(prestamoCuota.getNroCuota());
        prestamoCuotaEntity.setFechaVencimiento(prestamoCuota.getFechaVencimiento());
        prestamoCuotaEntity.setCapital(prestamoCuota.getCapital());
        prestamoCuotaEntity.setInteres(prestamoCuota.getInteres());
        prestamoCuotaEntity.setTotal(prestamoCuota.getCapital().add(prestamoCuota.getInteres()));
        prestamoCuotaEntity.setSaldoCapital(prestamoCuota.getSaldoCapital());
        
        prestamoCuotaRepository.save(prestamoCuotaEntity);
        
        return prestamoCuotaEntity;
    }

    @Transactional
    public void borrar(Consumer<PrestamoCuotaPkEntity> id) {
        
        PrestamoCuotaPkEntity pk = new PrestamoCuotaPkEntity();
        id.accept(pk);
        
        PrestamoCuotaEntity prestamoCuotaEntity = prestamoCuotaRepository.findById(pk).orElseThrow(
                ExceptionUtils.notFoundExceptionSupplier(
                        "NO EXISTE UN PRESTAMO CON ID %s", pk));
        
        prestamoCuotaRepository.delete(prestamoCuotaEntity);
    }
}
