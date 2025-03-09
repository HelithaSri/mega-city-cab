package lk.cab.manager.megacitycab.model;

import java.util.List;

public class VehicleDataDto {
    private List<VehicleDto> vehicleDtoList;
    private int total;
    private int available;

    public VehicleDataDto() {
    }

    public VehicleDataDto(List<VehicleDto> vehicleDtoList, int total, int available) {
        this.vehicleDtoList = vehicleDtoList;
        this.total = total;
        this.available = available;
    }

    public List<VehicleDto> getVehicleDtoList() {
        return vehicleDtoList;
    }

    public void setVehicleDtoList(List<VehicleDto> vehicleDtoList) {
        this.vehicleDtoList = vehicleDtoList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "VehicleDataDto{" +
                "vehicleDtoList=" + vehicleDtoList +
                ", total=" + total +
                ", available=" + available +
                '}';
    }
}
