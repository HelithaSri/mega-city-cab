package lk.cab.manager.megacitycab.service;

import lk.cab.manager.megacitycab.entity.Driver;
import lk.cab.manager.megacitycab.model.DriverDto;
import lk.cab.manager.megacitycab.repository.DriverRepository;
import lk.cab.manager.megacitycab.util.IdGenerator;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverService {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    DriverRepository driverRepository = new DriverRepository();


    public void addDriver(DriverDto dto) throws SQLException {
        String lastId = IdGenerator.generateNextId(QueryUtil.FIND_LAST_DRIVER_ID, "d");
        Driver driver = new Driver(
                lastId,
                dto.getName(),
                dto.getAddress(),
                dto.getNic(),
                dto.getDrivingLicense(),
                dto.getMobile(),
                dto.getEmail(),
                LocalDate.parse(dto.getDob()),
                dto.isAvailability(),
                LocalDate.now()
        );
        driverRepository.save(driver);
    }

    public void updateDriver(DriverDto dto) throws SQLException {
        Driver existingDriver = driverRepository.findById(dto.getId());
        if (existingDriver == null) {
            LOGGER.log(Level.WARNING, () -> "Driver not found by this ID: " + dto.getId());
            return;
        }
        Driver driver = new Driver(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getNic(),
                dto.getDrivingLicense(),
                dto.getMobile(),
                dto.getEmail(),
                LocalDate.parse(dto.getDob()),
                dto.isAvailability()
        );
        driverRepository.update(driver);
    }

    public void deleteDriver(String customerId) throws SQLException {
        Driver driver = driverRepository.findById(customerId);
        if (driver == null) {
            LOGGER.log(Level.WARNING, () -> "Driver not found by this ID: " + customerId);
            return;
        }
        driverRepository.delete(driver.getId());
    }

    public List<DriverDto> getAllDrivers() {
        List<DriverDto> drivers = new ArrayList<>();
        List<Driver> driverList = driverRepository.findAll();

        LOGGER.log(Level.INFO, () -> driverList.size() + " driverList entries found");

        driverList.forEach(item -> drivers.add(
                new DriverDto(
                        item.getId(),
                        item.getName(),
                        item.getAddress(),
                        item.getNic(),
                        item.getDrivingLicence(),
                        item.getMobile(),
                        item.getEmail(),
                        item.getDob().toString(),
                        item.isAvailability()
                )));

        LOGGER.log(Level.INFO, () -> drivers.size() + " drivers entries found");

        return drivers;
    }
}
