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
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<style type="text/css">
.ex {
    width: 180px;
	white-space: nowrap; /*不换行的*/
	overflow: hidden; /*超出范围隐藏*/
	text-overflow: ellipsis; /*超出用省略号 */
}
</style>
<title>今日头条</title>
</head>
<body>

<div class="container-fluid">
		<!-- 头 -->
		<div class="row" style="height: 44px">
			<div class="col-md-12 bg-dark pt-2">
				<font color="white">下载app</font>
				<div class="float-sm-right">
				  <c:if test="${loginu == null }">  <!-- 没有登陆显示注册和登陆按钮 -->
						<button class="btn btn-link btn-sm text-decoration-none" 	data-toggle="modal" data-target="#exampleModal" onclick="toReg()">
							<font color="white">注册</font>
					   </button>
					   <button class="btn btn-link btn-sm text-decoration-none" 	data-toggle="modal" data-target="#exampleModal" onclick="toLogin()">
							<font color="white">登陆</font>
					   </button>
				   </c:if>
				   
				    <c:if test="${loginu != null }"> <!-- 登陆成功显示的标签 -->
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
					</c:if>
				   
			   </div>
			</div>
		</div>
		<!-- 头部以下位置 -->
		<div class="row mt-2">
			<!-- 左侧菜单部分 -->
			<div class="col-md-2">
				<ul>
					<a href="/index.do"><img height="27px" width="108px" alt=""
						src="/resource/imges/index.png"></a>
					<li class="mt-2"><a class="channel-item ${channelId==null?"
						active":"" }"  href="/index.do">热点</a></li>
					<c:forEach items="${channels }" var="channel">
						<li><a class="channel-item  ${channelId==channel.id?"
							active":"" }" href="/index.do?channelId=${channel.id }">${channel.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 中间区域 -->
			<div class="col-md-7">
				<div>
					<!-- 判断是否显示分类 -->
					<c:if test="${channelId !=null }">
						<ul class="subchannel">
							<li class="sub-item  ${categoryId==null?"sub-selected":"" }"><a
								href="/index.do?channelId=${channelId }">全部</li>
							<c:forEach items="${categorys }" var="category">
								<li class="sub-item ${categoryId==category.id?"sub-selected":"" }"><a
									href="/index.do?channelId=${channelId}&categoryId=${category.id}">${category.name}</li>
							</c:forEach>
						</ul>
					</c:if>

					<!-- 判断是否显示轮播图  -->
					<c:if test="${channelId ==null }">
						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach items="${slides }" varStatus="i">
									<li data-target="#carouselExampleCaptions"
										data-slide-to="${i.index }" class="active"></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
								<c:forEach items="${slides }" var="slide" varStatus="i">
									<div class="carousel-item  ${i.index==0?"active":"" }">
										<img height="350px" src="${slide.picture }"
											class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"></div>
									</div>
								</c:forEach>

							</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
					</c:if>



					<!-- 文章显示 -->
					<div>
						<c:forEach items="${info.list }" var="a">
							<div class="media pt-2">
								<a href="/detail.do?id=${a.id }" target="_bank"><img class="rounded" alt="" src="${a.picture }" width="156px"
									height="102px"></a>
								<div class="media-body ml-2 pt-2">
									<a href="/detail.do?id=${a.id }" target="_bank"><h5 style="font-size: 22px; color: black;">${a.title }</h5></a>
									<p>${a.user.username }
										<fmt:formatDate value="${a.created }"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</p>
								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
					
					<jsp:include page="/WEB-INF/common/pages.jsp"></jsp:include>
				</div>



			</div>
			<!-- 右侧区域 -->
			<div class="col-md-3">
				<div class="card" style="width: 18rem;">
					 <div class="card-header">最新文章</div>
					<div class="card-body">
					   <c:forEach items="${lasts }" var="last">
						     <div class="media">
									<a href="/detail.do?id=${last.id }" target="_bank"> <img class="rounded" height="60px" width="60px" src="${last.picture }" class="mr-3" alt="..."></a>
								  <div class="media-body ml-2 ex">
								     	<a href="/detail.do?id=${last.id }" target="_bank"> ${last.title }</a>
								  </div>
						      </div>
						      <hr>
					     </c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
 </div>
 
   <!-- 模态框 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"  ></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="u">
         
         
      </div>
    </div>
  </div>
</div>
 

</body>

   <script type="text/javascript">
     function goPage(pageNum){
    	 //获取菜单id
    	  var  channelId ='${channelId}';
    	  //获取分类id
    	  var categoryId ='${categoryId}';
    	  
    	  location.href="/index.do?pageNum="+pageNum+"&channelId="+channelId+"&categoryId="+categoryId;
    	 
     }
     
     
     //加载注册页面
     function toReg(){
    	 $("#exampleModalLabel").text("用户注册");
    	 $("#u").load("/user/to/reg.do");
     }
     
   //加载登陆页面
     function toLogin(){
    	 $("#exampleModalLabel").text("用户登陆");
    	 $("#u").load("/user/to/login.do");
     }
   
   //拦截器跳转
   $(function(){
	  var  url  ='${url}';
	  if(url.length>0){
		  $("#exampleModal").modal("show"); //显示模态框 
		  $("#exampleModalLabel").text("被拦截器拦截，请先先登陆");
	      $("#u").load(url);
	  }
   });
   </script>

</html>