package lk.cab.manager.megacitycab.model;

public class DashboardResponse {
    private String totalCustomers;
    private String availableDrivers;
    private String availableVehicles;
    private String todayBookings;

    public DashboardResponse() {
    }

    public DashboardResponse(String totalCustomers, String availableDrivers, String availableVehicles, String todayBookings) {
        this.totalCustomers = totalCustomers;
        this.availableDrivers = availableDrivers;
        this.availableVehicles = availableVehicles;
        this.todayBookings = todayBookings;
    }

    public String getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(String totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public String getAvailableDrivers() {
        return availableDrivers;
    }

    public void setAvailableDrivers(String availableDrivers) {
        this.availableDrivers = availableDrivers;
    }

    public String getAvailableVehicles() {
        return availableVehicles;
    }

    public void setAvailableVehicles(String availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

    public String getTodayBookings() {
        return todayBookings;
    }

    public void setTodayBookings(String todayBookings) {
        this.todayBookings = todayBookings;
    }
}
