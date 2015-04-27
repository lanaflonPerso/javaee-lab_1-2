<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JavaEE lab2 - Servlets/JSP</title>
    <meta name="author" content="Vadym Pechenoha">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="/" class="navbar-brand">Rent Car</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/cars.jsp">Car List</a>
                </li>
                <li>
                    <a href="/add_car.jsp">Add New Car</a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">About</a></li>
                <li><a href="#">Help</a></li>
            </ul>

        </div>
    </div>
</div>
<div class="container main_container">
    <c:if test="${param.car_added != null}">
        <div class="alert alert-dismissible alert-info car_added_info">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>Congratulations!</strong>
            The car was successfully added to the database :)
        </div>
    </c:if>

    <h1 class="text-center">Our Cars:</h1>
    <div class="txt">
        ${param.test}
        <jsp:useBean id="model" class="lab2.CarModel" />
        <table class="table table-striped table-hover ">
            <thead>
            <tr class="success">
                <th>#</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Year</th>
                <th>Max Speed</th>
                <th>Price ($ / day)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${model.cars}">
            <tr>
                <td>${item.id}</td>
                <td>${item.brand}</td>
                <td>${item.model}</td>
                <td>
                    <c:choose>
                        <c:when test="${item.year != null}">
                            <c:set var="year" value="${item.year}" />
                            <fmt:formatDate value="${year}" pattern="yyyy" />
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><c:out value="${item.speed}" default="-" /></td>
                <td><c:out value="${item.price}" default="-" /></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
