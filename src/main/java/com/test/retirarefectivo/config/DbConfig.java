package com.test.retirarefectivo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Description: Clase de conexion
 */
@Configuration
public class DbConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String pasword;

    @Value("${spring.datasource.driver-class-name}")
    private String drivert;

    public  Connection getConnection() throws SQLException, IOException {


        String driverClassName = this.drivert;
        String url = this.url;
        String username = this.username;
        String password = this.pasword;

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }


}
