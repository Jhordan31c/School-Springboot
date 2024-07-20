package com.schoolbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.Pago;
import com.schoolbackend.models.ParametroPaga;
import com.schoolbackend.repositories.AlumnoRepository;
import com.schoolbackend.repositories.PagoRepository;
import com.schoolbackend.repositories.ParametroPagaRepository;

@Service
public class PagoServiceImpA implements PagoService {

    @Autowired
    AlumnoRepository ar;

    @Autowired
    PagoRepository pr;

    @Autowired
    ParametroPagaRepository ppr;



    @Transactional(readOnly = true)
    @Override
    public List<Pago> findAllByIdAlumno(Long id) {
        return pr.findAllByIdAlumno(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pago> findAllByIdAlumnoByYear(Long id, Integer year) {
        return pr.findAllByIdAlumnoAndYear(id, year);
    }
    
    
    @Transactional(readOnly = true)
    @Override
    public List<Pago> findAll() {
        return (List<Pago>) pr.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Pago> findById(Long id) {
        return pr.findById(id);
    }

    @Transactional
    @Override
    public Pago create(Pago p) {
        p.setId(null);
        p.setEstado(1);
        return pr.save(p);
    }

    @Transactional
    @Override
    public Optional<Pago> update(Pago p, Long id) {
        Optional<Pago> op = pr.findById(id);
        if(op.isPresent()){
            Pago x = op.orElseThrow();

            if (p.getNombre()   !=null) {
                x.setNombre (p.getNombre());
            }
            if (p.getDetalle()  !=null) {
                x.setDetalle(p.getDetalle());
            }
            if (p.getFecha_vencimiento()!=null) {
                x.setFecha_vencimiento(p.getFecha_vencimiento());
            }
            if (p.getFecha_pago()!=null) {
                x.setFecha_pago(p.getFecha_pago());
            }
            if(p.getAlumno()    !=null){
                x.setAlumno(p.getAlumno());
            }
            
            x.setMonto  (p.getMonto());
            x.setEstado(p.getEstado());
            

            Pago x2 = pr.save(x);
            return Optional.of(x2);
        }
        return op;
    }

    @Transactional
    @Override
    public Optional<Pago> delete(Long id) {
        Optional<Pago> op = pr.findById(id);
        op.ifPresent(x->pr.delete(x));
        return op;
    }

    @Transactional
    @Override
    public void generalCreate(Pago p, Integer x) {
        List<Alumno> alumnos = ar.findAllByEstado(x);
        alumnos.forEach(a->{
            Pago p1 = p;
            a.addPago(p1);
            ar.save(a);
        });
    }

    
    @Transactional
    @Override
    public Optional<Pago> updateEstado(Long id, int estado){
        Optional<Pago> op = pr.findById(id);

        if (op.isPresent()) {
            Pago x = op.orElseThrow();
            x.setEstado(estado);
            Optional.of(pr.save(x));
        }

        return op;
    }





    
    @Transactional
    @Override
    public List<ParametroPaga> createParametrosPago(List<ParametroPaga> lista) {    
        lista.forEach(pp->ppr.save(pp));

        return (List<ParametroPaga>) ppr.findAll();
    }

    @Transactional
    @Override
    public List<ParametroPaga> updateParametrosPago(List<ParametroPaga> lista){
        lista.forEach(pp->{
            Optional<ParametroPaga> op = ppr.findById(pp.getId());
            op.ifPresent(x->{
                x.setPrecioMatricula    (pp.getPrecioMatricula());
                x.setPrecioPension      (pp.getPrecioPension());
                x.setDia_vencimiento    (pp.getDia_vencimiento());
                x.setMora               (pp.getMora());
            });
        });

        return (List<ParametroPaga>) ppr.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public List<ParametroPaga> findParametroPagas() {
        return (List<ParametroPaga>) ppr.findAll();
    }
    
}
