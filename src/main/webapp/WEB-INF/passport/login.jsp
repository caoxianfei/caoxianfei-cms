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
<link rel="stylesheet" type="text/css" href="/resource/css/jquery/screen.css">
<!--3. 引入 jquery.js 一定要在bootstrap.min.js之前引入-->
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<!-- 引入js验证插件 -->
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<!--4. 引入bootstrap.min.js -->
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>

		<div class="container">
	  <div class="row">
	  
	   <div class="col-md-12">
	    <h1><c:if test="${username!=null}">
	          <span class="text-danger"> 恭喜用户${username }，注册成功！</span> 
	    </c:if>用户登录</h1>
	    <span id="msg" class="text-danger"></span>
	   </div>
	  </div>
		<div class="row">
			<div class="col-md-6">
				<form id="form1">
					<div class="form-group">
						<label for="username">用户名：</label> <input id="username"
							type="text" name="username" class="form-control">
					</div>
					<div class="form-group">
						<label for="password">密码：</label> <input id="password"
							type="password" name="password" class="form-control">
					</div>
					
					<div>
						<button class="btn btn-info" type="submit">登录</button>
						<button class="btn btn-warning" type="reset">重置</button>
					</div>


				</form>


			</div>
			<!-- 右侧图片区域 -->
			<div class="col-md-6">
					<img alt="" src="/resource/images/1545912971-KnczjduGsQ.jpeg" style="height:250px;weight:300px;">
			</div>

		</div>

	</div>
		
			



</body>
<script type="text/javascript">




$(function(){
	$("#form1").validate({
		//1.定义规则
		rules:{
			username:{
				required:true,
				rangelength:[3,10],
			},
			password:{
				required:true,	
				rangelength:[6,10],
			},
			
			
			
		},
		//2.消息提示
		messages:{
			username:{
				required:"用户名必须输入",
				rangelength:"用户名长度在3-10之间",
			},
			password:{
				required:"密码必须输入",	
				rangelength:"密码的长度在6-10之间",
			},
					
		},
		
		submitHandler:function(){
			$.post("/passport/loginto",$("#form1").serialize(),function(result){
				if(result.code==200){
					alert("登陆成功!");					
					location.href="/index";//注册成功跳转到登录页面
				}else{
					$("#msg").text(result.msg);
				}
			})
		}
		
		
		
	}) 
	
	 
	 
	 
	 
})



</script>
</html>