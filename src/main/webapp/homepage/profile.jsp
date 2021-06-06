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
    <title>MyProfile</title>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="/common/header2.jsp"/>
<h2><strong>
    <fmt:message key="welcomeText"/>
</strong>
</h2>
<a href="${pageContext.request.contextPath}/main">MAIN </a>
<hr/>

<table class="table" style="background-color: aliceblue">
    <thead>
    <tr>
        <th scope="col">Login</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Email</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${userProfile.login}</td>
            <td>${userProfile.firstName}</td>
            <td>${userProfile.lastName}</td>
            <td>${userProfile.email}</td>
        </tr>
    </tbody>
</table>
</body>
</html>