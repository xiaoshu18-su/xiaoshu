/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-06-25 13:01:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class template_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

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

      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<title>合同管理系统 - 列表列表</title>\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/favicon.ico\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/bootstrap.min.css?v=3.3.6\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/font-awesome.css?v=4.4.0\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/animate.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/style.css?v=4.1.0\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/sweetalert.css\" rel=\"stylesheet\">\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"gray-bg\">\r\n");
      out.write("\t<div class=\"wrapper wrapper-content animated fadeInRight\" id=\"temptop\">\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t    <div class=\"col-sm-2\">\r\n");
      out.write("\t\t        <a href=\"#templateform\" class=\"btn btn-primary btn-xs\">添加模板</a>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t\t<div class=\"col-sm-9\">\r\n");
      out.write("\t\t\t\t<table class=\"table table-striped\">\r\n");
      out.write("\t\t\t\t  <thead>\r\n");
      out.write("\t\t\t\t   <tr>\r\n");
      out.write("\t\t\t\t      <th>模板编号</th>\r\n");
      out.write("\t\t\t\t      <th>模板文件</th>\r\n");
      out.write("\t\t\t\t      <th>操作</th>\r\n");
      out.write("\t\t\t\t   </tr>\r\n");
      out.write("\t\t\t\t  </thead>\r\n");
      out.write("\t\t\t\t  <tbody id=\"template\"></tbody>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"col-sm-1\">\r\n");
      out.write("\t\t\t    <a href=\"javascript:window.location.reload();\"\r\n");
      out.write("                    class=\"collapse-link\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"刷新\">\r\n");
      out.write("                <i class=\"fa fa-refresh\"></i></a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t    <div class=\"col-sm-2\"></div>\r\n");
      out.write("\t\t    <div class=\"col-sm-10\">\r\n");
      out.write("\t\t        <form class=\"form-inline\" id=\"templateform\" method=\"post\" enctype=\"multipart/form-data\" role=\"form\">\r\n");
      out.write("\t\t\t\t  <div class=\"form-group\">\r\n");
      out.write("\t\t\t\t    <label for=\"template_file\">选择文件</label>\r\n");
      out.write("\t\t\t\t    <input type=\"file\" id=\"template_file\" name=\"template_file\">\r\n");
      out.write("\t\t\t\t  </div>\r\n");
      out.write("\t\t\t\t  <button type=\"submit\" class=\"btn btn-default btn-xs\">上传模板</button>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t    <div class=\"col-sm-1\">\r\n");
      out.write("\t\t       <a href=\"#temptop\" class=\"btn btn-info btn-xs\">回到顶部</a>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.min.js?v=2.1.4\"></script>\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/bootstrap.min.js?v=3.3.6\"></script>\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/content.js?v=1.0.0\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/iCheck/icheck.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/validate/jquery.validate.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/plugins/validate/messages_zh.min.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/sweetalert.min.js\"></script>\r\n");
      out.write("\t<script>\r\n");
      out.write("    //调用方法\r\n");
      out.write("    querylist();\r\n");
      out.write("\t\r\n");
      out.write("\t//查出列表方法\r\n");
      out.write("    function querylist(){\r\n");
      out.write("    \t$.post(\r\n");
      out.write("    \t   \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/template/list.do\",\r\n");
      out.write("    \t   {},\r\n");
      out.write("    \t   function(data){\r\n");
      out.write("    \t\t  var tr=\"\";\r\n");
      out.write("    \t\t  $(\"#template\").empty();\r\n");
      out.write("    \t\t  if(data!=null){\r\n");
      out.write("    \t\t\t  $.each(data,function(i,t){\r\n");
      out.write("        \t\t\t  tr+=\"<tr><td>\"+t.template_id+\"</td><td>\"+\r\n");
      out.write("        \t\t\t  \"<a href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/index/filedownload.do?myfile=\"\r\n");
      out.write("        \t\t\t  +t.template_file+\"&filename=\"+t.template_filename+\"'>\"+\r\n");
      out.write("        \t\t\t  t.template_filename+\"</a></td><td><button onclick='fdelete(\"+t.template_id+\")'\"+ \r\n");
      out.write("        \t\t\t  \"type='button' class='btn btn-danger btn-xs'>删除</button></td></tr>\";\r\n");
      out.write("        \t\t  });\r\n");
      out.write("    \t\t  }\r\n");
      out.write("    \t\t  $(\"#template\").html(tr);\r\n");
      out.write("    \t   }\r\n");
      out.write("    \t);\r\n");
      out.write("    }\r\n");
      out.write("\t\r\n");
      out.write("  //错误提示信息图标、\r\n");
      out.write("    var icon = \"<i class='fa fa-times-circle'></i>\";\r\n");
      out.write("\t//使用jquery.validate.js进行校验\r\n");
      out.write("\t$(\"#templateform\").validate({\r\n");
      out.write("\t\t/*  自定义规则 */\r\n");
      out.write("\t\trules:{\r\n");
      out.write("\t\t\ttemplate_file:{\r\n");
      out.write("\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tmessages:{\r\n");
      out.write("\t\t\ttemplate_file:{\r\n");
      out.write("\t\t\t\trequired:icon+\"请选择文件\",\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\t//使用formdata和ajax提交表单，包括文件上传\r\n");
      out.write("\t\tsubmitHandler:function(form){\r\n");
      out.write("\t\t\tvar file=$(\"#template_file\").val();\r\n");
      out.write("        \tvar extStart=file.lastIndexOf(\".\");\r\n");
      out.write("\t\t\tvar ext=file.substring(extStart,file.length).toUpperCase();\r\n");
      out.write("\t\t\tif(ext!=\".DOC\"&&ext!=\".DOCX\"&&ext!=\".WPS\"&&ext!=\".PDF\"){\r\n");
      out.write("\t\t\t\t\tswal({\r\n");
      out.write("\t\t\t\t\t\ttitle:\"添加失败\",\r\n");
      out.write("\t\t\t\t\t\ttext:\"请上传doc、docx、wps、pdf等文件\",\r\n");
      out.write("\t\t\t\t\t\ttype:\"error\"\r\n");
      out.write("\t\t\t\t    });\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t     var formData = new FormData($( \"#templateform\" )[0]); \r\n");
      out.write("\t\t     $.ajax({  \r\n");
      out.write("\t\t          url: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/template/add.do',  \r\n");
      out.write("\t\t          type: 'POST',  \r\n");
      out.write("\t\t          data: formData,  \r\n");
      out.write("\t\t          cache: false,  \r\n");
      out.write("\t\t          contentType: false,  \r\n");
      out.write("\t\t          processData: false,  \r\n");
      out.write("\t\t          success: function (data) {  \r\n");
      out.write("\t\t        \t  if(data == \"no\"){\r\n");
      out.write("\t\t \t\t\t\t\tswal({\r\n");
      out.write("\t\t \t\t\t\t\t\ttitle:\"添加失败\",\r\n");
      out.write("\t\t \t\t\t\t\t\ttext:\"请检查信息\",\r\n");
      out.write("\t\t \t\t\t\t\t\ttype:\"error\"\r\n");
      out.write("\t\t \t\t\t\t\t});\r\n");
      out.write("\t\t \t\t\t\t}\r\n");
      out.write("\t\t        \t  querylist();\r\n");
      out.write("\t\t          }, \r\n");
      out.write("\t\t          error:function(){\r\n");
      out.write("\t\t        \t  swal({\r\n");
      out.write("\t \t\t\t\t\t\ttitle:\"添加失败\",\r\n");
      out.write("\t \t\t\t\t\t\ttext:\"服务器出错\",\r\n");
      out.write("\t \t\t\t\t\t\ttype:\"error\"\r\n");
      out.write("\t \t\t\t\t\t});\r\n");
      out.write("\t\t          }\r\n");
      out.write("\t\t     }); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction fdelete(id){\r\n");
      out.write("\t\t$.post(\r\n");
      out.write("\t\t    \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/template/delete.do\",\r\n");
      out.write("\t\t    {template_id:id},\r\n");
      out.write("\t\t    function(data){\r\n");
      out.write("\t\t    \tif(data==\"no\"){\r\n");
      out.write("\t\t    \t\tswal({\r\n");
      out.write(" \t\t\t\t\t\ttitle:\"删除失败\",\r\n");
      out.write(" \t\t\t\t\t\ttext:\"\",\r\n");
      out.write(" \t\t\t\t\t\ttype:\"error\"\r\n");
      out.write(" \t\t\t\t\t});\r\n");
      out.write("\t\t    \t}\r\n");
      out.write("\t\t    \telse{\r\n");
      out.write("\t\t    \t\tquerylist();\r\n");
      out.write("\t\t    \t}\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t);\r\n");
      out.write("\t}\r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/jsp/template.jsp(4,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    // /WEB-INF/jsp/template.jsp(4,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/template.jsp(4,0) '${pageContext.request.contextPath }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${pageContext.request.contextPath }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }
}
