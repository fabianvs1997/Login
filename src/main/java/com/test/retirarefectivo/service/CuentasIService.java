package com.test.retirarefectivo.service;

import com.test.retirarefectivo.dto.CuentasDto;
import com.test.retirarefectivo.model.CuentasResponse;
import com.test.retirarefectivo.model.registro.CuentasRequest;

import java.io.IOException;
import java.sql.SQLException;

public interface CuentasIService {

    CuentasResponse consultaTarjeta(Long num_tarjeta, CuentasRequest request) throws SQLException, IOException;
}
