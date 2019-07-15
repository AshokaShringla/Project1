<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<% //In case, if Admin session is not set, redirect to Login page
if((session.getAttribute("manager")== null) )
{
%>
<jsp:forward page="index.jsp"></jsp:forward>
<%} %>
        <title>Check Requests</title>
    </head>
    <body>
        <form method="post" action="CheckEmployee">
        <table align="center">
        <tr>
<td>Username</td>
<td><input type="text" name="user" /></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Check"></input></td>
</tr>
</table>
        </form>
    </body>
</html>
