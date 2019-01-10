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
            var delStatus = window.confirm("是否确认删除id="+id+"小分类");
            if(delStatus){
                open("smallClass/delById?smallId="+id,"iframe_context");
            }
        }
        $(function () {
            $.post("/smallClass/queryBName",function (date) {
                for (var i = 0; i < date.length; i++) {
                    $("select[name='smBId']").append("<option value=\""+date[i].id+"\">"+date[i].bigName+"</option>");
                }
            });
        });

    </script>
</head>
<body>
<h3 style="color: #0000FF">${error}</h3>
<form action="/smallClass/query" method="post">
    <label>学号:</label>
    <input type="text" name="id" placeholder="请输入学号" />
    <label>小分类名称:</label>
    <input type="text" name="smSName" placeholder="请输入姓名"  />
    <label>大分类名称:</label>
    <select name="smBId">
        <option value="">请选择</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
<c:choose>
    <c:when test="${not empty Page.pageData}">
        ${error}
        <table id="stu" width="90%" class="layui-table" lay-size="sm">
            <tr>
                <th>编号</th>
                <th>小分类名称</th>
                <th>大分类名称</th>
                <th>小分类描述</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${Page.pageData}" var="sc">
                <tr>
                    <td>${sc.id}</td>
                    <td>${sc.smSName}</td>
                    <td>${sc.bigClass.bigName}</td>
                    <td>${sc.smText}</td>
                    <td><a class="layui-btn layui-btn-sm" onclick="del(${sc.id})">删除</a></td>
                    <td><a class="layui-btn layui-btn-sm" href="/smallClass/updateById?id=${sc.id}">修改</a></td>

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
