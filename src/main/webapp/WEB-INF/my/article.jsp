<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>       
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Insert title here</title>
</head>
<body>
 <div class="container">
		<ul class="list-unstyled mt-2">
		 <c:forEach items="${info.list }" var="article">
		 	 <li class="media">
					<img style="width: 60px; height: 60px" src="${article.picture }" class="mr-3 rounded" alt="...">
						<div class="media-body">
							<h5 class="mt-0 mb-1"><strong>${article.title }</strong></h5>
							
							<p class="pt-1">
							  <fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
							
							<button onclick="detail(${article.id})" type="button" class="btn btn-link" data-toggle="modal" 
							data-target="#exampleModalLong" >
								  详情
						     </button>
							</p>
							
						</div>
			  </li> 
			    <hr>
			 </c:forEach> 
			 <jsp:include page="/WEB-INF/common/pages.jsp"></jsp:include>
		</ul>
		 
		
 </div> 
 
 
 <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"><span id="t"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="con">
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>

  <script type="text/javascript">
     function  detail(articleId){
    	  $("#t").html(""); //清空上一次的标题 否者会一致追加 
    	  $("#con").html(""); //清空上一次的内容否者会一致追加 
    	 $.post(
    	   "/my/getArticleById.do",
    	   {articleId:articleId},
    	   function(obj){
    		   console.log(obj);
    		   $("#t").append(obj.title);
    		   $("#con").append(obj.content);
    	   }
    	 )
     }
     
     
     //分页方法
     function  goPage(pageNum){
    	 $("#content").load("/my/article.do?pageNum="+pageNum);
     }
  
  </script>


</html>