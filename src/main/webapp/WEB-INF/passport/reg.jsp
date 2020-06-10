<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
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
	    <h1>用户注册</h1>
	   </div>
	  </div>
		<div class="row">
			<div class="col-md-6">
			 <span id="msg" class="text-danger"></span>
				<form id="form1">
					<div class="form-group">
						<label for="username">用户名：</label> <input id="username"
							type="text" name="username" class="form-control">
					</div>
					<div class="form-group">
						<label for="password">密码：</label> <input id="password"
							type="password" name="password" class="form-control">
					</div>
					<div class="form-group">
						<label for="repassword">确认密码：</label> <input id="repassword"
							type="password" name="repassword" class="form-control">
					</div>
					<div class="form-group form-inline">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gender"
								id="boy" value="1" checked> <label
								class="form-check-label" for="boy">男</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gender"
								id="girl" value="0"> <label class="form-check-label"
								for="girl">女</label>
						</div>
					</div>
					<div>
						<button class="btn btn-info" type="submit">注册</button>
						<button class="btn btn-warning" type="reset">重置</button>
					</div>


				</form>


			</div>
			<!-- 右侧部分 -->
		<div class="col-md-6">
		
		</div>
		<!--  -->
		</div>

	</div>
		


</body>
<script type="text/javascript">

		$(function(){
			$("#form1").validate({
				// 1 .定义规则
				rules:{
					//
					username:{
						required:true,
						rangelength:[5,10],
						remote:{
							type:"post",
							data:{
								username:function(){
									return $("#username").val();
								}
							},
							url:"/passport/checkusername",
							
						}
					},
					//
					password:{
						required:true,
						rangelength:[6,10],
					},
					//
					repassword:{
						equalTo:"#password",
					},
					
				},
				
				//2.验证后 消息提示
				messages:{
					//
					username:{
						required:"用户名必须输入",
						rangelength:"用户名长度必须在5-10位之间",
						remote:"用户名已被注册!",
					},
					//
					password:{
						required:"密码不能为空",
						rangelength:"密码长度为6-10位之间",
					},
					//
					repassword:{
						equalTo:"两次输入的密码必须一致",
					},
					
					
				},
				
				submitHandler:function(){
					$.post("/passport/reg",$("#form1").serialize(),function(result){
						if(result.code==200){
							alert(result.msg);
							location.href="/passport/login?username="+$("[name='username']").val();//注册成功跳转到登录页面
						}else{
							$("#msg").text(result.msg);
						}
					})
				}
				
			})
			
			
		})

</script>
</html>