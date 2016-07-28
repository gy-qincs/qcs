<%@page import="com.alipay.vbizplatform.common.util.ReadConfig"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String rootPath = ReadConfig.getInstance().getConfigItem("project.publish.url");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>FAIL</title>
</head>
<body>

<script type="text/javascript">
//显示右按钮
AlipayJSBridge.call("hideOptionMenu");
location.replace('https://os.alipayobjects.com/others/rpcLimit.html?title=500&errtype=BUSY-ERR&url=<%=rootPath%>&webview_options=so%3DNO&button=%e8%bf%94%e5%9b%9e%e9%a6%96%e9%a1%b5');
</script>

</body>
</html>