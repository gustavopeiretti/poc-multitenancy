package com.todopago.pocmultitenancy.config.multitenant.interceptors;

import com.todopago.pocmultitenancy.config.multitenant.entity.TenantSource;
import com.todopago.pocmultitenancy.config.multitenant.exception.TenantException;
import com.todopago.pocmultitenancy.config.multitenant.repository.TenantSourceRepository;
import com.todopago.pocmultitenancy.config.multitenant.tenant.TenantContext;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.todopago.pocmultitenancy.constant.MultiTenantConstants.TENANT_DO_NOT_EXIST;
import static com.todopago.pocmultitenancy.constant.MultiTenantConstants.TENANT_NULL;

@Component
@Log4j2
public class Interceptor extends HandlerInterceptorAdapter {
    
    private static final String TENANT_HEADER = "X-TenantID";
    
    @Autowired
    private TenantSourceRepository tenantSourceRepository;
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) {
        return tenantSetUp(request);
    }
    
    private boolean tenantSetUp(HttpServletRequest request) {
        String tenant = request.getHeader(TENANT_HEADER);
        log.info("Interception request header value : " + tenant);
        if (StringUtils.isNotBlank(tenant)) {
            Optional<TenantSource> dataSourceConfig = tenantSourceRepository.findByName(tenant);
            if (dataSourceConfig.isPresent()) {
                TenantContext.setCurrentTenant(dataSourceConfig.get().getScheme());
            } else {
                throw new TenantException(TENANT_DO_NOT_EXIST);
            }
        } else {
            throw new TenantException(TENANT_NULL);
        }
        return true;
    }
    
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        TenantContext.clear();
    }
    
}
