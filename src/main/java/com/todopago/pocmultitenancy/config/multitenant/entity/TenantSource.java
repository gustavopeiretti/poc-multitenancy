package com.todopago.pocmultitenancy.config.multitenant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static com.todopago.pocmultitenancy.constant.MultiTenantConstants.DEFAULT_SCHEME;

@Entity
@Table(name = "tenant_source", schema = DEFAULT_SCHEME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TenantSource implements Serializable {
    
    private static final long serialVersionUID = 5104181924076372196L;
    @Id
    private Long id;
    private String name;
    private String scheme;
}
