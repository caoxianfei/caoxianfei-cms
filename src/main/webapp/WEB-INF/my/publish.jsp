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
	<form name="example" method="post" action="demo.jsp" id="form1">
	
			<div class="form-group">
			<label for="title"> 文章标题：</label> <input id="title"
				class="form-control" type="text" name="title">
				<%-- <input type="hidden" name="userId" value="${sessionScope.user.id }"> --%>
		</div>
		<div class="form-group">
			<label for="file">标题图片：</label> <input id="file" type="file"
				class="form-control-file" name="file">
		</div>
		<div class="form-group form-inline">
			所属栏目： <select class="form-control" id="channel" name="channelId">
				<option>请选择</option>
			</select> 所属分类： <select class="form-control" id="category" name="categoryId">
				
			</select>

		</div>
	
	
	
		<textarea name="content1" cols="100" rows="8" style="width:1248.16px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="button" name="button" class="btn btn-info" value="发布" onclick="publish()"/>
		
	</form>
</body>
<script type="text/javascript">
		$(function(){
			$.get("/my/channels",function(obj){
				
				for ( var i in obj) {
					$("#channel").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
				}
				
				$("#channel").change(function(){
					var channelId = $(this).val();
					$("#category").empty();
					$.get("/my/categorys",{channelId:channelId},function(obj){
						for ( var j in obj) {
							$("#category").append("<option value='"+obj[j].id+"'>"+obj[j].name+"</option>");
						}
					})
				})
			})
		})

		function publish(){
			var formData = new FormData($("#form1")[0]);
			formData.set("content",editor1.html());
			
			$.ajax({
				url:"/my/publish",
				data:formData,
				type:"post",
				processData:false,
				contentType:false,
				success:function(obj){
					if(obj>0){
						alert("发布成功!");
						location.href="/my";
					}else{
						alert("发布失败!");
					}
				}
			})
			
		}
		
		
		
		
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