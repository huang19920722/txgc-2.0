<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<#if requestURI=="/txgc/antenna/antennaOperate">
	<#assign requestURI = "/txgc/antenna/antennaList" />
</#if>
<#if requestURI=="/txgc/antenna/antennaList">
	<#assign requestURI = "/txgc/antenna/antennaList" />
</#if>
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var urls;
	var antennaDialog;
  	var antennaForm;
  	var addOrUpdate;
  	var lacForOperate="${lac!''}";
  	var choiceForOperate="${choiceForOperate!''}";
  	var urls3;
	$(function() {
	antennaForm = $('#antennaForm').form();
		if(choiceForOperate=="2"){
			urls3="${ctx}/antenna/datagrid2g";
		$("#choiceShowMessage").val("2g天线信息");
		document.getElementById("choiceShowMessage").disabled=true;
		}else if(choiceForOperate=="3"){
			urls3="${ctx}/antenna/datagrid3g";
		$("#choiceShowMessage").val("3g天线信息");
		document.getElementById("choiceShowMessage").disabled=true;
		}else if(choiceForOperate=="4"){
			urls3="${ctx}/antenna/datagrid4g";
		$("#choiceShowMessage").val("4g天线信息");
		document.getElementById("choiceShowMessage").disabled=true;
		}else if(choiceForOperate==""){
			urls3="${ctx}/antenna/datagrid3g";
		}
		datagrid = $('#datagrid').datagrid({
			url : urls3,
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
				title : '天线ID',
				width : 150,
				checkbox : true
			}] ],
			columns : [ [ {
				field : 'antennaType',
				title : '天线类型',
				width :80
			}, {
				field : 'wirelessEquipmentManufacturer',
				title : '无线设备厂家',
				width : 250
			}, {
				field : 'antennaLength',
				title : '天线长度',
				width : 150
			}, {
				field : 'lac',
				title : 'LAC',
				width : 100
			}, {
				field : 'ci',
				title : 'CI',
				width : 100
			}, {
				field : 'stationName',
				title : '基站名称',
				width : 150
			},{
				title : '操作',
				field : '1',
				width : 150,
				formatter:function(value,rec,i){
					var btnHtml="";
					<#if GeneralMethod.checkButton(requestURI,"icon-edit")>
						btnHtml="<a href='javascript:edit("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
						btnHtml+="<a href='javascript:remove();' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
					</#if>
					return btnHtml;
				}
			} ] ]
		});
		datagrid.datagrid('reload',{lac: lacForOperate});
		var urls2='';
		antennaDialog = $('#antennaDialog').show().dialog({
			modal : true,
			title : '天线信息',
			width:310,
			height:300,
			buttons : [ {
				text : '确定',
				style:'text-align:center',
				handler : function() {
				var choice=$('#choiceShowMessage').val();
					if(antennaForm.form('validate')){
					if(addOrUpdate=='update'){	
						if(choice=='2g天线信息'){
							urls2="${ctx}/antenna/update2gAntenna";
						}else if(choice=='3g天线信息'){
							urls2="${ctx}/antenna/update3gAntenna";
						}else{
							urls2="${ctx}/antenna/update4gAntenna";
						}
					}
					if(addOrUpdate=='add'){
						if(choice=='2g天线信息'){
							urls2="${ctx}/antenna/add2gAntenna";
						}else if(choice=='3g天线信息'){
							urls2="${ctx}/antenna/add3gAntenna";
						}else{
							urls2="${ctx}/antenna/add4gAntenna";
						}
					}
					$.ajax({
						url : urls2,
						data : antennaForm.serialize(),
						cache : false,
						success : function(data) {
							var msg ;
							if(data.success){
								msg = "操作成功";
								antennaForm.form('clear');
								$('#antennaDialog').dialog('close');
								clearAllSel();
								if(choice=="2g天线信息"){
										datagrid.datagrid('reload','${ctx}/antenna/datagrid2g');
									}else if (choice=="3g天线信息"){
										datagrid.datagrid('reload','${ctx}/antenna/datagrid3g');
									}else{
										datagrid.datagrid('reload','${ctx}/antenna/datagrid4g');
									}
							}else{
								msg="只有手动输入的数据才能修改";
								$('#antennaDialog').dialog('close');
							}
							$.messager.alert("提示",msg);
						}
					
					});
					}
				}			
			} ]
			
		}).dialog('close');
		$('#wirelessEquipmentManufacturer').combobox({    
		    url:'${ctx}/antenna/wirelessEquipmentManufacturer',    
		    valueField:'text',    
		    textField:'text'   
		}); 
	});
	//选择展示2、3、4g天线信息
	function changeDatagridUrl(value){
		if(value=="2g天线信息"){
			datagrid.datagrid('reload','${ctx}/antenna/datagrid2g');
		}else if (value=="3g天线信息"){
			datagrid.datagrid('reload','${ctx}/antenna/datagrid3g');
		}else{
			datagrid.datagrid('reload','${ctx}/antenna/datagrid4g');
		}
	}
	//删除选项
	function remove(){
		var rows=datagrid.datagrid('getSelections');
		var ids = [];
		if(rows.length>0){
			$.messager.confirm('请确认','您要删除当前所选项？',function(isdel){
			var choice=$('#choiceShowMessage').val();
					if(choice=='2g天线信息'){
						urls="${ctx}/antenna/delete2gById";
					}else if(choice=='3g天线信息'){
						urls="${ctx}/antenna/delete3gById";
					}else{
						urls="${ctx}/antenna/delete4gById";
					}
				if(isdel){
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
					$.ajax({
						url : urls,
						data : {
							ids: ids
						},
						cache : false,
						success:function(data){
							var msg ;
						if(data.success){
							msg = "操作成功";
							datagrid.datagrid('unselectAll');
							if(choice=="2g天线信息"){
								datagrid.datagrid('reload','${ctx}/antenna/datagrid2g');
								}else if (choice=="3g天线信息"){
								datagrid.datagrid('reload','${ctx}/antenna/datagrid3g');
								}else{
								datagrid.datagrid('reload','${ctx}/antenna/datagrid4g');
								}
						}else{
							msg="只有手动输入的数据才能删除";
						}
						$.messager.alert("提示",msg);
						}
					});
				}
			});
		}
		else {
				$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
	}
	 /*清除所有选择项*/
    function clearAllSel(){
    	$("#datagrid").datagrid('clearSelections');
    }
    //增加
	function append(){
		antennaDialog.dialog('open');
		antennaForm.form('clear');
		addOrUpdate='add';
	}
	//修改
	function edit(index){
		addOrUpdate='update';
		var row = datagrid.datagrid("getRows")[index];
		antennaForm.form('load', {
			id : row.id,
			ci:row.ci,
			lac:row.lac,
			stationName:row.stationName,
			antennaType: row.antennaType,
			wirelessEquipmentManufacturer:row.wirelessEquipmentManufacturer,
			antennaLength:row.antennaLength
		});
		antennaDialog.dialog('open');
	}
	 //查询
	function searchFun() {
		var choice=$('#choiceShowMessage').val();
		if(choice=="2g天线信息"){
			datagrid.datagrid('reload',{
					lac: $('#toolbar input[name=lac]').val(),
					antennaType: $('#toolbar input[name=antennaType]').val(),
					ci : $('#toolbar input[name=ci]').val()
					},
					'${ctx}/antenna/datagrid2g');
			}else if (choice=="3g天线信息"){
			datagrid.datagrid('reload',{
					lac: $('#toolbar input[name=lac]').val(),
					antennaType: $('#toolbar input[name=antennaType]').val(),
					ci : $('#toolbar input[name=ci]').val()
					},'${ctx}/antenna/datagrid3g');
			}else{
			datagrid.datagrid('reload',{
					lac: $('#toolbar input[name=lac]').val(),
					antennaType: $('#toolbar input[name=antennaType]').val(),
					ci : $('#toolbar input[name=ci]').val()
					},'${ctx}/antenna/datagrid4g');
		}
	}
	 /*工具栏里面类容清空*/
	function clearFun() {
		$('#toolbar input').val('');
		$("#datagrid").datagrid('load', {});
		datagrid.datagrid('unselectAll');
	
	}
	//返回
	function back(){
		window.history.go(-1);
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="search_table">
				<table>
					<tr>
					<td> 	
							<select id="choiceShowMessage" onchange="changeDatagridUrl(this.options[this.options.selectedIndex].value)" style="width:100px;background-color:silver;">   
							    <option value="3g天线信息">3g天线信息</option>  
							    <option value="2g天线信息">2g天线信息</option>    
							    <option value="4g天线信息">4g天线信息</option>      
							</select> 
						</td>
						<td>
							LAC：
							<input name="lac" class="easyui-textbox" style="height:26px;"/>
							CI：
							<input name="ci" class="easyui-textbox" style="height:26px;"/>
							天线类型：
							<input name="antennaType" class="easyui-textbox" style="height:26px;"/>	
						</td>
						<td>
							<#if GeneralMethod.checkButton(requestURI,"icon-search")>
								<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
							</#if>
								<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
							<#if GeneralMethod.checkButton(requestURI,"icon-add")>
								<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();"  href="javascript:void(0);">增 加</a> 
							</#if>
							<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
								<a class="easyui-linkbutton" iconCls="icon-remove" onclick="remove();"  href="javascript:void(0);">批量删除</a>
							</#if>
								<!--
									<a class="easyui-linkbutton" iconCls="icon-back" onclick="back();"  href="javascript:void(0);">返回</a>
								-->
						</td>
					</tr>
				</table>
		</div>
			<table id="datagrid"></table>
	</div>
	<div id="antennaDialog" style="display: none;overflow: hidden;">
		<form id="antennaForm" method="post">
			<input type="hidden" name="id" id="id"/>
			<table class="basic basic_dialog">
				<tr>
					<th width="85">LAC:</th>
					<td><input name="lac" id="lac" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" /></td>
				</tr>
				<tr>
					<th>CI:</th>
					<td><input name="ci" id="ci" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" /></td>
				</tr>
				<tr>
					<th>基站名称:</th>
					<td><input name="stationName" id="stationName" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" /></td>
				</tr>
				<tr>
					<th >天线类型:</th>
					<td><input name="antennaType" id="antennaType" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" /></td>
				</tr>
				<tr>
					<th >无线设备厂家:</th>
					<td><input id="wirelessEquipmentManufacturer" name="wirelessEquipmentManufacturer" style="width:175px; height:26px;"  required="true"></td>
				</tr>
				<tr>
					<th>天线长度:</th>
					<td><input name="antennaLength" id="antennaLength" class="easyui-textbox validatebox " style="width:155px; height:26px;"  required="true" />(米)</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>