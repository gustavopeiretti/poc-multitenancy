package com.todopago.pocmultitenancy.config.multitenant.schema;


import com.todopago.pocmultitenancy.config.multitenant.tenant.TenantContext;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import static com.todopago.pocmultitenancy.constant.MultiTenantConstants.DEFAULT_SCHEME;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {
    
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant = TenantContext.getCurrentTenant();
        return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_SCHEME;
    }
    
    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
