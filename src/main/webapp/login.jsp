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
<body class="text-center" style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="common/header2.jsp"/>
<h2 class="display-2" style="color: aliceblue"><fmt:message key="loginToSystem"/></h2>
<div class="container  w-25  ">
    <div class="row row-cols-1 row-cols-lg row-cols-md-1 g-1">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4 class="display-5" style="text-align: center">Enter your data</h4>
                    <form method="post" action="${pageContext.request.contextPath}/login">
                        <div class="mb-3 ">
                            <label  class="form-label"><fmt:message key="userName"/></label><br>
                            <input type="text" name="login" >
                        </div>
                        <div class="mb-3">
                            <label  class="form-label"><fmt:message key="password"/></label><br>
                            <input type="password" name="password">
                        </div>
                        <c:if test="${incorrect != null}">
                            <div class="alert alert-danger  p-2 " role="alert">
                                <fmt:message key="${incorrect}"/>
                                <button type="button" class="btn-close p-1" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="common/footer.jsp"/>--%>
</body>
</html>