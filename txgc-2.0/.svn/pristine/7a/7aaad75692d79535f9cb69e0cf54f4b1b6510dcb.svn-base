<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${webSiteName}</title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<link href="${static}/css/styles_manage.css" rel="stylesheet" type="text/css" media="screen"/>
<script type="text/javascript" charset="UTF-8">	
   	$(function() {
   	   var msg = '${msg!""}';
   	    if(null!=msg && ""!=msg){
   	    	alert(msg);
   	    }
   		$("#loginAcct").focus();
		var f = $("#loginInputForm");
		f.on('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				/*$.post('${ctx}/main/login', f.serialize(), function(result) {
					if (result.success) {
						var w = window.open("","_self");
						w.location.href="${ctx}/manage/index";
					}else{
						$("#loginAcct").focus();
						myMessage(result.msg);
						
					}
				});*/
				login();
			}
		});
    });
   	function myMessage(mg)
	{
	   $.messager.alert('提示',mg);
	}
	
	function login(){
			var f = $('#loginInputForm');
				/*$.post('${ctx}/main/login', 
				f.serialize(), 
				function(result) {
					//console.log(result);
					if (result.success) {
						var w = window.open("","_self");
						w.location.href="${ctx}/manage/index";
					}else {
					   myMessage(result.msg);
					   $("#loginAcct").focus();
					}
				});*/
				$.ajax({
					url:'${ctx}/main/login',
					data:f.serialize(),
					dataType:"json",
					beforeSend:function(){
						$.messager.progress({
							title:"请稍候",
							msg:"页面加载中..."
						});
					},
					success:function(result){
						$.messager.progress("close");
						if (result.success) {
							var w = window.open("","_self");
							w.location.href="${ctx}/manage/index";
						}else {
						   myMessage(result.msg);
						   $("#loginAcct").focus();
						}
					},
					error:function(){
						$.messager.progress("close");
						myMessage("操作失败，请刷新后重试！");
					}  
				});						
	}
</script>
</head>
<body style="height:100%; background-color:#CAE7F5;"> 
 <div class="login">
   <div class="login_box">
    <div class="login_box_mid" id="loginAndRegDialog">  
         <div class="loginbox">         
         	<form id="loginInputForm" method="post" contentType="text/html">     
	           <div class="line1 line1_h">
	             <input id="loginAcct" name="loginAcct" type="text" required="true" value="admin" class="txtbox1"/>
	           </div>
	           
	           <div class="line1 line1_h2">
	             <input input id="inputthispwd" name="password" type="password"  required="true" value="admin"   class="txtbox1"/>
	           </div>     
	          <div class="line2">
	             <div class="zhmm"></div>
	             <input name="input" type="button" class="loginbtn" onclick="login();"/>
	           </div>   
	         </form>  
         </div>
    </div>
   </div>
 </div>
</body>
</html>