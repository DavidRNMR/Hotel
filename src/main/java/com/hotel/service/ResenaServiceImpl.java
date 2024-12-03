package com.hotel.service;

import com.hotel.dto.ResenaDTO;
import com.hotel.entity.Cliente;
import com.hotel.entity.Resena;
import com.hotel.mapper.HotelMapper;
import com.hotel.repository.ClienteRepository;
import com.hotel.repository.ResenaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ResenaServiceImpl implements ResenaService{

    private ResenaRepository resenaRepository;
    private ClienteRepository clienteRepository;
    private HotelMapper hotelMapper;

    @Override
    public ResenaDTO crearResena(ResenaDTO resenaDTO,Long id) throws Exception {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new Exception("no existe cliente con esa id"));

        Resena resena = hotelMapper.fromResenaDTO(resenaDTO);
        resena.setCliente(cliente);
        resena.setFechaResena(LocalDateTime.now());

        resenaRepository.save(resena);

        return hotelMapper.fromResena(resena);
    }

    @Override
    public List<ResenaDTO> mostrarResenas() {

        List<Resena> resenas = resenaRepository.findAll();

        return resenas.stream().map(resena -> hotelMapper.fromResena(resena)).collect(Collectors.toList());
    }
}
