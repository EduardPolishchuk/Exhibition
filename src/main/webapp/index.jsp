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
</head>
<body style="background-color: black">
<jsp:include page="common/header2.jsp"/>
<h2 class="display-3" style="color: aliceblue"><fmt:message key="welcomeText"/></h2>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/user/userbuy" class="row g-3 needs-validation" novalidate>
                    <div class="mb-3">
                    </div>
                    <div class="mb-3">
                        <input type="hidden" class="form-control" id="exId-name" name="exEx">
                        <label for="validationCustom01" class="form-label">First name</label>
                        <input type="text" class="form-control" id="validationCustom01" name="amount" value="1"
                               required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Send message</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<c:if test="${noOfPages > 0}">
    <form  style="align-self: center">
        <div class="btn-group justify-content-center" role="group">
            <input type="submit" class="btn-check" name="sortBy" value="3" id="btnradio1" autocomplete="off" >
            <label class="btn btn-primary ${param.sortBy == '3'? 'active':''}" for="btnradio1">Theme</label>

            <input type="submit" class="btn-check active" name="sortBy" value="2" id="btnradio2" autocomplete="off" >
            <label class="btn btn-primary ${param.sortBy == '2'? 'active':''}" for="btnradio2">Date</label>

            <input type="submit" class="btn-check" name="sortBy" value="4" id="btnradio3" autocomplete="off">
            <label class="btn btn-primary ${param.sortBy == '4'? 'active':''}" for="btnradio3">Price</label>
        </div>
    </form>
</c:if>
<div2 class="album py-0 ">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
            <c:forEach var="item" items="${expoList}">
                <div class="col">
                    <div class="card shadow-sm">
                        <img class="card-img-top" src=${item.imageUrl} alt="'Picture'" style="min-height: 220px; max-height: 220px">
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
                                    <p class="card-text">${item.descriptionUk}</p>
                                </c:otherwise>
                            </c:choose>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <c:choose>
                                        <c:when test="${role =='USER' && userProfile.balance > item.price}">
                                            <form id="form1"
                                                  action="${pageContext.request.contextPath}/user/userExhibitionView"></form>
                                            <button type="button" class="btn btn-sm btn-outline-secondary"
                                                    data-bs-toggle="modal" data-bs-target="#exampleModal"
                                                    data-bs-theme="${language != 'uk'? item.theme : item.themeUk}" data-bs-id="${item.id}">
                                                <fmt:message
                                                        key="buy"/></button>
                                            <button form="form1" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}"><fmt:message
                                                    key="view"/></button>
                                        </c:when>
                                        <c:when test="${role =='ADMIN'}">
                                            <form id="data"
                                                  action="${pageContext.request.contextPath}/admin/adminExhibitionView"></form>
                                            <form id="data2"
                                                  action="${pageContext.request.contextPath}/admin/adminClientList"></form>
                                            <button form="data" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}">Edit
                                            </button>
                                            <button form="data2" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}">Visitors
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <form id="form2" action=""></form>
                                            <button type="submit" class="btn btn-sm btn-outline-secondary"
                                                    disabled><fmt:message
                                                    key="buy"/></button>
                                            <button form="form2" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}"><fmt:message
                                                    key="view"/></button>
                                        </c:otherwise>
                                    </c:choose>
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
<footer class="text-muted py-5">
</footer>
<jsp:include page="common/footer.jsp"/>

<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    var exampleModal = document.getElementById('exampleModal')
    exampleModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget
        var recipient = button.getAttribute('data-bs-theme')
        var exId = button.getAttribute('data-bs-id')
        var modalTitle = exampleModal.querySelector('.modal-title')
        var modalBodyInput = exampleModal.querySelector('.modal-body input')
        modalTitle.textContent = recipient
        modalBodyInput.value = exId
    })
</script>
</body>
</html>