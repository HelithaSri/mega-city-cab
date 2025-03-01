package lk.cab.manager.megacitycab.model;

public class DriverDto {
    private String id;
    private String name;
    private String address;
    private String nic;
    private String drivingLicence;
    private String mobile;
    private String email;
    private String dob;
    private boolean availability;

    public DriverDto() {
    }

    public DriverDto(String id, String name, String address, String nic, String drivingLicense, String mobile, String email, String dob, boolean availability) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.drivingLicence = drivingLicense;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.availability = availability;
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

    public String getDrivingLicense() {
        return drivingLicence;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicence = drivingLicense;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = Boolean.parseBoolean(availability);
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", drivingLicense='" + drivingLicence + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", availability=" + availability +
                '}';
    }
}
