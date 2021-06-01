<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1 style="text-align: end"> ${login}
</h1>
<h5 style="text-align: end">
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</h5>
<jsp:include page="/common/header.jsp"/>
<h2>
    Welcome to Exposition Events <br/>
</h2>
<br/>
<br/>
<br>
<br>

</body>
</html>

<a href="${pageContext.request.contextPath}/main">Main</a>
</body>
</html>