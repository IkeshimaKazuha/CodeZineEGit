/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-05-07 01:17:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class LeftMenu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      out.write("<style>\r\n");
      out.write("\t.ニックネーム{\r\n");
      out.write("\t\tborder-bottom:3px solid #69A4D8;\r\n");
      out.write("\t}\r\n");
      out.write("\t.情報{\r\n");
      out.write("\t\tborder-bottom:3px solid #69A4D8;\r\n");
      out.write("\t}\r\n");
      out.write("\t.container3{\r\n");
      out.write("\t\tbackground-color:white;\r\n");
      out.write("\t\theight:1000px;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t.画像class{\r\n");
      out.write("\t\tzoom: 2;\r\n");
      out.write("\t}\r\n");
      out.write("</style>\r\n");
      out.write("<div class=\"container3\">\r\n");
      out.write("\t<div class=\"ニックネーム\">\r\n");
      out.write("\t\t<div class=\"ニックネーム1\">\r\n");
      out.write("\t\t\tニックネーム\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"画像1\">\r\n");
      out.write("\t\t\t<img src=\"img/left_triangle.png\" class=\"画像class\">\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"情報\">\r\n");
      out.write("\t\t<div class=\"情報1\">\r\n");
      out.write("\t\t\t情報\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"情報中身\">\r\n");
      out.write("\t\t\t　星座\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"情報中身\">\r\n");
      out.write("\t\t\t　役職\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"情報中身\">\r\n");
      out.write("\t\t\t　電話番号\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"その他\">\r\n");
      out.write("\t\t<div class=\"その他中身\">\r\n");
      out.write("\t\tマイページ\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"情報中身\">\r\n");
      out.write("\t\t掲示板\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"情報中身\">\r\n");
      out.write("\t\t書類申請\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"情報中身\">\r\n");
      out.write("\t\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/Find\">ユーザー検索</a>\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"情報中身\">\r\n");
      out.write("\t\tE-ラーニング\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
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
}
