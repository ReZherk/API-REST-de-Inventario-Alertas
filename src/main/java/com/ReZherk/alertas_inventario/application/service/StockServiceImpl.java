package com.ReZherk.alertas_inventario.application.service;

import com.ReZherk.alertas_inventario.domain.model.Product;
import com.ReZherk.alertas_inventario.domain.model.StockMovement;
import com.ReZherk.alertas_inventario.domain.model.enums.MovementType;
import com.ReZherk.alertas_inventario.domain.repository.ProductRepository;
import com.ReZherk.alertas_inventario.domain.repository.StockMovementRepository;
import com.ReZherk.alertas_inventario.domain.service.StockService;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class StockServiceImpl implements StockService {

    private final ProductRepository productRepo;
    private final StockMovementRepository movementRepo;

    public StockServiceImpl(ProductRepository productRepo, StockMovementRepository movementRepo) {
        this.productRepo = productRepo;
        this.movementRepo = movementRepo;
    }

    @Override
    public StockMovement move(Long productId, MovementType type, int quantity, String note) {
        Product p = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        int current = p.getCurrentStock();
        switch (type) {
            case IN:
                p.setCurrentStock(current + quantity);
                break;
            case OUT:
                int newStock = current - quantity;
                if (newStock < 0) throw new IllegalArgumentException("Stock insuficiente");
                p.setCurrentStock(newStock);
                break;
            case ADJUST:
                p.setCurrentStock(quantity);
                break;
        }

        StockMovement m = new StockMovement();
        m.setProduct(p);
        m.setType(type);
        m.setQuantity(quantity);
        m.setNote(note);

        return movementRepo.save(m);
    }
}
