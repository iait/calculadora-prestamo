package com.iait.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iait.entities.PrestamoCuotaEntity;
import com.iait.entities.PrestamoCuotaPkEntity;

public interface PrestamoCuotaRepository 
        extends JpaRepository<PrestamoCuotaEntity, PrestamoCuotaPkEntity> {

}
