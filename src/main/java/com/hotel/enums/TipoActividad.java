package com.hotel.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum TipoActividad {

    SPA(new BigDecimal("60.00")),
    GIMNASIO(new BigDecimal("00.00")),
    AQUAGYM(new BigDecimal("00.00")),
    SURF(new BigDecimal("100.00")),
    SENDERISMO(new BigDecimal("30.00")),
    SNORKELING(new BigDecimal("75.00"));

    private BigDecimal precio;
    TipoActividad (BigDecimal precio){
        this.precio = precio;
    }
}
