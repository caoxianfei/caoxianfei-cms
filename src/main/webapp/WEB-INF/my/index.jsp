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
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<!--3. 引入 jquery.js 一定要在bootstrap.min.js之前引入-->
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<!--4. 引入bootstrap.min.js -->
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>

</head>
<body>

		<div class="container-fluid">
		<!-- 页面header -->
		<div class="row" style="background-color: #777; height: 30px">
			<div class="col-md-12">
				<font color="white">个人中心</font>
				 
				 <c:if test="${sessionScope.user!=null}">

				<div class="btn-group dropright mt-0">
					<button type="button" class="btn btn-line btn-sm dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						${sessionScope.user.username }</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/index">返回首页</a> <a
							class="dropdown-item" href="/passport/logout">注销</a> 
					</div>
				</div>

			</c:if>
				 
				 
			</div>
		</div>

		<!-- body -->
		<div class="row" style="padding-top: 5px">
			<div class="col-md-2 bg-light" style="height: 550px">

				<div class="list-group text-center"  >
					<a href="#" data="/my/articles" class="list-group-item list-group-item-action active">我的文章 </a>
				    <a href="#" data="/my/publish" class="list-group-item list-group-item-action">	发布文章</a> 
				    <a href="#" class="list-group-item list-group-item-action">	我的收藏</a> 
				    <a href="#" class="list-group-item list-group-item-action">	我的评论</a> 
				</div>
			</div>
			<div class="col-md-10" id="center">
			
			  <!-- 引入kindeditor的样式 -->
				<div style="display: none">
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include> 
				
				</div>
				
			</div>
			
		</div>
	</div>
	
</body>
<script type="text/javascript">

	
		$(function(){
			$("#center").load("/my/articles");
			
			$("a").click(function(){
				$("a").removeClass("active"); //删除当前的样式
				$(this).addClass("active");  //增加选中的当前样式
				var url = $(this).attr("data");
				$("#center").load(url);
			})
		})

</script>
</html>