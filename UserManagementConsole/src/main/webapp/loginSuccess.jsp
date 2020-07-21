<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

	if(session.getAttribute("email") == null)
	{
		response.sendRedirect("login.jsp");
	}
%>

<form action = "updateP" method="post">
<a href="home.jsp">Home</a> | 
<a href="#" onclick="this.parentNode.submit()">Update Profile</a> | 
<a href="contactUs.jsp">Contact Us</a> | 
<a href="logout">Logout</a>
</form>
<br>
${user}
${error}<br>
Email: ${email}<br>
Name: ${name}<br>
Address: ${address}<br>
<form action = "updateP" method="post">
<input type = "submit" value = "Update Profile">
 <input type="hidden" name="email" value="${email}">
 <input type="hidden" name="name" value="${uername}">
 <input type="hidden" name="password" value="${password}">
</form>


<form action = "logout" method="post">
<input type = "submit" value = "Logout">
</form>
</body>
</html>