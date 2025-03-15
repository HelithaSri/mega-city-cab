package service;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.entity.Driver;
import lk.cab.manager.megacitycab.entity.Transaction;
import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.model.DashboardResponse;
import lk.cab.manager.megacitycab.repository.CustomerRepository;
import lk.cab.manager.megacitycab.repository.DriverRepository;
import lk.cab.manager.megacitycab.repository.TransactionRepository;
import lk.cab.manager.megacitycab.repository.VehicleRepository;
import lk.cab.manager.megacitycab.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void testGetStats() {

        Driver driver1 = new Driver();
        driver1.setAvailability(true);
        Driver driver2 = new Driver();
        driver2.setAvailability(true);
        List<Driver> availableDrivers = Arrays.asList(driver1, driver2);

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        List<Customer> customers = Arrays.asList(customer1, customer2);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setStatus("Available");
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setStatus("Available");
        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);

        Transaction transaction1 = new Transaction();
        transaction1.setId("B0001");
        List<Transaction> transactions = List.of(transaction1);

        when(driverRepository.findAllByStatus(true)).thenReturn(availableDrivers);
        when(customerRepository.findAll()).thenReturn(customers);
        when(vehicleRepository.findAllByStatus("Available")).thenReturn(vehicles);
        when(transactionRepository.todayBookingCount()).thenReturn(1);
        when(transactionRepository.findAll()).thenReturn(transactions);

        DashboardResponse response = dashboardService.getStats();

        assertEquals("2", response.getTotalCustomers());
        assertEquals("2", response.getAvailableDrivers());
        assertEquals("2", response.getAvailableVehicles());
        assertEquals("1", response.getTodayBookings());
    }
}
