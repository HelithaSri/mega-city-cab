package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.QueryUtil;
import lk.cab.manager.megacitycab.util.ResultSetMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerRepository {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Customer findByUsername(String username) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_CUSTOMER_BY_USERNAME, username);
            if (rs.next()) {
                return ResultSetMapper.mapResultSetToEntity(rs, Customer.class);
            }
            LOGGER.log(Level.INFO, () -> "No Customer found for this username:" + username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findByUsername -> Error occurred while fetching customer by username. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

    public List<Customer> findAll() {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_ALL);
            List<Customer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(ResultSetMapper.mapResultSetToEntity(rs, Customer.class));
            }
            LOGGER.log(Level.INFO, () -> list.size() + " Customer entries found");
            return list;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findByUsername -> Error occurred while fetching customer by username. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

}
