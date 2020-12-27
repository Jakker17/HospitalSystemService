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
    <!-- Import jquery cdn -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous">
    </script>
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
        <form id="deleteForm" method="post" action="deletePersonal">
            <input type="hidden" name="birthNumberDelete" value="${personal.birthnumber}" />
            <button class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" type="button">Delete</button>
            <!-- Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">Delete</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to delete personal <c:out value="${personal.personName}"/><c:out value="${personal.personSurname}"/>?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                            <button type="submit" class="btn btn-primary" form="deleteForm">Yes</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
        integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous">
</script>
</body>
</html>
