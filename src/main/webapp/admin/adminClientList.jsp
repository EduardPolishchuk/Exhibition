<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>

<html>
<head>
    <title>Title</title>
    <jsp:include page="/common/windowstyle.jsp"/>

</head>
<body>
<jsp:include page="/common/header2.jsp"/>
<h2 class="display-3" style="color: aliceblue"><fmt:message key="loginToSystem"/></h2>
<div class="container justify-content-center w-50 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="display-4">Exhibition</h3>
                    <div class="mb-3 ">
                        <label class="form-label">${exhibition.imageUrl}</label>
                    </div>
                    <div class="mb-3 ">
                        <label class="form-label">Login</label>
                    </div>
                    <div class="mb-3 ">
                        <label class="form-label">Login</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Login</th>
        <th scope="col">Email</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        </tr>
    </c:forEach>
    <%--    --%>
    <%--    <tr>--%>
    <%--        <th scope="row">2</th>--%>
    <%--        <td>Jacob</td>--%>
    <%--        <td>Thornton</td>--%>
    <%--        <td>@fat</td>--%>
    <%--    </tr>--%>
    <%--    <tr>--%>
    <%--        <th scope="row">3</th>--%>
    <%--        <td colspan="2">Larry the Bird</td>--%>
    <%--        <td>@twitter</td>--%>
    <%--    </tr>--%>
    </tbody>
</table>
</body>
</html>
