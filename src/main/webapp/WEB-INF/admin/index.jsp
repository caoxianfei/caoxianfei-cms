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
<!--2.  引入bootstrap.css -->
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css" />
<!--3. 引入 jquery.js 一定要在bootstrap.min.js之前引入-->
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<!--4. 引入bootstrap.min.js -->
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>

			<div class="container-fulid"> 
					<div class="row">
							<div class="col-md-12 bg-info" style="height: 50px">

				<img alt="" src="/resource/images/toDayNewsLogo.png"
					class="pt-0.5 rounded-pill"> <strong><font
					color="white">管理员中心</font></strong>
					 
					 
					 
				 <c:if test="${sessionScope.admin!=null}">

				<div class="btn-group dropright mt-0">
					<button type="button" class="btn btn-line btn-sm dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						${sessionScope.admin.username }</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/index">返回首页</a> <a
							class="dropdown-item" href="/passport/logout">注销</a> 
					</div>
				</div>

			</c:if>
			
			
			</div>
					</div>
					
					
					<div class="row mt-2" style="padding-top: 5px">
			<div class="col-md-2 bg-light pt-2" style="height: 550px">
				<div class="list-group text-center">
					<a href="#" data="/admin/articles"
						class="list-group-item list-group-item-action active"> 管理文章 </a> <a
						href="#" data="/admin/users" class="list-group-item list-group-item-action "> 管理用户</a>
						
						<a href="#" data="/admin/" class="list-group-item list-group-item-action "> 友情链接</a>
						
				</div>


			</div>
			<div class="col-md-10" id="center"></div>
					
					
			</div>


</body>
<script type="text/javascript">
$(function(){
	 //默认加载所有的文章
	 $("#center").load("/admin/articles");
	 
	$("a").click(function(){
		var url =$(this).attr("data");
		$("a").removeClass("active");
		$(this).addClass("active");
		$("#center").load(url);
	}) 
})		


</script>
</html>