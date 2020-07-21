<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Information</title>
</head>
<body>
<%

	if(session.getAttribute("email") == null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<a href="home.jsp">Home</a> | 
<a href="contactUs.jsp">Contact Us</a> |
<a href="logout">Logout</a>
<br>
<form action = "update" method="post">
		<br>
			Name: <input type = "text" name = "username"><br><br><br>
			Email: <input type = "text" name = "email" value = "${email}" readonly style="min-width:10px;"><br><br><br>
			Password: <input type = "text" name = "password"><br><br><br>
			Address: <input type = "text" name = "address"><br><br><br>
			<input type = "submit" value = "Update"><br><br><br>
		</form>
	<form action = "login" method="post">
<input type = "submit" value = "Cancel">
</form>
</body>
</html>