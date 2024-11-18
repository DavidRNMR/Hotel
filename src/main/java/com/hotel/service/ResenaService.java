package com.hotel.service;

import com.hotel.dto.ResenaDTO;

import java.util.List;

public interface ResenaService {

    ResenaDTO crearResena (ResenaDTO resenaDTO) throws Exception;
    List<ResenaDTO> mostrarResenas ();
}
