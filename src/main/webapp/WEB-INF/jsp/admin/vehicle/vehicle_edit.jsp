<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>

</script>

<div class="wrapper">

    <div class="row">
        <%@include file="vehicle_sidebar.jsp" %>

        <div id=main-wrapper class="col-sm-10">
            <div class="col-sm-8">
                <c:set var="idx" value="0" scope="page" />
                <form:form class="form-horizontal" modelAttribute="vehicle" action="/admin/vehicle/update" method="post">
                    <form:hidden path="id" />
                    <div class="row">
                        <h6>Select an existing make or enter in a new one</h6>
                        <form:input path="vehicleModel.vehicleMake.vehicleMakeName"></form:input>
                        <form:input path="vehicleModel.vehicleModelName"></form:input>
                    </div>
                    <div class="row">
                        <label for="vehicleYearInput">Vehicle Year</label>
                        <form:input path="vehicleYear" id="vehicleYearInput"></form:input>
                        <label for="licensePlateInput">Vehicle License Plate</label>
                        <form:input path="licensePlate" id="licensePlateInput"></form:input>
                        <label for="vinInput">Vehicle VIN</label>
                        <form:input path="VIN" id="vinInput"></form:input>
                        <label for="vehicleColorInput"> Vehicle Color</label>
                        <form:input path="color" id="vehicleColorInput"></form:input>
                        <label for="isPurchaseCheckbox">Vehicle Purchased?</label>
                        <form:checkbox path="isPurchase" id="isPurchaseCheckbox"></form:checkbox>
                        <label for="purchasePriceInput">Vehicle Purchase Price</label>
                        <form:input type="number" step="10" path="purchasePrice" id="purchasePriceInput"></form:input>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <form:button type="submit" value="save" class="btn btn-default">Update</form:button>
                        </div>
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