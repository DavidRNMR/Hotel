package com.hotel.entity;

import com.hotel.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "RESERVA")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_tipo_id")
    private HabitacionTipo habitacionTipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id")
    private Pago pago;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Actividad> actividades;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "traslado_id")
    private Traslado traslado;
}
