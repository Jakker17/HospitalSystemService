<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<html>
<head>
    <title>Add New Prescription</title>
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
    <h1>Add new Prescription</h1>
    <form id="addNewPrescription" method="post" action="addNewPrescription">
        <div class="form-group">
            <label for="nameOfMedicament">Name of Medicament</label>
            <input name="nameOfMedicament" id="nameOfMedicament" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="usageOfMedicament">Usage of Medicaments</label>
            <input name="usageOfMedicament" id="usageOfMedicament" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="birthNumberOfPatient">BN of patient</label>
            <input type="text" id="birthNumberOfPatient" name="birthNumberOfPatient" class="form-control">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Add</button>
            <a href="index.jsp" class="btn btn-secondary">Main Page</a>
        </div>
    </form>
</div>
</body>
</html>