<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="lk.cab.manager.megacitycab.entity.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.cab.manager.megacitycab.entity.Customer" %>
<%@ page import="lk.cab.manager.megacitycab.entity.Driver" %>
<%@ page import="lk.cab.manager.megacitycab.entity.Vehicle" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String adminUser = (String) session.getAttribute("user");
    if (adminUser == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    List<Transaction> bookings = (List<Transaction>) request.getAttribute("bookings");
    Map<String, Customer> customerMap = (Map<String, Customer>) request.getAttribute("customerMap");
    Map<String, Driver> driverMap = (Map<String, Driver>) request.getAttribute("driverMap");
    Map<String, Vehicle> vehicleMap = (Map<String, Vehicle>) request.getAttribute("vehicleMap");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
%>

<html>
<head>
    <title>Mega City Cab - Transaction Management</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/dashboardStyles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/loader.css">
    <style>
        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border-radius: 8px;
            width: 60%;
            max-width: 700px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .modal-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
            margin-bottom: 15px;
        }

        .close {
            color: #aaa;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: #555;
        }

        .booking-details {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 10px;
        }

        .booking-details-item {
            margin-bottom: 10px;
        }

        .booking-details-label {
            font-weight: bold;
            margin-right: 5px;
        }

        .action-button {
            margin-right: 5px;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            border: none;
            font-weight: bold;
        }

        .view-button {
            background-color: #3498db;
            color: white;
        }

        .complete-button {
            background-color: #2ecc71;
            color: white;
        }

        .cancel-button {
            background-color: #e74c3c;
            color: white;
        }

        .disabled-button {
            background-color: #95a5a6;
            cursor: not-allowed;
        }
    </style>
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
        <div>Welcome, <span class="welcome-name"><%= adminUser.toUpperCase() %></span></div>
        <div class="user-avatar"><%= adminUser.toUpperCase().charAt(0) %></div>
        <button style="background-color: #e74c3c; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer; font-weight: bold;" onclick="logout()">Logout</button>
    </div>
</div>

<div class="main-container">
    <div class="content">
        <div class="content-page active">
            <h1 class="page-title">Transaction Management</h1>

            <div class="card">
                <div class="card-title">
                    <span class="card-title-icon">📝</span> All Bookings
                </div>
                <div class="table-responsive">
                    <table>
                        <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Customer</th>
                            <th>Vehicle</th>
                            <th>Driver</th>
                            <th>Start Date</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% if (bookings != null && !bookings.isEmpty()) { %>
                        <% for (Transaction booking : bookings) { %>
                        <tr>
                            <td><%= booking.getId() %></td>
                            <td>
                                <%
                                    Customer customer = customerMap.get(booking.getCustomerId());
                                    if (customer != null) {
                                        out.print(customer.getName());
                                    } else {
                                        out.print("Unknown");
                                    }
                                %>
                            </td>
                            <td>
                                <%
                                    Vehicle vehicle = vehicleMap.get(booking.getVehicleId());
                                    if (vehicle != null) {
                                        out.print(vehicle.getMake() + " " + vehicle.getModel() + " (" + vehicle.getLicensePlate() + ")");
                                    } else {
                                        out.print("Unknown");
                                    }
                                %>
                            </td>
                            <td>
                                <%
                                    Driver driver = driverMap.get(booking.getDriverId());
                                    if (driver != null) {
                                        out.print(driver.getName());
                                    } else {
                                        out.print("Unknown");
                                    }
                                %>
                            </td>
                            <td><%= booking.getStartDate().format(formatter) %></td>
                            <td>
                                <%
                                    String status = booking.getStatus();
                                    String badgeClass = "badge-pending";

                                    if ("COMPLETED".equalsIgnoreCase(status)) {
                                        badgeClass = "badge-success";
                                    } else if ("CANCELLED".equalsIgnoreCase(status)) {
                                        badgeClass = "badge-danger";
                                    } else if ("ONGOING".equalsIgnoreCase(status)) {
                                        badgeClass = "badge-warning";
                                    }
                                %>
                                <span class="badge <%= badgeClass %>"><%= status %></span>
                            </td>
                            <td class="action-links">
                                <button class="action-button view-button" onclick="viewBooking('<%= booking.getId() %>')">View</button>

                                <% if ("ONGOING".equalsIgnoreCase(booking.getStatus())) { %>
                                <form method="post" action="<%= request.getContextPath() %>/booking" style="display:inline;">
                                    <input type="hidden" name="action" value="complete">
                                    <input type="hidden" name="id" value="<%= booking.getId() %>">
                                    <button type="submit" class="action-button complete-button">Complete</button>
                                </form>

                                <form method="post" action="<%= request.getContextPath() %>/booking" style="display:inline;">
                                    <input type="hidden" name="action" value="cancel">
                                    <input type="hidden" name="id" value="<%= booking.getId() %>">
                                    <button type="submit" class="action-button cancel-button">Cancel</button>
                                </form>
                                <% } else { %>
                                <button class="action-button complete-button disabled-button" disabled>Complete</button>
                                <button class="action-button cancel-button disabled-button" disabled>Cancel</button>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="7">No bookings found.</td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal for viewing booking details -->
<div id="bookingModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Transaction Details</h2>
            <span class="close">&times;</span>
        </div>
        <div class="booking-details" id="bookingDetails">
            <!-- Transaction details will be populated here -->
        </div>
    </div>
</div>

<script>
    function logout() {
        fetch('${pageContext.request.contextPath}/auth/logout', {
            method: 'POST',
            credentials: 'same-origin'
        })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            }
        })
        .catch(error => console.error('Logout failed:', error));
    }

    document.addEventListener("DOMContentLoaded", function() {
        const allForms = document.querySelectorAll("form");
        allForms.forEach(function(form) {
            form.addEventListener("submit", function() {
                document.getElementById("loading-overlay").style.display = "flex";
            });
        });

        // Modal handling
        const modal = document.getElementById("bookingModal");
        const span = document.getElementsByClassName("close")[0];

        span.onclick = function() {
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    });
function viewBooking(bookingId) {
        document.getElementById("loading-overlay").style.display = "flex";

        fetch('${pageContext.request.contextPath}/api/booking?id=' + bookingId)
            .then(response => response.json())
            .then(data => {
                const detailsContainer = document.getElementById("bookingDetails");

                // Format dates
                const startDate = new Date(data.startDate).toLocaleString();
                const endDate = data.endDate ? new Date(data.endDate).toLocaleString() : "Not completed";
                const createdAt = new Date(data.createdAt).toLocaleString();

                // Build the HTML content
                let html = `
                    <div class="booking-details-item">
                        <span class="booking-details-label">Transaction ID:</span>
                        <span>${data.id}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Status:</span>
                        <span>${data.status}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Customer:</span>
                        <span>${data.customerName}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Driver:</span>
                        <span>${data.driverName}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Vehicle:</span>
                        <span>${data.vehicleDetails}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Rental Type:</span>
                        <span>${data.rentalType}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Estimated Mileage:</span>
                        <span>${data.estMileage} km</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Start Date:</span>
                        <span>${startDate}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">End Date:</span>
                        <span>${endDate}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Base Fee:</span>
                        <span>$${data.total}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Additional Fee:</span>
                        <span>$${data.additionalFee || "0.00"}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Discount:</span>
                        <span>$${data.discountFee || "0.00"}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Total Amount:</span>
                        <span>$${(parseFloat(data.total) + parseFloat(data.additionalFee || 0) - parseFloat(data.discountFee || 0)).toFixed(2)}</span>
                    </div>
                    <div class="booking-details-item">
                        <span class="booking-details-label">Created At:</span>
                        <span>${createdAt}</span>
                    </div>
                `;

                detailsContainer.innerHTML = html;
                document.getElementById("loading-overlay").style.display = "none";
                document.getElementById("bookingModal").style.display = "block";
            })
            .catch(error => {
                console.error('Error fetching booking details:', error);
                document.getElementById("loading-overlay").style.display = "none";
                alert("Failed to load booking details. Please try again.");
            });
    }
</script>

</body>
</html>