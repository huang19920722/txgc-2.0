<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	$(function() {
		
		userForm = $('#userForm').form();


		datagrid = $('#datagrid').datagrid({
			url : '${ctx}/log/datagrid',
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
				field : 'useTime',
				title : '操作时间',
				width : 150,
				sortable : true
			}] ],
			columns : [ [ {
				field : 'userName',
				title : '操作人',
				width :80
			}, {
				field : 'ip',
				title : 'ip',
				width : 50
			}, {
				field : 'note',
				title : '操作内容',
				width : 450
			}] ],
			onLoadSuccess:function(data) {
				
			}
		});
	});
	function remove() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${ctx}/log/del',
						data : {
							ids : ids.join(',')
						},
						cache : false,
						dataType : "json",
						success : function(response) {
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('reload');
							myMessage("删除成功");
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
	}

	function searchFun() {
		datagrid.datagrid('load', {
			userName : $('#toolbar input[name=userName]').val(),
			note : $('#toolbar input[name=note]').val()
		});
	}
</script>



</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="search_table">
				<table>
					<tr>
						<td >
							操作人：
							<input name="userName" class="easyui-textbox" style="height:26px;" />
						</td>
						<td>
							操作内容：
							<input name="note" class="easyui-textbox" style="height:26px;" />
						</td>
						<td>
						<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
						<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
						</td>
					</tr>
				</table>
		</div>
		<table id="datagrid"></table>
	</div>
</body>
</html>