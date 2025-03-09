package lk.cab.manager.megacitycab.util;

public class QueryUtil {
    // ADMIN
    public static final String FIND_ADMIN_BY_USERNAME = "SELECT * FROM admin WHERE username = ?";

    // CUSTOMER
    public static final String FIND_ALL_CUSTOMERS = "SELECT * FROM customers";
    public static final String REMOVE_CUSTOMER_BY_ID = "DELETE FROM customers WHERE id = ?";
    public static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    public static final String FIND_CUSTOMER_BY_USERNAME = "SELECT * FROM customers WHERE name = ?";
    public static final String FIND_LAST_CUSTOMER_ID = "SELECT id FROM customers ORDER BY id DESC LIMIT 1";
    public static final String SAVE_CUSTOMER = "INSERT INTO customers (id, name, address, nic, mobile, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_CUSTOMER = "UPDATE customers SET name = ?, address = ?, nic = ?, mobile = ?, email = ?, dob = ?  WHERE id = ?";

    // DRIVER
    public static final String FIND_ALL_DRIVERS = "SELECT * FROM drivers";
    public static final String FIND_ALL_DRIVERS_BY_STATUS = "SELECT * FROM drivers where availability=?";
    public static final String REMOVE_DRIVER_BY_ID = "DELETE FROM drivers WHERE id = ?";
    public static final String FIND_DRIVER_BY_ID = "SELECT * FROM drivers WHERE id = ?";
    public static final String FIND_DRIVER_BY_USERNAME = "SELECT * FROM drivers WHERE name = ?";
    public static final String FIND_LAST_DRIVER_ID = "SELECT id FROM drivers ORDER BY id DESC LIMIT 1";
    public static final String SAVE_DRIVER = "INSERT INTO drivers (id, name, address, nic, drivingLicence, mobile, email, dob, availability) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_DRIVER = "UPDATE drivers SET name = ?, address = ?, nic = ?, drivingLicence = ?, mobile = ?, email = ?, dob = ?, availability = ?  WHERE id = ?";

    // VEHICLE
    public static final String FIND_ALL_VEHICLES = "SELECT * FROM vehicles";

}