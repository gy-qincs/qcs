<%@page import="com.alipay.vbizplatform.common.util.ReadConfig"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String authPath = ReadConfig.getInstance().getConfigItem("filter.redirectAuthUrl");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>

<script type="text/javascript">
location.replace('https://os.alipayobjects.com/others/rpcLimit.html?title=%e7%b3%bb%e7%bb%9f%e7%b9%81%e5%bf%99&errtype=BUSY-ERR&url=<%=authPath%>&webview_options=so%3DNO&button=%e8%bf%94%e5%9b%9e%e9%a6%96%e9%a1%b5');
</script>

</body>
</html>