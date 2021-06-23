<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom_tag.tld" prefix="custom" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <title>MyEvents</title>
</head>
<body style="background-color: black">
<jsp:include page="/common/header2.jsp"/>
<h2 class="display-3" style="color: aliceblue">My events</h2>

<hr/>
<hr>
<div2 class="album py-5 bg-light">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">

            <c:forEach var="item" items="${userExhib.keySet()}">
                <c:set var="tickets" value="${userExhib.get(item)}"/>
                <div class="col">
                    <div class="card shadow-sm">
                        <img class="card-img-top" src=${item.imageUrl} alt="Picture" style="min-height: 220px; max-height: 220px">
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${language != 'uk'}">
                                    <p class="card-text"><strong>${item.theme}</strong></p>
                                    <p class="card-text">${item.date}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="card-text"><strong>${item.themeUk}</strong></p>
                                    <p class="card-text"><custom:formatDate value="${item.date}"
                                                                            pattern="dd/MM/yyyy"/></p>
                                </c:otherwise>
                            </c:choose>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary disabled">Tickets: ${tickets}</button>
                                    <form id="form2" action="${pageContext.request.contextPath}/exhibitionView"></form>
                                    <button form="form2" type="submit" class="btn btn-sm btn-outline-secondary"
                                            name="exId" value="${item.id}"><fmt:message
                                            key="view"/></button>
                                </div>
<%--                                <small class="text-muted">${item.max-item.current} <fmt:message--%>
<%--                                        key="ticketsLeft"/></small>--%>
<%--                                <small class="text-muted">${item.price} <fmt:message key="uah"/></small>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div2>
<hr>
<footer>
<%--    <jsp:include page="/common/footer.jsp"/>--%>
</footer>
</body>
</html>



