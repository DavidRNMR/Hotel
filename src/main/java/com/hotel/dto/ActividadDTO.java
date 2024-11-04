package com.hotel.dto;

import com.hotel.enums.TipoActividad;
import lombok.Data;


@Data
public class ActividadDTO {

    private Long id;
    private TipoActividad tipoActividad;
}
