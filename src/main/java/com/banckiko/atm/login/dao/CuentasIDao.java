package com.banckiko.atm.login.dao;

import com.banckiko.atm.login.dto.CuentasDto;

import java.io.IOException;
import java.sql.SQLException;

public interface CuentasIDao {

    CuentasDto conusltaNumTarjeta(Long num_tarjeta) throws SQLException, IOException;





}
