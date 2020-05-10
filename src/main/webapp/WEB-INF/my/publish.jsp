<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
		<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<%=htmlData%>
	<form  id="form">
		 <div class="form-group pt-2">
		    <label for="title">文章标题</label>
		    <input  class="form-control" name="title" id="title" >
		  </div>
		  
		  <div class="form-group pt-2">
		    <div class="row">
		      	<div class="col-md-2">
				    <label for="channelId">所属栏目:</label>
				    <select id="channelId" name="channelId">
				      <option> --请选择--</option>
				    </select>
		    	</div>
		    	<div class="col-md-2">
				    <label for="categoryId">所属分类:</label>
				    <select id="categoryId" name="categoryId">
				      <option> --请选择--</option>
				    </select>
		    	</div>
		    </div>
		  </div>
		  
		   <div class="form-group pt-2">
		    <input type="file" class="form-control-file" name="file"  >
		  </div>
		
		<textarea name="content1" cols="100" rows="8" style="width:1570px;height:350px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input id="tj" type="button" name="button" value="发表文章" />
	</form>
</body>
    <script type="text/javascript">
        $(function(){
        	
        	//获取栏目列表
        	 $.post(
        	    "/channel/getChannelList.do", //地址
        	    function(obj){  //obj  getChannelList.do查询后的结果
        	       //console.log(obj);	
	        	    for ( var i in obj) {
						$("#channelId").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>")
					}
        	    },"json"
        	 )
        });
       
        //栏目改变时获取选中栏目下面的分类
        $("#channelId").change(function(){
        	var channelId = $(this).val(); //获取选中栏目的id
        	// 清空以前的分类
        	 $("#categoryId").html("");
        	//获取分类列表
        	 $.post(
             	    "/channel/getCategoryList.do", //请求地址
             	    {"channelId":channelId}, //选中栏目的id
             	    function(obj){  //obj是getChannelList.do查询后的结果
             	      // console.log(obj);	
     	        	   for ( var i in obj) {
     						$("#categoryId").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>")
     					} 
             	    },"json"
             	 )
        })
        
        
        $("#tj").click(function(){
        	var formData = new FormData($("#form")[0]);
        	//获取富文本编辑器中的内容
        	var  con =editor1.html();
        	
        	formData.set("content",con); //把con赋值给content
        	$.ajax({
        		url:"/my/publish/article.do", //发布文章的方法
        		data:formData,  //数据
        		dataType:"json", //返回数据格式
        		type:"post",  //提交方式
        		processData:false, //其代表以对象的形式上传的数据都会被转换为字符串的形式
        		contentType:false, //前端发送数据的格式
        		success:function(obj){ //方法访问成功后的回调参数
        			if(obj){
        				$("#content").load("/my/article.do");
        			}else{
        				alert("发布失败！")
        			}
        		}
        	})
        	
        })
        
    
        
    </script>

</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>