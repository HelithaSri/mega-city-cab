package lk.cab.manager.megacitycab.service;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.entity.Driver;
import lk.cab.manager.megacitycab.entity.Vehicle;
import lk.cab.manager.megacitycab.model.DashboardResponse;
import lk.cab.manager.megacitycab.repository.CustomerRepository;
import lk.cab.manager.megacitycab.repository.DriverRepository;
import lk.cab.manager.megacitycab.repository.VehicleRepository;

import java.util.List;
import java.util.logging.Logger;

public class DashboardService {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    DriverRepository driverRepository = new DriverRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    VehicleRepository vehicleRepository = new VehicleRepository();

    public DashboardResponse getStats() {
        List<Driver> availableDrivers = driverRepository.findAllByStatus(true);
        List<Customer> customerList = customerRepository.findAll();
        List<Vehicle> vehicleList = vehicleRepository.findAllByStatus("Available");
        return new DashboardResponse(
                String.valueOf(customerList.size()),
                String.valueOf(availableDrivers.size()),
                String.valueOf(vehicleList.size()),
                "0"
        );
    }

}
