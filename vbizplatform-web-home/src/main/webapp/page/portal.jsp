<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>车主服务</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css?version=1.1.0.1">
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../js/jquery/jquery.cookie.js"></script>
<script src="https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js"></script>
<script type="text/javascript" src="../js/remoteLogUtil.js"></script>
<script type="text/javascript" src="../js/mui.min.js"></script>
</head>
<body>
	<div class="mui-content">
		<div class="transparent_bg" >
		<!-- 
			<header class="mui-bar mui-bar-nav transparent_cl">			
				<a class="mui-icon mui-pull-left"  onclick="goBack();" href="#">
				    <img src="../images/back-icon.png" class="exit-icon">
				    <span class="back-span">返回</span>
	          	</a>
				<h1 class="mui-title car-mui-title">车主服务</h1>
				<a class="mui-icon mui-pull-right" href="#" onclick="toCenter();">
					<img src="../images/center.png" class="personal-center">
				</a>
			</header>  -->
			<div class="car-index-top-box transparent_class">
				<div class="index-img-top-box">
					<a id="top-img-a" href="#">
						<img id="top-img" src="" class="index-img-top">
					</a>
				</div>
				<a id="top-span-a" href="#">
					<span id="car-title" class="car-top-span-car-title"></span>
				</a>
			</div>
			<div class="index-location-top transparent_class"
				onclick="locate(this);">
				<span class="mui-icon index-location-top-img"></span> <span
					class="mui-icon car-index-location-top-span" id="location">
					<c:if test="${empty cityMap.resident_city_name}">
				选择城市
				</c:if> <c:if test="${!empty cityMap.resident_city_name}">
				${cityMap.resident_city_name}
				</c:if>
				</span>
			</div>
			<div class="index-msg-top-bottom transparent_class"
				id="limitedContent">
				<c:if test="${!empty message}">
					<img src="../images/laba.png" class="index-msg-top-bottom-img">
					<div class="index-msg-top-bottom-box" onclick="gotoMsg();">
						${message.title}:
						<c:choose>
							<c:when test="${fn:length(message.content) > 31}">
								<c:out value="${fn:substring(message.content, 0, 30)}..." />
							</c:when>
							<c:otherwise>
								<c:out value="${message.content}" />
							</c:otherwise>
						</c:choose>
					</div>
					<img src="../images/right.png" class="index-msg-top-bottom-img">
				</c:if>
				<c:if test="${empty message}">
				<c:choose>
					<c:when test="${fn:length(limitedContent) > 31}">
						<c:out value="${fn:substring(limitedContent, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${limitedContent}" />
					</c:otherwise>
				</c:choose>
				</c:if>
			</div>
		</div>
		<ul class="mui-table-view mui-grid-view mui-grid-9">
		<!--  -->
            <c:forEach varStatus="status" var="item" items="${categoryAppList}">	
            	<c:choose>
				   <c:when test="${((status.index+1) % 3) == '0'}">
						<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right">
				   </c:when>
				   <c:otherwise> 
				   		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
				   </c:otherwise>
				</c:choose>
	              <c:if test="${!empty item.app_url}">
	              <a class="category_app_item" app_name='${item.app_name}' app_url='${item.app_url}' app_id='${item.app_id}' href="javascript:categoryApp('${item.app_url}','${item.app_name}','${item.app_id}');">
	              </c:if>
	              <c:if test="${empty item.app_url}">
	              <a href="#" onclick="toast()">
	              </c:if>
	                <img src="${item.app_logo}" class="carui-img-height">
	                <div class="mui-media-body carui-active-title">${item.app_name}</div>
	                <div class="mui-media-body carui-active-desc">${item.app_memo}</div>
	              </a>
	            </li>
			</c:forEach>
			<c:if test="${fn:length(categoryAppList)%3>0}">
				<c:forEach var="i" begin="1" end="${3-(fn:length(categoryAppList)%3)}" step="1">
				<c:choose>
				   <c:when test="${(3-(fn:length(categoryAppList)%3)) == i}">    
						<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right">
				   </c:when>
				   <c:otherwise> 
				   		<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
				   </c:otherwise>
				</c:choose>
				<a>
                <div class="carui-img-null-height"></div>
                <div class="mui-media-body carui-active-title"></div>
                <div class="mui-media-body carui-active-desc"></div>
        		</a>        
	            </li> 
				</c:forEach>			
			</c:if>
			
        </ul>
		<div id="slider" class="mui-slider carui-index-banner-box">
			${banner}
		</div>
		
	</div>
	<script type="text/javascript">
		var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
	 	//重构类目
		function reBuildCategoryApp(cityCode, viNumber){
			$.ajax({
				type : "POST",
				url : "../car/queryCategoryAppList",
				dataType : "json",
				cache : false,
				data : "r=" + Math.random() + '&cityCode='
						+ cityCode + '&viNumber='
						+ encodeURI(viNumber),
				success : function(data) {
					if (data != '') {
						var res = eval(data);
						var appList = "";
						for(var i = 0; i < res.length; i++) {
							if(((i + 1) % 3) == 0){
								appList+= '<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right">';
							} else {
								appList+= '<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">';
							}
							if(res[i].app_url != ''){
								appList+= '<a class="category_app_item" app_name="'+res[i].app_name+'" app_url="'+res[i].app_url+'" app_id="'+res[i].app_id+'" href="javascript:categoryApp(\''+res[i].app_url+'\',\''+res[i].app_name+'\',\''+res[i].app_id+'\');">';
							} else {
								appList+= '<a href="#" onclick="toast();">';
							}
							appList+= '<img src="'+res[i].app_logo+'" class="carui-img-height">';
							appList+= '<div class="mui-media-body carui-active-title">'+res[i].app_name+'</div>';
							appList+= '<div class="mui-media-body carui-active-desc">'+res[i].app_memo+'</div>';
							appList+= '</a></li>';
						}
						if((res.length % 3) > 0) {
							for(var k = 0; k < (3-(res.length % 3)); k++) {
								if((3-(res.length % 3)) == k){
									appList+= '<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3 carui-index-grid-9-border-right">';
								} else {
									appList+= '<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">';
								}
								appList+= '<a><div class="carui-img-null-height"></div>';
								appList+= '<div class="mui-media-body carui-active-title"></div>';
								appList+= '<div class="mui-media-body carui-active-desc"></div>';
								appList+= '</a></li>'; 
							}
						}
						//alert(appList);
						$('.mui-table-view').html(appList);
					}
				}
			});
		}
		//用户进入首页埋点
		/* remoteLog.infoLog('${appId}','用户进入首页','CARLIFE_HOME_PAGE','-'); */
		remoteLog.infoLog('mainpage','openPage');
		function categoryApp(appUrl,appName,appCode){
			//首页9宫格埋点
			/* remoteLog.infoLog('${appId}',appName,'CARLIFE_CATEGORY_APP_'+appCode,'-'); */
			remoteLog.infoLog('mainpage','clicked');
			var reUrl = /^(https|http)?:/;
			if(!reUrl.test(appUrl)){
				if(appUrl.indexOf('20000241') != -1 || appUrl.indexOf('20000781') != -1) {
					appUrl = appUrl.substring(0,appUrl.indexOf('url=')+4) + encodeURIComponent(appUrl.substring(appUrl.indexOf('url=')+4,appUrl.length));				
				}
				//alert(appUrl);
				location.href= appUrl;
			} else {
				//alert(appUrl);
				AlipayJSBridge.call('pushWindow', {
					url: appUrl,
					param: {
			              readTitle: "YES",
			              showToolBar: "NO",
			              showProgress: "NO",
			              transparentTitle: "none"
			            }
				});
			}	
		}
		/**
		*/
		$(document).on("optionMenu", function(){
			AlipayJSBridge.call('pushWindow', {
				url: "../owner/index?r=" + Math.random(),
				param: {
		              readTitle: "YES",
		              showToolBar: "NO",
		              showProgress: "NO",
		              transparentTitle: "none"
		            }
			});
		});

		function gotoMsg() {
			AlipayJSBridge.call('pushWindow', {
				url: "../msg/toMessageList?r=" + Math.random(),
				param: {
		              readTitle: "YES",
		              showToolBar: "NO",
		              showProgress: "NO",
		              transparentTitle: "none"
		            }
			});
		}
		function getLimitedContent(cityCode, cityName) {
			<c:if test="${empty message}">
			$.ajax({
				type : "POST",
				url : "../owner/queryLimited",
				dataType : "json",
				cache : false,
				data : "r=" + Math.random() + '&cityCode=' + cityCode + '&cityName=' + cityName,
				success : function(data) {
					var content = data.content;
					if (content != '' && content != null) {
						if(content.length>31){
							$("#limitedContent").text(content.substr(0,30)+'...');
						} else{
							$("#limitedContent").text(content);
						}
					} else {
						$("#limitedContent").text('');
					}
				}
			});
			</c:if>
		}
		mui.init();
		var slider = mui("#slider");
		slider.slider({
			interval:3000	
		});
		$(function() {
			setTimeout(loadJSAPI, 100);
			function loadJSAPI(){
				// 显示标题栏
		        AlipayJSBridge.call("showTitlebar");
		        // 显示右按钮
		        AlipayJSBridge.call("showOptionMenu");
		        // 设置标题
		        AlipayJSBridge.call("setTitle", {
		            title: '车主服务'
		        });
		        // 设置右按钮属性
		        AlipayJSBridge.call('setOptionMenu', {
		             icon : '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'
		        });
			}
			document.addEventListener('back', function (e) {
			     e.preventDefault();
				 goBack();
			}, false);
			function goBack() {
				AlipayJSBridge.call('exitApp');
			}
		});
	 	function toast(){
			AlipayJSBridge.call('toast', {
			    content: '即将上线，敬请期待',
			    type: '',
			    duration: 1200
			});
		}
	 	//获取经纬度
	 	(function(){
	 		setTimeout(function() {
	 			AlipayJSBridge.call('getLocation', function (result) {
	 				var locaname = result.city ? result.city : result.province;
	 				locaname = locaname.replace("市", "");
	 				var locacode = result.adcode;
					var oldcityName = '${cityMap.resident_city_name}';
					var oldcitycode = '${cityMap.resident_city_code}';
					if(result.error) {	//定位失败
						return;
					} else {	//定位成功
						var lastTimeGPS = '${lastTimeGPS}';
 						if(locacode.substring(0,3) == '110' || locacode.substring(0,3) == '120' || locacode.substring(0,3) == '310' || locacode.substring(0,3) == '500'){
 							locacode = locacode.substring(0,3) + '100';
	 					} else {
	 						locacode = locacode.substring(0,4) + '00';
		 				}
		 				//保存坐标
 						modifyLocation(result.latitude, result.longitude, locacode, locaname);
						//判断常驻城市
						if(oldcitycode != '' && oldcitycode != 'undefined'){//有常驻城市
							if(locacode != lastTimeGPS &&locacode != oldcitycode) {//当前城市与常驻城市不同
								AlipayJSBridge.call('confirm', {
			 					     title: '提醒',
			 					     message: '系统定位到您在'+locaname+'，是否切换？',
			 					     okButton: '立即切换',
			 					     cancelButton: '暂不切换'
			 					}, function (result) {
			 					     if(result.ok){
			 					    	$("#location").text(locaname.replace("市", ""));
				 						//重构类目
				 						reBuildCategoryApp(locacode, '${myCar.viNumber}');
				 						//设置默认城市
			 					    	modifyResidentcity(locacode, locaname);
			 					    	//查询限行
			 					    	getLimitedContent(locacode, locaname);
									}
			 					});
							}
						} else { //未设置常驻城市
 					    	$("#location").text(locaname.replace("市", ""));
	 						//重构类目
	 						reBuildCategoryApp(locacode, '${myCar.viNumber}');
	 						//设置常驻城市
 					    	modifyResidentcity(locacode, locaname);
 					    	//查询限行
 					    	getLimitedContent(locacode, locaname);
						}
					}
				});

		 		/**
	 			if('${locationMap.latitude}' == '' || '${locationMap.latitude}' == "undefined"){
		 			//定位
					AlipayJSBridge.call('getLocation', function(result) {
						var cityName = '${cityMap.resident_city_name}';
						var oldcitycode = '${cityMap.resident_city_code}';
			 			if (cityName == '') {	//未设置常驻城市
			 				var citycode = '330100';
		 					var cityname = '杭州';
		 					if (typeof(result.error) != "undefined") { //定位失败
			 					//alert('定位失败');
		 						getLimitedContent(citycode, cityname);
		 						$("#location").text(cityname);
		 						return;
		 					}
		 					var loca = result.city ? result.city : result.province;
		 					if (loca != '') { //定位成功
		 						$("#location").text(loca.replace("市", ""));
		 						citycode = result.adcode;
		 						cityname = loca.replace("市", "");
		 					} else {
		 						$("#location").text(cityname);
		 					}
		 					getLimitedContent(citycode, cityname);
			 			}
			 			
			 			var city = result.city?result.city:result.province;
						$.ajax({
							type : "POST",
							url : "../owner/modifyLocation",
							dataType : "json",
							cache : false,
							data : "r=" + Math.random() + '&latitude='
									+ result.latitude + '&longitude='
									+ result.longitude + '&citycode='
									+ result.adcode + '&city='
									+ city,
							success : function(data) {
								if (data != '' && data.result == '0') {
									//alert('保存坐标');
								}
							}
						});
						var citycodeN = result.adcode;
 						var citycd = citycodeN.substring(0,3);
 						if(citycd == '110' || citycd == '120' || citycd == '310' || citycd == '500'){
 							citycodeN = citycodeN.substring(0,3) + '100';
	 					} else {
	 						citycodeN = citycodeN.substring(0,4) + '00';
		 				}
		 				if(oldcitycode != '' && citycodeN != oldcitycode) { //定位城市与常驻城市不一致
		 					AlipayJSBridge.call('confirm', {
		 					     title: '亲',
		 					     message: '是否需要把当前城市设置为常驻城市',
		 					     okButton: '确定',
		 					     cancelButton: '算了'
		 					}, function (result) {
		 					     if(result.ok){
		 					    	$("#location").text(city.replace("市", ""));
			 						//重构类目
			 						reBuildCategoryApp(citycodeN,'${myCar.viNumber}');
			 						//设置默认城市
		 					    	modifyResidentcity(citycodeN,city);
								}
		 					});
			 			} else if(oldcitycode == ''){
			 				modifyResidentcity(citycodeN,city);
				 		}
					});
		 		}
		 		*/
	 		}, 500);
	 	})();
	 	//设置常驻城市
	 	function modifyResidentcity(citycode, cityname) {
	 		$.ajax({
				type : "POST",
				url : "../owner/modifyResidentcity",
				dataType : "json",
				cache : false,
				data : "r=" + Math.random() + '&cityName='
						+ cityname + '&cityCode='
						+ citycode,
				success : function(data) {
				}
			});
		}
		//保存坐标
		function modifyLocation(latitude, longitude, adcode, city) {
			$.ajax({
				type : "POST",
				url : "../owner/modifyLocation",
				dataType : "json",
				cache : false,
				data : "r=" + Math.random() + '&latitude='
						+ latitude + '&longitude='
						+ longitude + '&citycode='
						+ adcode + '&city='
						+ city,
				success : function(data) {
					if (data != '' && data.result == '0') {
						//alert('保存坐标');
					}
				}
			});
		}
	 	var flush = true;
		// 选择城市
		function locate() {
			flush = false;
			AlipayJSBridge.call('getCities', {
				//currentCity : '北京',
				//adcode : '110100'
			}, function(result) {
				// 隐藏标题栏
				AlipayJSBridge.call("hideTitlebar");
				
				if (result.city != '') {
					$("#location").text(result.city);
					$.ajax({
						type : "POST",
						url : "../owner/modifyResidentcity",
						dataType : "json",
						cache : false,
						data : "r=" + Math.random() + '&cityName='
								+ result.city + '&cityCode='
								+ result.adcode,
						success : function(data) {
							flush = true;
							location.reload(true);
							if (data != '' && data.result == '0') {
							}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							flush = true;
							location.reload(true);
					    }
					});
				}
			});
		}
		
		document.addEventListener('resume', function (event) {
			if(flush){
				$.ajax({
					type : "POST",
					url : "../car/getDefaultCarInfo",
					dataType : "json",
					cache : false,
					success : function(data) {
						if (data.success) { 
							var myCar = data.defaultCar;
							if(myCar != null && myCar != ''){
								if(myCar.viBrandName == '') {
									document.getElementById("top-img").src = "../images/index-logo.png";
									$("#top-img").css({
										"height": "69px",
								    	"margin": "2px"
									});
								} else {
									$("#top-img").css({
										"height": "45px",
										"border-radius": "100%",
								    	"margin": "19% 0"
									});
									if(myCar.viLogoUrl != ''){
										document.getElementById("top-img").src = myCar.viLogoUrl;
									} else {
										document.getElementById("top-img").src = myCar.viBrandLogo;
									}
								}
								
								if(myCar.viBrandName == '') {
									document.getElementById("car-title").innerHTML = myCar.viNumber.substr(0, 2)+"**"+myCar.viNumber.substr(4, 7);
								} else {
									document.getElementById("car-title").innerHTML = myCar.viBrandName+myCar.viSeriesName;
								}
								if(myCar.bgUrl != ''){
									var ImgObj = new Image(); //判断图片是否存在  
									ImgObj.src = myCar.bgUrl;  
									ImgObj.onload = function (){
										$(".transparent_bg").css("background-image","url('"+myCar.bgUrl+"')");  
									};
								} else {
									var ImgObj = new Image(); //判断图片是否存在  
									ImgObj.src = '../images/defualt_backgd_img.jpg';  
									ImgObj.onload = function (){
										$(".transparent_bg").css("background-image","url('"+'../images/defualt_backgd_img.jpg'+"')");  
									};
								}
								$('#top-img-a').off('click');
								
								$('#top-img-a').on('click',function () {
// 									alert(viId);
									var viId3 = (myCar.viId);
									if(!viId3){
										//首页添加车辆信息埋点
										/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
										remoteLog.infoLog('mainpage','clicked');
										AlipayJSBridge.call('pushWindow', {
											url: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),
											param: {
									              readTitle: "YES",
									              showToolBar: "NO",
									              showProgress: "NO",
									              transparentTitle: "none"
									            }
										});
									} else {
										AlipayJSBridge.call('pushWindow', {
											url: "../car/toCarInfo?viId="+viId3+"&backUrl=portal",
											param: {
									              readTitle: "YES",
									              showToolBar: "NO",
									              showProgress: "NO",
									              transparentTitle: "none"
									            }
										});
									}
								});
								
								$('#top-span-a').off('click');
								
								$('#top-span-a').on('click',function () {
// 									alert(viId);
									var viId3 = (myCar.viId);
									if(!viId3){
										//首页添加车辆信息埋点
										/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
										remoteLog.infoLog('mainpage','clicked');
										AlipayJSBridge.call('pushWindow', {
											url: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),
											param: {
									              readTitle: "YES",
									              showToolBar: "NO",
									              showProgress: "NO",
									              transparentTitle: "none"
									            }
										});
									} else {
										AlipayJSBridge.call('pushWindow', {
											url: "../car/toCarInfo?viId="+viId3+"&backUrl=portal",
											param: {
									              readTitle: "YES",
									              showToolBar: "NO",
									              showProgress: "NO",
									              transparentTitle: "none"
									            }
										});
									}
								});
							} else {
								var ImgObj = new Image(); //判断图片是否存在  
								ImgObj.src = '../images/defualt_backgd_img.jpg';  
								ImgObj.onload = function (){
									$(".transparent_bg").css("background-image","url('"+'../images/defualt_backgd_img.jpg'+"')");  
								};
								
								$("#top-img").css({
									"height": "45px",
									"border-radius": "100%",
							    	"margin": "19% 0"
								});
								//清空
								document.getElementById("top-img").src = "../images/iconfont-jiahao.png";
								document.getElementById("car-title").innerHTML = "添加爱车";
								$('#top-img-a').off('click');
								
								$('#top-img-a').on('click',function () {
// 									alert(viId2);
									//首页添加车辆信息埋点
									/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
									remoteLog.infoLog('mainpage','clicked');
									AlipayJSBridge.call('pushWindow', {
										url: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),
										param: {
								              readTitle: "YES",
								              showToolBar: "NO",
								              showProgress: "NO",
								              transparentTitle: "none"
								            }
									});
								});
								
								$('#top-span-a').off('click');
								
								$('#top-span-a').on('click',function () {
// 									alert(viId2);
									//首页添加车辆信息埋点
									/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
									remoteLog.infoLog('mainpage','clicked');
									AlipayJSBridge.call('pushWindow', {
										url: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),
										param: {
								              readTitle: "YES",
								              showToolBar: "NO",
								              showProgress: "NO",
								              transparentTitle: "none"
								            }
									});
								});
							}
							//动态修改 获取车辆ID 拼接到车险
							var vi_id = '';
							if(myCar != null && myCar != ''){
								vi_id = myCar.viNumber;	
							}
							var $appItem = $(".category_app_item");
							$appItem.each(function(){
								var category_id = $(this).attr('app_id');
								var category_name = $(this).attr('app_name');
								var category_urls = $(this).attr('app_url');
								if(category_id == 'CARLIFE013'){
									if(category_urls.indexOf('carNo') == -1){
										category_urls = category_urls+encodeURIComponent('&carNo='+vi_id);
									} else {
										var decUrl = category_urls;
										category_urls = decUrl.substring(0,decUrl.indexOf('carNo'))+encodeURIComponent('carNo='+vi_id);
									}
									$(this).attr('href',"javascript:categoryApp("+"'"+category_urls+"'"+","+"'"+category_name+"'"+","+"'"+category_id+"'"+")");
									//alert($(this).attr('href'));
								}
							});
							//更新最新消息
							var messageList = data.messageList;
							if(messageList != null && messageList != ''){
								var $limitedContent = $("#limitedContent");
								$limitedContent.empty();
								
								var contentHtml = 
								'<img src="../images/laba.png" class="index-msg-top-bottom-img">'+
								'<div class="index-msg-top-bottom-box" onclick="gotoMsg();">';	
								if(messageList.content.length >31){
									
									contentHtml += messageList.title+':'+messageList.content.substr(0, 30)+'...';
								} else {
									contentHtml += messageList.title+':'+messageList.content;
								}

								contentHtml += '</div><img src="../images/right.png" class="index-msg-top-bottom-img">';
								$limitedContent.html(contentHtml);
							} else {
								if(data.limitedContent.length >31){
									$("#limitedContent").text(data.limitedContent.substr(0, 30)+'...');
								} else {
									$("#limitedContent").text(data.limitedContent);
								}
							}
							
						}
					}
				});
		      }
		});
		
		// 跳转
		function jumphref(href){
			AlipayJSBridge.call('pushWindow', {
	            url: href,
	          	param: {
	              	readTitle: "YES",
	              	showToolBar: "NO",
	              	showProgress: "NO",
	              	transparentTitle: "none"
	            }
	        });
		}
	</script>
	<c:if test="${!empty myCar}">
	<script type="text/javascript">
		if('${myCar.viBrandName}' == '') {
			document.getElementById("top-img").src = "../images/index-logo.png";
			$("#top-img").css({
				"height": "69px",
		    	"margin": "2px"
			});
		} else {
			if('${myCar.viLogoUrl}' != ''){
				document.getElementById("top-img").src = "${myCar.viLogoUrl}";
			} else {
				document.getElementById("top-img").src = "${myCar.viBrandLogo}";
			}
		}
		if('${myCar.viBrandName}' == '') {
			document.getElementById("car-title").innerHTML = "${fn:substring(myCar.viNumber, 0, 2)}**${fn:substring(myCar.viNumber, 4, 7)}";
		} else {
			document.getElementById("car-title").innerHTML = "${myCar.viBrandName}${myCar.viSeriesName}";
		}
		
		if('${myCar.bgUrl}' != ''){
			var ImgObj = new Image(); //判断图片是否存在  
			ImgObj.src = '${myCar.bgUrl}';  
			ImgObj.onload = function (){
				$(".transparent_bg").css("background-image","url('${myCar.bgUrl}')");  
			};
		}
		//off
		$('#top-img-a').off('click');
		$('#top-img-a').on('click',function () {
			var viId1 = '${myCar.viId}';
			if(!viId1){
				//首页添加车辆信息埋点
				/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
				remoteLog.infoLog('mainpage','clicked');
				AlipayJSBridge.call('pushWindow', {
					url: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),
					param: {
			              readTitle: "YES",
			              showToolBar: "NO",
			              showProgress: "NO",
			              transparentTitle: "none"
			            }
				});
			} else {
				AlipayJSBridge.call('pushWindow', {
					url: "../car/toCarInfo?viId="+viId1+"&backUrl=portal",
					param: {
			              readTitle: "YES",
			              showToolBar: "NO",
			              showProgress: "NO",
			              transparentTitle: "none"
			            }
				});
			}
		});
			
		$('#top-span-a').off('click');
			
		$('#top-span-a').on('click',function () {
			var viId1 = '${myCar.viId}';
			if(!viId1){
				//首页添加车辆信息埋点
				/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
				remoteLog.infoLog('mainpage','clicked');
				AlipayJSBridge.call('pushWindow', {
					url: '../car/queryBrand?newCarFlag=1&r='+ Math.random(),
					param: {
			              readTitle: "YES",
			              showToolBar: "NO",
			              showProgress: "NO",
			              transparentTitle: "none"
			            }
				});
			} else {
				AlipayJSBridge.call('pushWindow', {
					url: "../car/toCarInfo?viId="+viId1+"&backUrl=portal",
					param: {
			              readTitle: "YES",
			              showToolBar: "NO",
			              showProgress: "NO",
			              transparentTitle: "none"
			            }
				});
			}
		});
	</script>
	</c:if>
	<c:if test="${empty myCar}">
	<script type="text/javascript">
		localStorage.setItem("tokenId", "first-in");
		document.getElementById("top-img").src = "../images/iconfont-jiahao.png";
		document.getElementById("car-title").innerHTML = "添加爱车";
		$('#top-img-a').off('click');
		$('#top-img-a').on('click',function () {
			//首页添加车辆信息埋点
			/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
			remoteLog.infoLog('mainpage','clicked');
			AlipayJSBridge.call('pushWindow', {
				url: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),
				param: {
		              readTitle: "YES",
		              showToolBar: "NO",
		              showProgress: "NO",
		              transparentTitle: "none"
		            }
			});
		});
		
		$('#top-span-a').off('click');
		$('#top-span-a').on('click',function () {
			//首页添加车辆信息埋点
			/* remoteLog.infoLog('${appId}','跳转添加车辆信息','CARLIFE_VW_ADDCARINFO','home_page'); */
			remoteLog.infoLog('mainpage','clicked');
			AlipayJSBridge.call('pushWindow', {
				url: '../car/queryBrand?newCarFlag=1&fromPage=home&r='+ Math.random(),
				param: {
		              readTitle: "YES",
		              showToolBar: "NO",
		              showProgress: "NO",
		              transparentTitle: "none"
		            }
			});
		});
	</script>
	</c:if>
</body>
</html>
