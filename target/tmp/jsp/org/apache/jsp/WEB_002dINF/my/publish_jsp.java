/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.7.v20160115
 * Generated at: 2020-05-10 12:53:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.my;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class publish_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {


private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";

      out.write("\r\n");
      out.write("<!doctype html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t<title>KindEditor JSP</title>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"/resource/kindeditor/themes/default/default.css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"/resource/kindeditor/plugins/code/prettify.css\" />\r\n");
      out.write("\t<script charset=\"utf-8\" src=\"/resource/kindeditor/kindeditor.js\"></script>\r\n");
      out.write("\t<script charset=\"utf-8\" src=\"/resource/kindeditor/lang/zh-CN.js\"></script>\r\n");
      out.write("\t<script charset=\"utf-8\" src=\"/resource/kindeditor/plugins/code/prettify.js\"></script>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tKindEditor.ready(function(K) {\r\n");
      out.write("\t\t\twindow.editor1 = K.create('textarea[name=\"content1\"]', {\r\n");
      out.write("\t\t\t\tcssPath : '/resource/kindeditor/plugins/code/prettify.css',\r\n");
      out.write("\t\t\t\tuploadJson : '/resource/kindeditor/jsp/upload_json.jsp',\r\n");
      out.write("\t\t\t\tfileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',\r\n");
      out.write("\t\t\t\tallowFileManager : true,\r\n");
      out.write("\t\t\t\tafterCreate : function() {\r\n");
      out.write("\t\t\t\t\tvar self = this;\r\n");
      out.write("\t\t\t\t\tK.ctrl(document, 13, function() {\r\n");
      out.write("\t\t\t\t\t\tself.sync();\r\n");
      out.write("\t\t\t\t\t\tdocument.forms['example'].submit();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\tK.ctrl(self.edit.doc, 13, function() {\r\n");
      out.write("\t\t\t\t\t\tself.sync();\r\n");
      out.write("\t\t\t\t\t\tdocument.forms['example'].submit();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tprettyPrint();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      out.print(htmlData);
      out.write("\r\n");
      out.write("\t<form  id=\"form\">\r\n");
      out.write("\t\t <div class=\"form-group pt-2\">\r\n");
      out.write("\t\t    <label for=\"title\">文章标题</label>\r\n");
      out.write("\t\t    <input  class=\"form-control\" name=\"title\" id=\"title\" >\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t  <div class=\"form-group pt-2\">\r\n");
      out.write("\t\t    <div class=\"row\">\r\n");
      out.write("\t\t      \t<div class=\"col-md-2\">\r\n");
      out.write("\t\t\t\t    <label for=\"channelId\">所属栏目:</label>\r\n");
      out.write("\t\t\t\t    <select id=\"channelId\" name=\"channelId\">\r\n");
      out.write("\t\t\t\t      <option> --请选择--</option>\r\n");
      out.write("\t\t\t\t    </select>\r\n");
      out.write("\t\t    \t</div>\r\n");
      out.write("\t\t    \t<div class=\"col-md-2\">\r\n");
      out.write("\t\t\t\t    <label for=\"categoryId\">所属分类:</label>\r\n");
      out.write("\t\t\t\t    <select id=\"categoryId\" name=\"categoryId\">\r\n");
      out.write("\t\t\t\t      <option> --请选择--</option>\r\n");
      out.write("\t\t\t\t    </select>\r\n");
      out.write("\t\t    \t</div>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t   <div class=\"form-group pt-2\">\r\n");
      out.write("\t\t    <input type=\"file\" class=\"form-control-file\" name=\"file\"  >\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<textarea name=\"content1\" cols=\"100\" rows=\"8\" style=\"width:1570px;height:350px;visibility:hidden;\">");
      out.print(htmlspecialchars(htmlData));
      out.write("</textarea>\r\n");
      out.write("\t\t<br />\r\n");
      out.write("\t\t<input id=\"tj\" type=\"button\" name=\"button\" value=\"发表文章\" />\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        $(function(){\r\n");
      out.write("        \t\r\n");
      out.write("        \t//获取栏目列表\r\n");
      out.write("        \t $.post(\r\n");
      out.write("        \t    \"/channel/getChannelList.do\", //地址\r\n");
      out.write("        \t    function(obj){  //obj  getChannelList.do查询后的结果\r\n");
      out.write("        \t       //console.log(obj);\t\r\n");
      out.write("\t        \t    for ( var i in obj) {\r\n");
      out.write("\t\t\t\t\t\t$(\"#channelId\").append(\"<option value='\"+obj[i].id+\"'>\"+obj[i].name+\"</option>\")\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("        \t    },\"json\"\r\n");
      out.write("        \t )\r\n");
      out.write("        });\r\n");
      out.write("       \r\n");
      out.write("        //栏目改变时获取选中栏目下面的分类\r\n");
      out.write("        $(\"#channelId\").change(function(){\r\n");
      out.write("        \tvar channelId = $(this).val(); //获取选中栏目的id\r\n");
      out.write("        \t// 清空以前的分类\r\n");
      out.write("        \t $(\"#categoryId\").html(\"\");\r\n");
      out.write("        \t//获取分类列表\r\n");
      out.write("        \t $.post(\r\n");
      out.write("             \t    \"/channel/getCategoryList.do\", //请求地址\r\n");
      out.write("             \t    {\"channelId\":channelId}, //选中栏目的id\r\n");
      out.write("             \t    function(obj){  //obj是getChannelList.do查询后的结果\r\n");
      out.write("             \t      // console.log(obj);\t\r\n");
      out.write("     \t        \t   for ( var i in obj) {\r\n");
      out.write("     \t\t\t\t\t\t$(\"#categoryId\").append(\"<option value='\"+obj[i].id+\"'>\"+obj[i].name+\"</option>\")\r\n");
      out.write("     \t\t\t\t\t} \r\n");
      out.write("             \t    },\"json\"\r\n");
      out.write("             \t )\r\n");
      out.write("        })\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        $(\"#tj\").click(function(){\r\n");
      out.write("        \tvar formData = new FormData($(\"#form\")[0]);\r\n");
      out.write("        \t//获取富文本编辑器中的内容\r\n");
      out.write("        \tvar  con =editor1.html();\r\n");
      out.write("        \t\r\n");
      out.write("        \tformData.set(\"content\",con); //把con赋值给content\r\n");
      out.write("        \t$.ajax({\r\n");
      out.write("        \t\turl:\"/my/publish/article.do\", //发布文章的方法\r\n");
      out.write("        \t\tdata:formData,  //数据\r\n");
      out.write("        \t\tdataType:\"json\", //返回数据格式\r\n");
      out.write("        \t\ttype:\"post\",  //提交方式\r\n");
      out.write("        \t\tprocessData:false, //其代表以对象的形式上传的数据都会被转换为字符串的形式\r\n");
      out.write("        \t\tcontentType:false, //前端发送数据的格式\r\n");
      out.write("        \t\tsuccess:function(obj){ //方法访问成功后的回调参数\r\n");
      out.write("        \t\t\tif(obj){\r\n");
      out.write("        \t\t\t\t$(\"#content\").load(\"/my/article.do\");\r\n");
      out.write("        \t\t\t}else{\r\n");
      out.write("        \t\t\t\talert(\"发布失败！\")\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("        \t\t}\r\n");
      out.write("        \t})\r\n");
      out.write("        \t\r\n");
      out.write("        })\r\n");
      out.write("        \r\n");
      out.write("    \r\n");
      out.write("        \r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
