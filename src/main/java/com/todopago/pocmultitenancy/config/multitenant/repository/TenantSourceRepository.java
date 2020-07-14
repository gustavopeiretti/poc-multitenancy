package com.todopago.pocmultitenancy.config.multitenant.repository;

import com.todopago.pocmultitenancy.config.multitenant.entity.TenantSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantSourceRepository extends JpaRepository<TenantSource, Long> {
    Optional<TenantSource> findByName(String name);
}
