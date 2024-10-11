package com.hotel.controller;

import com.hotel.auth.JwtUtil;
import com.hotel.dto.AuthResponseDTO;
import com.hotel.dto.ClienteDTO;
import com.hotel.mapper.HotelMapper;
import com.hotel.service.ClienteServiceImpl;

import com.hotel.entity.Cliente;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClienteDTO clienteDTO) {
        if (clienteServiceImpl.existsByEmail(clienteDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya está registrado");
        }
        clienteServiceImpl.registrarCliente(clienteDTO);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClienteDTO clienteDTO) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(clienteDTO.getEmail(), clienteDTO.getPassword())
            );

            final UserDetails userDetails = clienteServiceImpl.loadUserByUsername(clienteDTO.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponseDTO(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación");
        }
    }

}
