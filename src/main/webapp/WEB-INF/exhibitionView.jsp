<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Theme</label>
                            <input type="text" class="form-control " name="theme" value="${exhibition.theme}"
                                   disabled>
                        </div>
                        <div class="col">
                            <label class="form-label">Theme Uk</label>
                            <input type="text" class="form-control " name="themeUk" value="${exhibition.themeUk}"
                                   disabled>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Date</label>
                            <input type="text" class="form-control " name="date" value="${exhibition.date}"
                                   disabled>
                        </div>
                        <div class="col">
                            <label class="form-label">Price (<fmt:message key="uah"/>)</label>
                            <input type="text" class="form-control " name="price" value="${exhibition.price}"
                                   disabled>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <textarea class="form-control" name="description" disabled
                                  rows="2">${exhibition.description}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description Uk</label>
                        <textarea class="form-control" name="descriptionUk" disabled
                                  rows="2">${exhibition.descriptionUk}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Halls</label>
                        <input type="text" class="form-control " name="imageUrl" value="${exhibition.halls}"
                               disabled>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tickets Left</label>
                        <input type="text" class="form-control " name="imageUrl" value="${exhibition.max - exhibition.current}"
                               disabled>
                    </div>
                    <div>
                        <hr>
                    </div>
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
        </div>
    </div>
</div>
<hr>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
