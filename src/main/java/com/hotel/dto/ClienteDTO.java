package com.hotel.dto;


import lombok.Data;

import java.util.List;


@Data
public class ClienteDTO {

    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private List<ResenaDTO> resenaDTOList;

}
