<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="personalService" class="eng.hospitalSystemService.app.PersonalService" scope="request"/>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <link href="main.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>List of Employees</title>
</head>
<body class="pozadi">
<jsp:include page="alertPanel.jsp"/>
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

            <h2 align="center">Seznam Zaměstnanců</h2>
            <div class="container">
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <table class="table table-light table-striped table-hover">
                            <thead>
                            <tr>
                                <th scope="col">Příjmení</th>
                                <th scope="col">Jméno</th>
                                <th scope="col">Login</th>
                                <th scope="col">Birth Number</th>
                                <th scope="col">Department</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="personal" items="${personalService.listOfPersonal}">
                                <tr>
                                    <td>${personal.personSurname}</td>
                                    <td>${personal.personName}</td>
                                    <td>${personal.loginName}</td>
                                    <td>${personal.birthnumber}</td>
                                    <td>${personal.department}</td>
                                    <td><a href="editEmployee.jsp?birthNumber=${personal.birthnumber}" class="btn btn-primary">Upravit</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>

                        </table>
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
            <jsp:include page="noAccessPage.jsp"/>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if  test="${empty loggedUser}">
    <jsp:include page="noLoggedInPage.jsp"/>
</c:if>
</body>
</html>
