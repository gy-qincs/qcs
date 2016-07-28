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
//alert('aaaaaaaa');
location.replace('https://os.alipayobjects.com/others/rpcLimit.html?title=404&errtype=NOTFOUND-ERR&reloadUrl=<%=rootPath%>/car/portal&webview_options=so%3DNO');

</script>

</body>
</html>