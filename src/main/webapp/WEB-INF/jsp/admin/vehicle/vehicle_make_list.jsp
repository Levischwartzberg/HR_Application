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
                    <th>Vehicle Models</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                    <tr>
                        <td>${vehicleMake.id}</td>
                        <td>${vehicleMake.vehicleMakeName}</td>
                        <td>
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    View Models
                                </button>
                                <div class="dropdown-menu">
                                    <c:forEach items="${vehicleMake.vehicleModelList}" var="vehicleModel">
                                        <p class="dropdown-item"> ${vehicleModel.vehicleModelName} </p>
                                        <c:set var="idx" value="${idx + 1}" scope="page" />
                                    </c:forEach>
                                </div>
                            </div>
                        </td>
                        <td><a href="/admin/vehiclemake/edit/${vehicleMake.id}"> Edit </a></td>
                        <td><a href="/admin/vehiclemake/delete/${vehicleMake.id}"> Delete </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp" %>