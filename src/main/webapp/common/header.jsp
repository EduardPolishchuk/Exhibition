<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Title</title>
    <form style="background-color: black; text-align: end " ><br>
        <button type="submit" class="btn btn-light" name="language" value="en"><strong>EN</strong></button>
        <button type="submit" class="btn btn-light" name="language" value="uk"><strong>UA</strong></button>
<%--        <input type="hidden" name="command" value="changeLanguage"/>--%>
        <c:choose>
            <c:when test="${login != null}">
                <div style="display: flex; justify-content: flex-start">
                    <h5 style="color: aliceblue">${login}<br/>
                    </h5>
                    <h6>
                        <a style="" href="${pageContext.request.contextPath}/logout"> <fmt:message key="logout"/></a>
                    </h6>
                </div>
                <br/>
            </c:when>
            <c:otherwise>
                <div style="display: flex; justify-content: flex-start">
                    <h6>
                        <a style="" href="${pageContext.request.contextPath}/login"> <fmt:message key="login"/></a>
                    </h6>
                </div>
            </c:otherwise>
        </c:choose>
        <hr>
    </form>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<hr>
</body>
</html>
