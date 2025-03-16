package service;

import lk.cab.manager.megacitycab.entity.Driver;
import lk.cab.manager.megacitycab.model.DriverDto;
import lk.cab.manager.megacitycab.repository.DriverRepository;
import lk.cab.manager.megacitycab.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    private DriverDto driverDto;
    private Driver driver;

    @BeforeEach
    void setUp() {
        driverDto = new DriverDto("d001", "John Doe", "123 Main St", "987654321V",
                "B123456", "0771234567", "john@example.com",
                "1990-01-01", true);

        driver = new Driver("d001", "John Doe", "123 Main St", "987654321V",
                "B123456", "0771234567", "john@example.com",
                LocalDate.parse("1990-01-01"), true);
    }

    @Test
    void testAddDriver() throws SQLException {
        doNothing().when(driverRepository).save(any(Driver.class));

        assertDoesNotThrow(() -> driverService.addDriver(driverDto));
        verify(driverRepository, times(1)).save(any(Driver.class));
    }

    @Test
    void testUpdateDriver_WhenDriverExists() throws SQLException {

        when(driverRepository.findById(driverDto.getId())).thenReturn(driver);

        doNothing().when(driverRepository).update(any(Driver.class));

        assertDoesNotThrow(() -> driverService.updateDriver(driverDto));

        verify(driverRepository, times(1)).update(any(Driver.class));
    }

    @Test
    void testUpdateDriver_WhenDriverNotFound() throws SQLException {
        when(driverRepository.findById(driverDto.getId())).thenReturn(null);

        driverService.updateDriver(driverDto);

        verify(driverRepository, never()).update(any(Driver.class));
    }

    @Test
    void testDeleteDriver_WhenDriverExists() throws SQLException {
        when(driverRepository.findById(driverDto.getId())).thenReturn(driver);
        when(driverRepository.delete(driverDto.getId())).thenReturn(true);

        assertDoesNotThrow(() -> driverService.deleteDriver(driverDto.getId()));
        verify(driverRepository, times(1)).delete(driverDto.getId());
    }

    @Test
    void testDeleteDriver_WhenDriverNotFound() throws SQLException {
        when(driverRepository.findById(driverDto.getId())).thenReturn(null);

        driverService.deleteDriver(driverDto.getId());

        verify(driverRepository, never()).delete(anyString());
    }

    @Test
    void testGetAllDrivers() {
        List<Driver> driverList = Collections.singletonList(driver);
        when(driverRepository.findAll()).thenReturn(driverList);

        List<DriverDto> drivers = driverService.getAllDrivers();

        assertEquals(1, drivers.size());
        assertEquals(driverDto.getId(), drivers.get(0).getId());
        verify(driverRepository, times(1)).findAll();
    }
}
