package com.banckiko.atm.login.exception;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
