package com.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "el nombre es obligatorio")
    @Size(min = 2, max = 29, message = "el nombre debe tener entre 2 y 20 caracteres")
    private String nombre;

    @Email(message = "correo electrónico no válido")
    @NotNull(message = "el email es obligatorio")
    @Column(unique = true)
    private String email;

    @NotNull(message = "contraseña obligatoria")
    private String password;

    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Resena> resenas;

}
