<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<html>
<head>
    <title>Admin Main Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<c:forEach var="alert" items="${alertService.pull()}">
    <div class="col-6">
        <div class="alert alert-${alert.type}" role="alert">
                ${alert.text}
        </div>
    </div>
</c:forEach>

<div class="container">
    <H1>Admin main Page</H1>
    <div>
        <h2>Department</h2>
        <a href="addNewDepartment.jsp" class="btn btn-secondary">Add department</a>
        <a href="listOfDepartments.jsp"class="btn btn-secondary">List Of departments</a>
    </div>
    <div>
        <h2>Rooms</h2>
        <a href="addNewRoom.jsp"class="btn btn-secondary">Add new room</a>
        <a href="listOfRooms.jsp"class="btn btn-secondary">List of rooms</a>
    </div>
    <div>
        <h2>Personal</h2>
        <a href="addNewEmployee.jsp"class="btn btn-secondary">Add new employee</a>
        <a href="listOfEmployees.jsp"class="btn btn-secondary">list of employees</a>
    </div>
</div>
</body>
</html>