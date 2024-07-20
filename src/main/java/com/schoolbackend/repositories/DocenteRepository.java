package com.schoolbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.schoolbackend.dto.ContadorDto;
import com.schoolbackend.models.Docente;

public interface DocenteRepository extends CrudRepository<Docente, Long> {

    @Query("SELECT new Docente(d.id, d.dni, d.nombre, d.apellido, d.correo, d.fecha_registro, d.estado) FROM Docente d")
    List<Docente> findAllBasico();

    @Query("SELECT d FROM Docente d WHERE d.estado = ?1")
    List<Docente> findAllBasico(int estado);

    @Query("SELECT d FROM Docente d WHERE d.user.id = ?1")
    Optional<Docente> findByIdUser(Long id);

    @Query("SELECT NEW com.schoolbackend.dto.ContadorDto(d.estado, COUNT(d)) FROM Docente d "
            + "GROUP BY d.estado")
    List<ContadorDto> contarDocentesEstado();

    long countByEstado(int estado);

}
