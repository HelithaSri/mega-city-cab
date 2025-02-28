package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.cab.manager.megacitycab.model.CustomerDto;
import lk.cab.manager.megacitycab.service.CustomerService;
import lk.cab.manager.megacitycab.util.ResultSetMapper;

import java.io.IOException;
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
        req.setAttribute("customers", customers);
        req.setAttribute("total", customers.size());

        RequestDispatcher dispatcher = req.getRequestDispatcher("customer.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        LOGGER.log(Level.INFO, () -> "Received POST request: " + pathInfo);

        try {
            if ("/add".equals(pathInfo)) {
                CustomerDto customerDto = ResultSetMapper.mapRequestToEntity(req, CustomerDto.class);
                LOGGER.log(Level.INFO, () -> "Customer added request: " + customerDto.toString());
                customerService.addCustomer(customerDto);
                resp.sendRedirect(req.getContextPath() + "/customers");

            } else if ("/delete".equals(pathInfo)) {
                int customerId = Integer.parseInt(req.getParameter("id"));
                LOGGER.log(Level.INFO, () -> "Customer deleted by ID" + customerId);
                customerService.deleteCustomer(customerId);
                resp.sendRedirect(req.getContextPath() + "/customers");

            } else {
                LOGGER.log(Level.WARNING, () -> "Invalid POST request received: {}" + pathInfo);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid Endpoint");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error processing request" + pathInfo);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

}
