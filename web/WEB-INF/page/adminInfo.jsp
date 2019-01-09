<%--
  Created by IntelliJ IDEA.
  User: Hr_Ending
  Date: 2019/1/3
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <style>
        input{
            border-radius: 5px;
            height: 30px;
            font-size: 15px;
            padding-left: 5px;
        }
    </style>
</head>
<body>
<table id="stu" width="90%" class="layui-table" lay-size="sm">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>密码</th>
        <th>操作</th>
    </tr>
    <tr>
        <td>${admin.suId}</td>
        <td>${admin.suUser}</td>
        <td>${admin.suPwd}</td>
        <td><a class="layui-btn layui-btn-sm" href="/custom/updateById?id=${cus.id}">修改</a></td>
    </tr>
</table>
</body>
</html>
