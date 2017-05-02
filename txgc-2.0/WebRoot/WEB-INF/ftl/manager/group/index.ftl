<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#include "/inc/defineColumnsCollectInfo.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />

<!-- 界面样式调整 -->
<style>
	table#userInfoTab tr td,th{
			border-bottom:none;
			font-size:14px;
	}
	table#userInfoTab{
			border-bottom:none;
	}
	fieldset legend{
		font-size:16px;
	}
</style>

<script type="text/javascript" charset="UTF-8">
	var $terminalDataGrid;//终端设备列表
	
	
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div name="userInfoDiv">
			<fieldset>
			 	<legend>用户信息</legend>
				<table id="userInfoTab" class="basic basic_dialog">
					<tr>
						<td>登录号码：<span id="passwordSpan">123123123</span></td>
					</tr>
					<tr>
						<td>昵称：<span id="nikeNameSpan">123123123</span></td>
					</tr>
					<tr>
						<td>所属组：<span id="groupSpan">123123123</span></td>
					</tr>
				</table>
			</feildset>
		</div>
		<div name="terminalListDiv">
			<fieldset>
			 	<legend>终端设备管理</legend>
				<table id="terminalDataGrid"></table>
			</feildset>
		</div>
	</div>
	
	
	

</body>
</html>