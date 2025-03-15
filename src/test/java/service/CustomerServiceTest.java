package service;

import lk.cab.manager.megacitycab.entity.Customer;
import lk.cab.manager.megacitycab.model.CustomerDto;
import lk.cab.manager.megacitycab.repository.CustomerRepository;
import lk.cab.manager.megacitycab.service.CustomerService;
import lk.cab.manager.megacitycab.util.IdGenerator;
import lk.cab.manager.megacitycab.util.QueryUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerDto = new CustomerDto(
                "c001",
                "John Doe",
                "123 Street",
                "990011223V",
                "0771234567",
                "johndoe@example.com",
                "1990-01-01",
                LocalDate.now().atStartOfDay()
        );

        customer = new Customer(
                "c001",
                "John Doe",
                "123 Street",
                "990011223V",
                "0771234567",
                "johndoe@example.com",
                LocalDate.parse("1990-01-01")
        );
    }

    @Test
    void testAddCustomer() throws SQLException {

        mockStatic(IdGenerator.class);
        when(IdGenerator.generateNextId(QueryUtil.FIND_LAST_CUSTOMER_ID, "c")).thenReturn("c002");

        when(customerRepository.save(any(Customer.class))).thenReturn(true);

        boolean result = customerService.addCustomer(customerDto);

        assertTrue(result);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomer_WhenCustomerExists() throws SQLException {

        when(customerRepository.findById(customerDto.getId())).thenReturn(customer);

        doNothing().when(customerRepository).update(any(Customer.class));

        assertDoesNotThrow(() -> customerService.updateCustomer(customerDto));

        verify(customerRepository, times(1)).update(any(Customer.class));
    }

    @Test
    void testUpdateCustomer_WhenCustomerDoesNotExist() throws SQLException {

        when(customerRepository.findById(customerDto.getId())).thenReturn(null);

        customerService.updateCustomer(customerDto);

        verify(customerRepository, never()).update(any(Customer.class));
    }

    @Test
    void testDeleteCustomer_WhenCustomerExists() throws SQLException {

        when(customerRepository.findById("c001")).thenReturn(customer);

        when(customerRepository.delete("c001")).thenReturn(true);

        assertDoesNotThrow(() -> customerService.deleteCustomer("c001"));

        verify(customerRepository, times(1)).delete("c001");
    }

    @Test
    void testDeleteCustomer_WhenCustomerDoesNotExist() throws SQLException {

        when(customerRepository.findById("c999")).thenReturn(null);

        customerService.deleteCustomer("c999");

        verify(customerRepository, never()).delete(anyString());
    }

    @Test
    void testGetAllCustomers() {

        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));

        List<CustomerDto> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());

        verify(customerRepository, times(1)).findAll();
    }
}
