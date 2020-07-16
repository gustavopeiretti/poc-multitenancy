package com.todopago.pocmultitenancy.repository;

import com.todopago.pocmultitenancy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findById(Long id);
    
    void deleteById(long id);
}
