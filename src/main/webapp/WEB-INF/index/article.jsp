<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>  
<%@ taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>      
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
<title>${article.title }</title>
</head>
<body>
     <div class="container-fluid">
        <!-- 头部 -->
		<div class="row bg-dark" style="height: 34px">
			<div class="pt-1 ml-2">
				<font color="white">下载App</font>
			</div>
		</div>
		<div class="row mt-2">
		     <div class="col-md-3"></div>
		     <div class="col-md-7">
		         <h3>${article.title }</h3>
		         <p>${article.user.username }  <fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
		          <div style="width:800px">${article.content }</div>
		     </div>
		     <div class="col-md-2"></div>
		</div>
     </div>
</body>
</html>