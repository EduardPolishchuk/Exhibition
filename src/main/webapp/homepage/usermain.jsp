<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<html>
<head>
    <title>Title</title>
    <jsp:include page="/common/tablestyle.jsp"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<table>
    <thead>
    <tr>
        <th>Theme</th>
        <th>Halls</th>
    </tr>
    </thead>
<tbody>
<h1>${expoList}</h1>
<img  src="https://upload.wikimedia.org/wikipedia/commons/a/a5/Exposition_%22Gauguin%2C_Van_Gogh%2C_les_peintres_de_la_couleur%22.jpg">
<c:forEach var="exp" items="${expoList}" step="1" varStatus="status">
    <tr>

        <td>${exp.theme}</td>
        <td>${exp.halls}</td>
    </tr>
</c:forEach>
<img  src="https://news.artnet.com/app/news-upload/2018/04/VISUEL-NU-e1523542093641.jpg"/>
<img  src="https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg"/>
</tbody>
</table>

</body>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</html>
