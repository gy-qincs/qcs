<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/app.css">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css?version=1.1.0.051201">
</head>
<body style="background-color: #fff;">	
	<div class="nullcar">
		<img src="../images/no-car.png"><br />
		<span>尚未添加车辆</span>
	</div>
	<div class="mui-content-padded">
		<a href="javascript:addCar();">
			<button type="button" id="addCar" class="mui-btn-block carui-no-car-btn">添加车辆</button>
		</a>
	</div>
</body>
<script src="../js/jquery/jquery-1.11.1.min.js"></script>
<script src="../js/mui.min.js"></script>
<script src="https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js"></script>
<script type="text/javascript" src="../js/remoteLogUtil.js"></script>
<script type="text/javascript">
	var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
	$(function(){
		setTimeout(loadJSAPI, 300);
		function loadJSAPI() {
			remoteLog.infoLog('carsemptylist','openPage');
			// 显示标题栏
			AlipayJSBridge.call("showTitlebar");
			// 设置标题
			AlipayJSBridge.call("setTitle", {
			    title: '查询结果'
			});
			// 隐藏右按钮
			AlipayJSBridge.call("hideOptionMenu");
			
			document.addEventListener('back', function (e) {
			     e.preventDefault();
				 goBack();
			}, false);
			
			function goBack() {
				//location.replace('../owner/index?rand=' + Math.random());
				AlipayJSBridge.call('pushWindow', {
					url: '../owner/index?rand=' + Math.random()
				});
			}
		}
	});

	function addCar(){
		
			//车辆列表添加车辆信息埋点
			/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','cars_list'); */
			remoteLog.infoLog('carsemptylist','clicked');
			//location.href="../car/queryBrand?newCarFlag=1&r=" + Math.random();
			AlipayJSBridge.call('pushWindow', {
				url: "../car/queryBrand?newCarFlag=1&fromPage=home&r=" + Math.random() + '&backUrl=portal'
			});
	}
</script>
</html>