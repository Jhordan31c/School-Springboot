package com.schoolbackend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.schoolbackend.models.Rol;


public interface RolRepository extends CrudRepository<Rol, Integer> {
    

    Optional<Rol> findByNombre(String nombre);

}
