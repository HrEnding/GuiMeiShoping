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
<form action="/seller/sellerUpdate" class="layui-form">
    <label>编号</label>
    <input type="text" name="seId" readonly value="${seller.seId}"/><br>
    <label>姓名</label>
    <input type="text" name="seName" value="${seller.seName}"/><br>
    <label>昵称</label>
    <input type="text" name="seUser" value="${seller.seUser}"/><br>
    <label>邮箱</label>
    <input type="text" name="seEmail" value="${seller.seEmail}"/><br>
    <div class="post">
        <label>性别</label>
        <input class="layui-form-radio" type="radio" name="seSex" value="男" <c:if test="${seller.seSex=='男'}">checked="checked"</c:if>/>男&nbsp;&nbsp;&nbsp;
        <input class="layui-form-radio" type="radio" name="seSex" value="女"<c:if test="${seller.seSex=='女'}">checked="checked"</c:if>/>女
    </div>
    <label>电话</label>
    <input type="text" name="seTel" value="${seller.seTel}"/><br>
    <label>地址</label>
    <input type="text" name="seAddress" value="${seller.seAddress}"/><br>
    <label>身份证</label>
    <input type="text" name="seIdcard" value="${seller.seIdcard}"/><br>
    <label>密码</label>
    <input type="text" name="sePwd" value="${seller.sePwd}"/><br>
    <input class="layui-btn " id="sub" type="submit" value="修改"/>
</form>
</body>
</html>
