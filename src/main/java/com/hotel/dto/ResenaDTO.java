package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.enums.ResenaPuntuacion;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ResenaDTO {

    private Long id;
    private ResenaPuntuacion resenaPuntuacion;
    private String descripcion;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaResena;
    private Long clienteId;
}
