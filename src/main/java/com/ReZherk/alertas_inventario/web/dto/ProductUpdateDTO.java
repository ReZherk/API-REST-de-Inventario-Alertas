package com.ReZherk.alertas_inventario.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductUpdateDTO {

    @NotBlank(message = "No puede estar en blanco")
    @Size(max = 120)
    public String name;

    @NotNull(message = "Debe tener una cantidad")
    @Min(0)
    public Integer minStock;

    @NotNull(message = "Debe estar lleno el stock actual")
    @Min(0)
    public Integer currentStock;

    public Boolean active = true;
}
