<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Exposition Events</title>
    <style>
        div2 {
            background: rgba(255, 255, 255, 0);
            /*padding: 1rem;*/
            /*border-radius: 5px;*/
        }

    </style>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="common/header2.jsp"/>
<h2  class="display-1" style="color: aliceblue"><fmt:message key="welcomeText"/> ${pageContext.request.contextPath}</h2>
    <div2 class="album py-5 bg-light" >
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:forEach var="item" items="${expoList}" >
                    <div class="col">
                        <div class="card shadow-sm">
                            <img class="card-img-top" src=${item.imageUrl} alt="Picture" style="max-height: 240px" >
<%--                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnaasdasdasdasdasdsdasdasdasdasdasdasdasdasil" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>
                            <div class="card-body">
                                <p class="card-text"><fmt:message key="theme"/>: ${item.theme}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                    </div>
                                    <small class="text-muted">${item.max-item.current}  <fmt:message key="ticketsLeft"/></small>
                                    <small class="text-muted">${item.price}  <fmt:message key="uah"/></small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div2>
<hr>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="/Exhibition/?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/Exhibition/?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="/Exhibition/?page=${currentPage + 1}">Next</a></td>
</c:if>


<%--<nav aria-label="..." >--%>
<%--    <ul class="pagination justify-content-center">--%>
<%--        <li class="page-item disabled">--%>
<%--            <span class="page-link">Previous</span>--%>
<%--        </li>--%>
<%--        <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--        <li class="page-item active" aria-current="page">--%>
<%--            <span class="page-link">2</span>--%>
<%--        </li>--%>
<%--        <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--        <li class="page-item">--%>
<%--            <a class="page-link" href="#">Next</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--</nav>--%>


<footer class="text-muted py-5">

<%--    <div class="container">--%>
<%--        <p class="float-end mb-1">--%>
<%--            <a href="#" style="background-color: black">Back to top</a>--%>
<%--        </p>--%>
<%--    </div>--%>
</footer>
<jsp:include page="common/footer.jsp"/>

<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>


</body>
</html>