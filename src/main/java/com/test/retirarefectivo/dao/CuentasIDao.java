package com.test.retirarefectivo.dao;

import com.test.retirarefectivo.dto.CuentasDto;

import java.io.IOException;
import java.sql.SQLException;

public interface CuentasIDao {

    CuentasDto conusltaNumTarjeta(Long num_tarjeta) throws SQLException, IOException;


    Object crearCuenta(CuentasDto cuentasDto) throws SQLException, IOException;


    void activarCuenta(Long num_cuenta);


}
