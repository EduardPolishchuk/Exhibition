<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN PAGE</title>
</head>
<body>
<jsp:include page="/common/header2.jsp"/>
<h1>Hello <strong>${login}</strong>!</h1>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<a href="${pageContext.request.contextPath}/clientList">Logout</a>
</body>
</html>