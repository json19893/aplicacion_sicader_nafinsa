package com.org.backend_nafinsa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    //configuracion para datasource
    @Bean(destroyMethod = "")
    public javax.sql.DataSource dataSource() throws NamingException {
        Context context = new InitialContext();
        return (javax.sql.DataSource)context.lookup("jdbc/sicader");
    }



     //Configuracion de DS desde clase para prubas locales

      /*   @Bean(destroyMethod = "close")
        DataSource dataSource(Environment env) {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            config.setJdbcUrl("jdbc:oracle:thin:@172.16.8.113:1521:ocrl");
            config.setUsername("system");
            config.setPassword("N4f1n.0r4cl3DB");
            return new HikariDataSource(config);
        }
     
        @Bean
        JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }
     
        @Bean
        DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }
    
        */
    //configuracion para conexion local o directa sin  ds
/*
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
 */
}