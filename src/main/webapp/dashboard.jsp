<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String adminUser = (String) session.getAttribute("user");
    if (adminUser == null) {
        response.sendRedirect("index.jsp"); // Redirect to login if not logged in
    }
%>

<html>
<head>
    <title>Mega City Cab - Admin Dashboard</title>
</head>
<body style="font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; text-align: center;">

<!-- Navigation Bar -->
<div style="padding: 15px; background: white; justify-content: space-between; display: flex; box-shadow: 0 0 10px rgba(0,0,0,0.2); border-bottom: 8px;">
    <img src="<%= request.getContextPath() %>/resources/img/logo.png" alt="Mega City Cab Logo"
         style="width: 150px; height: auto;">
    <h2 style="color: black; margin: 0;">Welcome, <%= (String) ((String) session.getAttribute("user")).toUpperCase()%>
        !</h2>
</div>

<!-- Dashboard Container -->
<div style="margin: 20px auto; width: 80%; background: white; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.2); border-radius: 8px;">
    <!-- Dashboard Grid -->
    <div style="display: flex; flex-wrap: wrap; justify-content: center; gap: 20px; margin-top: 20px;">

        <!-- Booking Management -->
        <div style="width: 200px; padding: 20px; background-color: #007bff; color: white; border-radius: 8px; cursor: pointer;"
             onclick="location.href='manageBookings.jsp'">
            <h4>📖 Manage Bookings</h4>
            <p>View and update customer bookings</p>
        </div>

        <!-- Driver Management -->
        <div style="width: 200px; padding: 20px; background-color: #28a745; color: white; border-radius: 8px; cursor: pointer;"
             onclick="location.href='manageDrivers.jsp'">
            <h4>🚖 Driver Management</h4>
            <p>View and update driver details</p>
        </div>

        <!-- Car Management -->
        <div style="width: 200px; padding: 20px; background-color: #ffc107; color: black; border-radius: 8px; cursor: pointer;"
             onclick="location.href='manageCars.jsp'">
            <h4>🚗 Car Information</h4>
            <p>Manage cab details</p>
        </div>

        <!-- Billing System -->
        <div style="width: 200px; padding: 20px; background-color: #17a2b8; color: white; border-radius: 8px; cursor: pointer;"
             onclick="location.href='billing.jsp'">
            <h4>🧾 Billing</h4>
            <p>Generate and print customer bills</p>
        </div>

        <!-- Help Section -->
        <div style="width: 200px; padding: 20px; background-color: #6c757d; color: white; border-radius: 8px; cursor: pointer;"
             onclick="location.href='help.jsp'">
            <h4>❓ Help</h4>
            <p>View system usage guidelines</p>
        </div>

        <!-- Logout Button -->
        <div style="width: 200px; padding: 20px; background-color: #dc3545; color: white; border-radius: 8px; cursor: pointer;"
             onclick="location.href='logout.jsp'">
            <h4>🚪 Logout</h4>
            <p>Exit the system safely</p>
        </div>

    </div>
</div>

</body>
</html>
