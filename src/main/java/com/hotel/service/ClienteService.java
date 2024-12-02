package com.hotel.service;


import com.hotel.dto.ClienteProfileDTO;
import com.hotel.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO registrarCliente (ClienteDTO clienteDTO);

    ClienteDTO findByEmail (String email) throws Exception;
    boolean existsByEmail(String email);

    ClienteProfileDTO actualizarPerfil(ClienteProfileDTO clienteProfileDTO, String email) throws Exception;

    ClienteProfileDTO getCliente (Long id) throws Exception;

}
