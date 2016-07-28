/**
 * jquery.validate相关扩展验证
 * @author wuyu
 */

//验证用户名       
jQuery.validator.addMethod("emailOrMobile", function(value, element) { 
	var regMail=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var regMobile=/^0?1[3|4|5|8][0-9]\d{8}$/;
    return this.optional(element) || regMail.test(value)||regMobile.test(value);       
}, "请输入正确的邮箱或手机号码！");
jQuery.validator.addMethod("cardNum", function(value, element) { 
	var reg=/^(\d{15,15}|\d{17,17}|\d{19,19})$/;
    return this.optional(element) || reg.test(value);       
}, "请输入正确的手机充值卡卡号！");

jQuery.validator.addMethod("cardPw", function(value, element) { 
	var cardNum=$('#cardNum').val();
	var reg=/^(\d{18,18}|\d{19,19})$/;
	if(cardNum.length==15){
		reg=/^(\d{19,19})$/;
	}else if(cardNum.length==17){
		reg=/^(\d{18,18})$/;
	}else if(cardNum.length==19){
		reg=/^(\d{18,18})$/;
	}
    return this.optional(element) || reg.test(value);       
}, "请输入正确的手机充值卡密码！");