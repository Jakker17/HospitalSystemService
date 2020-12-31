<%--
  Created by IntelliJ IDEA.
  User: juros
  Date: 12/25/2020
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="alertService" class="eng.hospitalSystemService.app.AlertService" scope="session"/>
<jsp:useBean id="departmentService" class="eng.hospitalSystemService.app.DepartmentService" scope="request"/>
<html>
<head>
    <title>Departments list</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous">
    </script>
</head>
<body>
<c:forEach var="alert" items="${alertService.pull()}">
    <div class="col-6">
        <div class="alert alert-${alert.type}" role="alert">
                ${alert.text}
        </div>
    </div>
</c:forEach>

<h2>List of Departments</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID of Department</th>
        <th scope="col">Name of Department</th>
        <th scope="col"></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="department" items="${departmentService.listOfDepartments}">
    <tr>
        <td>${department.idoddeleni}</td>
        <td>${department.nazevoddeleni}</td>
        <td>
            <form id="deleteForm" method="post" action="deleteDepartment">
                <input type="hidden" name="departmentID" value="${department.idoddeleni}" />
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
                                Are you sure you want to delete department  <c:out value="${department.nazevoddeleni}"/>?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                <button type="submit" class="btn btn-primary" form="deleteForm">Yes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<a href="mainPage.jsp" class="btn btn-secondary" >Main page</a>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
</body>
</html>
