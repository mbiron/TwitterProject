<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<%@	page import="isep.tweetproject.client.Tweet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweets</title>
</head>
<body>
	<%
		Map<String, List<Tweet>> map = (Map<String, List<Tweet>>) request
				.getAttribute("map");
		for (Entry<String, List<Tweet>> entry : map.entrySet()) {
	%>
	<br>
	<div>
		Displaying tweets for user
		<%=entry.getKey()%></div>
	<br>
	<%
		List<Tweet> tweets = (List<Tweet>) entry.getValue();
			for (int i = 0; i < tweets.size(); i++) {
	%>
	<div><%=tweets.get(i)%></div>
	<%
		}
		}
	%>
	<br>
	<a href="http://localhost:8080/TweetProjectClient/ClientServlet">
		"Back"</a>
</body>
</html>