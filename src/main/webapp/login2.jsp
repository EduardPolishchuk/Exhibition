<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Login to the system</title>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="common/header2.jsp"/>
<h1><fmt:message key="loginToSystem"/></h1>

<div style="background-color: aliceblue">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <hr/>
        <tr>
            <td><fmt:message key="userName"/>${incorect}</td>
            <td><input type="text" name="login"></td>
        </tr>
        <br/><br/>
        <c:if test="${incorect != null}">
            <div class="alert alert-danger" role="alert">
                A simple danger alert—check it out!
            </div>
        </c:if>
        <tr>
            <td><fmt:message key="password"/></td>
            <td><input type="password" name="password"></td>
        </tr>
        <br/><br/>
        <input class="button" type="submit" value="<fmt:message key="singIn"/>">
        </table>
    </form>
    <hr/>
    <a href="${pageContext.request.contextPath}/"><fmt:message key="home"/> </a>
    <a href="${pageContext.request.contextPath}/singUp"><fmt:message key="home"/> </a>
</div>


<footer class="text-muted py-5">

    <div class="footer__inner" style="background-color: black; text-align: center; color: aliceblue ">
        &copy; Exhibition Events 2021
    </div>
</footer>
<%--<jsp:include page="common/footer.jsp"/>--%>
</body>
</html>