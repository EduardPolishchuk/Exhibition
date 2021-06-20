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
    <title>Exhibition</title>
    <jsp:include page="/common/windowstyle.jsp"/>

</head>
<body>
<jsp:include page="/common/header2.jsp"/>
<div class="container justify-content-center w-100 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3 mb-6">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="display-4">${exhibition.theme}</h3>

                    <div class="mb-5">
                        <img class="card-img-top" src=${exhibition.imageUrl} alt="Picture"
                             style="max-height: 360px; max-width: 600px">
                        <label class="form-label">${exhibition.date}</label>
                        <input type="text" class="form-control w-25" name="email" value="${exhibition.themeUk}" disabled>
                    </div>
                    <div class="mb-3 ">
                        <label class="form-label">${exhibition.date}</label>
                    </div>
                    <div class="mb-3 ">
                        <label class="form-label">${exhibition.halls}</label>
                    </div>
                    <div class="mb-3 ">
                        <c:forEach var="hall" items="${exhibition.halls}">

                        </c:forEach>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" ${not empty red? 'checked':''}>
                        <label class="form-check-label" for="flexCheckDefault">
                            Red
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Blue
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked2" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Green
                        </label>
                    </div>


                </div>
            </div>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="display-4">Visitors</h3>
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
                            <td><a href="#" style="color: black"><strong>${user.login}</strong></a></td>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                        </tr>
                        </c:forEach>

                </div>
            </div>
        </div>
    </div>
</div>
<%--<div class="container justify-content-center w-100 mb-5">--%>
<%--    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">--%>
<%--        <div class="col ">--%>
<%--            <div class="card shadow-sm">--%>
<%--                <div class="card-body">--%>
<%--                    <h3 class="display-4">Visitors</h3>--%>
<%--                    <table class="table">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th scope="col">Login</th>--%>
<%--                            <th scope="col">Email</th>--%>
<%--                            <th scope="col">First Name</th>--%>
<%--                            <th scope="col">Last Name</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                        <c:forEach var="user" items="${userList}">--%>
<%--                        <tr>--%>
<%--                            <td><a href="#" style="color: black"><strong>${user.login}</strong></a></td>--%>
<%--                            <td>${user.email}</td>--%>
<%--                            <td>${user.firstName}</td>--%>
<%--                            <td>${user.lastName}</td>--%>
<%--                        </tr>--%>
<%--                        </c:forEach>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>
