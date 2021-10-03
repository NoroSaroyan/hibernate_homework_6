USE products_db;

CREATE TABLE IF NOT EXISTS orders
(
    id_customer bigint NOT NULL,
    id_product  bigint NOT NULL,
    PRIMARY KEY (id_customer, id_product),
    FOREIGN KEY (id_customer) REFERENCES customers (id),
    FOREIGN KEY (id_product) REFERENCES products (id)
);