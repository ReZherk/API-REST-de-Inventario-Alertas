package com.ReZherk.alertas_inventario.domain.service;

import com.ReZherk.alertas_inventario.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product p);

    Product update(Long id, Product data);

    void delete(Long id);

    Optional<Product> findById(Long id);

    Optional<Product> findBySku(String sku);

    List<Product> findAll();
}
