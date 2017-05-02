//将表单所有数据项序列化成json对象 如果某个数据项包含多个值 使用,分割
//使用方法：$("#formId").serializeJson();
jQuery.fn.serializeJson=function(){
	   var serializeObj={};
	   var array=this.serializeArray();
	   var str=this.serialize();
	   $(array).each(function(){
		   if(serializeObj[this.name]){
			   if($.isArray(serializeObj[this.name])){
				   serializeObj[this.name].push(this.value);
			   }else{
				   var temp = serializeObj[this.name]+","+this.value;
				   serializeObj[this.name]=temp;  
			   }
		   }else{
			   serializeObj[this.name]=this.value;
		   }
	   });
	    return serializeObj;
	  };
/* 获取上下文路径 */
function rootPath() {
	var path = window.location.pathname;
	return path.substring(0, path.substr(1).indexOf('/')+1);
}

