package com.hotel.dto;

import com.hotel.entity.Reserva;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagoDTO {

    private Long id;
    private BigDecimal monto;
    private Reserva reserva;
}
