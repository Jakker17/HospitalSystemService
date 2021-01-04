<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="logout" id="logoutForm"></form>
<main class="flex-shrink-0 pozadi">
    <div class="container">
        <h1 class="mt-5">Není přístup</h1>
        <p class="lead">Vaše přístupová práva vám neumožňují používat tento portál.</p>
        <p>Opusťte stránku skrze <button type="submit" class="btn btn-link" form="logoutForm">tento</button> odkaz.</p>
    </div>
</main>
