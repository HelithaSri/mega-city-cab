package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.cab.manager.megacitycab.model.DefaultResponse;
import lk.cab.manager.megacitycab.service.AuthService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/auth/*")
public class AuthController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final AuthService loginService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.log(Level.INFO, () -> "Received POST request for auth" + req.getServletPath());

        String pathInfo = req.getPathInfo();

        switch (pathInfo) {
            case "/login":
                login(req, resp);
                break;
            case "/logout":
                logout(req, resp);
                break;
            default:
                LOGGER.log(Level.WARNING, () -> "Invalid POST request received: {}" + pathInfo);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");
                break;
        }

    }

    private void login(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        DefaultResponse response = loginService.adminLogin(req);

        if (response.isStatus()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", response.getData());
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp?error=" + response.getMessage());
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession(false);
//        session.setAttribute("user", null);
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

}