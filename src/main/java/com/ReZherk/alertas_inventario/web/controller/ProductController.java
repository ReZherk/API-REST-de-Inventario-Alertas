package com.ReZherk.alertas_inventario.web.controller;

import com.ReZherk.alertas_inventario.domain.model.Product;
import com.ReZherk.alertas_inventario.domain.service.ProductService;
import com.ReZherk.alertas_inventario.web.dto.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    // Para timestamps legibles en logs, auditorías o respuestas JSON
    private final DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductCreateDTO dto) {
        Product p = new Product();
        p.setName(dto.name);
        p.setSku(dto.sku);
        p.setMinStock(dto.minStock);
        p.setCurrentStock(dto.currentStock);
        p.setActive(true);

        Product saved = service.create(p);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody ProductUpdateDTO dto) {
        Product data = new Product();
        data.setName(dto.name);
        data.setMinStock(dto.minStock);
        data.setCurrentStock(dto.currentStock);
        data.setActive(dto.active != null ? dto.active : true);

        Product updated = service.update(id, data);
        return ResponseEntity.ok(toDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> get(@PathVariable Long id) {
        Product p = service.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return ResponseEntity.ok(toDTO(p));
    }


    @GetMapping
    public List<ProductResponseDTO> list() {
        return service.findAll().stream()
                .map(p -> this.toDTO(p))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProductResponseDTO toDTO(Product p) {
        ProductResponseDTO r = new ProductResponseDTO();
        r.id = p.getId();
        r.name = p.getName();
        r.sku = p.getSku();
        r.minStock = p.getMinStock();
        r.currentStock = p.getCurrentStock();
        r.active = p.getActive();
        r.createdAt = p.getCreatedAt().format(fmt);
        r.updatedAt = p.getUpdatedAt().format(fmt);
        return r;
    }
}