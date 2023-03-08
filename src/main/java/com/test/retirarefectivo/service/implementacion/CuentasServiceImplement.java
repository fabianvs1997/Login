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
    /*metodo publico con parametros que espera*/
    public CuentasResponse activaCuenta(Long num_cuenta) throws SQLException, IOException {
        /*Creacion de objeto CuentasDto y accesa al metodo dao y espera el parametro num_cuenta*/
        CuentasDto cuenta = dao.conusltaNumTarjeta(num_cuenta);
        /*Creacion de objeto para la respuesa*/
        CuentasResponse response = new CuentasResponse();
        /*Validacion si el estado que regresa la consulta es diferente a null entra a la segunda validadcion*/
        if(cuenta.getEstatus() != null){
            /*si el estado de la consulta es diferente a 1 entonces ingresa al metodo activar cuenta con el parametro num cuenta*/
            if(cuenta.getEstatus() !=1){
                dao.activarCuenta(num_cuenta);
                /*Envia los mensajes y con response envia mensaje yu detalles, asi como el folio de la transaccion que esta mencionando*/
                response.setMensaje("Cuenta Se activo.");
                response.setDetalles("Ya puede realizar sus transacciones.");
                response.setFolio(this.folios.folioResponse());
                /*Responde con el code 200*/
                response.setCode(200);
            }else {
                /*si el estado de la consulta es igual a 1 entonces nmotifica que la cuenta ya se encuentra activa y puede realizar transacciones de igual manera responde con un code 200*/
                response.setMensaje("La Cuenta Ya Se Encuentra Activa.");
                response.setDetalles("Ya puede realizar sus transacciones.");
                response.setFolio(this.folios.folioResponse());
                response.setCode(200);
            }
        }else{
            /*Validacion si el estado que regresa la consulta es igual a null entonces la consuilta regreso datos no satisfactorios y envia codigo 204 de cuenta no localizada*/
            /*Validacion si el estado que regresa la consulta es igual a null entonces la consuilta regreso datos no satisfactorios y envia codigo 204 de cuenta no localizada*/
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
