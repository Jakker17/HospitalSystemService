<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<jsp:useBean id="patientService" class="eng.hospitalSystemService.app.PatientService" scope="request"/>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <title>Přidat pacienta</title>
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
                <h1> Nový pacient</h1>
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
                        <label for="personalSurname">prijmeni ošetřovatele</label>
                        <input type="text" name="personalSurname" id="personalSurname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="roomID">Číslo pokoje</label>
                        <input type="text" name="roomID" id="roomID" class="form-control">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Přidat</button>
                        <a href="mainPage.jsp" class="btn btn-link" >Zpět bez uložení</a>
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
</body>
</html>
