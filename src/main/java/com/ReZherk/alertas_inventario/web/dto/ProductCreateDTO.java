package com.ReZherk.alertas_inventario.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductCreateDTO {

    @NotBlank(message = "No puede estar vacio el campo")
    @Size(max = 120)
    public String name;

    @NotBlank(message = "No puede estar vacio el codigo del producto")
    @Size(max = 60)
    public String sku;

    @NotNull(message = "No puede estar vacio el minStock")
    @Min(0)
    public Integer minStock;

    @NotNull
    @Min(0)
    public Integer currentStock;
}

/*
 * Validaciones automáticas con Bean Validation:
 * - @NotNull: el campo no puede ser null (puede estar vacío)
 * - @NotBlank: el campo no puede ser null, vacío ni solo espacios (solo para texto)
 * Nota:A estos de arriba se puede poner un atributo dentro.message="algo aqui"

 * - @Size(min, max): define el tamaño permitido para cadenas o colecciones
 * - @Min(value): valor numérico mínimo permitido
 *
 * Se usan en entidades y DTOs para asegurar integridad de datos.
 * Se activan con @Valid en controladores.
 */

