package com.hotel.dto;

import com.hotel.enums.TipoActividad;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActividadDTO {

    private Long id;
    private LocalDateTime horaInicio;
    private TipoActividad tipoActividad;
}
