<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<html>
<head>
    <title>Add new Room</title>
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
        <form id="addNewRoom" action="addNewRoom" method="post">
            <div class="form-group">
                <label for="roomID">Room ID</label>
                <input type="text" name="roomID" id="roomID" class="form-control">
            </div>
            <div class="form-group">
                <label for="capacityOfRoom">Room Capacity</label>
                <input type="text" name="capacityOfRoom" id="capacityOfRoom" class="form-control">
            </div>
            <div class="form-group">
                <label for="departmentID">Department ID</label>
                <input type="text" name="departmentID" id="departmentID" class="form-control">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add Room</button>
                <a href="index.jsp" class="btn btn-secondary">Main page</a>
            </div>
    </form>
</div>
</body>
</html>