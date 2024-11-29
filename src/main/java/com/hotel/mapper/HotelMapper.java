package com.hotel.mapper;
import com.hotel.dto.*;
import com.hotel.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HotelMapper {

public ReservaDTO fromReserva (Reserva reserva){

    ReservaDTO reservaDTO = new ReservaDTO();
    reservaDTO.setId(reserva.getId());
    reservaDTO.setFechaInicio(reserva.getFechaInicio());
    reservaDTO.setFechaFin(reserva.getFechaFin());
    reservaDTO.setEstado(reserva.getEstado());
    reservaDTO.setFechaReserva(LocalDateTime.now());

    Cliente cliente = reserva.getCliente();
    reservaDTO.setClienteId(cliente.getId());
    reservaDTO.setNombreCliente(cliente.getNombre());

    HabitacionTipo habitacionTipo = reserva.getHabitacionTipo();
    reservaDTO.setHabitacionTipoId(habitacionTipo.getId());

    Pago pago = reserva.getPago();
    reservaDTO.setPrecio(pago.getMonto());

    List<Actividad> actividades = reserva.getActividades();

    List<ActividadDTO> actividadDTOList = actividades.stream().map(this::fromActividad)
            .toList();

    reservaDTO.setTrasladoDTO(fromTraslado(reserva.getTraslado()));

reservaDTO.setActividadDTOList(actividadDTOList);
    return reservaDTO;
}


public ClienteDTO fromCliente (Cliente cliente){

    ClienteDTO clienteDTO = new ClienteDTO();
    BeanUtils.copyProperties(cliente,clienteDTO);

    return clienteDTO;
}

public Cliente fromClienteDTO (ClienteDTO clienteDTO){

    Cliente cliente = new Cliente();
    BeanUtils.copyProperties(clienteDTO,cliente);
    return cliente;
}

public ActividadDTO fromActividad (Actividad actividad){

    ActividadDTO actividadDTO = new ActividadDTO();
    BeanUtils.copyProperties(actividad,actividadDTO);

    return actividadDTO;
}

public TrasladoDTO fromTraslado (Traslado traslado){

    TrasladoDTO trasladoDTO = new TrasladoDTO();
    BeanUtils.copyProperties(traslado,trasladoDTO);
    return  trasladoDTO;
}

public Traslado fromTrasladoDTO (TrasladoDTO trasladoDTO){

    Traslado traslado = new Traslado();
    BeanUtils.copyProperties(trasladoDTO,traslado);
    return traslado;
}

public ResenaDTO fromResena (Resena resena){

    ResenaDTO resenaDTO = new ResenaDTO();
    resenaDTO.setId(resena.getId());
    resenaDTO.setResenaPuntuacion(resena.getResenaPuntuacion());
    resenaDTO.setDescripcion(resena.getDescripcion());
    resenaDTO.setFechaResena(LocalDateTime.now());
    resenaDTO.setClienteId(resena.getCliente().getId());

    return resenaDTO;
}

public Resena fromResenaDTO (ResenaDTO resenaDTO){

    Resena resena = new Resena();
    BeanUtils.copyProperties(resenaDTO,resena);
    return  resena;
}

}
