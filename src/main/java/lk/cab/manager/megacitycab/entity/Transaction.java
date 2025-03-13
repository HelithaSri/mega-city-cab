package lk.cab.manager.megacitycab.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String diverId;
    private String customerId;
    private String vehicleId;
    private String Status;
    private String estMileage;
    private BigDecimal total;
    private BigDecimal baseRate;
    private BigDecimal additionalFee;
    private BigDecimal discountFee;
    private LocalDateTime createdAt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
