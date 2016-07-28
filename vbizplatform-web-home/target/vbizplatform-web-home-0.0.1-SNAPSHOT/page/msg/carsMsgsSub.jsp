<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>服务消息</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/app.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mui.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/cars.css">
<style>
.title {
	margin: 20px 15px 10px;
	color: #6d6d72;
	font-size: 15px;
}
</style>
</head>
<body>
	<div class="mui-content">
		<!--下拉刷新容器-->
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
			<div class="mui-scroll">
				<!--数据列表-->
				<div class="car_msgscot">
					<ul id="OA_task_1" class="mui-table-view mui-table-view-chevron">
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<%=basePath%>js/mui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/cars.js"></script>
<script>
	mui.init({
		pullRefresh : {
			container : '#pullrefresh',
			down : {
				callback : pulldownRefresh
			},
			up : {
				contentrefresh : '正在加载...',
				callback : pullupRefresh
			}
		}
	});
	
	/**
	 * 下拉刷新具体业务实现
	 */
	function pulldownRefresh() {
		setTimeout(function() {
			var table = document.body.querySelector('.mui-table-view');
			var cells = document.body.querySelectorAll('.mui-table-view-cell');
			table.innerHTML = '';
			var msgsize = '${fn:length(messageList)}';
			
//			for (var i = cells.length, len = i + 3; i < len; i++) {
			<c:forEach var="item" items="${messageList}">
				var li = document.createElement('li');
				li.className = 'mui-table-view-cell mui-media';
				li.innerHTML = '<div class="mui-slider-right mui-disabled" msgid="${item.messageId}" castScope="${item.broadcastScope}">'
						+ '<a class="mui-btn mui-btn-red car-pull-left-delete">删除</a>'
						+ '</div>'
						+ '<div class="mui-input-row mui-radio mui-left mui-slider-handle" id="${item.messageId}" url="${item.urlLink}" read="${item.isRead}" scope="${item.broadcastScope}">'
						+ '<div class="cars-content">'
						+ '<div class="mui-media-object mui-pull-left" style="margin-left:0px;width: 42px;height: 42px;padding-left: 0px;border-radius: 20px;background: #ccc;">'
						<c:if test="${item.isRead == 'NO'}">
						+ '<span class="mui-badge mui-badge-warning" style="margin-bottom: 12px;margin-left: 35px;padding: 4px 4px;background-color: red;"></span>'
						</c:if>
						+ '</div>'
						+ '<div class="mui-media-body">'
						+ "${item.messageType}"
						+ '<font style="color: #000000;float:right;font-size: 13px;">' 
						+ "${item.createTime}" 
						+ '</font>'
						+ '<p class="mui-ellipsis">'
						+ "${item.title}" 
						+ '</p>'
						+ '</div></div></div>';
				//下拉刷新，新纪录插到最前面；
				table.insertBefore(li, table.firstChild);
			</c:forEach>	
//			}
			mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
		}, 1500);
	}
	var count = 0;
	/**
	 * 上拉加载具体业务实现
	 */
	function pullupRefresh() {
		setTimeout(function() {
			mui('#pullrefresh').pullRefresh().endPullupToRefresh(
					(++count > 2)); //参数为true代表没有更多数据了。
			var table = document.body.querySelector('.mui-table-view');
			var cells = document.body.querySelectorAll('.mui-table-view-cell');
			table.innerHTML = '';
			var msgsize = '${fn:length(messageList)}';
			
			//for (var i = cells.length, len = i + msgsize; i < len; i++) {
			<c:forEach var="item" items="${messageList}">
				var li = document.createElement('li');
				li.className = 'mui-table-view-cell mui-media';
				li.innerHTML = '<div class="mui-slider-right mui-disabled" msgid="${item.messageId}" castScope="${item.broadcastScope}">'
						+ '<a class="mui-btn mui-btn-red car-pull-left-delete">删除</a>'
						+ '</div>'
						+ '<div class="mui-input-row mui-radio mui-left mui-slider-handle" id="${item.messageId}" url="${item.urlLink}" read="${item.isRead}" scope="${item.broadcastScope}">'
						+ '<div class="cars-content">'
						+ '<div class="mui-media-object mui-pull-left" style="margin-left:0px;width: 42px;height: 42px;padding-left: 0px;border-radius: 20px;background: #ccc;">'
						<c:if test="${item.isRead == 'NO'}">
						+ '<span class="mui-badge mui-badge-warning" style="margin-bottom: 12px;margin-left: 35px;padding: 4px 4px;background-color: red;"></span>'
						</c:if>
						+ '</div>'
						+ '<div class="mui-media-body">'
						+ "${item.messageType}"
						+ '<font style="color: #000000;float:right;font-size: 13px;">'
						+ "${item.createTime}" 
						+ '</font>'
						+ '<p class="mui-ellipsis">'
						+ "${item.title}" 
						+ '</p>'
						+ '</div></div></div>';
				table.appendChild(li);
			</c:forEach>	
			//}
		}, 1500);
	}
	if (mui.os.plus) {
		mui.plusReady(function() {
			setTimeout(function() {
				mui('#pullrefresh').pullRefresh().pullupLoading();
			}, 1000);
		});
	} else {
		mui.ready(function() {
			mui('#pullrefresh').pullRefresh().pullupLoading();
		});
	}
	(function($) {
		$('#OA_task_1').on('tap', '.mui-btn', function(event) {
			var elem = this;
			var li = elem.parentNode.parentNode;
			var div = elem.parentNode;
			var btnArray = [ '确认', '取消' ];
			mui.confirm('你要删除这条消息吗？', '', btnArray, function(e) {
				if (e.index == 0) {
					var msgid = div.getAttribute("msgid");
					var castScope = div.getAttribute("castScope");
					$.ajax({
						type : "POST",
						url : "../msg/msgDel",
						dataType : "text",
						cache : false,
						data : "r=" + Math.random()+'&messageId='+msgid+'&broadcastScope='+castScope,
						success : function(data) {
							if (data != '' && data == 'ok') {
								li.parentNode.removeChild(li);
							}
						}
					});
				} else {
					setTimeout(function() {
						$.swipeoutClose(li);
					}, 0);
				}
			});
		});
		$('#OA_task_1').on('tap', '.mui-slider-handle', function(event) {
			var msgId = this.getAttribute("id");
			var url = this.getAttribute("url");
			var read = this.getAttribute("read");
			var scope = this.getAttribute("scope");
			if(read == 'false') {//未读
				$.ajax({
					type : "POST",
					url : "../msg/msgIsread",
					dataType : "text",
					cache : false,
					data : "r=" + Math.random() + '&messageId=' + msgId + '&broadcastScope=' +scope,
					success : function(data) {
						if (data != '' && data == 'ok') {
							location.replace(url);
						} else {
							location.replace(url);
						}
					}
				});
			}
		});
	})(mui);
</script>
</html>