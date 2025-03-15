package lk.cab.manager.megacitycab.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String driverId;
    private String customerId;
    private String vehicleId;
    private String Status;
    private BigDecimal estMileage;
    private String rentalType;
    private BigDecimal total;
    private BigDecimal additionalFee;
    private BigDecimal discountFee;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private String driverName;
    private String customerName;
    private String vehiclePlate;

    public Transaction() {
    }

    public Transaction(String id, String driverId, String customerId, String vehicleId, String status, BigDecimal estMileage, String rentalType, BigDecimal total, BigDecimal additionalFee, BigDecimal discountFee, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt) {
        this.id = id;
        this.driverId = driverId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        Status = status;
        this.estMileage = estMileage;
        this.rentalType = rentalType;
        this.total = total;
        this.additionalFee = additionalFee;
        this.discountFee = discountFee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }

    public Transaction(String id, String driverId, String customerId, String vehicleId, BigDecimal estMileage, String rentalType, BigDecimal additionalFee, BigDecimal discountFee, LocalDateTime startDate, LocalDateTime endDate, BigDecimal total) {
        this.id = id;
        this.driverId = driverId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.estMileage = estMileage;
        this.rentalType = rentalType;
        this.additionalFee = additionalFee;
        this.discountFee = discountFee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.total = total;
    }

    public Transaction(String id, String driverId, String customerId, String vehicleId, BigDecimal estMileage, String rentalType, BigDecimal additionalFee, BigDecimal discountFee, LocalDateTime startDate, LocalDateTime endDate, BigDecimal total, String driverName, String customerName, String vehiclePlate) {
        this.id = id;
        this.driverId = driverId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.estMileage = estMileage;
        this.rentalType = rentalType;
        this.total = total;
        this.additionalFee = additionalFee;
        this.discountFee = discountFee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.driverName = driverName;
        this.customerName = customerName;
        this.vehiclePlate = vehiclePlate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public BigDecimal getEstMileage() {
        return estMileage;
    }

    public void setEstMileage(BigDecimal estMileage) {
        this.estMileage = estMileage;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(BigDecimal additionalFee) {
        this.additionalFee = additionalFee;
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehicleName() {
        return vehiclePlate;
    }

    public void setVehicleName(String vehicleName) {
        this.vehiclePlate = vehicleName;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", driverId='" + driverId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", Status='" + Status + '\'' +
                ", estMileage=" + estMileage +
                ", rentalType='" + rentalType + '\'' +
                ", total=" + total +
                ", additionalFee=" + additionalFee +
                ", discountFee=" + discountFee +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdAt=" + createdAt +
                ", driverName='" + driverName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", vehiclePlate='" + vehiclePlate + '\'' +
                '}';
    }
}
