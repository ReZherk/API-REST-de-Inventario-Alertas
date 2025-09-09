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
        // EntityNotFoundException indica que no se encontró la entidad solicitada.
        Product p = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        p.setName(data.getName());
        p.setMinStock(data.getMinStock());
        p.setCurrentStock(data.getCurrentStock());
        p.setActive(data.getActive());
        return p;

        // Aunque no se llama a save(), el objeto 'p' está gestionado por JPA.
        // Al modificar sus atributos, JPA detecta los cambios (dirty checking).
        // Al finalizar la transacción, se genera automáticamente un UPDATE en la base de datos.
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findBySku(String sku) {
        return repo.findBySku(sku);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repo.findAll();
    }
}


// Metodo derivado de nombre: Spring genera la consulta automáticamente.
// 'findBy' indica que es una búsqueda.
// El nombre del campo debe coincidir con el atributo de la entidad.
// Operadores como 'GreaterThan', 'Containing', 'Between' permiten condiciones.
// 'And', 'Or' combinan múltiples criterios.
