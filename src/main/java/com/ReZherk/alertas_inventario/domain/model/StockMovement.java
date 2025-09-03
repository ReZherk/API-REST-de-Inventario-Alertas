package com.ReZherk.alertas_inventario.domain.model;


import com.ReZherk.alertas_inventario.domain.model.enums.MovementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stock_movements")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación @ManyToOne: esta entidad se vincula a un solo producto, pero un producto puede tener muchas instancias de esta entidad.
    // En la base de datos, esto se traduce en una columna 'product_id' que se repite en varias filas, apuntando al mismo producto.


    // Relación obligatoria y con carga diferida:
    // - 'optional = false' → no se permite null, se exige un producto válido.
    // - 'fetch = LAZY' → el producto se carga solo cuando se accede, optimizando el rendimiento.

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;


    // Enumeración persistida como texto: se usa @Enumerated(EnumType.STRING) para guardar el nombre del valor enum en la base de datos.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private MovementType type;

    @Column(nullable = false)
    private Integer quantity;

    private String note;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}

//NOTA:
// Relación @OneToMany: representa que esta entidad (por ejemplo, Product) está asociada a muchas instancias de otra entidad (por ejemplo, Alert).
// Es decir, un solo producto puede tener muchas alertas vinculadas a él.
// Esta relación no se traduce en una columna física en la tabla principal, sino que se refleja en la tabla secundaria mediante una clave foránea (product_id).
// Se usa para navegar desde el "uno" hacia los "muchos", permitiendo acceder a todas las entidades relacionadas desde el lado padre.
// Es útil para lógica de negocio y presentación, pero debe usarse con cuidado para evitar cargas innecesarias o acoplamientos excesivos.