package lk.cab.manager.megacitycab.util;

public class QueryUtil {
    // ADMIN
    public static final String FIND_ADMIN_BY_USERNAME = "SELECT * FROM admin WHERE username = ?";

    // CUSTOMER
    public static final String FIND_ALL = "SELECT * FROM customers";
    public static final String FIND_CUSTOMER_BY_USERNAME = "SELECT * FROM customers WHERE username = ?";
}
