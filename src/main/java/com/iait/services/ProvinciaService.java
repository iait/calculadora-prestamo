package com.iait.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iait.entities.ProvinciaEntity;
import com.iait.entities.QProvinciaEntity;
import com.iait.interfaces.Provincia;
import com.iait.repositories.ProvinciaRepository;
import com.iait.utiles.ExceptionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class ProvinciaService {
    
    @Autowired
    private ProvinciaRepository repository;
    
    @Transactional(readOnly = true)
    public Optional<ProvinciaEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<ProvinciaEntity> buscarTodo() {
        return repository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<ProvinciaEntity> buscar(Function<QProvinciaEntity, BooleanExpression> fun) {
        QProvinciaEntity q = QProvinciaEntity.provinciaEntity;
        BooleanExpression expr = fun.apply(q);
        return (List<ProvinciaEntity>) repository.findAll(expr);
    }
    
    @Transactional
    public ProvinciaEntity alta(Provincia dto) {
        
        ProvinciaEntity provincia = new ProvinciaEntity();
        
        Long id = repository.getMax().orElse(0L) + 1L;
        provincia.setId(id);
        provincia.setNombre(dto.getNombre());
        provincia.setRegion(dto.getRegion());
        
        return repository.save(provincia);
    }
    
    @Transactional
    public ProvinciaEntity actualizar(Long id, Provincia dto) {
        
        ProvinciaEntity provincia = repository.findById(id).orElseThrow(exceptionSupplier(id));
        
        provincia.setNombre(dto.getNombre());
        provincia.setRegion(dto.getRegion());
        
        return repository.save(provincia);
    }
    
    @Transactional
    public void borrar(Long id) {
        
        ProvinciaEntity provincia = repository.findById(id).orElseThrow(exceptionSupplier(id));
        
        repository.delete(provincia);
    }
    
    private Supplier<? extends RuntimeException> exceptionSupplier(Long id) {
        return ExceptionUtils.notFoundExceptionSupplier("No existe la provincia con id %s", id);
    }
}
