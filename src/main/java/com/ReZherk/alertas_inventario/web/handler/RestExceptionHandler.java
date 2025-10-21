package com.ReZherk.alertas_inventario.web.handler;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {


    /**
     * Maneja excepciones cuando una entidad NO se encuentra en la base de datos.
     * CUÁNDO SE LANZA: Típicamente en operaciones findById(), cuando buscas un
     * registro que no existe (ej: usuario con ID 999 que no está en la BD).
     * BUENA PRÁCTICA: Usar mensajes descriptivos que incluyan el ID o dato buscado.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> notFound(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Maneja excepciones de ARGUMENTOS INVÁLIDOS en la lógica de negocio.
     * CUÁNDO SE LANZA: Cuando los datos no cumplen reglas de negocio específicas
     * que tú defines manualmente en tu código (no validaciones de Bean Validation).
     * DIFERENCIA CON MethodArgumentNotValidException:
     * - IllegalArgumentException: Validaciones MANUALES en tu código Java
     * - MethodArgumentNotValidException: Validaciones AUTOMÁTICAS con anotaciones (@NotNull, @Size, etc.)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> badRequest(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }


    /**
     * Maneja errores de VALIDACIÓN con Bean Validation (anotaciones @Valid/@Validated).
     * CUÁNDO SE LANZA: Automáticamente cuando un @RequestBody con @Valid falla
     * las validaciones declaradas con anotaciones como:
     * - @NotNull, @NotEmpty, @NotBlank
     * - @Size(min=3, max=50)
     * - @Email, @Min, @Max, @Pattern, etc.
     * NOTA IMPORTANTE:
     * - getBindingResult(): Obtiene todos los errores de validación
     * - getAllErrors(): Lista de todos los errores encontrados
     * - FieldError: Tipo específico que contiene el nombre del campo y el mensaje
     * - Se castea a (FieldError) porque getAllErrors() devuelve ObjectError (tipo genérico)
     * VENTAJA: El cliente recibe un mapa claro de qué campos tienen errores
     * y qué mensaje específico para cada uno.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();

        // Itera sobre TODOS los errores de validación encontrados
        ex.getBindingResult().getAllErrors().forEach(err -> {
            // Obtiene el nombre del campo que falló (ej: "email", "nombre")
            String field = ((FieldError) err).getField();

            // Obtiene el mensaje de error definido en la anotación
            // (ej: "no debe estar vacío" para @NotBlank)
            String msg = err.getDefaultMessage();

            // Agrega al mapa: campo → mensaje
            errors.put(field, msg);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}