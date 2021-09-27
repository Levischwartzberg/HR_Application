<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <c:set var="idx" value="0" scope="page" />
        <div class="col-sm-12">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Vehicle Make</th>
                    <th>Vehicle Model</th>
                    <th>Vehicle Year</th>
                    <th>License Plate</th>
                    <th>VIN</th>
                    <th>Color</th>
                    <th>Purchase Price</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vehicle" items="${vehicleList}">
                    <tr>
                        <td>${vehicle.id}</td>
                        <td>${vehicle.vehicleModel.vehicleMake.vehicleMakeName}</td>
                        <td>
                            ${vehicle.vehicleModel.vehicleModelName}
                        </td>
                        <td>${vehicle.vehicleYear}</td>
                        <td>${vehicle.licensePlate}</td>
                        <td>${vehicle.VIN}</td>
                        <td>${vehicle.color}</td>
                        <td>${vehicle.purchasePrice}</td>
                        <td><a href="/admin/vehicle/edit/${vehicle.id}"> Edit </a></td>
                        <td><a href="/admin/vehicle/delete/${vehicle.id}"> Delete </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp" %>