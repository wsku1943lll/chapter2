<%--
  Created by IntelliJ IDEA.
  User: wsku1
  Date: 2018/5/8
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>My First Login Page</title>
</head>
<body>
    ${user.userName},欢迎登陆网站，您当前的积分为${user.credits};
</body>
</html>
