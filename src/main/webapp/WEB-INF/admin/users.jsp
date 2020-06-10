<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--1. 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <!--2.  引入bootstrap.css -->
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css" />
3. 引入 jquery.js 一定要在bootstrap.min.js之前引入
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
4. 引入bootstrap.min.js
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script> -->
</head>
<body>

<div class="container">
		<form id="form1">
			<div class="form-group form-inline">

				<div class="form-group">
					<label for="username">用户名</label> <input id="username" type="text"
						name="username" class="form-control" value="${userVO.username}">
					<label for="createdStart">注册时间</label> <input id="createdStart"
						type="date" name="createdStart" class="form-control"
						value="${userVO.createdStart}"> -- <input type="date"
						name="createdEnd" class="form-control"
						value="${userVO.createdEnd}">
				</div>

				<div class="form-inline">
				
					<div class="form-check">
						<label class="form-check-label" for="locked">全部</label> 
						<input class="form-check-input" id="locked" type="radio" name="locked"
							${userVO.locked==null?"checked":"" } value="" >
					</div>
					<div class="form-check">
						<label class="form-check-label" for="locked">禁用</label> 
						<input class="form-check-input" id="locked" type="radio" name="locked"
							${userVO.locked==1?"checked":"" } value="1">
					</div>
					<div class="form-check">
						<label class="form-check-label" for="unlocked">正常</label> <input
							class="form-check-input" id="unlocked" type="radio" name="locked"
							${userVO.locked==0?"checked":"" } value="0">
					</div>

				</div>

				<button class="btn btn-info" type="button" onclick="query()">查询</button>

			</div>
		</form>
		<table class="table table-sm table-bordered text-center table-hover">
			<tr>
				<td>序号</td>
				<td>用户名</td>
				<td>昵称</td>
				<td>性别</td>
				<td>注册日期</td>
				<td>账户状态</td>
			</tr>

			<c:forEach items="${info.list}" var="user" varStatus="i">

				<tr>
					<td>${i.count }</td>
					<td>${user.username }</td>
					<td>${user.nickname }</td>
					<td>${user.gender==0?"男":"女"}</td>
					<td><fmt:formatDate value="${user.created }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:if test="${user.locked==0}">
							<button type="button" class="btn btn-success btn-sm" onclick="update(${user.id},this)">正常</button>

						</c:if> <c:if test="${user.locked==1}">
							<button type="button" class="btn btn-danger btn-sm" onclick="update(${user.id},this)">禁用</button>
						</c:if></td>
				</tr>

			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/common/pages.jsp"></jsp:include>

	</div>



</body>
<script type="text/javascript">

   function update(id,obj){
	    //业务：  locked 1:禁用    0 ：正常
	    
	    
	   var locked =$(obj).text()=="禁用"?0:1;
	  $.post("/admin/updateUser",{id:id,locked:locked},function(flag){
		  if(flag){
			 //改变按钮的内容
			 $(obj).text(locked==0?"正常":"禁用");
			 //改变按钮的颜色
			 $(obj).attr("class",locked==0?"btn btn-success btn-sm":"btn btn-danger btn-sm")
		  }
	  })		   
   }



	function query() {
		/*  var username=$("[name='username']").val();
		 var createdStart=$("[name='createdStart']").val();
		 var createdEnd=$("[name='createdEnd']").val();
		 var locked=$("[name='locked']:checked").val(); */
		var url = "/admin/users?" + $("#form1").serialize();
		$("#center").load(url)

	}

	function goPage(pageNum) {
		$("#center").load("/admin/users?pageNum=" + pageNum);

	}

</script>
</html>