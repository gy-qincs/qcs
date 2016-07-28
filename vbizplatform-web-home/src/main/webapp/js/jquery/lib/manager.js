(function($){
	$.fn.extend({
		//清除空格
		"trimVal" : function(){
			return $(this).val() == null ? "" : $(this).val().replace(/(^\s*)|(\s*)|(\s*$)/g, "");
		},  
		//为空
		"isEmpty" : function(){
			if($(this).trimVal() == null || $(this).trimVal().length == 0)return true;
			return false;
		},
		//都不为空
		"areNotNull" : function(){
		    if($(this) == null || $(this).length == 0)return false;
			for(var i = 0;i<$(this).length;i++){
				if($(this).isEmpty())return false;
			}
			return true;
		},
		//是邮箱地址
		"isEmail" : function(){
			var re =/^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  	if (re.test($(this).val())) {
	        	return true;
	    	}else{
	    		return false;
	    	}
	    },
	    //获取window.location.href请求中的所有参数
	    "getUrlVars" : function(){   
		    var vars = [], hash;   
		    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');   
		    for(var i = 0; i < hashes.length; i++){   
		      hash = hashes[i].split('=');   
		      vars.push(hash[0]);   
		      vars[hash[0]] = hash[1];   
		    }   
		    return vars;   
		},   
		//获取request请求中的指定参数
		"getUrlVar" : function(name){   
		    return $(this).getUrlVars()[name];   
		},
		//根据邮箱地址获取邮箱服务器地址
		"getEmailServer" : function(){
			var hash={
			    'qq.com': 'http://mail.qq.com',
			    'gmail.com': 'http://mail.google.com',
			    'sina.com': 'http://mail.sina.com.cn',
			    '163.com': 'http://mail.163.com',
			    '126.com': 'http://mail.126.com',
			    'yeah.net': 'http://www.yeah.net/',
			    'sohu.com': 'http://mail.sohu.com/',
			    'tom.com': 'http://mail.tom.com/',
			    'sogou.com': 'http://mail.sogou.com/',
			    '139.com': 'http://mail.10086.cn/',
			    'hotmail.com': 'http://www.hotmail.com',
			    'live.com': 'http://login.live.com/',
			    'live.cn': 'http://login.live.cn/',
			    'live.com.cn': 'http://login.live.com.cn',
			    '189.com': 'http://webmail16.189.cn/webmail/',
			    'yahoo.com.cn': 'http://mail.cn.yahoo.com/',
			    'yahoo.cn': 'http://mail.cn.yahoo.com/',
			    'eyou.com': 'http://www.eyou.com/',
			    '21cn.com': 'http://mail.21cn.com/',
			    '188.com': 'http://www.188.com/',
			    'foxmail.coom': 'http://www.foxmail.com',
			    '19pay.com.cn' : 'https://mail.19e.com.cn/owa',
			    '19e.com.cn' : 'https://mail.19e.com.cn/owa'
			};
			if (typeof(hash[$(this).val().split('@')[1]]) == 'undefined'){
				return 'http://mail.' + $(this).val().split('@')[1];
			}
			return hash[$(this).val().split('@')[1]];
		},
		/*密码验证规则*/
	    "isPassword" : function(){
			var re=/^[\w]{6,20}$/;
		  	if (re.test($(this).val())) {
		        return true;
		    }else{
		    	return false;
		    }
	    },
	    //是否是中文或应为
	    "isUserName" : function(s){
			var re = /^[\u4e00-\u9fa5|a-zA-Z]+[\u4e00-\u9fa5|a-zA-Z]$/;
			return re.test(s);
		}
	});
	
		// 字符验证       
	jQuery.validator.addMethod("nameCheck", function(value, element) {       
	    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);       
	}, "只能包括中文字、英文字母、数字和下划线");   
	// 手机号码验证       
 	jQuery.validator.addMethod("isMobile", function(value, element) {       
	    var length = value.length;   
	    var mobile = /^1[34578]\d{9}$/;   
	    return this.optional(element) || (length == 11 && mobile.test(value));       
	}, "请正确填写您的手机号码");  
	//固话区号验证     
	jQuery.validator.addMethod("isAreaCode", function(value, element) {       
		 var length = value.length;   
		 var area = /^\d{3,4}$/;
		 return this.optional(element) || area.test(value);          
	},"请正确填写您的区号");
	//固话号码验证
	jQuery.validator.addMethod("isPhoneNum", function(value, element) {       
		 var length = value.length;   
		 var phoneNum = /^\d{7,9}$/;
		 return this.optional(element) || phoneNum.test(value);          
	},"请正确填写联系电话号码");
	// 身份证号码验证        
	jQuery.validator.addMethod("isIdCardNo", function(value, element) {         
		return this.optional(element) || isIdCardNo(value);         
	}, "请正确输入您的身份证号码");
	//邮编验证
	jQuery.validator.addMethod("zipCode", function(value, element) {
	    var tel = /^[0-9]{6}$/;
	    return this.optional(element) || (tel.test(value));
	}, "邮政编码格式错误");
	//验证码长度
	jQuery.validator.addMethod("vcodeLength", function(value, element) {
	    var length = value.length;   
	    return this.optional(element) || (length == 4);
	}, "请输入四位验证码");
	
	
})(jQuery);

/**   
  * 身份证号码验证  
  */   

function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if (intStrLen != 18) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    return true;
}
function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}

function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}

