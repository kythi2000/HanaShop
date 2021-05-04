<%-- 
    Document   : admin.jsp
    Created on : Jan 5, 2021, 6:21:21 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <script type="text/javascript">
            function ConfirmDelete() {

                var result = confirm("Do you want to remove this item ?");

                if (result) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <h1> Admin </h1>
        <font color = "red">
        Welcome, ${sessionScope.REGISTRATION.fullname} <a href="LogoutServlet">Logout</a><br>
        </font>

        <form action="SearchServlet?index=1" method="POST">
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
        <a href="create.jsp">New Food</a>
        <c:set var="result" value = "${requestScope.LISTFOOD}"/>
        <c:if test = "${not empty result}">
            <table border="1" style="width: 400; align-content: center" >
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Price</th>                          
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "dto" items="${result}" varStatus="counter">
                    <form action="MainController">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                                <input type="hidden" name="txtFoodName" value="${dto.name}" required/>                          
                            </td>
                            <td>
                                ${dto.quantity}
                                <input type="hidden" name="txtQuantity" min="1" value="${dto.quantity}" required/>
                            </td>
                            <td>
                                ${dto.category}
                                <input type="hidden" name="txtCategory" value="${dto.category}" required/>
                            </td>
                            <td>                                                            
                                ${dto.description}
                                <input type="hidden" name="txtDescription" value="${dto.description}" required/>
                            </td>
                            <td>                                                    
                                <img src="images/${dto.image}" width="80" height="50"/> 
                                <input type="hidden" name="txtImage" value="${dto.image}" required/>
                            </td>
                            <td>                                                         
                                ${dto.price}
                                <input type="hidden" name="txtPrice" min="1" value="${dto.price}" required/>
                            </td>
                            <td>                                                                       
                                ${dto.dateOfCreate}                               
                            </td>
                            <td>
                                <c:url var = "urlRewriting" value = "MainController">
                                    <c:param name="btnAction" value="Delete"/>
                                    <c:param name="txtFoodID" value="${dto.foodID}"/>                                  
                                </c:url>
                                <a href="${urlRewriting}" onclick="return ConfirmDelete()">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btnAction" />
                                <input type="hidden" name="txtFoodID" value="${dto.foodID}" />  
                            </td>
                        </tr>
                    </form>
                </c:forEach>    
            </tbody>
        </table>
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
    <c:if test = "${empty result}">
        <h2 color = "violet"> No record is matched</h2>               
    </c:if>
</body>
</html>
