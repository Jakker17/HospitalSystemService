<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<jsp:useBean id="roomService" scope="request" class="eng.hospitalSystemService.app.RoomService"/>
<jsp:useBean id="patientService" scope="request" class="eng.hospitalSystemService.app.PatientService"/>
<jsp:useBean id="personalService" scope="request" class="eng.hospitalSystemService.app.PersonalService"/>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <title>Rozpis Pokoje</title>
    <link href="main.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body class="pozadi">
<c:set var="room" value="${roomService.getRoom(param.roomID)}"/>

<c:if test="${not empty loggedUser}">
    <c:choose>
        <c:when test="${authorizationService.isLoggedAdmin(pageContext.request)}">
            <jsp:include page="adminMenuNav.jsp"/>
            <jsp:include page="noAccessPage.jsp"/>
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
                <h1 align="center">Pokoj ${room.roomid}</h1>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <table class="table table-light table-striped table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Příjmení</th>
                                <th scope="col">Jméno</th>
                                <th scope="col">Rodné číslo</th>
                                <th scope="col">Ošetřovatel</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="patient" items="${patientService.getAllByRoom(room.roomid)}">
                                <c:set var="personal" value="${personalService.get(patient.nursingStaffBirthnumber)}"/>
                                <tr>
                                    <td>${patient.pacientPersonSurname}</td>
                                    <td>${patient.pacientPersonName}</td>
                                    <td>${patient.pacientBirthnumber}</td>
                                    <td>${personal.personName} ${personal.personSurname}</td>
                                    <td align="right"><a href="editPatient.jsp?pacientBirthNumber=${patient.pacientBirthnumber}" class="btn btn-primary">Upravit</a><a href="listOfPrescriptions.jsp?pacientBirthNumber=${patient.pacientBirthnumber}" class="btn btn-secondary">Předpisy</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-1"></div>
                    <a href="myDepartment.jsp?personalBN=${loggedUser.birthnumber}" class="btn btn-secondary">Zpět</a>
                </div>
        </c:when>
        <c:otherwise>
            <jsp:include page="noAccessPageMain.jsp"/>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if  test="${empty loggedUser}">
    <jsp:include page="noLoggedInPage.jsp"/>
</c:if>
</body>
</html>