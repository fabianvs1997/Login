package com.test.retirarefectivo.model;

import lombok.Data;

@Data
public class CuentasResponse {

    private String mensaje;
    private String detalles;
    private String folio;


    public CuentasResponse(){
        super();
    }

    public CuentasResponse(String mensaje, String detalles, String folio) {
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.folio = folio;
    }
}
