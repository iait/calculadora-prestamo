package com.iait.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.iait.entities.PersonaEntity;
import com.iait.entities.PersonaPkEntity;

public interface PersonaRepository extends 
        JpaRepository<PersonaEntity, PersonaPkEntity>, QuerydslPredicateExecutor<PersonaEntity> {

}
