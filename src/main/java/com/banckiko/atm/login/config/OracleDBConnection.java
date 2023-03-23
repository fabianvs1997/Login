package com.banckiko.atm.login.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class OracleDBConnection {
  /**  public static void main(String[] args) {
        try {
            // Cargar el controlador JDBC de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Configurar la conexión utilizando la cartera descargada y las credenciales del cliente
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(PORT=1522)(HOST=adb.example.oraclecloud.com))(CONNECT_DATA=(SERVICE_NAME=adb1_high.adb.oraclecloud.com)))");
            ods.setOraclePKIKeystoreType("PKCS12");
            ods.setOraclePKIKeystoreFile("/path/to/wallet/cwallet.sso");
            ods.setOraclePKIWalletPassword("password");
            ods.setMaxStatements(200);
            ods.setMaxConnections(20);

            // Establecer la conexión
            OracleConnection connection = (OracleConnection) ods.getConnection();

            // Ejecutar una consulta
            String query = "SELECT * FROM my_table WHERE column1 = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "some_value");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // procesar resultados
            }

            // Cerrar resultados, declaración y conexión
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
   */
}
