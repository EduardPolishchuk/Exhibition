<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30.05.2021
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <jsp:include page="/common/tablestyle.jsp"/>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Hall</th>
        <th>Places</th>
    </tr>
    </thead>
<tbody>
<c:forEach var="exp" items="${expo}" step="1" varStatus="status">
    <tr>
        <td>${exp}</td>
        <td>${exp.places}</td>
    </tr>

</c:forEach>
</tbody>
</table>

</body>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</html>
