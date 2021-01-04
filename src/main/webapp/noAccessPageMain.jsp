<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="logout" id="logoutForm">
<main class="flex-shrink-0 pozadi">
    <div class="container">
        <h1 class="mt-5">No access</h1>
        <p class="lead">Your access rights are not enough to access this portal.</p>
        <p>leave via <button type="submit" class="btn btn-link" form="logoutForm">this</button> link.</p></form>
    </div>
</main>
