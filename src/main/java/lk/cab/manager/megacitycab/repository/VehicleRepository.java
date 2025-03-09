package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.CustomMapper;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleRepository {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public List<Vehicle> findAll() {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_ALL_VEHICLES);
            List<Vehicle> list = new ArrayList<>();
            while (rs.next()) {
                list.add(CustomMapper.mapResultSetToEntity(rs, Vehicle.class));
            }
            LOGGER.log(Level.INFO, () -> list.size() + " Vehicle entries found");
            return list;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findAll -> Error occurred while fetching vehicles. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return new ArrayList<>();
    }

    // (id, make, model, year, type, licensePlate, dailyRate, hourlyRate, weeklyRate, mileageLimit, extraMileageFee, status)
    public void save(Vehicle vehicle) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(
                QueryUtil.SAVE_VEHICLE,
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getType(),
                vehicle.getLicensePlate(),
                vehicle.getDailyRate(),
                vehicle.getHourlyRate(),
                vehicle.getWeeklyRate(),
                vehicle.getMileageLimit(),
                vehicle.getExtraMileageFee(),
                vehicle.getStatus()
        );
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to saved vehicle");
            return;
        }
        LOGGER.log(Level.INFO, () -> "Vehicle successfully saved!");
    }

    public void update(Vehicle vehicle) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(
                QueryUtil.UPDATE_VEHICLE,
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getType(),
                vehicle.getLicensePlate(),
                vehicle.getDailyRate(),
                vehicle.getHourlyRate(),
                vehicle.getWeeklyRate(),
                vehicle.getMileageLimit(),
                vehicle.getExtraMileageFee(),
                vehicle.getStatus(),
                vehicle.getId()
        );
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to update vehicle");
            return;
        }
        LOGGER.log(Level.INFO, () -> "Vehicle successfully updated!");
    }

    public boolean delete(String id) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.REMOVE_VEHICLE_BY_ID, id);
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to delete vehicle");
            return false;
        }
        LOGGER.log(Level.INFO, () -> "Vehicle successfully delete!");
        return true;
    }

    public Vehicle findById(String id) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_VEHICLE_BY_ID, id);
            if (rs.next()) {
                return CustomMapper.mapResultSetToEntity(rs, Vehicle.class);
            }
            LOGGER.log(Level.INFO, () -> "No Vehicle found for this id:" + id);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findById -> Error occurred while fetching vehicle by ID. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }
}
