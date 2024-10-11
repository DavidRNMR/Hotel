package com.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TRASLADO")
public class Traslado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origen;

    @OneToOne(mappedBy = "traslado",fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}
