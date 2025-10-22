package com.ReZherk.alertas_inventario.domain.model;


import com.ReZherk.alertas_inventario.domain.model.enums.AlertType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Agregar nombre explícito de la columna FK
    @ManyToOne(
            optional = false,                  // Toda alert DEBE tener un producto (no null)
            fetch = FetchType.LAZY             // Carga el producto solo cuando se accede (optimización)
    )
    @JoinColumn(
            name = "product_id",               // Nombre de la columna FK en tabla alerts
            nullable = false                   // La columna product_id no puede ser null
    )
    private Product product;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AlertType type;

    @Column(nullable = false, length = 200)
    private String message;


    @Column(nullable = false)
    private Boolean acknowledged = false;


    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
