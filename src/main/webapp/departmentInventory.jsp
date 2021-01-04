<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personalService" class="eng.hospitalSystemService.app.PersonalService" scope="request"/>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<jsp:useBean id="departmentService" class="eng.hospitalSystemService.app.DepartmentService" scope="request"/>
<jsp:useBean id="roomService" class="eng.hospitalSystemService.app.RoomService" scope="request"/>
<html>
<head>
    <title>Inventář oddělení</title>
    <link rel="stylesheet" type="text/css" href="main.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous">
    </script>
</head>
<body class="pozadi">
<c:set var="department" value="${departmentService.get(param.departmentID)}"/>

<c:if test="${not empty loggedUser}">
    <c:choose>
        <c:when test="${authorizationService.isLoggedAdmin(pageContext.request)}">
            <jsp:include page="adminMenuNav.jsp"/>
            <div class="container">
                <div class="row">
                    <div class="col-sm"></div>
                    <div class="col-sm"><jsp:include page="alertPanel.jsp"/></div>
                    <div class="col-sm"></div>
                </div>
            </div>
            <div class="container">
                <h1 align="center">Inventář oddělení ${department.nazevoddeleni} <button class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" type="button">Odstranit</button></h1>
                <form id="deleteForm" method="post" action="deleteDepartment">
                <input type="hidden" name="departmentID" value="${department.idoddeleni}"/>
                <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="myModalLabel">Delete</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Opravdu chcete smazat oddělení <c:out value="${department.nazevoddeleni}"/>?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                <button type="submit" class="btn btn-primary" form="deleteForm">Yes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
                </br></br></br>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <h2 align="center">Zaměstnanci</h2>
                        <table class="table table-light table-striped table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Příjmení</th>
                                <th scope="col">Jméno</th>
                                <th scope="col">Login</th>
                                <th scope="col">Birth Number</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="personal" items="${personalService.getListOfPersonalByDepartment(department.idoddeleni)}">
                                <tr>
                                    <td>${personal.personSurname}</td>
                                    <td>${personal.personName}</td>
                                    <td>${personal.loginName}</td>
                                    <td>${personal.birthnumber}</td>
                                    <td align="right"><a href="editEmployee.jsp?birthNumber=${personal.birthnumber}" class="btn btn-primary">Upravit</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-1"></div>
                </div>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <h2 align="center">Pokoje</h2>
                        <table class="table table-light table-striped table-hover">
                            <thead>
                            <th scope="col">room ID</th>
                            <th scope="col">Capacity</th>
                            <th scope="col">Department</th>
                            <th scope="col"></th>
                            </thead>
                            <tbody>
                            <c:forEach var="room" items="${roomService.getAllRoomsByDepartment(department.idoddeleni)}">
                                <tr>
                                    <td>${room.roomid}</td>
                                    <td>${room.capacity}</td>
                                    <td>${room.departmentid}</td>
                                    <td align="right">
                                        <a href="editRoom.jsp?roomid=${room.roomid}" class="btn btn-primary">Upravit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-1"></div>
                </div>
            </div>
            </div>
        </c:when>
        <c:when test="${authorizationService.isLoggedMedicalStaff(pageContext.request)}">
            <jsp:include page="employeeMenuNav.jsp"/>
            <jsp:include page="noAccessPage.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="noAccessPage.jsp"/>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if  test="${empty loggedUser}">
    <jsp:include page="noLoggedInPage.jsp"/>
</c:if>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
        integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous">
</script>
</body>
</html>