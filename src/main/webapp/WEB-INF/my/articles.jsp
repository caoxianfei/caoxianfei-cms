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
<!-- <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css" />
3. 引入 jquery.js 一定要在bootstrap.min.js之前引入
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>            //index 首页已经引入过这些样式   使用登录用户的样式 会引起冲突 所以 直接 注释。
4. 引入bootstrap.min.js
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script> -->
</head>
<body>
		<div class="container-fluid">
				<ul class="list-unstyled">
			<c:forEach items="${info.list}" var="article">
				<li class="media"><img src="${article.picture }" class="mr-3 rounded" alt="..." height="180" width="200">
					<div class="media-body">
						<h5 class="mt-2 mb-1 mt">${article.title }</h5>
						<p class="mt-5">点击量&nbsp;${article.hits }&emsp; ${article.displayDate }</p>
                         <div><button onclick="article(${article.id })" type="button" 
                         class="float-right btn btn-link" data-toggle="modal" data-target="#exampleModalLong" >
                                                       详情</button> </div>
					</div></li>
					<hr>
			</c:forEach>
			<jsp:include page="/WEB-INF/common/pages.jsp"></jsp:include>	
		</ul>
	
		<!-- Modal  模态框-->  
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle" ><span id="title"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
     
      </div>
    </div>
  </div>
</div>
				
		</div>


</body>
<script type="text/javascript">

		function goPage(pageNum){
			//alert(pageNum);
			 $("#center").load("/my/articles?pageNum="+pageNum);
		}

		function article(id){
		
			$.ajax({
				url:"/my/article",    //通过id 查找 单篇文章的内容。
				data:{"id":id},
				type:"post",
				dataType:"json",
				success:function(obj){
					
					$("#title").html(obj.title);
					$("#content").html(obj.content);
				}
			})
		}
</script>
</html>