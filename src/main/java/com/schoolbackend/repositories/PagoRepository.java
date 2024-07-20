package com.schoolbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.schoolbackend.dto.ContadorDto2;
import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.Pago;

public interface PagoRepository extends CrudRepository<Pago, Long> {
    
    @Query("SELECT p FROM Pago p WHERE p.alumno.id = ?1 ORDER BY p.fecha_vencimiento")
    List<Pago> findAllByIdAlumno(Long id);

    
    @Query("SELECT p FROM Pago p WHERE p.alumno = ?1")
    List<Pago> findAllByAlumno(Alumno a);

    @Query("SELECT p FROM Pago p WHERE p.alumno.id = ?1 AND EXTRACT(YEAR FROM p.fecha_vencimiento) = ?2")
    List<Pago> findAllByIdAlumnoAndYear(Long id, Integer year);




    @Query("SELECT NEW com.schoolbackend.dto.ContadorDto2(MONTH(p.fecha_pago), SUM(p.monto)) FROM Pago p "
            +"WHERE YEAR(p.fecha_pago) = YEAR(CURRENT_DATE) "
            +"GROUP BY MONTH(p.fecha_pago)")
    List<ContadorDto2> obtenerRecaudacionMensual();

    @Query("SELECT NEW com.schoolbackend.dto.ContadorDto2(MONTH(p.fecha_pago), SUM(p.monto)) FROM Pago p "
            +"WHERE EXTRACT(YEAR FROM p.fecha_pago) = ?1 "
            +"GROUP BY MONTH(p.fecha_pago)")
    List<ContadorDto2> obtenerRecaudacionMensual(int year);
}

