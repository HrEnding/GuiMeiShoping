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
        $(function(){
            var url1 = "/smallClass/querySmName";
            $.getJSON(url1,function(smallClass){
                $.each(smallClass,function (index,sm) {
                    $("select[name='gSmId']").append("<option value='"+sm.id+"'>"+sm.smSName+"</option>");
                });
                $("select[name='gSmId'] option[value='${goods.gSmId}']").attr("selected",true);
            });
            var url2 = "/seller/querySe";
            $.getJSON(url2,function(seller){
                $.each(seller,function (index,se) {
                    $("select[name='gSeId']").append("<option value='"+se.seId+"'>"+se.seName+"</option>");
                });
                $("select[name='gSeId'] option[value='${goods.gSeId}']").attr("selected",true);
            });
            var url3 = "/goods/queryDis";
            $.getJSON(url3,function(discount){
                $.each(discount,function (index,dis) {
                    $("select[name='gDId']").append("<option value='"+dis.id+"'>"+dis.dDisc*10+"折</option>");
                });
                $("select[name='gDId'] option[value='${goods.gDId}']").attr("selected",true);
            });
            //验证输入的小分类名称是否重名
            $("input[name='gName']").blur(function(){
                check();
            });
            $("select[name='gSmId']").blur(function(){
                check();
            });
            $("select[name='gSeId']").blur(function(){
                check();
            });
        });
        function check() {
            var name = $("input[name='gName']").val();
            var url = "/goods/queryGoodsName?gName="+$("input[name='gName']").val()+"&gSmId="+$("select[name='gSmId']").val()+"&gSeId="+$("select[name='gSeId']").val();
            $.post(url,function(status){
                if(status=="exists"){
                    if (name!=$("input[name='gName']").val()) {
                        alert("该商品名称已经存在！");
                        $("input[type='submit']").attr("disabled",true);
                    }
                }else{
                    $("input[type='submit']").attr("disabled",false);
                }
            },"json");
        }
    </script>
</head>
<body>
<h3 style="color: red">${error}</h3>
<form action="/goods/updateGoods" method="post" enctype="multipart/form-data">
    <label>编号</label>
    <input type="text" name="id" readonly value="${goods.id}"/><br>
    <label>商品名称</label>
    <input type="text" name="gName" value="${goods.gName}"/><br>
    <label>原价</label>
    <input type="text" name="gMoney" value="${goods.gMoney}"/><br>
    <label>数量</label>
    <input type="text" name="gNumber" value="${goods.gNumber}"/><br>
    <label>运费</label>
    <input type="text" name="gCarriage" value="${goods.gCarriage}"/><br>
    <label>小分类</label>
    <select name="gSmId" id="gSmId"></select><br>
    <label>商家</label>
    <select name="gSeId" id="gSeId"></select><br>
    <div>
        <label>是否新品</label>
        <input type="radio" name="gType" value="1" <c:if test="${goods.gType==1}">checked</c:if>/>新品
        <input type="radio" name="gType" value="0" <c:if test="${goods.gType==0}">checked</c:if>/>二手
    </div>
    <label>折扣</label>
    <select name="gDId" id="gDId"></select><br>
    <input type="file" name="goodsImages" value="${goods.gImg}"/>
    <input class="layui-btn " type="submit" value="修改"/>
</form>
</body>
</html>
