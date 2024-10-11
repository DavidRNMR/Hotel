package com.hotel.dto;

import com.hotel.entity.Reserva;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActividadDTO {

    private Long id;
    private String nombre;
    private LocalDateTime horaInicio;
    private Reserva reserva;
}
