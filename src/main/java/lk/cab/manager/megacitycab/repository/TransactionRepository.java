package lk.cab.manager.megacitycab.repository;

import lk.cab.manager.megacitycab.entity.Transaction;
import lk.cab.manager.megacitycab.util.CrudUtil;
import lk.cab.manager.megacitycab.util.CustomMapper;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionRepository {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public boolean save(Transaction transaction) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(
                QueryUtil.SAVE_BOOKING,
                transaction.getId(),
                transaction.getDriverId(),
                transaction.getCustomerId(),
                transaction.getVehicleId(),
                transaction.getEstMileage(),
                transaction.getRentalType(),
                transaction.getTotal(),
                transaction.getAdditionalFee(),
                transaction.getDiscountFee(),
                transaction.getStartDate(),
                transaction.getEndDate(),
                transaction.getCustomerName(),
                transaction.getDriverName(),
                transaction.getVehicleName()

        );
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to save booking");
            return false;
        }
        LOGGER.log(Level.INFO, () -> "Booking successfully saved!");
        return true;
    }

    public int todayBookingCount() {
        try {
            int count = 0;
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_TODAY_BOOKING_COUNT);
            while (rs.next()) {
                count = rs.getInt("today_count");
            }
            int finalCount = count;
            LOGGER.log(Level.INFO, () -> " Today's booking count: " + finalCount);
            return finalCount;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findAllByStatus -> Error occurred while fetching Today's booking count. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
            return 0;
        }
    }

    public List<Transaction> findAll() {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_ALL_BOOKINGS);
            List<Transaction> list = new ArrayList<>();
            while (rs.next()) {
                list.add(CustomMapper.mapResultSetToEntity(rs, Transaction.class));
            }
            LOGGER.log(Level.INFO, () -> list.size() + " Booking entries found");
            return list;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findAll -> Error occurred while fetching booking. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return new ArrayList<>();
    }

    public Transaction findById(String id) {
        try {
            ResultSet rs = CrudUtil.executeQuery(QueryUtil.FIND_BOOKING_BY_ID, id);
            if (rs.next()) {
                return CustomMapper.mapResultSetToEntity(rs, Transaction.class);
            }
            LOGGER.log(Level.INFO, () -> "No Bookings found for this id:" + id);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            LOGGER.log(Level.SEVERE, e, () -> "findById -> Error occurred while fetching booking by ID. exception:" + e.getLocalizedMessage());
            e.getStackTrace();
        }
        return null;
    }

    public void update(Transaction transaction) throws SQLException {
        boolean executed = CrudUtil.executeUpdate(QueryUtil.UPDATE_BOOKING, transaction.getStatus(), transaction.getId());
        if (!executed) {
            LOGGER.log(Level.WARNING, () -> "Failed to update Booking");
            return;
        }
        LOGGER.log(Level.INFO, () -> "Booking successfully updated!");
    }
}
