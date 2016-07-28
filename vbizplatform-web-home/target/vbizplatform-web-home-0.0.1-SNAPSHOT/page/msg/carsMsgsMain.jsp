<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core-jakarta"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>服务消息</title>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet" href="../css/mui.min.css">
<link rel="stylesheet" href="../css/cars.css">
</head>
<body>
	<div class="mui-content">
		<input type="hidden" name="page" id="page" value="-1">
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
<script type="text/javascript" src="../js/mui.min.js"></script>
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	function downRefresh(res) {
		var table = document.body.querySelector('.mui-table-view');
		var cells = document.body.querySelectorAll('.mui-table-view-cell');
		table.innerHTML = '';
		for (var i = 0; i < res.length; i++) {
			var li = document.createElement('li');
			li.className = 'mui-table-view-cell mui-media';
			var html = '<div class="mui-slider-right mui-disabled" msgid="'+res[i].messageId+'" castScope="'+res[i].broadcastScope+'">';
			html+= '<a class="mui-btn mui-btn-red car-pull-left-delete">删除</a>';
			html+= '</div>';
			html+= '<div class="mui-input-row mui-radio mui-left mui-slider-handle" id="'+res[i].messageId+'" url="'+res[i].urlLink+'" read="'+res[i].isRead+'" scope="'+res[i].broadcastScope+'">';
			html+= '<div class="cars-content">';
			html+= '<div class="mui-media-object mui-pull-left" style="margin-right: 15px;margin-left:0px;width: 36px;height: 36px;padding-left: 0px;margin-top: 4px;border-radius: 100%;background-image:url('+res[i].icon+');background-size:100% 100%;">';
			if(res[i].isRead == false) {
				html+= '<span class="mui-badge mui-badge-warning" style="position: absolute;top: 2px;margin-left: 27px;padding: 6px;background-color: red;"></span>';
			}
			html+= '</div>';
			html+= '<div class="mui-media-body">';
			html+= '<font style="color: #000000;">'+res[i].title+'</font>';
			html+= '<font style="color: #A5A5A5;float:right;font-size: 12px;">'+res[i].createTime+'</font>';
			html+= '<p class="mui-ellipsis">'+res[i].content+'</p>';
			html+= '</div></div></div>';
			li.innerHTML = html;
			//下拉刷新，新纪录插到最前面；
			table.appendChild(li);
		}
		mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
		mui('#pullrefresh').pullRefresh().refresh(true);
	}
	//上拉
	function upRefresh(res) {
		var table = document.body.querySelector('.mui-table-view');
		var cells = document.body.querySelectorAll('.mui-table-view-cell');
		for (var i = 0; i < res.length; i++) {
			var li = document.createElement('li');
			li.className = 'mui-table-view-cell mui-media';
			var html = '<div class="mui-slider-right mui-disabled" msgid="'+res[i].messageId+'" castScope="'+res[i].broadcastScope+'">';
			html+= '<a class="mui-btn mui-btn-red car-pull-left-delete">删除</a>';
			html+= '</div>';
			html+= '<div class="mui-input-row mui-radio mui-left mui-slider-handle" id="'+res[i].messageId+'" url="'+res[i].urlLink+'" read="'+res[i].isRead+'" scope="'+res[i].broadcastScope+'">';
			html+= '<div class="cars-content">';
			html+= '<div class="mui-media-object mui-pull-left" style="margin-right: 15px;margin-left:0px;width: 36px;height: 36px;padding-left: 0px;margin-top: 4px;border-radius: 100%;background-image:url('+res[i].icon+');background-size:100% 100%;">';
			if(res[i].isRead == false) {
				html+= '<span class="mui-badge mui-badge-warning" style="position: absolute;top: 2px;margin-left: 27px;padding: 6px;background-color: red;"></span>';
			}
			html+= '</div>';
			html+= '<div class="mui-media-body">';
			html+= '<font style="color: #000000;">'+res[i].title+'</font>';
			html+= '<font style="color: #A5A5A5;float:right;font-size: 12px;">'+res[i].createTime+'</font>';
			html+= '<p class="mui-ellipsis">'+res[i].content+'</p>';
			html+= '</div></div></div>';
			li.innerHTML = html; 
			table.appendChild(li);
		}
		mui('#pullrefresh').pullRefresh().endPullupToRefresh();
	}
	
	function showNullNotityMsg(){
		var table = document.body.querySelector('.mui-table-view');
		var flag = table.childElementCount;
		if(!flag){
			var $numData = $('<div id="nullMsgBlock"><img src="../images/null.png"><br><span>暂无服务消息</span></div>')
			.addClass('nulldata').css({
				'text-align': 'center',
			});
			$(table).append($numData);
		}
	}
	
	function clearNullNotityMsg(){
		var $nullMsgBlock = $("#nullMsgBlock");
		if($nullMsgBlock.length>0){
			$nullMsgBlock.remove();
		}
	}
	
	(function($) {
		$.init({
			pullRefresh : {
				container : '#pullrefresh',
				down : {callback : pulldownRefresh},
				up : {contentrefresh : '正在加载...', callback : pullupRefresh}
			}
		});
		/**
		 * 下拉刷新具体业务实现
		 */
		function pulldownRefresh() {
			setTimeout(function() {
				$.ajax({
					type : "POST",
					url : "../msg/getMessageList",
					dataType : "json",
					cache : false,
					data : "r=" + Math.random(),
					success : function(data) {
						//alert(data);
						if (data != '') {
							document.getElementById('page').value = '2';
							//$("#page").val("2");
							//清除消息空的提示块 
							clearNullNotityMsg();
							var res = eval(data);
							downRefresh(res);
						} else {
							mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
							/* 修改数据为空 并且 列表无服务消息  */
							showNullNotityMsg();
						}
					}
				});
			}, 300);
		}
		/**
		 * 上拉加载具体业务实现
		 */
		function pullupRefresh() {
			setTimeout(
				function() {
					var page = document.getElementById('page').value;
					if(page != '-1'){
						$.ajax({
							type : "POST",
							url : "../msg/getMessageList",
							dataType : "json",
							cache : false,
							data : "r=" + Math.random()+'&page='+page,
							success : function(data) {
								//alert(data);
								if (data != '') {
									var res = eval(data);
									if(res.length == 10){	//还有记录
										document.getElementById('page').value = Number(page) + 1;
										//$("#page").val(page++);
									} else {//没有记录了
										document.getElementById('page').value = '-1';
										mui('#pullrefresh').pullRefresh().endPullupToRefresh(true);
									}
									upRefresh(res);
								} else {
									document.getElementById('page').value = '-1';
									mui('#pullrefresh').pullRefresh().endPullupToRefresh(true);
								}
							}
						});
					} else {
						mui('#pullrefresh').pullRefresh().endPullupToRefresh(true);
					}
				}, 300);
		}
		if ($.os.plus) {
			$.plusReady(function() {
				setTimeout(function() {
					$('#pullrefresh').pullRefresh().pulldownLoading();
				}, 300);
			});
		} else {
			$.ready(function() {
				$('#pullrefresh').pullRefresh().pulldownLoading();
			});
		}
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
			} else {
				location.replace(url);
			}
		});
		$('#OA_task_1').on('tap', '.mui-btn', function(event) {
			var elem = this;
			var li = elem.parentNode.parentNode;
			var div = elem.parentNode;
			//var btnArray = [ '确认', '取消' ];
			//$.confirm('你要删除这条消息吗？', '', btnArray, function(e) {
				//if (e.index == 0) {
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
				
			//});
		});
		/***/	
	})(mui);
	$(function() {
		setTimeout(loadJSAPI, 200);
		function loadJSAPI() {
			// 显示标题栏
			AlipayJSBridge.call("showTitlebar");
			// 设置标题
			AlipayJSBridge.call("setTitle", {
			    title: '服务消息'
			});
			// 隐藏右按钮
			AlipayJSBridge.call("hideOptionMenu");
		}	
	});	
</script>
</body>
</html>