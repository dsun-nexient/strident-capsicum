<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<script>
		var user = 
		{	
		};

		var http = new XMLHttpRequest();
		var url = "http://localhost:8080/v1/users";
		http.open("POST", url, true);
		http.setRequestHeader("application/json");
		http.send(user);
	</script>


	<div ng-app="">
		<label>Add new user.</label>
		<table border=1 cellspacing=1>
			<tr>
				<th>Role: </th>
				<td><select ng-model="role">
						<option value="User" selected = "selected">User</option>
						<option value="Admin">Admin</option>
				</select></td>
			</tr>
			<tr>
				<th>Username: </th>
				<td><input type = "text" ng-model="username"/></td>
			</tr>
			<tr>
				<th>Password: </th>
				<td><input type = "password" ng-model="password"/></td>
			</tr>
		</table>
		<button onclick="http.send(JSON.stringify(user));">Submit</button>
	</div>
</body>
</html>