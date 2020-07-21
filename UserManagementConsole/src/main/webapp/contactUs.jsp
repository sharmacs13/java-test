<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Us Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<style>
<body>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>
<form action = "updateP" method="post">
<a href="home.jsp">Home</a> | 
<a href="#" onclick="this.parentNode.submit()">Update Profile</a> | 
<a href="contactUs.jsp">Contact Us</a> |
<a href="logout">Logout</a>
</form>
<br>
${msg}
<br>
<div class="container">
  <form action="contact" method = "post">
    <label for="name">Email</label>
    <input type="text" id="email" name="email" value = "${email}" readonly>

    <label for="subject">Subject</label>
    <input type="text" id="subject" name="subject" placeholder="Subject">


    <label for="message">Message</label>
    <textarea id="message" name="message" placeholder="Write something.." style="height:200px"></textarea>

    <input type="submit" value="Submit">
  </form>
</div>

</body>
</body>
</html>