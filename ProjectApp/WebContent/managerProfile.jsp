<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Page</title>
</head>
<% //In case, if Admin session is not set, redirect to Login page
if((session.getAttribute("manager")== null) )
{
%>
<jsp:forward page="index.jsp"></jsp:forward>
<%} %>
<body>
<h2>Manager's Home</h2>
 
Welcome <%=session.getAttribute("manager") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/mallrequests.jsp">See All Requests</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/mpendingrequests.jsp">Manage Pending Requests</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/macceptedrequests.jsp">See All Accepted Requests</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/CheckEmployee.jsp">Check Requests of Specific Employee</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/allemployee.jsp">See List of Employees</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Logout">Logout</a></div>
</body>
</html>