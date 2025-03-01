<%@ page import="java.util.Locale" %>
<%@ page import="lk.cab.manager.megacitycab.model.DashboardResponse" %>
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
</head>

<body style="font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; text-align: center;">

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

            <div class="card">
                <div class="card-title">
                    <span class="card-title-icon">📝</span> Recent Bookings
                </div>
                <div class="table-responsive">
                    <table>
                        <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Customer</th>
                            <th>Vehicle</th>
                            <th>Driver</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>B-1024</td>
                            <td>John Smith</td>
                            <td>Toyota Camry (ABC-123)</td>
                            <td>Michael Brown</td>
                            <td>Mar 01, 2025</td>
                            <td><span class="badge badge-success">Confirmed</span></td>
                            <td class="action-links">
                                <a href="#" class="edit-link">View</a>
                            </td>
                        </tr>
                        <tr>
                            <td>B-1023</td>
                            <td>Emily Johnson</td>
                            <td>Honda Accord (XYZ-789)</td>
                            <td>David Wilson</td>
                            <td>Mar 01, 2025</td>
                            <td><span class="badge badge-pending">Pending</span></td>
                            <td class="action-links">
                                <a href="#" class="edit-link">View</a>
                            </td>
                        </tr>
                        <tr>
                            <td>B-1022</td>
                            <td>Robert Davis</td>
                            <td>Ford Explorer (DEF-456)</td>
                            <td>Sarah Martinez</td>
                            <td>Feb 28, 2025</td>
                            <td><span class="badge badge-success">Completed</span></td>
                            <td class="action-links">
                                <a href="#" class="edit-link">View</a>
                            </td>
                        </tr>
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
</script>

</html>
