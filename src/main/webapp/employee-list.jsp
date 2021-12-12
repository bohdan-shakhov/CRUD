<%@ page import="java.util.List" %>
<%@ page import="com.acolyte.crudexample.model.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>Employee Management Application</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
	<nav class="navbar navbar-expand-md navbar-dark" style="background-color: midnightblue">

		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employees</a></li>
		</ul>
	</nav>
</header>
<br>

<div class="row">

	<div class="container">
		<h3 class="text-center">List of Employees</h3>
		<hr>
		<div class="container text-left">

			<a href="<%=request.getContextPath()%>/employee-form.jsp" class="btn btn-success">Add
				New Employees</a>
		</div>
		<br>
		<table class="table table-bordered">
			<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Salary</th>
				<th>Department</th>
			</tr>
			</thead>
			<tbody>

			<c:forEach var="employee" items="${listEmployee}">

				<tr>
					<td>
						<c:out value="${employee.id}" />
					</td>
					<td>
						<c:out value="${employee.name}" />
					</td>
					<td>
						<c:out value="${employee.salary}" />
					</td>
					<td>
						<c:out value="${employee.department}" />
					</td>
					<td><a href="edit?id=<c:out value='${employee.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${employee.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
			</tbody>

		</table>
	</div>
</div>
</body>

</html>
