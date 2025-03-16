package service;

import jakarta.servlet.http.HttpServletRequest;
import lk.cab.manager.megacitycab.entity.Admin;
import lk.cab.manager.megacitycab.model.DefaultResponse;
import lk.cab.manager.megacitycab.repository.AdminRepository;
import lk.cab.manager.megacitycab.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private AuthService authService;

    @Test
    void testAdminLogin_Success() {
        String username = "admin";
        String password = "admin";
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        DefaultResponse response = authService.adminLogin(request);
        assertTrue(response.isStatus());
        assertEquals("Login Success", response.getMessage());
        assertEquals(username, response.getData());
    }


    @Test
    void testAdminLogin_InvalidCredentials() {
        String username = "admin";
        String password = "wrongpassword";
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword("admin");  // Correct password is different
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);

        DefaultResponse response = authService.adminLogin(request);
        assertFalse(response.isStatus());
        assertEquals("Invalid credentials", response.getMessage());
        assertNull(response.getData());
    }


    @Test
    void testAdminLogin_AdminNotFound() {
        String username = "admins";
        String password = "admin";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        DefaultResponse response = authService.adminLogin(request);
        assertFalse(response.isStatus());
        assertEquals("Invalid credentials", response.getMessage());
        assertNull(response.getData());
    }
}
