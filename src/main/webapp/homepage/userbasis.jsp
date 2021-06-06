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
    <title>USER</title>

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
        <th scope="col">#</th>
        <th scope="col">Theme</th>
        <th scope="col">Date</th>
        <th scope="col">Tickets</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="1" scope="page" />
    <c:forEach var="key" items="${userExhib.keySet()}">
        <c:set var="exhib" value="${userExhib.get(key)}"/>
        <tr>
            <th scope="row">${count}</th>
            <td>${key.theme}</td>
            <td>${key.date}</td>
            <td>${exhib}</td>
        </tr>
        <c:set var="count" value="${count + 1}" scope="page"/>
    </c:forEach>
    </tbody>
</table>


<%--    <table>--%>
<%--        <h2>Expositions</h2>--%>
<%--        <c:forEach var="item" items="${expoList}" >--%>
<%--            <tr>--%>
<%--                <th>${item.theme} ITEM_THEME</th>--%>
<%--                <th>${item.date} ITEM_DATE</th>--%>
<%--                <th>--%>
<%--                </th>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
</body>
</html>



