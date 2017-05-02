//////////////////// layer相关  ////////////////////////

var index;//layer索引
layer.config({
	skin:'layer-ext-seaning',
	extend:'skin/seaning/style.css'
});
function startLoading(){
	index = layer.load();
}
function endLoading(){
	layerClose();
}
function layerClose(){
	layer.close(index);
}
function layerCloseAll(){
	layer.closeAll();
}
function layerMsg(msg,funcOk){
	layer.msg(msg, {icon: 6},function(){
		if(funcOk!=null){
			funcOk();
		}
	});
}

function layerInfo(info,funcOk){
	layer.alert(info, {icon: 6},function(){
		if(funcOk!=null){
			funcOk();
		}
	});
}
function layerError(error){
	layer.alert(error, {icon: 11,title:'错误'});
}
function layerConfirm(msg,funcOk,funcCancel){
	layer.confirm(msg, {icon: 4, title:'确认'}, funcOk, funcCancel);
}
function layerConfig(option){
	layer.config(option);
}
function layerOpen(url,title,width,height,successFn,fullFn,restoreFn){
	index = layer.open({
		title:title,
	    type: 2,
	    shade: [0.3, '#000'],
	    area: [width+'px', height+'px'],
	    maxmin: true,
	    content: url,
	    zIndex: layer.zIndex,
	    success:successFn,
	    full:fullFn,
	    restore:restoreFn
	    
	});
}

//////////////////////////// ajax 相关 ///////////////////////////

function layerSubmit (params){
	var contentType="application/x-www-form-urlencoded";
	if(params.contentType){
		contentType=params.contentType;
	}
	var dataType="json";
	if(params.dataType){
		dataType=params.dataType;
	}
	var type="post";
	if(params.type){
		type=params.type;
	}
	$.ajax({
		type : type,
		url : params.url,
		data : params.data,
		cache : false,
		contentType:contentType,
		dataType:dataType,
		beforeSend : function(){
			startLoading();
		},
		success : function(resp) {
			endLoading();
			if(params.success!=null){
					params.success(resp);
				}
		},
		error : function() {
			endLoading();
			layerError("操作失败,请刷新页面后重试");
			if(params.error!=null){
				params.error();	
			}
		},
		complete : function(XMLHttpRequest){
			endLoading();
			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus
	         if(sessionstatus=="timeout"){   
	        	 //超时就处理  
	           window.location.replace(sy.ctx()+"/error/timeout.jsp");   
	         }
	         var permission=XMLHttpRequest.getResponseHeader("permission");
	         if(permission=="No"){
	        	 //没有权限处理
	        	 window.location.replace(sy.ctx()+"/error/noright.jsp"); 
	         }
		}
	});
}

function layerLoad(id,url,type,showLoading){
	if(type!="get"){
		type = "post";
	}
	if(showLoading!=false){
		showLoading=true
	}
	$.ajax({
		type : type,
		url : url,
		cache : false,
		beforeSend : function(){
			if(showLoading){
				startLoading();
			}
		},
		success : function(resp) {
			if(resp){
				$("#"+id).html(resp);		
			}
		},
		error : function() {
			if(showLoading){
				endLoading();				
			}
		},
		complete : function(){
			if(showLoading){
				endLoading();				
			}
		}
	});
}





