package lk.cab.manager.megacitycab.service;

import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.model.VehicleDataDto;
import lk.cab.manager.megacitycab.model.VehicleDto;
import lk.cab.manager.megacitycab.repository.VehicleRepository;
import lk.cab.manager.megacitycab.util.IdGenerator;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleService {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    VehicleRepository vehicleRepository = new VehicleRepository();

    public VehicleDataDto getAllVehicles() {
        List<VehicleDto> vehicles = new ArrayList<>();
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        LOGGER.log(Level.INFO, () -> vehicleList.size() + " vehicleList entries found");

        vehicleList.forEach(item -> vehicles.add(
                new VehicleDto(
                        item.getId(),
                        item.getMake(),
                        item.getModel(),
                        item.getYear(),
                        item.getType(),
                        item.getLicensePlate(),
                        item.getDailyRate(),
                        item.getHourlyRate(),
                        item.getWeeklyRate(),
                        item.getMileageLimit(),
                        item.getExtraMileageFee(),
                        item.getStatus(),
                        item.getCreatedAt()
                )));

        int availableCount = (int) vehicles.stream().filter(vehicleDto -> vehicleDto.getStatus().equals("Available")).count();
        LOGGER.log(Level.INFO, () -> availableCount + "available vehicles found");
        return new VehicleDataDto(vehicles, vehicles.size(), availableCount);
    }


    public void addVehicle(VehicleDto dto) throws SQLException {
        String lastId = IdGenerator.generateNextId(QueryUtil.FIND_LAST_VEHICLE_ID, "V");
        Vehicle vehicle = new Vehicle(
                lastId,
                dto.getMake(),
                dto.getModel(),
                dto.getYear(),
                dto.getType(),
                dto.getLicensePlate(),
                dto.getDailyRate(),
                dto.getHourlyRate(),
                dto.getWeeklyRate(),
                dto.getMileageLimit(),
                dto.getExtraMileageFee(),
                dto.getStatus()
        );
        vehicleRepository.save(vehicle);
    }

    public void updateVehicle(VehicleDto dto) throws SQLException {
        Vehicle existingVehicle = vehicleRepository.findById(dto.getId());
        if (existingVehicle == null) {
            LOGGER.log(Level.WARNING, () -> "Vehicle not found by this ID: " + dto.getId());
            return;
        }
        Vehicle vehicle = new Vehicle(
                dto.getId(),
                dto.getMake(),
                dto.getModel(),
                dto.getYear(),
                dto.getType(),
                dto.getLicensePlate(),
                dto.getDailyRate(),
                dto.getHourlyRate(),
                dto.getWeeklyRate(),
                dto.getMileageLimit(),
                dto.getExtraMileageFee(),
                dto.getStatus()
        );
        vehicleRepository.update(vehicle);
    }

    public void deleteVehicle(String customerId) throws SQLException {
        Vehicle vehicle = vehicleRepository.findById(customerId);
        if (vehicle == null) {
            LOGGER.log(Level.WARNING, () -> "Vehicle not found by this ID: " + customerId);
            return;
        }
        vehicleRepository.delete(vehicle.getId());
    }

}
