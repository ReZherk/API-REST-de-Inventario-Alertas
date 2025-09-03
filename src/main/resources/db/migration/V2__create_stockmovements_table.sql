CREATE TABLE IF NOT EXISTS stock_movements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    type VARCHAR(16) NOT NULL,
    quantity INT NOT NULL,
    note TEXT,
    created_at DATETIME NOT NULL,

    CONSTRAINT fk_stock_movements_products FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
