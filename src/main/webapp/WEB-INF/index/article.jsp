<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${article.title }</title>
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</head>

<body>
<div class="container-fluid">
		<div class="row bg-dark" style="height: 34px">
			<div class="col-md-12 pt-1">
				<div class="float-right">
				 <a href="#" class="text-white ml-2">登录</a>
					<a href="#" class="text-white">侵权投诉</a><a href="#"
						class="text-white ml-2"> 头条产品</a>
				</div>
			</div>

		</div>
	</div>
	<div class="container">
	  <div class="row">
	  
	      <div class="col-md-2"></div>
	      <div class="col-md-7">
	         <h2><strong> ${article.title }</strong></h2>
	         <p>${article.user.username } · <fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
	       <hr>
	       ${article.content}
	       <br>
	       <span id="msg" class="text-danger"></span>
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	      <c:if test="${sessionScope.user == null }">
	       		<a class="text-primary" href="passport/login">请登录后再评论</a>
	       </c:if>
	       
	       <c:if test="${sessionScope.user != null }">
	          <div>
	       文章评论:
	       <textarea rows="5" cols="90" name="content"></textarea>
	       <input type="button" class="btn btn-info" onclick="tj()"  value="提交评论" />
	       </div>
	       </c:if>
	    
	       <c:forEach items="${info.list }" var="c">
	       				<div style="height: 50px;weight:150px">
	       						${c.user.username } &emsp; ${c.displaytime }
	       						<p>${c.content }</p>
	       				</div>
	       </c:forEach> 
	       
	       
	       
	      </div>
	      <div class="col-md-3"></div>
	  
	  </div>
	
	
	</div>
</body>
<script type="text/javascript">


	function tj() {
		var articleId = '${article.id}';
		var content = $("[name='content']").val();
		$.ajax({
			url:"/insert",
			data:{articleId:articleId,content:content},
			type:"post",
			success:function(result){
				if(result.code==200){
					alert("评论成功");
					location.reload();
				}else{
					alert("评论失败,请登录!");
				}
			}
		})
	}
</script>
</html>