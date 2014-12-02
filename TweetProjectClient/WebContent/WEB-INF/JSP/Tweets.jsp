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

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>

</head>
<body>
	<div style="width:50%; margin : auto;">
		<%
			Map<String, List<Tweet>> map = (Map<String, List<Tweet>>) request
					.getAttribute("map");
			if(map==null || map.isEmpty()){
				String error = (String) request.getAttribute("errorNickname");
		%>
				<br>
				<div>
					<%=error%>
				</div>
		<%
			}else{
				for (Entry<String, List<Tweet>> entry : map.entrySet()) {
		%>
					<br>
					<div Style="color:red">
						<b>Displaying tweets for user
						<%=entry.getKey()%>
						</b>
					</div>
					<br>
					<%
					List<Tweet> tweets = (List<Tweet>) entry.getValue();%>
					<table>
			  		<tr>
					    <th>Id</th>
					    <th>Author's Id</th>		
					    <th>Message</th>
				     	<th>Date</th>
				 	</tr>
					<%for (int i = 0; i < tweets.size(); i++) {
						%>
						<tr>
						    <td><%=tweets.get(i).getId()%></td>
						    <td><%=tweets.get(i).getAuthorId()%></td>		
						    <td><%=tweets.get(i).getMessage()%></td>
					    	<td><%=tweets.get(i).getDate()%></td>
				  		</tr>
					<%
					}%>
					</table>
				<%}
			}
		%>
		<br>
		<a href="http://localhost:8080/TweetProjectClient/ClientServlet">
			"Back"</a>
	</div>
</body>
</html>