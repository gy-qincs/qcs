/*根据页面传值初始化tab*/
function initTab(selectObj,detailObj,currentClassName,index){
	$(detailObj).each(function(){
		$(this).hide()
	});
	$(selectObj).each(function(){
		if($(this).hasClass(currentClassName)){
			$(this).removeClass(currentClassName);
		}
	});
	$(detailObj).eq(index).show();
	$(selectObj).eq(index).addClass(currentClassName);
}

var fadeInTab=function(selectObj,detailObj,currentClassName){
	$(selectObj).each(function(i,obj){
		$(obj).unbind('click').click(function(){
			$(detailObj).each(function(){
				$(this).hide()
			});
			$(selectObj).each(function(){
				if($(this).hasClass(currentClassName)){
					$(this).removeClass(currentClassName);
				}
			});
			$(detailObj).eq(i).fadeIn("slow");
			$(obj).addClass(currentClassName);
		});
	});
}

var fadeInTabNoBindClick=function(selectObj,detailObj,currentClassName,i){
	$(detailObj).each(function(){
		$(this).hide()
	});
	$(selectObj).each(function(){
		if($(this).hasClass(currentClassName)){
			$(this).removeClass(currentClassName);
		}
	});
	$(detailObj).eq(i).fadeIn("slow");
	$(selectObj).eq(i).addClass(currentClassName);
}
/*点击关于我们页面菜单，加载对应页面并修改菜单样式*/
var toAboutUsMain=function(menu,address,mainId,obj){
	if(menu){
		$("#aboutUsMenu ul li").each(function(){
			if($(this).hasClass("current")){
				$(this).removeClass("current");
			}
		});
		$(obj).parent("li").addClass("current");
	}
	$(mainId).load(address,function(){
	
	});
}
/*获取页面传值*/
function getQueryStr(str,LocString) {
	var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
	if (tmp = rs) {
	return tmp[2];
	}
	// parameter cannot be found
	return "";
} 

/*按下键盘enter键，点击对应按钮*/
function enterForm(formId){
	$(formId).keydown(function(e){
		if(e.keyCode==13){
			$(formId).submit();
		}
	});
}

/*注册上方样式变化*/
function stepHead(headId,nStep){
	var left=0;
	if(headId=="mailRegHead"){
		left=(nStep-1)*304;
	}else if(headId=="phoneRegHead"){
		left=(nStep-1)*608;
	}	
	$("#"+headId+" .leadBarBlue").css("background-position",left+"px -47px");
	$("#"+headId+" .leadBarWord span").each(function(){
		if($(this).hasClass("colorWhite")){
			$(this).removeClass("colorWhite");
		}
	});
	for(var i=0;i<nStep;i++){
		$("#"+headId+" .leadBarWord span").eq(i).addClass("colorWhite");
	};	
}
/*红色边框input*/
function wrongInput(obj){
	$(obj).focus();
	$(obj).addClass("wrongInput");
}


/*检测密码强度start---*/
function AuthPasswd(string,tipId) {
	if(string.length >=6) {
		if(/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string) && /\W+\D+/.test(string)) {
			noticeAssign(2,tipId);
		}else if(/[a-zA-Z]+/.test(string) || /[0-9]+/.test(string) || /\W+\D+/.test(string)) {
			if(/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string)) {
				noticeAssign(1,tipId);
			}else if(/\[a-zA-Z]+/.test(string) && /\W+\D+/.test(string)) {
				noticeAssign(1,tipId);
			}else if(/[0-9]+/.test(string) && /\W+\D+/.test(string)) {
				noticeAssign(1,tipId);
			}else{
				noticeAssign(0,tipId);
			}
		}
	}else{
		noticeAssign(0,tipId);
	}
}
function noticeAssign(num,tipId) {
	/*num=2-->强；num=1-->中；num=0->弱*/
	$("#"+tipId+" span").each(function(){
		if($(this).hasClass("blue")){
			$(this).removeClass("blue");
		}
	});
	$("#"+tipId+" span").eq(num).addClass("blue");

}
/*---检测密码强度end*/

/*座机号码由区号自动跳到下一个input*/
function toNextNum(num,obj){
	if(num.length>=4){
		$(obj).blur();
		$(obj).siblings("input").eq(0).focus();
	}
}