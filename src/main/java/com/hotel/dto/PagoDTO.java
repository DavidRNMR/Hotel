package com.hotel.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagoDTO {

    private Long id;
    private BigDecimal monto;
    private ReservaDTO reservaDTO;
}
