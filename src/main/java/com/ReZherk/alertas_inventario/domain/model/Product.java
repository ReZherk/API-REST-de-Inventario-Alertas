package com.ReZherk.alertas_inventario.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Configuración de la tabla "products" en la base de datos.
 * Se define un índice único sobre la columna "sku" para mejorar el rendimiento en búsquedas
 * y garantizar que no se repitan códigos de producto.
 * El índice se llama "idx_products_sku" y se aplica directamente al esquema generado por JPA.
 */

@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_products_sku", columnList = "sku", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
public class Product {
    // Estrategias tipos:
    // - GenerationType.IDENTITY → autoincremento nativo de la base de datos (ideal para MariaDB/MySQL).
    // - GenerationType.SEQUENCE → usa una secuencia definida en la base de datos (recomendado en PostgreSQL).
    // - GenerationType.AUTO → delega a JPA la elección según el proveedor (útil en proyectos simples).
    // - GenerationType.TABLE → usa una tabla auxiliar para generar IDs (menos común, más lento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 60, unique = true)
    private String sku;

    @Column(nullable = false)
    private Integer currentStock;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;


    // @PrePersist indica que este método se ejecutará automáticamente antes de insertar la entidad en la base de datos.
    // Se usa para inicializar valores como fechas de creación o campos por defecto antes del primer persist.
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;

    }

    // @PreUpdate indica que este método se ejecutará automáticamente antes de actualizar la entidad en la base de datos.
    // Se usa para actualizar la marca de tiempo updatedAt, manteniendo trazabilidad de modificaciones
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
