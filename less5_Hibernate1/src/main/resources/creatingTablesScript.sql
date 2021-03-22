BEGIN;

DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE products (
  id bigserial PRIMARY KEY,
  title VARCHAR(128),
  price INTEGER
);

INSERT INTO products (title, price) VALUES
('TV', 999),
('Xbox', 299),
('PlayStation', 399);

COMMIT;