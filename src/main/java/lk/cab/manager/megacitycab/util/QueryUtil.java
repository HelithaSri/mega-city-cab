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
    public static final String FIND_LAST_VEHICLE_ID = "SELECT id FROM vehicles ORDER BY id DESC LIMIT 1";
    public static final String SAVE_VEHICLE = "INSERT INTO vehicles (id, make, model, year, type, licensePlate, dailyRate, hourlyRate, weeklyRate, mileageLimit, extraMileageFee, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_VEHICLE = "UPDATE vehicles SET make = ?, model = ?, year = ?, type = ?, licensePlate = ?, dailyRate = ?, hourlyRate = ?, weeklyRate = ?, mileageLimit = ?, extraMileageFee = ?, status = ? WHERE id = ?";
    public static final String REMOVE_VEHICLE_BY_ID = "DELETE FROM vehicles WHERE id = ?";
    public static final String FIND_VEHICLE_BY_ID = "SELECT * FROM vehicles WHERE id = ?";
    public static final String FIND_ALL_VEHICLES_BY_STATUS = "SELECT * FROM vehicles where status=?";

    // BOOKING
    public static final String FIND_ALL_BOOKINGS = "SELECT * FROM Transaction";
    public static final String FIND_BOOKING_BY_ID = "SELECT * FROM Transaction WHERE id = ?";
    public static final String FIND_LAST_BOOKING_ID = "SELECT id FROM Transaction ORDER BY id DESC LIMIT 1";
    public static final String FIND_TODAY_BOOKING_COUNT = "SELECT COUNT(*) AS today_count FROM Transaction WHERE DATE(createdAt) = CURRENT_DATE;";
    public static final String SAVE_BOOKING = "INSERT INTO Transaction (id, driverId, customerId, vehicleId, estMileage, rentalType, total, additionalFee, discountFee, startDate, endDate, customerName, driverName, vehiclePlate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_BOOKING = "UPDATE Transaction SET status=?  WHERE id = ?";


}