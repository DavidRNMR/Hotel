package com.hotel.dto;

import com.hotel.enums.EstadoReserva;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ReservaDTO {

    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private Long clienteId;
    private String nombreCliente;
    private Long habitacionTipoId;
    private Long pagoId;
    private Long trasladoId;
}
