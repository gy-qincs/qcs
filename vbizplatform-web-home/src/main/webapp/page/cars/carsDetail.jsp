<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车辆信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/mui.picker.min.css?version=1.1.5.11">
<link rel="stylesheet" href="../css/cars.css?version=1.1.5.11">
<link rel="stylesheet" href="../css/app.css">
<script src="https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js"></script>
<script type="text/javascript" src="../js/remoteLogUtil.js"></script>
</head>
<body>
		<form action="distinguish" id="distinguish" name="distinguish">
			<input type="hidden" id="base64Content" name="base64Content" value=""/>
		</form>
	
	<div class="mui-content">
		<ul class="mui-table-view car-clear-ul-top carui-completion-ul-first">
			<li class="mui-table-view-cell mui-media">
			<a class="mui-navigate-right" href="javascript:selectBrand();">
			<c:choose>
			    <c:when test="${empty vehicleModel.viBrandName}">

					<img class="mui-media-object mui-pull-left carui-carsDetail-carLogo" src="../images/viBrandDefault.png">

					<div class="car-title">品牌车系</div>
			    </c:when>
			    <c:otherwise>
			        <c:choose>
						<c:when test="${empty vehicleModel.viLogoUrl}">
						 	<img class="mui-media-object mui-pull-left carui-carsDetail-carLogo" src="${vehicleModel.viBrandLogo}">
						</c:when>
						<c:otherwise>
							<img class="mui-media-object mui-pull-left carui-carsDetail-carLogo" src="${vehicleModel.viLogoUrl}">
						</c:otherwise>
					</c:choose>
					<div class="car-title">${vehicleModel.viBrandName}${vehicleModel.viSeriesName}</div>
			    </c:otherwise>
			</c:choose>
			</a>
			</li>
		</ul>
		<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul-before">
			<li class="mui-table-view-cell mui-media car-mui-table-view-cell">
				<div class="mui-input-row">
					<label class="mui-pull-left carui-cardetail-number">车牌号码</label>
					<label class="car-number carui-cardetail-city" id="selectCity">${cityAB}</label>
					<span class="car_mpecotspH carui-cardetail-mpecotspH"></span> 
					<input value="${carNumber}" maxlength="6" type="text" placeholder="请输入车牌号" name="carNumber" id="carNumber">
					<div class="carui-completion-iwjltxImg"><img src="../images/delete.gif" /></div>
					<div class="carui-completion-camera"><img src="../images/camera.png" onclick="popUpDriveLicense()"/></div>
				</div>
			</li>
			<li class="mui-table-view-cell mui-collapse mui-active car-mui-collapse-top">
				<div class="mui-collapse-content car-clear-content-top">
					<form class="mui-input-group" name="carInfoForm" id="carInfoForm" action="">
						<input type="hidden" name="viId" id="viId" value="${vehicleModel.viId}">
						<input type="hidden" name="cityAB" id="cityAB" value="${cityAB}">
						<input type="hidden" name="viNumber" id="viNumber" value="${vehicleModel.viNumber}">
						<input type="hidden" name="viStartTime" id="viStartTime" value="${viStartTime}">
						<input type="hidden" name="vlCityId" id="vlCityId" value="${vehicleModel.vlCityId}">
						<input type="hidden" name="vlCityName" id="vlCityName" value="${vehicleModel.vlCityName}">
						<input type="hidden" name="carYearName" id="carYearName" value="${vehicleModel.viStyleName}">
						<input type="hidden" name="sweptVolume" id="sweptVolume" value="${vehicleModel.sweptVolume}">
						<input type="hidden" name="manufacturer" id="manufacturer" value="${vehicleModel.manufacturer}">
						<input type="hidden" name="viSeriesName" id="viSeriesName" value="${vehicleModel.viSeriesName}">
						<input type="hidden" name="upst" id="upst">
						
						<div class="selectCarSeries carui-cardetail-upkeep">爱车保养需完善以下信息</div>
						<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul1-before">
							<li class="mui-table-view-cell">
								<a class="mui-navigate-right carui-carsDetail-Clear-a-margin" href="javascript:selectModel();"> 
									<label class="carui-cardetail-result">${vehicleModel.viModelName}</label>
									<label class="carui-cardetail-text">车型</label>
								</a>
							</li>
							<li class="mui-table-view-cell">
								<a> 
									<label class="mui-pull-left carui-cardetail-mileage-label">行驶里程</label>
									<input value="${vehicleModel.viMileage}" data-paste="false" name="viMileage" id="viMileage" type="tel" maxlength="6" placeholder="请输入当前里程数"> 
									<label class="carui-cardetail-mileage-units">km</label>
								</a>
							</li>
							<li class="mui-table-view-cell">
								<a id="time-pick-btn" data-options='{"type":"month"}' class="mui-navigate-right carui-carsDetail-Clear-a-margin carui-cardetail-onRoad-a" href="#"> 
									<label class="mui-pull-left carui-cardetail-onRoad-time-label">上路时间</label>
									<label id="picked-time" class="carui-cardetail-onRoad-time-result">${viStartTime}</label>
								</a>
							</li>
						</ul>
						<div class="selectCarSeries carui-cardetail-upkeep">违章查询需完善以下信息</div>
						<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul-before">
							<li class="mui-table-view-cell">
								<a class="mui-navigate-right carui-carsDetail-Clear-a-margin" href="javascript:locate();"> 
									<label class="carui-cardetail-result" id="queryCity">${vehicleModel.vlCityName}</label> 
									<label class="carui-cardetail-text">查询城市</label>
								</a>
							</li>
							<li class="mui-table-view-cell">
								<a class="carui-cardetail-onRoad-a"> 
									<label class="mui-pull-left carui-cardetail-vehicleIdent-label">车架号</label>
									<input value="${vehicleModel.viVin}" name="viVin" id="viVin" type="text" maxlength="17" placeholder="请输入完整的车架号"> 
									<span class="mui-icon mui-icon-help-filled"></span>
								</a>
							</li>
							<li class="mui-table-view-cell">
								<a class="carui-cardetail-onRoad-a"> 
									<label class="mui-pull-left carui-cardetail-vehicleIdent-label">发动机号</label>
									<input value="${vehicleModel.engineNo}" name="engineNo" id="engineNo" type="text" maxlength="30" placeholder="请输入完整的发动机号"> 
									<span class="mui-icon mui-icon-help-filled icon-line-height"></span>
								</a>
							</li>
						</ul>
					</form>
				</div></li>
		</ul>
		<div>
			<button id='confirmBtn' type="button" class="mui-btn car-btn-save mui-btn-outlined">保存</button>
		</div>
	</div>
	<div id="delete-sheet" class="mui-popover mui-popover-action mui-popover-bottom carui-cardetail-delete-sheet">
		<ul class="mui-table-view carui-cardetail-delete">
			<li class="mui-table-view-cell car-mui-navigate-right-title">
				你要删除这辆爱车的信息吗？</li>
			<li class="mui-table-view-cell carui-cardetail-li-delete">
				<a href="javascript:delThisCar();">删除</a>
			</li>
		</ul>
		<ul class="mui-table-view carui-cardetail-delete carui-cardetail-delete-last">
			<li class="mui-table-view-cell carui-cardetail-li-cancel" style="background-color: #ffffff;">
				<a >取消</a>
			</li>
		</ul>
	</div>
	
	<div class="mui-popup mui-popup-in dialogL" id="dialogL">
		      <div class="mui-popup-inner">
		        <a class="mui-icon mui-pull-left carui-sign-a-close" id="car-close-span-lice">
		          <span class="car-close-span-lice"></span>
		        </a>
		        <div class="mui-popup-text">
		          <img class="mui-popup-text-img" src="../images/driveLicense.png"/>
		        </div>
		        <div class="mui-popup-text">
		         		 自动获取行驶证相关信息
		        </div>
		        <div class="mui-popup-text tips">
					          调整拍摄角度，避免反光影响，<br>
					          确保图像清晰，可以提高识别正确率。
		        </div>
		        <button class="mui-popup-button" id="get-PopupL">获取</button>
		      </div>
		    </div>
		    <div class="mui-backdrop" id="backdrop-licence"></div>
	
	<!--提示内容-->
	<div class="mui-popup mui-popup-in dialog">
		<div class="mui-popup-inner">
			<div class="mui-popup-text">
				<img class="mui-popup-text-img" src="../images/help.png"/>
			</div>
		</div>
		<div class="mui-popup-buttons">
			<span class="mui-popup-button" id="close-Popup">确定</span>
		</div>
	</div>
	
	<!--遮罩层帮助-->
	<div class="mui-backdrop" id="backdrop-help"></div>
	<!-- 选择城市键盘 -->
	<div id="cityKeyboard" class="cityKeyboard"></div>
	<div class="mui-backdrop" id="backdrop"></div>
	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script src="../js/mui.min.js"></script>
	<script src="../js/mui.picker.all.js?version=1.2.5.11"></script>
	<script type="text/javascript">
		var brand_submit_flag = '0';
		var model_submit_flag = '0';
		var submit_flag = '0';
		var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
		window.onload = function() {
			/**
			*/
			setTimeout(loadJSAPI, 500);
			/**
			document.addEventListener('AlipayJSBridgeReady', function () {
				document.addEventListener('back', function (e) {
				     e.preventDefault();
				     AlipayJSBridge.call('popTo', {

					     index: -1
					});
				}, false);
			}, false);
			**/
			function loadJSAPI() {
				remoteLog.infoLog('carsdetail','openPage');
				// 显示标题栏
				AlipayJSBridge.call("showTitlebar");
				// 显示右按钮
				AlipayJSBridge.call("showOptionMenu");
				// 设置标题
				AlipayJSBridge.call("setTitle", {
				    title: '车辆信息'
				});
				// 设置右按钮属性
				AlipayJSBridge.call('setOptionMenu', {
				     title : '删除'
				});
				document.addEventListener('back', function (e) {
				     e.preventDefault();
					 goBack();
				}, false);
				
				function goBack() {

					/*var backUrl = ${backUrl};
					alert(backUrl);
					var backCode = 0;
					if(backUrl == '/car/myCarList'){
						backCode = -1;
					}
					alert(backCode);
					*/
					// popTo示例
					AlipayJSBridge.call('popTo', {

					     index: -1
					});
				}
			}
			
			$(document).on("optionMenu", function(){
				$("#carNumber").blur();
				$("#viMileage").blur();
				$("#viVin").blur();
				$("#engineNo").blur();
				$("#delete-sheet").addClass("mui-active").show();
				$("#backdrop").show();
			});
			$(".mui-icon-help-filled").click(function(){
				$("#backdrop-help").show();
				$(".dialog").show();
			});
			$('#close-Popup').click(function(){
				setTimeout(closebackdrop, 100);
				//closebackdrop();
			});
			$(".carui-cardetail-li-cancel").click(function(){
				$("#delete-sheet").removeClass("mui-active").hide();
				$("#backdrop").hide();
			});
			/*关闭遮罩方法*/
			function closebackdrop(){
				$("#backdrop-help").hide();
				$(".dialog").hide();
			}
		}
		function valiCarNumber() {
			var cityAB = $("#cityAB").val();
			var carNumber = $("#carNumber").val();
			var newNumber = cityAB + carNumber.toUpperCase();
			var carList = '${carList}';
			var viId = '${vehicleModel.viId}';
			if(carList != ''){
				var res = eval(carList);
				for(var i = 0; i < res.length; i++){
					if(res[i].viNumber == newNumber && res[i].viId != viId){
						return true;
					}
				}
			}
			return false;
		}
		function delThisCar(){
			var vurId = '${vehicleModel.vurId}';
			var viId = '${vehicleModel.viId}';
			var vurGmtModified = '${vehicleModel.vurGmtModified}';
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
						//location.href = "..${backUrl}?r=" + Math.random();
						AlipayJSBridge.call('popTo', {
						     index: -1
						});
					} else {
						AlipayJSBridge.call('toast', {
						     content: '网络忙，请稍候再试',
						     type: '',
						     duration: 2000
						}, function(){
						});
					}
				}
			});
		}
		function saveHidden(){
			var cityAB = $("#cityAB").val();
			var carNumber = $("#carNumber").val();
			$("#viNumber").val(encodeURI(cityAB) + carNumber);
			var vlCityName = $("#vlCityName").val();
			$("#vlCityName").val(encodeURI(vlCityName));
			var carYearName = $("#carYearName").val();
			$("#carYearName").val(encodeURI(carYearName))
			var manufacturer = $("#manufacturer").val();
			$("#manufacturer").val(encodeURI(manufacturer));
			
			//var sweptVolume = $("#sweptVolume").val();
			//$("#sweptVolume").val(encodeURI(sweptVolume));
			$("#upst").val('1');
		}
		function selectYearStyle(){
			saveHidden();
			var path = '../car/queryCarCC?r=' + Math.random();
			$("#carInfoForm").attr('action', path).submit();
		}
		function selectModel(){
			if('${vehicleModel.viSeriesName}' == ''){
				AlipayJSBridge.call('toast', {
				     content: '请完善品牌车系',
				     type: '',
				     duration: 2000
				}, function(){
				});
			} else {
				if(model_submit_flag == '0'){
					saveHidden();
					var path = '../car/queryCarCC?r=' + Math.random();
					$("#carInfoForm").attr('action', path).submit();
				}
				model_submit_flag = '1';
			}
		}
		function selectBrand(){
			if(brand_submit_flag == '0'){
				saveHidden();
				/** 新车修改车型进行展开标示 **/	
				var path = '../car/queryBrand?r=' + Math.random();
				$("#carInfoForm").attr('action', path).submit();
			} 
			brand_submit_flag = '1';
		}
		//监听只允许输入非零开头的6位数字
		$("#viMileage").bind('input propertychange', function() {
	      if ($("#viMileage").val()==0) {
	    	  $("#viMileage").val('');
	      }else{
	    	  $("#viMileage").val($("#viMileage").val().replace(/[^0-9]/g, ""));
	      }
	    });
	
		//
		$("#viVin").keypress(function(event) {
			var pos = $(this).getCursorPosition();
		      if ($(this).val().length < 17) {//限制输入字符串长度
		        var key = event.which;
		        if (key >= 97 && key <= 122) {//找到输入是小写字母的ascII码的范围
		          event.preventDefault();//取消事件的默认行为
		          if(pos!=$(this).val().length){
		            $(this).val($(this).val().substring(0,pos) + String.fromCharCode(key - 32) +$(this).val().substring(pos,$(this).val().length));
		          } else {
		            $(this).val($(this).val() + String.fromCharCode(key - 32));
		          }
		          $(this).setCursorPosition(pos+1);
		        }else if(key>=48&&key<=57){
		          event.preventDefault();//取消事件的默认行为
		          if(pos!=$(this).val().length){
		            $(this).val($(this).val().substring(0,pos) + String.fromCharCode(key) +$(this).val().substring(pos,$(this).val().length));
		          } else {
		            $(this).val($(this).val() + String.fromCharCode(key));
		          }
		          $(this).setCursorPosition(pos+1);
		        }else if(!(key>=48&&key<=57||key>=65&&key<=90)){//其余除大写字母和数字外禁用默认点击事件
		          event.preventDefault();//取消事件的默认行为
		        }
		      }else{
		        event.preventDefault();//取消事件的默认行为
		      }
		 });
		$('#viVin').bind('input propertychange', function() {
			checkInputModel('viVin',17);
		});  
		
		$("#engineNo").keypress(function(event) {
			var pos = $(this).getCursorPosition();
		      if ($(this).val().length < 30) {//限制输入字符串长度
		        var key = event.which;
		        if (key >= 97 && key <= 122) {//找到输入是小写字母的ascII码的范围
		          event.preventDefault();//取消事件的默认行为
		          if(pos!=$(this).val().length){
		            $(this).val($(this).val().substring(0,pos) + String.fromCharCode(key - 32) +$(this).val().substring(pos,$(this).val().length));
		          } else {
		            $(this).val($(this).val() + String.fromCharCode(key - 32));
		          }
		          $(this).setCursorPosition(pos+1);
		        }else if(key>=48&&key<=57){
		          event.preventDefault();//取消事件的默认行为
		          if(pos!=$(this).val().length){
		            $(this).val($(this).val().substring(0,pos) + String.fromCharCode(key) +$(this).val().substring(pos,$(this).val().length));
		          } else {
		            $(this).val($(this).val() + String.fromCharCode(key));
		          }
		          $(this).setCursorPosition(pos+1);
		        }else if(!(key>=48&&key<=57||key>=65&&key<=90||key==42||key==35||key==95||key==45)){//其余除大写字母和数字外禁用默认点击事件
		          event.preventDefault();//取消事件的默认行为
		        }
		      }else{
		        event.preventDefault();//取消事件的默认行为
		      }
		 });

		$('#engineNo').bind('input propertychange', function() {
			checkInputModel('engineNo',30);
		});
		
		
	    $("#carNumber").keypress(function(event) {//数字键盘、字母键盘、拼音键盘
	    	if($(".carui-completion-iwjltxImg").css("display")=="none"){
	            $(".carui-completion-iwjltxImg").css("display","block");
	        }
		    var pos = $(this).getCursorPosition();
		    if ($(this).val().length < 6) {//限制输入字符串长度
		      var key = event.which;
		      if (key >= 97 && key <= 122) {//找到输入是小写字母的ascII码的范围
		          event.preventDefault();//取消事件的默认行为
		          if(pos!=$(this).val().length){
		            $(this).val($(this).val().substring(0,pos) + String.fromCharCode(key - 32) +$(this).val().substring(pos,$(this).val().length));
		          } else {
		            $(this).val($(this).val() + String.fromCharCode(key - 32));
		          }
		          $(this).setCursorPosition(pos+1);
		        }else if(key>=48&&key<=57){
		          event.preventDefault();//取消事件的默认行为
		          if(pos!=$(this).val().length){
		            $(this).val($(this).val().substring(0,pos) + String.fromCharCode(key) +$(this).val().substring(pos,$(this).val().length));
		          } else {
		            $(this).val($(this).val() + String.fromCharCode(key));
		          }
		          $(this).setCursorPosition(pos+1);
		        }else if(!(key>=48&&key<=57||key>=65&&key<=90)){//其余除大写字母和数字外禁用默认点击事件
		          event.preventDefault();//取消事件的默认行为
		        }
	        }else{
	          event.preventDefault();//取消事件的默认行为
	        }
		});

		$('#carNumber').bind('input propertychange', function() {
			checkInputModel('carNumber',6);
		});
		
		/**
		$("#carNumber").on('input',function(){
			var patern = /^[a-zA-Z0-9]*$/;
			var flag = patern.test($("#carNumber").val());
			if(!flag)$("#carNumber").val("");
		});
		**/
		$("#backdrop").click(function(){
			$("#backdrop").hide();
			$("#delete-sheet").hide();
			$("#cityKeyboard").hide();
			// 允许滚动
			$("body").css({overflow:"auto"});
		});
		mui.init();
		(function($) {
			//changeCar
			var result = $('#picked-time')[0];
			document.getElementById("time-pick-btn").addEventListener('tap', function() {
				$("#carNumber")[0].blur();
				$("#viMileage")[0].blur();
				$("#viVin")[0].blur();
				$("#engineNo")[0].blur();
				var optionsJson = this.getAttribute('data-options') || '{}';
				var options = JSON.parse(optionsJson);
				var id = this.getAttribute('id');
				var picker = new $.DtPicker(options);
				picker.show(function(rs) {
					result.innerText = rs.value;
					document.getElementById('viStartTime').value = rs.value;
					picker.dispose();
				});
			}, false);
		})(mui);

		/**
		 * 自定义键盘
		 * @author zhaocj@19pay.com.cn
		 * @time 2016-04-01 17:34:53
		 */
		$("#selectCity").click(function() {
			// 禁止滚动
			$("body").css({overflow : "hidden"});
			// 显示遮罩层和键盘
			$("#cityKeyboard").show();
			$("#backdrop").show();
			// 判断是否已有键盘
			if ($("#cityKeyboard").html() == "") {
				// 获取城市数据
				$.ajax({
					type : "get",
					url : "../js/json/city.json",
					async : true,
					success : function(data) {
						var res = eval(data);
						var width = $(window).width();
						// 每行定义8个键盘
						// 空格数
						var space = 8 - data.length % 8;
						var le = res.length + space;
						for ( var i = 0; i < le; i++) {
							if (i + space < le) {
								$("#cityKeyboard").append("<div style=\"text-align: center;width:"+width/8+"px;\">"+ res[i].name+ "</div>");
							} else {
								$("#cityKeyboard").append("<div style=\"text-align: center;width:"+width/8+"px;\">　</div>");
							}
						}
						// 绑定单击事件回显城市简称
						$("#cityKeyboard").find("div").each(function() {
							$(this).click(function() {
								// 空白区域取消赋值
								if ($(this).text() != "　") {
									$("#cityAB").val($(this).text());
									$("#selectCity").text($(this).text());
									$("#backdrop-delete").hide();
									$("#delete-sheet").hide();
									$("#backdrop").hide();
									$("#delete-sheet").hide();
									$("#cityKeyboard").hide();
									// 允许滚动
									$("body").css({overflow:"auto"});
								}
							});
						});
					}
				});
			}
		});
	

		function closeBackdropLicence() {
			var backdrop = document.getElementById("dialogL");
			backdrop.style.display = "none";
			$("#backdrop-licence").hide();
		}
		$("#backdrop-licence").click(function(){
			closeBackdropLicence();
		});
		$("#car-close-span-lice").click(function(){
			closeBackdropLicence();
		});
		function popUpDriveLicense(){
			$(".dialogL").show();
			$("#backdrop-licence").show();
		}
		$("#get-PopupL").click(function(){
			getVehicleDrivingLicenseImg();
		});
		function getVehicleDrivingLicenseImg(){
			AlipayJSBridge.call('photo', {
				  dataType: 'dataURL',
				  imageFormat: 'jpg',
				  quality: 75,
				  maxWidth: 1000,
				  maxHeight: 512,
				  allowEdit: false,
				  upload:false // 目前只支持服务窗内使用
				}, function (result) {
					if(result.error != '10' && result.error != '11'){
						$("#base64Content").val(result.dataURL);
						postDistinguishData();
					}else{
						if(result.error == '11'){
							AlipayJSBridge.call('toast', {
							    content: '无法使用相机,权限不足',
							    type: '',
							    duration: 3000
							});
						}
					}
		   			
				});
			
		}
		
		function postDistinguishData(){
			closeBackdropLicence();
			AlipayJSBridge.call('showLoading', {
			  text: '',
			  delay: 100
			});
			$.ajax({
			   	type: "POST",
			   	url: "distinguish",
			   	data: $("#distinguish").serialize(),
			   	dataType:"json",
				timeout : 60000,
			   	async:true,
			   	cache:false,
			   	success: function(data){
			   		var obj = eval(data);
			   		AlipayJSBridge.call('hideLoading');
			   		if(obj.code == "00000"){   //返回成功
			   			
			   			var resData = JSON.parse(obj.res);
			   			//alert(resData.carNumber);
			   			//alert(resData.mainVin);
			   			//alert(resData.mainEngineNo);
			   			if(resData.hasOwnProperty("carNumber")){
			   				//alert(resData.carNumber.substr(1,1)+'---carNumber');
			   				//alert(resData.carNumber.substr(2,5)+'---carNumber');
			   				$("#selectCity").html(resData.carNumber.substr(0,1));
			   				$("#carNumber").val(resData.carNumber.substr(1,6));
			   				
			   				$("#cityAB").val(resData.carNumber.substr(0,1));
			   				$("#viNumber").val(resData.carNumber.substr(1,6));
			   			}
			   			if(resData.hasOwnProperty("mainEngineNo")){
			   				//alert(resData.mainEngineNo+'---mainEngineNo');
			   				$("#engineNo").val(resData.mainEngineNo);
			   			}
			   			if(resData.hasOwnProperty("mainVin")){
			   				//alert(resData.mainVin+'---mainVin');
			   				$("#viVin").val(resData.mainVin);
			   			}
						
						
						
			   		}else{
			   			AlipayJSBridge.call('toast', {
						    content: obj.errorMsg,
						    type: '',
						    duration: 3000
						});
			   		}
			   	},
			   	error:function(data){
			   		AlipayJSBridge.call('hideLoading');
			   		AlipayJSBridge.call('toast', {
					    content: '网络忙请稍候再试',
					    type: '',
					    duration: 3000
					});
			   	}
			   	
			});
			
		}	
		
	// 选择城市
	function locate() {
		AlipayJSBridge.call('getCities', {
			//currentCity : '北京',
			//adcode : '110100'
		}, function(result) {
			if (result.city != '') {
				$("#queryCity").text(result.city);
				$("#vlCityId").val(result.adcode);
				$("#vlCityName").val(result.city);
			}
		});
	}
	function valiCarInfo (){
		var carNumb = document.getElementById("carNumber");
		var carMo = document.getElementById("engineNo");
		var carStr = document.getElementById("viVin");
		var engineNo = document.getElementById("engineNo");
		var cityJC = $("#selectCity").text();
		var regExp = /^[a-zA-Z]{1}[a-zA-Z0-9]{5}$/;
		var regExpCarStr = /^[a-zA-Z0-9]{17}$/;
		var engineNoExp = /^[a-zA-Z0-9\-_*#]{4,30}$/;
		if(carNumb.value == ""){
			AlipayJSBridge.call('toast', {
			     content: '请填写正确的车牌号码',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if (carNumb.value.length != 6){
			AlipayJSBridge.call('toast', {
			     content: '请填写正确的车牌号码',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if (valiCarNumber()) {
			//已添加过该辆车
			AlipayJSBridge.call('toast', {
			     content: '已添加过该辆车',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if (carStr.value != "" && carStr.value.length != 17){
			AlipayJSBridge.call('toast', {
			     content: '请填写正确的17位车架号',
			     type: '',
			     duration: 2000
			}, function(){
			});
		}  else if(!regExp.test(carNumb.value)){
			AlipayJSBridge.call('toast', {
			     content: '车牌号码格式不正确',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if(carStr.value != "" && !regExpCarStr.test(carStr.value)){
			AlipayJSBridge.call('toast', {
			     content: '请填写正确的17位车架号',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if(engineNo.value != "" && !engineNoExp.test(engineNo.value)){
			AlipayJSBridge.call('toast', {
			     content: '发动机号格式不正确',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if(!checkCityJC(cityJC)){

		} else {
			if('${vehicleModel.viSeriesName}' == ''){
				AlipayJSBridge.call('toast', {
				     content: '请完善品牌车系',
				     type: '',
				     duration: 2000
				}, function(){
				});
			} else {
				/* remoteLog.infoLog('${appId}','修改车辆请求','CARLIFE_REQ_MODIFYCARINFO','-'); */
				remoteLog.infoLog('carsdetail','clicked');
				saveHidden();
				var path = '../car/updateCarInfo?r=' + Math.random(); 
				setTimeout(function() {
					ajaxSubmit();
				}, 200);
				$("#confirmBtn").off('click');				
			}
		}
	}
	function checkCityJC(cityJC){
        //alert(cityJC);
        var checkRes = false;
        $.ajax({
          type:"get",
          url:"./../js/json/city.json",
          async: false,
          success: function(data){
            var res = eval(data);
            var le = res.length;
            for ( var i = 0; i < le; i++) {
              if(cityJC == res[i].name){
                //alert('true');
                checkRes = true;
                break;
              }
            }
            if(!checkRes){
                AlipayJSBridge.call('toast', {
                         content: '车牌号码格式不正确',
                         type: '',
                         duration: 3000
                }, function(){
                });
              }
          },
          error:function(data){
              AlipayJSBridge.call('toast', {
               content: '网络忙请稍候再试',
               type: '',
               duration: 3000
           	  });
           }
        });
        return checkRes;
      }
	
	function ajaxSubmit(){
		$.ajax({
		    type: 'post',
		    url: '../car/updateCarInfoAjax?r=' + Math.random(),
		    data: $("#carInfoForm").serialize(),
		    async: false,
		    success: function(data) {
		        if(data.success == 'true'){
		        	AlipayJSBridge.call('popTo', {
					     index: -1
					});
		        } else {
		        	location.replace = data.errorUrl;
		        }
		    }
		});
	}
	
	$("#confirmBtn").on('click', function() {
		valiCarInfo();
	});

    $(".carui-completion-iwjltxImg").click(function(){
        $(this).hide();
        $("#carNumber").val("");
      });
      //监听输入出现删除 icon
      $(document).on('input','#carNumber',function(){
        if($(".carui-completion-iwjltxImg").css("display")=="none"){
          $(".carui-completion-iwjltxImg").css("display","block");
        }
        if($("#carNumber").val().length==0){
          $(".carui-completion-iwjltxImg").hide();
        }
      });
      $("#backdrop-help").click(function(){
          $("#backdrop-help").hide();
          $(".dialog").hide();
      });
      function checkInputModel(id,length){
  		if($('#'+id).val().length>length){
  			$('#'+id).val($('#'+id).val().substr(0,length));
  		}
  		var ua = navigator.userAgent.toLowerCase();  
  	    if (/iph|ipad|ipod/.test(ua)) {//暂不使用
//  	      for(var i=1;i<=$("#text-in-num").val().length;i++){
//  	        if($("#text-in-num").val().charCodeAt(i-1)>= 97&&$("#text-in-num").val().charCodeAt(i-1)<=122){
//  	          $("#text-in-num").val($("#text-in-num").val().substring(0,i)+String.fromCharCode($("#text-in-num").val().charCodeAt(i-1)-32)+$("#text-in-num").val().substring(i,$("#text-in-num").val().length))
//  	        }
//  	      }
  	    } else if (/android/.test(ua)) {
  	      var pos = $('#'+id).getCursorPosition();
  	      $('#'+id).val($('#'+id).val().toUpperCase());
  	      $('#'+id).setCursorPosition(pos);
  	    }
      }
      $.fn.setCursorPosition = function(position){
	      if(this.lengh == 0) return this;
	      return $(this).setSelection(position, position);
	  }

	  $.fn.setSelection = function(selectionStart, selectionEnd) {
	      if(this.lengh == 0) return this;
	      input = this[0];
	    
	      if (input.createTextRange) {
	          var range = input.createTextRange();
	          range.collapse(true);
	          range.moveEnd('character', selectionEnd);
	          range.moveStart('character', selectionStart);
	          range.select();
	      } else if (input.setSelectionRange) {
	          input.focus();
	          input.setSelectionRange(selectionStart, selectionEnd);
	      }
	    
	      return this;
	  }
	    
	  $.fn.focusEnd = function(){
	      this.setCursorPosition(this.val().length);
	  }
	    
	  $.fn.getCursorPosition = function() {
	      var el = $(this).get(0);
	      var pos = 0;
	      if ('selectionStart' in el) {
	          pos = el.selectionStart;
	      } else if ('selection' in document) {
	          el.focus();
	          var Sel = document.selection.createRange();
	          var SelLength = document.selection.createRange().text.length;
	          Sel.moveStart('character', -el.value.length);
	          pos = Sel.text.length - SelLength;
	      }
	      return pos;
	  }
	</script>
</body>
</html>
