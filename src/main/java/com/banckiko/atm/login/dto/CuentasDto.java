package com.banckiko.atm.login.dto;

import lombok.Data;

@Data
public class CuentasDto {

    private String nombre_cuenta;
    private Long numero_cuenta;
    private Integer estatus;

    public CuentasDto(){
        super();
    }
    public CuentasDto(String nombre_cuenta, Long numero_cuenta, Integer estatus) {
        this.nombre_cuenta = nombre_cuenta;
        this.numero_cuenta = numero_cuenta;
        this.estatus = estatus;
    }


}
