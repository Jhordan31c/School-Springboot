package com.schoolbackend.tests;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.Area;
import com.schoolbackend.models.Cita;
import com.schoolbackend.models.Docente;
import com.schoolbackend.models.Evento;
import com.schoolbackend.models.Materia;
import com.schoolbackend.repositories.AlumnoRepository;
import com.schoolbackend.repositories.AreaRepository;
import com.schoolbackend.repositories.CitaRepository;
import com.schoolbackend.repositories.DocenteRepository;
import com.schoolbackend.repositories.EventoRepository;
import com.schoolbackend.repositories.MateriaRepository;

@Service
public class GeneralTest {

    @Autowired
	private MateriaRepository mr;

	@Autowired
	private AreaRepository ar;

	@Autowired
	private AlumnoRepository alr;

    @Autowired
    private EventoRepository er; 
    
    @Autowired
    private DocenteRepository dr;

    @Autowired
    private CitaRepository cr;
    String texto=   "Neque porro quisquam est qui dolorem ipsum quia dolor"+
                    "sit amet, consectetur, adipisci velit… No hay nadie que"+
                    "ame el dolor mismo, que lo busque, lo encuentre y lo "+
                    "quiera, simplemente porque es el dolor.";

    public void eventosCrud(){
        System.out.println("\nEVENTOS CRUD\n");
        Evento e1 = new Evento("Evento 1", texto,new Date(), LocalTime.of(2, 00));
        Evento e2 = new Evento("Evento 2", texto,new Date(), LocalTime.of(3, 20));
        Evento e3 = new Evento("Evento 3", texto,new Date(), LocalTime.of(4, 00));
        Evento e4 = new Evento("Evento 4", texto,new Date(), LocalTime.of(2, 30));
        Evento e5 = new Evento("Evento 5", texto,new Date(), LocalTime.of(10, 10));
        Evento e6 = new Evento("Evento 6", texto,new Date(), LocalTime.of(12, 25));
        

        er.save(e1);
        er.save(e2);
        er.save(e3);
        er.saveAll(Set.of(e4,e5,e6));

        List<Evento> lista = (List<Evento>) er.findAll();
        lista.forEach(e->System.out.println(e));

        Evento x = er.findById(5L).orElseThrow();
        x.setDescripcion("a-a-a-a-a");
        er.save(x);

        List<Evento> lista2 = (List<Evento>) er.findAll();
        lista2.forEach(e->System.out.println(e));

        er.delete(x);
        List<Evento> lista3 = (List<Evento>) er.findAll();
        lista3.forEach(e->System.out.println(e));
    }



    public void citasCrud(){
        System.out.println("\nCITAS CRUD\n");
        Docente d1 = dr.findById(1L).orElseThrow();
        Docente d3 = dr.findById(3L).orElseThrow();
        Docente d4 = dr.findById(4L).orElseThrow();

        Alumno a1 = alr.findById(10L).orElseThrow();
        Alumno a2 = alr.findById(2L).orElseThrow();
        Alumno a3 = alr.findById(3L).orElseThrow();

        Cita c1 = new Cita(d1,a1,new Date(),LocalTime.of(10, 30),"ausencia" ,"el niño falta mucho a clases");
        Cita c2 = new Cita(d1,a2,new Date(),LocalTime.of( 9, 30),"tareas"   ,"el niño no hace las tareas");
        Cita c3 = new Cita(d3,a1,new Date(),LocalTime.of(18, 10),"tareas"   ,"el niño no hace las tareas");
        Cita c4 = new Cita(d3,a1,new Date(),LocalTime.of(15, 00),"proyecto" ,"el niño debe 2 proyectos");
        Cita c5 = new Cita(d4,a2,new Date(),LocalTime.of(11, 50),"ausencia" ,"el niño se fugo de la clase");
        Cita c6 = new Cita(d4,a3,new Date(),LocalTime.of(20, 20),"arma"     ,"el niño tenia un arma ");

        cr.save(c1);
        cr.save(c2);
        cr.save(c3);
        cr.save(c4);
        cr.save(c5);

        Cita c7 = cr.findById(5L).orElseThrow();
        System.out.println(c7);
        
        c6.setId(5L);
        cr.save(c6);

        Cita c8 = cr.findById(5L).orElseThrow();
        System.out.println(c8);
        
    }


    @Transactional
	public void materiasCrud(){
        System.out.println("\nMATERIAS CRUD\n");
		Area a = ar.findById(3L).orElseThrow();
		Materia m1 = new Materia("Quimica",a);
		Materia m2 = new Materia("Fisica",a);
		Materia m3 = new Materia("Biologia",a);
		Materia m4 = new Materia("Biol",a);
		
		mr.saveAll(Set.of(m1,m2,m3,m4));

        List<Materia> lista = (List<Materia>) mr.findAll();
        lista.forEach(x->System.out.println(x));

		Materia xm = mr.findById(21L).orElseThrow();
        xm.setNombre("Biologia avanzada");
        mr.save(xm);

        List<Materia> lista2 = (List<Materia>) mr.findAll();
        lista2.forEach(x->System.out.println(x));

		mr.delete(xm);
        
        List<Materia> lista3 = (List<Materia>) mr.findAll();
        lista3.forEach(x->System.out.println(x));

    }

}
