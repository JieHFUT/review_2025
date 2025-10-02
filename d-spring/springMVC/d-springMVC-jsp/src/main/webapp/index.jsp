<%--
  Created by IntelliJ IDEA.
  User: 34927
  Date: 2024/12/5
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jsp 页面</title>
</head>
<body>
    <h1>首页</h1>
    <%--pageContext 是第四种域对象，范围是当前 jsp 页面，通过 ${pageContext.request.contextPath} 设置路径上下文--%>
    <a href="${pageContext.request.contextPath}/success">success.jsp</a>
</body>
</html>
