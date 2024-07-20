package com.schoolbackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolbackend.dto.AlumnoMateriaDto2;
import com.schoolbackend.dto.AlumnoMateriaDto3;
import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.AlumnoMateria;
import com.schoolbackend.models.Aula;
import com.schoolbackend.models.Grado;
import com.schoolbackend.services.AlumnoService;
import com.schoolbackend.validations.Validacion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService as;

    @Autowired 
    private Validacion v;

    //LISTA BASE (id, dni, nombre, apellido, genero, fecha_naciemiento, estado, apoderado)
    @GetMapping
    public List<Alumno> getLista(){
        return as.findAll();
    }

    
    @GetMapping("/disponible/{x}")
    public List<Alumno> getListaDisponible(@PathVariable(name = "x") int estado){
        return as.findAllDisponible(estado);
    }

    

    @GetMapping("/estado/{x}")
    public List<Alumno> getLista(@PathVariable int x){
        return as.findAll(x);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id)
    {
        Optional<Alumno> oa = as.findById(id);
        if(oa.isPresent()){
            Alumno x = oa.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(x);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }







    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Alumno a, BindingResult br)
    {
        if(br.hasFieldErrors()){
            return v.informe(br);
        }
        Optional<Alumno> oa = as.create(a);
        if(oa.isPresent()){
            Alumno x = oa.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(x);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Alumno a, @PathVariable Long id)
    {
        Optional<Alumno> oa = as.update(a, id);
        if(oa.isPresent()){
            Alumno x = oa.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(x);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } 

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id)
    {
        Optional<Alumno> oa = as.delete(id);
        if (oa.isPresent()) {
            Alumno x = oa.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(x);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }





    @PutMapping("/{id}/{x}")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @PathVariable int x)
    {
        Optional<Alumno> oa = as.updateEstado(id, x);
        if (oa.isPresent()) {
            Alumno a = oa.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(a);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/aula/{id}")
    public ResponseEntity<?> buscarAula(@PathVariable Long id)
    {
        //Optional<Aula> oa = as.findWithHorariosAlumnos(id);
        Optional<Aula> oa = as.findAulaByAlumno(id);
        if(oa.isPresent()){
            return ResponseEntity.ok().body(oa.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    
    
    @GetMapping("/materias/{x1}/{x2}")
    public List<AlumnoMateriaDto2> getMaterias( @PathVariable(name = "x1")  Long idAlumno, 
                                                @PathVariable(name = "x2")  Long idGrado)
    {
        return as.findMaterias(idAlumno, idGrado);
    }

    
    @GetMapping("materias/{id}")
    public AlumnoMateriaDto3 buscarMateria(@PathVariable Long id)
    {
        return as.findMateria(id);
    }



    @GetMapping("/grados/{id}")
    public List<Grado> getGrados(@PathVariable Long id){
        return as.findGradosByAlumno(id);
    }


    @GetMapping("/materias-todo/{id}")
    public List<AlumnoMateria> findAllByAlumno(@PathVariable Long id) {
        return as.findAllByAlumno(id);
    }
}
