package org.apache.jsp.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class portal_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;
static private org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_1;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("fn:length", org.apache.taglibs.standard.functions.Functions.class, "length", new Class[] {java.lang.Object.class});
  _jspx_fnmap_1= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("fn:substring", org.apache.taglibs.standard.functions.Functions.class, "substring", new Class[] {java.lang.String.class, int.class, int.class});
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/c.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>车主服务</title>\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no\">\r\n");
      out.write("<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"../css/mui.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"../css/cars.css?version=1.1.0.1\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/jquery/jquery-1.11.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/jquery/jquery.cookie.js\"></script>\r\n");
      out.write("<script src=\"https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/remoteLogUtil.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/mui.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"mui-content\">\r\n");
      out.write("\t\t<div class=\"transparent_bg\" >\r\n");
      out.write("\t\t<!-- \r\n");
      out.write("\t\t\t<header class=\"mui-bar mui-bar-nav transparent_cl\">\t\t\t\r\n");
      out.write("\t\t\t\t<a class=\"mui-icon mui-pull-left\"  onclick=\"goBack();\" href=\"#\">\r\n");
      out.write("\t\t\t\t    <img src=\"../images/back-icon.png\" class=\"exit-icon\">\r\n");
      out.write("\t\t\t\t    <span class=\"back-span\">返回</span>\r\n");
      out.write("\t          \t</a>\r\n");
      out.write("\t\t\t\t<h1 class=\"mui-title car-mui-title\">车主服务</h1>\r\n");
      out.write("\t\t\t\t<a class=\"mui-icon mui-pull-right\" href=\"#\" onclick=\"toCenter();\">\r\n");
      out.write("\t\t\t\t\t<img src=\"../images/center.png\" class=\"personal-center\">\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</header>  -->\r\n");
      out.write("\t\t\t<div class=\"car-index-top-box transparent_class\">\r\n");
      out.write("\t\t\t\t<div class=\"index-img-top-box\">\r\n");
      out.write("\t\t\t\t\t<a id=\"top-img-a\" href=\"#\">\r\n");
      out.write("\t\t\t\t\t\t<img id=\"top-img\" src=\"\" class=\"index-img-top\">\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<a id=\"top-span-a\" href=\"#\">\r\n");
      out.write("\t\t\t\t\t<span id=\"car-title\" class=\"car-top-span-car-title\"></span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"index-location-top transparent_class\"\r\n");
      out.write("\t\t\t\tonclick=\"locate(this);\">\r\n");
      out.write("\t\t\t\t<span class=\"mui-icon index-location-top-img\"></span> <span\r\n");
      out.write("\t\t\t\t\tclass=\"mui-icon car-index-location-top-span\" id=\"location\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write(' ');
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t</span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"index-msg-top-bottom transparent_class\"\r\n");
      out.write("\t\t\t\tid=\"limitedContent\">\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_c_005fif_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<ul class=\"mui-table-view mui-grid-view mui-grid-9\">\r\n");
      out.write("\t\t<!--  -->\r\n");
      out.write("            ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fif_005f6(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("        </ul>\r\n");
      out.write("\t\t<div id=\"slider\" class=\"mui-slider carui-index-banner-box\">\r\n");
      out.write("\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${banner}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar GLOBLE_SESSIONID = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${GLOBLE_SESSIONID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t \t//重构类目\r\n");
      out.write("\t\tfunction reBuildCategoryApp(cityCode, viNumber){\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"../car/queryCategoryAppList\",\r\n");
      out.write("\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\tdata : \"r=\" + Math.random() + '&cityCode='\r\n");
      out.write("\t\t\t\t\t\t+ cityCode + '&viNumber='\r\n");
      out.write("\t\t\t\t\t\t+ encodeURI(viNumber),\r\n");
      out.write("\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\tif (data != '') {\r\n");
      out.write("\t\t\t\t\t\tvar res = eval(data);\r\n");
      out.write("\t\t\t\t\t\tvar appList = \"\";\r\n");
      out.write("\t\t\t\t\t\tfor(var i = 0; i < res.length; i++) {\r\n");
      out.write("\t\t\t\t\t\t\tif(((i + 1) % 3) == 0){\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right\">';\r\n");
      out.write("\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3\">';\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\tif(res[i].app_url != ''){\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '<a class=\"category_app_item\" app_name=\"'+res[i].app_name+'\" app_url=\"'+res[i].app_url+'\" app_id=\"'+res[i].app_id+'\" href=\"javascript:categoryApp(\\''+res[i].app_url+'\\',\\''+res[i].app_name+'\\',\\''+res[i].app_id+'\\');\">';\r\n");
      out.write("\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '<a href=\"#\" onclick=\"toast();\">';\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\tappList+= '<img src=\"'+res[i].app_logo+'\" class=\"carui-img-height\">';\r\n");
      out.write("\t\t\t\t\t\t\tappList+= '<div class=\"mui-media-body carui-active-title\">'+res[i].app_name+'</div>';\r\n");
      out.write("\t\t\t\t\t\t\tappList+= '<div class=\"mui-media-body carui-active-desc\">'+res[i].app_memo+'</div>';\r\n");
      out.write("\t\t\t\t\t\t\tappList+= '</a></li>';\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\tif((res.length % 3) > 0) {\r\n");
      out.write("\t\t\t\t\t\t\tfor(var k = 0; k < (3-(res.length % 3)); k++) {\r\n");
      out.write("\t\t\t\t\t\t\t\tif((3-(res.length % 3)) == k){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tappList+= '<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right\">';\r\n");
      out.write("\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tappList+= '<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3\">';\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '<a><div class=\"carui-img-null-height\"></div>';\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '<div class=\"mui-media-body carui-active-title\"></div>';\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '<div class=\"mui-media-body carui-active-desc\"></div>';\r\n");
      out.write("\t\t\t\t\t\t\t\tappList+= '</a></li>'; \r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t//alert(appList);\r\n");
      out.write("\t\t\t\t\t\t$('.mui-table-view').html(appList);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//用户进入首页埋点\r\n");
      out.write("\t\t/* remoteLog.infoLog('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("','用户进入首页','CARLIFE_HOME_PAGE','-'); */\r\n");
      out.write("\t\tremoteLog.infoLog('mainpage','openPage');\r\n");
      out.write("\t\tfunction categoryApp(appUrl,appName,appCode){\r\n");
      out.write("\t\t\t//首页9宫格埋点\r\n");
      out.write("\t\t\t/* remoteLog.infoLog('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',appName,'CARLIFE_CATEGORY_APP_'+appCode,'-'); */\r\n");
      out.write("\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
      out.write("\t\t\tvar reUrl = /^(https|http)?:/;\r\n");
      out.write("\t\t\tif(!reUrl.test(appUrl)){\r\n");
      out.write("\t\t\t\tif(appUrl.indexOf('20000241') != -1 || appUrl.indexOf('20000781') != -1) {\r\n");
      out.write("\t\t\t\t\tappUrl = appUrl.substring(0,appUrl.indexOf('url=')+4) + encodeURIComponent(appUrl.substring(appUrl.indexOf('url=')+4,appUrl.length));\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t//alert(appUrl);\r\n");
      out.write("\t\t\t\tlocation.href= appUrl;\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\t//alert(appUrl);\r\n");
      out.write("\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\t\turl: appUrl,\r\n");
      out.write("\t\t\t\t\tparam: {\r\n");
      out.write("\t\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t\t            }\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t/**\r\n");
      out.write("\t\t*/\r\n");
      out.write("\t\t$(document).on(\"optionMenu\", function(){\r\n");
      out.write("\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\turl: \"../owner/index?r=\" + Math.random(),\r\n");
      out.write("\t\t\t\tparam: {\r\n");
      out.write("\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t            }\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\tfunction gotoMsg() {\r\n");
      out.write("\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\turl: \"../msg/toMessageList?r=\" + Math.random(),\r\n");
      out.write("\t\t\t\tparam: {\r\n");
      out.write("\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t            }\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction getLimitedContent(cityCode, cityName) {\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fif_005f7(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tmui.init();\r\n");
      out.write("\t\tvar slider = mui(\"#slider\");\r\n");
      out.write("\t\tslider.slider({\r\n");
      out.write("\t\t\tinterval:3000\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(function() {\r\n");
      out.write("\t\t\tsetTimeout(loadJSAPI, 100);\r\n");
      out.write("\t\t\tfunction loadJSAPI(){\r\n");
      out.write("\t\t\t\t// 显示标题栏\r\n");
      out.write("\t\t        AlipayJSBridge.call(\"showTitlebar\");\r\n");
      out.write("\t\t        // 显示右按钮\r\n");
      out.write("\t\t        AlipayJSBridge.call(\"showOptionMenu\");\r\n");
      out.write("\t\t        // 设置标题\r\n");
      out.write("\t\t        AlipayJSBridge.call(\"setTitle\", {\r\n");
      out.write("\t\t            title: '车主服务'\r\n");
      out.write("\t\t        });\r\n");
      out.write("\t\t        // 设置右按钮属性\r\n");
      out.write("\t\t        AlipayJSBridge.call('setOptionMenu', {\r\n");
      out.write("\t\t             icon : 'iVBORw0KGgoAAAANSUhEUgAAAEIAAABCCAYAAADjVADoAAAABGdBTUEAALGPC/xhBQAACelJREFUeAHNmgusHVUVhr30gYBgoa0tD6GF8hDbQqVBigVqIwWkURNUfGDUUMUYrakEU6kBgVCUYAWjNTwCSQE1ARWDsSlFUyog0odQoqUPrraJVcG2ysUitPT6/YeZ6zpr9pyZvc85l67kz+y99lr/Wmefmf2a6XnTIEl/f/+BhDoenJhdD+V6cIa9XPsybOe6McPmnp6eVyh3XXq6FYEfPhzumRney/VdYD8QI7sxfhL8JsNv6ZjXYgjeMFs6YBr4IdgOOi3bILwJTH7DfmCrwCTWAz4Afg8GS5YTSHdaR6TtRyNL5mayqfMvvYSdnv8NYBvIxwU9Mvl4cRTlfBw5gHKVPIrBV3hk1lYZdqWdDjgc3AtayX9p/AWYCyaC2h2P7X5gKrgCPAT2gDJ5jYbvgxFd+bFlpAScDf4JymQ1DZcBzQwdEbjGgnlgPSiTLTSc2ZGArUgIMhTcBPaCkKxEOasVR7tt8OtO+TD4AwjJbpRfazdOqT/kB4JfhiKj2wouKnXuQgPxNEDPAWV35p20De1oaAhHgt+BkNyB8qCOBowgI7Zy0zgUkgdR1hlwqyNCdDDQM+9Fg9fcaobuW5CH7o7rfYJZfSnXYW1lAcFw8HBGaC87qLyvLfIuOJPTx8Aum2hW1uxWe8YqpIbzkgCpRu0JBeN9REFup4G/BvJemJQiRBqIvPwNxduTCDMn/A8AWoVqGb4MrAOaAX4FbgHnAu1TkgX/SaAPWNFMd0EUKQ4i8reYFkdnRBEZY3w11lwDfIKoCqKZ4Ktgf0MRVcT3g8BP8+I9shYRhpqnQ3uGS2oRBIzgmw50N8XKJhwmBihrqfCdHwj487rOXwg431DLOWAE16fAqwHOv6DTGHQDuBH8CPwdeNEddF6AupYK37s9IfXZLZ0xOAxoRrDyBJXYc4RGHPxmAq30rDxC5T2hRNBrGjwf+FWjOmNSyKdKh5/GpF5gRfXyKZVGPcNWtFaYUhUs1I7fKLDdkOl5XRCy9TrstJT/nvFV8TnwZm9bp47f+0XgZE7QF6NDwE5nvDhoXEMJj2YAK/NruDWZ4KwdpZUrmgwiKpA8aIkobwZDChQoL3eGeq6Tpkr8RgM7LjxUCFhDAYfujGdALnpsy2/pFpz4Tc1JzPWjBRcabUDZ3l4wqqnA91IRGEl6vBQODq05rLQzcGq5bWVp00+iZYptzcqnNBlFVPD/qeH7U4RrwRQe3RV2AL+lYFRTAc+FJi8VNQaOlXs+G3zcca3j6Otpp4upjjfGj5hydJE89uD0uHG03EZdq7gMq+eNpcaIxuORd8S5plHFe1w9tnq4cdhmyqlFy2G5o/iyTv2Jc2psHrWKHEmDfwzUc+3ILuOcvEw2HHbvYbmNSe2i/23n0AdDdEecBewW9QXqz9SmDRv+2aiPMeXU4jjjaLmNunZxJZZ63HI5hMIUdcQ7c012fYxbqN/pYqu9xmEWPZ4/gkZdr4ivEp1mrC23Udcr8tv0SuEpZ32yEjzRKde7ekr1AeP0NsofMvXY4mdxsI+G5Y7lyu39bzxJ87TfaX46t0696g4AW0Eu2kVGn2viMwbYw9knUnOyfnAuAFbu1x0x2hpR3uLq0VVuP73dvtk46kTrHiIXl7TGyBax1aGrtswazHNZlBfavG51/qN1R7xgu4byac4oqQqP7ooVjns5dd/xBX5sxoE1zvfHBcNEBbw6tLGyVh2hkycrJyTyF9wgPQo8b8kp/xtcDY73Dugmg++Al4EVbZDe6u1T63DNtOSUN6kj7OZI7celBgj5iQ9sEHFA9Jp/FVgLfIfl5hrDxoS4U3XwzcjJs2uvOsKeGUh/amqAMj84J4AXRR4pOo0eVcabqodztsvjaQ2WfY5Qr+c7IgTT6dA1kK0BKbxH4PdHOOaBoR1J6nUSn0ufyHcCu/pLXsvbRElcnwrdC06y+qysBdsmoD2EoNlEcXX+MR5Y0TpEs8XFcF7CjLTZNiaW1cFWdurRuA9YucpapJQh+wh4xZJm5RVcPwcaW98QN23jwFzgZw1UjcdrRsgvRgfPbSIzskgdcZ1RqKh/MVnw/wzQhxtWNBjOiiHFXge56lAtxqxoRol7UeMC47/SElK+TB3xSad8zvnVrsIzA+x2fDp3TH6+8T0I+Lv2JXR+j1QrT/yGAflbOVsdcYzVZGU7ZtQNcAS+fgr8Yi3nCiN4dXdcn+WWX3SnvKXCtdCMz/ScILvqEdY3oI1zQR2VW7m0wFChwFlLaCsLK1yimyFfYgNQ/lYsCT7fdBwrBjho8IPHwwONNQr4T3PkS6n31HCNMoFzf6DxJhf9m8fGkGD/bO6cXf8/OaDQmYEVDXa1j/Kxtc+wxogTYpKLsYX7bGDlu3X9cXq3dczKJw/4o9AGSas4K1oIVQoOY4Bdpi+udGrTgHj2ZY1WxrWOA7G7E1hZVUiF1m9bC8o7gF+BhfzmOL+O7F4LgYyCeH73eJ5pDhbxORrYP0xpf6lgXGL49YKhU+B3F8hli2vuSpVgWrr/Jw/K9bqqQNgsNvYqlv/RNN7hjLVR8svRppi0ay+Qy11NjV2sEHB5HpTrslahaJ8E/Prm6lIfjLVl9g7+PUCTP/Z293ptU2MXK8S1z/tTZaGw0xrkUWDlX1QOtT7afQ4IGxqtKv0ofDFOnxgwMgX08h9hVNpADZbYWK226nqD7r/HuIrfqs1mufDjtKTdCqy8TOX0kBd6zTg5Or52CMWUjpj6p/O4TX9o7kO7zh1C+x7tdqslI+DSJDpNOrLae9+wINeJwB8GadaYGpUhDouAl9UoOvNJb1Q2ccbkOAr0+uSpz4tjwhon7dL0/ZQXLWai31FEJ5DoQG4jweM+aeoPJFI2OmMsBH5Dphj62OvoZOIuOZKTHofQnaA7uXJx2DItCDSlhj77+wf66S2dB7GRXLTa7ANeNqKofJdSK1WIJoPQB6Pa/c2pRdIlI+Jr9lgA9gIv6oTos5WWqUI4HmzykbK6vqM+riVBFxqJeSoIjQdKaxXozJ3gcxcxWAFCorXGjaCjL2N8DqoTYxy4FewBIfkZyujTq1CsUh0BhoBrgV+ooGqIOuQH4JRSksQGOM8ES4DfBqBqiB7VLyfSp7kR8BywHrSSdTReCU4H9VZzJh18hoOzgDp+M2glT9KY/JaurSUxgYeR9+XgG6BqbfEiNqvBs2AD0F5Bb9n0BYvy0PQm6GRMH6/oxZDONl4/WKVQIjvQXwluzz5HKDEbBDUdorFjIdCb7sESTd/zgT4t2reEpEYAvadcC7ol2lJ/HnR0qd/Wo9HqbyDRibRfBGaCM8BwkCK7cHoM/Brcx+3fm0JS5dO1jrCB6RQ959r1vQPo+Z8ADgMaEzTN9QONF8J2sBFoHNFHX2v48a9y7ar8D/hhCYeAVaCRAAAAAElFTkSuQmCC'\r\n");
      out.write("\t\t        });\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tdocument.addEventListener('back', function (e) {\r\n");
      out.write("\t\t\t     e.preventDefault();\r\n");
      out.write("\t\t\t\t goBack();\r\n");
      out.write("\t\t\t}, false);\r\n");
      out.write("\t\t\tfunction goBack() {\r\n");
      out.write("\t\t\t\tAlipayJSBridge.call('exitApp');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t \tfunction toast(){\r\n");
      out.write("\t\t\tAlipayJSBridge.call('toast', {\r\n");
      out.write("\t\t\t    content: '即将上线，敬请期待',\r\n");
      out.write("\t\t\t    type: '',\r\n");
      out.write("\t\t\t    duration: 1200\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t \t//获取经纬度\r\n");
      out.write("\t \t(function(){\r\n");
      out.write("\t \t\tsetTimeout(function() {\r\n");
      out.write("\t \t\t\tAlipayJSBridge.call('getLocation', function (result) {\r\n");
      out.write("\t \t\t\t\tvar locaname = result.city ? result.city : result.province;\r\n");
      out.write("\t \t\t\t\tlocaname = locaname.replace(\"市\", \"\");\r\n");
      out.write("\t \t\t\t\tvar locacode = result.adcode;\r\n");
      out.write("\t\t\t\t\tvar oldcityName = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cityMap.resident_city_name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\t\t\tvar oldcitycode = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cityMap.resident_city_code}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\t\t\tif(result.error) {\t//定位失败\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t} else {\t//定位成功\r\n");
      out.write("\t\t\t\t\t\tvar lastTimeGPS = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lastTimeGPS}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write(" \t\t\t\t\t\tif(locacode.substring(0,3) == '110' || locacode.substring(0,3) == '120' || locacode.substring(0,3) == '310' || locacode.substring(0,3) == '500'){\r\n");
      out.write(" \t\t\t\t\t\t\tlocacode = locacode.substring(0,3) + '100';\r\n");
      out.write("\t \t\t\t\t\t} else {\r\n");
      out.write("\t \t\t\t\t\t\tlocacode = locacode.substring(0,4) + '00';\r\n");
      out.write("\t\t \t\t\t\t}\r\n");
      out.write("\t\t \t\t\t\t//保存坐标\r\n");
      out.write(" \t\t\t\t\t\tmodifyLocation(result.latitude, result.longitude, locacode, locaname);\r\n");
      out.write("\t\t\t\t\t\t//判断常驻城市\r\n");
      out.write("\t\t\t\t\t\tif(oldcitycode != '' && oldcitycode != 'undefined'){//有常驻城市\r\n");
      out.write("\t\t\t\t\t\t\tif(locacode != lastTimeGPS &&locacode != oldcitycode) {//当前城市与常驻城市不同\r\n");
      out.write("\t\t\t\t\t\t\t\tAlipayJSBridge.call('confirm', {\r\n");
      out.write("\t\t\t \t\t\t\t\t     title: '提醒',\r\n");
      out.write("\t\t\t \t\t\t\t\t     message: '系统定位到您在'+locaname+'，是否切换？',\r\n");
      out.write("\t\t\t \t\t\t\t\t     okButton: '立即切换',\r\n");
      out.write("\t\t\t \t\t\t\t\t     cancelButton: '暂不切换'\r\n");
      out.write("\t\t\t \t\t\t\t\t}, function (result) {\r\n");
      out.write("\t\t\t \t\t\t\t\t     if(result.ok){\r\n");
      out.write("\t\t\t \t\t\t\t\t    \t$(\"#location\").text(locaname.replace(\"市\", \"\"));\r\n");
      out.write("\t\t\t\t \t\t\t\t\t\t//重构类目\r\n");
      out.write("\t\t\t\t \t\t\t\t\t\treBuildCategoryApp(locacode, '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viNumber}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\r\n");
      out.write("\t\t\t\t \t\t\t\t\t\t//设置默认城市\r\n");
      out.write("\t\t\t \t\t\t\t\t    \tmodifyResidentcity(locacode, locaname);\r\n");
      out.write("\t\t\t \t\t\t\t\t    \t//查询限行\r\n");
      out.write("\t\t\t \t\t\t\t\t    \tgetLimitedContent(locacode, locaname);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t \t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t} else { //未设置常驻城市\r\n");
      out.write(" \t\t\t\t\t    \t$(\"#location\").text(locaname.replace(\"市\", \"\"));\r\n");
      out.write("\t \t\t\t\t\t\t//重构类目\r\n");
      out.write("\t \t\t\t\t\t\treBuildCategoryApp(locacode, '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viNumber}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\r\n");
      out.write("\t \t\t\t\t\t\t//设置常驻城市\r\n");
      out.write(" \t\t\t\t\t    \tmodifyResidentcity(locacode, locaname);\r\n");
      out.write(" \t\t\t\t\t    \t//查询限行\r\n");
      out.write(" \t\t\t\t\t    \tgetLimitedContent(locacode, locaname);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t \t\t/**\r\n");
      out.write("\t \t\t\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${locationMap.latitude}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("' == '' || '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${locationMap.latitude}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("' == \"undefined\"){\r\n");
      out.write("\t\t \t\t\t//定位\r\n");
      out.write("\t\t\t\t\tAlipayJSBridge.call('getLocation', function(result) {\r\n");
      out.write("\t\t\t\t\t\tvar cityName = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cityMap.resident_city_name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\t\t\t\tvar oldcitycode = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cityMap.resident_city_code}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\t \t\t\tif (cityName == '') {\t//未设置常驻城市\r\n");
      out.write("\t\t\t \t\t\t\tvar citycode = '330100';\r\n");
      out.write("\t\t \t\t\t\t\tvar cityname = '杭州';\r\n");
      out.write("\t\t \t\t\t\t\tif (typeof(result.error) != \"undefined\") { //定位失败\r\n");
      out.write("\t\t\t \t\t\t\t\t//alert('定位失败');\r\n");
      out.write("\t\t \t\t\t\t\t\tgetLimitedContent(citycode, cityname);\r\n");
      out.write("\t\t \t\t\t\t\t\t$(\"#location\").text(cityname);\r\n");
      out.write("\t\t \t\t\t\t\t\treturn;\r\n");
      out.write("\t\t \t\t\t\t\t}\r\n");
      out.write("\t\t \t\t\t\t\tvar loca = result.city ? result.city : result.province;\r\n");
      out.write("\t\t \t\t\t\t\tif (loca != '') { //定位成功\r\n");
      out.write("\t\t \t\t\t\t\t\t$(\"#location\").text(loca.replace(\"市\", \"\"));\r\n");
      out.write("\t\t \t\t\t\t\t\tcitycode = result.adcode;\r\n");
      out.write("\t\t \t\t\t\t\t\tcityname = loca.replace(\"市\", \"\");\r\n");
      out.write("\t\t \t\t\t\t\t} else {\r\n");
      out.write("\t\t \t\t\t\t\t\t$(\"#location\").text(cityname);\r\n");
      out.write("\t\t \t\t\t\t\t}\r\n");
      out.write("\t\t \t\t\t\t\tgetLimitedContent(citycode, cityname);\r\n");
      out.write("\t\t\t \t\t\t}\r\n");
      out.write("\t\t\t \t\t\t\r\n");
      out.write("\t\t\t \t\t\tvar city = result.city?result.city:result.province;\r\n");
      out.write("\t\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\t\t\turl : \"../owner/modifyLocation\",\r\n");
      out.write("\t\t\t\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\t\t\t\tdata : \"r=\" + Math.random() + '&latitude='\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ result.latitude + '&longitude='\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ result.longitude + '&citycode='\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ result.adcode + '&city='\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ city,\r\n");
      out.write("\t\t\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\t\t\tif (data != '' && data.result == '0') {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//alert('保存坐标');\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\tvar citycodeN = result.adcode;\r\n");
      out.write(" \t\t\t\t\t\tvar citycd = citycodeN.substring(0,3);\r\n");
      out.write(" \t\t\t\t\t\tif(citycd == '110' || citycd == '120' || citycd == '310' || citycd == '500'){\r\n");
      out.write(" \t\t\t\t\t\t\tcitycodeN = citycodeN.substring(0,3) + '100';\r\n");
      out.write("\t \t\t\t\t\t} else {\r\n");
      out.write("\t \t\t\t\t\t\tcitycodeN = citycodeN.substring(0,4) + '00';\r\n");
      out.write("\t\t \t\t\t\t}\r\n");
      out.write("\t\t \t\t\t\tif(oldcitycode != '' && citycodeN != oldcitycode) { //定位城市与常驻城市不一致\r\n");
      out.write("\t\t \t\t\t\t\tAlipayJSBridge.call('confirm', {\r\n");
      out.write("\t\t \t\t\t\t\t     title: '亲',\r\n");
      out.write("\t\t \t\t\t\t\t     message: '是否需要把当前城市设置为常驻城市',\r\n");
      out.write("\t\t \t\t\t\t\t     okButton: '确定',\r\n");
      out.write("\t\t \t\t\t\t\t     cancelButton: '算了'\r\n");
      out.write("\t\t \t\t\t\t\t}, function (result) {\r\n");
      out.write("\t\t \t\t\t\t\t     if(result.ok){\r\n");
      out.write("\t\t \t\t\t\t\t    \t$(\"#location\").text(city.replace(\"市\", \"\"));\r\n");
      out.write("\t\t\t \t\t\t\t\t\t//重构类目\r\n");
      out.write("\t\t\t \t\t\t\t\t\treBuildCategoryApp(citycodeN,'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viNumber}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\r\n");
      out.write("\t\t\t \t\t\t\t\t\t//设置默认城市\r\n");
      out.write("\t\t \t\t\t\t\t    \tmodifyResidentcity(citycodeN,city);\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t \t\t\t\t\t});\r\n");
      out.write("\t\t\t \t\t\t} else if(oldcitycode == ''){\r\n");
      out.write("\t\t\t \t\t\t\tmodifyResidentcity(citycodeN,city);\r\n");
      out.write("\t\t\t\t \t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t \t\t}\r\n");
      out.write("\t\t \t\t*/\r\n");
      out.write("\t \t\t}, 500);\r\n");
      out.write("\t \t})();\r\n");
      out.write("\t \t//设置常驻城市\r\n");
      out.write("\t \tfunction modifyResidentcity(citycode, cityname) {\r\n");
      out.write("\t \t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"../owner/modifyResidentcity\",\r\n");
      out.write("\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\tdata : \"r=\" + Math.random() + '&cityName='\r\n");
      out.write("\t\t\t\t\t\t+ cityname + '&cityCode='\r\n");
      out.write("\t\t\t\t\t\t+ citycode,\r\n");
      out.write("\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//保存坐标\r\n");
      out.write("\t\tfunction modifyLocation(latitude, longitude, adcode, city) {\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"../owner/modifyLocation\",\r\n");
      out.write("\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\tdata : \"r=\" + Math.random() + '&latitude='\r\n");
      out.write("\t\t\t\t\t\t+ latitude + '&longitude='\r\n");
      out.write("\t\t\t\t\t\t+ longitude + '&citycode='\r\n");
      out.write("\t\t\t\t\t\t+ adcode + '&city='\r\n");
      out.write("\t\t\t\t\t\t+ city,\r\n");
      out.write("\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\tif (data != '' && data.result == '0') {\r\n");
      out.write("\t\t\t\t\t\t//alert('保存坐标');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t \tvar flush = true;\r\n");
      out.write("\t\t// 选择城市\r\n");
      out.write("\t\tfunction locate() {\r\n");
      out.write("\t\t\tflush = false;\r\n");
      out.write("\t\t\tAlipayJSBridge.call('getCities', {\r\n");
      out.write("\t\t\t\t//currentCity : '北京',\r\n");
      out.write("\t\t\t\t//adcode : '110100'\r\n");
      out.write("\t\t\t}, function(result) {\r\n");
      out.write("\t\t\t\t// 隐藏标题栏\r\n");
      out.write("\t\t\t\tAlipayJSBridge.call(\"hideTitlebar\");\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif (result.city != '') {\r\n");
      out.write("\t\t\t\t\t$(\"#location\").text(result.city);\r\n");
      out.write("\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\t\turl : \"../owner/modifyResidentcity\",\r\n");
      out.write("\t\t\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\t\t\tdata : \"r=\" + Math.random() + '&cityName='\r\n");
      out.write("\t\t\t\t\t\t\t\t+ result.city + '&cityCode='\r\n");
      out.write("\t\t\t\t\t\t\t\t+ result.adcode,\r\n");
      out.write("\t\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\t\tflush = true;\r\n");
      out.write("\t\t\t\t\t\t\tlocation.reload(true);\r\n");
      out.write("\t\t\t\t\t\t\tif (data != '' && data.result == '0') {\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\terror: function(XMLHttpRequest, textStatus, errorThrown){\r\n");
      out.write("\t\t\t\t\t\t\tflush = true;\r\n");
      out.write("\t\t\t\t\t\t\tlocation.reload(true);\r\n");
      out.write("\t\t\t\t\t    }\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tdocument.addEventListener('resume', function (event) {\r\n");
      out.write("\t\t\tif(flush){\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\t\turl : \"../car/getDefaultCarInfo\",\r\n");
      out.write("\t\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\tif (data.success) { \r\n");
      out.write("\t\t\t\t\t\t\tvar myCar = data.defaultCar;\r\n");
      out.write("\t\t\t\t\t\t\tif(myCar != null && myCar != ''){\r\n");
      out.write("\t\t\t\t\t\t\t\tif(myCar.viBrandName == '') {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tdocument.getElementById(\"top-img\").src = \"../images/index-logo.png\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#top-img\").css({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\"height\": \"69px\",\r\n");
      out.write("\t\t\t\t\t\t\t\t    \t\"margin\": \"2px\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#top-img\").css({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\"height\": \"45px\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\"border-radius\": \"100%\",\r\n");
      out.write("\t\t\t\t\t\t\t\t    \t\"margin\": \"19% 0\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif(myCar.viLogoUrl != ''){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.getElementById(\"top-img\").src = myCar.viLogoUrl;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.getElementById(\"top-img\").src = myCar.viBrandLogo;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tif(myCar.viBrandName == '') {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tdocument.getElementById(\"car-title\").innerHTML = myCar.viNumber.substr(0, 2)+\"**\"+myCar.viNumber.substr(4, 7);\r\n");
      out.write("\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tdocument.getElementById(\"car-title\").innerHTML = myCar.viBrandName+myCar.viSeriesName;\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\tif(myCar.bgUrl != ''){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar ImgObj = new Image(); //判断图片是否存在  \r\n");
      out.write("\t\t\t\t\t\t\t\t\tImgObj.src = myCar.bgUrl;  \r\n");
      out.write("\t\t\t\t\t\t\t\t\tImgObj.onload = function (){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(\".transparent_bg\").css(\"background-image\",\"url('\"+myCar.bgUrl+\"')\");  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t};\r\n");
      out.write("\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar ImgObj = new Image(); //判断图片是否存在  \r\n");
      out.write("\t\t\t\t\t\t\t\t\tImgObj.src = '../images/defualt_backgd_img.jpg';  \r\n");
      out.write("\t\t\t\t\t\t\t\t\tImgObj.onload = function (){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t$(\".transparent_bg\").css(\"background-image\",\"url('\"+'../images/defualt_backgd_img.jpg'+\"')\");  \r\n");
      out.write("\t\t\t\t\t\t\t\t\t};\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-img-a').off('click');\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-img-a').on('click',function () {\r\n");
      out.write("// \t\t\t\t\t\t\t\t\talert(viId);\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar viId3 = (myCar.viId);\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif(!viId3){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//首页添加车辆信息埋点\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t/* remoteLog.infoLog('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\turl: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tparam: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t            }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\turl: \"../car/toCarInfo?viId=\"+viId3+\"&backUrl=portal\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tparam: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t            }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-span-a').off('click');\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-span-a').on('click',function () {\r\n");
      out.write("// \t\t\t\t\t\t\t\t\talert(viId);\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar viId3 = (myCar.viId);\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif(!viId3){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//首页添加车辆信息埋点\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t/* remoteLog.infoLog('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\turl: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tparam: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t            }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\turl: \"../car/toCarInfo?viId=\"+viId3+\"&backUrl=portal\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tparam: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t            }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\tvar ImgObj = new Image(); //判断图片是否存在  \r\n");
      out.write("\t\t\t\t\t\t\t\tImgObj.src = '../images/defualt_backgd_img.jpg';  \r\n");
      out.write("\t\t\t\t\t\t\t\tImgObj.onload = function (){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\".transparent_bg\").css(\"background-image\",\"url('\"+'../images/defualt_backgd_img.jpg'+\"')\");  \r\n");
      out.write("\t\t\t\t\t\t\t\t};\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$(\"#top-img\").css({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\"height\": \"45px\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\"border-radius\": \"100%\",\r\n");
      out.write("\t\t\t\t\t\t\t    \t\"margin\": \"19% 0\"\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t//清空\r\n");
      out.write("\t\t\t\t\t\t\t\tdocument.getElementById(\"top-img\").src = \"../images/iconfont-jiahao.png\";\r\n");
      out.write("\t\t\t\t\t\t\t\tdocument.getElementById(\"car-title\").innerHTML = \"添加爱车\";\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-img-a').off('click');\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-img-a').on('click',function () {\r\n");
      out.write("// \t\t\t\t\t\t\t\t\talert(viId2);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//首页添加车辆信息埋点\r\n");
      out.write("\t\t\t\t\t\t\t\t\t/* remoteLog.infoLog('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
      out.write("\t\t\t\t\t\t\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
      out.write("\t\t\t\t\t\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\turl: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tparam: {\r\n");
      out.write("\t\t\t\t\t\t\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t\t\t\t\t\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t\t\t\t\t\t\t            }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-span-a').off('click');\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#top-span-a').on('click',function () {\r\n");
      out.write("// \t\t\t\t\t\t\t\t\talert(viId2);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//首页添加车辆信息埋点\r\n");
      out.write("\t\t\t\t\t\t\t\t\t/* remoteLog.infoLog('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
      out.write("\t\t\t\t\t\t\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
      out.write("\t\t\t\t\t\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\turl: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tparam: {\r\n");
      out.write("\t\t\t\t\t\t\t\t              readTitle: \"YES\",\r\n");
      out.write("\t\t\t\t\t\t\t\t              showToolBar: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t              showProgress: \"NO\",\r\n");
      out.write("\t\t\t\t\t\t\t\t              transparentTitle: \"none\"\r\n");
      out.write("\t\t\t\t\t\t\t\t            }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t//动态修改 获取车辆ID 拼接到车险\r\n");
      out.write("\t\t\t\t\t\t\tvar vi_id = '';\r\n");
      out.write("\t\t\t\t\t\t\tif(myCar != null && myCar != ''){\r\n");
      out.write("\t\t\t\t\t\t\t\tvi_id = myCar.viNumber;\t\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\tvar $appItem = $(\".category_app_item\");\r\n");
      out.write("\t\t\t\t\t\t\t$appItem.each(function(){\r\n");
      out.write("\t\t\t\t\t\t\t\tvar category_id = $(this).attr('app_id');\r\n");
      out.write("\t\t\t\t\t\t\t\tvar category_name = $(this).attr('app_name');\r\n");
      out.write("\t\t\t\t\t\t\t\tvar category_urls = $(this).attr('app_url');\r\n");
      out.write("\t\t\t\t\t\t\t\tif(category_id == 'CARLIFE013'){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif(category_urls.indexOf('carNo') == -1){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tcategory_urls = category_urls+encodeURIComponent('&carNo='+vi_id);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvar decUrl = category_urls;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tcategory_urls = decUrl.substring(0,decUrl.indexOf('carNo'))+encodeURIComponent('carNo='+vi_id);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(this).attr('href',\"javascript:categoryApp(\"+\"'\"+category_urls+\"'\"+\",\"+\"'\"+category_name+\"'\"+\",\"+\"'\"+category_id+\"'\"+\")\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//alert($(this).attr('href'));\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t//更新最新消息\r\n");
      out.write("\t\t\t\t\t\t\tvar messageList = data.messageList;\r\n");
      out.write("\t\t\t\t\t\t\tif(messageList != null && messageList != ''){\r\n");
      out.write("\t\t\t\t\t\t\t\tvar $limitedContent = $(\"#limitedContent\");\r\n");
      out.write("\t\t\t\t\t\t\t\t$limitedContent.empty();\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tvar contentHtml = \r\n");
      out.write("\t\t\t\t\t\t\t\t'<img src=\"../images/laba.png\" class=\"index-msg-top-bottom-img\">'+\r\n");
      out.write("\t\t\t\t\t\t\t\t'<div class=\"index-msg-top-bottom-box\" onclick=\"gotoMsg();\">';\t\r\n");
      out.write("\t\t\t\t\t\t\t\tif(messageList.content.length >31){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\tcontentHtml += messageList.title+':'+messageList.content.substr(0, 30)+'...';\r\n");
      out.write("\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tcontentHtml += messageList.title+':'+messageList.content;\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\tcontentHtml += '</div><img src=\"../images/right.png\" class=\"index-msg-top-bottom-img\">';\r\n");
      out.write("\t\t\t\t\t\t\t\t$limitedContent.html(contentHtml);\r\n");
      out.write("\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\tif(data.limitedContent.length >31){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#limitedContent\").text(data.limitedContent.substr(0, 30)+'...');\r\n");
      out.write("\t\t\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#limitedContent\").text(data.limitedContent);\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t      }\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 跳转\r\n");
      out.write("\t\tfunction jumphref(href){\r\n");
      out.write("\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
      out.write("\t            url: href,\r\n");
      out.write("\t          \tparam: {\r\n");
      out.write("\t              \treadTitle: \"YES\",\r\n");
      out.write("\t              \tshowToolBar: \"NO\",\r\n");
      out.write("\t              \tshowProgress: \"NO\",\r\n");
      out.write("\t              \ttransparentTitle: \"none\"\r\n");
      out.write("\t            }\r\n");
      out.write("\t        });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t");
      if (_jspx_meth_c_005fif_005f8(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_c_005fif_005f9(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /page/portal.jsp(52,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty cityMap.resident_city_name}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t选择城市\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /page/portal.jsp(54,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty cityMap.resident_city_name}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cityMap.resident_city_name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /page/portal.jsp(61,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty message}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t<img src=\"../images/laba.png\" class=\"index-msg-top-bottom-img\">\r\n");
        out.write("\t\t\t\t\t<div class=\"index-msg-top-bottom-box\" onclick=\"gotoMsg();\">\r\n");
        out.write("\t\t\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message.title}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(":\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_c_005fchoose_005f0(_jspx_th_c_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t<img src=\"../images/right.png\" class=\"index-msg-top-bottom-img\">\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fotherwise_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /page/portal.jsp(66,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:length(message.content) > 31}", java.lang.Boolean.class, (PageContext)_jspx_page_context, _jspx_fnmap_0, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fwhen_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f0);
    // /page/portal.jsp(67,8) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:substring(message.content, 0, 30)}...", java.lang.Object.class, (PageContext)_jspx_page_context, _jspx_fnmap_1, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /page/portal.jsp(70,8) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message.content}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent(null);
    // /page/portal.jsp(76,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty message}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_c_005fchoose_005f1(_jspx_th_c_005fif_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f1 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f3);
    int _jspx_eval_c_005fchoose_005f1 = _jspx_th_c_005fchoose_005f1.doStartTag();
    if (_jspx_eval_c_005fchoose_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f1(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_c_005fotherwise_005f1(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f1 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    // /page/portal.jsp(78,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:length(limitedContent) > 31}", java.lang.Boolean.class, (PageContext)_jspx_page_context, _jspx_fnmap_0, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f1 = _jspx_th_c_005fwhen_005f1.doStartTag();
    if (_jspx_eval_c_005fwhen_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_c_005fout_005f2(_jspx_th_c_005fwhen_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f2 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f1);
    // /page/portal.jsp(79,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:substring(limitedContent, 0, 30)}...", java.lang.Object.class, (PageContext)_jspx_page_context, _jspx_fnmap_1, false));
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f1 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    int _jspx_eval_c_005fotherwise_005f1 = _jspx_th_c_005fotherwise_005f1.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_c_005fout_005f3(_jspx_th_c_005fotherwise_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f1);
    // /page/portal.jsp(82,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${limitedContent}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /page/portal.jsp(90,12) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("status");
    // /page/portal.jsp(90,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("item");
    // /page/portal.jsp(90,12) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${categoryAppList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\t\r\n");
          out.write("            \t");
          if (_jspx_meth_c_005fchoose_005f2(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t              ");
          if (_jspx_meth_c_005fif_005f4(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t              ");
          if (_jspx_meth_c_005fif_005f5(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t                <img src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_logo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" class=\"carui-img-height\">\r\n");
          out.write("\t                <div class=\"mui-media-body carui-active-title\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</div>\r\n");
          out.write("\t                <div class=\"mui-media-body carui-active-desc\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_memo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</div>\r\n");
          out.write("\t              </a>\r\n");
          out.write("\t            </li>\r\n");
          out.write("\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f2 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    int _jspx_eval_c_005fchoose_005f2 = _jspx_th_c_005fchoose_005f2.doStartTag();
    if (_jspx_eval_c_005fchoose_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t   ");
        if (_jspx_meth_c_005fwhen_005f2(_jspx_th_c_005fchoose_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t   ");
        if (_jspx_meth_c_005fotherwise_005f2(_jspx_th_c_005fchoose_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f2 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
    // /page/portal.jsp(92,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${((status.index+1) % 3) == '0'}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f2 = _jspx_th_c_005fwhen_005f2.doStartTag();
    if (_jspx_eval_c_005fwhen_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right\">\r\n");
        out.write("\t\t\t\t   ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f2 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
    int _jspx_eval_c_005fotherwise_005f2 = _jspx_th_c_005fotherwise_005f2.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("\t\t\t\t   \t\t<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3\">\r\n");
        out.write("\t\t\t\t   ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /page/portal.jsp(99,15) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty item.app_url}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t              <a class=\"category_app_item\" app_name='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("' app_url='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_url}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("' app_id='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("' href=\"javascript:categoryApp('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_url}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write('\'');
        out.write(',');
        out.write('\'');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write('\'');
        out.write(',');
        out.write('\'');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.app_id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\">\r\n");
        out.write("\t              ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /page/portal.jsp(102,15) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty item.app_url}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
    if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t              <a href=\"#\" onclick=\"toast()\">\r\n");
        out.write("\t              ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f6 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f6.setParent(null);
    // /page/portal.jsp(111,3) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:length(categoryAppList)%3>0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, _jspx_fnmap_0, false)).booleanValue());
    int _jspx_eval_c_005fif_005f6 = _jspx_th_c_005fif_005f6.doStartTag();
    if (_jspx_eval_c_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fif_005f6, _jspx_page_context))
          return true;
        out.write("\t\t\t\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f6);
    // /page/portal.jsp(112,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("i");
    // /page/portal.jsp(112,4) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setBegin(1);
    // /page/portal.jsp(112,4) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${3-(fn:length(categoryAppList)%3)}", java.lang.Integer.class, (PageContext)_jspx_page_context, _jspx_fnmap_0, false)).intValue());
    // /page/portal.jsp(112,4) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setStep(1);
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t");
          if (_jspx_meth_c_005fchoose_005f3(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t<a>\r\n");
          out.write("                <div class=\"carui-img-null-height\"></div>\r\n");
          out.write("                <div class=\"mui-media-body carui-active-title\"></div>\r\n");
          out.write("                <div class=\"mui-media-body carui-active-desc\"></div>\r\n");
          out.write("        \t\t</a>        \r\n");
          out.write("\t            </li> \r\n");
          out.write("\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f3 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    int _jspx_eval_c_005fchoose_005f3 = _jspx_th_c_005fchoose_005f3.doStartTag();
    if (_jspx_eval_c_005fchoose_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t   ");
        if (_jspx_meth_c_005fwhen_005f3(_jspx_th_c_005fchoose_005f3, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t   ");
        if (_jspx_meth_c_005fotherwise_005f3(_jspx_th_c_005fchoose_005f3, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f3, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f3 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f3);
    // /page/portal.jsp(114,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(3-(fn:length(categoryAppList)%3)) == i}", java.lang.Boolean.class, (PageContext)_jspx_page_context, _jspx_fnmap_0, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f3 = _jspx_th_c_005fwhen_005f3.doStartTag();
    if (_jspx_eval_c_005fwhen_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("    \r\n");
        out.write("\t\t\t\t\t\t<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right\">\r\n");
        out.write("\t\t\t\t   ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f3, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f3 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f3);
    int _jspx_eval_c_005fotherwise_005f3 = _jspx_th_c_005fotherwise_005f3.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("\t\t\t\t   \t\t<li class=\"mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3\">\r\n");
        out.write("\t\t\t\t   ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f7 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f7.setParent(null);
    // /page/portal.jsp(240,3) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f7.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty message}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f7 = _jspx_th_c_005fif_005f7.doStartTag();
    if (_jspx_eval_c_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t$.ajax({\r\n");
        out.write("\t\t\t\ttype : \"POST\",\r\n");
        out.write("\t\t\t\turl : \"../owner/queryLimited\",\r\n");
        out.write("\t\t\t\tdataType : \"json\",\r\n");
        out.write("\t\t\t\tcache : false,\r\n");
        out.write("\t\t\t\tdata : \"r=\" + Math.random() + '&cityCode=' + cityCode + '&cityName=' + cityName,\r\n");
        out.write("\t\t\t\tsuccess : function(data) {\r\n");
        out.write("\t\t\t\t\tvar content = data.content;\r\n");
        out.write("\t\t\t\t\tif (content != '' && content != null) {\r\n");
        out.write("\t\t\t\t\t\tif(content.length>31){\r\n");
        out.write("\t\t\t\t\t\t\t$(\"#limitedContent\").text(content.substr(0,30)+'...');\r\n");
        out.write("\t\t\t\t\t\t} else{\r\n");
        out.write("\t\t\t\t\t\t\t$(\"#limitedContent\").text(content);\r\n");
        out.write("\t\t\t\t\t\t}\r\n");
        out.write("\t\t\t\t\t} else {\r\n");
        out.write("\t\t\t\t\t\t$(\"#limitedContent\").text('');\r\n");
        out.write("\t\t\t\t\t}\r\n");
        out.write("\t\t\t\t}\r\n");
        out.write("\t\t\t});\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f8 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f8.setParent(null);
    // /page/portal.jsp(718,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f8.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty myCar}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f8 = _jspx_th_c_005fif_005f8.doStartTag();
    if (_jspx_eval_c_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t<script type=\"text/javascript\">\r\n");
        out.write("\t\tif('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viBrandName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("' == '') {\r\n");
        out.write("\t\t\tdocument.getElementById(\"top-img\").src = \"../images/index-logo.png\";\r\n");
        out.write("\t\t\t$(\"#top-img\").css({\r\n");
        out.write("\t\t\t\t\"height\": \"69px\",\r\n");
        out.write("\t\t    \t\"margin\": \"2px\"\r\n");
        out.write("\t\t\t});\r\n");
        out.write("\t\t} else {\r\n");
        out.write("\t\t\tif('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viLogoUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("' != ''){\r\n");
        out.write("\t\t\t\tdocument.getElementById(\"top-img\").src = \"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viLogoUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\";\r\n");
        out.write("\t\t\t} else {\r\n");
        out.write("\t\t\t\tdocument.getElementById(\"top-img\").src = \"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viBrandLogo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\";\r\n");
        out.write("\t\t\t}\r\n");
        out.write("\t\t}\r\n");
        out.write("\t\tif('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viBrandName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("' == '') {\r\n");
        out.write("\t\t\tdocument.getElementById(\"car-title\").innerHTML = \"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:substring(myCar.viNumber, 0, 2)}", java.lang.String.class, (PageContext)_jspx_page_context, _jspx_fnmap_1, false));
        out.write('*');
        out.write('*');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:substring(myCar.viNumber, 4, 7)}", java.lang.String.class, (PageContext)_jspx_page_context, _jspx_fnmap_1, false));
        out.write("\";\r\n");
        out.write("\t\t} else {\r\n");
        out.write("\t\t\tdocument.getElementById(\"car-title\").innerHTML = \"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viBrandName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viSeriesName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\";\r\n");
        out.write("\t\t}\r\n");
        out.write("\t\t\r\n");
        out.write("\t\tif('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.bgUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("' != ''){\r\n");
        out.write("\t\t\tvar ImgObj = new Image(); //判断图片是否存在  \r\n");
        out.write("\t\t\tImgObj.src = '");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.bgUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("';  \r\n");
        out.write("\t\t\tImgObj.onload = function (){\r\n");
        out.write("\t\t\t\t$(\".transparent_bg\").css(\"background-image\",\"url('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.bgUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("')\");  \r\n");
        out.write("\t\t\t};\r\n");
        out.write("\t\t}\r\n");
        out.write("\t\t//off\r\n");
        out.write("\t\t$('#top-img-a').off('click');\r\n");
        out.write("\t\t$('#top-img-a').on('click',function () {\r\n");
        out.write("\t\t\tvar viId1 = '");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("';\r\n");
        out.write("\t\t\tif(!viId1){\r\n");
        out.write("\t\t\t\t//首页添加车辆信息埋点\r\n");
        out.write("\t\t\t\t/* remoteLog.infoLog('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
        out.write("\t\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
        out.write("\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
        out.write("\t\t\t\t\turl: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),\r\n");
        out.write("\t\t\t\t\tparam: {\r\n");
        out.write("\t\t\t              readTitle: \"YES\",\r\n");
        out.write("\t\t\t              showToolBar: \"NO\",\r\n");
        out.write("\t\t\t              showProgress: \"NO\",\r\n");
        out.write("\t\t\t              transparentTitle: \"none\"\r\n");
        out.write("\t\t\t            }\r\n");
        out.write("\t\t\t\t});\r\n");
        out.write("\t\t\t} else {\r\n");
        out.write("\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
        out.write("\t\t\t\t\turl: \"../car/toCarInfo?viId=\"+viId1+\"&backUrl=portal\",\r\n");
        out.write("\t\t\t\t\tparam: {\r\n");
        out.write("\t\t\t              readTitle: \"YES\",\r\n");
        out.write("\t\t\t              showToolBar: \"NO\",\r\n");
        out.write("\t\t\t              showProgress: \"NO\",\r\n");
        out.write("\t\t\t              transparentTitle: \"none\"\r\n");
        out.write("\t\t\t            }\r\n");
        out.write("\t\t\t\t});\r\n");
        out.write("\t\t\t}\r\n");
        out.write("\t\t});\r\n");
        out.write("\t\t\t\r\n");
        out.write("\t\t$('#top-span-a').off('click');\r\n");
        out.write("\t\t\t\r\n");
        out.write("\t\t$('#top-span-a').on('click',function () {\r\n");
        out.write("\t\t\tvar viId1 = '");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${myCar.viId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("';\r\n");
        out.write("\t\t\tif(!viId1){\r\n");
        out.write("\t\t\t\t//首页添加车辆信息埋点\r\n");
        out.write("\t\t\t\t/* remoteLog.infoLog('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
        out.write("\t\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
        out.write("\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
        out.write("\t\t\t\t\turl: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),\r\n");
        out.write("\t\t\t\t\tparam: {\r\n");
        out.write("\t\t\t              readTitle: \"YES\",\r\n");
        out.write("\t\t\t              showToolBar: \"NO\",\r\n");
        out.write("\t\t\t              showProgress: \"NO\",\r\n");
        out.write("\t\t\t              transparentTitle: \"none\"\r\n");
        out.write("\t\t\t            }\r\n");
        out.write("\t\t\t\t});\r\n");
        out.write("\t\t\t} else {\r\n");
        out.write("\t\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
        out.write("\t\t\t\t\turl: \"../car/toCarInfo?viId=\"+viId1+\"&backUrl=portal\",\r\n");
        out.write("\t\t\t\t\tparam: {\r\n");
        out.write("\t\t\t              readTitle: \"YES\",\r\n");
        out.write("\t\t\t              showToolBar: \"NO\",\r\n");
        out.write("\t\t\t              showProgress: \"NO\",\r\n");
        out.write("\t\t\t              transparentTitle: \"none\"\r\n");
        out.write("\t\t\t            }\r\n");
        out.write("\t\t\t\t});\r\n");
        out.write("\t\t\t}\r\n");
        out.write("\t\t});\r\n");
        out.write("\t</script>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f9 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f9.setParent(null);
    // /page/portal.jsp(807,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f9.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty myCar}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f9 = _jspx_th_c_005fif_005f9.doStartTag();
    if (_jspx_eval_c_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t<script type=\"text/javascript\">\r\n");
        out.write("\t\tlocalStorage.setItem(\"tokenId\", \"first-in\");\r\n");
        out.write("\t\tdocument.getElementById(\"top-img\").src = \"../images/iconfont-jiahao.png\";\r\n");
        out.write("\t\tdocument.getElementById(\"car-title\").innerHTML = \"添加爱车\";\r\n");
        out.write("\t\t$('#top-img-a').off('click');\r\n");
        out.write("\t\t$('#top-img-a').on('click',function () {\r\n");
        out.write("\t\t\t//首页添加车辆信息埋点\r\n");
        out.write("\t\t\t/* remoteLog.infoLog('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
        out.write("\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
        out.write("\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
        out.write("\t\t\t\turl: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),\r\n");
        out.write("\t\t\t\tparam: {\r\n");
        out.write("\t\t              readTitle: \"YES\",\r\n");
        out.write("\t\t              showToolBar: \"NO\",\r\n");
        out.write("\t\t              showProgress: \"NO\",\r\n");
        out.write("\t\t              transparentTitle: \"none\"\r\n");
        out.write("\t\t            }\r\n");
        out.write("\t\t\t});\r\n");
        out.write("\t\t});\r\n");
        out.write("\t\t\r\n");
        out.write("\t\t$('#top-span-a').off('click');\r\n");
        out.write("\t\t$('#top-span-a').on('click',function () {\r\n");
        out.write("\t\t\t//首页添加车辆信息埋点\r\n");
        out.write("\t\t\t/* remoteLog.infoLog('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */\r\n");
        out.write("\t\t\tremoteLog.infoLog('mainpage','clicked');\r\n");
        out.write("\t\t\tAlipayJSBridge.call('pushWindow', {\r\n");
        out.write("\t\t\t\turl: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),\r\n");
        out.write("\t\t\t\tparam: {\r\n");
        out.write("\t\t              readTitle: \"YES\",\r\n");
        out.write("\t\t              showToolBar: \"NO\",\r\n");
        out.write("\t\t              showProgress: \"NO\",\r\n");
        out.write("\t\t              transparentTitle: \"none\"\r\n");
        out.write("\t\t            }\r\n");
        out.write("\t\t\t});\r\n");
        out.write("\t\t});\r\n");
        out.write("\t</script>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
    return false;
  }
}
