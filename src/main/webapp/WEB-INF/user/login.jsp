<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/jquery/screen.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<form id="signupForm">
		<div class="form-group">
			<label for="username">用户姓名</label> 
			<input type="text" class="form-control" name="username"  id="username"> 
		</div>
		<div class="form-group">
			<label for="pwd">密码</label> 
			<input type="password" class="form-control" id="pwd" name="password">
		</div>
		  <div class="form-group"><span id="msg" style="color: red">${adminMsg }</span></div>
		<div>
			<div class="float-right">
				<button type="submit" class="btn btn-primary">登陆</button>
			</div>
		</div>
	</form>
</body>

  <script type="text/javascript">
     $(function(){
    	 $("#signupForm").validate({
    		 rules: {
    			 username:{
    				 required: true, //必须填写
    				 rangelength:[3,6] //长度未3--6
    			 },
    			 password:{
    				 required: true, //必须填写
    				 rangelength:[3,6] //长度未3--6
    			 }
    		 },
    		 messages: {
    			 username:{
    				 required: "用户名必须填写", //必须填写
    				 rangelength:"用户名的长度3-6位" //长度未3--6
    			 },
    			 password:{
    				 required: "密码必须输入", //必须填写
    				 rangelength:"密码长度3-6位" //长度未3--6
    			 }
    		 },
    		 submitHandler:function(form){
    	            $.post(
    	               "/user/login.do",
    	               $("#signupForm").serialize(),
    	               function(obj){
    	            	   if(obj){
    	            		   location.reload();
    	            	   }else{
    	            		   $("#msg").text("账号密码错误或者账号被锁！！");
    	            	   }
    	               }
    	            )
    	        }
    		 
    	 })
     })
  
  </script>

</html>