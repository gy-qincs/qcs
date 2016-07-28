<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>完善车辆信息</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/mui.picker.min.css?version=1.1.5.11">
<link rel="stylesheet" href="../css/cars.css?version=1.1.5.12">
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
			<a class="carui-carsDetail-Clear-a-margin" href="#">
			<c:choose>
				<c:when test="${empty vehicleModel.viLogoUrl}">
				 	<img class="mui-media-object mui-pull-left carui-carsDetail-carLogo" src="${vehicleModel.viBrandLogo}">
				</c:when>
				<c:otherwise>
					<img class="mui-media-object mui-pull-left carui-carsDetail-carLogo" src="${vehicleModel.viLogoUrl}">
				</c:otherwise>
			</c:choose>
			<div class="car-title">${vehicleModel.viBrandName}${vehicleModel.viSeriesName}</div>
			</a>			
			</li>
		</ul>
		<ul class="mui-table-view car-clear-ul-top">
			<li class="mui-table-view-cell mui-media car-mui-table-view-cell">
				<div class="mui-input-row">
					<label class="mui-pull-left carui-cardetail-number">车牌号码</label>
					<label class="car-number carui-cardetail-city" id="selectCity">
					<c:if test="${empty cityAB}">浙</c:if>
					<c:if test="${!empty cityAB}">${cityAB}</c:if>
					</label>
					<span class="car_mpecotspH carui-cardetail-mpecotspH"></span>
					<input value="${carNumber}" maxlength="6" type="text" placeholder="请输入车牌号" name="carNumber" id="carNumber" >
					<div class="carui-completion-iwjltxImg"><img src="../images/delete.gif" /></div>
					<div class="carui-completion-camera"><img src="../images/camera.png" onclick="popUpDriveLicense()"/></div>
				</div>
			</li>

			<li class="mui-table-view-cell mui-collapse"><a id="open-detail" class="car-mui-navigate-right-a" href="#">展开完善我的车辆信息</a>
				<div class="mui-collapse-content  car-clear-content-top"">
					<form class="mui-input-group" name="carInfoForm" id="carInfoForm" action="">
						<div class="selectCarSeries carui-cardetail-upkeep">爱车保养需完善以下信息（选填）</div>
						
						<c:if test="${empty cityAB}">
						<input type="hidden" name="cityAB" id="cityAB" value="浙">
						</c:if>
						<c:if test="${!empty cityAB}">
						<input type="hidden" name="cityAB" id="cityAB" value="${cityAB}">
						</c:if>
						
						<input type="hidden" name="viNumber" id="viNumber" value="${vehicleModel.viNumber}">
						<input type="hidden" name="viStartTime" id="viStartTime" value="${viStartTime}">
						<input type="hidden" name="vlCityId" id="vlCityId" value="${vehicleModel.vlCityId}">
						<input type="hidden" name="vlCityName" id="vlCityName" value="${vehicleModel.vlCityName}">
						<input type="hidden" name="sweptVolume" id="sweptVolume" value="${vehicleModel.sweptVolume}">
						<input type="hidden" name="upst" id="upst">
						<input type="hidden" name="collapseFlag" id="collapseFlag">
						<input type="hidden" name="url_res" id="url_res">
						<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul1-before">
							<li class="mui-table-view-cell" id="selectYearStyle">
								<a class="mui-navigate-right carui-carsDetail-Clear-a-margin" href="javascript:selectModel();">
									<label class="carui-cardetail-result">${vehicleModel.viModelName}</label>
									<label class="carui-cardetail-text">车型</label>
								</a>
							</li>
							<li class="mui-table-view-cell">
								<a> 
									<label class="mui-pull-left carui-cardetail-mileage-label">行驶里程</label>
									<input value="${vehicleModel.viMileage}" data-paste="false" type="tel" maxlength="6" name="viMileage" id="viMileage" placeholder="请输入当前里程数">
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
						<div class="selectCarSeries carui-cardetail-upkeep">违章查询需完善以下信息（选填）</div>
						<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul-before">
							<li class="mui-table-view-cell" id="loca">
								<a class="mui-navigate-right carui-carsDetail-Clear-a-margin" href="#"> 
									<label class="carui-cardetail-result" id="queryCity">${vehicleModel.vlCityName}</label>
									<label class="carui-cardetail-text">查询城市</label>
								</a>	
							</li>
							<li class="mui-table-view-cell"><a class="carui-cardetail-onRoad-a"> 
								<label class="mui-pull-left carui-cardetail-vehicleIdent-label">车架号</label>
								<input value="${vehicleModel.viVin}" name="viVin" id="viVin" type="text" maxlength="17" placeholder="请输入完整的车架号"> 
								<span class="mui-icon mui-icon-help-filled"></span>
							</a></li>
							<li class="mui-table-view-cell"><a class="carui-cardetail-onRoad-a"> 
								<label class="mui-pull-left carui-cardetail-vehicleIdent-label">发动机号</label>
								<input value="${vehicleModel.engineNo}" name="engineNo" id="engineNo" type="text" maxlength="30" placeholder="请输入完整的发动机号"> 
								<span class="mui-icon mui-icon-help-filled icon-line-height"></span>
							</a></li>
						</ul>
					</form>
				</div></li>
		</ul>
		<div>
			<button id='confirmBtn' type="button" class="mui-btn car-btn-save mui-btn-outlined">保存</button>
		</div>
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
				<img src="../images/help.png" class="mui-popup-text-img"/>
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
	var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
	var brand_submit_flag = '0';
	var model_submit_flag = '0';
	var submit_flag = '0';
	
	remoteLog.infoLog('carscompletion','openPage');
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
	(function(){
		var collapseFlag = '${collapseFlag}';
		if(collapseFlag == '1'){
			 $("#open-detail").hide();
			 $(".mui-collapse").css("padding-top","0px").addClass("mui-active");
		}
	})();
	function saveHidden(){
		var cityAB = $("#cityAB").val();
		var carNumber = $("#carNumber").val();
		$("#viNumber").val(encodeURI(cityAB) + carNumber);
		var vlCityName = $("#vlCityName").val();
		$("#vlCityName").val(encodeURI(vlCityName));
		
		var sweptVolume = $("#sweptVolume").val();
		$("#sweptVolume").val(encodeURI(sweptVolume));
		
		$("#upst").val('0');
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
	
	function selectYearStyle(){
		saveHidden();
		
		/** 新车修改车型进行展开标示 **/
		$("#collapseFlag").val(1);
		
		var path = '../car/queryCarCC?r=' + Math.random();
		$("#carInfoForm").attr('action', path).submit();
	}
	function selectModel(){
		if(model_submit_flag == '0'){
			saveHidden();
			
			/** 新车修改车型进行展开标示 **/
			$("#collapseFlag").val(1);
			$("#url_res").val(1);
			var path = '../car/queryCarCC?&r=' + Math.random();
			$("#carInfoForm").attr('action', path).submit();
		}
		model_submit_flag = '1';
	}
	
	/**
	 * 自定义键盘
	 * 
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
							$("#cityKeyboard").append("<div style=\"text-align: center;width:" + width / 8 + "px;\">" + res[i].name + "</div>");
						} else {
							$("#cityKeyboard").append("<div style=\"text-align: center;width:" + width / 8 + "px;\">　</div>");
						}
					}
					// 绑定单击事件回显城市简称
					$("#cityKeyboard").find("div").each(function() {
						$(this).click(function() {
							// 空白区域取消赋值
							if ($(this).text() != "　") {
								$("#cityAB").val($(this).text());
								$("#selectCity").text($(this).text());
								$("#backdrop").hide();
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
	//监听只允许输入非零开头的6位数字
	$("#viMileage").bind('input propertychange', function() {
      if ($("#viMileage").val()==0) {
    	  $("#viMileage").val('');
      }else{
    	  $("#viMileage").val($("#viMileage").val().replace(/[^0-9]/g, ""));
      }
    });
	
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
 	$("#carNumber").on('input', function() {
		var patern = /^[a-zA-Z0-9]*$/;
		var flag = patern.test($("#carNumber").val());
		if (!flag){
			$("#carNumber").val("");
		}
	});
	**/
 	// 点击遮罩层取消键盘
	$("#backdrop").click(function() {
		$("#backdrop").hide();
		$("#cityKeyboard").hide();
		$("body").css({overflow : "auto"});
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
		   			$("#open-detail").hide();
					$(".mui-collapse").css("padding-top","0px").addClass("mui-active");
		   			
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
		var cityJC = $.trim($("#selectCity").text());
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
		} else if(carNumb.value.length != 6){
			AlipayJSBridge.call('toast', {
			     content: '请填写正确的车牌号码',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if(valiCarNumber()) { 
			//已添加过该辆车
			AlipayJSBridge.call('toast', {
			     content: '已添加过该辆车',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if(carStr.value != "" && carStr.value.length != 17){
			AlipayJSBridge.call('toast', {
			     content: '请填写17位车架号',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if(!regExp.test(carNumb.value)){
			AlipayJSBridge.call('toast', {
			     content: '车牌号码格式不正确',
			     type: '',
			     duration: 2000
			}, function(){
			});
		} else if(carStr.value != "" && !regExpCarStr.test(carStr.value)){
			AlipayJSBridge.call('toast', {
			     content: '车架号格式不正确',
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
			//valiCarNumber();
			if(submit_flag == '0'){
				/* remoteLog.infoLog('${appId}','添加车辆请求','CARLIFE_REQ_ADDCARINFO','-'); */
				remoteLog.infoLog('carscompletion','clicked');
				saveHidden();
				var path = '../car/addNewCarInfo?r=' + Math.random();
				setTimeout(function() {
					ajaxSubmit();
				}, 200)
			} 
			submit_flag = '1';
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
		    url: '../car/addNewCarInfoAjax?r=' + Math.random(),
		    data: $("#carInfoForm").serialize(),
		    async: false,
		    success: function(data) {
		        if(data.success == 'true'){
					if('${backUrl}'.indexOf('portal') == -1) {
			        	AlipayJSBridge.call('popTo', {
						     index: -1
						});						
					} else {
						AlipayJSBridge.call('pushWindow', {
							url: '../car/myCarList?rand=' + Math.random()
						});	
					}
		        } else {
		        	location.replace = data.errorUrl;
		        }
		    }
		});
	}
	
	function valiCarNumber() {
		var cityAB = $("#cityAB").val();
		var carNumber = $("#carNumber").val();
		var newNumber = cityAB + carNumber.toUpperCase();
		var carList = '${carList}';
		if(carList != ''){
			var res = eval(carList);
			for(var i = 0; i < res.length; i++){
				if(res[i].viNumber == newNumber){
					return true;
				}
			}
		}
		return false;
	}
	
	$(function(){
		
		document.getElementById("open-detail").addEventListener('tap', function() {
			$("#carNumber").blur();
			$("#open-detail").hide();
		    $(".mui-collapse").css("padding-top","0px");
 			$("#showDialog").css({
 				"display":"block",
 			});
		});
		$(document).on('click','#loca',function(){
			locate();
		});
		$(document).on('click','#confirmBtn',function(){
			valiCarInfo();
		});
		
		var muiConfirmShow = false;
		function goBack() {
	
			$("#backdrop-help").hide();
	        $(".dialog").hide();
	        $("#cityKeyboard").hide();
	        $("#backdrop").hide();
	        
	        $("#backdrop-licence").hide();
	        $("#dialogL").hide();
	        
			document.getElementById("carNumber").blur();
			document.getElementById("viMileage").blur();
			document.getElementById("viVin").blur();
			document.getElementById("engineNo").blur();
			var btnArray = ['不保存', '保存'];
			if(muiConfirmShow){
				location.replace('..${backUrl}?r=' + Math.random());
		        return ;
		     }
			///$('.mui-popup-backdrop').show();
			muiConfirmShow = true;
			mui.confirm('当前信息是否保存？', '', btnArray, function(e) {
				if (e.index == 1) {
					valiCarInfo ();
				} else {
					//location.href="..${backUrl}?r=" + Math.random();
					//location.replace('..${backUrl}?r=' + Math.random());
					// popTo示例
					AlipayJSBridge.call('popTo', {

					     index: -1
					});
				}
			})
			
			//$('.mui-popup-backdrop').click(function(){
		    //    $(".mui-popup.mui-popup-in").hide();
		    //    $(this).hide();
		    //   muiConfirmShow = false;
		    //  });
		}
		$('.mui-icon-help-filled').click(function(){
			$("#backdrop-help").show();
			$(".dialog").show();
		});
		$('#close-Popup').click(function(){
			//setTimeout(closebackdrop, 500);
			closebackdrop();
		});
		/*关闭遮罩方法*/
		function closebackdrop(){
			$("#backdrop-help").hide();
			$(".dialog").hide();
		}
		
		document.addEventListener('AlipayJSBridgeReady', function () {
			loadJSAPI();
		}, false);
		function loadJSAPI() {
			// 显示标题栏
			AlipayJSBridge.call("showTitlebar");
			// 隐藏右按钮
			AlipayJSBridge.call("hideOptionMenu");
			// 设置标题
			AlipayJSBridge.call("setTitle", {
			    title: '完善车辆信息'
			});
			document.addEventListener('back', function (e) {
			     e.preventDefault();
				 goBack();
			}, false);
		}
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
//	      for(var i=1;i<=$("#text-in-num").val().length;i++){
//	        if($("#text-in-num").val().charCodeAt(i-1)>= 97&&$("#text-in-num").val().charCodeAt(i-1)<=122){
//	          $("#text-in-num").val($("#text-in-num").val().substring(0,i)+String.fromCharCode($("#text-in-num").val().charCodeAt(i-1)-32)+$("#text-in-num").val().substring(i,$("#text-in-num").val().length))
//	        }
//	      }
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