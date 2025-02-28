package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.CustomMapper;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                return CustomMapper.mapResultSetToEntity(rs, Customer.class);
            }
            LOGGER.log(Level.INFO, () -> "No Customer found for this username:" + username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findByUsername -> Error occurred while fetching customer by username. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

    public Customer findById(String id) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_CUSTOMER_BY_ID, id);
            if (rs.next()) {
                return CustomMapper.mapResultSetToEntity(rs, Customer.class);
            }
            LOGGER.log(Level.INFO, () -> "No Customer found for this id:" + id);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findByUsername -> Error occurred while fetching customer by ID. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

    public List<Customer> findAll() {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_ALL_CUSTOMERS);
            List<Customer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(CustomMapper.mapResultSetToEntity(rs, Customer.class));
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

    public boolean save(Customer customer) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.SAVE_CUSTOMER, customer.getId(), customer.getName(), customer.getAddress(), customer.getNic(), customer.getMobile(), customer.getEmail(), customer.getDob());
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to saved customer");
            return false;
        }
        LOGGER.log(Level.INFO, () -> "Customer successfully saved!");
        return true;
    }

    public boolean delete(String id) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.REMOVE_CUSTOMER_BY_ID, id);
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to delete customer");
            return false;
        }
        LOGGER.log(Level.INFO, () -> "Customer successfully delete!");
        return true;
    }

    public void update(Customer customer) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.UPDATE_CUSTOMER, customer.getName(), customer.getAddress(), customer.getNic(), customer.getMobile(), customer.getEmail(), customer.getDob(), customer.getId());
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to update customer");
            return;
        }
        LOGGER.log(Level.INFO, () -> "Customer successfully updated!");
    }
}
