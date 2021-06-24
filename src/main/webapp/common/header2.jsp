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
            <a href="${pageContext.request.contextPath}/"
               class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="60" role="img" aria-label="Bootstrap">
                    <use xlink:href="${pageContext.request.contextPath}/"></use>
                </svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/"
                       class="nav-link px-1 text-secondary"><strong><fmt:message key="home"/> </strong></a></li>
                <c:choose>
                    <c:when test="${role == 'USER'}">
                        <li><a href="${pageContext.request.contextPath}/user/userevents"
                               class="nav-link px-2 text-white"><fmt:message key="myEvents"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/user/userprofile.jsp"
                               class="nav-link px-2 text-white"><fmt:message key="myProfile"/></a></li>
                    </c:when>
                    <c:when test="${role == 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/admin/adminbasis.jsp"
                               class="nav-link px-2 text-white"><fmt:message key="addExhibition"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/adminClientList"
                               class="nav-link px-2 text-white"><fmt:message key="visitorsList"/></a></li>
                    </c:when>
                </c:choose>
            </ul>
            <form action="${pageContext.request.contextPath}/search" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                <c:set var="searchLocale" value="${language != 'uk'? 'Search...':'Пошук...'}"/>
                <input type="search" class="form-control form-control-dark" name="search" placeholder="${searchLocale}"
                       aria-label="Search">
            </form>
            <c:choose>
                <c:when test="${role == 'USER' || role == 'ADMIN'}">
                    <c:if test="${role == 'ADMIN'}">
                        <h6 class="display-11" style="color: aliceblue"><fmt:message key="admin"/></h6><br>
                    </c:if>
                    <h5 class="display-6" style="color: aliceblue">${userProfile.login}
                    </h5>
                    <c:if test="${role == 'USER'}">
                        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-0">
                            <button type="button" class="btn btn-link "
                                    data-bs-toggle="modal" data-bs-target="#exampleModal2"
                            >${userProfile.balance} <fmt:message key="uah"/></button>
                        </form>
                    </c:if>
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
                    <c:forEach var="item" items="${param}">
                        <c:if test="${item.key != 'language'}">
                            <input type="hidden" name="${item.key}" value="${item.value}">
                        </c:if>
                    </c:forEach>
                    <button type="submit" class="btn btn-light" name="language" value="en"><strong>EN</strong></button>
                    <button type="submit" class="btn btn-light" name="language" value="uk"><strong>UA</strong></button>
                </form>
            </div>
        </div>
    </div>
</header>

<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="changeBalance"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/Exhibition/changeBalance" class="row g-3 needs-validation" novalidate>
                    <div class="mb-3">
                    </div>
                    <div class="mb-3">
                        <label for="validationCustom01" class="form-label"><fmt:message key="amount"/></label>
                        <input type="hidden" name="page" value="${pageContext.request.requestURI}">
                        <input type="text" class="form-control" id="validationCustom01" name="amount" value="0"
                               required>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary"><fmt:message key="confirm"/> </button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="close"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
