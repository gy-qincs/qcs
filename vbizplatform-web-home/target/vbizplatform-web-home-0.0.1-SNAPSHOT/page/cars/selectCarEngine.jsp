<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>请选择发动机型号</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css" />
</head>
<body>
	<form action="" id="selectEngineForm" name="selectEngineForm">
	<div class="mui-content">
	
		<input type="hidden" name="engineType" id="engineType" >
		<input type="hidden" name="carCCId" id="carCCId" >
		<input type="hidden" name="upst" id="upst" value="${upst}">
		<input type="hidden" name="viId" id="viId" value="${viId}">
		<input type="hidden" name="sweptVolume" id="sweptVolume" value="${sweptVolume}">
		<input type="hidden" name="carYearName" id="carYearName" value="${carYearName}">
		<input type="hidden" name="carModelName" id="carModelName" value="${carModelName}">
		<input type="hidden" name="carEngineFlag" id="carEngineFlag" value="${carEngineFlag}">
		<input type="hidden" name="collapseFlag" id="collapseFlag" value="${collapseFlag}">
	</div>
	
	<ul class="mui-table-view">
		<c:forEach var="carEngine" items="${carEngineList}">
			<li class="mui-table-view-cell">
			<a class="mui-navigate-right" href="javascript:carModel('${carEngine}','');">${carEngine}</a></li>
		</c:forEach>
	</ul>
	</form>
	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script src="https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js"></script>
	<script type="text/javascript" src="../js/remoteLogUtil.js"></script>
	<script type="text/javascript">
	var submit_flag = "0";
	var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
	$(function() {
		setTimeout(loadJSAPI, 100);
		function loadJSAPI(){
			remoteLog.infoLog('selectcarengine','openPage');
			// 显示标题栏
			AlipayJSBridge.call("showTitlebar");
			// 显示右按钮
			AlipayJSBridge.call("hideOptionMenu");
			// 设置标题
			AlipayJSBridge.call("setTitle", {
			    title: '请选择发动机型号'
			});
			/**
			if($("#upst").val() == ''){
				// 设置右按钮属性
				AlipayJSBridge.call('setOptionMenu', {
				     title : '跳过'
				});
				document.addEventListener('optionMenu', function () {
					var upst = $("#upst").val();
					var path = '../car/newCarInfo?r=' + Math.random();
					if(upst == '1') {	//跳转到车辆修改页面
						path = '../car/toCarInfo?r=' + Math.random();
					}
					$("#selectYearForm").attr('action', path).submit();
				}, false);
			} else {
				// 隐藏右按钮
				AlipayJSBridge.call("hideOptionMenu");
			}
			**/
			document.addEventListener('back', function (e) {
			     e.preventDefault();
				 goBack();
			}, false);
			function goBack() {
				history.go(-1);
			}
		}
	});
		/**
		$(function() {
			$(".back-span").click(function() {
				history.go(-1);
			});
			$(".skip-span").click(function() {
				$("#selectYearForm").attr('action', '../car/newCarInfo').submit();
			});
		});**/
		function carModel(carEngine, id){
			if(submit_flag == '0'){
				$("#engineType").val(encodeURI(carEngine));
				$("#carYearName").val(encodeURI($("#carYearName").val()));
				$("#carModelName").val(encodeURI($("#carModelName").val()));
				
				var upst = $("#upst").val();
//				if(upst == '0') {	//跳转到车辆信息完善
//					path = '../car/newCarInfo?r=' + Math.random();
//				} else if(upst == '1') {	//跳转到车辆修改页面
//					path = '../car/toCarInfo?r=' + Math.random();
//				}
				var path = ''; 
				if(upst == '1') {	//跳转到车辆修改页面
					path = '../car/toCarInfo?r=' + Math.random();
					/* remoteLog.infoLog('${appId}','修改车辆请求','CARLIFE_REQ_QUERYCARENGINE','MODIFYCARINFO'); */
					remoteLog.infoLog('selectcarengine','clicked');
				} else {
					path = '../car/newCarInfo?r=' + Math.random();
					/* remoteLog.infoLog('${appId}','添加车辆请求','CARLIFE_REQ_QUERYCARENGINE','ADDCARINFO'); */
					remoteLog.infoLog('selectcarengine','clicked');
				}
				$("#selectEngineForm").attr('action', path).submit();
			}
			submit_flag = '1';
		}
	</script>
</body>
</html>
