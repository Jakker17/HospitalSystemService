<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="authorizationService" class="eng.hospitalSystemService.app.AuthorizationService" />
<c:set var="loggedUser" value="${authorizationService.getLoggedUser(pageContext.request)}"/>


<html>
<head>
    <title>Hlavní stránka</title>
    <link rel="stylesheet" href="main.css" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body class="pozadi">
    <c:if test="${not empty loggedUser}">
        <c:choose>
            <c:when test="${authorizationService.isLoggedAdmin(pageContext.request)}">
                <jsp:include page="adminMenuNav.jsp"/>
                <div class="container">
                    <div class="row">
                        <div class="col-sm"></div>
                        <div class="col-sm"><jsp:include page="alertPanel.jsp"/></div>
                        <div class="col-sm"></div>
                    </div>
                </div>
                <jsp:include page="alertPanel.jsp"/>
            </c:when>
            <c:when test="${authorizationService.isLoggedMedicalStaff(pageContext.request)}">
                <jsp:include page="employeeMenuNav.jsp"/>
                <div class="container">
                    <div class="row">
                        <div class="col-sm"></div>
                        <div class="col-sm"><jsp:include page="alertPanel.jsp"/></div>
                        <div class="col-sm"></div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container">
                    <div class="row">
                        <div class="col-sm"></div>
                        <div class="col-sm"><jsp:include page="alertPanel.jsp"/></div>
                        <div class="col-sm"></div>
                    </div>
                </div>
                <jsp:include page="noAccessPageMain.jsp"/>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if  test="${empty loggedUser}">
        <div class="container">
            <div class="row">
                <div class="col-sm"></div>
                <div class="col-sm"><jsp:include page="alertPanel.jsp"/></div>
                <div class="col-sm"></div>
            </div>
        </div>
        <jsp:include page="noLoggedInPage.jsp"/>
    </c:if>
</body>
</html>
