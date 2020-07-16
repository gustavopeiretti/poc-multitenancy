package com.todopago.pocmultitenancy.constant;


public class MultiTenantConstants {
    
    public static final String DEFAULT_SCHEME = "public";
    
    /**
     * Messages Exception
     */
    public static final String TENANT_GENERAL_ERROR = "Error during execution tenant validation.";
    public static final String TENANT_DO_NOT_EXIST = "Tenant doesn't exist";
    public static final String TENANT_NULL = "Tenant is null";
    
    
    /**
     * Private constructor by default
     */
    private MultiTenantConstants() {
    
    }
}
