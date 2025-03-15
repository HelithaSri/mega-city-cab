package service;

import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.model.VehicleDataDto;
import lk.cab.manager.megacitycab.model.VehicleDto;
import lk.cab.manager.megacitycab.repository.VehicleRepository;
import lk.cab.manager.megacitycab.service.VehicleService;
import lk.cab.manager.megacitycab.util.IdGenerator;
import lk.cab.manager.megacitycab.util.QueryUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private VehicleDto vehicleDto;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vehicleDto = new VehicleDto(
                "V001",
                "Toyota",
                "Corolla",
                2022,
                "Sedan",
                "WP-ABC1234",
                new BigDecimal("5000.0"),
                new BigDecimal("500.0"),
                new BigDecimal("30000.0"),
                new BigDecimal(200),
                new BigDecimal("10.0"),
                "Available",
                null
        );

        vehicle = new Vehicle(
                "V001",
                "Toyota",
                "Corolla",
                2022,
                "Sedan",
                "WP-ABC1234",
                new BigDecimal("5000.0"),
                new BigDecimal("500.0"),
                new BigDecimal("30000.0"),
                new BigDecimal(200),
                new BigDecimal("10.0"),
                "Available"
        );
    }

    @Test
    void testGetAllVehicles() {
        when(vehicleRepository.findAll()).thenReturn(Collections.singletonList(vehicle));

        VehicleDataDto result = vehicleService.getAllVehicles();

        assertNotNull(result);
        assertEquals(1, result.getTotal());
        assertEquals(1, result.getAvailable());
        assertEquals("Toyota", result.getVehicleDtoList().get(0).getMake());

        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void testAddVehicle() throws SQLException {
        mockStatic(IdGenerator.class);
        when(IdGenerator.generateNextId(QueryUtil.FIND_LAST_VEHICLE_ID, "V")).thenReturn("V002");

        doNothing().when(vehicleRepository).save(any(Vehicle.class));

        assertDoesNotThrow(() -> vehicleService.addVehicle(vehicleDto));

        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    void testUpdateVehicle_WhenVehicleExists() throws SQLException {
        when(vehicleRepository.findById(vehicleDto.getId())).thenReturn(vehicle);
        doNothing().when(vehicleRepository).update(any(Vehicle.class));

        assertDoesNotThrow(() -> vehicleService.updateVehicle(vehicleDto));

        verify(vehicleRepository, times(1)).update(any(Vehicle.class));
    }

    @Test
    void testUpdateVehicle_WhenVehicleDoesNotExist() throws SQLException {
        when(vehicleRepository.findById(vehicleDto.getId())).thenReturn(null);

        vehicleService.updateVehicle(vehicleDto);

        verify(vehicleRepository, never()).update(any(Vehicle.class));
    }

    @Test
    void testDeleteVehicle_WhenVehicleExists() throws SQLException {
        when(vehicleRepository.findById("V001")).thenReturn(vehicle);
        when(vehicleRepository.delete("V001")).thenReturn(true);

        assertDoesNotThrow(() -> vehicleService.deleteVehicle("V001"));

        verify(vehicleRepository, times(1)).delete("V001");
    }

    @Test
    void testDeleteVehicle_WhenVehicleDoesNotExist() throws SQLException {
        when(vehicleRepository.findById("V999")).thenReturn(null);

        vehicleService.deleteVehicle("V999");

        verify(vehicleRepository, never()).delete(anyString());
    }
}
