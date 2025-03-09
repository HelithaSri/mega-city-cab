package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.cab.manager.megacitycab.model.VehicleDataDto;
import lk.cab.manager.megacitycab.model.VehicleDto;
import lk.cab.manager.megacitycab.service.VehicleService;
import lk.cab.manager.megacitycab.util.CustomMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/vehicle/*")
public class VehicleController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.log(Level.INFO, () -> "Received GET request for vehicles");

        VehicleDataDto vehiclesData = vehicleService.getAllVehicles();

        req.setAttribute("vehicles", vehiclesData.getVehicleDtoList());
        req.setAttribute("total", vehiclesData.getTotal());
        req.setAttribute("available", vehiclesData.getAvailable());

        RequestDispatcher dispatcher = req.getRequestDispatcher("vehicle.jsp");
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
        VehicleDto vehicleDto = CustomMapper.mapRequestToEntity(req, VehicleDto.class);
        LOGGER.log(Level.INFO, () -> "vehicle added request: " + vehicleDto.toString());
        vehicleService.addVehicle(vehicleDto);
        resp.sendRedirect(req.getContextPath() + "/vehicle");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        VehicleDto vehicleDto = CustomMapper.mapRequestToEntity(req, VehicleDto.class);
        LOGGER.log(Level.INFO, () -> "vehicle update request: " + vehicleDto.toString());
        vehicleService.updateVehicle(vehicleDto);
        resp.sendRedirect(req.getContextPath() + "/vehicle");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, SQLException {
        String drivingId = req.getParameter("id");
        LOGGER.log(Level.INFO, () -> "vehicle deleted by ID: " + drivingId);
        vehicleService.deleteVehicle(drivingId);
        resp.sendRedirect(req.getContextPath() + "/vehicle");
    }
}
