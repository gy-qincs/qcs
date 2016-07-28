jQuery.validator.addMethod("_email", function(value, element) { 
	var regMail=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	//var regMobile=/^0?1[3|4|5|8][0-9]\d{8}$/;
    return this.optional(element) || regMail.test(value) ;       
}, "请输入正确的邮箱");

jQuery.validator.addMethod("mobile", function(value, element) { 
	var regMobile=/^0?1[3|4|5|8][0-9]\d{8}$/;
    return this.optional(element)||regMobile.test(value);       
}, "请输入正确的手机号码");

jQuery.validator.addMethod("chinaStr", function(value, element) { 
	var regChina=/^[\u4e00-\u9fa5]+$/g;
    return this.optional(element)||regChina.test(value);       
}, "请输入中文");

jQuery.validator.addMethod("post_code", function(value, element) { 
	var result = false;
	if(isNumber(value) && value.length==6){
		result = true;
	}
    return this.optional(element)|| result;     
}, "请输入正确的邮编");

jQuery.validator.addMethod("cardNum", function(value, element) { 
	var reg=/^(\d{15,15}|\d{17,17}|\d{19,19})$/;
    return this.optional(element) || reg.test(value);       
}, "请输入正确的手机充值卡卡号");

jQuery.validator.addMethod("cardPw", function(value, element) { 
	var cardNum=$('[name="cardNum"]').val();
	if(cardNum){
		var reg=/^(\d{18,18}|\d{19,19})$/;
		if(cardNum.length==15){
			reg=/^(\d{19,19})$/;
		}else if(cardNum.length==17){
			reg=/^(\d{18,18})$/;
		}else if(cardNum.length==19){
			reg=/^(\d{18,18})$/;
		}
		return this.optional(element) || reg.test(value); 
	}else{
		return false;
	}         
}, "请输入正确的手机充值卡密码");

jQuery.validator.addMethod("cardNumSingle", function(value, element) { 	
	var reg=/^(\d{15,15}|\d{17,17}|\d{19,19})$/;
	var tarType=$('#targetWrap .phoneCardPriceCur .proIcon').attr('type');
	if(tarType=='yd'){
		reg=/^(\d{17,17})$/;
	}else if(tarType=='lt'){
		reg=/^(\d{15,15})$/;
	}else if(tarType=='dx'){
		reg=/^(\d{19,19})$/;
	}
    return this.optional(element) || reg.test(value);       
}, "请输入正确的手机充值卡卡号");

jQuery.validator.addMethod("cardPwSingle", function(value, element) { 
	var cardNum=$('[name="cardNum"]').val();
	if(cardNum){
		var reg=/^(\d{18,18}|\d{19,19})$/;
		if(cardNum.length==15){
			reg=/^(\d{19,19})$/;
		}else if(cardNum.length==17){
			reg=/^(\d{18,18})$/;
		}else if(cardNum.length==19){
			reg=/^(\d{18,18})$/;
		}
		return this.optional(element) || reg.test(value); 
	}else{
		return false;
	}         
}, "请输入正确的手机充值卡密码");

jQuery.validator.addMethod("tenTimes", function(value, element) { 
	var result=!(value%10);
    return this.optional(element) || result;       
}, "error");