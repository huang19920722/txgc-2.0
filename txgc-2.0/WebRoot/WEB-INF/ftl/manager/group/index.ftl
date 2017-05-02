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
	
	$terminalDataGrid=$("#terminalDataGrid").datagrid({
			url : '${ctx}/terminalInfo/datagrid',
			toolbar : '',
			title : '',
			iconCls : 'icon-save',
			rownumbers: true,
			pagination : true,
			pageSize : 10,
			pageList : [5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'id',
			frozenColumns : [ [ {
				field : 'id',
				title : '采集终端ID',
				width : 150,
				checkbox : true
			}] ],
			columns : [ [ {
				field : 'code',
				title : '终端编码',
				width :100
			}, {
				field : 'model',
				title : '终端型号',
				width : 130
			}, {
				field : 'terminalState',
				title : '终端状态',
				width : 100,
				formatter:function(value,rec,i){
					if(value=="1"){
						return "停用";	
					}else if(value=="2"){
						return "未分配";
					}else if(value=="3"){
						return "已分配";
					}else if(value=="4"){
						return "未归还";
					}
				}
			},{
				field : 'terminalUser',
				title : '使用者',
				width : 100,
				formatter:function(value,rec,i){
					if(value){
						return rec.terminalUser.name;
					}else{
					return "";
					}
				}	
			},{
				field : 'castTime',
				title : '投用时间',
				width : 200,
				sortable: true	
			}, {
				field : 'org',
				title : '终端所属机构',
				width : 200,
				formatter:function(value,rec,i){
					if(value){
						return rec.org.orgName;
					}else{
					return "";
					}
				}
			},{
				title : '操作',
				field : '1',
				width : 250,
				formatter:function(value,rec,i){
					var btnHtml="";
					var str=rec.terminalState;
					<#if GeneralMethod.checkButton(requestURI,"icon-edit")>
						btnHtml="<a href='javascript:edit("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";
					</#if>

					return btnHtml;
				}
			} ] ]
		});
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