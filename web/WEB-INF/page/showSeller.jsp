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
<h3 style="color: #0000FF">${error}</h3>
<%@include file="unionForm.jsp"%>
<c:choose>
    <c:when test="${not empty Page.pageData}">
        ${error}
        <table id="stu" width="90%" class="layui-table" lay-size="sm">
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>昵称</th>
                <th>生日</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>身份证</th>
                <th>地址</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${Page.pageData}" var="seller">
                <tr>
                    <td>${seller.seId}</td>
                    <td>${seller.seName}</td>
                    <td>${seller.seSex}</td>
                    <td>${seller.seUser}</td>
                    <td>${seller.seBirth}</td>
                    <td>${seller.seTel}</td>
                    <td>${seller.seEmail}</td>
                    <td>${seller.seIdcard}</td>
                    <td>${seller.seAddress}</td>
                    <td><a class="layui-btn layui-btn-sm" href="/seller/customDelete?id=${seller.seId}">删除</a></td>
                    <td><a class="layui-btn layui-btn-sm" href="/seller/updateById?id=${seller.seId}">修改</a></td>

                </tr>
            </c:forEach>
            <tr>
                <td colspan="9">
                    <%@include file="PageUtil.jsp"%>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <h3>暂无商家信息</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
