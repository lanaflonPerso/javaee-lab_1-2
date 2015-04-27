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
    <div class="container" class="">
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
    <h1 class="text-center">Add a new Car</h1>
    <c:choose>
        <c:when test="${param.error == 'true'}">
            <div class="alert alert-dismissible alert-danger car_operation_messagebox">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <strong>Incorrect input!</strong>
                Please check your data and try to add the car again.
            </div>
        </c:when>
    </c:choose>
    <div class="txt">
        <form class="form-horizontal" method="post" action="add_car_action">
            <fieldset>
                <div class="form-group">
                    <label for="brand" class="col-lg-2 control-label">Brand</label>
                    <div class="col-lg-10">
                        <input name ="brand" id="brand" placeholder="Brand" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="model" class="col-lg-2 control-label">Model</label>
                    <div class="col-lg-10">
                        <input name ="model" id="model" placeholder="Model" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="year" class="col-lg-2 control-label">Year</label>
                    <div class="col-lg-10">
                        <input name ="year" id="year" placeholder="Year" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="speed" class="col-lg-2 control-label">Max speed</label>
                    <div class="col-lg-10">
                        <input name ="speed" id="speed" placeholder="Max speed" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-lg-2 control-label">Price ($ / day)</label>
                    <div class="col-lg-10">
                        <input name ="price" id="price" placeholder="Price ($ / day)" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="reset" class="btn btn-default">Cancel</button>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
