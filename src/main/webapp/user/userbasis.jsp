<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>USER</title>

</head>
<body>
<jsp:include page="/common/header.jsp"/>
<h2><strong>
    <fmt:message key="welcomeText"/>
</strong>

</h2>
<br/>
<br/>
<br>
<br>
</body>
</html>



