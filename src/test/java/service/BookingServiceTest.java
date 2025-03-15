package service;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.entity.Driver;
import lk.cab.manager.megacitycab.entity.Transaction;
import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.model.BookingRequest;
import lk.cab.manager.megacitycab.repository.CustomerRepository;
import lk.cab.manager.megacitycab.repository.DriverRepository;
import lk.cab.manager.megacitycab.repository.TransactionRepository;
import lk.cab.manager.megacitycab.repository.VehicleRepository;
import lk.cab.manager.megacitycab.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testDoBooking_Success() throws SQLException {

        BookingRequest bookingRequest = new BookingRequest(
                "D001", "C001", "V001", new BigDecimal("100"), "Hourly",
                new BigDecimal("200"), new BigDecimal("10"), new BigDecimal("5"),
                "2025-03-16 09:00", "2025-03-16 12:00");

        Customer customer = new Customer("C001", "John Doe", "123 Main St", "123456789", "987654321", "john@example.com",
                LocalDate.of(1990, 5, 20), LocalDateTime.now());
        Driver driver = new Driver("D001", "Jane Doe", "456 Elm St", "987654321V", "B123456", "0761234567",
                "jane@example.com", LocalDate.of(1985, 7, 15), true, LocalDateTime.now());
        Vehicle vehicle = new Vehicle("V001", "Toyota", "Corolla", 2020, "Sedan", "XYZ123", new BigDecimal(50), new BigDecimal(100), new BigDecimal(200), new BigDecimal(500), new BigDecimal(500), "Available");

        when(customerRepository.findById("C001")).thenReturn(customer);
        when(driverRepository.findById("D001")).thenReturn(driver);
        when(vehicleRepository.findById("V001")).thenReturn(vehicle);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(true);

        boolean result = bookingService.doBooking(bookingRequest);

        verify(customerRepository).findById("C001");
        verify(driverRepository).findById("D001");
        verify(vehicleRepository).findById("V001");
        verify(transactionRepository).save(any(Transaction.class));

        assertTrue(result);
    }

    @Test
    void testDoBooking_CustomerNotFound() {

        BookingRequest bookingRequest = new BookingRequest(
                "D001", "C001", "V001", new BigDecimal("100"), "Hourly",
                new BigDecimal("200"), new BigDecimal("10"), new BigDecimal("5"),
                "2025-03-16 09:00", "2025-03-16 12:00");

        when(customerRepository.findById("C001")).thenReturn(null); // Customer not found

        boolean result = bookingService.doBooking(bookingRequest);

        verify(customerRepository).findById("C001");

        assertFalse(result); // Expect false since customer was not found
    }

    @Test
    void testDoBooking_DriverNotFound() {

        BookingRequest bookingRequest = new BookingRequest(
                "D001", "C001", "V001", new BigDecimal("100"), "Hourly",
                new BigDecimal("200"), new BigDecimal("10"), new BigDecimal("5"),
                "2025-03-16 09:00", "2025-03-16 12:00");

        Customer customer = new Customer("C001", "John Doe", "123 Main St", "123456789", "987654321", "john@example.com",
                LocalDate.of(1990, 5, 20), LocalDateTime.now());
        Vehicle vehicle = new Vehicle("V001", "Toyota", "Corolla", 2020, "Sedan", "XYZ123", new BigDecimal(50), new BigDecimal(100), new BigDecimal(200), new BigDecimal(500), new BigDecimal(500), "Available");

        when(customerRepository.findById("C001")).thenReturn(customer);
        when(driverRepository.findById("D001")).thenReturn(null); // Driver not found
        when(vehicleRepository.findById("V001")).thenReturn(vehicle);

        boolean result = bookingService.doBooking(bookingRequest);

        verify(driverRepository).findById("D001");

        assertFalse(result); // Expect false since driver was not found
    }

    @Test
    void testDoBooking_VehicleNotFound() {

        BookingRequest bookingRequest = new BookingRequest(
                "D001", "C001", "V001", new BigDecimal("100"), "Hourly",
                new BigDecimal("200"), new BigDecimal("10"), new BigDecimal("5"),
                "2025-03-16 09:00", "2025-03-16 12:00");

        Customer customer = new Customer("C001", "John Doe", "123 Main St", "123456789", "987654321", "john@example.com",
                LocalDate.of(1990, 5, 20), LocalDateTime.now());
        Driver driver = new Driver("D001", "Jane Doe", "456 Elm St", "987654321V", "B123456", "0761234567",
                "jane@example.com", LocalDate.of(1985, 7, 15), true, LocalDateTime.now());

        when(customerRepository.findById("C001")).thenReturn(customer);
        when(driverRepository.findById("D001")).thenReturn(driver);
        when(vehicleRepository.findById("V001")).thenReturn(null); // Vehicle not found

        boolean result = bookingService.doBooking(bookingRequest);

        verify(vehicleRepository).findById("V001");

        assertFalse(result); // Expect false since vehicle was not found
    }
}
