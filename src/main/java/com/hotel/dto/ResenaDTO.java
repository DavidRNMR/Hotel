package com.hotel.dto;

import com.hotel.enums.ResenaPuntuacion;
import lombok.Data;


@Data
public class ResenaDTO {

    private Long id;
    private ResenaPuntuacion resenaPuntuacion;
    private String descripcion;
    private Long clienteId;
}
