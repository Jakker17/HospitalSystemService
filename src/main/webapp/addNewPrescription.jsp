<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <title>Přidat předpis</title>
    <link href="main.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body class="pozadi">
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
                <h1>Nový Předpis</h1>
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
                        <input type="text" id="birthNumberOfPatient" name="birthNumberOfPatient" class="form-control" readonly value="${param.patientBN}">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Přidat</button>
                        <a href="listOfPrescriptions.jsp?pacientBirthNumber=${param.patientBN}" class="btn btn-secondary">Zpět bez uložení</a>
                    </div>
                </form>
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


<div class="container">

</div>
</body>
</html>