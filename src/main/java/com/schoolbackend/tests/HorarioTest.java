package com.schoolbackend.tests;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolbackend.models.Aula;
import com.schoolbackend.models.Docente;
import com.schoolbackend.models.Horario;
import com.schoolbackend.models.Materia;
import com.schoolbackend.repositories.AulaRepository;
import com.schoolbackend.repositories.DocenteRepository;
import com.schoolbackend.repositories.HorarioRepository;
import com.schoolbackend.repositories.MateriaRepository;

@Service
public class HorarioTest {
    
    @Autowired
    HorarioRepository hr;

    @Autowired
    AulaRepository ar;

    @Autowired
    DocenteRepository dr;

    @Autowired
    MateriaRepository mr;

    public void horarioCrud(){
        System.out.println("\nHorario CRUD\n");
        Aula    a = ar.findById(1L).orElseThrow();
        Docente d = dr.findById(4L).orElseThrow();
        Materia m = mr.findById(7L).orElseThrow();

        Horario h = new Horario(m, d,a);
        h.setDia    (2);
        h.setInicio (LocalTime.of(8, 0));
        h.setFin    (LocalTime.of(9,30));

        hr.save(h);
        


        Aula    a2 = ar.findById(1L).orElseThrow();
        Docente d2 = dr.findById(3L).orElseThrow();
        Materia m2 = mr.findById(5L).orElseThrow();

        Horario h2 = new Horario(m2,d2,a2);
        h2.setDia    (2);
        h2.setInicio (LocalTime.of(9, 45));
        h2.setFin    (LocalTime.of(11,15));

        hr.save(h2);



        Aula    a3 = ar.findById(1L).orElseThrow();
        Docente d3 = dr.findById(1L).orElseThrow();
        Materia m3 = mr.findById(10L).orElseThrow();

        Horario h3 = new Horario(m3,d3,a3);
        h3.setDia    (2);
        h3.setInicio (LocalTime.of(11, 30));
        h3.setFin    (LocalTime.of(1,0));

        hr.save(h3);

        List<Horario> lista = (List<Horario>) hr.findAll();
        lista.forEach(x->System.out.println(x+"\n"));

        ar.delete(a3);
    }


}
