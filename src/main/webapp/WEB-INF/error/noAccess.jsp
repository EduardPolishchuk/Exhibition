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
    <title>Success</title>
    <jsp:include page="/common/windowstyle.jsp"/>
</head>
<body class="text-center" style="background-color: black">
<jsp:include page="/common/header2.jsp"/>
<h2 class="display-3" style="color: aliceblue"><fmt:message key="error"/></h2>
<div class="container  w-50  " style="text-align:center">
    <div class="row row-cols-1 row-cols-lg row-cols-md-1 g-1">
        <div class="col " style="text-align:center">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4 class="display-5" style="text-align: center"><fmt:message key="noAccess"/></h4>
                    <form method="post" action="${pageContext.request.contextPath}/">
                        <button type="submit" class="btn btn-primary"><fmt:message key="home"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
