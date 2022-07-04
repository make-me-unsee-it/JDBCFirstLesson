<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.step.hryshkin.utils.UtilsForShop" %>
<%@ page import="com.step.hryshkin.model.User" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop!</title>
</head>

<body>
    <h3 align="center">Hey, <%=((User) request.getSession().getAttribute("user")).getName()%></h3>
    <form action="/shop_page.jsp" method="post">
        <p align="center">
            <label>
            <select name="select" size="1">
                <c:forEach var="item" items="$(goods)">
                    <option>
                        <c:out value ="$(item)"/>
                    </option>
                </c:forEach>
                <input type="submit" value="Add item">
            </select>
            </label>
        </p>
    </form>
</body>
</html>