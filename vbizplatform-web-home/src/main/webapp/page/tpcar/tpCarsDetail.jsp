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
	<div class="mui-content">
		<ul class="mui-table-view car-clear-ul-top carui-completion-ul-first">
			<li class="mui-table-view-cell mui-media" id="viSeriesNameLi" style="display: none;">
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
				</div>
			</li>
			<li class="mui-table-view-cell mui-collapse mui-active car-mui-collapse-top">
				<div class="mui-collapse-content car-clear-content-top">
					<form class="mui-input-group" name="carInfoForm" id="carInfoForm" action="">
						<input type="hidden" name="viBrandName" id="viBrandName" value="${vehicleModel.viBrandName}" alt="品牌车系">
						<input type="hidden" name="viModelName" id="viModelName" value="${vehicleModel.viModelName}" alt="车型">
						<input type="hidden" name="viId" id="viId" value="${vehicleModel.viId}">
						<input type="hidden" name="cityAB" id="cityAB" value="${cityAB}">
						<input type="hidden" name="viNumber" id="viNumber" value="${vehicleModel.viNumber}">
						<input type="hidden" name="viStartTime" id="viStartTime" value="${viStartTime}" alt="上路时间">
						<input type="hidden" name="vlCityId" id="vlCityId" value="${vehicleModel.vlCityId}">
						<input type="hidden" name="vlCityName" id="vlCityName" value="${vehicleModel.vlCityName}" alt="查询城市">
						<input type="hidden" name="carYearName" id="carYearName" value="${vehicleModel.viStyleName}">
						<input type="hidden" name="sweptVolume" id="sweptVolume" value="${vehicleModel.sweptVolume}">
						<input type="hidden" name="manufacturer" id="manufacturer" value="${vehicleModel.manufacturer}">
						<input type="hidden" name="viSeriesName" id="viSeriesName" value="${vehicleModel.viSeriesName}" alt="品牌车系">
						<input type="hidden" name="upst" id="upst">
						
						<div class="selectCarSeries carui-cardetail-upkeep" id="baoyang" style="display: none;">爱车保养需完善以下信息</div>
						<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul1-before" id="baoyangDiv" style="display: none;">
							<li class="mui-table-view-cell" id="viModelNameLi" style="display: none;">
								<a class="mui-navigate-right carui-carsDetail-Clear-a-margin" href="javascript:selectModel();"> 
									<label class="carui-cardetail-result">${vehicleModel.viModelName}</label>
									<label class="carui-cardetail-text">车型</label>
								</a>
							</li>
							<li class="mui-table-view-cell" id="viMileageLi" style="display: none;">
								<a> 
									<label class="mui-pull-left carui-cardetail-mileage-label">行驶里程</label>
									<input value="${vehicleModel.viMileage}" data-paste="false" name="viMileage" id="viMileage" type="tel" maxlength="6" placeholder="请输入当前里程数" alt="行驶里程"> 
									<label class="carui-cardetail-mileage-units">km</label>
								</a>
							</li>
							<li class="mui-table-view-cell" id="viStartTimeLi" style="display: none;">
								<a id="time-pick-btn" data-options='{"type":"month"}' class="mui-navigate-right carui-carsDetail-Clear-a-margin carui-cardetail-onRoad-a" href="#"> 
									<label class="mui-pull-left carui-cardetail-onRoad-time-label">上路时间</label>
									<label id="picked-time" class="carui-cardetail-onRoad-time-result">${viStartTime}</label>
								</a>
							</li>
						</ul>
						<div class="selectCarSeries carui-cardetail-upkeep" id="weizhang" style="display: none;">违章查询需完善以下信息</div>
						<ul class="mui-table-view car-clear-ul-top carui-selectSeries-ul-before" id="weizhangDiv" style="display: none;">
							<li class="mui-table-view-cell" id="vlCityNameLi" style="display: none;">
								<a class="mui-navigate-right carui-carsDetail-Clear-a-margin" href="javascript:locate();"> 
									<label class="carui-cardetail-result" id="queryCity">${vehicleModel.vlCityName}</label> 
									<label class="carui-cardetail-text">查询城市</label>
								</a>
							</li>
							<li class="mui-table-view-cell" id="viVinLi" style="display: none;">
								<a class="carui-cardetail-onRoad-a"> 
									<label class="mui-pull-left carui-cardetail-vehicleIdent-label">车架号</label>
									<input value="${vehicleModel.viVin}" name="viVin" id="viVin" type="text" maxlength="17" placeholder="请输入完整的车架号" alt="车架号"> 
									<span class="mui-icon mui-icon-help-filled"></span>
								</a>
							</li>
							<li class="mui-table-view-cell" id="engineNoLi" style="display: none;">
								<a class="carui-cardetail-onRoad-a"> 
									<label class="mui-pull-left carui-cardetail-vehicleIdent-label">发动机号</label>
									<input value="${vehicleModel.engineNo}" name="engineNo" id="engineNo" type="text" maxlength="30" placeholder="请输入完整的发动机号" alt="发动机号"> 
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
	<script type="text/javascript" src="../js/jquery/jquery.md5.js"></script>
	<script src="../js/mui.min.js"></script>
	<script src="../js/mui.picker.all.js?version=1.2.5.11"></script>
	<script type="text/javascript">
		var brand_submit_flag = '0';
		var model_submit_flag = '0';
		var submit_flag = '0';
		var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
		window.onload = function() {
			var mustParam = '${mustParamList}';
			if(mustParam != ''){
				var mustParamList = eval(mustParam);
				for(var i = 0; i < mustParamList.length; i++){
					$("#"+mustParamList[i]).show();
				}
			}
			setTimeout(loadJSAPI, 500);
			function loadJSAPI() {
				remoteLog.infoLog('tpcarsdetail','openPage');
				// 显示右按钮
				AlipayJSBridge.call("showOptionMenu");
				// 显示标题栏
				AlipayJSBridge.call("showTitlebar");
				// 设置标题
				AlipayJSBridge.call("setTitle", {
				    title: '车辆信息'
				});
				// 设置右按钮属性
				AlipayJSBridge.call('setOptionMenu', {
				     title : '换车'
				});
				document.addEventListener('back', function (e) {
				     e.preventDefault();
					 goBack();
				}, false);
				function goBack() {
					//history.go(-1);
					AlipayJSBridge.call('closeWebview');
					//AlipayJSBridge.call('popTo', {
					//     index: -1,
					//}, function (result) {
					//});
				}
			}
			
			//换车按钮
			$(document).on("optionMenu", function(){
				var viId = '${vehicleModel.viId}';
				var path = '../tpitf/myCarList?vid='+viId+'&r=' + Math.random();
				//location.href = path;
				location.replace(path);
				//$("#carInfoForm").attr('action', path).submit();
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
		function saveHidden(){
			var cityAB = $("#cityAB").val();
			var carNumber = $("#carNumber").val();
			$("#viNumber").val(encodeURI(cityAB) + carNumber);
			var vlCityName = $("#vlCityName").val();
			if(vlCityName.indexOf('%') == -1) {
				$("#vlCityName").val(encodeURI(vlCityName));
			}
			var manufacturer = $("#manufacturer").val();
			if(manufacturer.indexOf('%') == -1) {
				$("#manufacturer").val(encodeURI(manufacturer));
			}
			$("#upst").val('1');
		}
		function selectModel(){
			if('${vehicleModel.viSeriesName}' == ''){
				AlipayJSBridge.call('toast', {
				     content: '请完善品牌车系',
				     type: 'none',
				     duration: 2000
				}, function(){
				});
			} else {
				if(model_submit_flag == '0'){
					model_submit_flag = '1';
					saveHidden();
					var path = '../tpitf/queryCarCC?r=' + Math.random();
					$("#carInfoForm").attr('action', path).submit();
				}
			}
		}
		function selectBrand(){
			if(brand_submit_flag == '0'){
				brand_submit_flag = '1';
				saveHidden();
				/** 新车修改车型进行展开标示 **/	
				var path = '../tpitf/queryBrand?r=' + Math.random();
				$("#carInfoForm").attr('action', path).submit();
			} 
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
		} else if(valiMustParam()){
			
		} else {
			if(submit_flag == '0'){
				saveHidden();
				if(valiVehicleMD5()){
					return;
				}
				AlipayJSBridge.call('showLoading', {
				     text: '保存中'
				});
				submit_flag = '1';
				/* remoteLog.infoLog('${appId}','修改车辆请求','CARLIFE_REQ_MODIFYCARINFO','-'); */
				remoteLog.infoLog('tpcarsdetail','clicked');
				setTimeout(function() {
					ajaxSubmit();
				}, 200)
			}
		}
	}
	function valiVehicleMD5(){
		var vehicleStrMd5 = '${vehicleStrMd5}';
		if(vehicleStrMd5 != '' && vehicleStrMd5 != "undefined") {
			var mustParam = '${mustParamList}';
			var vl = '';
			var vehicleWebInfo = $("#viNumber").val();
			if(mustParam != '') {
				var mustParamList = eval(mustParam);
				for(var i = 0; i < mustParamList.length; i++) {
					if(mustParamList[i].indexOf('Li') != -1) {
						vl = $("#"+mustParamList[i].substring(0, mustParamList[i].length-2));
						vehicleWebInfo += vl.val();
					}
				}
				var webMD5 = $.md5(decodeURI(vehicleWebInfo));
				//alert(decodeURI(vehicleWebInfo));
				//alert(webMD5);
				//alert(vehicleStrMd5);
				if(webMD5 == vehicleStrMd5) {
					AlipayJSBridge.call('hideLoading');
					AlipayJSBridge.call('popWindow',{
					    data: {
					    	viId: '${vehicleModel.viId}'
					    }
					});
					return true;
				} 
			}
		}
		return false;
	}
	function ajaxSubmit(){
		$.ajax({
		    type: 'post',
		    url: '../tpitf/updateCarInfoAjax?r=' + Math.random(),
		    data: $("#carInfoForm").serialize(),
		    timeout: 15000, //超时时间：15秒
		    async: false,
		    success: function(str) {
		    	AlipayJSBridge.call('hideLoading');
		        if(str.success == 'true'){
		        	$("#confirmBtn").off('click');
		        	AlipayJSBridge.call('toast', {
					     content: '车辆修改成功',
					     type: 'success',
					     duration: 1000
					}, function(){
						AlipayJSBridge.call('popWindow',{
						    data: {
						    	viId: str.viId
						    }
						});
					});
		        } else {
			    	submit_flag = '0';
					AlipayJSBridge.call('toast', {
					     content: str.errorMsg,
					     type: 'none',
					     duration: 2000
					}, function(){
					});
		        }
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		    	AlipayJSBridge.call('hideLoading');
		    	AlipayJSBridge.call('toast', {
				     content: '保存车辆信息失败,请稍候再试',
				     type: 'none',
				     duration: 3000
				}, function(){
				});
		    }
		});
	}
	function valiMustParam() {
		var tf = false;
		var mustParam = '${mustParamList}';
		var vl;
		if(mustParam != ''){
			var mustParamList = eval(mustParam);
			for(var i = 0; i < mustParamList.length; i++){
				if(mustParamList[i].indexOf('Li') != -1){
					vl = $("#"+mustParamList[i].substring(0,mustParamList[i].length-2));
					if(vl.val() == ''){
						tf = true;
						AlipayJSBridge.call('toast', {
						     content: vl.attr("alt") + '不可为空',
						     type: 'none',
						     duration: 2000
						}, function(){
						});
						break;
					}
				}
			}
		}
		return tf;
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
