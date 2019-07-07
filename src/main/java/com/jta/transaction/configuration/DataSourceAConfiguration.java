package com.jta.transaction.configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.jta.transaction.oracle.repository.OracleDatasourceProperties;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
//@EnableConfigurationProperties
@EnableConfigurationProperties(OracleDatasourceProperties.class)
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.jta.transaction.oracle.repository", entityManagerFactoryRef = "entityManagerFactoryA")
@PropertySource(value = "classpath:oracle.properties")
public class DataSourceAConfiguration {

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    private OracleDatasourceProperties oracleDatasourceProperties;

    @Bean(name = "dataSourceA")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.one")
    public DataSource getOracleAtomikosDataSourceBean() {
        return new AtomikosDataSourceBean();
    }

    /*@Bean(name = "dataSourceA")//, initMethod = "init", destroyMethod = "close"
    public DataSource getOracleAtomikosDataSourceBean() {
        JdbcDataSource h2XaDataSource = new JdbcDataSource();
        h2XaDataSource.setURL(oracleDatasourceProperties.getUrl());
        h2XaDataSource.setUser(oracleDatasourceProperties.getUser());
        h2XaDataSource.setPassword(oracleDatasourceProperties.getPassword());
        com.atomikos.jdbc.AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(h2XaDataSource);
        xaDataSource.setUniqueResourceName("DataSourceA");
        return xaDataSource;
    }*/

    @Bean(name = "entityManagerFactoryA")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryA() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        entityManagerFactoryBean.setJpaPropertyMap(properties);
        entityManagerFactoryBean.setPersistenceUnitName("PersistenceUnitA");
        entityManagerFactoryBean.setJtaDataSource(getOracleAtomikosDataSourceBean());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.jta.transaction.oracle.entity");
        return entityManagerFactoryBean;
    }

}
