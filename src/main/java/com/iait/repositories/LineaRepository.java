package com.iait.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iait.entities.LineaEntity;

public interface LineaRepository extends JpaRepository<LineaEntity, Long> {
    
    @Query("select max(e.id) from LineaEntity e")
    Optional<Long> getMax();
}
