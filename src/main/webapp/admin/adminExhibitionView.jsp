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
<body style="background-color: black">
<jsp:include page="/common/header2.jsp"/>
<div class="container justify-content-center w-100 bg-light">
    <div class="row row-cols-2 row-cols-sm-2 row-cols-md-2 g-3 mb-6">

        <div class="col">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/adminExhibitionUpdate">
                    <div class="row mb-3">

                        <div class="col">
                            <label class="form-label">Theme</label>
                            <input type="text" class="form-control " name="theme" value="${exhibition.theme}"
                            ${vari}>
                        </div>
                        <div class="col">
                            <label class="form-label">Theme Uk</label>
                            <input type="text" class="form-control " name="themeUk" value="${exhibition.themeUk}"
                            ${vari}>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Date</label>
                            <input type="date" class="form-control " name="date" value="${exhibition.date}"
                                   min="${currentDate}"
                                   pattern="yyyy.MM.dd" ${vari}>
                        </div>
                        <div class="col">
                            <label class="form-label">Price</label>
                            <input type="number" class="form-control " min="1" name="price" value="${exhibition.price}"
                            ${vari}>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <textarea class="form-control" name="description" ${vari}
                                  rows="1">${exhibition.description}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description Uk</label>
                        <textarea class="form-control" name="descriptionUk" ${vari}
                                  rows="1">${exhibition.descriptionUk}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Image Url</label>
                        <input type="text" class="form-control " name="imageUrl" value="${exhibition.imageUrl}"
                        ${vari}>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Tickets Sold</label>
                            <input type="text" class="form-control " name="date" value="${exhibition.currentPlaces}"
                                   disabled>
                        </div>
                        <div class="col">
                            <label class="form-label">Places</label>
                            <input type="text" class="form-control " name="price" value="${exhibition.maxPlaces}"
                                   disabled>
                        </div>
                    </div>
                    <div>
                        <input type="hidden" name="exId" value="${param.exId}">
                        <hr>
                        <c:if test="${error != null}">
                            <div class="alert alert-danger  p-1 w-auto" role="alert">
                                <fmt:message key="incorrectInput"/>: "${error}"
                                <button type="button" class="btn-close btn-sm" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                            </div>
                        </c:if>
                        <c:if test="${!exhibition.isCanceled}">
                            <button type="submit" class="btn btn-primary"
                                ${vari}>Update
                            </button>
                            <button form="form2" type="submit" class="btn btn-dark" name="edit" value="${vari != null  ? '1' : ''}">Edit
                            </button>
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal"
                                    data-bs-theme="${language != 'uk'? exhibition.theme : exhibition.themeUk}"
                                    data-bs-id="${exhibition.id}">Cancel
                            </button>
                        </c:if>
                    </div>
                </form>
                <form id="form2" action="${pageContext.request.requestURI}">
                    <c:forEach var="item" items="${param}">
                        <c:if test="${item.key != 'edit'}">
                            <input type="hidden" name="${item.key}" value="${item.value}">
                        </c:if>
                    </c:forEach>
                </form>

            </div>
        </div>
        <div class="col ">
            <div class="card shadow-sm" mb-5>
                <div class="card-body">
                    <h3 class="display-4">${exhibition.theme}</h3>
                    <div class="">
                        <img class="card-img-top" src=${exhibition.imageUrl} alt="Picture"
                             style="max-height: 360px; max-width: 600px">
                    </div>
                </div>
            </div>
            <hr>
            <form action="${pageContext.request.contextPath}/admin/adminClientList">
                <button type="submit" class="btn btn-sm btn-outline-secondary"
                        name="exId" value="${param.exId}">Visitors
                </button>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/admin/adminCancelExhibition" class="row g-3 needs-validation"
                      novalidate>
                    <div class="mb-3">
                    </div>
                    <div class="mb-3">
                        <input type="hidden" class="form-control" id="exId-name" name="exID">
                        <label class="form-label">Please confirm to cancel the exhibition</label>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Confirm</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
        var modalTitle = exampleModal.querySelector('.modal-title')
        var modalBodyInput = exampleModal.querySelector('.modal-body input')
        modalTitle.textContent = recipient
        modalBodyInput.value = exId
    })
</script>
<hr>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
