<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<%@include file="../includes/subnav_admin.jsp" %>

<div class="container">

    <%@include file="element_sidebar.jsp" %>

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
</div>

<%@include file="../includes/footer.jsp" %>