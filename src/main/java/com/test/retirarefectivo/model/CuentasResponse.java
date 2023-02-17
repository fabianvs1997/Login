package com.test.retirarefectivo.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CuentasResponse {

    private String mensaje;
    private String detalles;
    private String folio;

    private Integer code;


    public CuentasResponse(){
        super();
    }

    public CuentasResponse(String mensaje, String detalles, String folio, Integer code) {
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.folio = folio;
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CuentasResponse{");
        sb.append("mensaje: '").append(mensaje).append('\'');
        sb.append(", detalles: '").append(detalles).append('\'');
        sb.append(", folio: '").append(folio).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Map<String, Object> map(){
        Map<String, Object> res = new HashMap<>();
        res.put("mensaje", mensaje);
        res.put("detalles", detalles);
        res.put("folio", folio);
        return res;

    }
}
