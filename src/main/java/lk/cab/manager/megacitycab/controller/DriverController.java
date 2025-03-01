package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.cab.manager.megacitycab.model.DriverDto;
import lk.cab.manager.megacitycab.service.DriverService;
import lk.cab.manager.megacitycab.util.CustomMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/driver/*")
public class DriverController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    DriverService driverService = new DriverService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.log(Level.INFO, () -> "Received GET request for drivers");

        List<DriverDto> drivers = driverService.getAllDrivers();

        int availableCount = 0;
        for (DriverDto driver : drivers) {
            if (driver.isAvailability()) {
                availableCount++;
            }
        }

        req.setAttribute("drivers", drivers);
        req.setAttribute("total", drivers.size());
        req.setAttribute("available", availableCount);

        RequestDispatcher dispatcher = req.getRequestDispatcher("driver.jsp");
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
        DriverDto driverDto = CustomMapper.mapRequestToEntity(req, DriverDto.class);
        LOGGER.log(Level.INFO, () -> "driver added request: " + driverDto.toString());
        driverService.addDriver(driverDto);
        resp.sendRedirect(req.getContextPath() + "/driver");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        DriverDto driverDto = CustomMapper.mapRequestToEntity(req, DriverDto.class);
        LOGGER.log(Level.INFO, () -> "driver update request: " + driverDto.toString());
        driverService.updateDriver(driverDto);
        resp.sendRedirect(req.getContextPath() + "/driver");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        String drivingId = req.getParameter("id");
        LOGGER.log(Level.INFO, () -> "driver deleted by ID: " + drivingId);
        driverService.deleteDriver(drivingId);
        resp.sendRedirect(req.getContextPath() + "/driver");
    }
}
