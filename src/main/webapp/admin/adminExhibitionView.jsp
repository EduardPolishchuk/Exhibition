<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<c:set var="vari" value="${not empty param.edit ? null : 'disabled'}" scope="session"/>

<html>
<head>
    <title>Exhibition</title>
    <jsp:include page="/common/windowstyle.jsp"/>

</head>
<body>
<jsp:include page="/common/header2.jsp"/>
<div class="container justify-content-center w-100 bg-light">
    <div class="row row-cols-2 row-cols-sm-2 row-cols-md-2 g-3 mb-6">
        <div class="col ">
            <div class="card shadow-sm" mb-5>
                <div class="card-body" >
                    <h3 class="display-4">${exhibition.theme}</h3>
                    <div class="">
                        <img class="card-img-top" src=${exhibition.imageUrl} alt="Picture"
                             style="max-height: 360px; max-width: 600px">
                    </div>
                </div>
            </div>
            <hr>
            <form action="${pageContext.request.requestURI}" >
                <c:forEach var="item" items="${param}">
                    <c:if test="${item.key != 'edit'}">
                    <input type="hidden" name="${item.key}" value="${item.value}">
                    </c:if>
                </c:forEach>
                <button type="submit" class="btn btn-dark" name="edit" value="${vari != null  ? '1' : ''}">Edit
                </button>
            </form>
            <form action="${pageContext.request.contextPath}/admin/adminClientList">
                <button type="submit" class="btn btn-sm btn-outline-secondary"
                        name="exId" value="${param.exId}">Visitors</button>
            </form>
        </div>
        <div class="col">
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col">
                        <label class="form-label">Theme</label>
                        <input type="text" class="form-control " name="email" value="${exhibition.theme}"
                        ${vari}>
                    </div>
                    <div class="col">
                        <label class="form-label">Theme Uk</label>
                        <input type="text" class="form-control " name="email" value="${exhibition.themeUk}"
                        ${vari}>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col">
                        <label class="form-label">Date</label>
                        <input type="text" class="form-control " name="email" value="${exhibition.date}"
                        ${vari}>
                    </div>
                    <div class="col">
                        <label class="form-label">Price</label>
                        <input type="text" class="form-control " name="email" value="${exhibition.price}"
                        ${vari}>
                    </div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Description</label>
                    <input type="text" class="form-control  p-5" name="email" value="${exhibition.description}"
                    ${vari}>
                </div>
                <div class="mb-3">
                    <label class="form-label">Description Uk</label>
                    <input type="text" class="form-control  p-5" name="email" value="${exhibition.descriptionUk}"
                    ${vari}>
                </div>
                <div class="mb-3">
                    <label class="form-label">Image Url</label>
                    <input type="text" class="form-control " name="email" value="${exhibition.imageUrl}"
                    ${vari}>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1" ${vari}>
                    <label class="form-check-label" for="inlineCheckbox1">RED</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2" ${vari}>
                    <label class="form-check-label" for="inlineCheckbox2">GREEN</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" ${vari}>
                    <label class="form-check-label" for="inlineCheckbox3">BLUE</label>
                </div>
            </div>
        </div>
    </div>

    <%--    <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-3 mb-6">--%>
    <%--        <hr>--%>
    <%--        <div class="col ">--%>
    <%--            <h3 class="display-4">Visitors</h3>--%>
    <%--            <table class="table">--%>
    <%--                <thead>--%>
    <%--                <tr>--%>
    <%--                    <th scope="col">Login</th>--%>
    <%--                    <th scope="col">Email</th>--%>
    <%--                    <th scope="col">First Name</th>--%>
    <%--                    <th scope="col">Last Name</th>--%>
    <%--                </tr>--%>
    <%--                </thead>--%>
    <%--                <tbody>--%>
    <%--                <c:forEach var="user" items="${userList}">--%>
    <%--                <tr>--%>
    <%--                    <td><a href="#" style="color: black"><strong>${user.login}</strong></a></td>--%>
    <%--                    <td>${user.email}</td>--%>
    <%--                    <td>${user.firstName}</td>--%>
    <%--                    <td>${user.lastName}</td>--%>
    <%--                </tr>--%>
    <%--                </c:forEach>--%>

    <%--        </div>--%>

    <%--    </div>--%>
</div>
<hr>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
