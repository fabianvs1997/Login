package com.test.retirarefectivo.controller;


import com.test.retirarefectivo.model.CuentasResponse;
import com.test.retirarefectivo.model.registro.CuentasRequest;
import com.test.retirarefectivo.model.registro.RegistroCuentasRequest;
import com.test.retirarefectivo.service.CuentasIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Controlador para las cuentas de usuario. Este controlador maneja las solicitudes HTTP relacionadas con las cuentas de los usuarios.
 * Proporciona endpoints para consultar el estado de una cuenta, crear una nueva cuenta y activar una cuenta existente. Cada endpoint
 * recibe y devuelve objetos JSON que se corresponden con las solicitudes y respuestas del servicio subyacente. Las solicitudes se
 * validan y se procesan utilizando una instancia del servicio `CuentasIService`, que maneja la lógica de negocios y la interacción con
 * la base de datos. Las respuestas se devuelven como objetos `ResponseEntity` que contienen la información de la cuenta y un código de
 * estado HTTP apropiado.
 *
 * Autor: Fabian Villaseñor Sanhcez
 * Fecha de creación: 16/02/2023
 * Última modificación: 16/02/2023
 * Versión: 0.1
 *
 * Uso:
 * - POST /api/v1/login: Consulta el estado de una cuenta existente y devuelve una respuesta con información de la cuenta y un código de
 * estado HTTP.
 * - POST /api/v1/crearCuenta: Crea una nueva cuenta y devuelve una respuesta con información de la cuenta creada y un código de estado
 * HTTP.
 * - GET /api/v1/activarCuenta: Activa una cuenta existente y devuelve una respuesta con información de la cuenta y un código de estado
 * HTTP.
 *
 * Notas:
 * - Este controlador utiliza las anotaciones `@RestController` y `@RequestMapping` para indicar que es un controlador que maneja
 * solicitudes en el path "/api/v1".
 * - La inyección de dependencias se realiza mediante la anotación `@Autowired`.
 * - Las respuestas se devuelven como objetos `ResponseEntity`.
 * - Este controlador depende del servicio `CuentasIService`, que se encarga de la lógica de negocios y la interacción con la base de datos.
 */
@RestController              // creación de API RESTful para manejar solicitudes HTTP y responder con objetos JSON o XML
@RequestMapping("/login")              //para mapear una URL específica
public class CuentasController {


    @Autowired
    private CuentasIService service;

    /**
     * Consulta el estado de una cuenta y devuelve una respuesta con información de la cuenta y un código de estado HTTP.
     */
    @PostMapping("/consultarTarjeta")              // asigana la ruta del login
    public ResponseEntity<?> consultaTarjeta(@RequestBody CuentasRequest request, @RequestParam("cuenta") String num_tarjeta) throws SQLException, IOException {        //metodo que envia dos parametros, uno como body y otro como parametro
        Long numT = Long.valueOf(num_tarjeta);    //convierte la cadena de caracteres tipo texto a long.
        CuentasResponse response = this.service.consultaTarjeta(numT, request);  // respuesta del request
        return new ResponseEntity<>(response.map(), HttpStatusCode.valueOf(response.getCode())); //mapeo de la respuesta y el codigo del estatus
    }

    /**
     * Crea una nueva cuenta y devuelve una respuesta con información de la cuenta creada y un código de estado HTTP.
     */
    @PostMapping("/crearCuenta")
    public ResponseEntity<?> crearCuenta(@RequestBody RegistroCuentasRequest request) throws SQLException, IOException {
        CuentasResponse response = this.service.crearCuenta(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

    /**
     * Activa una cuenta existente y devuelve una respuesta con información de la cuenta y un código de estado HTTP.
     */
    @GetMapping("/activarCuenta")
    /*METODO GET MAPPING UNICAMENTE RECIBE INFORMACION, ESTA ES RECIBIDA POR URL*/
    /*metodo publico ResponseEntity que permite regresar peticion http Metodo Activar cuenta que recibe parametro cuenta, Tiene las dos excepciones*/
    public ResponseEntity<?> activarCuenta(@RequestParam("cuenta") Long num_cuenta) throws SQLException, IOException {
        /*Declaracion de objeto que tiene un servicio avtivaCuenta*/
        CuentasResponse response = this.service.activaCuenta(num_cuenta);
        /**/
        return new ResponseEntity<>(response.map(), HttpStatusCode.valueOf(response.getCode()));
    }
}



