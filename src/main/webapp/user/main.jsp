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

</head>
<body>
<table>
    <thead>
    <tr>
        <th>Exposition</th>
        <th>Price</th>
        <th>Halls</th>
        <th>Date</th>
    </tr>
    </thead>
<tbody>
<c:forEach var="exp" items="${expo}" step="1" varStatus="status">
    <tr>
        <td>${exp.theme}</td>
        <td>${exp.price}</td>
        <td>${exp.halls}</td>
        <td>${exp.date}</td>
    </tr>
tr>td>*5

<%--    <li> ${exp.theme}</li>--%>
<%--    <li>${exp.price}</li>--%>
<%--    <li>${exp.halls}</li>--%>
<%--    <li>${exp.date}</li>--%>
</c:forEach>
</tbody>
</table>

</body>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</html>
