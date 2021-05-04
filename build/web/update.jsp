<%-- 
    Document   : update.jsp
    Created on : Jan 11, 2021, 9:14:45 AM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update</title>
    </head>
    <body>
        <h1> Update </h1>
        <a href="MainController">Home</a>
        <form action="UpdateServlet">
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
            <input type="submit" value="Update"/>
            <input type="hidden" name="txtFoodID" value="${param.txtFoodID}" />
            <input type="hidden" name="index" value="${param.index}" />
            
        </form>              
    </body>
</html>
