package com.org.backend_nafinsa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
@Configuration
public class DataSourceConfig {

    //configuracion para datasource
    @Bean(destroyMethod = "")
    public javax.sql.DataSource dataSource() throws NamingException {
        Context context = new InitialContext();
        return (javax.sql.DataSource)context.lookup("jdbc/sicader");
    }
    //configuracion para conexion local o directa sin  ds
    /*
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    */
}
