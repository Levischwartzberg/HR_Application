<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        $("#successAlert").delay(3000).fadeOut(1500);
        $("#warningAlert").delay(8000).fadeOut(1500);
        $("#errorAlert").delay(5000).fadeOut(1500);
        console.log($('#vehicleMakeSelect').val());
    });

</script>

<div class="wrapper">

    <div class="row">
        <%@include file="vehicle_sidebar.jsp" %>

        <div id=main-wrapper class="col-sm-10">
            <div class="col-sm-8">
                <c:set var="idx" value="0" scope="page" />
                <form:form class="form-horizontal" modelAttribute="vehicleVO" action="/admin/vehicle/add" method="post">
                    <div class="row">
                        <label for="vehicleMakeSelect">Vehicle Make</label>
                        <form:select path="vehicleMake" cssClass="form-control" id="vehicleMakeSelect" name="vehicleMake">
                            <c:forEach items="${vehicleVO.vehicleMakeList}" var="vehicleMake">
                                <option value="${vehicleMake.vehicleMakeName}">${vehicleMake.vehicleMakeName}</option>
                            </c:forEach>
                        </form:select>
                        <form:input path="newVehicleMake" cssClass="form-control"></form:input>

                        <label for="vehicleModelSelect">Vehicle Model</label>
                        <form:select path="vehicleModel" cssClass="form-control" id="vehicleModelSelect" name="vehicleModel">
                            <c:forEach items="${vehicleVO.vehicleModelList}" var="vehicleModel">
                                <option value="${vehicleModel.vehicleModelName}">${vehicleModel.vehicleModelName}</option>
                            </c:forEach>
                        </form:select>
                        <form:input path="newVehicleModel" cssClass="form-control"></form:input>
                    </div>
                    <div class="row">
                        <label for="vehicleYearInput">Vehicle Year</label>
                        <form:input path="newVehicleYear" id="vehicleYearInput" cssClass="form-control"></form:input>
                        <label for="licensePlateInput">Vehicle License Plate</label>
                        <form:input path="newLicensePlate" id="licensePlateInput" cssClass="form-control"></form:input>
                        <label for="vinInput">Vehicle VIN</label>
                        <form:input path="newVIN" id="vinInput" cssClass="form-control"></form:input>
                        <label for="vehicleColorInput"> Vehicle Color</label>
                        <form:input path="newVehicleColor" id="vehicleColorInput" cssClass="form-control"></form:input>
                        <label for="isPurchaseCheckbox">Vehicle Purchased?</label>
                        <form:checkbox path="newIsPurchase" id="isPurchaseCheckbox" cssClass="form-check"></form:checkbox>
                        <br/>
                        <label for="purchasePriceInput">Vehicle Purchase Price</label>
                        <form:input type="number" step="10" path="newPurchasePrice" id="purchasePriceInput" cssClass="form-control"></form:input>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
                            <form:button type="submit" value="save" class="btn btn-default">Save</form:button>
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