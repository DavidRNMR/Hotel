package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteProfileDTO {

    private String nombre;
    private String email;
    private String password;
    private String telefono;
}
