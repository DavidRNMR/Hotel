package com.hotel.service;

import com.hotel.auth.ClienteUserDetails;
import com.hotel.dto.ClienteDTO;
import com.hotel.entity.Cliente;
import com.hotel.mapper.HotelMapper;
import com.hotel.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService, UserDetailsService {

    private ClienteRepository clienteRepository;
    private HotelMapper mapper;
    private PasswordEncoder passwordEncoder;


    @Override
    public ClienteDTO registrarCliente(ClienteDTO clienteDTO) {

            Cliente cliente = mapper.fromClienteDTO(clienteDTO);
            cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
            clienteRepository.save(cliente);
            return mapper.fromCliente(cliente);
        }

    @Override
    public ClienteDTO findByEmail(String email) throws Exception {

        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(()->new Exception("no existe cliente"));

        return mapper.fromCliente(cliente);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new ClienteUserDetails(cliente);
    }
}
