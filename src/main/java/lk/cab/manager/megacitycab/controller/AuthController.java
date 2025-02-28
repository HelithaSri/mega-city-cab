package lk.cab.manager.megacitycab.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.cab.manager.megacitycab.model.DefaultResponse;
import lk.cab.manager.megacitycab.service.LoginService;

import java.io.IOException;


@WebServlet("/auth/*")
public class AuthController extends HttpServlet {

    private final LoginService loginService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        System.out.println("Request come for auth");

        switch (pathInfo) {
            case "/login":
                System.out.println("Request come for login");
                login(req, resp);
                break;
            case "/logout":
                logout(req, resp);
                break;
            default:
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
            resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
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