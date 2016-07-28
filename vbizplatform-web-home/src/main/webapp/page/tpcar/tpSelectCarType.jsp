<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>选择车型</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css" />
</head>
<body>
	<form action="" id="selectModelForm" name="selectModelForm">
	<div class="mui-content">
		<input type="hidden" name="carYearName" id="carYearName" value="${carYearName}">
		<input type="hidden" name="carYearId" id="carYearId" value="${carYearId}">
		<input type="hidden" name="sweptVolume" id="sweptVolume" value="${sweptVolume}">
		<input type="hidden" name="carModelName" id="carModelName" value="-1">
		<input type="hidden" name="carModelId" id="carModelId" value="-1">
		<input type="hidden" name="upst" id="upst" value="${upst}">
		<input type="hidden" name="viId" id="viId" value="${viId}">
		<input type="hidden" name="collapseFlag" id="collapseFlag" value="${collapseFlag}">
		<input type="hidden" name="engineFlag" id="engineFlag" value="${engineFlag}">
		<!-- 
		<div class="selectCarSeries">${brandName} ${seriesName}</div>
		 -->
		<ul class="mui-table-view">
		<c:forEach var="carModel" items="${carModelList}">
			<li class="mui-table-view-cell"><a class="mui-navigate-right"
				href="javascript:newCarInfo('${carModel}','');">${carModel}</a></li>
		</c:forEach>
		</ul>
	</div>
	</form>
	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script src="https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js"></script>
	<script type="text/javascript" src="../js/remoteLogUtil.js"></script>
	<script type="text/javascript">
		var submit_flag = "0";
		var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
		$(function() {
			setTimeout(loadJSAPI, 100);
			function loadJSAPI() {
				remoteLog.infoLog('tpselectcartype','openPage');
				// 显示标题栏
				AlipayJSBridge.call("showTitlebar");
				// 隐藏右按钮
				AlipayJSBridge.call("hideOptionMenu");
				// 设置标题
				AlipayJSBridge.call("setTitle", {
				    title: '请选择车型'
				});
				document.addEventListener('back', function (e) {
				     e.preventDefault();
					 goBack();
				}, false);
				function goBack() {
					history.go(-1);
				}
			}
		});
		function newCarInfo(carModelName, id){
			if(submit_flag == '0'){
				submit_flag = '1';
				var carYearName = $("#carYearName").val();
				$("#carYearName").val(encodeURI(carYearName));
				$("#carModelName").val(encodeURI(carModelName));
				$("#carModelId").val(id);
				//修改车辆信息
				if($("#upst").val()=='1'){
					/* remoteLog.infoLog('${appId}','修改车辆请求','CARLIFE_REQ_QUERYCARTYPE','MODIFYCARINFO'); */
					remoteLog.infoLog('tpselectcartype','clicked');
				} else {
					/* remoteLog.infoLog('${appId}','添加车辆请求','CARLIFE_REQ_QUERYCARTYPE','ADDCARINFO'); */
					remoteLog.infoLog('tpselectcartype','clicked');
				}
				var path = '../tpitf/queryCarEngine?r=' + Math.random();
				$("#selectModelForm").attr('action', path).submit();
			}
		}
	</script>
</body>
</html>
