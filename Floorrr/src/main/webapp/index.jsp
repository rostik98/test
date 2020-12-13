<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<input type="submit" value="add room" onClick="myFunction()"/>
     <script>
       function myFunction() {
         window.location.href="newRoom.jsp";
       }
     </script>
     <form action="room" method="post">
	<label for="name">Name</label> <input name="name">
	<br>
	<input type="submit" value="DRAW"><br>
	
</form>
</body>