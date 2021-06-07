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
    <jsp:include page="/common/windowstyle.jsp"/>
    <style>
        /*div2 {*/
        /*    background: rgba(255, 255, 255, 0);*/
        /*}*/
        /*#zatemnenie {*/
        /*    background: rgba(102, 102, 102, 0.5);*/
        /*    width: 100%;*/
        /*    height: 100%;*/
        /*    position: absolute;*/
        /*    top: 0;*/
        /*    left: 0;*/
        /*    display: none;*/
        /*}*/
        /*#okno {*/
        /*    width: 300px;*/
        /*    height: 50px;*/
        /*    text-align: center;*/
        /*    padding: 15px;*/
        /*    border: 3px solid #0000cc;*/
        /*    border-radius: 10px;*/
        /*    color: #0000cc;*/
        /*    position: absolute;*/
        /*    top: 0;*/
        /*    right: 0;*/
        /*    bottom: 0;*/
        /*    left: 0;*/
        /*    margin: auto;*/
        /*    background: #fff;*/
        /*    z-index:9999;*/
        /*}*/
        /*#zatemnenie:target {display: block;}*/
        /*.close {*/
        /*    display: inline-block;*/
        /*    border: 1px solid #0000cc;*/
        /*    color: #0000cc;*/
        /*    padding: 0 12px;*/
        /*    margin: 10px;*/
        /*    text-decoration: none;*/
        /*    background: #f2f2f2;*/
        /*    font-size: 14pt;*/
        /*    cursor:pointer;*/
        /*}*/
        /*.close:hover {background: #e6e6ff;}*/
    </style>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="common/header2.jsp"/>
<h2 class="display-2" style="color: aliceblue"><fmt:message key="welcomeText"/></h2>
<%--<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" name="modl" value="1">--%>
<%--    Launch demo modal--%>
<%--</button>--%>
<div id="zatemnenie">
    <div id="okno">
        Всплывающее окошко!<br>
        <a href="#" class="close">Закрыть окно</a>
    </div>
</div>
<form style="align-content: end">
    <select class="form-select form-select-sm w-25 p-1" aria-label="Default select example" name="sortBy"
            style="text-align: end ">
        <option selected value="1">Sort by</option>
        <option value="2">Date</option>
        <option value="3">Theme</option>
        <option value="4">Price</option>
    </select>
    <button type="submit" class="btn btn-light">Sort</button>

</form>
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
                        <a class="page-link" href="/Exhibition/?page=${currentPage - 1}&sortBy=${sortBy}">Previous</a>
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
                        <li class="page-item"><a class="page-link"
                                                 href="/Exhibition/?page=${i}&sortBy=${sortBy}">${i}</a></li>
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
                        <a class="page-link" href="/Exhibition/?page=${currentPage + 1}&sortBy=${sortBy}">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</c:if>
<div2 class="album py-5 bg-light">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
            <c:forEach var="item" items="${expoList}">
                <div class="col">
                    <div class="card shadow-sm">
                        <img class="card-img-top" src=${item.imageUrl} alt="Picture" style="max-height: 240px">
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${language != 'uk'}">
                                    <p class="card-text"><strong>${item.theme}</strong></p>
                                    <p class="card-text">${item.date}</p>
                                    <p class="card-text">${item.description}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="card-text"><strong>${item.themeUk}</strong></p>
                                    <p class="card-text"><custom:formatDate value="${item.date}"
                                                                            pattern="dd/MM/yyyy"/></p>
                                    <p class="card-text">${item.description}</p>
                                </c:otherwise>
                            </c:choose>
                            <div class="d-flex justify-content-between align-items-center">
                                <form action="/Exhibition/homepage/userbuy">
                                    <input type="hidden" name="id" value="${item.id}">
                                    <div class="btn-group">
                                        <c:choose>
                                            <c:when test="${role !='USER' || userProfile.balance < item.price}">
                                                <button type="submit" class="btn btn-sm btn-outline-secondary"
                                                        disabled><fmt:message
                                                        key="buy"/></button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-sm btn-outline-secondary" data-bs-id="idId"
                                                        data-bs-toggle="modal" data-bs-target="#exampleModal"><fmt:message
                                                        key="buy"/></button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </form>

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

<footer class="text-muted py-5">
</footer>
<jsp:include page="common/footer.jsp"/>

<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>