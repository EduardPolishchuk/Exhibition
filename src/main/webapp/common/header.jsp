<%--
  Created by IntelliJ IDEA.
  User: y
  Date: 16.05.21
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="uk"/>
<fmt:setBundle basename="resources_utf8_uk"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4 style="text-align: end">
    <form action="/Servlet" method="post" >
        <button type="submit" name="language" value="en">EN</button>
        <button type="submit" name="language" value="uk">UA</button>
        <input type="hidden" name="command" value="changeLanguage"/>
    </form>
</h4>

<a href="/"><fmt:message key="beautyShop"/></a>
<c:if test="${login}">
    <div style="display: flex; justify-content: flex-end">
        <a href="/logout">logout</a>
    </div>
</c:if>
<hr>
</body>
</html>
