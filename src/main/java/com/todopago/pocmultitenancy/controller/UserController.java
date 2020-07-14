package com.todopago.pocmultitenancy.controller;

import com.todopago.pocmultitenancy.config.multitenant.repository.TenantSourceRepository;
import com.todopago.pocmultitenancy.entity.User;
import com.todopago.pocmultitenancy.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    TenantSourceRepository tenantSourceRepository;
    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/")
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping(value = "/")
    public ResponseEntity<List<User>> getAll() throws SQLException {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable(value = "id") Long id) {
        User user = userService.get(id);
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable(value = "id") String id) {
        userService.delete(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
