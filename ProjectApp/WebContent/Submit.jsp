<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Transaction Submit Page</title>
</head>
<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("employee")== null) )
{
%>
<jsp:forward page="index.jsp"></jsp:forward>
<%} %>
<body>
<h2>Employee Transaction Submit Page</h2>
 
Welcome <%=session.getAttribute("username") %>

<form method="post" action="Submit">
        <table align="center">
        <tr>
<td>Description (Include monetary amount)</td>
<td><input type="text" name="description" /></td>
</tr>
<tr>
<td><span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value=Submit></input><input type="reset" value="Reset"></input></td>
</tr>
</table>
        </form>


<div style="text-align: right"><a href="<%=request.getContextPath()%>/employeeProfile.jsp">Back</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Logout">Logout</a></div>
</body>
</html>