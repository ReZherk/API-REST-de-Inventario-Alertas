package com.ReZherk.alertas_inventario.web.dto;

import com.ReZherk.alertas_inventario.domain.model.enums.MovementType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MovementRequestDTO {

    @NotNull(message = "El id del producto no puede esta vacio")
    public Long productId;

    @NotNull(message = "No puede esta vacio el tipo")
    public MovementType type;

    @NotNull(message = "Llenar la cantidad")
    @Min(1)
    public Integer quantity;

    public String note;
}
