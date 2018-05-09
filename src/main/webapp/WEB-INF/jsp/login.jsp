<%--
  Created by IntelliJ IDEA.
  User: wsku1
  Date: 2018/5/8
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>First Login Page</title>
</head>
<body>
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"/></font>
</c:if>
<form action="<c:url value="/loginCheck.html"/>" method="post">
    用户名:<input type="text" name="userName">
    <br>
    密码:<input type="password" name="password">
    <br>
    <input type="submit" value="登陆">
    <input type="reset" value="重置">
</form>
</body>
</html>
