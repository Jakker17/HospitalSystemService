<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<jsp:include page="alertPanel.jsp"/>

<div class="container">
    <h1>LOG IN</h1>
    <form method="post" action="login">
        <div class="form-group">
            <label for="loginName">LOGIN</label>
            <input id="loginName" type="text" class="form-control" name="loginName">
        </div>
        <div class="form-group">
            <label for="password">PASSWORD</label>
            <input id="password" type="password" class="form-control" name="password">
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
            <button type="submit" class="btn btn-primary">LOG IN</button>
        </div>
    </form>
</div>
</body>
</html>