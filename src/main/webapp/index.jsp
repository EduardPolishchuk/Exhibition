<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/custom_tag.tld" prefix="custom" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Exhibitions&Events</title>
    <style>
        div2 {
            background: rgba(255, 255, 255, 0);
        }
    </style>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="common/header2.jsp"/>
<h2 class="display-2" style="color: aliceblue"><fmt:message key="welcomeText"/> ${pageContext.request.contextPath}</h2>
<select  class="form-select form-select-sm w-25 p-1" aria-label="Default select example" style="text-align: end ">
    <option selected>Sort by</option>
    <option value="1">Theme</option>
    <option value="2">Price</option>
    <option value="3">Date</option>
</select>
<hr>
<div2 class="album py-5 bg-light">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
            <c:forEach var="item" items="${expoList}">
                <div class="col">
                    <div class="card shadow-sm">
                        <img class="card-img-top" src=${item.imageUrl} alt="Picture" style="max-height: 240px">
                            <%--                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnaasdasdasdasdasdsdasdasdasdasdasdasdasdasil" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>
                        <div class="card-body">
                            <p class="card-text"><fmt:message key="theme"/>: ${item.theme}</p>
                            <c:choose>
                                <c:when test="${language != 'uk'}">
                                    <p class="card-text">${item.date}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="card-text"><custom:formatDate value="${item.date}"
                                                                            pattern="dd/MM/yyyy"/></p>
                                </c:otherwise>
                            </c:choose>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary disabled">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">${item.max-item.current} <fmt:message
                                        key="ticketsLeft"/></small>
                                <small class="text-muted">${item.price} <fmt:message key="uah"/></small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div2>
<hr>
<c:if test="${noOfPages > 0}">
    <nav aria-label="...">
        <ul class="pagination justify-content-center">
            <c:choose>
                <c:when test="${currentPage <= 1}">
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="/Exhibition/?page=${currentPage - 1}">Previous</a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">${i}</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/Exhibition/?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage >= noOfPages}">
                    <li class="page-item disabled">
                        <span class="page-link">Next</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="/Exhibition/?page=${currentPage + 1}">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </nav>
</c:if>
<footer class="text-muted py-5">
</footer>
<jsp:include page="common/footer.jsp"/>

<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>