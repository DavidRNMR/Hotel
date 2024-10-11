package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hotel.entity.Resena;
import com.hotel.entity.Reserva;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;

}
