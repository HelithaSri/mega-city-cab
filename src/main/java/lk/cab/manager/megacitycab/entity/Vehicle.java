package lk.cab.manager.megacitycab.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Vehicle {
    private String id;
    private String make;
    private String model;
    private int year;
    private String type;
    private String licensePlate;
    private BigDecimal dailyRate;
    private BigDecimal hourlyRate;
    private BigDecimal weeklyRate;
    private BigDecimal mileageLimit;
    private BigDecimal extraMileageFee;
    private String status;
    private LocalDateTime createdAt;

    public Vehicle() {
    }

    public Vehicle(String id, String make, String model, int year, String type, String licensePlate, BigDecimal dailyRate, BigDecimal hourlyRate, BigDecimal weeklyRate, BigDecimal mileageLimit, BigDecimal extraMileageFee, String status, LocalDateTime createdAt) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.licensePlate = licensePlate;
        this.dailyRate = dailyRate;
        this.hourlyRate = hourlyRate;
        this.weeklyRate = weeklyRate;
        this.mileageLimit = mileageLimit;
        this.extraMileageFee = extraMileageFee;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Vehicle(String id, String make, String model, int year, String type, String licensePlate, BigDecimal dailyRate, BigDecimal hourlyRate, BigDecimal weeklyRate, BigDecimal mileageLimit, BigDecimal extraMileageFee) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.licensePlate = licensePlate;
        this.dailyRate = dailyRate;
        this.hourlyRate = hourlyRate;
        this.weeklyRate = weeklyRate;
        this.mileageLimit = mileageLimit;
        this.extraMileageFee = extraMileageFee;
    }

    public Vehicle(String id, String make, String model, int year, String type, String licensePlate, BigDecimal dailyRate, BigDecimal hourlyRate, BigDecimal weeklyRate, BigDecimal mileageLimit, BigDecimal extraMileageFee, String status) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.licensePlate = licensePlate;
        this.dailyRate = dailyRate;
        this.hourlyRate = hourlyRate;
        this.weeklyRate = weeklyRate;
        this.mileageLimit = mileageLimit;
        this.extraMileageFee = extraMileageFee;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getWeeklyRate() {
        return weeklyRate;
    }

    public void setWeeklyRate(BigDecimal weeklyRate) {
        this.weeklyRate = weeklyRate;
    }

    public BigDecimal getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(BigDecimal mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    public BigDecimal getExtraMileageFee() {
        return extraMileageFee;
    }

    public void setExtraMileageFee(BigDecimal extraMileageFee) {
        this.extraMileageFee = extraMileageFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
