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
<title>用户管理</title>
</head>
<body>
   <div class="container-fluid">
      <div class="row">
              <div class="form-group col-md-3 form-inline">
			    <label for="username">用户名称：</label>
			    <input id="username" class="form-control ml-1"   value="${user.username }">
			  </div>
			   <div class="col-md-3 form-inline">
			      状态：
			     <div class="ml-1">
			         <input type="radio" checked  name="locked" ${user.locked==null?"checked":"" }  value="">全部
			     </div>
			     <div class="ml-1">
			         <input type="radio" name="locked"  ${user.locked==false?"checked":"" }  value="0">正常
			     </div>
			      <div class="ml-1">
			         <input type="radio" name="locked" ${user.locked==true?"checked":"" }  value="1">禁用
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
		      <th scope="col">用户名称</th>
		      <th scope="col">用户昵称</th>
		      <th scope="col">生日</th>
		      <th scope="col">性别</th>
		      <th scope="col">创建时间</th>
		      <th scope="col">状态</th>
		    </tr>
  </thead>
    
  <tbody>
    <c:forEach items="${info.list }" var="u" varStatus="index">
    	<tr>
	      <th scope="row">${index.count }</th>
	      <td>${u.username }</td>
	      <td>${u.nickname }</td>
	      <td><fmt:formatDate value="${u.birthday }" pattern="yyyy-MM-dd"/> </td>
	      <td>${u.gender.disPlay }</td>
	      <td><fmt:formatDate value="${u.created }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	      <td  class="text-center">
	         <c:if test="${!u.locked }">
	           	<button type="button" class="btn btn-info btn-sm" onclick="updatelocked(${u.id},$(this))">正常</button>
	         </c:if>
	         <c:if test="${u.locked}">
	           	<button type="button" class="btn btn-danger btn-sm" onclick="updatelocked(${u.id},$(this))">禁用</button>
	         </c:if>
	      </td>
    </tr>
    </c:forEach> 
  </tbody>
    </table>
     <jsp:include page="/WEB-INF/common/pages.jsp"></jsp:include>
     

     
     

</body>

   <script type="text/javascript">
   
     function  getList(){
    	  //获得用户名称
    	   var username = $.trim($("#username").val());
    	  //获取状态得查询条件
    	  var locked = $("[name='locked']:checked").val();
    	 $("#center").load("/admin/getUserList.do?username="+username+"&locked="+locked);
     }
     
     //分页
     function  goPage(pageNum){
    	 //获得用户名称
    	  var username = $.trim($("#username").val());
    	  //获取状态得查询条件
    	  var locked = $("[name='locked']:checked").val();
    	  
    	 $("#center").load("/admin/getUserList.do?username="+username+"&locked="+locked+"&pageNum="+pageNum); 
     }
     
     //控制是否禁用
     function  updatelocked(id,obj){
    	 var locked = obj.text()=="禁用"?0:1;
    	 $.post(
   	    	  "/admin/user/update.do",
   	    	  {id:id,locked:locked},
   	    	  function(data){
   	    		 if(data){
   	    		   //改变是否热门
   	    		  var  txt = obj.text()=="禁用"?"正常":"禁用";
   	    		  obj.text(txt);
   	    		  //改变样式
   	    	      obj.attr("class",obj.text()=="禁用"?"btn btn-danger btn-sm":"btn btn-info btn-sm"); 
   	    		 }
   	    	  },"json"
    	  )
    	 
     }
     
     
     
   
   </script>

</html>