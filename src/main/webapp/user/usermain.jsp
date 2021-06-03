<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<html>
<head>
    <title>Title</title>
    <jsp:include page="/common/tablestyle.jsp"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<table>
    <thead>
    <tr>
        <th>Theme</th>
        <th>Halls</th>
    </tr>
    </thead>
<tbody>
<h1>${expoList}</h1>
<c:forEach var="exp" items="${expoList}" step="1" varStatus="status">
    <tr>
        <img style="size: B5" src="https://i1.sndcdn.com/avatars-000296802399-mmqk5k-t500x500.jpg" alt="nema">
        <td>${exp.theme}</td>
        <td>${exp.halls}</td>
    </tr>
</c:forEach>
</tbody>
</table>

</body>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</html>
