package com.schoolbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolbackend.models.Alumno;
import com.schoolbackend.models.Docente;
import com.schoolbackend.models.User;
import com.schoolbackend.repositories.DocenteRepository;
import com.schoolbackend.services.AlumnoService;
import com.schoolbackend.services.UserService;
import com.schoolbackend.validations.Validacion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService us;

    @Autowired
    private DocenteRepository ds;

    @Autowired
    private AlumnoService as;

    @Autowired 
    private Validacion v;

    @GetMapping
    public List<User> lista(){
        return us.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody User u, BindingResult br){
        if(br.hasFieldErrors()){
            return v.informe(br);
        }
        User x = us.create(u,0);
        return ResponseEntity.status(HttpStatus.CREATED).body(x);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> crearUsuario2(@Valid @RequestBody User u, BindingResult br){
        u.setAdmin(false);
        if(br.hasFieldErrors()){
            return v.informe(br);
        }
        User x = us.create(u,0);
        return ResponseEntity.status(HttpStatus.CREATED).body(x);
    }

    @GetMapping("/{id}/{rol}")
    public ResponseEntity<?> buscarUsuario( @PathVariable(name = "id") Long id, 
                                            @PathVariable(name = "rol") String rol)
    {
        if (rol.equalsIgnoreCase("ROLE_ADMIN")) {
            User    x = us.findById(id)     .orElseThrow();
            return ResponseEntity.ok().body(x);

        } else if (rol.equalsIgnoreCase("ROLE_DOCENTE")) {
            Docente x = ds.findByIdUser(id) .orElseThrow();
            return ResponseEntity.ok().body(x);

        } else if (rol.equalsIgnoreCase("ROLE_ALUMNO")){
            Alumno  x = as.findByUser(id)   .orElseThrow();
            return ResponseEntity.ok().body(x);
        }
        return ResponseEntity.badRequest().build();
    }

}
