<%-- 
    Document   : historyOrder
    Created on : Jan 17, 2021, 2:18:34 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History </title>
    </head>
    <body>
        <h1>History</h1>
        <a href="MainController">Home</a>
        Search:<br>
        <form action="SearchHistoryServlet" method="POST">
            Name: <input type="text" name="txtSearchName" value="" />  <br>
            Date: <input type="date" name="txtSearchDate" value="" required />  <br>
            <input type="submit" value="Search Historry" name="btnAction" >Search</input>
        </form>
        <c:set var="result" value = "${sessionScope.HistoryOrder}"/>
        <c:if test = "${not empty result}">

            <c:forEach var = "map" items="${result.historyOrder}" >
                <table border="1" style="width: 400; align-content: center" >
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Image</th>
                            <th>Category</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${map.value}" varStatus="counter">
                            <c:set var="date" value="${dto.dateOfCreateOrder}"/>
                            <c:set var="total" value="${dto.total}"/>
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.foodInOrder.name}
                                </td>
                                <td>
                                    ${dto.quantity}
                                </td>
                                <td>                                    
                                    <img src="${dto.foodInOrder.image}" width="80px" height="50px"/>
                                </td>
                                <td>                                    
                                    ${dto.foodInOrder.category}
                                </td>
                                <td>                                    
                                    ${dto.price}
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5">Total</td>
                            <td>${total}</td>
                        </tr>
                    </tbody>
                </table>
                Date: ${date}<br>
                <font style="color: green">--------------------------------------------------------------------</font><br>
            </c:forEach>    
        </c:if>
    </body>
</html>
