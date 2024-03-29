<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="<%=basePath%>resources/jquery-1.11.0.min.js"></script>
<style type="text/css">
a {
	border: 1px solid rgb(73, 58, 58);
	background-color: rgb(133, 133, 133);
	height: 50px;
	line-height: 50px;
	color: white;
	text-decoration: none;
	font-weight: bold;
	padding: 5px;
	margin: 5px;
}
table{border-top:1pt solid #C1DAD7;border-left:1pt solid #C1DAD7;margin: 0 auto;} 

td{ padding:5px 10px; text-align:center;border-right:1pt solid #C1DAD7;border-bottom:1pt solid #C1DAD7;}

tr:nth-of-type(odd){ background:#F5FAFA;} /* odd 标识奇数行，even标识偶数行 */

tr:hover{ background: #E0F0F0;} /*鼠标悬停后表格背景颜色*/
</style>
<script type="text/javascript">

function deleteUser(id){
	$.ajax({
		type: 'delete',
		url:'<%=basePath%>user/'+id,
		dataType:'text', 
		success:function(data){
			if(data=="suc"){
				alert("删除成功");
				location.reload();
			}
		},
		error:function(data){
		}
	});
}

</script>
</head>

<body>
	<div style="margin: 0 auto; width: 500px;">
		<a href="<%=basePath%>user/add">新增用户</a> <a
			href="<%=basePath%>user/createPhoto">生成图片</a>
		<table>
			<tr>
				<th>用户ID</th>
				<th>用户名称</th>
				<th>操作</th>
			</tr>
			<c:forEach var="user" items="${list }">
				<c:if test="${items.index %2 eq 0}"></c:if>
					<tr >
						<td>${user.userId }</td>
						<td>${user.userName }</td>
						<td><a href="<%=basePath %>user/${user.userId}/edit">编辑用户</a>
							<a href="<%=basePath %>user/${user.userId}">查看用户</a> <a
							href="javascript:void(0);" onclick="deleteUser(${user.userId })">删除该用户</a>
						</td>
					</tr>
				
			</c:forEach>
		</table>
	</div>
</body>
</html>
