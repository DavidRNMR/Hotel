package com.hotel.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum TipoActividad {

    SURF(new BigDecimal("100.00")),
    GIMNASIO(new BigDecimal("50.00")),
    SPA(new BigDecimal("50.00"));

    private BigDecimal precio;
    TipoActividad (BigDecimal precio){
        this.precio = precio;
    }
}
