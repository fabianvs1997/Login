package com.banckiko.atm.login.controller;


import com.banckiko.atm.login.config.JwtTokenProvider;
import com.banckiko.atm.login.model.CuentasResponse;
import com.banckiko.atm.login.model.UserCredentials;
import com.banckiko.atm.login.model.registro.CuentasRequest;
import com.banckiko.atm.login.service.CuentasIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * <p>
 * Autor: Fabian Villaseñor Sanhcez
 * Fecha de creación: 16/02/2023
 * Última modificación: 16/02/2023
 * Versión: 0.1
 * <p>
 * Uso:
 * - POST /api/v1/login: Consulta el estado de una cuenta existente y devuelve una respuesta con información de la cuenta y un código de
 * estado HTTP.
 * - POST /api/v1/crearCuenta: Crea una nueva cuenta y devuelve una respuesta con información de la cuenta creada y un código de estado
 * HTTP.
 * - GET /api/v1/activarCuenta: Activa una cuenta existente y devuelve una respuesta con información de la cuenta y un código de estado
 * HTTP.
 * <p>
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


    @Autowired //Crea una instancia de inyeccion de dependencias
    private CuentasIService service;

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // Una instancia de JwtTokenProvider, que se utilizará para generar y validar tokens JWT


    // Maneja las solicitudes HTTP POST a la ruta "/auth/login"
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        // Genera un token JWT utilizando la instancia de JwtTokenProvider
        String token = jwtTokenProvider.generateToken(credentials.getUsername());
        // Devuelve el token JWT en una respuesta HTTP 200 OK utilizando ResponseEntity.ok().body(token)
        return ResponseEntity.ok().body(token);
    }




    /**
     * Consulta el estado de una cuenta y devuelve una respuesta con información de la cuenta y un código de estado HTTP.
     */
    @PostMapping("/logeo")              // asigana la ruta del login
    public ResponseEntity<?> consultaTarjeta(@RequestBody CuentasRequest request,
                                             @RequestParam("cuenta") String num_tarjeta,
                                             @RequestHeader("Authorization") String tokenHeader)
            throws SQLException, IOException {        //metodo que envia dos parametros, uno como body y otro como parametro
// Extrae el token JWT del encabezado de solicitud "Authorization"
        String token = tokenHeader.substring(7);

        if (jwtTokenProvider.validateToken(token)) {
        Long numT = Long.valueOf(num_tarjeta);    //convierte la cadena de caracteres tipo texto a long.
        CuentasResponse response = this.service.consultaTarjeta(numT, request);  // respuesta del request
        return new ResponseEntity<>(response.map(), HttpStatus.valueOf(response.getCode())); //mapeo de la respuesta y el codigo del estatus
        } else {
            return new ResponseEntity<>("error", HttpStatus.valueOf(400));
        }
    }


    @PostMapping("/nip")
    public ResponseEntity<?> nip(@RequestBody CuentasRequest request, @RequestParam("cuenta") String num_tarjeta) throws SQLException, IOException {        //metodo que envia dos parametros, uno como body y otro como parametro
        Long numT = Long.valueOf(num_tarjeta);    //convierte la cadena de caracteres tipo texto a long.
        CuentasResponse response = this.service.consultaTarjeta(numT, request);  // respuesta del request
        return new ResponseEntity<>(response.map(), HttpStatus.valueOf(response.getCode())); //mapeo de la respuesta y el codigo del estatus
    }
}



