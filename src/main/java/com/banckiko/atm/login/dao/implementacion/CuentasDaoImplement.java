package com.banckiko.atm.login.dao.implementacion;

import com.banckiko.atm.login.config.DbConfig;
import com.banckiko.atm.login.dao.CuentasIDao;
import com.banckiko.atm.login.dto.CuentasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;


@Repository
public class CuentasDaoImplement implements CuentasIDao {


    @Autowired
    private DbConfig conn;

    @Override
    public CuentasDto conusltaNumTarjeta(Long num_tarjeta) throws SQLException, IOException {

        ResultSet resultSet = null;   // nos sirve para obtener nuestras columnas de la BD
        CuentasDto cuentasDto = new CuentasDto();  // invocacion de nuestro objeto de cuentas
        try (                     //detecta las excepcion generadas
                Statement statement = this.conn.getConnection().createStatement();  //guarda la conexion de la Bd
        ) {

            String query = "SELECT * FROM ROOT.CUENTAS WHERE NUMERO_CUENTA =" + num_tarjeta;   //query para seleccionar el #cuenta pasandole el request num_tarjeta

            resultSet = statement.executeQuery(query);  //ejecuta query sin modificar

            ResultSetMetaData metadata = resultSet.getMetaData();  //obtiene los datos de nuestro query
            int columnCount = metadata.getColumnCount();           //devuelve el numero de columnas y lo guarda en en la variable columnCount
            for (int i = 1; i <= columnCount; i++) {              // cada vez que encuentra una cloumna mientras esta se <=1 imprimie el nombre de la columna
                System.out.println(metadata.getColumnName(i));
            }


            while (resultSet.next()) {
                cuentasDto.setNombre_cuenta(resultSet.getString("NOMBRE_CUENTA")); // se asigna el valor del resultado al campo de nuestro objeto cuentasdto
                cuentasDto.setNumero_cuenta(resultSet.getLong("NUMERO_CUENTA"));   // se asigna el valor del resultado al campo de nuestro objeto cuentasdto
                cuentasDto.setEstatus(resultSet.getInt("ESTATUS"));                // se asigna el valor del resultado al campo de nuestro objeto cuentasdto
            }


        } catch (SQLException | IOException e) {   // cacha las excepciones
            e.printStackTrace();
        }


        return cuentasDto;
    }


}
