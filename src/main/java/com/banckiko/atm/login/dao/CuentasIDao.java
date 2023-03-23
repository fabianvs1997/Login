package com.banckiko.atm.login.dao;

import com.banckiko.atm.login.dto.CuentasDto;

import java.io.IOException;
import java.sql.SQLException;

public interface CuentasIDao {

    CuentasDto conusltaNumTarjeta(Long num_tarjeta) throws SQLException, IOException;


    Object crearCuenta(CuentasDto cuentasDto) throws SQLException, IOException;


    void activarCuenta(Long num_cuenta);


}
