package com.hotel.dto;

import com.hotel.entity.Reserva;
import com.hotel.enums.TipoHabitacion;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class HabitacionTipoDTO {

    private Long id;
    private TipoHabitacion tipo;
    private BigDecimal precio;
    private int cantidadTotal;
    private List<ReservaDTO> reservasDTO;
}
