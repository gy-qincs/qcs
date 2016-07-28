<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>请选择生产年份</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css" />
</head>
<body>
	<form action="" id="selectYearForm" name="selectYearForm">
	<div class="mui-content">
	
		<input type="hidden" name="carYearName" id="carYearName" >
		<input type="hidden" name="carYearId" id="carYearId" >
		<input type="hidden" name="sweptVolume" id="sweptVolume" value="${sweptVolume}">
		<input type="hidden" name="upst" id="upst" value="${upst}">
		<input type="hidden" name="viId" id="viId" value="${viId}">
		<input type="hidden" name="collapseFlag" id="collapseFlag" value="${collapseFlag}">
		
		<ul class="mui-table-view car-clear-ul-top">
			<li class="mui-table-view-cell mui-media car-sel-seri-li">
				<div class="carui-selectSeries-top-box">
					<img class="mui-media-object mui-pull-left carui-selectYearImg-margin" src="${modPicUrl}" />
					<span>${brandName} ${carSeriesName}</span>
				</div>
			</li>
		</ul>
	</div>
	<div class="mui-content carui-selectYear-content">
		<ul class="mui-table-view car-clear-ul-top">
		<c:forEach var="carYear" items="${carYearList}">
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right" href="javascript:carModel('${carYear}','');">${carYear}</a>
			</li>
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
			function loadJSAPI(){
				remoteLog.infoLog('tpselectcarproductiondate','openPage');
				// 显示标题栏
				AlipayJSBridge.call("showTitlebar");
				// 显示右按钮
				AlipayJSBridge.call("hideOptionMenu");
				// 设置标题
				AlipayJSBridge.call("setTitle", {
				    title: '请选择生产年份'
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
		function carModel(carYearName, id){
			if(submit_flag == '0'){
				submit_flag = '1';
				$("#carYearName").val(encodeURI(carYearName));
				$("#carYearId").val(id);
				var upst = $("#upst").val();
				var path = '../tpitf/queryCarModels?r=' + Math.random();
				//修改车辆信息
				if(upst=='1'){
					/* remoteLog.infoLog('${appId}','修改车辆请求','CARLIFE_REQ_QUERYCARPRODUCTIONDATE','MODIFYCARINFO'); */
					remoteLog.infoLog('tpselectcarproductiondate','clicked');
				} else {
					/* remoteLog.infoLog('${appId}','添加车辆请求','CARLIFE_REQ_QUERYCARPRODUCTIONDATE','ADDCARINFO'); */
					remoteLog.infoLog('tpselectcarproductiondate','clicked');
				}
				$("#selectYearForm").attr('action', path).submit();
			}
		}
	</script>
</body>
</html>
