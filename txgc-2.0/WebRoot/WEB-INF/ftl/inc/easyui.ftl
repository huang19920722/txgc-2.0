<script src="${static}/js/jquery-1.11.3.min.js" charset="UTF-8" type="text/javascript"></script>
<script src="${static}/js/jquery-migrate-1.2.1.js" charset="UTF-8" type="text/javascript"></script>
<link href="${static}/js/jquery-easyui/1.4.4/themes/ui-cupertino/easyui.css" rel="stylesheet" type="text/css" >
<link href="${static}/js/jquery-easyui/1.4.4/themes/icon.css" rel="stylesheet" type="text/css" >
<link href="${static}/css/baseCss.css" rel="stylesheet" type="text/css" />
<script src="${static}/js/jquery-easyui/1.4.4/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script>
<script src="${static}/js/jquery-easyui/1.4.4/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script>
<script src="${static}/js/syUtils.js" charset="UTF-8" type="text/javascript"></script>

<script type="text/javascript" charset="UTF-8">

   //对返回的数字格式的日期进行日期格式化
  Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(), //day 
        "h+": this.getHours(), //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter 
        "S": this.getMilliseconds() //millisecond 
    }
    if (/(y+)/.test(format)) 
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) 
        if (new RegExp("(" + k + ")").test(format)) 
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}
function myMessage(msg,fn){
	$.messager.alert('提示',msg,'',function(){
		if(fn && typeof(fn)=='function'){
			fn();
		}
	});
}
</script>