package com.hotel.service;

import com.hotel.dto.ReservaDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    ReservaDTO crearReserva (ReservaDTO reservaDTO) throws Exception;

    List<ReservaDTO> obtenerReservasPorCliente (Long clienteId);

    void cancelarReserva(Long reservaId, Long clienteId) throws Exception;
}
