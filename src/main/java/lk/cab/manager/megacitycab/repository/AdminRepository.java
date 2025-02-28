package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Admin;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.QueryUtil;
import lk.cab.manager.megacitycab.util.ResultSetMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminRepository {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Admin findByUsername(String username) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_ADMIN_BY_USERNAME, username);
            if (rs.next()) {
                return ResultSetMapper.mapResultSetToEntity(rs, Admin.class);
            }
            LOGGER.log(Level.INFO, () -> "No Customer found for this username:" + username);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "findByUsername -> Error occurred while fetching admin by username. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

}
