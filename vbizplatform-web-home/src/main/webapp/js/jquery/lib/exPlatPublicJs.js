/*����ҳ�洫ֵ��ʼ��tab*/
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
/*�����������ҳ��˵������ض�Ӧҳ�沢�޸Ĳ˵���ʽ*/
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
/*��ȡҳ�洫ֵ*/
function getQueryStr(str,LocString) {
	var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
	if (tmp = rs) {
	return tmp[2];
	}
	// parameter cannot be found
	return "";
} 

/*���¼���enter���������Ӧ��ť*/
function enterForm(formId){
	$(formId).keydown(function(e){
		if(e.keyCode==13){
			$(formId).submit();
		}
	});
}

/*ע���Ϸ���ʽ�仯*/
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
/*��ɫ�߿�input*/
function wrongInput(obj){
	$(obj).focus();
	$(obj).addClass("wrongInput");
}


/*�������ǿ��start---*/
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
	/*num=2-->ǿ��num=1-->�У�num=0->��*/
	$("#"+tipId+" span").each(function(){
		if($(this).hasClass("blue")){
			$(this).removeClass("blue");
		}
	});
	$("#"+tipId+" span").eq(num).addClass("blue");

}
/*---�������ǿ��end*/

/*���������������Զ�������һ��input*/
function toNextNum(num,obj){
	if(num.length>=4){
		$(obj).blur();
		$(obj).siblings("input").eq(0).focus();
	}
}