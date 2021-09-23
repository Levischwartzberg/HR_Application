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
        <%@include file="element_sidebar.jsp" %>

        <div id=main-wrapper class="col-sm-10">
            <div class="col-sm-8">
                <form:form cssClass="form=horizontal" modelAttribute="elementVO" action="/admin/element/add" method="post">
                    <fieldset>
                        <legend>Element Management</legend>
                        <div class="form_group">
                            <label for="inputNewElementType" class="col-lg-2 control-label"> Element Type </label>
                            <div class="col-lg-10">
                                <form:input path="newElementType" type="text" class="form-control" id="inputNewElementType" placeholder="Element Type"></form:input>
                            </div>
                        </div>
                        <div class="form_group">
                            <label for="inputNewElements" class="col-lg-2 control-label"> Elements </label>
                            <div class="col-lg-10">
                                <form:textarea path="newElements" type="text" class="form-control" rows="3" id="inputNewElements"></form:textarea>
                                <span class="help-block">Enter each new Element on a new line.</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-10">
                                <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
                                <form:button type="submit" value="save" class="btn btn-default">Save</form:button>
                            </div>
                        </div>
                    </fieldset>
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
                        <strong>Enter in a new Element Type, as well as individual child Elements, separated by a new line.</strong>
                    </div>
                </div>
                <div class="${errorAlert == null ? 'hidden' : errorAlert}" id="errorAlert">
                    <div class="alert alert-dismissable alert-danger">
                        <button type="button" class="close" data-dismiss="alert">X</button>
                        <strong>You need to enter in at least an Element Type!</strong>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<%@include file="../../includes/footer.jsp" %>