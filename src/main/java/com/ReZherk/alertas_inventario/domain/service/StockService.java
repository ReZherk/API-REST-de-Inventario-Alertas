package com.ReZherk.alertas_inventario.domain.service;

import com.ReZherk.alertas_inventario.domain.model.StockMovement;
import com.ReZherk.alertas_inventario.domain.model.enums.MovementType;

public interface StockService {

    StockMovement move(Long productId, MovementType type, int quantity, String note);
}
