<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale/resources"/>


<html>
<head>
    <title>Add New Event</title>
    <jsp:include page="/common/windowstyle.jsp"/>
</head>
<body>
<jsp:include page="/common/header2.jsp"/>
<div class="container justify-content-center w-50 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">

                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Theme</label>
                            <input type="text" class="form-control " name="email" value=""
                                   disabled>
                        </div>
                        <div class="col">
                            <label class="form-label">Theme Uk</label>
                            <input type="text" class="form-control " name="email" value=""
                                   disabled>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Date</label>
                            <input type="text" class="form-control " name="email" value=""
                                   disabled>
                        </div>
                        <div class="col">
                            <label class="form-label">Price</label>
                            <input type="text" class="form-control " name="email" value=""
                                   disabled>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <input type="text" class="form-control  p-5" name="email" value=""
                               disabled>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description Uk</label>
                        <input type="text" class="form-control  p-5" name="email" value=""
                               disabled>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Image Url</label>
                        <input type="text" class="form-control " name="email" value=""
                               disabled>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value=""
                               id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Red
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Blue
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked2" checked>
                        <label class="form-check-label" for="flexCheckChecked">
                            Green
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>