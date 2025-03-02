CREATE TABLE customers
(
    id        VARCHAR(50) PRIMARY KEY,
    name      VARCHAR(100)       NOT NULL,
    address   VARCHAR(255)       NOT NULL,
    nic       VARCHAR(20) UNIQUE NOT NULL,
    mobile    VARCHAR(15)        NOT NULL,
    email     VARCHAR(100)       NOT NULL,
    dob       DATE               NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
