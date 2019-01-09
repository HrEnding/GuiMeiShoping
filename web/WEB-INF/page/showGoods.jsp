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
    <script src="static/js/jquery-1.8.2.min.js"></script>
    <script>
        function del(id) {
            var delStatus = window.confirm("是否确认删除id="+id+"商品");
            if(delStatus){
                open("goods/delById?goodsId="+id,"iframe_context");
            }
        }
    </script>
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
                <th>商品名称</th>
                <th>原价</th>
                <th>数量</th>
                <th>运费</th>
                <th>类型</th>
                <th>小分类</th>
                <th>商家</th>
                <th>图片</th>
                <th>折扣</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${Page.pageData}" var="goods">
                <tr>
                    <td>${goods.id}</td>
                    <td>${goods.gName}</td>
                    <td>${goods.gMoney}</td>
                    <td>${goods.gNumber}</td>
                    <td>${goods.gCarriage}</td>
                    <td>
                        <c:if test="${goods.gType==1}">新品</c:if>
                        <c:if test="${goods.gType==0}">二手</c:if>
                    </td>
                    <td>${goods.smallclass.smSName}</td>
                    <td>${goods.seller.seName}</td>
                    <td><img src="static/images/goodsImage/${goods.gImg}"></td>
                    <td>${goods.discount.dDisc}</td>
                    <td><a class="layui-btn layui-btn-sm" onclick="del(${goods.id})">删除</a></td>
                    <td><a class="layui-btn layui-btn-sm" href="/goods/updateById?id=${goods.id}">修改</a></td>

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
        <h3>暂无货物信息</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
