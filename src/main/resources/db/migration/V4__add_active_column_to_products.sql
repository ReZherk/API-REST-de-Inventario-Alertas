-- V4__add_active_column_to_products.sql
ALTER TABLE products
ADD COLUMN active BOOLEAN DEFAULT TRUE NOT NULL;