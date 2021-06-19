<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>

<header class="p-3 bg-black text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="60" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/" class="nav-link px-1 text-secondary">Home</a></li>
                <c:choose>
                    <c:when test="${role == 'USER'}">
                        <li><a href="${pageContext.request.contextPath}/user/userevents" class="nav-link px-2 text-white">My Events</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/userprofile.jsp" class="nav-link px-2 text-white">My Profile</a></li>
                    </c:when>
                    <c:when test="${role == 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/admin/adminbasis.jsp" class="nav-link px-2 text-white">Admin Main</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/adminClientList" class="nav-link px-2 text-white">Client List</a></li>
                    </c:when>
                </c:choose>
            </ul>
            <form action="${pageContext.request.contextPath}/search" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                <c:set var="searchLocale" value="${language != 'uk'? 'Search...':'Пошук...'}"/>
                <input type="search" class="form-control form-control-dark" name="search" placeholder="${searchLocale}" aria-label="Search">
            </form>
            <c:choose>
                <c:when test="${role == 'USER' || role == 'ADMIN'}">
                    <h5 class="display-6" style="color: aliceblue">${userProfile.login}
                        <a href="">${userProfile.balance}</a>
                    </h5>
                    <br>
                    <form action="${pageContext.request.contextPath}/logout"
                          class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-0">
                        <button type="submit" class="btn btn-outline-light me-2"><fmt:message key="logout"/></button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="${pageContext.request.contextPath}/login.jsp"
                          class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-0">
                        <button type="submit" class="btn btn-outline-light me-2"><fmt:message key="singIn"/></button>
                    </form>
                    <form action="${pageContext.request.contextPath}/singUp.jsp"
                          class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                        <button type="submit" class="btn btn-warning"><fmt:message key="singUp"/></button>
                    </form>
                </c:otherwise>
            </c:choose>
            <div class="text-end">
                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                    <c:if test="${currentPage != null}">
                        <input type="hidden" class="key" name="page" value="${currentPage}">
<%--                        <input type="hidden" class="key" name="sortBy" value="${sortBy}">--%>
                    </c:if>
                    <c:if test="${search != null}">
                        <input type="hidden" class="key" name="search" value="${search}">
                    </c:if>
                    <button type="submit" class="btn btn-light" name="language" value="en"><strong>EN</strong></button>
                    <button type="submit" class="btn btn-light" name="language" value="uk"><strong>UA</strong></button>
                </form>
            </div>
        </div>
    </div>
</header>
<hr>
</body>
</html>
