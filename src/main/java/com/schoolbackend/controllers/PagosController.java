package com.schoolbackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.Pago;
import com.schoolbackend.models.ParametroPaga;
import com.schoolbackend.services.AlumnoService;
import com.schoolbackend.services.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagosController {

    @Autowired
    private AlumnoService as;

    @Autowired
    private PagoService ps;





    @GetMapping
    public List<Pago> listaPagos(){
        return ps.findAll();
    }


    @GetMapping("/alumnos")
    public List<Alumno> listaAlumnos(){
        return as.findAll();
    }

    //EN Alumno Controller, hay un metodo que te trae al lumno con todo su info incluyendo pagos
    @GetMapping("/alumnos/{id}")
    public List<Pago> listaPorAlumno(@PathVariable Long id){
        return ps.findAllByIdAlumno(id);
    }


    @GetMapping("/alumnos/{id}/{año}")
    public List<Pago> listaPorAlumno(@PathVariable Long id, @PathVariable Integer año){
        return ps.findAllByIdAlumnoByYear(id, año);
    }

    





    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Pago p){
        return ResponseEntity.ok().body(ps.create(p));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pago p)
    {
        Optional<Pago> op = ps.update(p, id);
        if (op.isPresent()) {
            return ResponseEntity.ok().body(op.orElseThrow());
        }
        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id)
    {
        Optional<Pago> op = ps.delete(id);
        if (op.isPresent()) {
            return ResponseEntity.ok().body(op.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }




    
    @PutMapping("/{id}/{x}")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @PathVariable int x)
    {
        Optional<Pago> op = ps.updateEstado(id, x);
        if (op.isPresent()) {
            return ResponseEntity.ok().body(op.orElseThrow());
        }
        return ResponseEntity.badRequest().build();
    }





    @PutMapping("/parametro-paga")
    public ResponseEntity<?> actualizarParametros(@RequestBody List<ParametroPaga> lista)
    {
        List<ParametroPaga> lista2 = ps.updateParametrosPago(lista);
        return ResponseEntity.ok().body(lista2);
    }

    @GetMapping("/parametro-paga")
    public ResponseEntity<?> actualizarParametros()
    {
        List<ParametroPaga> lista = ps.findParametroPagas();
        return ResponseEntity.ok().body(lista);
    }

}
