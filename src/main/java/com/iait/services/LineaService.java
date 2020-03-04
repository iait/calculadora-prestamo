package com.iait.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.entities.LineaEntity;
import com.iait.interfaces.Linea;
import com.iait.repositories.LineaRepository;
import com.iait.utiles.ExceptionUtils;

@Service
public class LineaService {
    
    @Autowired
    private LineaRepository repository;
    
    @Transactional(readOnly = true)
    public Optional<LineaEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<LineaEntity> buscarTodo() {
        return repository.findAll();
    }
    
    @Transactional
    public LineaEntity alta(Linea dto) {
        
        LineaEntity linea = new LineaEntity();
        
        Long id = repository.getMax().orElse(0L) + 1L;
        linea.setId(id);
        linea.setNombre(dto.getNombre());
        linea.setSistemaAmortizacion(dto.getSistemaAmortizacion());
        linea.setTasaMinima(dto.getTasaMinima());
        linea.setTasaMaxima(dto.getTasaMaxima());
        linea.setCuotasMinimas(dto.getCuotasMinimas());
        linea.setCuotasMaximas(dto.getCuotasMaximas());
        linea.setCapitalMinimo(dto.getCapitalMinimo());
        linea.setCapitalMaximo(dto.getCapitalMaximo());
        linea.setFechaAlta(LocalDate.now());
        
        return repository.save(linea);
    }
    
    @Transactional
    public LineaEntity actualizar(Long id, Linea dto) {
        
        LineaEntity linea = repository.findById(id).orElseThrow(exceptionSupplier(id));
        
        linea.setNombre(dto.getNombre());
        linea.setSistemaAmortizacion(dto.getSistemaAmortizacion());
        linea.setTasaMinima(dto.getTasaMinima());
        linea.setTasaMaxima(dto.getTasaMaxima());
        linea.setCuotasMinimas(dto.getCuotasMinimas());
        linea.setCuotasMaximas(dto.getCuotasMaximas());
        linea.setCapitalMinimo(dto.getCapitalMinimo());
        linea.setCapitalMaximo(dto.getCapitalMaximo());
        
        return repository.save(linea);
    }
    
    @Transactional
    public void borrar(Long id) {
        
        LineaEntity linea = repository.findById(id).orElseThrow(exceptionSupplier(id));
        
        repository.delete(linea);
    }
    
    private Supplier<? extends RuntimeException> exceptionSupplier(Long id) {
        return ExceptionUtils.notFoundExceptionSupplier("No existe la línea con id %s", id);
    }
}
