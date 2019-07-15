<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Requests</title>
</head>
<body>
Welcome <%=session.getAttribute("employee") %>, here are your requests

<table id="table" border="1">
<tr id="ttr">
<th> Description </th>
<th> Status </th>
</tr>
<c:forEach items="${AllRequests}" var="requests">
    <tr>
      <td>${requests.description}"</td>
      <td>${requests.status}"</td>
    </tr>
  </c:forEach>
</table>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/employeeProfile.jsp">Back</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/Logout">Logout</a></div>
</body>
</html>