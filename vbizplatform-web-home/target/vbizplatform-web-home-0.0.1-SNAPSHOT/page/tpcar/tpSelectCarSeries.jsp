<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>选择车系</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css" />
</head>
<body>
	<form action="" name="carSerieForm" id="carSerieForm">
	<div class="mui-content">
		<input type="hidden" name="brandName" id="brandName" value="${brandName}">
		<input type="hidden" name="picUrl" id="picUrl" value="${picUrl}">
		<input type="hidden" name="viId" id="viId" value="${viId}">
		<input type="hidden" name="carSeriesName" id="carSeriesName">
		<input type="hidden" name="carSeriesId" id="carSeriesId">
		<input type="hidden" name="manufacturer" id="manufacturer">
		<input type="hidden" name="modPicUrl" id="modPicUrl">
		<input type="hidden" name="bgUrl" id="bgUrl" value="${bgUrl}">
		<input type="hidden" name="upst" id="upst" value="${upst}">
		<input type="hidden" name="newCarFlag" id="newCarFlag" value="${newCarFlag}">
		<ul class="mui-table-view car-clear-ul-top">
			<li class="mui-table-view-cell mui-media car-sel-seri-li">
				<div class="carui-selectSeries-top-box">
					<img class="mui-media-object mui-pull-left" src="${picUrl}" />
					<span>${brandName}</span>
				</div>
			</li>
		</ul>
	</div>
	<!-- 
	<div class="selectCarSeries">奥迪进口</div>
	 -->
	<div class="mui-content car-clear-content-top">
		<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul-before">
		<c:forEach var="carSeries" items="${carSeriesMap}">
			<li data-group="${carSeries.key}" class="mui-table-view-divider selectCarSeries carui-selectSeri-li-title">${carSeries.key}</li>
			<c:forEach var="series" items="${carSeries.value}">
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right" href="javascript:carYear('${series.name}','${series.id}','${series.logoUrl}','${series.companyName}');">
				${series.name}
				</a>
			</li>
			</c:forEach>
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
		function carYear(carSeriesName, carSeriesId, logoUrl,manufacturer){
			if(submit_flag == '0'){
				$("#carSeriesName").val(encodeURI(carSeriesName));
				$("#carSeriesId").val(carSeriesId);
				$("#modPicUrl").val(encodeURI(logoUrl));
				$("#manufacturer").val(encodeURI(manufacturer));
				$("#brandName").val(encodeURI($("#brandName").val()));
				$("#bgUrl").val(encodeURI($("#bgUrl").val()));
				var upst = $("#upst").val();
				if(upst == '0' || upst == '') {	//跳转到车辆信息完善
					path = '../tpitf/newCarInfo?r=' + Math.random();
					/* remoteLog.infoLog('${appId}','添加车辆请求','CARLIFE_REQ_QUERYCARSERIES','ADDCARINFO'); */
					remoteLog.infoLog('tpselectcarseries','clicked');
				} else if(upst == '1') {	//跳转到车辆修改页面
					path = '../tpitf/toCarInfo?r=' + Math.random();
					/* remoteLog.infoLog('${appId}','修改车辆请求','CARLIFE_REQ_QUERYCARSERIES','MODIFYCARINFO'); */
					remoteLog.infoLog('tpselectcarseries','clicked');
				}
				$("#carSerieForm").attr('action', path).submit();
			}
			submit_flag = '1';
			
		}
		$(function() {
			setTimeout(loadJSAPI, 100);
			function loadJSAPI() {
				remoteLog.infoLog('tpselectcarseries','openPage');
				// 显示标题栏
				AlipayJSBridge.call("showTitlebar");
				// 设置标题
				AlipayJSBridge.call("setTitle", {
				    title: '请选择车系'
				});
				// 隐藏右按钮
				AlipayJSBridge.call("hideOptionMenu");
				
				document.addEventListener('back', function (e) {
				    e.preventDefault();
					goBack();
				}, false);
				function goBack() {
					history.go(-1);
				}
			}
		});
	</script>
</body>
</html>
