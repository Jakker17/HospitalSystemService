<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>
<jsp:useBean id="alertService" scope="session" class="eng.hospitalSystemService.app.AlertService"/>

<html class="pozadi">
<head>
    <title>Přidat nového zaměstnance</title>
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
            <div class="container">
                <h1>Přidání nového zaměstnance</h1>
                <form method="post" action="addNewEmployee">
                    <div class="form-group">
                        <label for="birthNumber">Rodné číslo</label>
                        <input type="text" name="birthNumber" id="birthNumber" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="name">Jméno</label>
                        <input type="text" name="name" id="name" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="surname">Příjmení</label>
                        <input type="text" name="surname" id="surname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="department">Oddělení</label>
                        <input type="text" name="department" id="department" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="profession">Profese</label>
                        <input type="list" class="form-control" id="profession" list="professionList" name="profession" >
                        <datalist id="professionList">
                            <option value="Zdravotnicky Personal">
                            <option value="Technicky personal">
                            <option value="admin">
                        </datalist>
                    </div>
                    <div class="form-group">
                        <label for="password">Heslo</label>
                        <input type="password" name="password" id="password" class="form-control"><br>
                        <input type="checkbox"  onclick="myFunction()"> Zobraz heslo
                        <script>
                            function myFunction() {
                                var x = document.getElementById("password");
                                if (x.type === "password") {
                                    x.type = "text";
                                } else {
                                    x.type = "password";
                                }
                            }
                        </script>

                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Přidat zaměstnance</button>
                        <a href="mainPage.jsp" class="btn btn-link" >Zpět bez uložení</a>
                    </div>
                </form>
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
