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
    <jsp:include page="/common/windowstyle.jsp"/>
</head>
<body class="text-center">
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
                            <input type="text" name="login" value="qw">
                        </div>
                        <div class="mb-3">
                            <label  class="form-label"><fmt:message key="password"/></label><br>
                            <input type="password" name="password" value="1">
                        </div>
                        <c:if test="${incorrect != null}">
                            <div class="alert alert-danger  p-2 " role="alert">
                                <fmt:message key="${incorrect}"/>
                                <button type="button" class="btn-close btn-sm" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>