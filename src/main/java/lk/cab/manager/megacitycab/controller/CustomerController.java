package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.cab.manager.megacitycab.model.CustomerDto;
import lk.cab.manager.megacitycab.service.CustomerService;
import lk.cab.manager.megacitycab.util.CustomMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/customer/*")
public class CustomerController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.log(Level.INFO, () -> "Received GET request for customers");

        List<CustomerDto> customers = customerService.getAllCustomers();

        // Get the current month and year
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        int newOnThisMonth = 0;
        for (CustomerDto customer : customers) {
            LocalDateTime createdAt = customer.getCreatedAt();
            if (createdAt.getYear() == currentYear && createdAt.getMonthValue() == currentMonth) {
                newOnThisMonth++;
            }
        }

        req.setAttribute("customers", customers);
        req.setAttribute("total", customers.size());
        req.setAttribute("new_total", newOnThisMonth);

        RequestDispatcher dispatcher = req.getRequestDispatcher("customer.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        LOGGER.log(Level.INFO, () -> "Received POST request: " + pathInfo);

        try {
            switch (pathInfo) {
                case "/add":
                    add(req, resp);
                    break;
                case "/update":
                    update(req, resp);
                    break;
                case "/delete":
                    delete(req, resp);
                    break;
                default:
                    LOGGER.log(Level.WARNING, () -> "Invalid POST request received: {}" + pathInfo);
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");
                    break;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error processing request" + pathInfo);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        CustomerDto customerDto = CustomMapper.mapRequestToEntity(req, CustomerDto.class);
        LOGGER.log(Level.INFO, () -> "Customer added request: " + customerDto.toString());
        customerService.addCustomer(customerDto);
        resp.sendRedirect(req.getContextPath() + "/customer");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        CustomerDto customerDto = CustomMapper.mapRequestToEntity(req, CustomerDto.class);
        LOGGER.log(Level.INFO, () -> "Customer update request: " + customerDto.toString());
        customerService.updateCustomer(customerDto);
        resp.sendRedirect(req.getContextPath() + "/customer");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        String customerId = req.getParameter("id");
        LOGGER.log(Level.INFO, () -> "Customer deleted by ID: " + customerId);
        customerService.deleteCustomer(customerId);
        resp.sendRedirect(req.getContextPath() + "/customer");
    }
}
