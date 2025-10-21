package com.ReZherk.alertas_inventario.web.controller;

import com.ReZherk.alertas_inventario.domain.model.Alert;
import com.ReZherk.alertas_inventario.domain.service.AlertService;
import com.ReZherk.alertas_inventario.web.dto.AlertResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;
    private final DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public List<AlertResponseDTO> list() {
        return alertService.findAll().stream().map(a -> {
            AlertResponseDTO dto = new AlertResponseDTO();
            dto.id = a.getId();
            dto.productId = a.getProduct().getId();
            dto.productSku = a.getProduct().getSku();
            dto.productName = a.getProduct().getName();
            dto.type = a.getType().name();
            dto.message = a.getMessage();
            dto.acknowledged = a.getAcknowledged();
            dto.createdAt = a.getCreatedAt().format(fmt);
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping("/{id}/ack")
    public ResponseEntity<Void> acknowledge(@PathVariable Long id) {
        alertService.acknowledge(id);
        return ResponseEntity.noContent().build();
    }
}
