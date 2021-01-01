
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <title>index</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body>
<jsp:include page="alertPanel.jsp"/>
<div class="container-fluid">

    <c:if test="${not empty loggedUser}">
        Logged in <i>${loggedUser.loginName}</i> as ${loggedUser.proffesion}

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
        <a href="index.jsp">Log IN</a></br>

    </c:if>
</div>
</body>
</html>
