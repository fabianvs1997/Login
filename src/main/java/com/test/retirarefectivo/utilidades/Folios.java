package com.test.retirarefectivo.utilidades;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class Folios {


    public String folioResponse(){
        //MSR-160223-ASKDJHG45
        String ramdomText = UUID.randomUUID().toString().toUpperCase().replace("-","").substring(0,10);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
        String fechaActual = simpleDateFormat.format(date);
        return  "MSR-"+fechaActual+"-"+ramdomText;
    }


}
