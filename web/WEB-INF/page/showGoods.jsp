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
        $(function () {
            var url1 = "/smallClass/querySmName";
            $.getJSON(url1,function(smallClass){
                $.each(smallClass,function (index,sm) {
                    $("select[name='gSmId']").append("<option value='"+sm.id+"'>"+sm.smSName+"</option>");
                });
            });
            var url2 = "/seller/querySe";
            $.getJSON(url2,function(seller){
                $.each(seller,function (index,se) {
                    $("select[name='gSeId']").append("<option value='"+se.seId+"'>"+se.seName+"</option>");
                });
            });
            $.post("/smallClass/queryBName",function (date) {
                for (var i = 0; i < date.length; i++) {
                    $("select[name='smBId']").append("<option value=\""+date[i].id+"\">"+date[i].bigName+"</option>");
                }
            });
        })
        $(function () {
            $("#smBId").change(function () {
                var bigClassId = $(this).val();
                if(bigClassId>0){
                    var url = "/smallClass/querySmName?smBId="+bigClassId;
                    $.getJSON(url,function (smallClassList) {
                        //清空原来小分类列表中的数据信息
                        $("select[name='gSmId']").empty();
                        $.each(smallClassList,function (index,smallClass) {
                            $("select[name='gSmId']").append("<option value='"+smallClass.id+"'>"+smallClass.smSName+"</option>");
                        });
                    });
                } else {
                    var url = "/smallClass/querySmName";
                    $.getJSON(url,function (smallClassList) {
                        //清空原来小分类列表中的数据信息
                        $("select[name='gSmId']").empty();
                        $("select[name='gSmId']").append(" <option value=\"0\">-请选择-</option>");
                        $.each(smallClassList,function (index,smallClass) {
                            $("select[name='gSmId']").append("<option value='"+smallClass.id+"'>"+smallClass.smSName+"</option>");
                        });
                    });
                }
            })
        })
    </script>
</head>
<body>
<h3 style="color: #0000FF">${error}</h3>
<form action="/goods/union" method="post">
    <label>商品编号:</label>
    <input type="text" name="id" placeholder="请输入商品ID" />
    <label>商品名称:</label>
    <input type="text" name="gName" placeholder="请输入商品名称"  />
    <label>所属商家:</label>
    <select name="gSeId">
        <option value="0">请选择</option>
    </select>
    <label>商品类型</label>
    <select name="gType">
        <option value="0">新品</option>
        <option value="1">二手</option>
    </select>
    <label>商品所属大分类</label>
    <select id="smBId" name="smBId">
        <option value="0">-请选择-</option>
    </select>
    <label>商品所属小分类类型</label>
    <select name="gSmId">
        <option value="0">-请选择-</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
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
                    <td>${goods.discount.dDisc*10}折</td>
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
