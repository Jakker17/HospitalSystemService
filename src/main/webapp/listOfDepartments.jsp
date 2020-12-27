<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 12/25/2020
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<jsp:useBean id="departmentService" class="eng.hospitalSystemService.app.DepartmentService" scope="request"/>
<html>
<head>
    <title>Departments list</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<c:forEach var="alert" items="${alertService.pull()}">
    <div class="col-6">
        <div class="alert alert-${alert.type}" role="alert">
                ${alert.text}
        </div>
    </div>
</c:forEach>

<h2>List of Departments</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID of Department</th>
        <th scope="col">Name of Department</th>
        <th scope="col"></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="department" items="${departmentService.listOfDepartments}">
    <tr>
        <td>${department.idoddeleni}</td>
        <td>${department.nazevoddeleni}</td>
        <td>
            <form method="post" action="deleteDepartment">
            <input type="hidden" name="departmentID" value="${department.idoddeleni}"/>
            <button type="submit" class="btn btn-sm btn-danger">Smazat profil</button>
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<a href="index.jsp" class="btn btn-secondary" >Main page</a>
</body>
</html>
