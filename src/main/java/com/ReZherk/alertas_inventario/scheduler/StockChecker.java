package com.ReZherk.alertas_inventario.scheduler;

import com.ReZherk.alertas_inventario.domain.model.Product;
import com.ReZherk.alertas_inventario.domain.service.AlertService;
import com.ReZherk.alertas_inventario.domain.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockChecker {

    private final ProductService productService;
    private final AlertService alertService;

    public StockChecker(ProductService productService, AlertService alertService) {
        this.productService = productService;
        this.alertService = alertService;
    }

    // Programa la ejecución periódica del método cada 60,000 milisegundos (60 segundos), independientemente de cuánto tarde en completarse.
    // Ideal para tareas de monitoreo o verificación continua, como chequeo de stock o envío de alertas.
    // Esta anotación es parte de org.springframework.scheduling.annotation y requiere que @EnableScheduling esté activa en la configuración.
    @Scheduled(fixedRate = 60_000)
    public void checkLowStock() {
        productService.findAll().stream()
                .filter(p -> p.getActive())
                .filter(p -> p.getCurrentStock() <= p.getMinStock())
                .forEach(p -> {
                    String msg = "Stock bajo: " + p.getCurrentStock() +
                            " (mínimo " + p.getMinStock() + ")";
                    alertService.createLowStockAlert(p.getId(), msg);
                });
    }
}