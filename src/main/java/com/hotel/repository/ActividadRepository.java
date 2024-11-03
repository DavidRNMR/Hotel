package com.hotel.repository;

import com.hotel.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad,Long> {
}
