<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<html>
<head>
    <title>Přidat nové oddělení</title>
    <link href="main.css" rel="stylesheet" type="text/css">
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

                <h2 align="center">Přidání nového oddělení</h2>

                <div class="container">
                    <div class="row">
                        <div class="col-1"></div>
                        <div class="col-10">
                            <form method="post" action="addNewDepartment">
                                <div class="form-group">
                                    <label for="departmentNumber">Číslo oddělení</label>
                                    <input type="text" name="departmentNumber" id="departmentNumber" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="departmentName">Název oddělení</label>
                                    <input type="text" name="departmentName" id="departmentName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">Přidat</button>
                                    <a href="mainPage.jsp" class="btn btn-secondary" >Zpět bez uložení</a>
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
