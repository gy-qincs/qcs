<%@ page language="java" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>

<table width="100%" height="100%">
<tr><td>
</td></tr>
</table>
<script type="text/javascript" src="./js/jquery/jquery-1.11.1.min.js" ></script>
<script>
	window.onload = function() {
		document.addEventListener('AlipayJSBridgeReady', function () {
			AlipayJSBridge.call('pushWindow', {
  	          url: "./tpitf/carInfo?mustParam=viSeriesName,viModelName,viMileage,viVin,engineNo,vlCityName&viId=1606232004400010012077400026",
  	          param: {
				readTitle: true,
				defaultTitle: true,
				showToolBar: false
  	            }
  	        });
		}, false);
	}
	document.addEventListener('resume', function (event) {
		var obj = event.data;
		alert(obj.viId);
	});

  </script>
</body>
</html>