<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	$(function(){
		timeInputBoxBindOpenPanel('useTimeBegin');
		timeInputBoxBindOpenPanel('useTimeEnd');
		datagrid = $('#datagrid').datagrid({
			url : '${ctx}/terminalUseInfo/datagrid',
			toolbar : '#toolbar',
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
				title : '终端使用记录ID',
				width : 50,
				checkbox : true
			}] ],
			columns : [ [ {
				field : 'terminalUserName',
				title : '领用人',
				width :50
			}, {
				field : 'terminalCode',
				title : '终端编号',
				width : 100,
				sortable: true
			}, {
				field : 'terminalUserPhone',
				title : '联系电话',
				width : 100
			}, {
				field : 'useTime',
				title : '预计归还时间',
				width : 100,
				sortable: true
			}, {
				field : 'backTime',
				title : '归还时间',
				width : 100,
				sortable: true
			},
			{
				field : 'creatTime',
				title : '操作时间',
				width : 100,
				sortable: true
			}, {
				field : 'useAreaName',
				title : '使用区域',
				width : 100
			}, {
				field : 'useAdress',
				title : '使用地点',
				width : 100
			}, {
				field : 'terminalState',
				title : '仪表状态',
				width : 50,
				formatter:function(value,rec,i){
					if(value=="1"){
						return "停用";	
					}else if(value=="2"){
						return "归还";
					}else if(value=="3"){
						return "分配";
					}else if(value=="4"){
						return "超期";
					}
				}
			}, {
				field : 'creatTime',
				title : '创建时间',
				width : 100,
				sortable: true
			}, {
				field : 'org',
				title : '运营商',
				width : 120,
				formatter:function(value,rec,i){
					if(value){
						return rec.org.orgName;
					}else{
					return "";
					}
				}
			}] ]
		});
	});
	 //查询
	function searchFun() {
		datagrid.datagrid('load', {
			terminalUserName : $('#toolbar input[name=terminalUserName]').val(),
			terminalCode : $('#toolbar input[name=terminalCode]').val(),
			terminalState : $('#toolbar input[name=terminalState]').val(),
			"backTimeBegin" :$("#backTimeBegin").datetimebox("getValue"),
			"backTimeEnd" :$("#backTimeEnd").datetimebox("getValue"),
			useAreaName : $('#toolbar input[name=useAreaName]').val(),
			useAdress : $('#toolbar input[name=useAdress]').val(),
			orgIdForSearch : $('#toolbar input[name=orgIdForSearch]').val()
		});
	}
	 /*工具栏里面类容清空*/
	function clearFun() {
		$('#toolbar input').val('');
		$('#toolbar select').val('');
		$("#datagrid").datagrid('load', {});
		datagrid.datagrid('unselectAll');
	
	}
	//时间
	function timeInputBoxBindOpenPanel(id){
		var timeInput=$("#"+id).parent().children()[1];
		timeInput=$(timeInput).children()[0];
		$(timeInput).click(function(){
			$("#"+id).datetimebox("showPanel");
		});
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="search_table">
				<table>
					<tr>
						<td>
							使用者名称：
							<input name="terminalUserName" class="easyui-textbox" style="height:26px;"/>
							终端编号：
							<input name="terminalCode" class="easyui-textbox" style="height:26px;"/>
							终端状态：
							<select name="terminalState" id="terminalState" class="easyui-combobox" style="height:26px;width:175px;">
								<option value=null select="selected"></option>
								<option value="1">停用</option>
								<option value="2">未分配</option>
								<option value="3">已分配</option>
								<option value="4">未归还</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							使用点：
							<input name="useAdress" class="easyui-textbox" style="height:26px;"/>
							使用地区：
							<input name="useAreaName" class="easyui-textbox" style="height:26px;"/>
							运营商:
							<input id="orgIdForSearch" name="orgIdForSearch" class="easyui-combobox" style="width:175px; height:26px;"   
        					data-options="valueField:'id',textField:'orgName',url:'${ctx}/terminalInfo/findOrgByCurrentUser'"/>
						</td>
						
					</tr>
					<tr>
						<td>
							归还时间：
							<input name="backTimeBegin" id="backTimeBegin" editable="false"  class="easyui-datetimebox" style="width: 175px; height:26px;" onfocus="foxDateTimeBox()" />
							<span class="span_line">一</span>
							<input name="backTimeEnd" id="backTimeEnd"  editable="false" class="easyui-datetimebox" style="width: 175px; height:26px;" onfocus="foxDateTimeBox()" />
							<#if GeneralMethod.checkButton(requestURI,"icon-search")>
								<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
							</#if>
								<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
						</td>
					</tr>
				</table>
		</div>
		<table id="datagrid"></table>
	</div>
	</body>
	</html>