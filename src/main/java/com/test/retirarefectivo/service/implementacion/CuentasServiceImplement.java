package com.test.retirarefectivo.service.implementacion;

import com.test.retirarefectivo.dao.CuentasIDao;
import com.test.retirarefectivo.dto.CuentasDto;
import com.test.retirarefectivo.exception.GenericaException;
import com.test.retirarefectivo.model.CuentasResponse;
import com.test.retirarefectivo.model.registro.CuentasRequest;
import com.test.retirarefectivo.model.registro.RegistroCuentasRequest;
import com.test.retirarefectivo.service.CuentasIService;
import com.test.retirarefectivo.utilidades.Folios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CuentasServiceImplement implements CuentasIService {

    @Autowired
    private CuentasIDao dao;

    @Autowired
    private Folios folios;

    @Override
    public CuentasResponse consultaTarjeta(Long num_tarjeta, CuentasRequest request) throws SQLException, IOException {
        CuentasDto cuenta = dao.conusltaNumTarjeta(num_tarjeta);
        CuentasResponse response = new CuentasResponse();

        if (cuenta.getEstatus() != null) {
            if (cuenta.getEstatus() == 0) {
                throw new GenericaException("Cuenta No Activa.",
                        "Comunícate con un ejecutivo para activar tu cuenta.",
                        folios.folioResponse(), 200);
            }

            response.setMensaje("Aceptada.");
            response.setDetalles("Bienvenido " + cuenta.getNombre_cuenta() + ", ingresa tu NIP.");
            response.setFolio(folios.folioResponse());
            response.setCode(202);
        } else {
            throw new GenericaException("Cuenta No Encontrada.",
                    "Valida tus datos.", folios.folioResponse(), 204);
        }

        return response;
    }

    @Override
    public CuentasResponse crearCuenta(RegistroCuentasRequest request) throws SQLException, IOException {

        CuentasDto resultSet = dao.conusltaNumTarjeta(request.getCuenta());
        CuentasResponse response = new CuentasResponse();
        if (resultSet.getNombre_cuenta() == null) {
            dao.crearCuenta(convertirRequest(request));
            response.setMensaje("Cuenta Creada.");
            response.setDetalles("Se requiere activacion de cuenta.");
            response.setFolio(this.folios.folioResponse());
            response.setCode(201);
        } else {
            response.setMensaje("Cuenta Existente.");
            response.setDetalles("Valide sus datos.");
            response.setFolio(this.folios.folioResponse());
            response.setCode(200);
        }

        return response;
    }

    @Override
    public CuentasResponse avtivaCuenta(Long num_cuenta) throws SQLException, IOException {
        CuentasDto cuenta = dao.conusltaNumTarjeta(num_cuenta);
        CuentasResponse response = new CuentasResponse();
        if(cuenta.getEstatus() != null){
            if(cuenta.getEstatus() !=1){
                dao.activarCuenta(num_cuenta);
                response.setMensaje("Cuenta Se activo.");
                response.setDetalles("Ya puede realizar sus transacciones.");
                response.setFolio(this.folios.folioResponse());
                response.setCode(200);
            }else {
                response.setMensaje("La Cuenta Ya Se Encuentra Activa.");
                response.setDetalles("Ya puede realizar sus transacciones.");
                response.setFolio(this.folios.folioResponse());
                response.setCode(200);
            }
        }else{
            throw new GenericaException("Cuenta No Encontrada.",
                    "Valida tus datos.", folios.folioResponse(), 204);
        }

        return response;
    }


    private CuentasDto convertirRequest(RegistroCuentasRequest request) {
        CuentasDto cuentasDto = new CuentasDto();
        cuentasDto.setNombre_cuenta(request.getNombre());
        cuentasDto.setNumero_cuenta(request.getCuenta());
        cuentasDto.setEstatus(request.getStatus());
        return cuentasDto;
    }
}
