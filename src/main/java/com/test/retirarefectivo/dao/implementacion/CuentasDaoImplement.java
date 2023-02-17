package com.test.retirarefectivo.dao.implementacion;

import com.test.retirarefectivo.config.DbConfig;
import com.test.retirarefectivo.dao.CuentasIDao;
import com.test.retirarefectivo.dto.CuentasDto;
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

        ResultSet resultSet = null;
        CuentasDto cuentasDto = new CuentasDto();
        try (
                Statement statement = conn.getConnection().createStatement();
        ) {

            String query = "SELECT * FROM ROOT.CUENTAS WHERE NUMERO_CUENTA =" + num_tarjeta;

            resultSet = statement.executeQuery(query);

            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(metadata.getColumnName(i));
            }


            while (resultSet.next()) {
                cuentasDto.setNombre_cuenta(resultSet.getString("NOMBRE_CUENTA"));
                cuentasDto.setNumero_cuenta(resultSet.getLong("NUMERO_CUENTA"));
                cuentasDto.setEstatus(resultSet.getInt("ESTATUS"));
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


        return cuentasDto;
    }

    @Override
    public Object crearCuenta(CuentasDto cuentasDto) throws SQLException, IOException {
        ResultSet resultSet = null;
        CuentasDto nuevaCuenta;
        try (
                PreparedStatement statement = conn.getConnection().prepareStatement("INSERT INTO root.CUENTAS (nombre_cuenta,  numero_cuenta, estatus) VALUES (?, ?, ?)")
        ) {

            statement.setString(1, cuentasDto.getNombre_cuenta());
            statement.setLong(2, cuentasDto.getNumero_cuenta());
            statement.setInt(3, 0);
            statement.executeUpdate();


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            nuevaCuenta = conusltaNumTarjeta(cuentasDto.getNumero_cuenta());
        }

        return nuevaCuenta;
    }

    @Override
    public void activarCuenta(Long num_cuenta) {
        try (
                PreparedStatement statement = conn.
                        getConnection().
                        prepareStatement("UPDATE ROOT.CUENTAS SET ESTATUS = ? WHERE NUMERO_CUENTA = ?")
        ) {
            statement.setInt(1, 1);
            statement.setLong(2, num_cuenta);
            statement.executeUpdate();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
