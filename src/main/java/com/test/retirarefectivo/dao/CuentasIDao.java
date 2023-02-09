package com.test.retirarefectivo.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CuentasIDao {

    ResultSet conusltaNumTarjeta(Long num_tarjeta) throws SQLException, IOException;

}
