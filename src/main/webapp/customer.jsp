<%@ page import="lk.cab.manager.megacitycab.model.CustomerDto" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Mega City Cab - Customer Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%-- Link to the external CSS file --%>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/styles.css">
</head>
<body>

<div class="header">
    <img src="<%= request.getContextPath() %>/resources/img/logo.png" alt="Mega City Cab Logo"
         style="width: 150px; height: auto;">
    <h2>Welcome, <span class="welcome-name"><%= ((String) session.getAttribute("user")).toUpperCase()%></span>!</h2>
</div>

<div class="main-container">
    <div class="content">
        <h1 class="page-title">Customer Management</h1>

        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-value">${pageContext.request.getAttribute("total")}</div>
                <div class="stat-label">Total Customers</div>
            </div>
            <div class="stat-card">
                <div class="stat-value">42</div>
                <div class="stat-label">New This Month</div>
            </div>
        </div>

        <%
            // Get the customer to edit (if any)
            CustomerDto customerToEdit = (CustomerDto) request.getAttribute("customerToEdit");
            boolean isEditMode = customerToEdit != null;

            // Set form action based on mode
            String formAction = isEditMode ? "UpdateCustomerServlet" : "AddCustomerServlet";
            String formTitle = isEditMode ? "Edit Customer" : "Add New Customer";
            String buttonText = isEditMode ? "Update Customer" : "Add Customer";
            String buttonIcon = isEditMode ? "✏️" : "➕";
        %>

        <div class="card">
            <div class="card-title">
                <span class="card-title-icon"><%= buttonIcon %></span> <%= formTitle %>
            </div>
            <form action="<%= formAction %>" method="post" data-mode="<%= isEditMode ? "edit" : "add" %>">
                <div class="form-grid">
                    <div>
                        <div class="form-group">
                            <label for="firstName" class="form-label">First Name</label>
                            <input type="text" id="firstName" name="firstName" class="form-control"
                                   placeholder="First Name" required
                                   value="<%= isEditMode ? customerToEdit.getName().split(" ")[0] : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" id="lastName" name="lastName" class="form-control"
                                   placeholder="Last Name" required
                                   value="<%= isEditMode ? (customerToEdit.getName().split(" ").length > 1 ? customerToEdit.getName().substring(customerToEdit.getName().indexOf(" ") + 1) : "") : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="nic" class="form-label">NIC</label>
                            <input type="text" id="nic" name="nic" class="form-control" placeholder="NIC" required
                                   value="<%= isEditMode ? customerToEdit.getNic() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="dob" class="form-label">Date of Birth</label>
                            <input type="date" id="dob" name="dob" class="form-control" required
                                   value="<%= isEditMode ? customerToEdit.getDob() : "" %>">
                        </div>
                    </div>
                    <div>
                        <div class="form-group">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" id="address" name="address" class="form-control" placeholder="Address"
                                   required
                                   value="<%= isEditMode ? customerToEdit.getAddress() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="mobileNo" class="form-label">Mobile No</label>
                            <input type="text" id="mobileNo" name="mobileNo" class="form-control"
                                   placeholder="Mobile No" required
                                   value="<%= isEditMode ? customerToEdit.getMobile() : "" %>">
                        </div>
                        <div class="form-group">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" id="email" name="email" class="form-control" placeholder="Email Address"
                                   required
                                   value="<%= isEditMode ? customerToEdit.getEmail() : "" %>">
                        </div>
                        <div class="form-group" style="margin-top: 33px;">
                            <button type="submit" class="btn btn-primary" style="width: 100%;"><%= buttonText %>
                            </button>
                            <% if (isEditMode) { %>
                            <a href="CustomerManagementServlet" class="btn btn-secondary"
                               style="width: 100%; margin-top: 10px;">Cancel</a>
                            <% } %>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="card">
            <div class="card-title">
                <span class="card-title-icon">👥</span> Customer List
            </div>
            <div class="table-responsive">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>NIC</th>
                        <th>Mobile</th>
                        <th>Email</th>
                        <th>DOB</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<CustomerDto> customers = (List<CustomerDto>) request.getAttribute("customers");
                        if (customers != null) {
                            for (CustomerDto customer : customers) {
                    %>
                    <tr>
                        <td><%= customer.getId() %>
                        </td>
                        <td><%= customer.getName() %>
                        </td>
                        <td><%= customer.getAddress() %>
                        </td>
                        <td><%= customer.getNic() %>
                        </td>
                        <td><%= customer.getMobile() %>
                        </td>
                        <td><%= customer.getEmail() %>
                        </td>
                        <td><%= customer.getDob() %>
                        </td>
                        <td class="action-links">
                            <a href="javascript:void(0)" class="edit-link"
                               onclick="loadCustomerForEdit('<%= customer.getId() %>',
                                                   '<%= customer.getName() %>',
                                                   '<%= customer.getAddress() %>',
                                                   '<%= customer.getNic() %>',
                                                   '<%= customer.getMobile() %>',
                                                   '<%= customer.getEmail() %>',
                                                   '<%= customer.getDob() %>')">Edit</a>
                            <a href="DeleteCustomerServlet?id=<%= customer.getId() %>" class="delete-link"
                               onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
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

    function loadCustomerForEdit(id, name, address, nic, mobile, email, dob) {
    const form = document.querySelector('form');

    // If already in edit mode, just update fields
    if (form.getAttribute('data-mode') === 'edit') {
        document.getElementById('customerId').value = id;
    } else {
        // Change form action and mode
        form.action = 'UpdateCustomerServlet';
        form.setAttribute('data-mode', 'edit');

        // Add hidden customer ID field if not exists
        let idField = document.getElementById('customerId');
        if (!idField) {
            idField = document.createElement('input');
            idField.type = 'hidden';
            idField.id = 'customerId';
            idField.name = 'id';
            form.appendChild(idField);
        }
        idField.value = id;

        // Change button text and title
        document.querySelector('button[type="submit"]').textContent = 'Update Customer';
        document.querySelector('.card-title').innerHTML = '<span class="card-title-icon">✏️</span> Edit Customer';
    }

    // Update form fields
    const nameParts = name.split(' ');
    document.getElementById('firstName').value = nameParts[0];
    document.getElementById('lastName').value = nameParts.slice(1).join(' ');
    document.getElementById('nic').value = nic;
    document.getElementById('dob').value = dob;
    document.getElementById('address').value = address;
    document.getElementById('mobileNo').value = mobile;
    document.getElementById('email').value = email;

    // Scroll to the form smoothly
    document.querySelector('.card').scrollIntoView({ behavior: 'smooth' });
}

    // Function to reset the form back to "Add Customer" mode
    function resetForm() {
    const form = document.querySelector('form');

    form.reset(); // Clear form fields
    form.action = 'AddCustomerServlet'; // Reset action
    form.setAttribute('data-mode', 'add'); // Set mode back to "add"

    document.querySelector('button[type="submit"]').textContent = 'Add Customer';
    document.querySelector('.card-title').innerHTML = '<span class="card-title-icon">➕</span> Add New Customer';

    // Remove the customer ID field if it exists
    const idField = document.getElementById('customerId');
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
</script>

</body>
</html>