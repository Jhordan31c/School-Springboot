package com.schoolbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.schoolbackend.tests.AlumnoTest;
import com.schoolbackend.tests.AulaTest;
import com.schoolbackend.tests.GeneralTest;
import com.schoolbackend.tests.HorarioTest;

@SpringBootApplication
public class SchoolBackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SchoolBackendApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		// gt.eventosCrud();
		// gt.materiasCrud();
		// at1.alumno_crud();
		// at1.alumno_pagos();
		// at2.docentes();	
		// at2.aulas();	
		// at2.aulas2();	
		// //ht.horarioCrud();
		// gt.citasCrud();
	}
	
	@Autowired
	AlumnoTest at1;
	
	@Autowired
	AulaTest at2;

	@Autowired
	HorarioTest ht;

	@Autowired
	GeneralTest gt;

}
