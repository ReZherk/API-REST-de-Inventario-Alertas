-- Agregar min_stock si no existe
ALTER TABLE products
ADD COLUMN IF NOT EXISTS min_stock INT NOT NULL DEFAULT 10;