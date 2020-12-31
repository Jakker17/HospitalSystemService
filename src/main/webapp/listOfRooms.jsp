<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<jsp:useBean id="roomService" scope="request" class="eng.hospitalSystemService.app.RoomService"/>
<html>
<head>
    <title></title>
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
    <table class="table">
        <thead>
        <th scope="col">room ID</th>
        <th scope="col">Capacity</th>
        <th scope="col">Department</th>
        <th scope="col"></th>
        </thead>
        <tbody>
        <c:forEach var="room" items="${roomService.allRooms}">
            <tr>
                <td>${room.roomid}</td>
                <td>${room.capacity}</td>
                <td>${room.departmentid}</td>
                <td>
                    <a href="editRoom.jsp?roomid=${room.roomid}" class="btn btn-primary">Edit room</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="mainPage.jsp" type="btn btn-secondary">Main page</a>
</div>
</body>
</html>