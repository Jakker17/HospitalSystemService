<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>

<form method="post" action="logout">
<div class="navigace">
    <a href="mainPage.jsp" class="btn btn-link">Hlavní stránka</a>
    <div class="seznam">
        <button class="seznamRozbalit btn btn-link">Personál<i class="fa fa-caret-down"></i></button>
        <div class="seznam-content">
            <a href="addNewEmployee.jsp">Přidat personál</a>
            <a href="listOfEmployees.jsp">Seznam zaměstnanců</a>
        </div>
    </div>
    <div class="seznam">
        <button class="seznamRozbalit btn btn-link">Oddělení<i class="fa fa-caret-down"></i>
        </button>
        <div class="seznam-content">
            <a href="addNewDepartment.jsp">Přidat oddělení</a>
            <a href="listOfDepartments.jsp">Seznam oddělení</a>
        </div>
    </div>
    <div class="seznam">
        <button class="seznamRozbalit btn btn-link">Pokoje<i class="fa fa-caret-down"></i>
        </button>
        <div class="seznam-content">
            <a href="addNewRoom.jsp">Přidat pokoj</a>
            <a href="listOfRooms.jsp">Seznam pokojů</a>
        </div>
    </div>
    <div align="right">
        <button type="submit" class="btn btn-link">Odhlásit</button>
    </div>
</div>
</form>