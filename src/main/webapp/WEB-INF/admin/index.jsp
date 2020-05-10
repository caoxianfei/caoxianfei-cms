<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>       
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
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<title>管理员中心</title>
</head>
<body>

   <div class="container-fluid">
      <div class="row bg-dark">
     		<img class="rounded-circle" style="height: 30px" alt="" src="/resource/imges/1.jpg">
            <font  color="white">管理员中心</font>
      </div>
      <div class="row mt-2">
        <div class="col-md-2 bg-light" style="height: 550px ">
          	<div class="list-group text-center">
			  <a href="#" url="/admin/getArticleList.do" class="list-group-item list-group-item-action active">文章审核</a>
			  <a href="#" url="/admin/getUserList.do" class="list-group-item list-group-item-action">用户管理</a>
			  <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">系统设置</a>
    		 </div>
        </div>
        <div class="col-md-10" id="center">
        </div>
      </div>
   </div>

</body>

   <script type="text/javascript">
     //默认加载文章管理
	   $(function(){
	  	 $("#center").load("/admin/getArticleList.do");
	   });
   
     //a标签点击事件
      $("a").click(function(){
    	   $("a").removeClass("active");
    	   $(this).addClass("active");
    	  //获取请求得地址
    	  var url =$(this).attr("url");
    	  $("#center").load(url);
      })
   
   </script>

</html>