<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <form style="background-color: black; text-align: end " ><br>
        <button type="submit" class="btn btn-light" name="language" value="en"><strong>EN</strong></button>
        <button type="submit" class="btn btn-light" name="language" value="uk"><strong>UA</strong></button>
        <hr>
    </form>
    <title>Login to the system</title>
</head>
<body>
<h1><fmt:message key="loginToSystem"/></h1>
<form method="post" action="${pageContext.request.contextPath}/login">
    <hr/>
    <tr>
        <td><fmt:message key="userName"/> </td>
        <td><input type="text" name="login"></td>
    </tr><br/><br/>

    <tr>
        <td><fmt:message key="password"/> </td>
        <td><input type="password" name="password"></td>
    </tr><br/><br/>
    <input class="button" type="submit" value="<fmt:message key="singIn"/>">
    </table>
</form>
<hr/>
<a href="${pageContext.request.contextPath}/"><fmt:message key="home"/> </a>
<a href="${pageContext.request.contextPath}/singUp"><fmt:message key="home"/> </a>
</body>
</html>