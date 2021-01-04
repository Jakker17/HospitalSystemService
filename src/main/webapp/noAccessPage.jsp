<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="flex-shrink-0 pozadi">
    <div class="container">
        <h1 class="mt-5">Není přístup</h1>
        <p class="lead">Vaše přístupová práva vám neumožňují přístup na tuto stránku.</p>
        <p>Vraťte se zpět přes <a href="mainPage.jsp">tento</a> odkaz.</p>
    </div>
</main>
