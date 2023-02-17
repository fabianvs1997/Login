package com.test.retirarefectivo.exception;


import com.test.retirarefectivo.model.CuentasResponse;
import org.springframework.http.HttpStatusCode;
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
        return  new ResponseEntity<>(response.map(), HttpStatusCode.valueOf(e.getCode()));

    }


}
