package com.ReZherk.alertas_inventario.domain.repository;

import com.ReZherk.alertas_inventario.domain.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
