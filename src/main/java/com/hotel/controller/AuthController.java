package com.hotel.controller;

import com.hotel.auth.ClienteUserDetails;
import com.hotel.auth.JwtUtil;
import com.hotel.dto.AuthResponseDTO;
import com.hotel.dto.ClienteProfileDTO;
import com.hotel.dto.ClienteDTO;
import com.hotel.entity.Cliente;
import com.hotel.service.ClienteServiceImpl;
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
@CrossOrigin("*")
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
        return ResponseEntity.ok("usuario registrado con exito");
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
    @GetMapping("/cliente")
    public ResponseEntity<String> getNombre(Authentication authentication){
        ClienteUserDetails userDetails = (ClienteUserDetails) authentication.getPrincipal();
        Cliente cliente = userDetails.getCliente();
        return ResponseEntity.status(HttpStatus.OK).body(cliente.getNombre());
    }

    @PutMapping("/actualizar-perfil")
    public ResponseEntity<ClienteProfileDTO> actualizarPerfil (@RequestBody ClienteProfileDTO clienteProfileDTO, Authentication authentication) throws Exception {
        ClienteUserDetails userDetails = (ClienteUserDetails) authentication.getPrincipal();
        Cliente cliente = userDetails.getCliente();

        return ResponseEntity.status(HttpStatus.OK).body(clienteServiceImpl.actualizarPerfil(clienteProfileDTO,cliente.getEmail()));
    }

    @GetMapping()
    public ResponseEntity<ClienteProfileDTO> obtenerDatosCliente (Authentication authentication) throws Exception {
        ClienteUserDetails userDetails = (ClienteUserDetails) authentication.getPrincipal();
        Cliente cliente = userDetails.getCliente();

        return ResponseEntity.status(HttpStatus.OK).body(clienteServiceImpl.getCliente(cliente.getId()));

    }

}
