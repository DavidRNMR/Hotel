package com.hotel.dto;

import com.hotel.entity.Reserva;
import lombok.Data;

@Data
public class TrasladoDTO {

    private Long id;
    private String origen;
    private Reserva reserva;
}
