<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var organDialog;
	var organForm;
	var selectedNodeId;
	var url;
	var $areaCombobox;//区域combobox
	
	$(function() {

		organForm=$("#organForm").form();
		organDialog = $('#organDialog').show().dialog({
			modal : true,
			title : "新增机构",
			width:300,
			height:300,
			onBeforeClose:function(){
				organForm.form('clear');
				$('#orgName').val('');
				$('#orgCode').val('');
				$('#legalRep').val('');
				$('#described').val('');
				$('#parentName').val('');
				$("#nodeLevel").val('');
				$('#parentId').val('');
			},
			buttons : [ {
				text : '确定',
				style:'text-align:center',
				handler : function() {
					save();
				}
			} ]
		}).dialog('close');
		

		$areaCombobox=$('#areaCombobox').combobox({    
						    url:'${ctx}/organ/findAreaList',    
						    valueField:'code',
						    textField:'name'
						});  

		var treegrid = $('#treegrid').treegrid({
			title : '',
			iconCls : 'icon-save',
			rownumbers:true,
			fit : true,
			fitColumns : true,
			nowrap : true,
			animate : false,
			border : false,
			idField : 'id',
			treeField : 'orgName',
			frozenColumns : [
			[
			{
				checkbox:true,
				field:""
			},
			{
				field : 'orgName',
				title : '机构名称',
				width : 200
			}]],
			columns : [[
			{
				field : 'orgCode',
				title : '机构代码',
				width : 50
			},{
				field : 'legalRep',
				title : '主责单位',
				width : 50
			},{
				field : 'areaName',
				title : '所在区域',
				width : 50
			}
			
			,{
				field : 'orgDesc',
				title : '机构简介',
				width : 150
			}
			
			]],
			onLoadSuccess:function(row,data){
				$("#treegrid").treegrid("select",selectedNodeId)
			},
			onBeforeExpand:function(node){
				var root=$("#treegrid").treegrid("getRoot");
				if(node.id!=root.id){
					$("#treegrid").treegrid("options").url='${ctx}/organ/treegrid?orgId='+node.id;
					//$("#treegrid").treegrid("reload",node.id);
				}
				/*if(node.id!=n.id){
					//获取是否已经加载子节点
					var childs=$("#treegrid").treegrid("getChildren",node.id)
					$.ajax({
						url : '${ctx}/organ/treegrid?orgId='+node.id,
						cache : false,
						dataType : "json",
						success : function(d) {
							//将已经添加的子菜单排除
							for(var i=0;i<d.rows.length;i++){
								for(var j=0;j<childs.length;j++){
									if(d.rows[i].id==childs[i].id){
										d.rows.splice(i,1);
										d.total=d.total-1;
									}
								}
							}
							if(d.total>0){
								$('#treegrid').treegrid('append',{
										parent: node.id,  // 这里指定父级标识就可以了
										data: d.rows
								});
							}
							$("#treegrid").treegrid("expand",node.id);
						}
					});
				}*/
			}
		});
		buildTreeGrid();
		
		
	});
	function buildTreeGrid(id){
		//销毁已存在的树
		$("#treegrid").empty();
		if(id){
			selectedNodeId=id;
		}else{
			selectedNodeId=0;
		}
		var rootData={total:1,rows:[{"id":0,"orgName":"根节点","state":"closed"}]};
		//获取一级菜单信息
		$.ajax({
			url : '${ctx}/organ/treegrid',
			cache : false,
			dataType : "json",
			success : function(data) {
				rootData.rows[0].children=data.rows
				$("#treegrid").treegrid("loadData",rootData);
				$("#treegrid").treegrid("expand",0);
			}
		});
	}
	function remove(){
		var node=$("#treegrid").treegrid("getSelected");
		selectedNodeId=node.id;
		var root=$("#treegrid").treegrid("getRoot");
		if(!node){
			$.messager.alert('提示',"当前没有选中任何机构！");
			return false;
		}
		if(node.id==root.id){
			$.messager.alert('提示',"不能删除根节点！");
			return false;
		}
		$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
			if (r) {
				var ids = new Array();
			    ids.push(node.id);	
				$.ajax({
					url : '${ctx}/organ/del',
					data :JSON.stringify(ids),
					cache : false,
					dataType : "json",
					contentType:"application/json",
					success : function(d) {
						if(d){
							$.messager.alert('提示',"操作成功");
							if(node._parentId){
								selectedNodeId=node.id;
								$("#treegrid").treegrid("reload",node._parentId);
							}else{
								buildTreeGrid(node.id);
							}
						}else{
								$.messager.alert('提示',"操作失败");
						}
					}
				});
			}
		});
	}
	function edit(){
		var node=$("#treegrid").treegrid("getSelected");
		var root=$("#treegrid").treegrid("getRoot");
		if(!node){
			$.messager.alert('提示',"当前没有选中任何机构！");
			return false;
		}
		if(node.id==root.id){
			$.messager.alert('提示',"不能修改根节点！");
			return false;
		}
		$.ajax({
			url : '${ctx}/organ/search',
			data : {id:node.id},
			cache : false,
			dataType : "json",
			success : function(data) {
				/*
				for(var i  in data){
				console.log(">>>key>"+i+"<<<Value>>>"+data[i]);
				}
				*/
				if(!data.parentId){
					data.parentName="根节点";
				}
				organForm.form('load', {
					"id":data.id,
					"orgName":data.orgName,
					"orgCode":data.orgCode,
					"legalRep":data.legalRep,
					"orgDesc":data.orgDesc,
					"parentName":data.parentName,
					"nodeLevel" : data.nodeLevel,
					"parentId":data.pid
				});
				$areaCombobox.combobox('setValue',data.areaCode);
				
			}
		});
		openDialog(true);
	}
	function add(){
		var node=$("#treegrid").treegrid("getSelected");
		if(!node){
			$.messager.alert('提示',"当前没有选中任何机构！");
			return;
		}
		$('#parentId').val(node.id);
		$('#parentName').val(node.orgName);
		openDialog(false);
	}
	function save(){
		if(organForm.form('validate')){
		
			$("input[name='areaName']").val($areaCombobox.combobox('getText'));
			$.ajax({
				url : url,
				data : organForm.serialize(),
				cache : false,
				dataType : "json",
				success : function(data) {
					$.messager.alert('提示',"操作成功");
					var pid=$('#parentId').val();
					var root=$("#treegrid").treegrid("getRoot");
					var n=$("#treegrid").treegrid("getSelected");
					if(n._parentId == null){
					
						buildTreeGrid(n.id);
					}else{
						selectedNodeId=n.id;
					
						$("#treegrid").treegrid("reload",n._parentId);
						$("#treegrid").treegrid("expandTo",n._parentId);
					}
					$('#organDialog').dialog('close');
					
					$('#parentId').val("");
				}
			});
		}
	}
    function openDialog(flag){
		var title="添加机构"
		url='${ctx}/organ/add'
		if(flag){
			title="修改机构"
			url='${ctx}/organ/update'
		}
		organDialog.panel({"title":title});
		organDialog.dialog("open");
	}
	function move(flag){
		var node=$("#treegrid").treegrid("getSelected");
		var root=$("#treegrid").treegrid("getRoot");
		if(!node){
			$.messager.alert('提示',"当前没有选中任何机构！");
			return false;
		}
		if(node.id==root.id){
			$.messager.alert('提示',"不能修改根节点！");
			return false;
		}
		$.ajax({
			url : '${ctx}/organ/updateSort',
			data : {"id":node.id,"moveFlag":flag},
			cache : false,
			dataType : "json",
			success : function(data) {
				var root=$("#treegrid").treegrid("getRoot");
				var n=$("#treegrid").treegrid("getSelected");
				if(n._parentId==null){
					buildTreeGrid(n.id);
				}else{
					selectedNodeId=n.id;
					$("#treegrid").treegrid("reload",n._parentId);
					
				}
				
			}
		});
	}
</script>
</head>
<body class="easyui-layout">
	<div region="center" border="false" style="overflow: hidden;">
		<div  class="search_table datagrid-toolbar" style="display:block;">
			<table>
				<tr>
					<td>
						<#if GeneralMethod.checkButton(requestURI,"icon-add")>
							<a href="javascript:add();" class="easyui-linkbutton" iconCls="icon-add"  >新  增</a>
						</#if>
						<#if GeneralMethod.checkButton(requestURI,"icon-edit")>	
							<a href="javascript:edit();" class="easyui-linkbutton" iconCls="icon-edit"  >修  改</a>
						</#if>
						<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
							<a href="javascript:remove();" class="easyui-linkbutton" iconCls="icon-remove"  >删 除</a>
						</#if>
						<#if GeneralMethod.checkButton(requestURI,"icon-up")>
							<a href="javascript:move(1);" class="easyui-linkbutton" iconCls="icon-up"  >上 移</a>
						</#if>
						<#if GeneralMethod.checkButton(requestURI,"icon-down")>
							<a href="javascript:move(0);" class="easyui-linkbutton" iconCls="icon-down"  >下 移</a>
						</#if>
					</td>
				</tr>
			</table>
		</div>
		<table id="treegrid"  ></table>
	</div>
	<div id="organDialog" style="display: none;overflow-x:hidden;">
			<form id="organForm" method="post">
				<table class="basic basic_dialog">
					<tr>
						<th width="85" >机构名称：</th>
						<td>
							<input name="orgName" id="orgName" class="easyui-textbox validatebox "  style="width:170px; height:26px;" required="true" missingMessage="此项必须填写"/>
							<input name="nodeLevel" id="nodeLevel" type="hidden" >
							<input name="id" id="id" type="hidden" >
						</td>
					</tr>
					<tr>
						<th  >机构代码：</th>
						<td><input name="orgCode" id="orgCode" class="easyui-textbox validatebox "  style="width:170px; height:26px;"  required="true" missingMessage="此项必须填写" /></td>
					</tr>
					<tr>
						<th  >主责单位：</th>
						<td><input name="legalRep" id="legalRep" class="easyui-textbox validatebox "  style="width:170px; height:26px;"  required="true" missingMessage="此项必须填写"/></td>
					</tr>
					<tr>
						<th  >描&#12288;&#12288;述：</th>
						<td><input name="orgDesc" id="orgDesc" class="easyui-textbox validatebox "  style="width:170px; height:26px;" /></td>
					</tr>
					<tr>
						<th  >上级机构：</th>
						<td>
							<input id="parentName" name="parentName"  class="textbox" style="width:170px; height:26px;" readonly="true" />
							<input name="parentId" id="parentId" type="hidden" >
						</td>
					</tr>
					<tr>
						<th >所在区域：</th>
						<td>
							<input id="areaCombobox" name="areaCode"  class="textbox" style="width:170px; height:26px;"  />
							<input name="areaName" type="hidden"/>
						</td>
					</tr>
				</table>
			</form>
	</div>
</body>
</html>