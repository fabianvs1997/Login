package com.test.retirarefectivo.service.implementacion;

import com.test.retirarefectivo.dao.CuentasIDao;
import com.test.retirarefectivo.dto.CuentasDto;
import com.test.retirarefectivo.model.CuentasResponse;
import com.test.retirarefectivo.model.registro.CuentasRequest;
import com.test.retirarefectivo.service.CuentasIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CuentasServiceImplement implements CuentasIService {

    @Autowired
    private CuentasIDao dao;

    @Override
    public CuentasResponse consultaTarjeta(Long num_tarjeta, CuentasRequest request) throws SQLException, IOException {

        ResultSet resultSet = dao.conusltaNumTarjeta(num_tarjeta);
        CuentasResponse response = new CuentasResponse();
        if(resultSet.getString("estatus") != null){

            response.setMensaje("Aceptada.");
            response.setDetalles("Bienvenido "+ resultSet.getString("nombre_cuenta")+", ingresa tu nip.");
            response.setFolio("DSFUASH4534");


        }else {
            //detonar exception
        }


        return response;
    }
}
