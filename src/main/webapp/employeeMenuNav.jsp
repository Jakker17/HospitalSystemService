<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>

<form method="post" action="logout" id="logoutForm"></form>
    <div class="navigace">
        <a href="mainPage.jsp" class="btn btn-link">Hlavní stránka</a>
        <div class="seznam">
            <button class="seznamRozbalit btn btn-link">Pacient<i class="fa fa-caret-down"></i></button>
            <div class="seznam-content">
                <a href="addNewPatient.jsp">Přidat pacienta</a>
                <a href="listOfPatients.jsp">Seznam pacientů</a>
                <a href="myPatients.jsp">Moji pacienti</a><!-- musím dodělat -->
            </div>
        </div>
        <div class="seznam">
            <button class="seznamRozbalit btn btn-link">Oddělení<i class="fa fa-caret-down"></i>
            </button>
            <div class="seznam-content">
                <a href="listOfDepartments.jsp">Seznam oddělení</a>
                <a href="myDepartment.jsp">Moje oddělení</a><!-- musím dodělat -->
            </div>
        </div>
        <div align="right"><button type="submit" class="btn btn-link" form="logoutForm">Odhlásit</button></div>
    </div>
