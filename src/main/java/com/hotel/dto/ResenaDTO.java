package com.hotel.dto;

import com.hotel.entity.Cliente;
import com.hotel.enums.ResenaPuntuacion;
import lombok.Data;


@Data
public class ResenaDTO {

    private Long id;
    private ResenaPuntuacion resenaPuntuacion;
    private Cliente cliente;
}
