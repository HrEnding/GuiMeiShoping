<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <script src="static/js/jquery-1.8.2.min.js"></script>
    <style>
        input{
            border-radius: 5px;
            height: 30px;
            font-size: 15px;
            padding-left: 5px;
        }
        form{
            width: 80%;
            margin: auto;
            padding: 40px;
        }
    </style>

</head>
<body>
<h3 style="color: red">${error}</h3>
<form action="/goods/addGoods" method="post" enctype="multipart/form-data">
    <label>商品名称</label>
    <input type="text" name="gName" required/><br>
    <label>原价</label>
    <input type="text" name="gMoney" required/><br>
    <label>数量</label>
    <input type="number" name="gNumber" required/><br>
    <label>运费</label>
    <input type="text" name="gCarriage" required/><br>
    <label>小分类</label>
    <select name="gSmId" id="gSmId" required></select><br>
    <label>商家</label>
    <select name="gSeId" id="gSeId" required></select><br>
    <div>
        <label>是否新品</label>
        <input type="radio" name="gType" value="1" required/>新品
        <input type="radio" name="gType" value="0" required/>二手
    </div>
    <label>折扣</label>
    <select name="gDId" id="gDId" required></select><br>
    <label>上传商品图片:</label>
    <input type="file" name="goodsImages" required />
    <b id="imageMsg">文件路径必须是 (文件大小不能超过5M)</b><br>
    <input type="submit" id="sub" value="添加" class="layui-btn"/>
</form>
</body>
<script>
    $(function () {
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

        $("input[type='file']").change(function(){
            var fileName = $(this).val();
            var fileType = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
            var fileTypeReg = /gif|bmp|png|jpg|jpeg|icon/;
            if(fileTypeReg.test(fileType)){
                $("#imageMsg").html("√");
            }else{
                $("#imageMsg").html("文件路径必须是gif|bmp|png|jpg|jpeg|icon").css("color","red");
                $(this).val("");
                }
            });
        });
    });
    function check() {
        var url = "/goods/queryGoodsName?gName="+$("input[name='gName']").val()+"&gSmId="+$("select[name='gSmId']").val()+"&gSeId="+$("select[name='gSeId']").val();
        $.post(url,function(status){
            if(status=="exists"){
                alert("该商品名称已经存在！");
                $("input[type='submit']").attr("disabled",true);
            }else{
                $("input[type='submit']").attr("disabled",false);
            }
        },"json");
    }

</script>
</html>


