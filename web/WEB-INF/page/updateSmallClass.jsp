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
            var url = "/smallClass/queryBName";
            $.getJSON(url,function(bigClass){
                $.each(bigClass,function (index,bg) {
                    $("select[name='smBId']").append("<option value='"+bg.id+"'>"+bg.bigName+"</option>");
                });
                $("option[value='${smallClass.smBId}']").attr("selected",true);

            });
            var name = $("input[name='smSName']").val();
            //验证输入的小分类名称是否重名
            $("input[name='smSName']").blur(function(){
                var url = "/smallClass/queryName?smallClassName="+$("input[name='smSName']").val();
                $.post(url,function(status){
                    if(status=="exists"){
                        if(name!=$("input[name='smSName']").val()){
                            alert("该小分类名称已经存在！");
                            $("input[type='submit']").attr("disabled",true);
                        }
                    }else{
                        $("input[type='submit']").attr("disabled",false);
                    }
                },"json");
            });

        });
    </script>
</head>
<body>
<h3 style="color: red">${error}</h3>
<form action="/smallClass/updateSmallClass" method="post">
    <label>编号:</label><input type="text"  name="id" value="${smallClass.id}" readonly/><br>
    <label>小分类类名称:</label><input id="smSName" type="text" name="smSName" value="${smallClass.smSName}" /><br>
    <label>小分类所属大分类名称:</label>
    <select name="smBId">
    </select><br>
    <label>描述信息:</label><input type="text" name="smText" value="${smallClass.smText}" /><br>
    <input type="submit" value="保存修改" class="layui-btn"/>
</form>
</body>
</html>
