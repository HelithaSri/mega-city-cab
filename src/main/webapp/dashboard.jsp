<%@ page import="java.util.Locale" %>
<%@ page import="lk.cab.manager.megacitycab.model.DashboardResponse" %>
<%@ page import="lk.cab.manager.megacitycab.entity.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String adminUser = (String) session.getAttribute("user");

    if (adminUser == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>


<html>
<head>
    <title>Mega City Cab - Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/dashboardStyles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/loader.css">
</head>

<body style="font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; text-align: center;">

<div id="loading-overlay">
    <div class="spinner"></div>
</div>

<div class="header">
    <div class="logo" onclick="location.href='dashboard'">
        <span>🚕</span> Mega City Cab
    </div>
    <div class="user-profile">
        <div>Welcome, <span class="welcome-name"><%= ((String) session.getAttribute("user")).toUpperCase()%></span>
        </div>
        <div class="user-avatar"><%= ((String) session.getAttribute("user")).toUpperCase(Locale.ROOT).charAt(0)%>
        </div>
        <button
                style="background-color: #e74c3c; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer; font-weight: bold;"
                onclick="logout()">Logout
        </button>
    </div>
</div>


<div class="main-container">
    <div class="content">
        <!-- Dashboard Page -->
        <div id="dashboard-page" class="content-page active">
            <h1 class="page-title">Dashboard</h1>

            <div class="stats-grid">
                <%
                    DashboardResponse stats = (DashboardResponse) request.getAttribute("stats");
                %>
                <div class="stat-card">
                    <div class="stat-value"><%= stats.getTotalCustomers()%>
                    </div>
                    <div class="stat-label">Total Customers</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value"><%= stats.getAvailableVehicles()%>
                    </div>
                    <div class="stat-label">Active Vehicles</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value"><%= stats.getAvailableDrivers()%>
                    </div>
                    <div class="stat-label">Available Drivers</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value"><%= stats.getTodayBookings()%>
                    </div>
                    <div class="stat-label">Today's Bookings</div>
                </div>
            </div>

            <div class="card">
                <div class="card-title">
                    <span class="card-title-icon">⚡️</span> Quick Actions
                </div>
                <div class="summary-cards">
                    <div class="action-card" onclick="location.href='customer'">
                        <div class="card-icon customers">👥</div>
                        <h3>Customer Manage</h3>
                    </div>
                    <div class="action-card" onclick="location.href='vehicle'">
                        <div class="card-icon vehicles">🚗</div>
                        <h3>Vehicle Manage</h3>
                    </div>
                    <div class="action-card" onclick="location.href='driver'">
                        <div class="card-icon vehicles">👮‍♀️</div>
                        <h3>Driver Manage</h3>
                    </div>
                    <div class="action-card" onclick="location.href='booking'">
                        <div class="card-icon vehicles">📝</div>
                        <h3>Booking Manage</h3>
                    </div>
                </div>
            </div>
            <%--

                        <div class="card">
                            <div class="card-title">
                                <span class="card-title-icon">📈</span> Booking Analytics
                            </div>
                            <div class="chart-container">
                                [Bookings Chart - Monthly Trend]
                            </div>
                        </div>
            --%>

            <!-- Status Messages -->
            <%
                String bookingMessage = (String) session.getAttribute("bookingMessage");
                String bookingStatus = (String) session.getAttribute("bookingStatus");

                if (bookingMessage != null) {
                    String alertClass = "alert-info";
                    if ("success".equalsIgnoreCase(bookingStatus)) {
                        alertClass = "alert-success";
                    } else if ("error".equalsIgnoreCase(bookingStatus)) {
                        alertClass = "alert-danger";
                    }
            %>
            <div class="alert <%= alertClass %>">
                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                <%= bookingMessage %>
            </div>
            <%
                    // Remove the attributes after displaying
                    session.removeAttribute("bookingMessage");
                    session.removeAttribute("bookingStatus");
                }
            %>
            <%--            --%>

            <div class="card">
                <div class="card-title">
                    <span class="card-title-icon">📝</span> Recent Bookings
                </div>
                <div class="table-responsive">
                    <%
                        System.out.println(pageContext.getServletContext().getContextPath());

                    %>
                    <table>
                        <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Customer</th>
                            <th>Vehicle</th>
                            <th>Driver</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Total</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Transaction> bookings = stats.getBookings();
                            for (Transaction booking : bookings) {
                        %>
                        <tr>
                            <td><%= booking.getId() %>
                            </td>
                            <td><%= booking.getCustomerId() %> | <%= booking.getCustomerName() %>
                            </td>
                            <td><%= booking.getVehicleId() %> | <%= booking.getVehicleName() %>
                            </td>
                            <td><%= booking.getDriverId() %> | <%= booking.getDriverName() %>
                            </td>
                            <td><%= booking.getStartDate().toLocalDate() %>
                            </td>
                            <td><%= booking.getEndDate().toLocalDate() %>
                            </td>
                            <td><%= booking.getTotal() %>
                            </td>
                            <td>
                            <span class="badge badge-<%=booking.getStatus().equalsIgnoreCase("Ongoing")?"pending":(booking.getStatus().equalsIgnoreCase("Complete")?"success":"danger")%>">
                                <%= booking.getStatus() %>
                            </span>
                            </td>
                            <td class="action-links">
                                <% if ("Ongoing".equalsIgnoreCase(booking.getStatus())) { %>
                                <form action="<%=pageContext.getServletContext().getContextPath()%>/booking/cancel"
                                      method="POST" style="display:inline;">
                                    <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                                    <button type="submit" class="delete-btn">Cancel</button>
                                </form>
                                <form action="<%=pageContext.getServletContext().getContextPath()%>/booking/complete"
                                      method="POST" style="display:inline;">
                                    <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                                    <button type="submit" class="success-btn">Complete</button>
                                </form>
                                <% } else if ("Cancelled".equalsIgnoreCase(booking.getStatus()) || "Completed".equalsIgnoreCase(booking.getStatus())) { %>
                                <button class="action-button" disabled>Completed</button>
                                <% } %>
                                <!-- View Booking Button -->
                                <%--<form action="/admin/viewBookingDetails" method="GET" style="display:inline;">
                                    <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                                    <button type="submit" class="pending-btn">View</button>
                                </form>--%>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
</body>

<script>
    function logout() {
        fetch('${pageContext.request.contextPath}/auth/logout', {
        method: 'POST',
        credentials: 'same-origin' // Ensure session cookies are sent
    })
    .then(response => {
        if (response.redirected) {
            window.location.href = response.url; // Redirect to index.jsp
        }
    })
    .catch(error => console.error('Logout failed:', error));
}

        document.addEventListener("DOMContentLoaded", function () {
    const allForms = document.querySelectorAll("form");

    allForms.forEach(function (form) {
        form.addEventListener("submit", function () {
            document.getElementById("loading-overlay").style.display = "flex";
        });
    });
});

    // Auto-dismiss alerts after 5 seconds
document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(function(alert) {
            if (alert) {
                alert.style.opacity = '0';
                alert.style.transition = 'opacity 0.5s';
                setTimeout(function() {
                    alert.style.display = 'none';
                }, 500);
            }
        });
    }, 5000);
});
</script>

</html>
