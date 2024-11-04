package com.hotel.dto;

import com.hotel.enums.TipoTraslado;
import lombok.Data;

@Data
public class TrasladoDTO {

    private Long id;
    private TipoTraslado tipoTraslado;

}
