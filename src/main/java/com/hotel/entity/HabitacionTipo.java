package com.hotel.entity;

import com.hotel.enums.TipoHabitacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "HabitacionTipo")
public class HabitacionTipo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipo;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    private int cantidadTotal;

    @OneToMany(mappedBy = "habitacionTipo", cascade = CascadeType.ALL)
    private List<Reserva> reservas;




}
