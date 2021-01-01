<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>

<jsp:include page="alertPanel.jsp"/>

<div class="container">
    <h2>Patient</h2>
    <a href="addNewPatient.jsp" class="btn btn-secondary">Add new patient</a>
    <a href="listOfPatients.jsp" class="btn btn-secondary">List of patients</a>
    <h2>Prescription</h2>
    <a href="addNewPrescription.jsp" class="btn btn-secondary">Add new prescription</a>
</div>
</body>
</html>