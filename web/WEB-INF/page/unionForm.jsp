<%--
  Created by IntelliJ IDEA.
  User: Hr_Ending
  Date: 2019/1/4
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form>
    <label id="idNumber"></label>
    <input type="text" id="userId" name="id" placeholder="请输入学号" />
    <label id="name"></label>
    <input type="text" id="cusName" name="cusName" placeholder="请输入姓名"  />
    <select name="cus" id="cus">
        <option value="">请选择</option>
    </select>
    <label id="select"></label>
    <select name="cusSex" id="cusSex">
        <option value="">请选择</option>
    </select>
    <input class="layui-btn " id="sub" type="button" onclick="submitbtn()" value="查询"/>
</form>
<script type="text/javascript" src="/static/js/jquery-1.8.2.min.js"></script>
<script>
    $(function () {
        var tip = "${Page.tip}";
        if (tip=="custom" || tip=="seller"){
            $("#idNumber").html("编号");
            $("#name").html("名称");
            $("#select").html("性别");
            $("#cus").hide();
            $("#cusSex").append("<option value=\"男\">男</option>");
            $("#cusSex").append("<option value=\"女\">女</option>");
        } else if(tip=="smallClass"){
            $("#idNumber").html("编号");
            $("#name").html("名称");
            $("#select").html("大分类名称");
            $("#cus").hide();
            $.post("/smallClass/queryBName",function (date) {
                for (var i = 0; i < date.length; i++) {
                    $("#cusSex").append("<option value=\""+date[i].id+"\">"+date[i].bigName+"</option>");
                }
            });
        }else if (tip=="goods"){
            $("#idNumber").html("编号");
            $("#name").html("商家名称");
            $("#select").html("小分类名称");
            $("#cusName").hide();
            var url1 = "/smallClass/querySmName";
            $.getJSON(url1,function(smallClass){
                $.each(smallClass,function (index,sm) {
                    $("select[name='cusSex']").append("<option value='"+sm.id+"'>"+sm.smSName+"</option>");
                });
            });
            var url2 = "/seller/querySe";
            $.getJSON(url2,function(seller){
                $.each(seller,function (index,se) {
                    $("select[name='cus']").append("<option value='"+se.seId+"'>"+se.seName+"</option>");
                });
            });
        }
    })
    function submitbtn() {
        var userId = document.getElementById("userId").value;
        userId = (userId==''||userId==null)?0:userId;
        var cusName = document.getElementById("cus").value;
        var cusSex = document.getElementById("cusSex").value;
        var tip = "${Page.tip}";
        if (tip=="custom"){
            location.href = "/custom/union?pageNumber=1&id="+userId+"&cusName="+cusName+"&cusSex="+cusSex;
        } else if (tip=="seller"){
            location.href = "/seller/union?pageNumber=1&seId="+userId+"&seName="+cusName+"&seSex="+cusSex;
        } else if(tip=="smallClass"){
            location.href = "/smallClass/query?id="+userId+"&smSName="+cusName+"&smBId="+cusSex+"&pageNumber=1";
        } else if(tip=="goods"){
            location.href = "/goods/union?id="+userId+"&gSeId="+cusName+"&gSmId="+cusSex+"&pageNumber=1";
        }

    }
</script>
