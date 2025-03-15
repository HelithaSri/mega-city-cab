package lk.cab.manager.megacitycab.model;

import java.math.BigDecimal;

public class BookingRequest {
    private String driverId;
    private String customerId;
    private String vehicleId;
    private String rentalType;

    private BigDecimal estimatedMileage;
    private BigDecimal calculatedPrice;
    private BigDecimal additionalFees;
    private BigDecimal discount;

    private String startDateTime;
    private String endDateTime;

    public BookingRequest() {
    }

    public BookingRequest(String driverId, String customerId, String vehicleId, BigDecimal estimatedMileage, String rentalType, BigDecimal calculatedPrice, BigDecimal additionalFees, BigDecimal discount, String startDateTime, String endDateTime) {
        this.driverId = driverId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.estimatedMileage = estimatedMileage;
        this.rentalType = rentalType;
        this.calculatedPrice = calculatedPrice;
        this.additionalFees = additionalFees;
        this.discount = discount;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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

    public BigDecimal getEstimatedMileage() {
        return estimatedMileage;
    }

    public void setEstimatedMileage(BigDecimal estimatedMileage) {
        this.estimatedMileage = estimatedMileage;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public BigDecimal getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(BigDecimal calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public BigDecimal getAdditionalFees() {
        return additionalFees;
    }

    public void setAdditionalFees(BigDecimal additionalFees) {
        this.additionalFees = additionalFees;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "driverId='" + driverId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", estimatedMileage='" + estimatedMileage + '\'' +
                ", rentalType='" + rentalType + '\'' +
                ", calculatedPrice=" + calculatedPrice +
                ", additionalFees=" + additionalFees +
                ", discount=" + discount +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                '}';
    }
}