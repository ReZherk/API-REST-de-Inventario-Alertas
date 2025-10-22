-- Eliminar la constraint existente
ALTER TABLE alerts
DROP FOREIGN KEY fk_alerts_product;

-- Recrear la constraint con ON DELETE CASCADE
ALTER TABLE alerts
ADD CONSTRAINT fk_alerts_product
FOREIGN KEY (product_id)
REFERENCES products(id)
ON DELETE CASCADE;