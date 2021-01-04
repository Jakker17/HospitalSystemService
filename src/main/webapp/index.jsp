<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Přihlášení</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="main.css">
    <link href="signIn.css" rel="stylesheet">
</head>

<c:if test="${not empty loggedUser}">
    <%
        response.sendRedirect("mainPage.jsp");
    %>
</c:if>
<body class="text-center pozadi">
<main class="form-signin">
    <jsp:include page="alertPanel.jsp"/>
    <form method="post" action="login">
        <h1 class="h3 mb-3 fw-normal">Přihlášení</h1>
        <input type="text" id="loginName" name="loginName" class="form-control" placeholder="Login" required autofocus>
        <input type="password" id="password" name="password" class="form-control" placeholder="Heslo" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" onclick="myFunction()"> Zobraz heslo
            </label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Přihlásit</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2020-2021</p>
    </form>
</main>
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
</body>
</html>
