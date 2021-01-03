<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<jsp:useBean id="departmentService" class="eng.hospitalSystemService.app.DepartmentService" scope="request"/>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<jsp:useBean id="roomService" class="eng.hospitalSystemService.app.RoomService"/>
<jsp:useBean id="patientService" class="eng.hospitalSystemService.app.PatientService" scope="request"/>
<jsp:useBean id="personalService" class="eng.hospitalSystemService.app.PersonalService" scope="request"/>
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <title>Moje Oddělení</title>
    <link href="main.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>
<body class="pozadi">
<c:set var="personal" value="${personalService.get(param.personalBN)}"/>
<c:set var="department" value="${departmentService.get(personal.department)}"/>
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
            <h2 align="center">Seznam oddělení</h2>
            <div class="container">
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <h2 align="center">Seznam oddělení</h2>
                        <table class="table table-light table-striped table-hover">
                            <thead>
                            <tr>
                                <th scope="col">ID of Department</th>
                                <th scope="col">Name of Department</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="department" items="${departmentService.listOfDepartments}">
                                <tr>
                                    <td>${department.idoddeleni}</td>
                                    <td>${department.nazevoddeleni}</td>
                                    <td align="right">
                                        <a href="departmentInventory.jsp?departmentID=${department.idoddeleni}" class="btn btn-primary">Inventář</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-1"></div>
                </div>
            </div>
        </c:when>
        <c:when test="${authorizationService.isLoggedMedicalStaff(pageContext.request)}">
            <jsp:include page="employeeMenuNav.jsp"/>
            <div class="container">
                <div class="row">
                    <div class="col-sm"></div>
                    <div class="col-sm"><jsp:include page="alertPanel.jsp"/></div>
                    <div class="col-sm"></div>
                </div>
            </div>
            <div class="container">
                <h1 align="center">Moje Oddělení</h1>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <h2 align="center">Seznam pacientů</h2>
                        <table class="table table-light table-striped table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Příjmení</th>
                                <th scope="col">Jméno</th>
                                <th scope="col">Rodné číslo</th>
                                <th scope="col">Pokoj</th>
                                <th scope="col">Ošetřovatel</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="patient" items="${patientService.getAllByDepartment(personal.department)}">
                                <tr>
                                    <td>${patient.pacientPersonSurname}</td>
                                    <td>${patient.pacientPersonName}</td>
                                    <td>${patient.pacientBirthnumber}</td>
                                    <td>${patient.roomid}</td>
                                    <td>${personal.personName} ${personal.personSurname}</td>
                                    <td align="right"><a href="editPatient.jsp?pacientBirthNumber=${patient.pacientBirthnumber}" class="btn btn-primary">Upravit</a><a href="listOfPrescriptions.jsp?pacientBirthNumber=${patient.pacientBirthnumber}" class="btn btn-secondary">Předpisy</a></td>
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
                        <h2 align="center">Seznam pokojů</h2>
                        <table class="table table-light table-striped table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Pokoj</th>
                                <th scope="col">Kapacita</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="room" items="${roomService.getAllRoomsByDepartment(personal.department)}">
                                <tr>
                                    <td>${room.roomid}</td>
                                    <td>${room.capacity}</td>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-1"></div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <jsp:include page="noAccessPage.jsp"/>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if  test="${empty loggedUser}">
    <jsp:include page="noLoggedInPage.jsp"/>
</c:if>

</body>
</html>