package com.iait.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iait.entities.ProvinciaEntity;

public interface ProvinciaRepository extends JpaRepository<ProvinciaEntity, Long> {
    
    @Query("select max(e.id) from ProvinciaEntity e")
    Optional<Long> getMax();
}
