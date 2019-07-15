<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Page</title>
</head>
<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("employee")== null) )
{
%>
<jsp:forward page="index.jsp"></jsp:forward>
<%} %>
<body>
<h2>Employee's Home</h2>
 
Welcome <%=session.getAttribute("username") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/allrequests.jsp">See All Requests</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/pendingrequests.jsp">See Pending Requests</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/acceptedrequests.jsp">See Accepted Requests</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Submit.jsp">Submit a Request</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/information.jsp">See/Update Information</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Logout">Logout</a></div>
</body>
</html>