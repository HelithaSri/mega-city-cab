package lk.cab.manager.megacitycab.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
    private String id;
    private String name;
    private String address;
    private String nic;
    private String mobile;
    private String email;
    private LocalDate dob;
    private LocalDateTime createdAt;

    public Customer() {
    }

    public Customer(String id, String name, String address, String nic, String mobile, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
    }

    public Customer(String id, String name, String address, String nic, String mobile, String email, LocalDate dob, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}