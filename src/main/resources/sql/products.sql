CREATE
DATABASE IF NOT EXISTS products_db;
USE
products_db;

CREATE TABLE IF NOT EXISTS products
(
    id
    bigint
    NOT
    NULL,
    title
    varchar
(
    128
),
    price int,
    PRIMARY KEY
(
    id
)
    );