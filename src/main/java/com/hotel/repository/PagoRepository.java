package com.hotel.repository;

import com.hotel.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago,Long> {
}
