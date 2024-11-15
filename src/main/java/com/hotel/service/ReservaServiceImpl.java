package com.hotel.service;

import com.hotel.dto.ActividadDTO;
import com.hotel.dto.ReservaDTO;
import com.hotel.entity.*;
import com.hotel.enums.EstadoReserva;
import com.hotel.enums.TipoTraslado;
import com.hotel.exception.HabitacionNoDisponibleException;
import com.hotel.mapper.HotelMapper;
import com.hotel.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private ActividadRepository actividadRepository;
    private TrasladoRepository trasladoRepository;


    public ReservaDTO crearReserva (ReservaDTO reservaDTO) throws Exception {

            Long clienteId = reservaDTO.getClienteId();
            Long habitacionTipoId = reservaDTO.getHabitacionTipoId();
            LocalDate fechaInicio = reservaDTO.getFechaInicio();
            LocalDate fechaFin = reservaDTO.getFechaFin();

            HabitacionTipo habitacionTipo = habitacionTipoRepository.findById(habitacionTipoId)
                    .orElseThrow(() -> new Exception("no existe esa habitación"));

            if (!verificarDisponibilidad(habitacionTipoId, fechaInicio, fechaFin)) {
                throw new HabitacionNoDisponibleException("habitación no disponible");
            }

            Reserva reserva = new Reserva();
            reserva.setCliente(clienteRepository.findById(clienteId).orElseThrow(() -> new Exception("cliente no existe")));
            reserva.setHabitacionTipo(habitacionTipo);
            reserva.setFechaInicio(fechaInicio);
            reserva.setFechaFin(fechaFin);
            reserva.setEstado(EstadoReserva.CONFIRMADA);

            List<ActividadDTO> actividadDTOList = reservaDTO.getActividadDTOList();


            for (ActividadDTO actividadDTO: actividadDTOList){

                Actividad actividad = new Actividad();
                actividad.setId(actividadDTO.getId());
                actividad.setTipoActividad(actividadDTO.getTipoActividad());
                actividad.setReserva(reserva);

                reserva.aniadirActividad(actividad);
                actividadRepository.save(actividad);
            }

            Traslado traslado = mapper.fromTrasladoDTO(reservaDTO.getTrasladoDTO());
            traslado.setReserva(reserva);
            reserva.setTraslado(traslado);
            trasladoRepository.save(traslado);

            Pago pago = new Pago();
            pago.setMonto(calcularPrecioReserva(reservaDTO));
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
    public void cancelarReserva(Long reservaId) throws Exception {

        Reserva reserva = reservaRepository.findById(reservaId)
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

    @Override
    public BigDecimal calcularPrecioReserva(ReservaDTO reservaDTO) throws Exception {
        BigDecimal montoTotal = BigDecimal.ZERO;

        HabitacionTipo habitacionTipo = habitacionTipoRepository.findById(reservaDTO.getHabitacionTipoId())
                .orElseThrow(() -> new Exception("no existe habitacion con esa id"));

        BigDecimal precioHabitacion = habitacionTipo.getTipo().getPrecioBase();
        montoTotal = montoTotal.add(precioHabitacion);

        if (reservaDTO.getActividadDTOList() != null) {
            for (ActividadDTO actividadDTO : reservaDTO.getActividadDTOList()) {

                BigDecimal precioActividad = actividadDTO.getTipoActividad().getPrecio();
                montoTotal = montoTotal.add(precioActividad);
            }
        }

        if (reservaDTO.getTrasladoDTO() != null && reservaDTO.getTrasladoDTO().getTipoTraslado() != null) {
            BigDecimal precioTraslado = reservaDTO.getTrasladoDTO().getTipoTraslado().getPrecio();
            montoTotal = montoTotal.add(precioTraslado);
        }

        return montoTotal;
    }

}
