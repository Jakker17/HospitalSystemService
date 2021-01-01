<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>


<html>
<head>
    <title>Hlavní stránka</title>
    <link rel="stylesheet" href="main.css" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body>
<jsp:include page="alertPanel.jsp"/>
    <c:if test="${not empty loggedUser}">
        <c:choose>
            <c:when test="${authorizationService.isLoggedAdmin(pageContext.request)}">
                <jsp:include page="adminMenuNav.jsp"/>
            </c:when>
            <c:when test="${authorizationService.isLoggedMedicalStaff(pageContext.request)}">

            </c:when>
            <c:otherwise>
                Jako technický zaměstnanec nemáte přústup k této části systému
            </c:otherwise>
        </c:choose>
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
    <c:if  test="${empty loggedUser}">
        PROSÍM PŘIHLASTE SE, ABY JSTE MĚL PŘÍSTUP. <a href="index.jsp" class="btn btn-primary">Přihlásit se</a>
    </c:if>
</body>
</html>
