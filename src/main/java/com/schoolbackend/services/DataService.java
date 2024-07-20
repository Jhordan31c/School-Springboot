package com.schoolbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolbackend.dto.ContadorDto;
import com.schoolbackend.dto.ContadorDto2;
import com.schoolbackend.repositories.AlumnoRepository;
import com.schoolbackend.repositories.AulaRepository;
import com.schoolbackend.repositories.CitaRepository;
import com.schoolbackend.repositories.DocenteRepository;
import com.schoolbackend.repositories.EventoRepository;
import com.schoolbackend.repositories.PagoRepository;

@Service
public class DataService {
    
    @Autowired
    EventoRepository er;

    @Autowired
    PagoRepository pr;

    @Autowired
    CitaRepository cr;

    @Autowired
    AlumnoRepository alr;

    @Autowired
    DocenteRepository dr;

    @Autowired
    AulaRepository ar;


    
    
    @Transactional(readOnly = true)
    public List<ContadorDto> eventosPorMes(){
        return er.contarEventosPorMes2();
    }

    @Transactional(readOnly = true)
    public List<ContadorDto> eventosPorMes(int year){
        return er.contarEventosPorMes(year);
    }




    @Transactional(readOnly = true)
    public List<ContadorDto2> recaudacionPorMeses(){
        return pr.obtenerRecaudacionMensual();
    }
    @Transactional(readOnly = true)
    public List<ContadorDto2> recaudacionPorMeses(int year){
        return pr.obtenerRecaudacionMensual();
    }




    @Transactional(readOnly = true)
    public List<ContadorDto> alumnosPorEstado(){
        return alr.contarAlumnosEstado();
    }

    @Transactional(readOnly = true)
    public long alumnosPorEstado(int estado){
        return alr.countByEstado(estado);
    }



    
    @Transactional(readOnly = true)
    public List<ContadorDto> docentesPorEstado(){
        return dr.contarDocentesEstado();
    }

    @Transactional(readOnly = true)
    public long docentesPorEstado(int estado){
        return dr.countByEstado(estado);
    }

    @Transactional(readOnly = true)
    public long citasPorEstado(int estado){
        return cr.countByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<ContadorDto> aulasPorEstado(){
        return ar.contarAulasEstado();
    }

    @Transactional(readOnly = true)
    public long aulasPorEstado(int estado){
        return ar.countByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<ContadorDto> citasPorMeses(){
        return cr.obtenerCantidadPorMes();
    }



}
