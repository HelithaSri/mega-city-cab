package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.CustomMapper;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.ResultSet;
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

}
