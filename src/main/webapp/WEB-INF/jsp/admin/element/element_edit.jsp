<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        $('.remove_button').click(function() {
            // console.log(this.name);
            // console.log($('#' + this.name).val());

            //set field to ""
            $('#' + this.name).val('');

            $('#elementType').submit();
        })
    })

</script>

<div class="wrapper">


        <%@include file="element_sidebar.jsp" %>

        <div class="col-sm-10 row">
            <div class="col-sm-8">
                <c:set var="idx" value="0" scope="page" />
                <form:form class="form-horizontal" modelAttribute="elementType" action="/admin/element/update" method="post">
                    <form:hidden path="id" />
                    <form:hidden path="version" />

                    <div class="row">
                        <div class="form-group">
                            <label for="inputElementTypeName" class="col-sm-2 form-control-label"> Element Type </label>
                            <div class="col-sm-12">
                                <form:input path="elementTypeName" type="text" id="inputElementTypeName" cssClass="form-control"></form:input>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <hr>
                        </div>
                    </div>


                    <c:forEach items="${elementType.elementList}" var="element">
                        <form:hidden path="elementList[${idx}].id" />
                        <form:hidden path="elementList[${idx}].version" />
                        <div class="row">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label" for="${idx}">Element</label>
                                </div>
                                <div class="col-sm-10">
                                    <div class="input-group">
                                            <form:input path="elementList[${idx}].elementName" id="${idx}" cssClass="form-control" />
                                            <span class="input-group-btn">
                                                <button name="${idx}" class="btn btn-default remove_button" type="button">Remove</button>
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var="idx" value="${idx + 1}" scope="page" />
                    </c:forEach>

                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-4 col-lg-3">
                                <label class="control-label" for="inputNewElement">Add New Element</label>
                            </div>
                            <div class="col-md-8 col-lg-9">
                                <input type="text" class="form-control" name="inputNewElement" id="inputNewElement">
                            </div>
                        </div>
                    </div>

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
                        <strong>Enter in a new element</strong>
                    </div>
                </div>
                <div class="${failureAlert == null ? 'hidden' : failureAlert}" id="failureAlert">
                    <div class="alert alert-dismissable alert-danger">
                        <button type="button" class="close" data-dismiss="alert">X</button>
                        <strong>Something Went Wrong!</strong>
                    </div>
                </div>
            </div>
        </div>
</div>


<%@include file="../../includes/footer.jsp" %>