CREATE TABLE vehicles
(
    id              VARCHAR(50) PRIMARY KEY,
    make            VARCHAR(100)       NOT NULL,
    model           VARCHAR(100)       NOT NULL,
    year            INT                NOT NULL,
    type            VARCHAR(100)       NOT NULL,
    licensePlate    VARCHAR(20) UNIQUE NOT NULL,
    dailyRate       DECIMAL(10, 2)     NOT NULL,
    hourlyRate      DECIMAL(10, 2),
    weeklyRate      DECIMAL(10, 2),
    mileageLimit    DECIMAL(10, 2) DEFAULT 500,
    extraMileageFee DECIMAL(10, 2) DEFAULT 0.10,
    status          VARCHAR(100)   DEFAULT 'Available',
    createdAt       TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
);