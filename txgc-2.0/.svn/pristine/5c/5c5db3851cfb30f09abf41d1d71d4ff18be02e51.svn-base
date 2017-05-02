<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#include "/inc/defineColumns.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />



<script type="text/javascript" charset="UTF-8">

	function searchFun() {
		$("#datagrid").datagrid('load', {
			longVal : $('#toolbar input[name=longVal]').val(),
			shortVal : $('#toolbar input[name=shortVal]').val()
		});
	}
 /*工具栏里面类容清空*/
	function clearFun() {
		$('#toolbar input').val('');
		$("#datagrid").datagrid('load', {});
		datagrid.datagrid('unselectAll');
	
	}
	
	
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="search_table">
			<table>
				<tr>
					<td>
						基线长度：
						<input name="shortVal" class="easyui-textbox" style="height:26px;"/>
						到
						<input name="longVal" class="easyui-textbox" style="height:26px;"/>
					</td>

					<td>
						<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
						<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
					</td>
				</tr>
				<tr>
					<td>

					<!--	
						<a class="easyui-linkbutton" onclick="personAudit()" href="javascript:void(0);">审核数据</a>
				-->
					<a class="easyui-linkbutton" onclick="dc.showDefineDialog('1482836590971')">自定义列</a>
					</td>
				</tr>

			</table>
		</div>
		<table id="datagrid"></table>
	</div>
	<script>
	$(function(){
		$("#datagrid").datagrid({
			url:'${ctx}/rtuController/rtuInfoDataGrid',
			fitColumns:false,
			fit:true,
			toolbar:'#toolbar',
			pagination:true,
			columns:dc.defineColumns('1482836590971')
		});
	});
</script>
</body>
</html>