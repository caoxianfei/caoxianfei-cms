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
			<span id="uname"></span>
		</div>
		<div class="form-group">
			<label for="nickname">用户昵称</label> 
			<input type="text" class="form-control" name="nickname"  id="nickname" > 
		</div>
		<div class="form-group">
			<label for="pwd">密码</label> 
			<input type="password" class="form-control" id="pwd" name="password">
		</div>
	   <div class="form-group">
			<label for="reppassword">重复密码</label> 
			<input type="password" class="form-control" id="reppassword" name="reppassword">
		</div>
		 <div class="form-group">
			<label for="birthday">生日</label> 
			<input type="date" class="form-control" id="birthday" name="birthday">
		</div>
		
		<div class="row ml-2">
		性别：
		   <div class="ml-1 form-inline">
			<input checked type="radio" class="form-check-input form-inline" id="man" name="gender" value="MALE">
			<label class="form-check-label" for="man">男</label>
			</div>
			<div class="ml-4">
			<input type="radio" class="form-check-input" id="woman" name="gender" value="FAMALE">
			<label class="form-check-label" for="woman">女</label>
			</div>
		</div>
		  <div><span id="msg"></span></div>
		<div>
			<div class="float-right">
				<button type="submit" class="btn btn-primary">注册</button>
				<button type="reset" class="btn btn-primary">重置</button>
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
    			 nickname:"required",
    			 password:{
    				 required: true, //必须填写
    				 rangelength:[3,6] //长度未3--6
    			 },
    			 reppassword:{
    				 equalTo:"#pwd"
    			 },
    			 birthday:{
    				    required: true, //必须填写s
    					date:true
    			 }
    		 },
    		 messages: {
    			 username:{
    				 required: "用户名必须填写", //必须填写
    				 rangelength:"用户名的长度3-6位" //长度未3--6
    			 },
    			 nickname:"昵称必须填写",
    			 password:{
    				 required: "密码必须输入", //必须填写
    				 rangelength:"密码长度3-6位" //长度未3--6
    			 }, 
    			 reppassword:{
    				 equalTo:"两次的输入密码不一致"
    			 },
    			 birthday:{
    				required:"生日必须填写",
 					date:"请输入正确得日期格式"
 				 }
    		 },
    		 submitHandler:function(form){
    	            $.post(
    	               "/user/reg.do",
    	               $("#signupForm").serialize(),
    	               function(obj){
    	            	   if(obj.code=='200'){
    	            		   $("#exampleModalLabel").text("用户登陆");
       	            	       $("#u").load("/user/to/login.do");  
    	            	   }else{
    	            		   if(obj.msg =='用户名称已经存在'){
    	            			   $("#uname").text(obj.msg);
    	            		   }
    	            	   }
    	            	    
    	               }
    	            )
    	        }
    	 })
    	 
    	 
     })
  
  </script>

</html>