<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="2" width="70%" cellpadding="2">  
<tr><th>User Name</th><th>Email Id</th><th>Activity Name</th><th>Amount</th> <th>First Occurrence</th> <th>Last Occurrence</th></tr>  
   <c:forEach var="emp" items="${list}">   
   <tr>  
   <td>${emp.username}</td>  
   <td>${emp.email}</td> 
   <td>${emp.activity_name}</td>  
   <td>${emp.amount}</td>  
   <td>${emp.first}</td>
   <td>${emp.last}</td>  
   </tr>  
   </c:forEach>  
   </table> 
   <br>
   <a href="logout">Logout</a> 
</body>
</html>