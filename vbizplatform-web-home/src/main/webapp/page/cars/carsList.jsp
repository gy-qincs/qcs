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
		<ul id="OA_task_1" class="mui-table-view">
		<c:forEach var="item" items="${myCarList}">
			<li class="mui-table-view-cell mui-media carui-carList-li-box" vurId="${item.vurId}"
				viId="${item.viId}" vurGmtModified="${item.vurGmtModified}" defCar="${item.defaultStatus}">
				<div class="mui-slider-right mui-disabled">
					<a class="mui-btn mui-btn-red car-pull-left-delete">删除</a>
				</div>
				<div class="mui-input-row mui-radio mui-left mui-slider-handle carui-carList-inBox">
					<c:if test="${item.defaultStatus eq 1}">
					<div class="radio on"></div>
					</c:if>
					<c:if test="${item.defaultStatus eq 0}">
					<div class="radio"></div>
					</c:if>
					<div class="cars-content mui-navigate-right" viId="${item.viId}">
					<!-- -->
					<c:choose>
					    <c:when test="${empty item.viBrandName}">
					        <div class="carui-carList-carInfo">
							<img class="mui-media-object mui-pull-left carui-carList-carInfoImg" src="../images/viBrandDefault.png">
							<span>${fn:substring(item.viNumber, 0, 2)}**${fn:substring(item.viNumber, 4, 7)}</span>
							<c:if test="${item.vlCheckStatus == 0}">
							<div class="carui-carsList-check-box">
								<span>已验证</span>
							</div>
							</c:if>
							<p class='mui-ellipsis carui-carList-carNumber'>去完善品牌车系</p>
							</div>
					    </c:when>
					    <c:otherwise>
					        <div class="carui-carList-carInfo">
							<img class="mui-media-object mui-pull-left carui-carList-carInfoImg" src="${item.viBrandLogo}">
							<span>${item.viBrandName}${item.viSeriesName}</span>
							<c:if test="${item.vlCheckStatus == 0}">
							<div class="carui-carsList-check-box">
								<span>已验证</span>
							</div>
							</c:if>
							<p class='mui-ellipsis carui-carList-carNumber'>${fn:substring(item.viNumber, 0, 2)}**${fn:substring(item.viNumber, 4, 7)}</p>
						<!-- -->
							</div>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${item.defaultStatus eq 1}">
						<div class="carui-carList-carDefault">默认车辆</div>
					</c:if>
					</div>
				</div> <!--</a>-->
			</li>
		</c:forEach>
		</ul>
		<div class="mui-content-padded">
			<a href="javascript:addCar();">
				<button type="button" id="addCar" class="mui-btn mui-btn-block mui-btn-outlined">添加车辆</button>
			</a>
		</div>
	</div>
</body>
<script src="../js/jquery/jquery-1.11.1.min.js"></script>
<script src="../js/mui-1.0.1.js"></script>
<script src="../js/cars.js"></script>
<script type="text/javascript">
	var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
	
	$j(function(){
	     var ua = navigator.userAgent.toLowerCase();  
	      if (/iphone|ipad|ipod/.test(ua)) {
	        $j(".carui-carList-carDefault").css("margin-top","17px");
	        
	        $j(".mui-navigate-right").addClass('mui-navigate-right-arrow-ios');
	      } else if (/android/.test(ua)) {
	        $j(".carui-carList-carDefault").css("margin-top","20px");
	        $j(".mui-navigate-right").addClass('mui-navigate-right-arrow-android');
	        $j(".mui-media-object.mui-pull-left.carui-carList-carInfoImg ~ span").css("margin-top","11px");
	      }
        $j("#mui-content_id").show();
		setTimeout(loadJSAPI, 500);
		function loadJSAPI(){
			remoteLog.infoLog('carslist','openPage');
			// 显示标题栏
			AlipayJSBridge.call("showTitlebar");
			// 隐藏右按钮
			AlipayJSBridge.call("hideOptionMenu");
			// 设置标题
			AlipayJSBridge.call("setTitle", {
			    title: '我的车辆'
			});
			document.addEventListener('back', function (e) {
			     e.preventDefault();
				 goBack();
			}, false);
			function goBack() {
				//location.href = "../owner/index?r=" + Math.random();
 				//location.replace('../owner/index?r=' + Math.random());
				AlipayJSBridge.call('pushWindow', {
					url: '../owner/index?rand=' + Math.random()
				});
			}
		}
	});
		
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
			remoteLog.infoLog('carslist','clicked');
			//location.href="../car/queryBrand?newCarFlag=1&r=" + Math.random();
			AlipayJSBridge.call('pushWindow', {
				url: "../car/queryBrand?newCarFlag=1&fromPage=home&r=" + Math.random()
			});
		}
	}
	mui.init();
	(function($) {
		$('#OA_task_1').on('tap', '.mui-btn', function(event) {
			var elem = this;
			var li = elem.parentNode.parentNode;
			var liLen = $(".mui-table-view-cell").length;
			var btnArray = [ '再想想', '确定' ];
			mui.confirm('你要删除这辆爱车的信息吗？', '', btnArray, function(e) {
				if (e.index == 0) {
					setTimeout(function() {
						$.swipeoutClose(li);
					}, 0);
				} else {
					var vurId = li.getAttribute("vurId");
					var viId = li.getAttribute("viId");
					var vurGmtModified = li.getAttribute("vurGmtModified");
					var defCar = li.getAttribute("defCar");
					$.ajax({
						type : "POST",
						url : "../car/delMyCar",
						dataType : "text",
						cache : false,
						data : "vurId=" + vurId
										+ "&viId=" + viId
										+ "&vurGmtModified=" + vurGmtModified
										+ "&r=" + Math.random(),
						success : function(data) {
							if (data == 'ok') { 
								if (liLen == 1) {
									location.href = "../car/myCarList?r=" + Math.random();
								} else if(defCar == '1'){
									location.reload();
								} else {
									li.parentNode.removeChild(li);
								}
							}else{
								setTimeout(function() {
									$.swipeoutClose(li);
								}, 0);
							}
						}
					});
				}
			});
		});
	})(mui);
	$j(".cars-content").click(function() {
		var viId = this.getAttribute("viId");
		//location.href = '../car/toCarInfo?viId=' + viId +'&r=' + Math.random()+'&backUrl=carList';
		AlipayJSBridge.call('pushWindow', {
				url: '../car/toCarInfo?viId=' + viId +'&r=' + Math.random()+'&backUrl=carList'
			});
	});

	// 单选按钮切换
	$j(".radio").click(function() {
		$j(".radio").removeClass("on");
		$j(this).addClass("radio on");
		$j(".carui-carList-carDefault").remove();
		if($j(this).parent().children(".mui-navigate-right").children(".carui-carList-carDefault").html() == undefined){
			$j(this).parent().children(".mui-navigate-right").append("<div class='carui-carList-carDefault'>默认车辆</div>");
		}
		var ua = navigator.userAgent.toLowerCase();  
        if (/iphone|ipad|ipod/.test(ua)) {
          $j(".carui-carList-carDefault").css("margin-top","17px");
        } else if (/android/.test(ua)) {
          $j(".carui-carList-carDefault").css("margin-top","20px");
          $j(".mui-media-object.mui-pull-left.carui-carList-carInfoImg ~ span").css("margin-top","11px");
        }
		var li = this.parentNode.parentNode;
		var vurId = li.getAttribute("vurId");
		var viId = li.getAttribute("viId");
		var vurGmtModified = li.getAttribute("vurGmtModified");
		var carSize = $j("li").length;
		$j.ajax({
			type : "POST",
			url : "../car/setDefaultCar",
			dataType : "text",
			cache : false,
			data : "vurId=" + vurId
							+ "&viId=" + viId
							+ "&r=" + Math.random(),
			success : function(data) {
				if (data == 'ok') {
					if(carSize != 1){
						//toast('成功修改默认车辆');
					}
				} else {
					//toast('修改默认车辆失败');
				}
			}
		});
		//$(this).f
	});
	function toast(str){
		AlipayJSBridge.call('toast', {
		     content: str,
		     type: 'success',
		     duration: 2000
		}, function(){
		});
	}
	document.addEventListener('resume', function (event) {

	    location.reload(true);

	});
</script>
</html>