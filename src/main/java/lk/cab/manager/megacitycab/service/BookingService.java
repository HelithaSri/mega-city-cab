package lk.cab.manager.megacitycab.service;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.entity.Driver;
import lk.cab.manager.megacitycab.entity.Transaction;
import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.enums.BookingStatus;
import lk.cab.manager.megacitycab.enums.VehicleStatus;
import lk.cab.manager.megacitycab.model.BookingRequest;
import lk.cab.manager.megacitycab.repository.CustomerRepository;
import lk.cab.manager.megacitycab.repository.DriverRepository;
import lk.cab.manager.megacitycab.repository.TransactionRepository;
import lk.cab.manager.megacitycab.repository.VehicleRepository;
import lk.cab.manager.megacitycab.util.IdGenerator;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingService {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    TransactionRepository transactionRepository = new TransactionRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    DriverRepository driverRepository = new DriverRepository();
    VehicleRepository vehicleRepository = new VehicleRepository();

    public boolean doBooking(BookingRequest booking) {
        try {
            LOGGER.log(Level.INFO, () -> "Trying to do booking with these values: " + booking.toString());

            Customer customer = customerRepository.findById(booking.getCustomerId());
            if (customer == null) {
                LOGGER.log(Level.SEVERE, () -> "No customer found for id:" + booking.getCustomerId());
                return false;
            }

            Driver driver = driverRepository.findById(booking.getDriverId());
            if (driver == null) {
                LOGGER.log(Level.SEVERE, () -> "No driver found for id:" + booking.getDriverId());
                return false;
            }

            Vehicle vehicle = vehicleRepository.findById(booking.getVehicleId());
            if (vehicle == null) {
                LOGGER.log(Level.SEVERE, () -> "No vehicle found for id:" + booking.getVehicleId());
                return false;
            }

            vehicle.setStatus(VehicleStatus.RENTED.name());
            driver.setAvailability(false);
            vehicleRepository.update(vehicle);
            driverRepository.update(driver);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startDate = LocalDateTime.parse(booking.getStartDateTime(), formatter);
            LocalDateTime endDate = LocalDateTime.parse(booking.getEndDateTime(), formatter);

            String lastId = IdGenerator.generateNextId(QueryUtil.FIND_LAST_BOOKING_ID, "B");
            Transaction transaction = new Transaction(
                    lastId,
                    booking.getDriverId(),
                    booking.getCustomerId(),
                    booking.getVehicleId(),
                    booking.getEstimatedMileage(),
                    booking.getRentalType(),
                    booking.getAdditionalFees(),
                    booking.getDiscount(),
                    startDate,
                    endDate,
                    booking.getCalculatedPrice(),
                    driver.getName(),
                    customer.getName(),
                    vehicle.getLicensePlate()
            );
            boolean isSaved = transactionRepository.save(transaction);
            LOGGER.log(Level.INFO, () -> "Transaction is " + (isSaved ? "Success" : "Failed"));
            return isSaved;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error occurred while creating booking. error: " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean cancelBooking(String bookingId) {
        try {
            LOGGER.log(Level.INFO, () -> "Trying to cancel booking id: " + bookingId);

            Transaction booking = transactionRepository.findById(bookingId);
            if (booking == null) {
                LOGGER.log(Level.SEVERE, () -> "No booking found for id:" + bookingId);
                return false;
            }

            LOGGER.log(Level.INFO, () -> "Booking Details: " + booking);


            Driver driver = driverRepository.findById(booking.getDriverId());
            if (driver == null) {
                LOGGER.log(Level.SEVERE, () -> "No driver found for id:" + booking.getDriverId());
                return false;
            }

            Vehicle vehicle = vehicleRepository.findById(booking.getVehicleId());
            if (vehicle == null) {
                LOGGER.log(Level.SEVERE, () -> "No vehicle found for id:" + booking.getVehicleId());
                return false;
            }

            vehicle.setStatus(VehicleStatus.AVAILABLE.name());
            driver.setAvailability(true);
            booking.setStatus(BookingStatus.CANCELED.name());

            vehicleRepository.update(vehicle);
            driverRepository.update(driver);
            transactionRepository.update(booking);

            LOGGER.log(Level.INFO, () -> "Booking mark as a canceled");
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error occurred while canceling booking. error: " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean completeBooking(String bookingId) {
        try {
            LOGGER.log(Level.INFO, () -> "Trying to complete booking id: " + bookingId);

            Transaction booking = transactionRepository.findById(bookingId);
            if (booking == null) {
                LOGGER.log(Level.SEVERE, () -> "No booking found for id:" + bookingId);
                return false;
            }

            Driver driver = driverRepository.findById(booking.getDriverId());
            if (driver == null) {
                LOGGER.log(Level.SEVERE, () -> "No driver found for id:" + booking.getDriverId());
                return false;
            }

            Vehicle vehicle = vehicleRepository.findById(booking.getVehicleId());
            if (vehicle == null) {
                LOGGER.log(Level.SEVERE, () -> "No vehicle found for id:" + booking.getVehicleId());
                return false;
            }

            vehicle.setStatus(VehicleStatus.AVAILABLE.name());
            driver.setAvailability(true);
            booking.setStatus(BookingStatus.COMPLETED.name());

            vehicleRepository.update(vehicle);
            driverRepository.update(driver);
            transactionRepository.update(booking);

            LOGGER.log(Level.INFO, () -> "Booking mark as a complete");
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error occurred while complete booking. error: " + e.getLocalizedMessage());
            return false;
        }
    }

}
