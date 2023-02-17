package com.test.retirarefectivo.service;

import com.test.retirarefectivo.dto.CuentasDto;
import com.test.retirarefectivo.model.CuentasResponse;
import com.test.retirarefectivo.model.registro.CuentasRequest;
import com.test.retirarefectivo.model.registro.RegistroCuentasRequest;

import java.io.IOException;
import java.sql.SQLException;

public interface CuentasIService {

    CuentasResponse consultaTarjeta(Long num_tarjeta, CuentasRequest request) throws SQLException, IOException;

    CuentasResponse crearCuenta(RegistroCuentasRequest request) throws SQLException, IOException;
    CuentasResponse avtivaCuenta(Long num_cuenta) throws SQLException, IOException;
}