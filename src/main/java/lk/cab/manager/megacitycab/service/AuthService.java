package lk.cab.manager.megacitycab.service;

import jakarta.servlet.http.HttpServletRequest;
import lk.cab.manager.megacitycab.entity.Admin;
import lk.cab.manager.megacitycab.model.DefaultResponse;
import lk.cab.manager.megacitycab.repository.AdminRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthService {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final AdminRepository adminRepository = new AdminRepository();

    public DefaultResponse adminLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && password.equals(admin.getPassword())) {
            LOGGER.log(Level.INFO, () -> "Login Success");
            return new DefaultResponse(true, "Login Success", admin.getUsername());
        } else {
            LOGGER.log(Level.WARNING, () -> "Invalid credentials");
            return new DefaultResponse(false, "Invalid credentials", null);
        }

    }

}
