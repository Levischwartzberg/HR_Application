<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%@include file="element_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <c:set var="idx" value="0" scope="page" />
        <div class="col-sm-12">
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
                            <td>
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        View Elements
                                    </button>
                                    <div class="dropdown-menu">
                                        <c:forEach items="${elementType.elementList}" var="element">
                                            <p class="dropdown-item"> ${element.elementName} </p>
                                            <c:set var="idx" value="${idx + 1}" scope="page" />
                                        </c:forEach>
                                    </div>
                                </div>

                            </td>
                            <td><a href="/admin/element/edit/${elementType.id}"> Edit </a></td>
                            <td><a href="/admin/element/delete/${elementType.id}"> Delete </a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp" %>