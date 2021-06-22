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
                                <label class="form-label">Theme</label>
                                <input type="text" class="form-control " name="theme" value=""
                                >
                            </div>
                            <div class="col">
                                <label class="form-label">Theme Uk</label>
                                <input type="text" class="form-control " name="themeUk" value=""
                                >
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Date</label>
                                <input type="date" class="form-control " name="date" value="" min="2020-01-01" max="2022-12-31"
                                       pattern="yyyy.MM.dd">
                            </div>
                            <div class="col">
                                <label class="form-label">Price</label>
                                <input type="text" class="form-control " name="price" value=""
                                >
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description</label>
                            <textarea class="form-control" name="description"
                                      rows="1">${exhibition.descriptionUk}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description Uk</label>
                            <textarea class="form-control" name="descriptionUk"
                                      rows="1">${exhibition.descriptionUk}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Image Url</label>
                            <input type="text" class="form-control " name="imageUrl" value=""
                            >
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="RED"
                                   value="RED" ${vari}>
                            <label class="form-check-label" for="inlineCheckbox1">RED</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="inlineCheckbox2" name="GREEN"
                                   value="GREEN" ${vari}>
                            <label class="form-check-label" for="inlineCheckbox2">GREEN</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="inlineCheckbox3" name="BLUE"
                                   value="BLUE" ${vari}>
                            <label class="form-check-label" for="inlineCheckbox3">BLUE</label>
                        </div>
                        <hr>
                        <c:if test="${error != null}">
                            <c:choose>
                                <c:when test="${error eq 'passwordInvalid' || error eq 'loginInvalid'}">
                                    <div class="alert alert-danger  p-1 " role="alert">
                                        <fmt:message key="${error}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-danger  p-1 " role="alert">
                                        <fmt:message key="incorrectInput"/>: "${error}"
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Add</button>
                            <button type="reset" class="btn btn-secondary">Reset</button>
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