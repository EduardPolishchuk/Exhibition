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
    <title>SingUp</title>
</head>
<body class="text-center" style="background-color: black">
<jsp:include page="common/header2.jsp"/>
<h2 class="display-3" style="color: aliceblue">Registration</h2>
    <div class="container justify-content-center w-50 ">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
            <div class="col ">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h3 class="display-4">Enter your data</h3>
                        <form method="post"  action="${pageContext.request.contextPath}/singUp">
                            <div class="mb-3 ">
                                <label  class="form-label">Login</label>
                                <input type="text" class="form-control" name="login" value=" ${param.login}">
                            </div>
                            <div class="mb-3 ">
                                <label  class="form-label">Email address</label>
                                <input type="text" class="form-control" name="email" value=" ${param.email}">
                            </div>
                            <div class="mb-3 ">
                                <label  class="form-label">First Name</label>
                                <input type="text" class="form-control" name="firstName" value=" ${param.firstName}">
                            </div>
                            <div class="mb-3 ">
                                <label  class="form-label">Last Name</label>
                                <input type="text" class="form-control" name="lastName" value=" ${param.lastName}">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" name="password" >
                            </div>
                            <c:if test="${error != null}">
                                <c:choose>
                                    <c:when test="${error eq 'passwordInvalid' || error eq 'loginInvalid'}">
                                        <div class="alert alert-danger  p-1 " role="alert">
                                            <fmt:message key="${error}"/>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="alert alert-danger  p-1 " role="alert">
                                            <fmt:message key="incorrectInput"/>: "${error}"
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<footer class="text-muted py-1">

    <div class="footer__inner" style="background-color: black; text-align: center; color: aliceblue ">
        &copy; Exhibition Events 2021
    </div>
</footer>
<%--<jsp:include page="common/footer.jsp"/>--%>
</body>
</html>