package com.hotel.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum TipoHabitacion {

    DOBLE(new BigDecimal("100.00")),
    TRIPLE(new BigDecimal("150.00")),
    DELUXE(new BigDecimal("200.00"));

    private BigDecimal precioBase;

    TipoHabitacion (BigDecimal precioBase){
        this.precioBase = precioBase;
    }

}
