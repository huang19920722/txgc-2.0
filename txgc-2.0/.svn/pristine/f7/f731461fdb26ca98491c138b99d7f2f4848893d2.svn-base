<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>

<#assign requestURI = springMacroRequestContext.getRequestUri() />
<style>
	.dialog-button {
		padding: 5px;
		text-align: center;
	}
 	table#collectInfoTab tr th{
		text-align:center;
	}
</style>	
<script type="text/javascript" charset="UTF-8">
	var urlConfigId="${urlConfigId!''}";
	var $collectInfoDatagrid;//采集信息
	var $collectDialog;//采集信息详情弹窗
	
	
	$(function(){
	var usrl='${ctx}/rtuController/rtuCollectInfoDataGrid';
	if(urlConfigId != ""){
		usrl='${ctx}/rtuController/rtuCollectInfoDataGrid?rtuConfigId='+urlConfigId;
	}

		$collectInfoDatagrid=$("#collectInfoDatagrid").datagrid({
			url:usrl,
			singleSelect:true,
			toolbar:'#toolbar',
			idField : 'id',
			rownumbers:true,
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 20,
			border:1,
			pageList : [5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			frozenColumns : [[ {
				title : 'id',
				field : 'id',
				width : 50,
				checkbox : true
			}]],
			columns : [ [ 
				{
					field : 'name',
					title : 'RTU名称',
					align:'center',
					width : 200,
					formatter:function(value,rec,i){
						if(rec.rtu != null){
							return rec.rtu.rtuName;
						}
					}
					
				},{
					field : 'adress',
					title : 'RTU地址',
					align:'center',
					width : 200,
					formatter:function(value,rec,i){
						if(rec.rtu != null){
							return rec.rtu.rtuAdress;
						}
					}
				},{
					field : 'sid',
					title : 'SID序列',
					align:'center',
					width : 200
				},{
					field : 'uploadTime',
					title : '上传时间',
					width : 200,
					align:'center',
					sortable : true
				},{
					field : 'slaveState',
					title : '寄存器采集状态',
					align:'center',
					width : 200,
					formatter:function(value,rec,i){
						if(parseInt(value) == 0){
							return "正常";
						}else{
							return "异常";
						}
					}
				},{
					title : '操作',
					field : '1',
					align:'center',
					width :200,
					formatter:function(value,rec,i){
						var btnHtml="";
							btnHtml+="<a href='javascript:showDetail("+rec.id+");' plain='true'  iconcls='icon-tip' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-tip' style='padding-left: 20px;'>更多信息</span></span></a>";
						return btnHtml;
					}
			} ]],
			onDblClickRow:function(rowIndex, rowData){
				var id=rowData.id;
				showDetail(id);
			}
		});
	});


	function searchFun() {
		$("#collectInfoDatagrid").datagrid('load', {
			sid : $('#toolbar input[name=sid]').val(),
			uploadTimeStart: $('#toolbar input[name=uploadTimeStart]').val(),
			uploadTimeEnd: $('#toolbar input[name=uploadTimeEnd]').val(),
			rtuName: $('#toolbar input[name=rtuName]').val(),
			rtuAdress: $('#toolbar input[name=rtuAdress]').val()
		});
	}
 /*工具栏里面类容清空*/
	function clearFun() {
		$('#toolbar input').val('');
		$("#collectInfoDatagrid").datagrid('load', {});
		$collectInfoDatagrid.datagrid('unselectAll');
	
	}
	
	//查询RTU采集信息信息
	function showDetail(collectId){
		//初始化弹窗
		if($collectDialog != null){
			$collectDialog=null;
		}
		$collectDialog=$("#collectDialog").show().dialog({
			modal : true,
			title : '采集信息详情',
			width:600,
			height:450,
			buttons : [ {
				text : '关闭',
				style:'text-align:center',
				handler : function() {
					$collectDialog.dialog('close');
				}
			}],
			onClose:function(){
				$("#collectInfoTab tr:not(:first)").remove();
			}
		}).dialog('close');
		//collectInfoTab
		$.ajax({
			url : '${ctx}/rtuController/queryCollectInfoByCollectId',
			data : {
				collectId:collectId
			},
			type:'POST',
			cache : false,
			success:function(data){
				if(data!=null && data.success && data.obj != null){
				
					var htmlStr="";
					for(var i=0;i<data.obj.length;i++){
						htmlStr+="<tr><td>"+data.obj[i].regName+"</td><td>"+data.obj[i].regValue+"</td></tr>";
					//<td>"+data.obj[i].regState+"</td>
					}
					$("#collectInfoTab").append(htmlStr);
				}
			}
		});

		$collectDialog.dialog('open');
	}



	
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="search_table">
			<table>
				<tr>
					<th>RTU名称：</th>	
					<td><input name="rtuName" class="easyui-textbox" style="height:26px;"/></td>	
					<th>RTU地址：</th>
					<td><input name="rtuAdress" class="easyui-textbox" style="height:26px;"/></td>
					<th>上传时间：</th>
					<td><input name="uploadTimeStart" class="easyui-datetimebox" style="height:26px;"/>
					至
					<input name="uploadTimeEnd" class="easyui-datetimebox" style="height:26px;"/>
					</td>
					<td>
					</td>

				</tr>
				<tr>
				<th>sid：</th>
				<td ><input name="sid" class="easyui-textbox" style="height:26px;"/></td>
				
				<td colspan="5" >
					<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
					<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
				</td>
				</tr>


			</table>
		</div>
		<table id="collectInfoDatagrid"></table>
	</div>
	
	<!--RTU采集信息详情弹窗-->
	<div id="collectDialog" style="display: none;overflow-x:hidden;">
			<table id="collectInfoTab" class="basic basic_dialog">
				<tr>
					<th width="200px">属性名称</th>
					<th>属性值</th>
					<!--<th>属性状态</th>-->
				</tr>
			</table>
		
	</div>

</body>
</html>