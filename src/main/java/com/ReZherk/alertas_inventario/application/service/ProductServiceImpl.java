package com.ReZherk.alertas_inventario.application.service;

import com.ReZherk.alertas_inventario.domain.model.Product;
import com.ReZherk.alertas_inventario.domain.repository.ProductRepository;
import com.ReZherk.alertas_inventario.domain.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


// Esta anotación le dice a Spring que esta clase es un "servicio".
// Se usa cuando la clase contiene lógica de negocio (crear, actualizar, buscar, eliminar).
// Spring la detecta automáticamente y la puede usar en otras partes del proyecto (como en controladores).
@Service

// Esta anotación activa el manejo de transacciones en la base de datos.
// Se usa cuando los métodos modifican datos (guardar, actualizar, eliminar).
// Si ocurre un error, Spring cancela la operación (rollback) para proteger la integridad de los datos.
@Transactional
public class ProductServiceImpl implements ProductService {

    // Atributo inyectado por constructor. Es final para asegurar que no se reasigne.
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product create(Product p) {
        // El metodo "ifPresent" pertenece a Optional y ejecuta el bloque solo si hay valor presente.
        repo.findBySku(p.getSku()).ifPresent(x -> {
            throw new IllegalArgumentException("Sku ya existe: " + p.getSku());
        });
        return repo.save(p);
    }

    @Override
    public Product update(Long id, Product data) {

        // orElseThrow lanza una excepción si el Optional está vacío.
        Product p = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        p.setName(data.getName());
        p.setMinStock(data.getMinStock());
        p.setCurrentStock(data.getCurrentStock());
        p.setActive(data.getActive());
        return p;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return Collections.emptyList();
    }
}
