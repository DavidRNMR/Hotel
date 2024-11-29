package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.enums.EstadoReserva;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private BigDecimal precio;
    private List<ActividadDTO> actividadDTOList = new ArrayList<>();
    private TrasladoDTO trasladoDTO;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaReserva;
}
