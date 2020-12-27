<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 12/20/2020
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="personalService" class="eng.hospitalSystemService.app.PersonalService" scope="request"/>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>List of Employees</title>
</head>
<body>

<c:forEach var="alert" items="${alertService.pull()}">
    <div class="col-6">
        <div class="alert alert-${alert.type}" role="alert">
                ${alert.text}
        </div>
    </div>
</c:forEach>

<h2>List of Employees:</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Birth Number</th>
            <th scope="col">Login</th>
            <th scope="col"></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="personal" items="${personalService.listOfPersonal}">
            <tr>
                <td>${personal.personName}</td>
                <td>${personal.personSurname}</td>
                <td>${personal.birthnumber}</td>
                <td>${personal.loginName}</td>
                <td><a href="editEmployee.jsp?birthNumber=${personal.birthnumber}" class="btn btn-primary">Upravit</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
<a href="index.jsp" class="btn btn-secondary" >Main page</a>
</body>
</html>
