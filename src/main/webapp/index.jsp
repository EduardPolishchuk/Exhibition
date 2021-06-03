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
<body >
<jsp:include page="common/header.jsp"/>
<h2><fmt:message key="welcomeText"/></h2>
<hr/>
<table style="background-image: url(images/123.jpg)">
    <h2>Expositions </h2>

    <c:forEach var="item" items="${expoList}" >
        <tr>
            <th>${item.theme} ITEM_THEME</th>
            <th>${item.date} ITEM_DATE</th>
            <th>
            </th>
        </tr>
    </c:forEach>
</table>
</body>
</html>