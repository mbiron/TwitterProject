<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@	page import="isep.tweetproject.client.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
</head>
<body>
<body>
	<%List<User> users = (List<User>) request.getAttribute("users");
	for(int i = 0; i < users.size() ; i++){%>
	<div><%=users.get(i)%></div>
	<%}%>
	<br>
	<a href="http://localhost:8080/TweetProjectClient/ClientServlet"> "Back"</a>
</body>
</body>
</html>