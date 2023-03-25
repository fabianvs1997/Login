package com.banckiko.atm.login.service;

import com.banckiko.atm.login.model.registro.CuentasRequest;
import com.banckiko.atm.login.model.registro.RegistroCuentasRequest;
import com.banckiko.atm.login.model.CuentasResponse;

import java.io.IOException;
import java.sql.SQLException;

public interface CuentasIService {

    CuentasResponse consultaTarjeta(Long num_tarjeta, CuentasRequest request) throws SQLException, IOException;        // espera dos parametros, Resquest (modelo de datos)

}


// SQLException indica lo que pasa en Bd Ioexception conversion de datos