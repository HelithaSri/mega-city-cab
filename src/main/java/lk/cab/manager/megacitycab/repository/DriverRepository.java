package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Driver;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.CustomMapper;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverRepository {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Driver findById(String id) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_DRIVER_BY_ID, id);
            if (rs.next()) {
                return CustomMapper.mapResultSetToEntity(rs, Driver.class);
            }
            LOGGER.log(Level.INFO, () -> "No Driver found for this id:" + id);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findByUsername -> Error occurred while fetching driver by ID. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

    public List<Driver> findAll() {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_ALL_DRIVERS);
            List<Driver> list = new ArrayList<>();
            while (rs.next()) {
                list.add(CustomMapper.mapResultSetToEntity(rs, Driver.class));
            }
            LOGGER.log(Level.INFO, () -> list.size() + " Driver entries found");
            return list;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findAll -> Error occurred while fetching drivers. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Driver> findAllByStatus(boolean status) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_ALL_DRIVERS_BY_STATUS, status);
            List<Driver> list = new ArrayList<>();
            while (rs.next()) {
                list.add(CustomMapper.mapResultSetToEntity(rs, Driver.class));
            }
            LOGGER.log(Level.INFO, () -> list.size() + " Driver entries found");
            return list;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findAllByStatus -> Error occurred while fetching driver by status. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return new ArrayList<>();
    }

    public void save(Driver driver) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.SAVE_DRIVER, driver.getId(), driver.getName(), driver.getAddress(), driver.getNic(), driver.getDrivingLicence(), driver.getMobile(), driver.getEmail(), driver.getDob(), driver.isAvailability());
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to saved driver");
            return;
        }
        LOGGER.log(Level.INFO, () -> "Driver successfully saved!");
    }

    public boolean delete(String id) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.REMOVE_DRIVER_BY_ID, id);
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to delete driver");
            return false;
        }
        LOGGER.log(Level.INFO, () -> "Driver successfully delete!");
        return true;
    }

    public void update(Driver driver) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.UPDATE_DRIVER, driver.getName(), driver.getAddress(), driver.getNic(), driver.getDrivingLicence(), driver.getMobile(), driver.getEmail(), driver.getDob(), driver.isAvailability(), driver.getId());
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to update driver");
            return;
        }
        LOGGER.log(Level.INFO, () -> "Driver successfully updated!");
    }
}
