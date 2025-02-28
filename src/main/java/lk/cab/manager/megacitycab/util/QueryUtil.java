package lk.cab.manager.megacitycab.util;

public class QueryUtil {
    // ADMIN
    public static final String FIND_ADMIN_BY_USERNAME = "SELECT * FROM admin WHERE username = ?";

    // CUSTOMER
    public static final String FIND_ALL_CUSTOMERS = "SELECT * FROM customers";
    public static final String REMOVE_CUSTOMER_BY_ID = "DELETE FROM customers WHERE id = ?";
    public static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    public static final String FIND_CUSTOMER_BY_USERNAME = "SELECT * FROM customers WHERE username = ?";
    public static final String FIND_LAST_CUSTOMER_ID = "SELECT id FROM customers ORDER BY id DESC LIMIT 1";
    public static final String SAVE_CUSTOMER = "INSERT INTO customers (id, name, address, nic, mobile, email, dob) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_CUSTOMER = "UPDATE customers SET name = ?, address = ?, nic = ?, mobile = ?, email = ?, dob = ?  WHERE id = ?";
}