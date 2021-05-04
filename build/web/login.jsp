<%-- 
    Document   : login.jsp
    Created on : Jan 5, 2021, 5:17:27 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <title>Login Page</title>
        <style>
            body {
                background-image: url('images/login.jpg') ; 
                background-repeat: no-repeat; 
                background-size: 100% 100%; 
                background-attachment:  fixed
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center; color: pink">Login</h1>
        <form action="MainController" method="POST" style="width: 300px; margin: auto">
            <div class="mb-3">
                <label class="form-label"></label>
                <input class="form-control"  name="txtUsername" value="" placeholder="Username">
            </div>
            <c:if test = "${not empty requestScope.ERROR}">
                <font color="red">
                ${requestScope.ERROR}
                </font><br>
            </c:if>
            <div class="mb-3">
                <label class="form-label"></label>
                <input type="password" class="form-control" name="txtPassword" value="" placeholder="Password">
            </div>
            <input type="submit" value="Login" name="btnAction" />
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/J3.L.P0013/LoginGoogleServlet&response_type=code
               &client_id=27231438814-ua670bjt7bp7pfe1en4tni6p3jcordn9.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
        </form>

    </body>
</html>
