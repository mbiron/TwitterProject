<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@	page import="isep.tweetproject.client.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Users</title>
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
		<%List<User> users = (List<User>) request.getAttribute("users"); %>
		<div style="width:50%; margin : auto;">
			<table>
		  		<tr>
				    <th>Id</th>
				    <th>Name</th>		
				    <th>TwitterNickname</th>
			     	<th>Joining Date</th>
			 	</tr>
		  		<%for(int i = 0; i < users.size() ; i++){%>
				<tr>
				    <td><%=users.get(i).getId()%></td>
				    <td><%=users.get(i).getName()%></td>		
				    <td><%=users.get(i).getTwitterNickname()%></td>
			    	<td><%=users.get(i).getJoinedDate()%></td>
			  	</tr>
				<%}%>
			</table>
			<br>
			
		<%-- 	<%for(int i = 0; i < users.size() ; i++){%> --%>
		<%-- 	<div><%=users.get(i)%></div> --%>
		<%-- 	<%}%> --%>
		<!-- 	<br> -->
		
			<a href="http://localhost:8080/TweetProjectClient/ClientServlet"> "Back"</a>
		</div>
	</body>
</html>