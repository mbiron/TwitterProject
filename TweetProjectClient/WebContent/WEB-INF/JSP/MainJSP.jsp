<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweet Project Menu</title>
</head>
<body>
	<div style="width : 50%; margin : auto; ">
		<h1>TweetProject's Homepage</h1>
		<form name="listUsers" enctype="form-data" method="post"
			action="/TweetProjectClient/ClientServlet" >
			<input type="submit" name="Send" value="List Users"></input><br><br>
			<input type="text" name="username"></input>
			<input type="submit" name="Send" value="Get Users tweets"></input>
<%-- 			<%String message = (String) request.getAttribute("errorNickname"); --%>
<%-- 			if(message != null && !message.isEmpty()){%> --%>
<%-- 			 <font color="red"><%=message%></font>  --%>
<%-- 			<%}%> --%>
			<br><br>
			<input type="submit" name="Send" value="List All Tweets"></input><br><br>
			<input type="submit" name="Send" value="Fill DB"></input><br><br>
		</form>
	</div>
</body>
</html>