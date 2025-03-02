CREATE TABLE drivers
(
    id             VARCHAR(50) PRIMARY KEY,
    name           VARCHAR(100)       NOT NULL,
    address        TEXT               NOT NULL,
    nic            VARCHAR(20) UNIQUE NOT NULL,
    drivingLicence VARCHAR(50) UNIQUE NOT NULL,
    mobile         VARCHAR(15)        NOT NULL,
    email          VARCHAR(100)       NOT NULL,
    dob            DATE               NOT NULL,
    availability   BOOLEAN   DEFAULT TRUE,
    createdAt      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);