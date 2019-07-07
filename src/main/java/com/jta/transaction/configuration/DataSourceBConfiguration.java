package com.jta.transaction.configuration;

import com.jta.transaction.h2.repository.H2DatasourceProperties;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
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
@PropertySource(value = "classpath:h2.properties")
@EnableConfigurationProperties(H2DatasourceProperties.class)
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.jta.transaction.h2.repository", entityManagerFactoryRef = "entityManagerFactoryB")
public class DataSourceBConfiguration {

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    private H2DatasourceProperties h2DatasourceProperties;

    @Bean(name = "dataSourceB")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.two")
    public DataSource getH2AtomikosDataSourceBean() {
        return new AtomikosDataSourceBean();
    }

    /*@Bean(name = "dataSourceB")//, initMethod = "init", destroyMethod = "close"
    public DataSource getH2AtomikosDataSourceBean() {
        JdbcDataSource h2XaDataSource = new JdbcDataSource();
        h2XaDataSource.setURL(h2DatasourceProperties.getUrl());
        h2XaDataSource.setUser(h2DatasourceProperties.getUser());
        h2XaDataSource.setPassword(h2DatasourceProperties.getPassword());
        com.atomikos.jdbc.AtomikosDataSourceBean xaDataSource = new com.atomikos.jdbc.AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(h2XaDataSource);
        xaDataSource.setUniqueResourceName("DataSourceB");
        return xaDataSource;
    }*/

    @Bean(name = "entityManagerFactoryB")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryB() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        entityManagerFactoryBean.setJpaPropertyMap(properties);
        entityManagerFactoryBean.setPersistenceUnitName("PersistenceUnitB");
        entityManagerFactoryBean.setJtaDataSource(getH2AtomikosDataSourceBean());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.jta.transaction.h2.entity");
        return entityManagerFactoryBean;
    }

}
