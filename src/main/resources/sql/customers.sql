USE
products_db;

CREATE TABLE IF NOT EXISTS customers
(
    id
    bigint
    NOT
    NULL,
    title
    varchar
(
    32
),
    PRIMARY KEY
(
    id
)
    );