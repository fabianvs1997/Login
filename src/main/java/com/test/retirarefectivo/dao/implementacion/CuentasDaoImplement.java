package com.test.retirarefectivo.dao.implementacion;

import com.test.retirarefectivo.config.DbConfig;
import com.test.retirarefectivo.dao.CuentasIDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Repository
public class CuentasDaoImplement implements CuentasIDao {


    @Autowired
    private DbConfig conn;

    @Override
    public ResultSet conusltaNumTarjeta(Long num_tarjeta) throws SQLException, IOException {

        ResultSet resultSet = null;

        try(
                Statement statement = conn.getConnection().createStatement();
                ){

            String query ="SELECT * FROM CUENTAS";

            resultSet = statement.executeQuery(query);


        }catch (SQLException | IOException e){
            e.printStackTrace();
        }


        return resultSet;
    }
}
