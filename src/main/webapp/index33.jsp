<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<html>
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Exposition Events</title>
    <style>
        div2 {
            background: rgba(255, 255, 255, 0); /* Полупрозрачный фон */
            padding: 1rem; /* Поля вокруг текста */
            border-radius: 5px; /* Радиус скругления */
        }
        
    </style>
</head>
<body style="background-image: url(https://cdn.substack.com/image/fetch/f_auto,q_auto:good,fl_progressive:steep/https%3A%2F%2Fbucketeer-e05bbc84-baa3-437e-9518-adb32be77984.s3.amazonaws.com%2Fpublic%2Fimages%2F97b1c8e4-b31e-42a1-9d93-83fe161f56b2_1920x1075.jpeg)">
<jsp:include page="common/header2.jsp"/>
<h2  class="display-1" style="color: aliceblue"><fmt:message key="welcomeText"/><a href="${pageContext.request.contextPath}/start">START </a></h2>
    <div2 class="album py-5 bg-light" >
        <div class="container">

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:forEach var="item" items="${expoList}" >
                    <div class="col">
                        <div class="card shadow-sm">
                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnaasdasdasdasdasdsdasdasdasdasdasdasdasdasil" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>

                            <div class="card-body">
                                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                    </div>
                                    <small class="text-muted">${item.max-item.current}  залишилось місць</small>
                                    <small class="text-muted">${item.price}  <fmt:message key="uah"/></small>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>

<%--                --%>
<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col">--%>
<%--                    <div class="card shadow-sm">--%>
<%--                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>

<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                            <div class="d-flex justify-content-between align-items-center">--%>
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">9 mins</small>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
        </div>
    </div2>



<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#" style="background-color: black">Back to top</a>
        </p>
    </div>
</footer>


<script src="/docs/5.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>


</body>
</html>