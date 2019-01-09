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
   <form action="/custom/customUpdate" class="layui-form">
       <label>编号</label>
       <input type="text" name="id" readonly value="${cus.id}"/><br>
        <label>姓名</label>
       <input type="text" name="cusName" value="${cus.cusName}"/><br>
        <label>昵称</label>
       <input type="text" name="cusLoginName" value="${cus.cusLoginName}"/><br>
       <label>邮箱</label>
       <input type="text" name="cusEmail" value="${cus.cusEmail}"/><br>
       <div>
           <label>性别</label>
           <input class="layui-form-radio" type="radio" name="cusSex" value="男" <c:if test="${cus.cusSex=='男'}">checked="checked"</c:if>/>男&nbsp;&nbsp;&nbsp;
           <input class="layui-form-radio" type="radio" name="cusSex" value="女"<c:if test="${cus.cusSex=='女'}">checked="checked"</c:if>/>女
       </div>
       <label>爱好</label>
       <input type="text" name="cusHobby" value="${cus.cusHobby}"/><br>
        <label>密码</label>
       <input type="text" name="cusPassword" value="${cus.cusPassword}"/><br>
       <input class="layui-btn " id="sub" type="submit" value="修改"/>
   </form>
</body>
</html>
