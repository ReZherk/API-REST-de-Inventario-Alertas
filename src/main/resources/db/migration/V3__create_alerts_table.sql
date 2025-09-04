CREATE TABLE IF NOT EXISTS alerts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL,
    message VARCHAR(200) NOT NULL,
    acknowledged BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_alerts_product
            FOREIGN KEY (product_id)
            REFERENCES products(id)
);
