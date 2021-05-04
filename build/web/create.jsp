<%-- 
    Document   : create
    Created on : Jan 13, 2021, 9:40:45 AM
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
        <title>Create</title>
    </head>
    <body style="margin: 15px">
        <h1>Food</h1>
        <form action="MainController" method="POST" >
            <h1> Create </h1>       
            Name: <input type="text" name="txtFoodName" value="${param.txtFoodName}" required/> <br>
            Quantity: <input type="number" name="txtQuantity" min="1" value="${param.txtQuantity}" required/><br>
            Category: <select name="txtCategory">
                <c:forEach var="categoryDTO" items="${sessionScope.CATEGORY}">
                    <option value="${categoryDTO.category}">${categoryDTO.category}</option>
                </c:forEach>
            </select> <br>
            Description: <input type="text" name="txtDescription" value="${param.txtDescription}" required/><br>
            Image: <input type="file" name="txtImage" accept="image/*" required/><br>
            Price: <input type="number" name="txtPrice" min="1" value="${param.txtPrice}" required/><br>
            <input type="submit" value="Create" name="btnAction"/>
        </form>
    </body>
</html>