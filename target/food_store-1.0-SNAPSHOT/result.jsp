<%-- 
    Document   : result
    Created on : Nov 28, 2023, 2:02:17 PM
    Author     : nguyendatdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h1>Uploaded Image</h1>
        <c:forEach items="${uploaded}" var="value">
            <img src="images/${value.getImageName()}" style="max-width: 200px"/>
        </c:forEach>
</body>
</html>
