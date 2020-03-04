package com.iait.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.iait.entities.TipoDocumentoEntity;

public interface TipoDocumentoRepository extends 
        JpaRepository<TipoDocumentoEntity, Long>, QuerydslPredicateExecutor<TipoDocumentoEntity> {

}
