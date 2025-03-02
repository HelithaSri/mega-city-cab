package lk.cab.manager.megacitycab.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Driver {
    private String id;
    private String name;
    private String address;
    private String nic;
    private String drivingLicence;
    private String mobile;
    private String email;
    private LocalDate dob;
    private Boolean availability;
    private LocalDateTime createdAt;

    public Driver() {
    }

    public Driver(String id, String name, String address, String nic, String drivingLicence, String mobile, String email, LocalDate dob, boolean availability) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.drivingLicence = drivingLicence;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.availability = availability;
    }

    public Driver(String id, String name, String address, String nic, String drivingLicence, String mobile, String email, LocalDate dob, boolean availability, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.drivingLicence = drivingLicence;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.availability = availability;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public LocalDateTime getRegisteredDate() {
        return createdAt;
    }

    public void setRegisteredDate(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", drivingLicence='" + drivingLicence + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", availability=" + availability +
                ", createdAt=" + createdAt +
                '}';
    }
}