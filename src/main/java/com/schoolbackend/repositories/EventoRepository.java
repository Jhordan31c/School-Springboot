package com.schoolbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.schoolbackend.dto.ContadorDto;
import com.schoolbackend.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long> {
    
    @Query("SELECT e FROM Evento e WHERE EXTRACT(YEAR FROM e.fecha) = ?1")
    List<Evento> findAllByYear(int year);

    @Query("SELECT e FROM Evento e WHERE EXTRACT(YEAR FROM e.fecha) = ?1 AND EXTRACT(MONTH FROM e.fecha) = ?2")
    List<Evento> findAllByYear(int year, int month);



    @Query( "SELECT MONTH(e.fecha) AS mes, COUNT(e) AS cantidad FROM Evento e "
            +"GROUP BY MONTH(e.fecha)")
    List<Object[]> contarEventosPorMes();

    @Query( "SELECT NEW com.schoolbackend.dto.ContadorDto(MONTH(e.fecha), COUNT(e)) FROM Evento e "
            +"WHERE YEAR(e.fecha) = YEAR(CURRENT_DATE) "
            +"GROUP BY MONTH(e.fecha)")
    List<ContadorDto> contarEventosPorMes2();

    @Query( "SELECT NEW com.schoolbackend.dto.ContadorDto(MONTH(e.fecha), COUNT(e)) FROM Evento e "
            +"WHERE EXTRACT(YEAR FROM e.fecha) = ?1 "
            +"GROUP BY MONTH(e.fecha)")
    List<ContadorDto> contarEventosPorMes(int year);

}


