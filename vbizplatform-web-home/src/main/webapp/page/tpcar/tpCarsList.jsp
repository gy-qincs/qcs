<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的车辆</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/app.css">
<link rel="stylesheet" href="../css/cars.css">
<script src="https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js"></script>
<script type="text/javascript" src="../js/remoteLogUtil.js"></script>
</head>
<body>
	<div class="mui-content" style="display:none" id="mui-content_id">
		<ul id="OA_task_1" class="mui-table-view mui-table-view-radio car-clear-ul-top">
		<c:forEach var="item" items="${myCarList}">
			<li class="mui-table-view-cell carui-pecc-carlist-li 
			<c:if test="${item.viId eq viId}">
			mui-selected
			</c:if>
			" viId="${item.viId}">
				<a class="mui-navigate-right">
				<div class="mui-input-row mui-radio mui-left">
					<div class="cars-content" viId="${item.viId}">
					<c:choose>
					    <c:when test="${empty item.viBrandName}">
							<img class="mui-media-object mui-pull-left carui-carList-carInfoImg" src="../images/viBrandDefault.png">
							<span>${fn:substring(item.viNumber, 0, 2)}**${fn:substring(item.viNumber, 4, 7)}</span>
							<p class='mui-ellipsis carui-carList-carNumber'>去完善品牌车系</p>
					    </c:when>
					    <c:otherwise>
							<img class="mui-media-object mui-pull-left carui-carList-carInfoImg" src="${item.viBrandLogo}">
							<span>${item.viBrandName}${item.viSeriesName}</span>
							<p class='mui-ellipsis carui-carList-carNumber'>${fn:substring(item.viNumber, 0, 2)}**${fn:substring(item.viNumber, 4, 7)}</p>
						</c:otherwise>
					</c:choose>
					</div>
				</div>
				</a>
			</li>
		</c:forEach>
		</ul>
	</div>
</body>
<script src="../js/jquery/jquery-1.11.1.min.js"></script>
<script src="../js/mui-1.0.1.js"></script>
<script src="../js/cars.js"></script>
<script type="text/javascript">
	var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
	
	$j(function(){
        $j("#mui-content_id").show();
		setTimeout(loadJSAPI, 500);
		function loadJSAPI(){
			remoteLog.infoLog('tpcarslist','openPage');
			// 显示标题栏
			AlipayJSBridge.call("showTitlebar");
			// 显示右按钮
			AlipayJSBridge.call("showOptionMenu");
			// 设置标题
			AlipayJSBridge.call("setTitle", {
			    title: '我的车辆'
			});
			// 设置右按钮属性
			AlipayJSBridge.call('setOptionMenu', {
			     title : '新增车辆'
			});
			document.addEventListener('back', function (e) {
			     e.preventDefault();
				 goBack();
			}, false);
			function goBack() {
				//history.go(-1);
				AlipayJSBridge.call('closeWebview');
			}
		}
		//新增车辆按钮
		document.addEventListener('optionMenu', function () {
			addCar();
		}, false);
		
		function addCar(){
			var carSize = $j("li").length;
			if(carSize >= 5){
				AlipayJSBridge.call('toast', {
				     content: '添加爱车数量已满',
				     type: '',
				     duration: 2000
				}, function(){
				});
			} else {
				//车辆列表添加车辆信息埋点
				/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','cars_list'); */
				remoteLog.infoLog('tpcarslist','clicked');
				location.replace('../tpitf/carInfo?r=' + Math.random());
				//location.href = '../tpitf/carInfo?r=' + Math.random();
			}
		}
	});
		
	mui.init();
	
	$j(".carui-pecc-carlist-li").click(function() {
		var viId = this.getAttribute("viId");
		//车辆列表添加车辆信息埋点
		/* remoteLog.infoLog('${appId}','跳转修改车辆信息','CARLIFE_VW_ADDCARINFO','cars_list'); */
		remoteLog.infoLog('tpcarslist','clicked');
		location.replace('../tpitf/carInfo?r=' + Math.random()+'&viId=' + viId);
		//location.href = '../tpitf/carInfo?r=' + Math.random()+'&viId=' + viId;
	});

	document.addEventListener('resume', function (event) {
	    location.reload(true);
	});
</script>
</html>