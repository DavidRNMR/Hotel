package com.hotel.controller;

import com.hotel.auth.ClienteUserDetails;
import com.hotel.dto.ReservaDTO;
import com.hotel.entity.Cliente;
import com.hotel.service.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@AllArgsConstructor
@CrossOrigin("*")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO, Authentication authentication) throws Exception {
        ClienteUserDetails userDetails = (ClienteUserDetails) authentication.getPrincipal();
        Cliente cliente = userDetails.getCliente();

         reservaDTO.setClienteId(cliente.getId());

         ReservaDTO newReservaDTO = reservaService.crearReserva(reservaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newReservaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas(Authentication authentication) {
        ClienteUserDetails userDetails = (ClienteUserDetails) authentication.getPrincipal();
        Cliente cliente = userDetails.getCliente();
        List<ReservaDTO> reservas = reservaService.obtenerReservasPorCliente(cliente.getId());
        return ResponseEntity.ok(reservas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable Long id, Authentication authentication) throws Exception {
        ClienteUserDetails userDetails = (ClienteUserDetails) authentication.getPrincipal();
        Cliente cliente = userDetails.getCliente();
        reservaService.cancelarReserva(id, cliente.getId());
        return ResponseEntity.ok("Reserva cancelada exitosamente");
    }
}
