<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<% //In case, if Admin session is not set, redirect to Login page
if((session.getAttribute("manager")== null) )
{
%>
<jsp:forward page="index.jsp"></jsp:forward>
<%} %>
<meta charset="ISO-8859-1">
<title>All Resolved Requests</title>
</head>
<body>
Welcome <%=session.getAttribute("manager") %>, here are all resolved requests

<table id="table" border="1">
<tr id="ttr">
<th> ID </th>
<th> Description </th>
<th> Status </th>
<th> Employee </th>
</tr>
<c:forEach items="${AcceptedRequests}" var="requests">
    <tr>
      <td>${requests.id}</td>
      <td>${requests.description}</td>
      <td>${requests.status}</td>
      <td>${requests.name}</td>
    </tr>
  </c:forEach>
</table>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/managerProfile.jsp">Back</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Logout">Logout</a></div>
</body>
</html>