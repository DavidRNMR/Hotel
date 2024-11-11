package com.hotel.enums;

import java.math.BigDecimal;

public enum TipoTraslado {

    IDA(new BigDecimal("10.00")),
    IDAVUELTA(new BigDecimal("20.00")),
    SINTRASLADO(new BigDecimal("00.00"));


    private BigDecimal precio;

    TipoTraslado (BigDecimal precio) {

        this.precio = precio;
    }

    public BigDecimal getPrecio (){
        return precio;
    }
}
