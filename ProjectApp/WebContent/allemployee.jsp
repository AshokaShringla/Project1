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
<title>All Employees</title>
</head>
<body>
Welcome <%=session.getAttribute("manager") %>, here are all the employees

<table id="table" border="1">
<tr id="ttr">
<th> Name </th>
</tr>
<c:forEach items="${all}" var="requests">
    <tr>
      <td>${requests}</td>
    </tr>
  </c:forEach>
</table>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/managerProfile.jsp">Back</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Logout">Logout</a></div>
</body>
</html>