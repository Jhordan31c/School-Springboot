package com.schoolbackend.tests;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.AlumnoMateria;
import com.schoolbackend.models.Apoderado;
import com.schoolbackend.models.Grado;
import com.schoolbackend.models.Materia;
import com.schoolbackend.models.Pago;
import com.schoolbackend.repositories.AlumnoMateriaRepository;
import com.schoolbackend.repositories.AlumnoRepository;
import com.schoolbackend.repositories.GradoRepository;
import com.schoolbackend.repositories.MateriaRepository;

@Service
public class AlumnoTest {
    @Autowired
	private MateriaRepository mr;

	@Autowired
	private AlumnoRepository ar;

	@Autowired
	private AlumnoMateriaRepository amr;

	@Autowired
	private GradoRepository gr;

	

	@Transactional
	public void alumno_crud(){
        System.out.println("\nALUMNOS\n");
		Alumno a1 = new Alumno("11112222", "AA-aa", "BB-bb", 'M');
		Alumno a2 = new Alumno("22223333", "CC-cc", "DD-dd", 'M');
		Alumno a3 = new Alumno("33334444", "EE-ee", "FF-ff", 'M');
		Alumno a4 = new Alumno("44445555", "GG-gg", "HH-hh", 'M');
		Alumno a5 = new Alumno("55556666", "II-ii", "JJ-jj", 'M');
		Alumno a6 = new Alumno("66667777", "KK-kk", "LL-ll", 'F');
		Alumno a7 = new Alumno("77778888", "MM-mm", "NN-nn", 'F');
		Alumno a8 = new Alumno("88889999", "OO-oo", "PP-pp", 'F');
		Alumno a9 = new Alumno("99990000", "QQ-qq", "RR-rr", 'F');
		Alumno a10= new Alumno("00001111", "SS-ss", "TT-tt", 'F');

		ar.save(a1);
		ar.save(a3);
		ar.save(a2);
		ar.saveAll(Set.of(a4,a5,a6,a7,a8,a9,a10));

		List<Alumno> lista = (List<Alumno>) ar.findAll();
		lista.forEach(a->System.out.println(a));

		Alumno a11 = ar.findById(10L).orElseThrow();
		Apoderado ap = new Apoderado("22332233","XX-mm","MM-mm","padre","51+ 123 123 123","mm@gmail.com","su casa");
		a11.setApoderado(ap);
		a11.addPago(new Pago("Pension Mes Abril",100.50,new Date(),1));
		a11.addPago(new Pago("Pension Mes Abril",150.70,new Date(),1));
		ar.save(a11);

		Alumno x = ar.findByIdWithApoderadoMateriasPagos(10L).orElseThrow();
		System.out.println("\nAPODERADO DEL ALUMNO => "+x.getApoderado());
		//ar.delete(x);
		

		List<Alumno> lista2 = (List<Alumno>) ar.findAll();
		lista2.forEach(a->System.out.println(a));
	}

	@Transactional
	public void alumno_materia_grado(){
        System.out.println("\nALUMNOS-MATERIA-GRADO\n");
		Alumno a1 = new Alumno("11112222","AA-aa","BB-bb",'M');
		ar.save(a1);

		Alumno a2 = ar.findById(1L).orElseThrow();
		Grado g1 = gr.findById(4L).orElseThrow();
		Materia m4 = mr.findById(3L).orElseThrow();

		AlumnoMateria am = new AlumnoMateria(a2, g1, m4);
		amr.save(am);

		AlumnoMateria am_2 = amr.findById(1L).orElseThrow();
		System.out.println("\n"+am_2+"\n");



		Alumno a3 = ar.findById(1L).orElseThrow();
		Grado g2 = gr.findById(4L).orElseThrow();
		Materia m5 = mr.findById(1L).orElseThrow();

		AlumnoMateria am_3 = new AlumnoMateria(a3, g2, m5);
		amr.save(am_3);

		AlumnoMateria am_4 = amr.findById(2L).orElseThrow();
		System.out.println("\n"+am_4+"\n");

		
		//amr.delete(am_4);
		//mr.delete(m5);
	}

	


	@Transactional
	public void alumno_pagos(){
		
	}
}
