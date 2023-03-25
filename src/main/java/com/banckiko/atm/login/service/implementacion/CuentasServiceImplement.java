package com.banckiko.atm.login.service.implementacion;

import com.banckiko.atm.login.dao.CuentasIDao;
import com.banckiko.atm.login.dto.CuentasDto;
import com.banckiko.atm.login.exception.GenericaException;
import com.banckiko.atm.login.model.CuentasResponse;
import com.banckiko.atm.login.model.registro.CuentasRequest;
import com.banckiko.atm.login.service.CuentasIService;
import com.banckiko.atm.login.utilidades.Folios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

}
