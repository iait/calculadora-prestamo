package com.iait.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.iait.entities.PersonaPkEntity;
import com.iait.entities.UsuarioEntity;

public interface UsuarioRepository extends 
        JpaRepository<UsuarioEntity, PersonaPkEntity>, QuerydslPredicateExecutor<UsuarioEntity> {

}
