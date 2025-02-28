package lk.cab.manager.megacitycab.service;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.model.CustomerDto;
import lk.cab.manager.megacitycab.repository.CustomerRepository;
import lk.cab.manager.megacitycab.util.IdGenerator;
import lk.cab.manager.megacitycab.util.QueryUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerService {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    CustomerRepository customerRepository = new CustomerRepository();


    public boolean addCustomer(CustomerDto dto) throws SQLException {
        String lastId = IdGenerator.generateNextCustomerId(QueryUtil.FIND_LAST_CUSTOMER_ID, "c");
        Customer customer = new Customer(
                lastId,
                dto.getName(),
                dto.getAddress(),
                dto.getNic(),
                dto.getMobile(),
                dto.getEmail(),
                LocalDate.parse(dto.getDob())
        );
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) throws SQLException {
        Customer customer = customerRepository.findById(customerId);
        customerRepository.delete(customer.getId());
    }

    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        LOGGER.log(Level.INFO, () -> customerList.size() + " customerList entries found");

        customerList.forEach(item -> customers.add(
                new CustomerDto(
                        item.getId(),
                        item.getName(),
                        item.getAddress(),
                        item.getNic(),
                        item.getMobile(),
                        item.getEmail(),
                        item.getDob().toString()
                )));
        LOGGER.log(Level.INFO, () -> customers.size() + " customers entries found");

        return customers;
    }
}
