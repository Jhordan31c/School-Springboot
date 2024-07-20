package com.schoolbackend.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.schoolbackend.dto.ContadorDto;
import com.schoolbackend.models.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

        @Query("SELECT a FROM Alumno a LEFT JOIN FETCH a.apoderado WHERE a.id = ?1")
        Optional<Alumno> findByIdWithApoderado(Long id);

        @Query("SELECT a FROM Alumno a LEFT JOIN FETCH a.materias WHERE a.id =?1")
        Optional<Alumno> findByIdWithMaterias(Long id);

        @Query("SELECT a FROM Alumno a LEFT JOIN FETCH a.pagos WHERE a.id =?1")
        Optional<Alumno> findByIdWithPagos(Long id);

        @Query("SELECT a FROM Alumno a LEFT JOIN FETCH a.apoderado LEFT JOIN FETCH a.materias LEFT JOIN FETCH a.pagos WHERE a.id = ?1")
        Optional<Alumno> findByIdWithApoderadoMateriasPagos(Long id);

        @Query("SELECT a FROM Alumno a WHERE a.estado =?1")
        List<Alumno> findAllByEstado(Integer estado);

        @Query("SELECT new Alumno(a.id, a.dni, a.nombre, a.apellido, a.genero, a.fecha_nacimiento, a.estado) FROM Alumno a WHERE a.id = ?1")
        Optional<Alumno> findBasicoById(Long id);

        @Query("SELECT new Alumno(a.id, a.dni, a.nombre, a.apellido, a.genero, a.fecha_nacimiento, a.estado) FROM Alumno a")
        List<Alumno> findAllBasico();

        @Query("SELECT new Alumno(a.id, a.dni, a.nombre, a.apellido, a.genero, a.fecha_nacimiento, a.estado) FROM Alumno a WHERE a.estado = ?1")
        List<Alumno> findAllBasico(int estado);

        @Query("SELECT new Alumno(a.id, a.dni, a.nombre, a.apellido, a.genero, a.fecha_nacimiento, a.estado) FROM Alumno a JOIN a.aulas au WHERE au.id = ?1")
        Set<Alumno> findAllByAula(Long aulaId);

        @Query("SELECT NEW com.schoolbackend.dto.ContadorDto(a.estado, COUNT(a)) AS cantidad FROM Alumno a "
                        + "GROUP BY a.estado")
        List<ContadorDto> contarAlumnosEstado();

        long countByEstado(int estado);

        @Query("SELECT a FROM Alumno a WHERE a.user.id = ?1")
        Optional<Alumno> findByIdUser(Long id);

        @Query("SELECT new Alumno(a.id, a.dni, a.nombre, a.apellido, a.genero, a.fecha_nacimiento, a.estado) FROM Alumno a "
                        + "WHERE NOT EXISTS (SELECT 1 FROM a.aulas au WHERE YEAR(a.fecha_registro) = YEAR(CURRENT_DATE) ) AND a.estado = ?1")
        List<Alumno> findAllDisponible(int id);

}
