/**
 * jquery.validate�����չ��֤
 * @author wuyu
 */

//��֤�û���       
jQuery.validator.addMethod("emailOrMobile", function(value, element) { 
	var regMail=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var regMobile=/^0?1[3|4|5|8][0-9]\d{8}$/;
    return this.optional(element) || regMail.test(value)||regMobile.test(value);       
}, "��������ȷ��������ֻ����룡");

jQuery.validator.addMethod("cardNum", function(value, element) { 
	var reg=/^(\d{15,15}|\d{17,17}|\d{19,19})$/;
    return this.optional(element) || reg.test(value);       
}, "��������ȷ���ֻ���ֵ�����ţ�");

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
}, "��������ȷ���ֻ���ֵ�����룡");
jQuery.validator.addMethod("tenTimes", function(value, element) { 
	var result=!(value%10);
    return this.optional(element) || result;       
}, "������10����������");

jQuery.validator.addMethod("sqlInputCheck", function(value, element) { 
	var result = filterSqlStr(value);
    return this.optional(element) || result;       
}, "��������ȷ�����ݣ�");


var sql_str="and,delete,or,exec,insert,select,union,update,count,truncate,*,',join,>,<";
function filterSqlStr(value){  
	var sqlStr=sql_str.split(',');  
    var flag=true;  
      
    for(var i=0;i<sqlStr.length;i++){    
    	if(value.toLowerCase().indexOf(sqlStr[i])!=-1){  
            flag=false;  
           	break;  
        }  
    }  
    return flag;  
}  