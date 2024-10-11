package com.hotel.repository;

import com.hotel.entity.Reserva;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.habitacionTipo.id = :habitacionTipoId AND r.estado != 'CANCELADA' AND "
            + "((:fechaInicio BETWEEN r.fechaInicio AND r.fechaFin) OR "
            + "(:fechaFin BETWEEN r.fechaInicio AND r.fechaFin) OR "
            + "(r.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR "
            + "(r.fechaFin BETWEEN :fechaInicio AND :fechaFin) OR "
            + "(r.fechaInicio <= :fechaInicio AND r.fechaFin >= :fechaFin))")
    int countHabitacionesOcupadas(@Param("habitacionTipoId") Long habitacionTipoId,
                                  @Param("fechaInicio") LocalDate fechaInicio,
                                  @Param("fechaFin") LocalDate fechaFin);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reserva r WHERE r.habitacionTipo.id = :habitacionTipoId AND r.estado != 'CANCELADA' AND "
            + "((:fechaInicio BETWEEN r.fechaInicio AND r.fechaFin) OR "
            + "(:fechaFin BETWEEN r.fechaInicio AND r.fechaFin) OR "
            + "(r.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR "
            + "(r.fechaFin BETWEEN :fechaInicio AND :fechaFin) OR "
            + "(r.fechaInicio <= :fechaInicio AND r.fechaFin >= :fechaFin))")
    List<Reserva> findReservasForUpdate(@Param("habitacionTipoId") Long habitacionTipoId,
                                        @Param("fechaInicio") LocalDate fechaInicio,
                                        @Param("fechaFin") LocalDate fechaFin);

    Optional<Reserva> findByIdAndClienteId   (Long id, Long clienteId);

    List<Reserva> findByClienteId(Long clienteId);
}
