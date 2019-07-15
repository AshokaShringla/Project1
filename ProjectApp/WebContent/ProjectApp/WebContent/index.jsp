<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
        <title>login form</title>
    </head>
    <body>
        <form method="post" action="LoginAction">
        <table align="center">
        <tr>
<td>Username</td>
<td><input type="text" name="user" /></td>
</tr>
<tr>
<td>Password</td>
<td><input type="password" name="pass" /></td>
</tr>
<tr>
<td><span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Login"></input><input type="reset" value="Reset"></input></td>
</tr>
</table>
        </form>
    </body>
</html>
