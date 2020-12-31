<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 11/28/2020
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<html>
<head>
    <title>Přidat nového zaměstnance</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <h1>Přidání nového zaměstnance</h1>

    <c:forEach var="alert" items="${alertService.pull()}">
        <div class="col-6">
            <div class="alert alert-${alert.type}" role="alert">
                    ${alert.text}
            </div>
        </div>
    </c:forEach>

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
            <input type="checkbox"  onclick="myFunction()"> Show Password

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
            &nbsp;
            <a href="mainPage.jsp" class="btn btn-link" >Zpět bez uložení</a>
        </div>
    </form>
</div>
</body>
</html>
