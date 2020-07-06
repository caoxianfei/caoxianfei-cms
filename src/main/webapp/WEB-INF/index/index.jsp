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
<link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<!--3. 引入 jquery.js 一定要在bootstrap.min.js之前引入-->
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<!--4. 引入bootstrap.min.js -->
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>

			<div class="container-fluid">
		<div class="row bg-dark" style="height: 36px">
			<div class="col-md-12 pt-1">
				<a href="#" class="text-white">下载APP</a> <a href="/passport/reg"
					class="text-white ml-2"> 注册</a>
			<c:if test="${sessionScope.user!=null}">

				<div class="btn-group dropright mt-0">
					<button type="button" class="btn btn-dark btn-sm dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						${sessionScope.user.username }</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/my">个人中心</a> <a
							class="dropdown-item" href="/passport/logout">注销</a> 
					</div>
				</div>

			</c:if>
			  <!-- 管理员 -->
			<c:if test="${sessionScope.admin!=null}">

				<div class="btn-group dropright mt-0">
					<button type="button" class="btn btn-dark btn-sm dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						${sessionScope.admin.username }</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/admin">管理员中心</a> <a
							class="dropdown-item" href="/passport/logout">注销</a> 
					</div>
				</div>

			</c:if>
			
			<c:if test="${sessionScope.user==null && sessionScope.admin==null }">
				<a href="/passport/login" class="text-white ml-2">登录</a>
			</c:if>
					
					
					 <!-- <a href="/passport/login" class="text-white ml-2">登录</a> -->
				<div class="float-right">
					<a href="#" class="text-white">侵权投诉</a><a href="#"
						class="text-white ml-2"> 头条产品</a>
				</div>

			</div>

		</div>
	</div>
	<div class="container mt-2">
		<div class="row" style="height: 550px">
			<!-- 左侧菜单 -->
			<div class="col-md-2">
				<div class="channel">
					<ul>
						<li class="mb-2"><a href="/" class="logo"> <img alt=""
								src="/resource/images/toDayNewsLogo.png" width="108px" height="38px">
						</a></li>
						<li><a href="/"
							class="channel-item ${article.channelId==null?"active":""}">热点</a></li>
						<c:forEach items="${channels}" var="channel">
							<li><a href="/?channelId=${channel.id}"
								class="channel-item ${article.channelId==channel.id?"active":"" }">${channel.name }</a></li>
						</c:forEach>


					</ul>

				</div>
			</div>
			<!--中间显示 文章列表 -->
			<div class="col-md-7">
				<!-- 如果是栏目id为空，则显示热点文章-->
				<c:if test="${article.channelId==null }">
					<!-- 显示轮播图 -->
					<div class="mb-3">

						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach items="${slides}" var="slide" varStatus="i">
									<li data-target="#carouselExampleCaptions"
										data-slide-to="${i.index}" class="${i.index==0?"active":""}"></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
								<c:forEach items="${slides }" var="slide" varStatus="i">
									<div class="carousel-item ${i.index==0?"active":"" }">
										<img src="${slide.picture }" class="d-block w-100 rounded" alt="..." style="width: 250px;height: 220px">
										<div class="carousel-caption d-none d-md-block">
											<h5>${slide.title }</h5>
										</div>
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


					</div>




			<!--当点击热点时  即栏目id为null   则显示热点文章的样式  使点击标题 能加在对应的文章信息 -->
					<ul class="list-unstyled">
						<c:forEach items="${info.list}" var="hotArticle">
							<li class="media"><a href="/detail?id=${hotArticle.id }"
								target="_blank"><img style="height: 101.8px; width: 156px"
									src="${hotArticle.picture }" class="mr-3 rounded"
									alt="..."></a>
								<div class="media-body">
									<div class="title-box">
										<a href="/detail?id=${hotArticle.id }" target="_blank">
											${hotArticle.title }</a>
									</div>
									<p>${hotArticle.user.username}·${hotArticle.displayDate }</p>
								</div></li>
							<hr>
						</c:forEach>
					</ul>
					<jsp:include page="/WEB-INF/common/pages.jsp" />
				</c:if>





				<!-- 如果栏目id不为null  -->
			
				<c:if test="${article.channelId!=null }">
					<!-- 显示栏目的分类 -->
					<div class="subchannel">
						<ul class="sub-list">
							<li class="sub-item ${article.categoryId==null?"sub-selected":"" }"><a
								href="/?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${categorys}" var="category">
								<li class="sub-item"><a
									href="/?channelId=${article.channelId}&categoryId=${category.id }"
									class="${article.categoryId==category.id?"sub-selected":"" }">
										${category.name }</a></li>
							</c:forEach>
						</ul>
					</div>
					<!-- 显示文章  使点击标题 显示分类菜单及分类文章-->
					<div>


						<ul class="list-unstyled">
							<c:forEach items="${info.list}" var="article">
								<li class="media"><a href="/detail?id=${article.id }"
									target="_blank"><img style="height: 101.8px; width: 156px"
										src="${article.picture }" class="mr-3 rounded" alt="..."></a>
									<div class="media-body">
										<div class="title-box">
											<a href="/detail?id=${article.id }" target="_blank"  <!-- _blank 在新窗口 打开这个链接 _parent在父窗口打开 _self在当前窗口打开为默认值 _top 在当前窗口打开  并且替换当前整个窗体(框架页) -->
												class="title">${article.title }</a>
										</div>
										<p>${article.user.username}·${article.displayDate }</p>
									</div></li>
								<hr>
							</c:forEach>
						</ul>
						<jsp:include page="/WEB-INF/common/pages.jsp" />



					</div>
				</c:if>


			</div>
			<!-- 右侧	 -->
			<div class="col-md-3">
			
			<form action="/">
					<!--  搜索框 -->
					<div class="card"
						style="width: 18rem; margin-bottom: 5px; border: 0px">
						<div class="form-inline">
							<input type="text" placeholder="请输入要搜索的内容" class="form-control"
								style="width: 14rem; margin-right: 5px" name="key" value=${key }>
							<!-- <button class="btn btn-info" type="button">搜索</button> -->
							<input type="submit" value="搜索"  class="btn btn-info">
						</div>
	
					</div>
				</form>
			
			
			
			
			<div class="card" style="width: 18rem;">
	<div class="card-header title-box">24小时热文</div>
	<div class="card-body">

		<ul class="list-unstyled">
			<c:forEach items="${hot24Articles.list}" var="hot24Article">
				<li class="media"><a href="/detail?id=${hot24Article.id }"
					target="_blank"><img style="height: 60px; width: 60px"
						src="${hot24Article.picture }" class="mr-3 rounded" alt="..."></a>
					<div class="media-body">
					    <p>
							<a href="/detail?id=${hot24Article.id }" target="_blank">
								${hot24Article.title }</a>
						</p>
					
					</div></li>
				<hr>
			</c:forEach>
		</ul>


	</div>
</div>
			
			<!-- 最新文章 -->
				<div class="card" style="width: 18rem;">
					<div class="card-header" style="text-align: center;">最新文章</div>
					<div class="card-body">
						<c:forEach items="${lastInfo.list}" var="lastArticle">
							<ul class="list-unstyled">
								<li class="media"><img src="${lastArticle.picture }"
									class="mr-3" alt="..." width="60" height="60">
									<div class="media-body">
										<p style="font-size: 14px">
											<a href="/articleDetail?id=${lastArticle.id}"
												target="_blank">${lastArticle.title }</a>
										</p>
									</div></li>
							</ul>
							<hr>
						</c:forEach>
					</div>
				</div>
			
			
			</div>


		</div>

	</div>
			
</body>
<script type="text/javascript">
function goPage(pageNum) {
	var channelId = '${article.channelId}';
	var categoryId = '${article.categoryId}';
	var key = '${key}';
	var url = "/?pageNum=" + pageNum + "&key=" + key;
	if (channelId != "")
		url += "&channelId=" + channelId;
	if (categoryId != "")
		url += "&categoryId=" + categoryId;

	location.href = url;
}

</script>
</html>