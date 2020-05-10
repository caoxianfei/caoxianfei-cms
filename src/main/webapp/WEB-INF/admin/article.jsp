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
<title>文章审核</title>
</head>
<body>
   <div class="container-fluid">
      <div class="row">
              <div class="form-group col-md-3 form-inline">
			    <label for="title">标题：</label>
			    <input id="title" class="form-control ml-1" id="title"  value="${article.title }">
			  </div>
			   <div class="col-md-3 form-inline">
			      状态：
			     <div class="ml-1">
			         <input type="radio" checked name="status" value="2">全部
			     </div>
			     <div class="ml-1">
			         <input type="radio" name="status" ${article.status==0?"checked":"" }  value="0">待审
			     </div>
			      <div class="ml-1">
			         <input type="radio" name="status" ${article.status==1?"checked":"" } value="1">已审
			     </div>
			      <div class="ml-1">
			         <input type="radio" name="status" ${article.status==-1?"checked":"" } value="-1">驳回
			     </div>
			   
			   </div>
			  
	         <div>
	            <button type="button" onclick="getList()" class="btn btn-info">查询</button>
	         </div>
      </div>
   </div>

    <table class="table table-bordered">
    	 <thead>
		    <tr>
		      <th scope="col">序号</th>
		      <th scope="col">标题</th>
		      <th scope="col">所属栏目</th>
		      <th scope="col">所属分类</th>
		      <th scope="col">作者</th>
		      <th scope="col">发布时间</th>
		      <th scope="col">是否热门</th>
		      <th scope="col">状态</th>
		      <th scope="col">详情</th>
		    </tr>
  </thead>
    
  <tbody>
    <c:forEach items="${info.list }" var="a" varStatus="index">
    <tr>
      <th scope="row">${index.count }</th>
      <td>${a.title }</td>
      <td>${a.channel.name }</td>
      <td>${a.category.name }</td>
      <td>${a.user.username }</td>
      <td><fmt:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
      <td  class="text-center">
      		  <c:if test="${a.hot ==0 }">
                 <button type="button" class="btn btn-info btn-sm" onclick="updateHot(${a.id},$(this))">否</button>
              </c:if>
               <c:if test="${a.hot ==1 }">
                 <button type="button" class="btn btn-danger btn-sm" onclick="updateHot(${a.id},$(this))">是</button>
              </c:if>
      </td>
      <td>${a.status==0?"待审":a.status==1?"通过":"驳回" }</td>
      <td>
        <button type="button" onclick="datail(${a.id})" class="btn btn-link" data-toggle="modal" data-target="#exampleModalLong">
 		详情</button>
 		</td>
    </tr>
    </c:forEach>
  </tbody>
    </table>
     <jsp:include page="/WEB-INF/common/pages.jsp"></jsp:include>
     
     
<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"><span id="t"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
      
      </div>
      <div class="modal-footer">
         <button type="button" class="btn btn-info" onclick="update(1)">通过</button>
         <button type="button" class="btn btn-danger" onclick="update(-1)">驳回</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> 
     
     

</body>

   <script type="text/javascript">
      //全局变量
     var  aid;
   
     function  getList(){
    	 //获取标题的查询条件 $.trim() 取除前后空格
    	var title= $.trim($("#title").val());
    	 //获取状态的查询条件
    	 var status = $("[name='status']:checked").val();
    	 $("#center").load("/admin/getArticleList.do?title="+title+"&status="+status);
     }
     
     //分页
     function  goPage(pageNum){
    	 //获取标题的查询条件 $.trim() 取除前后空格
     	var title= $.trim($("#title").val());
     	 //获取状态的查询条件
     	 var status = $("[name='status']:checked").val();
    	 $("#center").load("/admin/getArticleList.do?title="+title+"&status="+status+"&pageNum="+pageNum); 
     }
     
     
     //详情
     function  datail(id){
    	 //模态框中深刻文章需要文章的id
    	  aid= id;  //给全局变量赋值
    	 //清空以前标题和内容
    	 $("#t").html("");
    	 $("#content").html("");
    	 $.post(
    	   "/admin/getArticleById.do",
    	   {articleId:id},
    	   function(obj){
    		   $("#t").append(obj.title);
    		   $("#content").append(obj.content);
    	   },"json"
    	 )
     }
     
     //文章审核
     function  update(status){
    	 $.post(
    	  "/admin/update.do",
    	  {id:aid,status:status},
    	  function(obj){
    		 if(obj){
    			 location.reload();
    		 }
    		  
    	  },"json"
    	 
    	 )
    	 
     }
     
     //控制是否热门
     function  updateHot(id,obj){
    	 var hot = obj.text()=="是"?0:1;
    	 $.post(
   	    	  "/admin/update.do",
   	    	  {id:id,hot:hot},
   	    	  function(data){
   	    		 if(data){
   	    		  //改变是否热门
   	    		  var  txt = obj.text()=="是"?"否":"是";
   	    		  obj.text(txt);
   	    		  //改变样式
   	    	      obj.attr("class",obj.text()=="是"?"btn btn-danger btn-sm":"btn btn-info btn-sm");
   	    		 }
   	    	  },"json"
    	  )
    	 
     }
     
     
     
   
   </script>

</html>