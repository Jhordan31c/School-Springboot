package com.schoolbackend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.schoolbackend.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);  
}
