package com.schoolbackend.services;

import java.util.List;
import java.util.Optional;

import com.schoolbackend.dto.AlumnoMateriaDto2;
import com.schoolbackend.dto.AlumnoMateriaDto3;
import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.AlumnoMateria;
import com.schoolbackend.models.Aula;
import com.schoolbackend.models.Grado;



public interface AlumnoService {

    public List<Alumno> findAll();

    public List<Alumno> findAll(int estado);

    
    public Optional<Alumno> findByIdWithMaterias(Long id);

    public Optional<Alumno> findByIdWithPagos(Long id);

    public Optional<Alumno> findByIdWithMateriasPago(Long id);


    public Optional<Alumno> findById(Long id);

    public Optional<Alumno> create(Alumno a);

    public Optional<Alumno> update(Alumno a, Long id);

    public Optional<Alumno> delete(Long id);

    public Optional<Alumno> updateEstado(Long id, int estado);

    public Optional<Alumno> findByUser(Long id);

    public Optional<Aula> findAulaByAlumno(Long id);

    public List<Alumno> findAllDisponible(int x);

    public List<Grado> findGradosByAlumno(Long id);
    
    public List<AlumnoMateriaDto2> findMaterias(Long idAlumno, Long grado);
    
    public AlumnoMateriaDto3 findMateria(Long idMateria);

    public List<AlumnoMateria> findAllByAlumno(Long id);
}
