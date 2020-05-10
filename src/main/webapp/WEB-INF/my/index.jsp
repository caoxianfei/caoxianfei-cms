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
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<title>个人中心</title>
</head>
<body>
<div class="container-fluid">
		<!-- 头 -->
		<div class="row" style="height: 44px">
			   <div class="col-md-12 bg-dark pt-2">
       		  		<img class="rounded-circle" style="height: 30px" alt="" src="/resource/imges/1545912971-KnczjduGsQ.jpeg">
       		  		<font color="white">个人中心</font>
       		   	<div class="float-sm-right">
       		     <div class="btn-group dropleft">
						  <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						      ${loginu.username}
						  </button>
						  <div class="dropdown-menu"> 
						       <ul>
						         <a href="/my/index.do">个人中心</a>
						       </ul>
						       <c:if test="${loginu.role == 1 }" >
							       <ul>
							         <a href="/admin/index.do">管理员中心</a>
							       </ul>
						       </c:if>
						        <ul>
						         <a href="/user/loginout.do">注销</a>
						       </ul>
						  </div>
					</div>
				</div>
       		  </div>
       </div>
       <!-- 菜单 -->
       <div class="row">
          	 <div class="col-md-2 bg-light pt-1" style="height: 550px">
	          	 <div class="list-group text-center">
					  <a href="#" url="/my/article.do" class="list-group-item list-group-item-action active">我的文章</a>
					  <a href="#" url="/my/publish.do"  class="list-group-item list-group-item-action">发布文章</a>
					  <a href="#" class="list-group-item list-group-item-action">我的评论</a>
					  <a href="#" class="list-group-item list-group-item-action">我的收藏</a>
					  <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">个人设置</a>
				</div>
				  
					 
				
          	 </div>	 
          	 <div class="col-md-10" id="content">
          	    <div  class="row pt-2" style="display: none">
          	        <div class="pt-2">
          	           <jsp:include page="publish.jsp"></jsp:include> <!-- 引入publish.jsp否则样式出现问题 -->
          	        </div>
          	    </div>
          	 
          	 </div>	 
       </div>
    
    </div>

</body>
   <!-- pt 与上一个元素的间隔  bg北京颜色  row：行  col-md-2  列占的比例  -->
   <script type="text/javascript">
     $(function(){
    	//默认加载我的文章  
     	$("#content").load("/my/article.do");
     })    
   
   
     $("a").click(function(){
        	//拿到啊标签上的请求地址
        	var url= $(this).attr("url"); 
        	//内容区域加载
        	$("#content").load(url);
        	
        	//清除a标签的样式active
        	$("a").removeClass("active");
        	//当前点击的a标签追加样式
        	$(this).addClass("active");
        })
   
   </script>
</html>