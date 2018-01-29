<%--
  Created by IntelliJ IDEA.
  User: zhangfeng
  Date: 2018/1/11
  Time: 下午5:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

<title>所有出货单</title>
</head>
<body>
<jsp:include page="loginOutDiv.jsp" flush="true"/> 
	<table border="1" cellspacing="0" style="font-size: 17px;">

		<tr>
			<th align="center" colspan="21">所有出货单</th>
		</tr>
		<tr>
			<td colspan="21">

				<div style="float: left">
					<form
						action="${pageContext.request.contextPath }/findChuHuoDanByCountName.do"
						method="post">
						<input type="text" placeholder="输入姓名所包含的字" name="moHuName"
							id="moHuName"> <input type="submit" value="模糊查询">
					</form>
				</div>

				<div style="float: right">计量单位 Kg</div>

			</td>
		</tr>
		<tr>
			<td>编号</td>
			<td>创建时间</td>
			<td>收货单位</td>
			<td>等级强度</td>
			<td>浇筑方式</td>
			<td>工程名称</td>
			<td>坍塌度</td>
			<td>抗渗等级</td>
			<td>施工部位</td>
			<td>现场坍塌度</td>
			<td>到达时间</td>
			<td>车号</td>
			<td>司机</td>
			<td>车次</td>
			<td>本次方量</td>
			<td>毛重</td>
			<td>皮重</td>
			<td>净重</td>
			<td>累计方量</td>
			<td>备注</td>
			<td>操作</td>
		</tr>


		<c:forEach items="${list}" var="l">
			<tr>
				<td>${l.id}</td>
				<td>${l.createtime}</td>

				<td>${l.shouhuodanwei}</td>
				<td>${l.dengjiqiangdu}</td>
				<td>${l.jiaozhufangshi}</td>
				<td>${l.gongchengname}</td>
				<td>${l.tantadu}</td>
				<td>${l.kangshendengji}</td>
				<td>${l.shigongbuwei}</td>
				<td>${l.xianchangtantadu}</td>
				<td>${l.daodatime}</td>
				<td>${l.chehao}</td>
				<td>${l.siji}</td>
				<td>${l.checi}</td>
				<td>${l.bencifangliang}</td>
				<td>${l.maozhong}</td>
				<td>${l.pizhong}</td>
				<td>${l.jingzhong}</td>
				<td>${l.leiji}</td>
				<td>${l.beizhu}</td>
				<td><input type="button" value="删除"
					onclick="location.href='${pageContext.request.contextPath }/delItemById.do?id=${l.id}'" />
					<input type="button" value="更改"
					onclick="location.href='${pageContext.request.contextPath }/updateFindById.do?id=${l.id}'" />
					<input type="button" value="导入Excel"
					onclick="location.href='${pageContext.request.contextPath }/daoRuExcelById.do?id=${l.id}'" />
				</td>
			</tr>
		</c:forEach>

	</table>
	<div style="float: left">
		<input type="button" value="展示所有出货单"
			onclick="location.href ='${pageContext.request.contextPath }/findAll.do'">
		<input type="button" value="添加订单" onclick="location.href='add.jsp'">
	</div>
	<%--分页--%>
	<div class="col-md-12 column" style="float: right">

		<input type="button"
			onclick="location.href='${pageContext.request.contextPath }/findAll.do?pageNum=1'"
			value="首页" />
		<c:if test="${pageNum<1}">
			<input type="button"
				onclick="location.href='${pageContext.request.contextPath }/findAll.do?pageNum=1'"
				value="上一页" />
		</c:if>
		<c:if test="${pageNum>=1}">
			<input type="button"
				onclick="location.href='${pageContext.request.contextPath }/findAll.do?pageNum=${pageNum-1}'"
				value="上一页" />
		</c:if>
		<input type="button"
			onclick="location.href='${pageContext.request.contextPath }/findAll.do?pageNum=${pageNum}'"
			value="${pageNum}" />
		<c:if test="${pageNum+1<=lastPage+1}">
			<input type="button"
				onclick="location.href='${pageContext.request.contextPath }/findAll.do?pageNum=${pageNum+1}'"
				value="下一页" />
		</c:if>
		<c:if test="${pageNum+1>lastPage+1}">
			<input type="button"
				onclick="location.href='${pageContext.request.contextPath }/findAll.do?pageNum=${lastPage+1}'"
				value="下一页" />
		</c:if>
		<input type="button"
			onclick="location.href='${pageContext.request.contextPath }/findAll.do?pageNum=${lastPage+1}'"
			value="尾页" />

	</div>

</body>
</html>
