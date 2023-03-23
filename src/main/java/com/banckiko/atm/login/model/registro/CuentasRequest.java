package com.banckiko.atm.login.model.registro;

import lombok.Data;

@Data
public class CuentasRequest {

    private String folio;
    private String idCajero;
    private String fecha;


    public CuentasRequest(){
        super();
    }

    public CuentasRequest(String folio, String idCajero, String fecha) {
        this.folio = folio;
        this.idCajero = idCajero;
        this.fecha = fecha;
    }
}
