<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="patientService" class="eng.hospitalSystemService.app.PatientService" scope="request"/>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<html>
<head>
    <title>List of Patients</title>
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

<h2>List of Patients</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Surname</th>
        <th scope="col">Name</th>
        <th scope="col">Birth Number</th>
        <th scope="col">Pokoj</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="patient" items="${patientService.listOfPatient}">
        <tr>
            <td>${patient.pacientPersonSurname}</td>
            <td>${patient.pacientPersonName}</td>
            <td>${patient.pacientBirthnumber}</td>
            <td>${patient.roomid}</td>
            <td><a href="editPatient.jsp?pacientBirthNumber=${patient.pacientBirthnumber}" class="btn btn-primary">Upravit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="index.jsp" class="btn btn-secondary" >Main page</a>
</body>
</html>
