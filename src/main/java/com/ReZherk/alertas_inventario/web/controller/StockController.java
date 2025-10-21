package com.ReZherk.alertas_inventario.web.controller;

import com.ReZherk.alertas_inventario.domain.model.StockMovement;
import com.ReZherk.alertas_inventario.domain.service.StockService;
import com.ReZherk.alertas_inventario.web.dto.MovementRequestDTO;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/move")
    public ResponseEntity<Long> move(@Valid @RequestBody MovementRequestDTO dto) {
        StockMovement m = stockService.move(dto.productId, dto.type, dto.quantity, dto.note);
        return ResponseEntity.ok(m.getId());
    }
}
