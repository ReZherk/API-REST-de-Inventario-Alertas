package com.ReZherk.alertas_inventario.application.service;

import com.ReZherk.alertas_inventario.domain.model.Alert;
import com.ReZherk.alertas_inventario.domain.model.Product;
import com.ReZherk.alertas_inventario.domain.model.enums.AlertType;
import com.ReZherk.alertas_inventario.domain.repository.AlertRepository;
import com.ReZherk.alertas_inventario.domain.repository.ProductRepository;
import com.ReZherk.alertas_inventario.domain.service.AlertService;
import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class AlertServiceImpl implements AlertService {

    private static final Logger log = LoggerFactory.getLogger(AlertServiceImpl.class);

    private final AlertRepository alertRepo;
    private final ProductRepository productRepo;

    public AlertServiceImpl(AlertRepository alertRepo, ProductRepository productRepo) {
        this.alertRepo = alertRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Alert createLowStockAlert(Long productId, String message) {
        Product p = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        Alert a = new Alert();
        a.setProduct(p);
        a.setType(AlertType.LOW_STOCK);
        a.setMessage(message);

        Alert saved = alertRepo.save(a);

        // Simular envío de correo
        log.info("[ALERTA] LOW_STOCK | SKU={} | {} -> {}", p.getSku(), p.getName(), message);

        return saved;
    }

    @Override @Transactional(readOnly = true)
    public List<Alert> findAll() { return alertRepo.findAll(); }

    @Override
    public Alert acknowledge(Long alertId) {
        Alert a = alertRepo.findById(alertId)
                .orElseThrow(() -> new EntityNotFoundException("Alerta no encontrada"));
        a.setAcknowledged(true);
        return a;
    }
}