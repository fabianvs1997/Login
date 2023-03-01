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

    @Override                                                //hace refencia a una interfaz
    public CuentasResponse consultaTarjeta(Long num_tarjeta, CuentasRequest request) throws SQLException, IOException {
        CuentasDto cuenta = this.dao.conusltaNumTarjeta(num_tarjeta);      //cuentasdto es el objeto que hace refencia a nuestra tabla de la Bd
        CuentasResponse response = new CuentasResponse();                 //dao conexion a Bd donde el metodo espera un parametro tipo long
                                                                           //objeto - modelo de respuesta
        if (cuenta.getEstatus() != null) {                // si no se obtienen datos de la consulta
            if (cuenta.getEstatus() == 0) {               // si es estatus de mi cuenta es =0 manda esta excepcion
                throw new GenericaException("Cuenta No Activa.",
                        "Comunícate con un ejecutivo para activar tu cuenta.",
                        this.folios.folioResponse(), 200);    //genera un folio con nuestro metodo que genera folios
            }

            response.setMensaje("Aceptada.");  //si es diferente de 0 va a mandar esta respuesta, con el folio, codigo y detalles.
            response.setDetalles("Bienvenido " + cuenta.getNombre_cuenta() + ", ingresa tu NIP.");
            response.setFolio(this.folios.folioResponse());
            response.setCode(202);
        } else {
            throw new GenericaException("Cuenta No Encontrada.",     //si hay alguna excepcion mostrara esta generica
                    "Valida tus datos.", this.folios.folioResponse(), 204);
        }

        return response;  //retornamos nuestra variable response
    }

    @Override
    public CuentasResponse crearCuenta(RegistroCuentasRequest request) throws SQLException, IOException {

        CuentasDto resultSet = this.dao.conusltaNumTarjeta(request.getCuenta());
        CuentasResponse response = new CuentasResponse();
        if (resultSet.getNombre_cuenta() == null) {
            this.dao.crearCuenta(convertirRequest(request));
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


    private CuentasDto convertirRequest(RegistroCuentasRequest request) {       //convierte el resquest a cuenta dto
        CuentasDto cuentasDto = new CuentasDto();
        cuentasDto.setNombre_cuenta(request.getNombre());
        cuentasDto.setNumero_cuenta(request.getCuenta());
        cuentasDto.setEstatus(request.getStatus());
        return cuentasDto;
    }
}
