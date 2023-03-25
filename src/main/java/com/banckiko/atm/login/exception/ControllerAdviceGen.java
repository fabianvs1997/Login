package com.banckiko.atm.login.exception;


import com.banckiko.atm.login.model.CuentasResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceGen {


    @ExceptionHandler(GenericaException.class)
    public ResponseEntity<?> gen(GenericaException e){
        CuentasResponse response = new CuentasResponse();
        response.setMensaje(e.getMensaje());
        response.setDetalles(e.getDetalles());
        response.setFolio(e.getFolio());
        return  new ResponseEntity<>(response.map(), HttpStatus.valueOf(e.getCode()));

    }


}
