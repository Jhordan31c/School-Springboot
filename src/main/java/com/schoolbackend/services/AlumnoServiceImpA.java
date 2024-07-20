package com.schoolbackend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolbackend.dto.AlumnoMateriaDto2;
import com.schoolbackend.dto.AlumnoMateriaDto3;
import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.AlumnoMateria;
import com.schoolbackend.models.Aula;
import com.schoolbackend.models.Grado;
import com.schoolbackend.models.User;
import com.schoolbackend.repositories.AlumnoMateriaRepository;
import com.schoolbackend.repositories.AlumnoRepository;
import com.schoolbackend.repositories.ApoderadoRepository;
import com.schoolbackend.repositories.AulaRepository;
import com.schoolbackend.repositories.HorarioRepository;

@Service
public class AlumnoServiceImpA implements AlumnoService{

    @Autowired
    ApoderadoRepository apr;

    @Autowired
    AlumnoRepository ar;

    @Autowired
    AlumnoMateriaRepository amr;

    @Autowired
    UserService us;

    @Autowired
    private HorarioRepository hr;

    @Autowired
    AulaRepository aur;

    //LISTA BASE (id, dni, nombre, apellido, genero, fecha_naciemiento, estado, apoderado)
    @Transactional(readOnly = true)
    @Override
    public List<Alumno> findAll() {
        return (List<Alumno>) ar.findAllBasico();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Alumno> findAll(int estado) {
        return (List<Alumno>) ar.findAllBasico(estado);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Alumno> findById(Long id) {
        return ar.findById(id);
    }


    //EXTRAS
    @Transactional(readOnly = true)
    @Override
    public Optional<Alumno> findByIdWithMaterias(Long id) {
        return ar.findByIdWithMaterias(id);
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Alumno> findByIdWithPagos(Long id) {
        return ar.findByIdWithPagos(id);
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Alumno> findByIdWithMateriasPago(Long id) {
        return ar.findByIdWithApoderadoMateriasPagos(id);
    }


    


    //CRUD
    @Transactional
    @Override
    public Optional<Alumno> create(Alumno a) {
        a.setId(null);
        a.setFecha_registro(new Date());
        a.setEstado(1);

        if (a.getUser()!=null) {
            User u = a.getUser();
            a.setUser(us.create(u,1));
        }
        
        Alumno x = ar.save(a);
        return Optional.ofNullable(x);
    }

    

    @Transactional
    @Override
    public Optional<Alumno> update(Alumno a, Long id) {
        Optional<Alumno> op = ar.findByIdWithApoderado(id);
        if(op.isPresent()){
            Alumno x = op.orElseThrow();
            
            if(a.getDni()!=null){
                x.setDni        (a.getDni());
            }
            if (a.getNombre()!=null) {
                x.setNombre     (a.getNombre());
            }

            if (a.getApellido()!=null) {
                x.setApellido   (a.getApellido());
            }
            if (a.getGenero()=='M'||a.getGenero()=='F') {
                x.setGenero     (a.getGenero());
            }
            if (a.getFecha_nacimiento()!=null) {
                x.setFecha_nacimiento(a.getFecha_nacimiento());
            }

            if(a.getApoderado()!=null){
                x.setApoderado(a.getApoderado());
            }
            if(x.getUser() !=null   &&  a.getUser() !=null){
                us.update(x.getUser(), a.getUser());
            }
            else if(x.getUser() ==null  &&  a.getUser() != null){
                User u = a.getUser();
                x.setUser(us.create(u,1));
            }
            
            Alumno x2 = ar.save(x);
            return Optional.of(x2);
        }
        return op;
    }


    @Transactional
    @Override
    public Optional<Alumno> delete(Long id) {
        Optional<Alumno> op = ar.findById(id);
        op.ifPresent(x->ar.delete(x));
        return op;
    }

    @Transactional
    @Override
    public Optional<Alumno> updateEstado(Long id, int estado) {
        Optional<Alumno> op = ar.findByIdWithApoderado(id);
        
        if(op.isPresent()){
            Alumno x = op.orElseThrow();
            x.setEstado(estado);
            return Optional.of(ar.save(x));
        }
        
        return op;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Alumno> findByUser(Long id) {
        return ar.findByIdUser(id);
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Aula> findAulaByAlumno(Long id) {

        Optional<Aula> oa = aur.findByAlumno(id);

        if (oa.isPresent()) {
            Aula x = oa.orElseThrow();
            x.setHorarios(hr.findAllByAula(x.getId()));
            x.setAlumnos(ar.findAllByAula(x.getId()));
            return Optional.of(x);
        }

        return oa;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Alumno> findAllDisponible(int x) {
        return ar.findAllDisponible(x);
    }





    @Transactional(readOnly = true)
    @Override
    public List<Grado> findGradosByAlumno(Long id) {
        return amr.findAllGradosByAlumno(id);
    }
    

    @Transactional(readOnly = true)
    @Override
    public List<AlumnoMateriaDto2> findMaterias(Long idAlumno, Long grado) {
        List<AlumnoMateriaDto2> lista = new ArrayList<>();

        List<AlumnoMateria> materias = amr.findAllByIdAlumnoGrado(idAlumno, grado);
        materias.forEach(m->{
            AlumnoMateriaDto2 dto = new AlumnoMateriaDto2(
                m.getId(), 
                m.getMateria().getNombre(), 
                m.getMateria().getArea().getNombre()
            );
            
            lista.add(dto);
        });

        return lista;
    }
    
    @Transactional(readOnly = true)
    @Override
    public AlumnoMateriaDto3 findMateria(Long idMateria){
        AlumnoMateria       am  = amr.findById(idMateria).orElseThrow();
        AlumnoMateriaDto3   dto = new AlumnoMateriaDto3(
            am.getId(),
            am.getDocente().getApellido().concat(", ").concat(am.getDocente().getNombre()),
            am.getMateria().getNombre(),
            am.getBimestres()
        ); 
        return dto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AlumnoMateria> findAllByAlumno(Long id) {
        return amr.findAllByIdAlumno(id);
    }


    
    
}
