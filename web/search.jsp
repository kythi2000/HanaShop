<%-- 
    Document   : search.jsp
    Created on : Jan 5, 2021, 5:27:52 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <style>
            /*            body {
                            background-image: url('images/search.jpg') ; 
                            background-repeat: no-repeat; 
                            background-size: 100% 100%; 
                            background-attachment:  fixed;
                            color: white;
                        }*/
        </style>
    </head>
    <body>
        <h1 style="text-align: center; color: blueviolet">Search Page</h1> 
        <c:if test="${empty sessionScope.REGISTRATION}">
            <a href="login.jsp">Login</a>
        </c:if>
        <c:if test="${not empty sessionScope.REGISTRATION}">
            <font color = "red">
            Welcome, ${sessionScope.REGISTRATION.fullname} <a href="MainController?btnAction=Logout">Logout</a><br>
            </font>
        </c:if>
        <form action="MainController" method="POST">
            Name: <input type="text" name="txtName" value="${param.txtName}" /><br>
            Price: <br>
            From: <input type="number" min="0" name="txtFromPrice" value="${param.txtFromPrice}" />
            To: <input type="number" name="txtToPrice" value="${param.txtToPrice}" /><br>
            Category: <select name="txtCategory">
                <option value="Choose category">Choose category</option>
                <c:forEach var="categoryDTO" items="${sessionScope.CATEGORY}">
                    <option value="${categoryDTO.category}">${categoryDTO.category}</option>
                </c:forEach>
            </select> <br>
            <input type="submit" value="Search" name="btnAction" />
        </form>

        <c:if test="${not empty sessionScope.REGISTRATION}">
            <a href="MainController?btnAction=ViewCart" style="font-size: 20px">Your Cart
                <c:if test="${not empty sessionScope.Cart.cart.values()}">
                    (${sessionScope.Cart.cart.values().size()})
                </c:if>
            </a><br>

            <a href="MainController?btnAction=History">History Order</a>
        </c:if>



        <c:set var="result" value = "${requestScope.LISTFOOD}"/>
        <c:if test = "${not empty result}">
            <table border="1" style="width: 400px ; align-content: center" class="table table-dark table-hover" >
                <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Description</th>
                        <th scope="col">Image</th>
                        <th scope="col">Category</th>
                        <th scope="col">Price</th>                          
                        <th scope="col">Date</th>
                            <c:if test="${not empty sessionScope.REGISTRATION}">
                            <th scope="col">Action</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.quantity}
                            </td>
                            <td>                                    
                                ${dto.description}
                            </td>
                            <td>                                    
                                <img src="images/${dto.image}" width="80px" height="50px"/>
                            </td>
                            <td>                                    
                                ${dto.category}
                            </td>
                            <td>                                    
                                ${dto.price}
                            </td>
                            <td>                                    
                                ${dto.dateOfCreate}
                            </td>
                            <c:if test="${not empty sessionScope.REGISTRATION}">
                                <td>  
                                    <form action="MainController" method="POST">
                                        <button name="btnAction" value="Add">Add to cart</button>
                                        <input type="hidden" name="txtFoodID" value="${dto.foodID}" />
                                        <input type="hidden" name="txtName" value="${param.txtName}" />
                                        <input type="hidden" name="txtFromPrice" value="${param.txtFromPrice}" />
                                        <input type="hidden" name="txtToPrice" value="${param.txtToPrice}" />
                                        <input type="hidden" name="txtCategory" value="${param.txtCategory}" />
                                        <input type="hidden" name="index" value="${param.index}" />
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>    
                </tbody>
            </table>           
            <c:if test = "${empty result}">
                <h2 color = "violet"> No record is matched</h2>               
            </c:if>

            <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                <a id="${i}" href="SearchServlet?index=${i}&txtName=${param.txtName}&txtFromPrice=${param.txtFromPrice}&txtToPrice=${param.txtToPrice}&txtCategory=${param.txtCategory}">${i}</a>
            </c:forEach>


            <c:forEach begin="1" end="${requestScope.endPageAll}" var="i">
                <a id="${i}" href="GetAllFoodServlet?index=${i}">${i}</a>
            </c:forEach>  

        </c:if>

        <script>
            document.getElementById('${param.index}').style.color = "red";
        </script>
    </body>
</html>
