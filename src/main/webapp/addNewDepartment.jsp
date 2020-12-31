<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 12/25/2020
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<html>
<head>
    <title>Přidat nové oddělení</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Přidání nového oddělení</h1>
    <c:forEach var="alert" items="${alertService.pull()}">
        <div class="col-6">
            <div class="alert alert-${alert.type}" role="alert">
                    ${alert.text}
            </div>
        </div>
    </c:forEach>
<form method="post" action="addNewDepartment">
<div class="form-group">
    <label for="departmentNumber">Číslo oddělení</label>
    <input type="text" name="departmentNumber" id="departmentNumber" class="form-control">
</div>
<div class="form-group">
    <label for="departmentName">Název Oddělení</label>
    <input type="text" name="departmentName" id="departmentName" class="form-control">
</div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary">Přidat oddělení</button>
        <a href="mainPage.jsp" class="btn btn-secondary" >Zpět bez uložení</a>
    </div>
    </form>
</div>

</body>
</html>
