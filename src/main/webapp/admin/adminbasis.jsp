<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>


<html>
<head>
    <title>Add New Event</title>
    <jsp:include page="/common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="/common/header2.jsp"/>
<div class="container justify-content-center w-50 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/admin/adminAddExhibition">
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="theme"/></label>
                                <input type="text" class="form-control " name="theme" value="${param.theme}"
                                >
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="themeUk"/></label>
                                <input type="text" class="form-control " name="themeUk" value="${param.themeUk}"
                                >
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="date"/> </label>
                                <input type="date" class="form-control " name="date" value="${param.date}"
                                       min="${currentDate}" pattern="yyyy.MM.dd">
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="price"/></label>
                                <input type="number" class="form-control " min="1" name="price" value="${param.price}"
                                >
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="description"/></label>
                            <textarea class="form-control" name="description"
                                      rows="1">${param.description}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="descriptionUk"/> </label>
                            <textarea class="form-control" name="descriptionUk"
                                      rows="1">${param.descriptionUk}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="imageUrl"/> </label>
                            <input type="text" class="form-control " name="imageUrl" value="${param.imageUrl}"
                            >
                        </div>
                        <label class="form-label"><fmt:message key="halls"/> : </label>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="RED"
                                   value="RED"  ${not empty param.RED?'checked':''}>
                            <label class="form-check-label" for="inlineCheckbox1">RED</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="GREEN"
                                   value="GREEN"  ${not empty param.GREEN?'checked':''}>
                            <label class="form-check-label" for="inlineCheckbox2">GREEN</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="inlineCheckbox3" name="BLUE"
                                   value="BLUE"  ${not empty param.BLUE?'checked':''}>
                            <label class="form-check-label" for="inlineCheckbox3">BLUE</label>
                        </div>
                        <hr>
                        <c:if test="${error != null}">
                            <div class="alert alert-danger  p-1 w-auto" role="alert">
                                <fmt:message key="incorrectInput"/>: "${error}"
                                <button type="button" class="btn-close btn-sm" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                            </div>
                            ${pageContext.session.removeAttribute('error')}
                        </c:if>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary"><fmt:message key="add"/></button>
                            <button type="reset" class="btn btn-secondary"><fmt:message key="reset"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>