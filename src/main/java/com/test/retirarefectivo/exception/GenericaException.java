package com.test.retirarefectivo.exception;


import lombok.Data;

@Data
public class GenericaException extends RuntimeException {

    private String mensaje;
    private String detalles;
    private String folio;
    private Integer code;

    public GenericaException() {
    }

    public GenericaException(String mensaje, String detalles, String folio, Integer code) {
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.folio = folio;
        this.code = code;
    }




}
