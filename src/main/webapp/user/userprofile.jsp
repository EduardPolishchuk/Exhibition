<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<c:set var="vari" value="${not empty param.edit ? null : 'disabled'}" scope="session"/>
<html>
<head>
    <title>MyProfile</title>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="/common/header2.jsp"/>
<%--<h2 class="display-3" style="color: aliceblue">PROFILE</h2>--%>
<div class="container justify-content-center w-50 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="display-4">Enter your data</h3>
                    <form method="post" action="${pageContext.request.contextPath}/user/update">
                        <div class="mb-3 ">
                            <label class="form-label">Email address</label>
                            <input type="text" class="form-control" name="email" value="${userProfile.email}" ${vari}>
                        </div>
                        <div class="mb-3 ">
                            <label class="form-label">First Name</label>
                            <input type="text" class="form-control" name="firstName" value="${userProfile.firstName}"
                            ${vari}>
                        </div>
                        <div class="mb-3 ">
                            <label class="form-label">Last Name</label>
                            <input type="text" class="form-control" name="lastName"
                                   value="${userProfile.lastName}" ${vari}>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1"
                                   name="password" ${vari}>
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
                        <button type="submit" class="btn btn-primary"  ${vari}>Update</button>
                    </form>
                    <form action="">
                        <button type="submit" class="btn btn-dark" name="edit" value="${vari != null  ? '1' : null}">Edit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="text-muted py-5">

    <div class="footer__inner" style="background-color: black; text-align: center; color: aliceblue ">
        &copy; Exhibition Events 2021
    </div>
</footer>
</body>
</html>