package com.hotel.enums;

import java.math.BigDecimal;

public enum TipoTraslado {

    IDA(new BigDecimal("50.00")),
    IDAVUELTA(new BigDecimal("100.00"));


    private BigDecimal precio;

    TipoTraslado (BigDecimal precio) {

        this.precio = precio;
    }

    public BigDecimal getPrecio (){
        return precio;
    }
}
