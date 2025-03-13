package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.cab.manager.megacitycab.model.BookingRequest;
import lk.cab.manager.megacitycab.service.BookingService;
import lk.cab.manager.megacitycab.service.CustomerService;
import lk.cab.manager.megacitycab.service.DriverService;
import lk.cab.manager.megacitycab.service.VehicleService;
import lk.cab.manager.megacitycab.util.CustomMapper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/booking/*")
public class BookingController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    VehicleService vehicleService = new VehicleService();
    CustomerService customerService = new CustomerService();
    DriverService driverService = new DriverService();
    BookingService bookingService = new BookingService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.log(Level.INFO, () -> "Received GET request for vehicles");

        req.setAttribute("availableVehicles", vehicleService.getAllVehicles().getVehicleDtoList());
        req.setAttribute("customers", customerService.getAllCustomers());
        req.setAttribute("availableDrivers", driverService.getAllDrivers());

        RequestDispatcher dispatcher = req.getRequestDispatcher("booking.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        LOGGER.log(Level.INFO, () -> "Received POST request: " + pathInfo);

        try {
            BookingRequest booking = CustomMapper.mapRequestToEntity(req, BookingRequest.class);
            LOGGER.log(Level.INFO, () -> "vehicle added request: " + booking.toString());
            bookingService.doBooking(booking);
            resp.sendRedirect(req.getContextPath() + "/Booking");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e, () -> "Error processing request" + pathInfo);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

}
