package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.cab.manager.megacitycab.model.DashboardResponse;
import lk.cab.manager.megacitycab.service.DashboardService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/dashboard/*")
public class DashboardController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.log(Level.INFO, () -> "Received GET request for dashboard");

        DashboardResponse stats = dashboardService.getStats();
        req.setAttribute("stats", stats);

        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(req, resp);
    }
}
