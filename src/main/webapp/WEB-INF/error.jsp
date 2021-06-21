<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<%@ page import="java.util.*, java.text.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Success</title>
</head>
<body style="background-color: black">
<jsp:include page="/common/header2.jsp"/>
<%--<h2 class="display-1" style="color: aliceblue"><fmt:message key="success"/>--%>
    <h2 class="display-3" style="color: aliceblue">
        Error<br/>
        <i>Oops, something went wrong...<%= exception %>
            <a href="${pageContext.request.contextPath}/">Back.</a>
        </i>
    </h2>
</body>
</html>
