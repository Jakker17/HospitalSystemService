<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 12/26/2020
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<html>
<head>
    <title>Add new Patient</title>
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

    <h1>Přidání nového pacienta</h1>

    <form method="post" action="addNewPatient">
        <div class="form-group">
            <label for="patientBirthNumber">Rodné číslo</label>
            <input type="text" name="patientBirthNumber" id="patientBirthNumber" class="form-control">
        </div>
        <div class="form-group">
            <label for="patientName">Jméno</label>
            <input type="text" name="patientName" id="patientName" class="form-control">
        </div>
        <div class="form-group">
            <label for="patientSurname">Příjmení</label>
            <input type="text" name="patientSurname" id="patientSurname" class="form-control">
        </div>
        <div class="form-group">
            <label for="anamnesis">Anamnéza</label>
            <input type="text" name="anamnesis" id="anamnesis" class="form-control">
        </div>
        <div class="form-group">
            <label for="medicaments">Léky</label>
            <input type="text" name="medicaments" id="medicaments" class="form-control">
        </div>
        <div class="form-group">
            <label for="personalSurname">birthNumber ošetřovatele</label>
            <input type="text" name="personalSurname" id="personalSurname" class="form-control">
        </div>
        <div class="form-group">
            <label for="roomID">Číslo pokoje</label>
            <input type="text" name="roomID" id="roomID" class="form-control">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Přidat pacienta</button>
            <a href="index.jsp" class="btn btn-link" >Zpět bez uložení</a>
        </div>
    </form>
</div>
</body>
</html>
