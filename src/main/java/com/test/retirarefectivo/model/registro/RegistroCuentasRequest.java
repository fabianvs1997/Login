package com.test.retirarefectivo.model.registro;

import lombok.Data;

@Data
public class RegistroCuentasRequest {

    private String nombre;
    private Long cuenta;
    private Integer status;


}
