<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("employee")== null) )
{
%>
<jsp:forward page="index.jsp"></jsp:forward>
<%} %>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Welcome <%=session.getAttribute("employee") %>, here is your information

<table id="table" border="1">
<tr id="ttr">
<th> Description </th>
<th> Status </th>
</tr>
<tr>
<td> <%=session.getAttribute("employee") %> </td>
<td> Employee </td>
</tr>
</table>

Update name:
 <form method="post" action="UpdateInfo">
Name
<input type="text" name="user" required/>
<input type="submit" value="Change"></input><input type="reset" value="Reset"></input>
        </form>
        
<div style="text-align: right"><a href="<%=request.getContextPath()%>/employeeProfile.jsp">Back</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Logout">Logout</a></div>
</body>
</html>