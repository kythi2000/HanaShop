<%-- 
    Document   : viewCart
    Created on : Jan 14, 2021, 2:18:44 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <script type="text/javascript">

            function ConfirmDelete() {

                var result = confirm("Do you want to remove this item from cart?");

                if (result) {
                    return true;
                } else {
                    return false;
                }

            }

        </script>
    </head>
    <body>
        <h1>${sessionScope.Cart.customerName}</h1>
        <form action="MainController">
            <c:set var="cart" value="${sessionScope.Cart.cart.values()}"/>
            <c:if test="${not empty cart}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart}" var = "dto" varStatus = "counter" >
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.name}</td>
                                <td>
                                    <input type="number" name="txtQuantity" value="${dto.quantity}" min="1" max="${dto.avaiableQuantity}" required/> 
                                    <input type="hidden" name="txtFoodID" value="${dto.foodID}" />
                                </td>
                                <td>${dto.price}</td>
                                <td>
                                    <input type="checkbox" name="chkRemove" value="${dto.foodID}" />
                                </td>   

                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3">Total</td>
                            <td>${sessionScope.Cart.total}</td>
                            <td>
                                <button type="submit" value="Remove" name="btnAction" onclick="return ConfirmDelete()" >Remove</button>
                            </td>
                            <th>
                                <button type="submit" value="Update Cart" name="btnAction" >Update</button>
                            </th>
                        </tr>
                    </tbody>
                </table>
                <button type="submit" value="Order" name="btnAction" >Order nè!!!!</button>
            </form>

            <script
                src="https://www.paypal.com/sdk/js?client-id=Ab0sIAJdAAlM6MOUnwzS_zLNlKoFVl7B-wUNK0cuG9srZQqoiM8vVt-fwZBB9iKekqyPPfxLJPJOge1N"> // Required. Replace SB_CLIENT_ID with your sandbox client ID.
            </script>
            <script src="js/paypal.js" ></script>

            <form action="" method="POST">                 
                <div id="paypal-button-container">

                </div>
                <input type="hidden" name="btnAction" value="Order" />
            </form>

        </c:if>
        <a href="MainController">Shopping continute</a>
        <font style="color: red">${requestScope.ERROR}</font>
    </body>
</html>
