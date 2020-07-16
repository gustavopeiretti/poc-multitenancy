package com.todopago.pocmultitenancy.config.multitenant.schema;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.todopago.pocmultitenancy.constant.MultiTenantConstants.DEFAULT_SCHEME;

@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantConnectionProvider.class);
    private final transient DataSource datasource;
    
    public TenantConnectionProvider(DataSource dataSource) {
        this.datasource = dataSource;
    }
    
    @Override
    public Connection getAnyConnection() throws SQLException {
        return datasource.getConnection();
    }
    
    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }
    
    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        logger.info("Get connection for tenant {}", tenantIdentifier);
        final Connection connection = getAnyConnection();
        connection.setSchema(tenantIdentifier);
        return connection;
    }
    
    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        logger.info("Release connection for tenant {}", tenantIdentifier);
        connection.setSchema(DEFAULT_SCHEME);
        releaseAnyConnection(connection);
    }
    
    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }
    
    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
