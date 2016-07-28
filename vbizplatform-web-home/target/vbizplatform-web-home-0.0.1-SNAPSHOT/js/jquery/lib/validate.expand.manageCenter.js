/**
 * jquery.validate相关扩展验证
 * @author wuyu
 */

/*验证小数点后不能超过两位*/
jQuery.validator.addMethod("decimals", function(value, element,d){ 
     var str = 3 .toString();// 1后面的空格不可少,但使用变量的时候无此限制
	// 设str为参数
	var reg = new RegExp( "^\\-?([1-9]\\d*|0)(\\.\\d{0," + str + "})?$" );
    return this.optional(element) || reg.test(value);   
}); 

jQuery.validator.addMethod("maxCountCheck", function(value, element,d){
	
	return this.optional(element) || value>0;
});