<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        $("#successAlert").delay(3000).fadeOut(1500);
        $("#warningAlert").delay(8000).fadeOut(1500);
        $("#errorAlert").delay(5000).fadeOut(1500);
    });
</script>

<div class="wrapper">

    <div class="row">
        <%@include file="vehicle_sidebar.jsp" %>

        <div id=main-wrapper class="col-sm-10">
            <div class="col-sm-8">
                <c:set var="idx" value="0" scope="page" />
                <form:form class="form-horizontal" modelAttribute="vehicleMakeList" action="/admin/vehicle/add" method="post">
                    <c:forEach items="${vehicleMakeList}" var="vehicleMake">
                        <p>${vehicleMake.vehicleMakeName}</p>
                    </c:forEach>
                    <div class="row">
                        <button class="btn btn-primary">Update</button>
                    </div>
                </form:form>
            </div>
            <div class="col-sm-4">
                <div class="${successAlert == null ? 'hidden' : successAlert}" id="successAlert">
                    <div class="alert alert-dismissable alert-success">
                        <button type="button" class="close" data-dismiss="alert">X</button>
                        <strong>Success</strong>
                    </div>
                </div>
                <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="warningAlert">
                    <div class="alert alert-dismissable alert-warning">
                        <button type="button" class="close" data-dismiss="alert">X</button>
                        <strong>Enter in a new Vehicle Type, as well as individual child Elements, separated by a new line.</strong>
                    </div>
                </div>
                <div class="${errorAlert == null ? 'hidden' : errorAlert}" id="errorAlert">
                    <div class="alert alert-dismissable alert-danger">
                        <button type="button" class="close" data-dismiss="alert">X</button>
                        <strong>You need to enter in at least an Vehicle Type!</strong>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<%@include file="../../includes/footer.jsp" %>