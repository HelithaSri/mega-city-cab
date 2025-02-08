package lk.cab.manager.megacitycab.service;

import jakarta.servlet.http.HttpServletRequest;
import lk.cab.manager.megacitycab.entity.Admin;
import lk.cab.manager.megacitycab.model.DefaultResponse;
import lk.cab.manager.megacitycab.repository.AdminRepository;

public class LoginService {
    private final AdminRepository adminRepository = new AdminRepository();

    public DefaultResponse adminLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && password.equals(admin.getPassword())) {
            return new DefaultResponse(true, "Login Success", admin.getUsername());
        } else {
            return new DefaultResponse(false, "Invalid credentials", null);
        }

    }

}
