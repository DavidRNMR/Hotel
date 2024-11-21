package com.hotel.controller;

import com.hotel.auth.ClienteUserDetails;
import com.hotel.dto.ResenaDTO;
import com.hotel.entity.Cliente;
import com.hotel.service.ResenaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/resenas")
@CrossOrigin("*")
public class ResenaController {

    private ResenaService resenaService;

    @PostMapping
    public ResponseEntity<?> crearResena(@RequestBody ResenaDTO resenaDTO, Authentication authentication) throws Exception {

        ClienteUserDetails userDetails = (ClienteUserDetails) authentication.getPrincipal();
        Cliente cliente = userDetails.getCliente();

        resenaDTO.setClienteId(cliente.getId());
        ResenaDTO newResenaDTO = resenaService.crearResena(resenaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newResenaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listarResenas() {
        return ResponseEntity.status(HttpStatus.OK).body(resenaService.mostrarResenas());
    }
}
