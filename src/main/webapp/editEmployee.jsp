<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 12/17/2020
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personalService" class="eng.hospitalSystemService.app.PersonalService" scope="request"/>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Upravit Zaměstnance</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>

<c:set var="personal" value="${personalService.get(param.birthNumber)}"/>

<div class="container">
    <h1>Upravit zaměstnance</h1>

    <c:forEach var="alert" items="${alertService.pull()}">
        <div class="col-6">
            <div class="alert alert-${alert.type}" role="alert">
                    ${alert.text}
            </div>
        </div>
    </c:forEach>

    <form method="post" action="editEmployee">

        <input type="hidden" name="birthNumber" value="${personal.birthnumber}">
        <input type="hidden" name="profession" value="${personal.proffesion}">
        <input type="hidden" name="salt" value="${personal.saltHash}">
        <input type="hidden" name="loginName" value="${personal.loginName}">



        <div class="form-group">
            <label for="name">Jméno</label>
            <input type="text" name="name" id="name" class="form-control" value="${personal.personName}">
        </div>

        <div class="form-group">
            <label for="surname">Příjmení</label>
            <input type="text" name="surname" id="surname" class="form-control" value="${personal.personSurname}">
        </div>

        <div class="form-group">
            <label for="department">oddělení</label>
            <input type="text" name="department" id="department" class="form-control" value="${personal.department}">
        </div>

        <div class="form-group">
            <label for="password">Heslo</label>
            <input type="password" name="password" id="password" class="form-control" value="${personal.passwordHash}"><br>
            <input type="checkbox"  onclick="myFunction()"> Show Password
            <script>
                function myFunction()
                {
                    var x = document.getElementById("password");
                    if (x.type === "password")
                    {
                        x.type = "text";
                    }
                    else
                        {
                            x.type = "password";
                        }
                }
            </script>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Uložit úpravy</button>
            <a href="index.jsp" class="btn btn-link">Zpět bez uložení</a>
        </div>

    </form>

    <div>
        <form method="post" action="deletePersonal">
            <input type="hidden" name="birthNumber" value="${personal.birthnumber}"/>
            <button type="submit" class="btn btn-sm btn-danger">Smazat profil</button>
        </form>
    </div>

</div>
</body>
</html>
