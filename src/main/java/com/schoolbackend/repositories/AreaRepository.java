package com.schoolbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.schoolbackend.models.Area;

public interface AreaRepository extends CrudRepository<Area,Long>{
    
    @Query("SELECT a FROM Area a LEFT JOIN FETCH a.materias WHERE a.id=?1")
    Optional<Area> findWithAreas(Long id);
    
}
