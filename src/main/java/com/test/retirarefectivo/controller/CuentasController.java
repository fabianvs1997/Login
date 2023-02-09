package com.test.retirarefectivo.controller;


import com.test.retirarefectivo.model.CuentasResponse;
import com.test.retirarefectivo.model.registro.CuentasRequest;
import com.test.retirarefectivo.service.CuentasIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class CuentasController {



    @Autowired
    private CuentasIService service;


    @PostMapping("/login")
    public ResponseEntity<CuentasResponse> consultaTarjeta(@RequestBody CuentasRequest resquest, @RequestParam("cuenta") String num_tarjeta) throws SQLException, IOException {

        Long numT = Long.valueOf(num_tarjeta);

        return new ResponseEntity<>(this.service.consultaTarjeta(numT, resquest), HttpStatusCode.valueOf(202));

    }


}
