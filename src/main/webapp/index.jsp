<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://custom.com" prefix="custom" %>
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
                <form action="${pageContext.request.contextPath}/user/userbuy" class="row g-3 needs-validation"
                      >
                    <div class="mb-3">
                    </div>
                    <div class="mb-3">
                        <input type="hidden" class="form-control" id="exId-name" name="exId">
                        <input type="hidden" class="form-control" id="maxAmount" name="maxAmount">
                        <label for="validationCustom01" class="form-label"><fmt:message key="tickets"/></label>
                        <input type="number" class="form-control" id="validationCustom01" min="1"
                               name="amount"
                               value="1" required>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary"><fmt:message key="confirm"/></button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                key="close"/></button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<c:if test="${noOfPages > 0}">
    <div class="row " style="align-self: center">
        <div class="col">
            <form>
                <div class="btn-group justify-content-center" role="group">
                    <input type="submit" class="btn-check" name="sortBy" value="${param.sortBy eq 3? '': 3}"
                           id="btnradio1" autocomplete="off">
                    <label class="btn btn-primary ${param.sortBy == '3'? 'active':''}" for="btnradio1"><fmt:message
                            key="theme"/> </label>

                    <input type="submit" class="btn-check active" name="sortBy" value="${param.sortBy eq 2? '': 2}"
                           id="btnradio2"
                           autocomplete="off">
                    <label class="btn btn-primary ${param.sortBy == '2'? 'active':''}" for="btnradio2"><fmt:message
                            key="date"/></label>

                    <input type="submit" class="btn-check" name="sortBy" value="${param.sortBy eq 4? '': 4}"
                           id="btnradio3" autocomplete="off">
                    <label class="btn btn-primary ${param.sortBy == '4'? 'active':''}" for="btnradio3"><fmt:message
                            key="price"/></label>
                </div>
                <c:if test="${not empty param.canceled}">
                    <input type="hidden" name="canceled" value="${param.canceled}">
                </c:if>
            </form>
        </div>
        <c:if test="${role == 'ADMIN'}">
            <div class="col">
                <form action="">
                    <c:if test="${not empty param.sortBy}">
                        <input type="hidden" name="sortBy" value="${param.sortBy}">
                    </c:if>
                    <input type="submit" class="btn-check" name="canceled" value="${not empty param.canceled? '': '1'}"
                           id="btncanceled">
                    <label class="btn btn-outline-light ${not empty param.canceled ? 'active':''}"
                           for="btncanceled"><fmt:message key="canceled"/></label>
                </form>
            </div>
        </c:if>
    </div>
</c:if>
<div2 class="album py-0 ">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
            <c:forEach var="item" items="${expoList}">
                <div class="col">
                    <div class="card shadow-sm">
                        <img class="card-img-top" src=${item.imageUrl} alt="'Picture'"
                             style="min-height: 220px; max-height: 220px">
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${language != 'uk'}">
                                    <p class="card-text"><strong>${item.theme}</strong></p>
                                    <p class="card-text">${item.date}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="card-text"><strong>${item.themeUk}</strong></p>
                                    <p  class="card-text"><custom:formatDate value="${item.date}"
                                                                            pattern="dd/MM/yyyy"/></p>
                                </c:otherwise>
                            </c:choose>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <c:choose>
                                        <c:when test="${role =='USER' && userProfile.balance > item.price}">
                                            <c:choose>
                                                <c:when test="${item.isCanceled}">
                                                    <button type="submit" class="btn btn-sm btn-outline-danger"
                                                            disabled><fmt:message key="canceled"/>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" class="btn btn-sm btn-outline-secondary"
                                                            data-bs-toggle="modal" data-bs-target="#exampleModal"
                                                            data-bs-theme="${language != 'uk'? item.theme : item.themeUk}"
                                                            data-bs-id="${item.id}" data-bs-price="${item.price}"
                                                            data-bs-balance="${userProfile.balance}" ${currentDate.isAfter(item.date)? 'disabled':''}>
                                                        <fmt:message
                                                                key="buy"/></button>
                                                </c:otherwise>
                                            </c:choose>
                                            <form id="form1"
                                                  action="${pageContext.request.contextPath}/exhibitionView"></form>

                                            <button form="form1" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}"><fmt:message
                                                    key="view"/></button>
                                        </c:when>
                                        <c:when test="${role =='ADMIN'}">
                                            <c:if test="${item.isCanceled}">
                                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                                        disabled><fmt:message key="canceled"/>
                                                </button>
                                            </c:if>
                                            <form id="data"
                                                  action="${pageContext.request.contextPath}/admin/adminExhibitionView"></form>
                                            <form id="data2"
                                                  action="${pageContext.request.contextPath}/admin/adminClientList"></form>
                                            <button form="data" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}"><fmt:message key="edit"/>
                                            </button>
                                            <button form="data2" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}"><fmt:message key="visitors"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <form id="form2"
                                                  action="${pageContext.request.contextPath}/exhibitionView"></form>
                                            <button type="submit" class="btn btn-sm btn-outline-secondary"
                                                    disabled><fmt:message
                                                    key="buy"/></button>
                                            <button form="form2" type="submit" class="btn btn-sm btn-outline-secondary"
                                                    name="exId" value="${item.id}"><fmt:message
                                                    key="view"/></button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
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
            <c:set var="cnl" value="${not empty param.canceled? '&canceled=1':''}"/>
            <c:choose>
                <c:when test="${currentPage <= 1}">
                    <li class="page-item disabled">
                        <span class="page-link"><fmt:message key="previous"/></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="/Exhibition/?page=${currentPage - 1}&sortBy=${sortBy}${cnl}"><fmt:message key="previous"/></a>
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
                                                 href="/Exhibition/?page=${i}&sortBy=${sortBy}${cnl}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage >= noOfPages}">
                    <li class="page-item disabled">
                        <span class="page-link"><fmt:message key="next"/></span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="/Exhibition/?page=${currentPage + 1}&sortBy=${sortBy}${cnl}"><fmt:message key="next"/></a>
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
        var price = button.getAttribute('data-bs-price')
        var balance = button.getAttribute('data-bs-balance')
        var modalTitle = exampleModal.querySelector('.modal-title')
        var modalBodyInput = exampleModal.querySelector('.modal-body input')
        var modalBodyInput2 = exampleModal.querySelector('.modal-body #maxAmount')
        modalTitle.textContent = recipient
        modalBodyInput.value = exId
        modalBodyInput2.value = balance / price
    })
</script>
</body>
</html>