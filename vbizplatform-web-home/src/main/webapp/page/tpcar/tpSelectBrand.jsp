<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>请选择品牌</title>
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/mui.indexedlist.css" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../js/jquery/jquery.loadlazy.js"></script>
<script src="https://os.alipayobjects.com/rmsportal/oknmeDPmBzRhliY.js"></script>
<script type="text/javascript" src="../js/remoteLogUtil.js"></script>
</head>
<body>
	<div class="mui-content">
		<div id='list' class="mui-indexed-list">
			<div class="mui-indexed-list-search mui-input-row mui-search carui-selectBrand-search">
				<input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索">
			</div>
		<form action="../tpitf/queryCarSeries" name="brandForm" id="brandForm">
			<div class="mui-indexed-list-bar car-indexed-list-bar-color">
				<input type="hidden" name="brandName" id="brandName">
				<input type="hidden" name="picUrl" id="picUrl">
				<input type="hidden" name="bgUrl" id="bgUrl">
				<input type="hidden" name="viId" id="viId" value="${viId}">
				<input type="hidden" name="upst" id="upst" value="${upst}">
				<input type="hidden" name="newCarFlag" id="newCarFlag" value="${newCarFlag}">
				<input type="hidden" name="fromPage" id="fromPage" value="${fromPage}">
				<a>#</a> <a>A</a> <a>B</a> <a>C</a> <a>D</a> <a>E</a> <a>F</a> <a>G</a>
				<a>H</a> <a>I</a> <a>J</a> <a>K</a> <a>L</a> <a>M</a> <a>N</a> <a>O</a>
				<a>P</a> <a>Q</a> <a>R</a> <a>S</a> <a>T</a> <a>U</a> <a>V</a> <a>W</a>
				<a>X</a> <a>Y</a> <a>Z</a>
			</div>
			<div class="mui-indexed-list-alert"></div>
			<div class="mui-indexed-list-inner" id="div1">
				<ul class="mui-table-view mui-table-view-chevron">
					<c:forEach var="item" items="${brandsMap}">
					<li data-group="${item.key}" class="mui-table-view-divider mui-indexed-list-group">${item.key}</li>
					<c:forEach var="brands" items="${item.value}">
					<li data-value="" data-tags="${brands.quanpin}" class="mui-table-view-cell mui-indexed-list-item carui-selectBrand-listLi-padd">
						<div class="mui-input-row mui-left carui-selectBrand-li-span" onclick="javascript:getCarSeries('${brands.name}','${brands.logoUrl}','${brands.backgroundUrl}');">
							<img data-original="${brands.logoUrl}" src="../images/brand_zhanwei.png" class="car-brand-img"/><span>${brands.name}</span>
						</div>
					</li>
					</c:forEach>
				</c:forEach>
				</ul>				
			</div>
		</form>
		</div>
	</div>
	<script src="../js/mui.min.js"></script>
	<script src="../js/mui.indexedlist.js"></script>
	
	<script type="text/javascript">
		var submit_flag = "0";
		var GLOBLE_SESSIONID = '${GLOBLE_SESSIONID}';
		$(function(){
			$("img").lazyload({
				effect : "fadeIn"
			});
			setTimeout(loadJSAPI, 100);
			function loadJSAPI() {
				remoteLog.infoLog('tpselectbrand','openPage');
				// 显示标题栏
				AlipayJSBridge.call("showTitlebar");
				// 隐藏右按钮
				AlipayJSBridge.call("hideOptionMenu");
				// 设置标题
				AlipayJSBridge.call("setTitle", {
				    title: '请选择品牌'
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
		mui.init();
		/***/
		mui.ready(function() {
			var list = document.getElementById('list');
			//calc hieght
			list.style.height = (document.body.offsetHeight) + 'px';
			//create
			window.indexedList = new mui.IndexedList(list);
		});
		
		// 选择品牌跳转
		function getCarSeries(brandName, picUrl, backgroundUrl){
			if(submit_flag == '0'){
				//修改车辆信息
				if($("#upst").val()=='1'){
					/* remoteLog.infoLog('${appId}','修改车辆请求','CARLIFE_REQ_QUERYBRAND','MODIFYCARINFO'); */
					remoteLog.infoLog('tpselectbrand','clicked');
				} else {
					/* remoteLog.infoLog('${appId}','添加车辆请求','CARLIFE_REQ_QUERYBRAND','ADDCARINFO'); */
					remoteLog.infoLog('tpselectbrand','clicked');
				}			
				$("#brandName").val(encodeURI(brandName));
				$("#picUrl").val(encodeURI(picUrl));
				$("#bgUrl").val(encodeURI(backgroundUrl));
				$("#brandForm").submit();
			}
			submit_flag = '1';
		}
	</script>
</body>
</html>
