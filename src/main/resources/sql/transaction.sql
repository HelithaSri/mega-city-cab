CREATE TABLE Transaction
(
    id            VARCHAR(36) PRIMARY KEY,
    driverId      VARCHAR(36)    NOT NULL,
    customerId    VARCHAR(36)    NOT NULL,
    vehicleId     VARCHAR(36)    NOT NULL,
    status        VARCHAR(50) DEFAULT 'Ongoing',
    estMileage    DECIMAL(10, 2),
    rentalType    VARCHAR(150),
    total         DECIMAL(10, 2) NOT NULL,
    additionalFee DECIMAL(10, 2),
    discountFee   DECIMAL(10, 2),
    startDate     TIMESTAMP      NOT NULL,
    endDate       TIMESTAMP      NOT NULL,
    customerName  VARCHAR(36)    NOT NULL,
    driverName    VARCHAR(36)    NOT NULL,
    vehiclePlate  VARCHAR(36)    NOT NULL,
    createdAt     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);