<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />

<script type="text/javascript" charset="UTF-8">
	var editRow;
	var treegrid001;
	var functionData = [{key:'0',value:'菜单'},{key:'1',value:'功能点'}]
	
	var iconData = [ {
		iconcls : '',
		text : '默认图标'
	}, {
		iconcls : 'icon-add',
		text : 'icon-add'
	}, {
		iconcls : 'icon-edit',
		text : 'icon-edit'
	}, {
		iconcls : 'icon-remove',
		text : 'icon-remove'
	}, {
		iconcls : 'icon-save',
		text : 'icon-save'
	}, {
		iconcls : 'icon-cut',
		text : 'icon-cut'
	}, {
		iconcls : 'icon-ok',
		text : 'icon-ok'
	}, {
		iconcls : 'icon-no',
		text : 'icon-no'
	}, {
		iconcls : 'icon-cancel',
		text : 'icon-cancel'
	}, {
		iconcls : 'icon-reload',
		text : 'icon-reload'
	}, {
		iconcls : 'icon-search',
		text : 'icon-search'
	}, {
		iconcls : 'icon-print',
		text : 'icon-print'
	}, {
		iconcls : 'icon-help',
		text : 'icon-help'
	}, {
		iconcls : 'icon-undo',
		text : 'icon-undo'
	}, {
		iconcls : 'icon-redo',
		text : 'icon-redo'
	}, {
		iconcls : 'icon-back',
		text : 'icon-back'
	}, {
		iconcls : 'icon-sum',
		text : 'icon-sum'
	}, {
		iconcls : 'icon-tip',
		text : 'icon-tip'
	},{
		iconcls : 'icon-up',
		text : 'icon-up'
	},{
		iconcls : 'icon-down',
		text : 'icon-down'
	},{
		iconcls:'icon-pencil',
		text:'icon-pencil'
	},{
		iconcls:'icon-audit',
		text:'icon-audit'
	},{
		iconcls:'icon-addChild',
		text:'icon-addChild'
	},{
		iconcls:'icon-video',
		text:'icon-video'
	},{
		iconcls:'icon-file',
		text:'icon-file'
	},{
		iconcls:'icon-lock',
		text:'icon-lock'
	},{
		iconcls:'icon-unlock',
		text:'icon-unlock'
	},{
		iconcls:'icon-person',
		text:'icon-person'
	}  ];
	$(function() {
		
		treegrid001 = $('#treegrid001').treegrid({
			url : '${ctx}/menu/treegrid',
			rownumbers:true,
			toolbar : '#toolbar001',
			fit : true,
			fitColumns : true,
			nowrap : true,
			animate : false,
			border : false,
			idField : 'id',
			treeField : 'text',
			frozenColumns : [ [{
				field : 'text',
				title : '菜单名称',
				width : 200,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				}
			} ] ],
			columns : [ [  {
				field : 'parentId',
				title : '上级菜单',
				width : 200,
				formatter : function(value, rowData, rowIndex) {
					return rowData.parentText;
				},
				editor : {
					type : 'combotree',
					options : {
						url : '${ctx}/menu/treeAll',
						animate : false,
						lines : !sy.isLessThanIe8(),
						onLoadSuccess : function(node, data) {
							var t = $(this);
							if(editRow._parentId){
								var pNode=t.tree("find",editRow._parentId);
								t.tree("collapseAll");
								t.tree("expandTo",pNode.target);
								t.tree("select",pNode.target);
							}else{
								t.tree("collapseAll");
							}
						}
					}
				}
			}, {
				field : 'parentText',
				title : '上级菜单',
				width : 80,
				hidden : true
			},{
				field : 'func',
				title : '类型',
				width : 150,
				formatter : function(v) {
					if('0' == v) {
						return "菜单";
					} else if('1' == v) {
						return "功能点";
					} else {
						return "";
					}
			    },
				editor : {
					type : 'combobox',
					options : {
						valueField : 'key',
						textField : 'value',
						panelHeight : '40',
						data : functionData,
						formatter : function(v) {
							return v.value;
						}
					}
				}
			},{
				field : 'iconCls',
				title : '菜单图标',
				width : 150,
				editor : {
					type : 'combobox',
					options : {
						valueField : 'iconcls',
						textField : 'text',
						panelHeight : '200',
						data : iconData,
						formatter : function(v) {
							return sy.fs('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.iconcls, v.text);
						}
					}
				}
			}, {
				field : 'seq',
				title : '排序',
				width : 50,
				editor : {
					type : 'numberbox',
					options : {
						min : 0,
						max : 999,
						required : true
					}
				}
			},
			
			/*
			{
				title : 'ID',
				field : 'id',
				width : 100
			}, 
			
			*/
			{
				field : 'src',
				title : '菜单地址',
				width : 200,
				editor : {
					type : 'text'
				}
			} ] ],
			onDblClickRow : function(row) {
				if (editRow) {
					myMessage('您没有结束之前编辑的数据，请先保存或取消编辑！');
				} else {
					treegrid001.treegrid('beginEdit', row.id);
					editRow = row;
				}
			},
			onAfterEdit : function(row, changes) {
				if (row.parentId != row.id) {
					$.ajax({
						url : '${ctx}/menu/edit',
						data : JSON.stringify(row),
						cache : false,
						dataType : "json",
						contentType:"application/json",
						success : function(r) {
							if (r.success) {
								myMessage(r.msg)
								treegrid001.treegrid('reload');
								//parent.tree.tree('reload');
								editRow = undefined;
							} else {
								myMessage("保存菜单失败")
							}
						}
					});
				} else {
				    myMessage("保存失败，上级菜单不可以是自己");
				    editRow =undefined;
				}
			},
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onLoadSuccess : function(row, data) {
				
			},
			onExpand : function(row) {
				treegrid001.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				treegrid001.treegrid('unselectAll');
			}
		});

	});

	function edit() {
	 
		if (editRow) {
		    myMessage("您没有结束之前编辑的数据，请先保存或取消编辑！");
		} else {
			var node = treegrid001.treegrid('getSelected');
			if (node && node.id) {
				treegrid001.treegrid('beginEdit', node.id);
				editRow = node;
			}else{
				 myMessage("请选择要编辑的数据!");
			}
		}
	}
	function append() {
		// treegrid.treegrid('collapseAll');
		if (editRow) {
		    myMessage("您没有结束之前编辑的数据，请先保存或取消编辑！");
		} else {
			var node = treegrid001.treegrid('getSelected');
			var data = [ {
				id : '',
				text : '新菜单',
				src : '',
				seq : 999,
				state:'0',
				func:'0',
				parentId : (node ? node.id : '')
			} ];
			var opts = {
				parent : data[0].parentId,
				data : data
			};
			sy.ajaxSubmit({
				url : '${ctx}/menu/add',
				data : data[0],
				cache : false,
				dataType : "json",
				success : function(r) {
					if (r.success) {
						opts.data[0].id=r.msg;
						treegrid001.treegrid('append', opts);
						treegrid001.treegrid('beginEdit', opts.data[0].id);
						editRow = opts.data[0];
						treegrid001.treegrid("expandTo",opts.data[0].id);
					} else {
					    myMessage("添加菜单失败!");
					}
				}
			});
		}
	}
	function remove1() {
		var node = treegrid001.treegrid('getSelected');
		if (node) {
			if (node.id == '0') {
			    myMessage("不能删除根节点!");
				return;
			}
			$.messager.confirm('询问', '您确定要删除【' + node.text + '】菜单？', function(b) {
				if (b) {
					$.ajax({
						url : '${ctx}/menu/del',
						data : {
							id : node.id
						},
						cache : false,
						dataType : "json",
						success : function(r) {
							if (r.success) {
								treegrid001.treegrid('reload');
								//parent.tree.tree('reload');
								myMessage(r.msg);
								editRow = undefined;
							} else {
							    myMessage("删除菜单失败!");
							}
						}
					});
				}
			});
		}
	}
	function myMessage(mg)
	{
	   $.messager.alert('提示',mg);
	}
	
	function expandAll(){
		var node = treegrid001.treegrid('getSelected');
		if (node) {
			treegrid001.treegrid('expandAll', node.id);
		} else {
			treegrid001.treegrid('expandAll');
		}
	}
	
	function collapseAll(){
		var node = treegrid001.treegrid('getSelected');
		if (node) {
			treegrid001.treegrid('collapseAll', node.id);
		} else {
			treegrid001.treegrid('collapseAll');
		}
	}
	function refresh(){
		editRow = undefined;
		treegrid001.treegrid('reload');
	}
	function save(){
		if (editRow) {
			treegrid001.treegrid('endEdit', editRow.id);
		}
	}
	function cancelEdit(){
		if (editRow) {
			treegrid001.treegrid('cancelEdit', editRow.id);
			editRow = undefined;
			treegrid001.treegrid('reload');
		}
	}
	function unselectAll(){
		treegrid001.treegrid('unselectAll');
	}
</script>
</head>
<body id ="menuid" class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar001" class="search_table" >
				<table>
					<tr>
						<td>
						
							<a class="easyui-linkbutton" iconCls="icon-redo"  onclick="expandAll();" href="javascript:void(0);">展开</a>
							<a class="easyui-linkbutton" iconCls="icon-undo"  onclick="collapseAll();" href="javascript:void(0);">折叠</a>
							<a class="easyui-linkbutton" iconCls="icon-reload"  onclick="refresh();" href="javascript:void(0);">刷新</a>
							<a class="easyui-linkbutton" iconCls="icon-add"  onclick="append();" href="javascript:void(0);">增加</a>
							<a class="easyui-linkbutton" iconCls="icon-remove"  onclick="remove1();" href="javascript:void(0);">删除</a>
							<a class="easyui-linkbutton" iconCls="icon-edit"  onclick="edit();" href="javascript:void(0);">编辑</a>
							<a class="easyui-linkbutton" iconCls="icon-save"  onclick="save();" href="javascript:void(0);">保存</a>
							<a class="easyui-linkbutton" iconCls="icon-reset"  onclick="cancelEdit();" href="javascript:void(0);">取消编辑</a>
							<a class="easyui-linkbutton" iconCls="icon-reset"  onclick="unselectAll();" href="javascript:void(0);">取消选中</a>
							
						</td>
					</tr>
				</table>
			</div>
		<table id="treegrid001"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<#if GeneralMethod.checkButton(requestURI,"icon-add")>
			<div onclick="append();" iconCls="icon-add">增加</div>
		</#if>
		<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
			<div onclick="remove1();" iconCls="icon-remove">删除</div>
		</#if>
		<#if GeneralMethod.checkButton(requestURI,"icon-edit")>
			<div onclick="edit();" iconCls="icon-edit">编辑</div>
		</#if>
	</div>
</body>
</html>