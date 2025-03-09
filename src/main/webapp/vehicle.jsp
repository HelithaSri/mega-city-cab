<%@ page import="lk.cab.manager.megacitycab.model.VehicleDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
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

<!DOCTYPE html>
<html>
<head>
    <title>Mega City Cab - Vehicle Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/loader.css">
</head>
<body>

<div id="loading-overlay">
    <div class="spinner"></div>
</div>

<div class="header">
    <div class="logo" onclick="location.href='dashboard'">
        <span>🚕</span> Mega City Cab
    </div>
    <div class="user-profile">
        <div>Welcome, <span class="welcome-name"><%= ((String) session.getAttribute("user")).toUpperCase() %></span>
        </div>
        <div class="user-avatar"><%= ((String) session.getAttribute("user")).toUpperCase(Locale.ROOT).charAt(0) %>
        </div>
        <button style="background-color: #e74c3c; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer; font-weight: bold;"
                onclick="logout()">Logout
        </button>
    </div>
</div>

<div class="main-container">
    <div class="content">
        <h1 class="page-title">Vehicle Management</h1>

        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-value">${pageContext.request.getAttribute("total")}</div>
                <div class="stat-label">Total Vehicles</div>
            </div>
            <div class="stat-card">
                <div class="stat-value">${pageContext.request.getAttribute("available")}</div>
                <div class="stat-label">Available Vehicles</div>
            </div>
        </div>

        <%
            VehicleDto vehicleToEdit = (VehicleDto) request.getAttribute("vehicleToEdit");
            boolean isEditMode = vehicleToEdit != null;
            String formAction = isEditMode ? "vehicle/update" : "vehicle/add";
            String formTitle = isEditMode ? "Edit Vehicle" : "Add New Vehicle";
            String buttonText = isEditMode ? "Update Vehicle" : "Add Vehicle";
            String buttonIcon = isEditMode ? "✏️" : "➕";
        %>

        <div class="card">
            <div class="card-title">
                <span class="card-title-icon"><%= buttonIcon %></span> <%= formTitle %>
            </div>
            <form action="<%= formAction %>" method="post" data-mode="<%= isEditMode ? "edit" : "add" %>"
                  autocomplete="off">
                <div class="form-grid">
                    <div>
                        <div class="form-group">
                            <label for="make" class="form-label">Make</label>
                            <input type="text" id="make" name="make" class="form-control" placeholder="Make" required
                                   value="<%= isEditMode ? vehicleToEdit.getMake() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="model" class="form-label">Model</label>
                            <input type="text" id="model" name="model" class="form-control" placeholder="Model" required
                                   value="<%= isEditMode ? vehicleToEdit.getModel() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="year" class="form-label">Year</label>
                            <input type="number" id="year" name="year" class="form-control" placeholder="Year" required
                                   value="<%= isEditMode ? vehicleToEdit.getYear() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="type" class="form-label">Type</label>
                            <input type="text" id="type" name="type" class="form-control" placeholder="Type" required
                                   value="<%= isEditMode ? vehicleToEdit.getType() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="licensePlate" class="form-label">License Plate</label>
                            <input type="text" id="licensePlate" name="licensePlate" class="form-control" required
                                   value="<%= isEditMode ? vehicleToEdit.getLicensePlate() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="status" class="form-label">Status</label>
                            <select id="status" name="status" class="form-control" required>
                                <option value="Available" <%= isEditMode && vehicleToEdit.getStatus().equals("available") ? "selected" : "" %>>
                                    Available
                                </option>
                                <option value="Rented" <%= isEditMode && vehicleToEdit.getStatus().equals("rented") ? "selected" : "" %>>
                                    Rented
                                </option>
                                <option value="Maintenance" <%= isEditMode && vehicleToEdit.getStatus().equals("maintenance") ? "selected" : "" %>>
                                    Maintenance
                                </option>
                            </select>
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <label for="dailyRate" class="form-label">Daily Rate</label>
                            <input type="text" id="dailyRate" name="dailyRate" class="form-control" required
                                   value="<%= isEditMode ? vehicleToEdit.getDailyRate() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="hourlyRate" class="form-label">Hourly Rate</label>
                            <input type="text" id="hourlyRate" name="hourlyRate" class="form-control"
                                   value="<%= isEditMode ? vehicleToEdit.getHourlyRate() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="weeklyRate" class="form-label">Weekly Rate</label>
                            <input type="text" id="weeklyRate" name="weeklyRate" class="form-control"
                                   value="<%= isEditMode ? vehicleToEdit.getWeeklyRate() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="mileageLimit" class="form-label">Mileage Limit</label>
                            <input type="text" id="mileageLimit" name="mileageLimit" class="form-control"
                                   value="<%= isEditMode ? vehicleToEdit.getMileageLimit() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="extraMileageFee" class="form-label">Extra Mileage Limit</label>
                            <input type="text" id="extraMileageFee" name="extraMileageFee" class="form-control"
                                   value="<%= isEditMode ? vehicleToEdit.getExtraMileageFee() : "" %>">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" style="width: 100%;"><%= buttonText %>
                            </button>
                            <% if (isEditMode) { %>
                            <a href="VehicleManagementServlet" class="btn btn-secondary"
                               style="width: 100%; margin-top: 10px;">Cancel</a>
                            <% } %>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="card">
            <div class="card-title">
                <span class="card-title-icon">🚗</span> Vehicle List
            </div>
            <div class="table-responsive">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Make</th>
                        <th>Model</th>
                        <th>Year</th>
                        <th>Type</th>
                        <th>License Plate</th>
                        <th>Daily Rate</th>
                        <th>Hourly Rate</th>
                        <th>Weekly Rate</th>
                        <th>Mileage Limit</th>
                        <th>Extra Mileage Limit</th>
                        <th>Vehicle Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<VehicleDto> vehicles = (List<VehicleDto>) request.getAttribute("vehicles");
                        if (vehicles != null) {
                            for (VehicleDto vehicle : vehicles) {
                    %>
                    <tr>
                        <td><%= vehicle.getId() %>
                        </td>
                        <td><%= vehicle.getMake() %>
                        </td>
                        <td><%= vehicle.getModel() %>
                        </td>
                        <td><%= vehicle.getYear() %>
                        </td>
                        <td><%= vehicle.getType() %>
                        </td>
                        <td><%= vehicle.getLicensePlate() %>
                        </td>
                        <td><%= vehicle.getDailyRate() %>
                        </td>
                        <td><%= vehicle.getHourlyRate() %>
                        </td>
                        <td><%= vehicle.getWeeklyRate() %>
                        </td>
                        <td><%= vehicle.getMileageLimit() %>
                        </td>
                        <td><%= vehicle.getExtraMileageFee() %>
                        <td><span
                                class="badge badge-<%= vehicle.getStatus().equals("Available") ? "success" : vehicle.getStatus().equalsIgnoreCase("Rented") ? "pending" : vehicle.getStatus().equals("Maintenance") ? "danger" : "" %>"><%= vehicle.getStatus() %></span>
                        </td>
                        <td class="action-links">
                            <a href="javascript:void(0)" class="edit-link"
                               onclick="loadVehicleForEdit('<%= vehicle.getId() %>',
                                                   '<%= vehicle.getMake() %>',
                                                   '<%= vehicle.getModel() %>',
                                                   '<%= vehicle.getYear() %>',
                                                   '<%= vehicle.getType() %>',
                                                   '<%= vehicle.getLicensePlate() %>',
                                                   '<%= vehicle.getDailyRate() %>',
                                                   '<%= vehicle.getHourlyRate() %>',
                                                   '<%= vehicle.getWeeklyRate() %>',
                                                   '<%= vehicle.getMileageLimit() %>',
                                                   '<%= vehicle.getExtraMileageFee() %>',
                                                   '<%= vehicle.getStatus() %>',)">Edit</a>
                            <form action="vehicle/delete" method="post" onsubmit="return confirmDelete()"
                                  style="display:inline;">
                                <input type="hidden" name="id" value="<%= vehicle.getId() %>">
                                <button type="submit" class="delete-btn">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


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


    function loadVehicleForEdit(id, make, model, year, type, licensePlate, dailyRate, hourlyRate, weeklyRate, mileageLimit, extraMileageFee, status) {
    const form = document.querySelector('form');

    // If already in edit mode, just update fields
    if (form.getAttribute('data-mode') === 'edit') {
        document.getElementById('vehicleId').value = id;
    } else {
        // Change form action and mode
        form.action = 'vehicle/update';
        form.setAttribute('data-mode', 'edit');

        // Add hidden vehicle ID field if not exists
        let idField = document.getElementById('vehicleId');
        if (!idField) {
            idField = document.createElement('input');
            idField.type = 'hidden';
            idField.id = 'vehicleId';
            idField.name = 'id';
            form.appendChild(idField);
        }
        idField.value = id;

        // Change button text and title
        document.querySelector('button[type="submit"]').textContent = 'Update Vehicle';
        document.querySelector('.card-title').innerHTML = '<span class="card-title-icon">✏️</span> Edit Vehicle';
    }

    // Update form fields
    document.getElementById('make').value = make;
    document.getElementById('model').value = model;
    document.getElementById('year').value = year;
    document.getElementById('type').value = type;
    document.getElementById('licensePlate').value = licensePlate;
    document.getElementById('dailyRate').value = dailyRate;
    document.getElementById('hourlyRate').value = hourlyRate;
    document.getElementById('weeklyRate').value = weeklyRate;
    document.getElementById('mileageLimit').value = mileageLimit;
    document.getElementById('extraMileageFee').value = extraMileageFee;
    document.getElementById('status').value = status;

    // Scroll to the form smoothly
    document.querySelector('.card').scrollIntoView({ behavior: 'smooth' });
}

    // Function to reset the form back to "Add Vehicle" mode
    function resetForm() {
    const form = document.querySelector('form');

    form.reset(); // Clear form fields
    form.action = 'vehicle/add'; // Reset action
    form.setAttribute('data-mode', 'add'); // Set mode back to "add"

    document.querySelector('button[type="submit"]').textContent = 'Add Vehicle';
    document.querySelector('.card-title').innerHTML = '<span class="card-title-icon">➕</span> Add New Vehicle';

    // Remove the vehicle ID field if it exists
    const idField = document.getElementById('vehicleId');
    if (idField) {
        idField.remove();
    }
}

    // Add a reset button to the form
    document.addEventListener('DOMContentLoaded', function() {
    // Only target the vehicle form, not the logout form
    const vehicleForm = document.querySelector('form[action^="vehicle/"]');
    if (vehicleForm) {
        const submitBtn = vehicleForm.querySelector('button[type="submit"]');
        const resetBtn = document.createElement('button');
        resetBtn.type = 'button';
        resetBtn.className = 'btn btn-secondary';
        resetBtn.style.width = '100%';
        resetBtn.style.marginTop = '10px';
        resetBtn.textContent = 'Cancel';
        resetBtn.onclick = resetForm;

        submitBtn.parentNode.appendChild(resetBtn);
    }
});

    document.addEventListener("DOMContentLoaded", function () {
    const allForms = document.querySelectorAll("form");

    allForms.forEach(function (form) {
        form.addEventListener("submit", function () {
            document.getElementById("loading-overlay").style.display = "flex";
        });
    });
});
</script

</body>
</html>
