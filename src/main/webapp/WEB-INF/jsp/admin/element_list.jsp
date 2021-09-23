<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<%@include file="../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%@include file="element_sidebar.jsp" %>

    <div id="main-wrapper" class="col-md-11">
        <div class="col-lg-8">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Element Type</th>
                        <th>Elements</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="elementType" items="${elementTypeList}">
                        <tr>
                            <td>${elementType.id}</td>
                            <td>${elementType.elementTypeName}</td>
                            <td>Elements go here....</td>
                            <td>Edit</td>
                            <td>Delete</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<%@include file="../includes/footer.jsp" %>