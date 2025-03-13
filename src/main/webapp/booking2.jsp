<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
        import="java.util.List, java.util.Set, java.util.HashSet, lk.cab.manager.megacitycab.model.*, java.util.Locale" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Booking</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.13/flatpickr.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/bookingStyles.css">
    <style>
        .rate-info {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .calculation-section {
            background-color: #e9ecef;
            padding: 20px;
            border-radius: 5px;
            margin-top: 30px;
        }

        .total-price {
            font-size: 24px;
            font-weight: bold;
            color: #0d6efd;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #f1f1f1;
        }

        .logo {
            font-size: 24px;
            cursor: pointer;
        }

        .user-profile {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .user-avatar {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background-color: #0d6efd;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="logo" onclick="location.href='dashboard'">🚕 Mega City Cab</div>
    <div class="user-profile">
        <div>Welcome, <span class="welcome-name"><%= ((String) session.getAttribute("user")).toUpperCase() %></span>
        </div>
        <div class="user-avatar"><%= ((String) session.getAttribute("user")).toUpperCase(Locale.ROOT).charAt(0) %>
        </div>
        <button class="btn btn-danger" onclick="logout()">Logout</button>
    </div>
</div>

<div class="container mt-4">
    <h1 class="page-title">Customer Management</h1>
    <form id="bookingForm" action="saveBooking" method="post">
        <div class="row">
            <!-- Customer Selection -->
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-header"><h5>Customer Information</h5></div>
                    <div class="card-body">
                        <div class="mb-3">
                            <label for="customerId" class="form-label">Select Customer</label>
                            <select class="form-select" id="customerId" name="customerId" required>
                                <option value="">-- Select Customer --</option>
                                <% for (CustomerDto customer : (List<CustomerDto>) request.getAttribute("customers")) { %>
                                <option value="<%= customer.getId() %>"><%= customer.getName() %>
                                    - <%= customer.getMobile() %>
                                </option>
                                <% } %>
                            </select>
                        </div>
                        <div id="customerDetails" class="d-none">
                            <p><strong>Name:</strong> <span id="customerName"></span></p>
                            <p><strong>Address:</strong> <span id="customerAddress"></span></p>
                            <p><strong>Mobile:</strong> <span id="customerMobile"></span></p>
                            <p><strong>Email:</strong> <span id="customerEmail"></span></p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Driver Selection -->
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-header"><h5>Driver Information</h5></div>
                    <div class="card-body">
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" id="requireDriver" name="requireDriver">
                            <label class="form-check-label" for="requireDriver">Require a driver</label>
                        </div>
                        <div id="driverSelection" class="d-none">
                            <div class="mb-3">
                                <label for="driverId" class="form-label">Select Driver</label>
                                <select class="form-select" id="driverId" name="driverId">
                                    <option value="">-- Select Driver --</option>
                                    <% for (DriverDto driver : (List<DriverDto>) request.getAttribute("availableDrivers")) { %>
                                    <% if (driver.isAvailability()) { %>
                                    <option value="<%= driver.getId() %>"><%= driver.getName() %>
                                        - <%= driver.getMobile() %>
                                    </option>
                                    <% } %>
                                    <% } %>
                                </select>
                            </div>
                            <div id="driverDetails" class="d-none">
                                <p><strong>Name:</strong> <span id="driverName"></span></p>
                                <p><strong>License:</strong> <span id="driverLicense"></span></p>
                                <p><strong>Mobile:</strong> <span id="driverMobile"></span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Vehicle Selection -->
            <div class="col-md-12 mb-4">
                <div class="card">
                    <div class="card-header"><h5>Vehicle Selection</h5></div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="vehicleType" class="form-label">Vehicle Type</label>
                                    <select class="form-select" id="vehicleType" name="vehicleType">
                                        <option value="">All Types</option>
                                        <% Set<String> vehicleTypes = new HashSet<>();
                                            for (VehicleDto vehicle : (List<VehicleDto>) request.getAttribute("availableVehicles")) {
                                                vehicleTypes.add(vehicle.getType());
                                            }
                                            for (String type : vehicleTypes) { %>
                                        <option value="<%= type %>"><%= type %>
                                        </option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="mb-3">
                                    <label for="vehicleId" class="form-label">Select Vehicle</label>
                                    <select class="form-select" id="vehicleId" name="vehicleId" required>
                                        <option value="">-- Select Vehicle --</option>
                                        <% for (VehicleDto vehicle : (List<VehicleDto>) request.getAttribute("availableVehicles")) { %>
                                        <% if ("Available".equals(vehicle.getStatus())) { %>
                                        <option value="<%= vehicle.getId() %>"
                                                data-make="<%= vehicle.getMake() %>"
                                                data-model="<%= vehicle.getModel() %>"
                                                data-year="<%= vehicle.getYear() %>"
                                                data-type="<%= vehicle.getType() %>"
                                                data-plate="<%= vehicle.getLicensePlate() %>"
                                                data-daily="<%= vehicle.getDailyRate() %>"
                                                data-hourly="<%= vehicle.getHourlyRate() %>"
                                                data-weekly="<%= vehicle.getWeeklyRate() %>"
                                                data-mileagelimit="<%= vehicle.getMileageLimit() %>"
                                                data-extramileagefee="<%= vehicle.getExtraMileageFee() %>">
                                            <%= vehicle.getMake() %> <%= vehicle.getModel() %> (<%= vehicle.getYear() %>
                                            ) - <%= vehicle.getLicensePlate() %>
                                        </option>
                                        <% } %>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div id="vehicleDetails" class="rate-info d-none">
                            <div class="row">
                                <div class="col-md-6">
                                    <h6>Vehicle Information</h6>
                                    <p><strong>Make/Model:</strong> <span id="vehicleMakeModel"></span></p>
                                    <p><strong>Year:</strong> <span id="vehicleYear"></span></p>
                                    <p><strong>Type:</strong> <span id="vehicleTypeInfo"></span></p>
                                    <p><strong>License Plate:</strong> <span id="vehiclePlate"></span></p>
                                </div>
                                <div class="col-md-6">
                                    <h6>Rate Information</h6>
                                    <p><strong>Hourly Rate:</strong> LKR<span id="hourlyRate"></span></p>
                                    <p><strong>Daily Rate:</strong> LKR<span id="dailyRate"></span></p>
                                    <p><strong>Weekly Rate:</strong> LKR<span id="weeklyRate"></span></p>
                                    <p><strong>Mileage Limit:</strong> <span id="mileageLimit"></span> km</p>
                                    <p><strong>Extra Mileage Fee:</strong> LKR<span id="extraMileageFee"></span>/km</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Booking Details -->
            <div class="col-md-12 mb-4">
                <div class="card">
                    <div class="card-header"><h5>Booking Details</h5></div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="startDateTime" class="form-label">Start Date/Time</label>
                                    <input type="text" class="form-control" id="startDateTime" name="startDateTime"
                                           required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="endDateTime" class="form-label">End Date/Time</label>
                                    <input type="text" class="form-control" id="endDateTime" name="endDateTime"
                                           required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="rentalType" class="form-label">Rental Type</label>
                                    <select class="form-select" id="rentalType" name="rentalType" required>
                                        <option value="hourly">Hourly</option>
                                        <option value="daily" selected>Daily</option>
                                        <option value="weekly">Weekly</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="estimatedMileage" class="form-label">Estimated Mileage</label>
                                    <input type="number" class="form-control" id="estimatedMileage"
                                           name="estimatedMileage" min="0">
                                </div>
                            </div>
                        </div>
                        <div class="calculation-section">
                            <h5>Price Calculation</h5>
                            <div class="row">
                                <div class="col-md-6">
                                    <p><strong>Base Rate:</strong> LKR<span id="baseRateAmount">0.00</span></p>
                                    <p><strong>Duration:</strong> <span id="durationText">0 days</span></p>
                                    <p><strong>Extra Mileage Charge:</strong> LKR<span
                                            id="extraMileageCharge">0.00</span>
                                    </p>
                                    <p><strong>Driver Fee:</strong> LKR<span id="driverFee">0.00</span></p>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="discount" class="form-label">Discount (%)</label>
                                        <input type="number" class="form-control" id="discount" name="discount" min="0"
                                               max="100" value="0">
                                    </div>
                                    <div class="mb-3">
                                        <label for="additionalFees" class="form-label">Additional Fees</label>
                                        <input type="number" step="0.01" class="form-control" id="additionalFees"
                                               name="additionalFees" min="0" value="0">
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-6">
                                    <p class="total-price">Total: LKR<span id="totalPrice">0.00</span></p>
                                </div>
                                <div class="col-md-6 text-end">
                                    <input type="hidden" id="calculatedPrice" name="calculatedPrice" value="0">
                                    <button type="submit" class="btn btn-primary btn-lg">Create Booking</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.6.13/flatpickr.min.js"></script>
<script>
    $(document).ready(function() {
        // Initialize datetime pickers
        flatpickr("#startDateTime", { enableTime: true, dateFormat: "Y-m-d H:i", minDate: "today" });
        flatpickr("#endDateTime", { enableTime: true, dateFormat: "Y-m-d H:i", minDate: "today" });

        // Customer selection
        $("#customerId").change(function() {
            const selectedId = $(this).val();
            if (selectedId) {
                $.ajax({
                    url: "getCustomerDetails",
                    type: "GET",
                    data: { customerId: selectedId },
                    success: function(customer) {
                        $("#customerName").text(customer.name);
                        $("#customerAddress").text(customer.address);
                        $("#customerMobile").text(customer.mobile);
                        $("#customerEmail").text(customer.email);
                        $("#customerDetails").removeClass("d-none");
                    }
                });
            } else {
                $("#customerDetails").addClass("d-none");
            }
        });

        // Driver requirement toggle
        $("#requireDriver").change(function() {
            if ($(this).is(":checked")) {
                $("#driverSelection").removeClass("d-none");
                $("#driverId").prop("required", true);
            } else {
                $("#driverSelection").addClass("d-none");
                $("#driverDetails").addClass("d-none");
                $("#driverId").prop("required", false);
            }
            calculateTotal();
        });

        // Driver selection
        $("#driverId").change(function() {
            const selectedId = $(this).val();
            if (selectedId) {
                $.ajax({
                    url: "getDriverDetails",
                    type: "GET",
                    data: { driverId: selectedId },
                    success: function(driver) {
                        $("#driverName").text(driver.name);
                        $("#driverLicense").text(driver.drivingLicence);
                        $("#driverMobile").text(driver.mobile);
                        $("#driverDetails").removeClass("d-none");
                    }
                });
            } else {
                $("#driverDetails").addClass("d-none");
            }
            calculateTotal();
        });

        // Vehicle type filter
        $("#vehicleType").change(function() {
            const selectedType = $(this).val();
            $("#vehicleId option").show();
            if (selectedType) {
                $("#vehicleId option").each(function() {
                    if ($(this).data("type") !== selectedType && $(this).val() !== "") {
                        $(this).hide();
                    }
                });
            }
            $("#vehicleId").val("");
            $("#vehicleDetails").addClass("d-none");
            calculateTotal();
        });

        // Vehicle selection
        $("#vehicleId").change(function() {
            const selectedOption = $(this).find("option:selected");
            if (selectedOption.val()) {
                const vehicle = {
                    make: selectedOption.data("make"),
                    model: selectedOption.data("model"),
                    year: selectedOption.data("year"),
                    type: selectedOption.data("type"),
                    plate: selectedOption.data("plate"),
                    hourlyRate: selectedOption.data("hourly"),
                    dailyRate: selectedOption.data("daily"),
                    weeklyRate: selectedOption.data("weekly"),
                    mileageLimit: selectedOption.data("mileagelimit"),
                    extraMileageFee: selectedOption.data("extramileagefee")
                };

                $("#vehicleMakeModel").text(vehicle.make + " " + vehicle.model);
                $("#vehicleYear").text(vehicle.year);
                $("#vehicleTypeInfo").text(vehicle.type);
                $("#vehiclePlate").text(vehicle.plate);

                $("#hourlyRate").text(vehicle.hourlyRate);
                $("#dailyRate").text(vehicle.dailyRate);
                $("#weeklyRate").text(vehicle.weeklyRate);
                $("#mileageLimit").text(vehicle.mileageLimit);
                $("#extraMileageFee").text(vehicle.extraMileageFee);

                $("#vehicleDetails").removeClass("d-none");
            } else {
                $("#vehicleDetails").addClass("d-none");
            }
            calculateTotal();
        });

        // Rental dates change
        $("#startDateTime, #endDateTime").change(calculateTotal);

        // Other inputs change
        $("#rentalType, #estimatedMileage, #discount, #additionalFees").change(calculateTotal);
        $("#estimatedMileage, #discount, #additionalFees").on("input", calculateTotal);

        // Calculate total function
        function calculateTotal() {
            const selectedVehicle = $("#vehicleId option:selected");
            if (!selectedVehicle.val()) return;

            const hourlyRate = parseFloat(selectedVehicle.data("hourly")) || 0;
            const dailyRate = parseFloat(selectedVehicle.data("daily")) || 0;
            const weeklyRate = parseFloat(selectedVehicle.data("weekly")) || 0;
            const mileageLimit = parse            const extraMileageFee = parseFloat(selectedVehicle.data("extramileagefee")) || 0;

            // Get dates
            const startDate = new Date($("#startDateTime").val());
            const endDate = new Date($("#endDateTime").val());

            if (isNaN(startDate.getTime()) || isNaN(endDate.getTime())) {
                return;
            }

            // Calculate duration
            const diffTime = Math.abs(endDate - startDate);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
            const diffHours = Math.ceil(diffTime / (1000 * 60 * 60));
            const diffWeeks = Math.floor(diffDays / 7);
            const remainingDays = diffDays % 7;

            // Get rental type
            const rentalType = $("#rentalType").val();

            let baseRate = 0;
            let durationText = "";

            // Calculate base rate based on rental type
            if (rentalType === "hourly") {
                baseRate = hourlyRate * diffHours;
                durationText = diffHours + " hour" + (diffHours !== 1 ? "s" : "");
            } else if (rentalType === "daily") {
                baseRate = dailyRate * diffDays;
                durationText = diffDays + " day" + (diffDays !== 1 ? "s" : "");
            } else if (rentalType === "weekly") {
                baseRate = (weeklyRate * diffWeeks) + (dailyRate * remainingDays);
                durationText = diffWeeks + " week" + (diffWeeks !== 1 ? "s" : "");
                if (remainingDays > 0) {
                    durationText += " and " + remainingDays + " day" + (remainingDays !== 1 ? "s" : "");
                }
            }

            // Calculate extra mileage charge
            const estimatedMileage = parseFloat($("#estimatedMileage").val()) || 0;
            let extraMileageCharge = 0;
            if (estimatedMileage > mileageLimit) {
                extraMileageCharge = (estimatedMileage - mileageLimit) * extraMileageFee;
            }

            // Calculate driver fee (simplified - in real app would be more complex)
            let driverFee = 0;
            if ($("#requireDriver").is(":checked") && $("#driverId").val()) {
                // Example: $20 per day for driver
                driverFee = 20 * diffDays;
            }

            // Calculate discount
            const discount = parseFloat($("#discount").val()) || 0;
            const discountAmount = (baseRate * discount) / 100;

            // Additional fees
            const additionalFees = parseFloat($("#additionalFees").val()) || 0;

            // Calculate total
            const totalPrice = baseRate + extraMileageCharge + driverFee + additionalFees - discountAmount;

            // Update UI
            $("#baseRateAmount").text(baseRate.toFixed(2));
            $("#durationText").text(durationText);
            $("#extraMileageCharge").text(extraMileageCharge.toFixed(2));
            $("#driverFee").text(driverFee.toFixed(2));
            $("#totalPrice").text(totalPrice.toFixed(2));

            // Set hidden field for form submission
            $("#calculatedPrice").val(totalPrice.toFixed(2));
        }

        // Form validation
        $("#bookingForm").submit(function(event) {
            const startDate = new Date($("#startDateTime").val());
            const endDate = new Date($("#endDateTime").val());

            if (endDate <= startDate) {
                alert("End date must be after start date");
                event.preventDefault();
                return false;
            }

            if ($("#requireDriver").is(":checked") && !$("#driverId").val()) {
                alert("Please select a driver");
                event.preventDefault();
                return false;
            }

            return true;
        });
    });
</script>
</body>
</html>