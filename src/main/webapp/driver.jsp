<%@ page import="lk.cab.manager.megacitycab.model.DriverDto" %>
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
    <title>Mega City Cab - Driver Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%-- Link to the external CSS file --%>
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
        <h1 class="page-title">Driver Management</h1>

        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-value">${pageContext.request.getAttribute("total")}</div>
                <div class="stat-label">Total Drivers</div>
            </div>
            <div class="stat-card">
                <div class="stat-value">${pageContext.request.getAttribute("available")}</div>
                <div class="stat-label">Total Available Drivers</div>
            </div>
            <%--<div class="stat-card">
                <div class="stat-value">42</div>
                <div class="stat-label">New This Month</div>
            </div>--%>
        </div>

        <%
            // Get the driver to edit (if any)
            DriverDto driverToEdit = (DriverDto) request.getAttribute("driverToEdit");
            boolean isEditMode = driverToEdit != null;

            // Set form action based on mode
            String formAction = isEditMode ? "driver/update" : "driver/add";
            String formTitle = isEditMode ? "Edit Driver" : "Add New Driver";
            String buttonText = isEditMode ? "Update Driver" : "Add Driver";
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
                            <label for="name" class="form-label">Name</label>
                            <input type="text" id="name" name="name" class="form-control"
                                   placeholder="Name" required
                                   value="<%= isEditMode ? driverToEdit.getName() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="drivingLicence" class="form-label">Driving Licence Number</label>
                            <input type="text" id="drivingLicence" name="drivingLicence" class="form-control"
                                   placeholder="Driving Licence Number" required
                                   value="<%= isEditMode ? driverToEdit.getDrivingLicense() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="nic" class="form-label">NIC</label>
                            <input type="text" id="nic" name="nic" class="form-control" placeholder="NIC" required
                                   value="<%= isEditMode ? driverToEdit.getNic() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="dob" class="form-label">Date of Birth</label>
                            <input type="date" id="dob" name="dob" class="form-control" required
                                   value="<%= isEditMode ? driverToEdit.getDob() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="availability" class="form-label">Availability</label>
                            <select id="availability" name="availability" class="form-control" required>
                                <option value="true" <%= isEditMode && driverToEdit.isAvailability() ? "selected" : "" %>>Available</option>
                                <option value="false" <%= isEditMode && !driverToEdit.isAvailability() ? "selected" : "" %>>Unavailable</option>
                            </select>
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" id="address" name="address" class="form-control" placeholder="Address"
                                   required
                                   value="<%= isEditMode ? driverToEdit.getAddress() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="mobile" class="form-label">Mobile No</label>
                            <input type="text" id="mobile" name="mobile" class="form-control"
                                   placeholder="Mobile No" required
                                   value="<%= isEditMode ? driverToEdit.getMobile() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" id="email" name="email" class="form-control" placeholder="Email Address"
                                   required
                                   value="<%= isEditMode ? driverToEdit.getEmail() : "" %>">
                        </div>
                        <div class="form-group" style="margin-top: 33px;">
                            <button type="submit" class="btn btn-primary" style="width: 100%;"><%= buttonText %>
                            </button>
                            <% if (isEditMode) { %>
                            <a href="DriverManagementServlet" class="btn btn-secondary"
                               style="width: 100%; margin-top: 10px;">Cancel</a>
                            <% } %>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="card">
            <div class="card-title">
                <span class="card-title-icon">👮‍♀️</span> Driver List
            </div>
            <div class="table-responsive">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>NIC</th>
                        <th>Licence No</th>
                        <th>Mobile</th>
                        <th>Email</th>
                        <th>DOB</th>
                        <th>Availability</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<DriverDto> drivers = (List<DriverDto>) request.getAttribute("drivers");
                        if (drivers != null) {
                            for (DriverDto driver : drivers) {
                    %>
                    <tr>
                        <td><%= driver.getId() %>
                        </td>
                        <td><%= driver.getName() %>
                        </td>
                        <td><%= driver.getAddress() %>
                        </td>
                        <td><%= driver.getNic() %>
                        </td>
                        <td><%= driver.getDrivingLicense() %>
                        </td>
                        <td><%= driver.getMobile() %>
                        </td>
                        <td><%= driver.getEmail() %>
                        </td>
                        <td><%= driver.getDob() %>
                        </td>
                        <td><span
                                class="badge badge-<%= driver.isAvailability() ? "success" : "danger" %>"><%= driver.isAvailability() ? "Available" : "Unavailable" %></span>
                        </td>
                        </td>
                        <td class="action-links">
                            <a href="javascript:void(0)" class="edit-link"
                               onclick="loadDriverForEdit('<%= driver.getId() %>',
                                                   '<%= driver.getName() %>',
                                                   '<%= driver.getAddress() %>',
                                                   '<%= driver.getNic() %>',
                                                   '<%= driver.getMobile() %>',
                                                   '<%= driver.getEmail() %>',
                                                   '<%= driver.getDob() %>',
                                                   '<%= driver.getDrivingLicense() %>',
                                                   '<%= driver.isAvailability() %>')">Edit</a>
                            <%--<a href="driver/delete?id=<%= driver.getId() %>" class="delete-link"
                               onclick="return confirm('Are you sure you want to delete this driver?')">Delete</a>--%>
                            <form action="driver/delete" method="post" onsubmit="return confirmDelete()"
                                  style="display:inline;">
                                <input type="hidden" name="id" value="<%= driver.getId() %>">
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

    function loadDriverForEdit(id, name, address, nic, mobile, email, dob, drivingLicence,availability) {
    const form = document.querySelector('form');

    // If already in edit mode, just update fields
    if (form.getAttribute('data-mode') === 'edit') {
        document.getElementById('driverId').value = id;
    } else {
        // Change form action and mode
        form.action = 'driver/update';
        form.setAttribute('data-mode', 'edit');

        // Add hidden driver ID field if not exists
        let idField = document.getElementById('driverId');
        if (!idField) {
            idField = document.createElement('input');
            idField.type = 'hidden';
            idField.id = 'driverId';
            idField.name = 'id';
            form.appendChild(idField);
        }
        idField.value = id;

        // Change button text and title
        document.querySelector('button[type="submit"]').textContent = 'Update Driver';
        document.querySelector('.card-title').innerHTML = '<span class="card-title-icon">✏️</span> Edit Driver';
    }

    // Update form fields
    document.getElementById('name').value = name;
    <%--    document.getElementById('lastName').value = nameParts.slice(1).join(' ');--%>
    document.getElementById('nic').value = nic;
    document.getElementById('dob').value = dob;
    document.getElementById('address').value = address;
    document.getElementById('mobile').value = mobile;
    document.getElementById('email').value = email;
    document.getElementById('drivingLicence').value = drivingLicence;
    document.getElementById('availability').value = availability;

    // Scroll to the form smoothly
    document.querySelector('.card').scrollIntoView({ behavior: 'smooth' });
}

    // Function to reset the form back to "Add Driver" mode
    function resetForm() {
    const form = document.querySelector('form');

    form.reset(); // Clear form fields
    form.action = 'driver/add'; // Reset action
    form.setAttribute('data-mode', 'add'); // Set mode back to "add"

    document.querySelector('button[type="submit"]').textContent = 'Add Driver';
    document.querySelector('.card-title').innerHTML = '<span class="card-title-icon">➕</span> Add New Driver';

    // Remove the driver ID field if it exists
    const idField = document.getElementById('driverId');
    if (idField) {
        idField.remove();
    }
}

    // Add a reset button to the form
    document.addEventListener('DOMContentLoaded', function() {
        const submitBtn = document.querySelector('button[type="submit"]');
        const resetBtn = document.createElement('button');
        resetBtn.type = 'button';
        resetBtn.className = 'btn btn-secondary';
        resetBtn.style.width = '100%';
        resetBtn.style.marginTop = '10px';
        resetBtn.textContent = 'Cancel';
        resetBtn.onclick = resetForm;

        submitBtn.parentNode.appendChild(resetBtn);
    });

    document.addEventListener("DOMContentLoaded", function () {
    const allForms = document.querySelectorAll("form");

    allForms.forEach(function (form) {
        form.addEventListener("submit", function () {
            document.getElementById("loading-overlay").style.display = "flex";
        });
    });
});

</script>

</body>
</html>