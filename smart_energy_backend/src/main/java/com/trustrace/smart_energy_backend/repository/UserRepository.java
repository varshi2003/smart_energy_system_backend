package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    List<User> findByRoles(String role);
}
