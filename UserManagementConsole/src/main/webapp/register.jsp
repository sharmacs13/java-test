<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

${error}
		<form action = "addUser" method="post">
		<br>
			Name: <input type = "text" name = "username"><br>
			Email: <input type = "text" name = "email"><br>
			Password: <input type = "text" name = "password"><br>
			Address: <input type = "text" name = "address"><br>			
			<input type = "submit"><br>
		</form>
</body>
</html>