<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.js"></script>
    

    <title>淇县信誉商砼有限公司送货单</title>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <%
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date currentTime = new java.util.Date();
        String createtime = simpleDateFormat.format(currentTime).toString();
    %>
    <style type="text/css">
        .qiangdu {
            width: 100px;
            height: 30px;
            line-height: 25px;
        }

        input {
            width: 110px;
            height: 25px;
        }

        .g3 {
            width: 400px;
        }

        .xcttd {
            width: 200px;
        }
    </style>


</head>
<body>
<jsp:include page="loginOutDiv.jsp" flush="true"/> 

<form action="${pageContext.request.contextPath }/addChuHuoDanToMySql.do" method="post">
    <table border="1" width="100%" height="400" align="center">
        <tr>
            <th colspan="8" align="center" height="100">淇县信誉商砼有限公司送货单</th>
        </tr>
        <tr>
            <td width="100">编号</td>
            <td width="200" colspan="3" >等待生成编号</td>
            <td width="100" align="center">时间:</td>
            <td width="300" ><input name="createtime" type="text" value="<%=createtime%>"></td>
            <td width="100">计量单位</td>
            <td width="100" ><input name="jiliangdanwei" type="text" value="Kg"></td>
        </tr>
        <tr>
            <td width="100">收货单位</td>
            <td colspan="3" width="300"><input  name="shouhuodanwei" class="g3"
                                                type="text" placeholder="收货单位名称"></td>
            <td width="120" class="xcttd">等级强度</td>
            <td width="100" width="100"><select name="dengjiqiangdu"
                                                class="qiangdu">
                <option>c10</option>
                <option>c15</option>
                <option>c20</option>
                <option>c25</option>
                <option>c30</option>
                <option>c35</option>
            </select></td>
            <td width="100">浇筑方式</td>
            <td width="100"><input  name="jiaozhufangshi" type="text"
                                    placeholder="浇筑"></td>
        </tr>

        <tr>
            <td width="100">工程名称</td>
            <td colspan="3" width="300"><input  name="gongchengname" class="g3"
                                                type="text" placeholder="中建二局商业楼"></td>
            <td width="120" class="xcttd">坍塌度</td>
            <td width="100" ><input name="tantadu" type="text" value="180±20"></td>
            <td width="100">抗渗等级</td>
            <td width="100" ><input name="kangshendengji" type="text"
                                    placeholder="P6"></td>
        </tr>
        <tr>
            <td width="100">施工部位</td>
            <td colspan="3" width="300"><input  name="shigongbuwei" id="g3"
                                                type="text" placeholder="筑基"></td>
            <td width="120" class="xcttd">现场坍塌度</td>
            <td width="100" ><input name="xianchangtantadu"
                                    type="xianchangtantadu"></td>
            <td width="100">到达时间</td>
            <td width="100"><input  name="daodatime" type="time"></td>
        </tr>
        <tr>
            <td width="100">车号</td>
            <td width="120" ><input name="chehao" type="text"
                                    placeholder="豫F88888"></td>
            <td width="50">司机</td>
            <td width="150"><input  name="siji" type="text" placeholder="张三"></td>
            <td width="100" class="xcttd">车次</td>
            <td width="100" ><input name="checi" type="number" min="1"
                                    placeholder="1"></td>
            <td width="100">本次方量</td>
            <td width="100" ><input name="bencifangliang" type="text"></td>
        </tr>
        <tr>
            <td width="100">毛重</td>
            <td width="120" ><input name="maozhong" id="maozid"
                                    type="number" step="0.01" min="1" placeholder="2000"></td>
            <td width="50">皮重</td>
            <td width="150"><input  name="pizhong"  id="pizid"
                                    type="number" step="0.01" min="1" placeholder="1000"></td>
            <td width="100" class="xcttd">净重</td>
            <td width="100" step="0.01" min="1"><input  name="jingzhong"  type="number" id="jingzid"></td>
            <td width="100">累计方量</td>
            <td width="100" > <input name="leiji" type="text"></td>
        </tr>
        <tr>
            <td width="100"  >备注</td>
            <td colspan="7" >
                <textarea rows="10px" cols="20px" name="beizhu"></textarea>

            </td>
        </tr>
    </table>
    <div style="float: right">
        <input type="button" value="展示所有出货单" onclick="location.href ='${pageContext.request.contextPath }/findAll.do'">
        <input type="reset" value="清空输入">
        <input type="submit" value="生成出货单">
    </div>

</form>
</body>
    <script type="text/javascript">

     $(document).ready(function(){
    	
        $("#pizid").change(function () {
            $("#jingzid").val($("#maozid").val()-$("#pizid").val());
        })
    }); 
</script>
</html>