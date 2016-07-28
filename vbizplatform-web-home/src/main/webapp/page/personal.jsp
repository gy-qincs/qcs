<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css">
</head>
<body>
	<div class="mui-content">
		<ul id="list" class="mui-table-view mui-table-view-chevron">
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right" href="../car/myCarList"> 我的车辆 </a>
			</li>
		</ul>
	</div>
	<div class="mui-content carui-personal-content-top">
		<ul id="list1" class="mui-table-view mui-table-view-chevron car-personal-clear-ul-top">
		<!-- -->
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right" href="${myOrders}"> 我的订单 </a>
			</li>
		 
		 <li class="mui-table-view-cell">
				<a class="mui-navigate-right" href="alipays://platformapi/startapp?appId=20000241&url=%2Fwww%2Findex.html%3Fpage%3Duserpolicys%26from%3Dczfw"> 我的保单</a>
		</li>
		<li class="mui-table-view-cell">
			<a class="mui-navigate-right" href="alipays://platformapi/startapp?appId=20000021&b=c&a=sh&pid=${pid}"> 我的卡券 </a>
		</li>
		<li class="mui-table-view-cell">
			<a class="mui-navigate-right" href="../msg/toMessageList"> 服务消息 </a>
		</li>
		</ul>

	</div>
	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		window.onload = function() {
			document.addEventListener('AlipayJSBridgeReady', function () {
				loadJSAPI();
			}, false);
		};
		
		function loadJSAPI() {
			// 显示标题栏
			AlipayJSBridge.call("showTitlebar");
			// 隐藏右按钮
			AlipayJSBridge.call("showOptionMenu");
			// 设置标题
			AlipayJSBridge.call("setTitle", {
			    title: '个人中心'
			});
			document.addEventListener('back', function (e) {
			     e.preventDefault();
				 goBack();
			}, false);
			function goBack() {
				//AlipayJSBridge.call('pushWindow', {
				//	  url: '../car/portal?rand=' + Math.random()
				//	});
				AlipayJSBridge.call('popTo', {
				     index: 0
				});
			}
		}
		// 设置右按钮属性
		AlipayJSBridge.call('setOptionMenu', {
		     title : '帮助'
		});
		/**
		*/
		$(document).on("optionMenu", function(){
			//location.href = "../owner/index?r=" + Math.random();
			AlipayJSBridge.call('pushWindow', {
				url: "https://csmobile.alipay.com/router.htm?scene=app_carservice",
				param: {
		              readTitle: "YES",
		              showToolBar: "NO",
		              showProgress: "NO",
		              transparentTitle: "none"
		            }
			});
		});
	</script>
</body>

</html>
