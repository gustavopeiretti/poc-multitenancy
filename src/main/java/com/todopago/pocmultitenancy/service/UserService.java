package com.todopago.pocmultitenancy.service;

import com.todopago.pocmultitenancy.entity.User;
import com.todopago.pocmultitenancy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    
    public void save(User user) {
        userRepository.save(user);
    }
    
    public List<User> getAll() {
        return userRepository.findAll();
        
    }
    
    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public void delete(long id) {
        userRepository.deleteById(id);
    }
    
}
