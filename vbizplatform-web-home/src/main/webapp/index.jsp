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
  	          url: "./car/portal",
  	          param: {
  	              readTitle: "YES",
  	              defaultTitle: true,
  	              showToolBar: "NO",
  	              showProgress: "NO",
  	              transparentTitle: "auto"
  	            }
  	        });
		}, false);
	}
  /**
  	(function(){
  		setTimeout(function(){
  			AlipayJSBridge.call('pushWindow', {
  	            url: "./car/portal",
  	          param: {
  	              readTitle: "YES",
  	              defaultTitle: true,
  	              showToolBar: "NO",
  	              showProgress: "NO",
  	              transparentTitle: "auto"
  	            }
  	        });
  		}, 200);
  	})();
  	*/
    //$("#confirmBtn").click(function(){
    //  AlipayJSBridge.call('pushWindow', {
    //      url: "./car/portal",
    //    param: {
    //        readTitle: "YES",
    //        defaultTitle: true,
    //        showToolBar: "NO",
    //        showProgress: "NO",
    //        transparentTitle: "auto"
    //      }
    //  });
    //});
  </script>
</body>
</html>