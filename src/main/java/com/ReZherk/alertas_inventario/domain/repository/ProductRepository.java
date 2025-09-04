package com.ReZherk.alertas_inventario.domain.repository;

import com.ReZherk.alertas_inventario.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}

/**
 * Repositorio de acceso a datos para la entidad Product.
 * Extiende JpaRepository<Product, Long>:
 *   - Product: entidad gestionada
 *   - Long: tipo de clave primaria
 *
 * Métodos heredados:
 *   - findAll(), findById(), save(), deleteById(), etc.
 *
 * Método personalizado:
 *   - Optional<Product> findBySku(String sku):
 *     Busca un producto por su SKU. Devuelve Optional para evitar null.
 *
 * Ejemplos_Adicionales:
 *   - findByName(String name)
 *   - findByCategory(String category)
 *   - findByPriceGreaterThan(Double price)
 *   - findByCurrentStock(Integer stock)
 */

