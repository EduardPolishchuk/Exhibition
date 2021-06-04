<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>USER</title>

</head>
<body>
<jsp:include page="/common/header2.jsp"/>
<h2><strong>
    <fmt:message key="welcomeText"/>
</strong>
</h2>
<a href="${pageContext.request.contextPath}/main">MAIN </a>
<hr/>
    <table>
        <h2>Expositions</h2>
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



