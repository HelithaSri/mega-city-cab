package lk.cab.manager.megacitycab.util;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultSetMapper {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Generic method to map a ResultSet to an entity of type T
    public static <T> T mapResultSetToEntity(ResultSet rs, Class<T> entityClass) throws SQLException {
        T entity = null;

        try {
            // Create a new instance of the entity class
            entity = entityClass.getDeclaredConstructor().newInstance();

            // Iterate over each field in the entity class
            for (Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true); // Make private fields accessible

                // Get the column name that matches the field name in ResultSet
                try {
                    Object value = rs.getObject(field.getName());

                    if (field.getType().equals(LocalDate.class) && value instanceof Date) {
                        // Convert java.sql.Date to java.time.LocalDate
                        value = ((Date) value).toLocalDate();
                    }

                    field.set(entity, value); // Set the value to the entity object
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.SEVERE, e, () -> "mapResultSetToEntity -> Failed to map result. exception:" + e.getLocalizedMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "mapResultSetToEntity -> Failed to map result. exception:" + e.getLocalizedMessage());
        }

        return entity;
    }

    public static <T> T mapRequestToEntity(HttpServletRequest res, Class<T> entityClass) throws SQLException {
        T entity = null;

        try {
            // Create a new instance of the entity class
            entity = entityClass.getDeclaredConstructor().newInstance();

            // Iterate over each field in the entity class
            for (Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true); // Make private fields accessible

                // Get the column name that matches the field name in HttpServletRequest
                try {
                    Object value = res.getParameter(field.getName());
                    field.set(entity, value); // Set the value to the entity object
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.SEVERE, e, () -> "mapRequestToEntity -> Failed to map HttpServletRequest. exception:" + e.getLocalizedMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "mapRequestToEntity -> Failed to map HttpServletRequest. exception:" + e.getLocalizedMessage());
        }

        return entity;
    }
}