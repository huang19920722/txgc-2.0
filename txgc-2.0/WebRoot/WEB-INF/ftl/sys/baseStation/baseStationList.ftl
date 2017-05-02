<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var datagrid;//基站datagrid
	var datagridAnn;//天线datagrid
	var urls;//天线查询URL
	var baseStationDialog;//基站创建和修改弹窗
  	var baseStationForm;//基站表单
  	var antennaDialog;//天线弹窗
  	var addOrUpdate;//修改或更新状态字段
	$(function() {
	baseStationForm = $('#baseStationForm').form();
		datagrid = $('#datagrid').datagrid({
			url : '${ctx}/baseStation/datagrid3g',
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
				title : '基站ID',
				width : 80,
				checkbox : true
			}] ],
			columns : [ [ {
				field : 'lac',
				title : 'LAC',
				width : 100
			},{
				field : 'stationName',
				title : '基站名称',
				width : 100
			},{
				field : 'stationType',
				title : '基站类型',
				width :80
			}, {
				field : 'transmissionMedium',
				title : '基站传输介质',
				width : 150
			}, {
				field : 'openTime1',
				title : '基站开通时间',
				width : 155
			}, {
				field : 'detailAdress',
				title : '基站详细地址',
				width : 200
			}, {
				field : 'enbIp',
				title : '基站IP',
				width : 200
			},{
				title : '操作',
				field : '1',
				width : 250,
				formatter:function(value,rec,i){
					var btnHtml="";
					<#if GeneralMethod.checkButton(requestURI,"icon-edit")>
						btnHtml="<a href='javascript:edit("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
						btnHtml+="<a href='javascript:remove();' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-search")>
						btnHtml+="<a href='javascript:see("+i+");' plain='true'  iconcls='icon-search' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-search' style='padding-left: 20px;'>查看天线</span></span></a>";
					</#if>
					return btnHtml;
				}
			} ] ]
		});
		var urls2='';
		baseStationDialog = $('#baseStationDialog').show().dialog({
			modal : true,
			title : '基站信息',
			width:310,
			height:300,
			buttons : [ {
				text : '确定',
				style:'text-align:center',
				handler : function() {
				var choice=$('#choiceShowMessage').val();
					if(baseStationForm.form('validate')){
					if(addOrUpdate=='update'){	
						if(choice=='2'){
							urls2="${ctx}/baseStation/update2gBaseStation";
						}else if(choice=='3'){
							urls2="${ctx}/baseStation/update3gBaseStation";
						}else{
							urls2="${ctx}/baseStation/update4gBaseStation";
						}
					}
					if(addOrUpdate=='add'){
						if(choice=='2'){
							urls2="${ctx}/baseStation/add2gBaseStation";
						}else if(choice=='3'){
							urls2="${ctx}/baseStation/add3gBaseStation";
						}else{
							urls2="${ctx}/baseStation/add4gBaseStation";
						}
					}
					$.ajax({
						url : urls2,
						data : baseStationForm.serialize(),
						cache : false,
						success : function(data) {
							var msg ;
							if(data.success){
								msg = "操作成功";
								baseStationForm.form('clear');
								$('#baseStationDialog').dialog('close');
								clearAllSel();
								if(choice=="2"){
										datagrid.datagrid('reload','${ctx}/baseStation/datagrid2g');
									}else if (choice=="3"){
										datagrid.datagrid('reload','${ctx}/baseStation/datagrid3g');
									}else{
										datagrid.datagrid('reload','${ctx}/baseStation/datagrid4g');
									}
							}else{
								msg="只有手动输入的数据才能修改";
								$('#baseStationDialog').dialog('close');
							}
							$.messager.alert("提示",msg);
						}
					
					});
					}
				}			
			} ]
			
		}).dialog('close'); 
	});
	//选择展示2、3、4g基站信息
	function changeDatagridUrl(value){
		if(value=="2"){
			datagrid.datagrid('reload','${ctx}/baseStation/datagrid2g');
		}else if (value=="3"){
			datagrid.datagrid('reload','${ctx}/baseStation/datagrid3g');
		}else{
			datagrid.datagrid('reload','${ctx}/baseStation/datagrid4g');
		}
	}
	//删除选项
	function remove(){
		var rows=datagrid.datagrid('getSelections');
		var lacs = [];
		if(rows.length>0){
			$.messager.confirm('请确认','您要删除当前所选项？',function(isdel){
				var choice=$('#choiceShowMessage').val();
					if(choice=='2'){
						urls="${ctx}/baseStation/delete2gByLac";
					}else if(choice=='3'){
						urls="${ctx}/baseStation/delete3gByLac";
					}else{
						urls="${ctx}/baseStation/delete4gByLac";
					}
				if(isdel){
					for(var i=0;i<rows.length;i++){
						lacs.push(rows[i].lac);
					}
					$.ajax({
						url : urls,
						data : {
							lacs: lacs
						},
						cache : false,
						success:function(data){
							var msg ;
						if(data.success){
							msg = "操作成功";
							datagrid.datagrid('unselectAll');
							if(choice=="2"){
								datagrid.datagrid('reload','${ctx}/baseStation/datagrid2g');
								}else if (choice=="3"){
								datagrid.datagrid('reload','${ctx}/baseStation/datagrid3g');
								}else{
								datagrid.datagrid('reload','${ctx}/baseStation/datagrid4g');
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
		baseStationDialog.dialog('open');
		baseStationForm.form('clear');
		addOrUpdate='add';
	}
	//修改
	function edit(index){
		addOrUpdate='update';
		var row = datagrid.datagrid("getRows")[index];
		baseStationForm.form('load', {
			id : row.id,
			lac:row.lac,
			stationName:row.stationName,
			stationType: row.stationType,
			transmissionMedium:row.transmissionMedium,
			openTime:row.openTime,
			detailAdress:row.detailAdress,
			enbIp:row.enbIp
		});
		baseStationDialog.dialog('open');
	}
	//查看基站所有天线
	function see(index){
	
		antennaDialog = $('#antennaDialog').show().dialog();
		var row = datagrid.datagrid("getRows")[index];
		if(row!=null){
		var choice=$('#choiceShowMessage').val();
			if(choice=='2'){
				urls="${ctx}/antenna/datagrid2g";
			}else if(choice=='3'){
				urls="${ctx}/antenna/datagrid3g";
			}else{
				urls="${ctx}/antenna/datagrid4g";
			}
			
			datagridAnn = $('#datagridAnn').datagrid({
			url : urls+"?lac="+row.lac,
			toolbar : '',
			title : '',
			iconCls : 'icon-save',
			rownumbers: true,
			pagination : false,
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
				width : 150
				
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
			}] ]
		});
			
		}	
	}
	 //查询
	function searchFun() {
		var choice=$('#choiceShowMessage').val();
		if(choice=="2"){
			datagrid.datagrid('reload',{
					lac: $('#toolbar input[name=lac]').val(),
					stationType: $('#toolbar input[name=stationType]').val()
					},
					'${ctx}/baseStation/datagrid2g');
			}else if (choice=="3"){
			datagrid.datagrid('reload',{
					lac: $('#toolbar input[name=lac]').val(),
					stationType: $('#toolbar input[name=stationType]').val()
					},'${ctx}/baseStation/datagrid3g');
			}else{
			datagrid.datagrid('reload',{
					lac: $('#toolbar input[name=lac]').val(),
					stationType: $('#toolbar input[name=stationType]').val()
					},'${ctx}/baseStation/datagrid4g');
		}
	}
	 /*工具栏里面类容清空*/
	function clearFun() {
		$('#toolbar input').val('');
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
	//更多操作
	function moreOperate(){
		var urls3;
		var choiceForOperate=$('#choiceShowMessage').val();
		var lacforOperate=$("#lacforOperate").val();
		if(choiceForOperate=='2'){																			
				urls3="/txgc/antenna/antennaOperate?choiceForOperate="+choiceForOperate+"&lacforOperate="+lacforOperate;
			}else if(choiceForOperate=='3'){
				urls3="/txgc/antenna/antennaOperate";
			}else{
				urls3="/txgc/antenna/antennaOperate";
			};
		window.location.href=urls3; 
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
							    <option value="3">3g基站信息</option>  
							    <option value="2">2g基站信息</option>    
							    <option value="4">4g基站信息</option>      
							</select> 
						</td>
						<td>
							LAC：
							<input name="lac" class="easyui-textbox" style="height:26px;"/>
						</td>
						<td>
							基站类型：
							<input name="stationType" class="easyui-textbox" style="height:26px;"/>
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
						
						</td>
					</tr>
				</table>
		</div>
			<table id="datagrid"></table>
	</div>
	<div id="baseStationDialog" style="display: none;overflow: hidden;">
		<form id="baseStationForm" method="post">
			<input type="hidden" name="id" id="id"/>
			<table class="basic basic_dialog">
				<tr>
					<th width="85">LAC:</th>
					<td><input name="lac" id="lac" class="easyui-textbox validatebox " style="width:175px; height:26px;" required="true"  /></td>
				</tr>
				<tr>
					<th width="85">基站名称:</th>
					<td><input name="stationName" id="stationName" class="easyui-textbox validatebox " style="width:175px; height:26px;" required="true"  /></td>
				</tr>
				<tr>
					<th width="85">基站类型:</th>
					<td><input name="stationType" id="stationType" class="easyui-textbox validatebox " style="width:175px; height:26px;"  /></td>
				</tr>
				<tr>
					<th width="85">基站传输介质:</th>
					<td><input name="transmissionMedium" id="transmissionMedium" class="easyui-textbox validatebox " style="width:175px; height:26px;"  /></td>
				</tr>
				<tr>
					<th width="85">基站开通时间:</th>
					<td><input name="openTime" id="openTime" editable="false" class="easyui-datetimebox" style="width:175px;height:26px;" onfocus="foxDateTimeBox()"/></td>
				</tr>
				<tr>
					<th width="85">基站详细地址:</th>
					<td><input name="detailAdress" id="detailAdress" class="easyui-textbox validatebox " style="width:175px; height:26px;"  /></td>
				</tr>
				<tr>
					<th width="85">基站IP:</th>
					<td><input name="enbIp" id="enbIp" class="easyui-textbox validatebox " style="width:175px; height:26px;" /></td>
				</tr>
			</table>
		</form>
	</div>
	
		<div id="antennaDialog" title="天线信息" style="display: none;overflow: hidden;width:800px;height:200px;">
			<table id="datagridAnn" >
			</table>
		</div>
</body>
</html>