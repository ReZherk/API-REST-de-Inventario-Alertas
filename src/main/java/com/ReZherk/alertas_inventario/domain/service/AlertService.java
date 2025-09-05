package com.ReZherk.alertas_inventario.domain.service;

import com.ReZherk.alertas_inventario.domain.model.Alert;

import java.util.List;

public interface AlertService {
    Alert createLowStockAlert(Long productId, String message);

    List<Alert> findAll();

    Alert acknowledge(Long alertId);

}
