package com.hotel.enums;

import lombok.Getter;

@Getter
public enum ResenaPuntuacion {
    one (1),two (2), three (3), four (4), five (5);


    private final int estrella;

    ResenaPuntuacion (int estrella){
        this.estrella = estrella;
    }

}
