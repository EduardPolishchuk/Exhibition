<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<html>
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Exposition Events</title>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="common/header.jsp"/>
<h2  class="display-1" style="color: aliceblue"><fmt:message key="welcomeText"/><a href="${pageContext.request.contextPath}/start">START </a></h2>
<hr/>
<table >
<%--    <img  src="https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg"/>--%>
    <c:forEach var="item" items="${expoList}" >
        <tr>
            <th>${item.theme} ITEM_THEME</th>
            <th>${item.date} ITEM_DATE</th>
            <th>
            </th>
        </tr>
    </c:forEach>
</table>
<jsp:include page="common/footer.jsp"/>
</body>
</html>