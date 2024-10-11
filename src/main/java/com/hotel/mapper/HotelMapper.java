package com.hotel.mapper;
import com.hotel.dto.ClienteDTO;
import com.hotel.dto.HabitacionTipoDTO;
import com.hotel.dto.ReservaDTO;
import com.hotel.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelMapper {

public ReservaDTO fromReserva (Reserva reserva){

    ReservaDTO reservaDTO = new ReservaDTO();
    reservaDTO.setId(reserva.getId());
    reservaDTO.setFechaInicio(reserva.getFechaInicio());
    reservaDTO.setFechaFin(reserva.getFechaFin());
    reservaDTO.setEstado(reserva.getEstado());

    Cliente cliente = reserva.getCliente();
    reservaDTO.setClienteId(cliente.getId());
    reservaDTO.setNombreCliente(cliente.getNombre());

    HabitacionTipo habitacionTipo = reserva.getHabitacionTipo();
    reservaDTO.setHabitacionTipoId(habitacionTipo.getId());

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
public HabitacionTipoDTO fromHabitacionTipo(HabitacionTipo habitacionTipo){

    HabitacionTipoDTO habitacionTipoDTO = new HabitacionTipoDTO();
    BeanUtils.copyProperties(habitacionTipo,habitacionTipoDTO);
    return habitacionTipoDTO;
}

public HabitacionTipo fromHabitacionTipoDTO (HabitacionTipoDTO habitacionTipoDTO){

    HabitacionTipo habitacionTipo = new HabitacionTipo();
    BeanUtils.copyProperties(habitacionTipoDTO,habitacionTipo);
    return habitacionTipo;
}
}
