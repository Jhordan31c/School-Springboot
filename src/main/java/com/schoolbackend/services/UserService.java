package com.schoolbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolbackend.models.Rol;
import com.schoolbackend.models.User;
import com.schoolbackend.repositories.RolRepository;
import com.schoolbackend.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository ur;

    @Autowired
    private RolRepository rr;

    @Autowired
    private PasswordEncoder encriptador;


    @Transactional(readOnly = true)
    public Optional<User> findById(Long id){
        return ur.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return (List<User>) ur.findAll();
    }

    @Transactional
    public User create(User u, int tipo){
        List<Rol> roles = new ArrayList<>();
    
        Optional<Rol> or = rr.findByNombre("ROLE_USER");
        or.ifPresent(roles::add);

        if(u.isAdmin()){
            Optional<Rol> orAdmin = rr.findByNombre("ROLE_ADMIN");
            orAdmin.ifPresent(roles::add);
        }

        if (tipo==1) {
            Optional<Rol> orAdmin = rr.findByNombre("ROLE_ALUMNO");
            orAdmin.ifPresent(roles::add);
        }
        if (tipo==2) {
            Optional<Rol> orAdmin = rr.findByNombre("ROLE_DOCENTE");
            orAdmin.ifPresent(roles::add);
        }

        String password = encriptador.encode(u.getPassword());

        u.setActivo(true);
        u.setRoles(roles);
        u.setPassword(password);

        return ur.save(u);
    }

    @Transactional
    public User update(User oldUser, User newUser){
        String password = newUser.getPassword();
        String username = newUser.getUsername();

        if (!password.isBlank()) {
            password = encriptador.encode(newUser.getPassword());
            oldUser.setPassword(password);
        }
        if (!username.isBlank()) {
            oldUser.setUsername(username);
        }
        if (newUser.getRoles()!=null) {
            if (newUser.getRoles().isEmpty()) {
                oldUser.setRoles(newUser.getRoles());
            }
        }
        oldUser.setActivo(newUser.isActivo());
        
        return ur.save(oldUser);
    }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return ur.existsByUsername(username);
    }
    
}
