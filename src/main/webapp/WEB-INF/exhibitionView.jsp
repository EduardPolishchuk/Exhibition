<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="http://custom.com" %>
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
<body style="background-color: black">
<jsp:include page="/common/header2.jsp"/>
<div class="container justify-content-center w-100 bg-light">
    <div class="row row-cols-2 row-cols-sm-2 row-cols-md-2 g-3 mb-6">

        <div class="col">
            <div class="card-body">
                <form action="/success.jsp">
                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="theme"/> </label>
                        <input type="text" class="form-control " name="theme"
                               value="${language != 'uk'? exhibition.theme : exhibition.themeUk}"
                               disabled>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="price"/> (<fmt:message key="uah"/>)</label>
                        <input type="text" class="form-control " name="price" value="${exhibition.price}"
                               disabled>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="description"/></label>
                        <textarea class="form-control" name="description" disabled
                                  rows="1">${language != 'uk'? exhibition.description : exhibition.descriptionUk}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="halls"/></label>
                        <input type="text" class="form-control " name="imageUrl" value="${exhibition.halls}"
                               disabled>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="ticketsLeft"/> </label>
                        <input type="text" class="form-control " name="imageUrl"
                               value="${exhibition.maxPlaces - exhibition.currentPlaces}"
                               disabled>
                    </div>
                    <div>
                        <hr>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><fmt:message key="date"/></label>
                        <c:choose>
                            <c:when test="${language != 'uk'}">
                                <ins>
                                    <p class="form-label">${exhibition.date}</p>
                                </ins>
                            </c:when>
                            <c:otherwise>
                                <p class="form-label"><ins>
                                    <custom:formatDate value="${exhibition.date}"
                                                       pattern="dd/MM/yyyy"/>
                                </ins></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>
            </div>
        </div>
        <div class="col ">
            <div class="card shadow-sm" mb-5>
                <div class="card-body">
                    <h3 class="display-4">${language != 'uk'? exhibition.theme : exhibition.themeUk}</h3>
                    <div class="">
                        <img class="card-img-top" src=${exhibition.imageUrl} alt="Picture"
                             style="max-height: 360px; max-width: 600px">
                    </div>
                </div>
            </div>
            <hr>
            <c:if test="${!exhibition.isCanceled && userProfile.balance > exhibition.price && role == 'USER'}">
                <button type="button" class="btn btn-sm btn-outline-secondary"
                        data-bs-toggle="modal" data-bs-target="#exampleModal"
                        data-bs-theme="${language != 'uk'? exhibition.theme : exhibition.themeUk}"
                        data-bs-id="${exhibition.id}" data-bs-price="${exhibition.price}"
                        data-bs-balance="${userProfile.balance}">
                    <fmt:message
                            key="buy"/></button>
            </c:if>
        </div>
    </div>
</div>
<hr>
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
                        <input type="hidden" class="form-control" id="maxAmount" name="maxAmount">
                        <input type="hidden" class="form-control" id="exId" name="exID">
                        <label for="amountInput" class="form-label"><fmt:message key="tickets"/> </label>
                        <input type="number" class="form-control" id="amountInput" min="1"
                               max="${userProfile.balance / exhibition.price}"
                               name="amount"
                               value="1" required>
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
<script type="text/javascript">
    var exampleModal = document.getElementById('exampleModal')
    exampleModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget
        var recipient = button.getAttribute('data-bs-theme')
        var exId = button.getAttribute('data-bs-id')
        var price = button.getAttribute('data-bs-price')
        var balance = button.getAttribute('data-bs-balance')
        var modalTitle = exampleModal.querySelector('.modal-title')
        var modalBodyInput = exampleModal.querySelector('.modal-body #exId')
        var modalBodyInput2 = exampleModal.querySelector('.modal-body #maxAmount')
        modalTitle.textContent = recipient
        modalBodyInput.value = exId
        modalBodyInput2.value = balance / price
    })
</script>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
