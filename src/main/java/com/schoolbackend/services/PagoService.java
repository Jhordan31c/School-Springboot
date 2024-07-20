package com.schoolbackend.services;

import java.util.List;
import java.util.Optional;

import com.schoolbackend.models.Pago;
import com.schoolbackend.models.ParametroPaga;

public interface PagoService {

    List<Pago> findAll();

    List<Pago> findAllByIdAlumno(Long id);

    List<Pago> findAllByIdAlumnoByYear(Long id, Integer year);

    Pago create(Pago p);

    Optional<Pago> findById(Long id);

    Optional<Pago> update(Pago p, Long id);

    Optional<Pago> delete(Long id);

    void generalCreate(Pago p, Integer x);
    
    Optional<Pago> updateEstado(Long id, int estado);

    List<ParametroPaga> findParametroPagas();

    List<ParametroPaga> createParametrosPago(List<ParametroPaga> lista);

    List<ParametroPaga> updateParametrosPago(List<ParametroPaga> lista);

}
