package com.ReZherk.alertas_inventario.domain.repository;

import com.ReZherk.alertas_inventario.domain.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
