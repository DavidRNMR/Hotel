package com.hotel.service;


import com.hotel.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO registrarCliente (ClienteDTO clienteDTO);

    ClienteDTO findByEmail (String email) throws Exception;
    boolean existsByEmail(String email);
}
