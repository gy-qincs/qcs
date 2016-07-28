var remoteLog = {};

remoteLog.infoLog = function(_name,_type){
//	alert('进入log方法'+appId+'----'+operateName+'-----'+operateId+'-----'+sourceId);
	 var _params = {};
	  var _seedId = "";
	  var _mdata = {};
	  if(!_name){
	    return this;
	  }

	  if(!_type){
	    _type = "openPage"
	  }
	  _mdata.monitor_type = _type;
	  _name = _name.toLocaleLowerCase();
	  
      _seedId = "eco-carlife-platform-"+_name;
      _params.carlife_appid = "20000919";
      _params.carlife_app = "eco_carlife_platform";
	  
	  _params.carlife_sid = typeof GLOBLE_SESSIONID != "undefined" ? GLOBLE_SESSIONID : "";
	  if(typeof BizLog != 'undefined'){
	    console.log("埋点开始");
	    console.log(BizLog);
	    BizLog.call('info', {
	      seedId : _seedId,
	      params : _params,
	      mdata : _mdata
	    });
	    console.log(BizLog);
	    console.log("埋点结束");
	  }
	  console.log({
	    seedId : _seedId,
	    params : _params,
	    mdata : _mdata
	  });
};

