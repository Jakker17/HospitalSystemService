<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 12/17/2020
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<html>


<head>
    <title>index</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body>
<div class="container-fluid">
<c:forEach var="alert" items="${alertService.pull()}">
    <div class="col-6">
        <div class="alert alert-${alert.type}" role="alert">
            ${alert.text}
        </div>
    </div>
</c:forEach>

<br class="container">
    <h1>Hospital System</h1>

    <a href="listOfEmployees.jsp">Seznam zaměstnanců</a></br>
    <a href="listOfRooms.jsp">Seznam Pokojů</a></br>
    <a href="listOfDepartments.jsp">Seznam Oddělení</a></br>
    <a href="listOfPatients.jsp">Seznam pacientů</a></br>
    <a href="addNewEmployee.jsp">Přidat zaměstnance</a></br>
    <a href="addNewDepartment.jsp">Přidat Oddělení</a></br>
    <a href="addNewPatient.jsp">Přidat pacienta</a></br>
    <a href="addNewPrescription.jsp">Přidat předpis</a></br>
    <a href="addNewRoom.jsp">Přidat Pokoj</a></br>

</div>
</body>
</html>
