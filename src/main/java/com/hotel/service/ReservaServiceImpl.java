package com.hotel.service;

import com.hotel.dto.ReservaDTO;
import com.hotel.entity.HabitacionTipo;
import com.hotel.entity.Pago;
import com.hotel.entity.Reserva;
import com.hotel.enums.EstadoReserva;
import com.hotel.mapper.HotelMapper;
import com.hotel.repository.ClienteRepository;
import com.hotel.repository.HabitacionTipoRepository;
import com.hotel.repository.PagoRepository;
import com.hotel.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ReservaServiceImpl implements ReservaService {

    private ReservaRepository reservaRepository;
    private HotelMapper mapper;
    private HabitacionTipoRepository habitacionTipoRepository;
    private ClienteRepository clienteRepository;
    private PagoRepository pagoRepository;


    public ReservaDTO crearReserva (ReservaDTO reservaDTO) throws Exception {

            Long clienteId = reservaDTO.getClienteId();
            Long habitacionTipoId = reservaDTO.getHabitacionTipoId();
            LocalDate fechaInicio = reservaDTO.getFechaInicio();
            LocalDate fechaFin = reservaDTO.getFechaFin();

            HabitacionTipo habitacionTipo = habitacionTipoRepository.findById(habitacionTipoId)
                    .orElseThrow(() -> new Exception("no existe esa habitación"));

            if (!verificarDisponibilidad(habitacionTipoId, fechaInicio, fechaFin)) {
                throw new Exception("habitación no disponible");
            }

            Reserva reserva = new Reserva();
            reserva.setCliente(clienteRepository.findById(clienteId).orElseThrow(() -> new Exception("cliente no existe")));
            reserva.setHabitacionTipo(habitacionTipo);
            reserva.setFechaInicio(fechaInicio);
            reserva.setFechaFin(fechaFin);
            reserva.setEstado(EstadoReserva.CONFIRMADA);

            Pago pago = new Pago();
            pago.setMonto(habitacionTipo.getTipo().getPrecioBase());
            reserva.setPago(pago);
            pagoRepository.save(pago);

            reservaRepository.save(reserva);

            return mapper.fromReserva(reserva);

    }

    @Override
    public List<ReservaDTO> obtenerReservasPorCliente(Long clienteId) {

        List<Reserva> reservas = reservaRepository.findByClienteId(clienteId);

        return reservas.stream()
                .map(reserva -> mapper.fromReserva(reserva)).collect(Collectors.toList());
    }

    @Override
    public void cancelarReserva(Long reservaId, Long clienteId) throws Exception {

        Reserva reserva = reservaRepository.findByIdAndClienteId(reservaId,clienteId)
                .orElseThrow(()-> new Exception("no existe reserva"));

        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaRepository.save(reserva);
    }
    public boolean verificarDisponibilidad(Long habitacionTipoId, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        HabitacionTipo habitacionTipo = habitacionTipoRepository.findById(habitacionTipoId)
                .orElseThrow(() -> new Exception("no existe esa habitación"));

        int cantidadTotal = habitacionTipo.getCantidadTotal();


        List<Reserva> reservas = reservaRepository.findReservasForUpdate(habitacionTipoId, fechaInicio, fechaFin);

        int habitacionesOcupadas = reservas.size();

        int habitacionesDisponibles = cantidadTotal - habitacionesOcupadas;

        return habitacionesDisponibles > 0;
    }

}
