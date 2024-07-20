package com.schoolbackend.tests;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.Aula;
import com.schoolbackend.models.Docente;
import com.schoolbackend.models.Grado;
import com.schoolbackend.repositories.AlumnoRepository;
import com.schoolbackend.repositories.AulaRepository;
import com.schoolbackend.repositories.DocenteRepository;
import com.schoolbackend.repositories.GradoRepository;

@Service
public class AulaTest {

    @Autowired
    DocenteRepository dr;

    @Autowired
    AulaRepository ar;

    @Autowired
    GradoRepository gr;

    @Autowired
    AlumnoRepository xr;
    
    @Transactional
    public void docentes(){
        System.out.println("\nDOCENTES\n");
        Docente d1 = new Docente("11112222","AA-aa","BB-bb","aa@aa.com");
        Docente d2 = new Docente("33334444","CC-cc","DD-dd","cc@cc.com");
        Docente d3 = new Docente("55556666","EE-ee","FF-ff","ee@ee.com");
        Docente d4 = new Docente("77778888","GG-gg","HH-hh","hh@hh.com");

        dr.save(d1);
        dr.save(d2);
        dr.save(d3);
        dr.save(d4);

        List<Docente> lista = (List<Docente>) dr.findAll();
        lista.forEach(d-> System.out.println(d));

        Docente x = dr.findById(2L).orElseThrow();
        dr.delete(x);

        List<Docente> lista_2 = (List<Docente>) dr.findAll();
        lista_2.forEach(d-> System.out.println(d));

    }

    @Transactional
    public void aulas(){
        System.out.println("\nAULAS\n");
        Grado   g = gr.findById(7L).orElseThrow();
        Docente d = dr.findById(3L).orElseThrow();
        Aula    a1= new Aula(g,'A',d);

        Grado   g2 = gr.findById(7L).orElseThrow();
        Docente d2 = dr.findById(4L).orElseThrow();
        Aula    a2 = new Aula(g2,'B',d2);

        Grado   g3 = gr.findById(9L).orElseThrow();
        Docente d3 = dr.findById(1L).orElseThrow();
        Aula    a3 = new Aula(g3,'B',d3);

        ar.save(a1);
        ar.save(a2);
        ar.save(a3);

        List<Aula> lista = (List<Aula>) ar.findAll();
        lista.forEach(a->System.out.println(a));

        Aula a4 = ar.findById(2L).orElseThrow();
        ar.delete(a4);
        List<Aula> lista2 = (List<Aula>) ar.findAll();
        lista2.forEach(a->System.out.println(a));
    }
    

    
    @Transactional
    public void aulas2(){
        System.out.println("\nAULAS-ALUMNOS\n");
        Alumno x1 = xr.findById(1L).orElseThrow();
        Alumno x2 = xr.findById(2L).orElseThrow();
        Alumno x3 = xr.findById(3L).orElseThrow();
        Alumno x4 = xr.findById(4L).orElseThrow();
        Alumno x5 = xr.findById(5L).orElseThrow();
        Alumno x6 = xr.findById(6L).orElseThrow();
        //Alumno x7 = xr.findById(7L).orElseThrow();
        Alumno x8 = xr.findById(8L).orElseThrow();

        Aula   a1 = ar.findById(1L).orElseThrow();
        Aula   a3 = ar.findById(3L).orElseThrow();

        a1.getAlumnos().add(x1);
        a1.getAlumnos().add(x2);
        a1.getAlumnos().add(x3);
        a1.getAlumnos().add(x4);
        
        a3.getAlumnos().addAll(Set.of(x4,x5,x6));
        //ACTUALIZANDO LAS AULAS CON LISTAS DE ALUMNOS
        ar.save(a1);
        ar.save(a3);

        //LLAMANDO LA LISTA DE AULAS
        List<Aula> lista = (List<Aula>) ar.findAll();
        lista.forEach(a->{
            System.out.println("----"+a);
        });

        //REVISANDO LAS AULAS CON ALUMNOS REGISTRADOS
        Aula   a11 = ar.findWithAlumnos(1L).orElseThrow();
        a11.getAlumnos().forEach(a->System.out.println("------"+a));
        Aula   a33 = ar.findWithAlumnos(3L).orElseThrow();
        a33.getAlumnos().forEach(a->System.out.println("------"+a));

        //ELIMINANDO REFERENCIA DE ALUMNOS DE LAS AULAS - SIN ELIMNARLOS
        System.out.println("ELIMINANDO ALUMNOS");
        a11.getAlumnos().remove(x1);
        a11.getAlumnos().remove(x2);
        a11.getAlumnos().remove(x3);
        ar.save(a11);
        Aula   aula1 = ar.findWithAlumnos(1L).orElseThrow();
        aula1.getAlumnos().forEach(a->System.out.println("------"+a));
        
        System.out.println("AGREGANDO ALUMNOS");
        aula1.getAlumnos().add(x1);
        aula1.getAlumnos().add(x2);  
        aula1.getAlumnos().add(x4); //No puede agregar un alumno que ya esta agregado
        Aula   aula11 = ar.findWithAlumnos(1L).orElseThrow();
        aula11.getAlumnos().forEach(a->System.out.println("------"+a));
        //ar.delete(aula11);
        xr.delete(x4);
        xr.delete(x8);
    }
}
