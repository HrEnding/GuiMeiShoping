<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h3 style="color: red">${error}</h3>
<form action="/seller/sellerInsert" class="layui-form">
    <label>姓名</label>
    <input type="text" name="seName"/><br>
    <label>昵称</label>
    <input type="text" name="seUser"/><br>
    <label>邮箱</label>
    <input type="text" name="seEmail"/><br>
    <div class="post">
        <label>性别</label>
        <input class="layui-form-radio" type="radio" name="seSex" value="男" checked/>男&nbsp;&nbsp;&nbsp;
        <input class="layui-form-radio" type="radio" name="seSex" value="女"/>女
    </div>
    <label>电话</label>
    <input type="text" name="seTel"/><br>
    <label>地址</label>
    <input type="text" name="seAddress"/><br>
    <label>生日</label>
    <input type="date" name="seBirth"/><br>
    <label>身份证</label>
    <input type="text" name="seIdcard"/><br>
    <label>密码</label>
    <input type="text" name="sePwd"/><br>
    <input class="layui-btn " id="sub" type="submit" value="修改"/>
</form>
</body>
</html>
