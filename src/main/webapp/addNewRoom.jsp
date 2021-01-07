<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <title>Přidat nový pokoj</title>
    <link rel="stylesheet" href="main.css" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body class="pozadi">

<c:if test="${not empty loggedUser}">
    <c:choose>
        <c:when test="${authorizationService.isLoggedAdmin(pageContext.request)}">
            <jsp:include page="adminMenuNav.jsp"/>
            <div class="container">
                <div class="row">
                    <div class="col-sm"></div>
                    <div class="col-sm"><jsp:include page="alertPanel.jsp"/></div>
                    <div class="col-sm"></div>
                </div>
            </div>

            <h2 align="center">Přidat pokoj</h2>

            <div class="container">
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <form id="addNewRoom" action="addNewRoom" method="post">
                            <div class="form-group">
                                <label for="roomID">ID pokoje</label>
                                <input type="text" name="roomID" id="roomID" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="capacityOfRoom">Kapacita pokoje</label>
                                <input type="text" name="capacityOfRoom" id="capacityOfRoom" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="departmentID">ID oddělení</label>
                                <input type="text" name="departmentID" id="departmentID" class="form-control">
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Přidat pokoj</button>
                                <a href="mainPage.jsp" class="btn btn-secondary">Odejít bez uložení</a>
                            </div>
                        </form>
                    </div>
                    <div class="col-1"></div>
                </div>
            </div>
        </c:when>
        <c:when test="${authorizationService.isLoggedMedicalStaff(pageContext.request)}">
            <jsp:include page="employeeMenuNav.jsp"/>
            <jsp:include page="noAccessPage.jsp"/>
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