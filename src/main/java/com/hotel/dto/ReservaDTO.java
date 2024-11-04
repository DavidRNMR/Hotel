package com.hotel.dto;

import com.hotel.enums.EstadoReserva;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    private List<ActividadDTO> actividadDTOList = new ArrayList<>();
    private TrasladoDTO trasladoDTO;
}
