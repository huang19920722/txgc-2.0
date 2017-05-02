<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var terminalInfoDialog;
  	var terminalInfoForm;
  	var assignDialog;
  	var assignForm;
  	var otherDialog;
  	var otherForm;
  	var addOrUpdate;
  	var organTree;
  	var $userCobobox;
  	
  	var $mapDialog;//地图对象
  	$(function() {
		terminalInfoForm = $('#terminalInfoForm').form();
		assignForm = $('#assignForm').form();
		otherForm = $('#otherForm').form();
		organTree=$("[name=orgId]").combobox({    
		    url:'${ctx}/terminalInfo/findOrgByCurrentUser',    
		    valueField:'id',    
		    textField:'orgName',
		    onChange:function(newValue, oldValue){
		   	if(newValue != null && !isNaN(newValue)){
			   
			    	var urlUser="${ctx}/terminalUser/findTerminalUserName?orgId="+newValue;
		   			$userCobobox=$('#terminalUserName').combobox({    
					    url:urlUser,    
					    valueField:'name',    
					    textField:'name',
					    onSelect:function(rec){
							$('#terminalUserPhone').textbox('setValue',rec.phone);
							$('#terminalUserId').val(rec.id);
					    }
					}); 
			    }
			    
			    
		    }
		});

		timeInputBoxBindOpenPanel('castTimeBegin');
		timeInputBoxBindOpenPanel('castTimeEnd');
  		datagrid = $('#datagrid').datagrid({
			url : '${ctx}/terminalInfo/datagrid',
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
					<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
						btnHtml+="<a href='javascript:remove();' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-pencil")>
					if(str=='2'){
						btnHtml+="<a href='javascript:assign("+i+");' plain='true'  iconcls='icon-pencil' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-pencil' style='padding-left: 20px;'>分配</span></span></a>";
						btnHtml+="<a href='javascript:stopUse("+i+");' plain='true'  iconcls='icon-pencil' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-pencil' style='padding-left: 20px;'>停用</span></span></a>";
					}
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-pencil")>
					if(str=='1'){
						btnHtml+="<a href='javascript:assign("+i+");' plain='true'  iconcls='icon-pencil' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-pencil' style='padding-left: 20px;'>分配</span></span></a>";
					}
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-pencil")>
					if(str=='3'){
						btnHtml+="<a href='javascript:assign1("+i+");' plain='true'  iconcls='icon-pencil' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-search' style='padding-left: 20px;'>使用详情</span></span></a>";
						btnHtml+="<a href='javascript:comeBack("+i+");' plain='true'  iconcls='icon-pencil' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-pencil' style='padding-left: 20px;'>归还</span></span></a>";
					}
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-pencil")>
					if(str=='4'){
						btnHtml+="<a href='javascript:assign1("+i+");' plain='true'  iconcls='icon-pencil' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-pencil' style='padding-left: 20px;'>未归还</span></span></a>";
					}
					</#if>
					return btnHtml;
				}
			} ] ]
		});
		//修改和增加
		var urls='';
		terminalInfoDialog = $('#terminalInfoDialog').show().dialog({
			modal : true,
			title : '终端使用者信息',
			width:400,
			height:300,
			buttons : [ {
				text : '确定',
				style:'text-align:center',
				handler : function() {
					if(terminalInfoForm.form('validate')){
					if(addOrUpdate=='update'){
							urls="${ctx}/terminalInfo/updateTerminalInfo";
						}
						if(addOrUpdate=='add'){
							urls="${ctx}/terminalInfo/addTerminalInfo";
						}
					$.ajax({
						url : urls,
						data : terminalInfoForm.serialize(),
						cache : false,
						success : function(data) {
							var msg = "操作失败";
							if(data.success){
								msg = "操作成功";
								terminalInfoForm.form('clear');
								$('#terminalInfoDialog').dialog('close');
								
								clearAllSel();
								datagrid.datagrid('reload');
							}
							$.messager.alert("提示",msg);
						}
					
					});
					}
				}			
			} ]
			
		}).dialog('close');  
	//分配
		assignDialog = $('#assignDialog').show().dialog({
			modal : true,
			title : '终端分配',
			width:600,
			height:300,
			buttons : [ {
				text : '确  定',
				style:'text-align:center',
				handler : function() {
					if(assignForm.form('validate')){
					$.ajax({
						url : "${ctx}/terminalUseInfo/assignTerminalUseInfo",
						data : assignForm.serialize(),
						cache : false,
						success : function(data) {
							var msg = "操作失败";
							if(data.success){
								msg = "操作成功";
								assignForm.form('clear');
								$('#assignDialog').dialog('close');
								
								clearAllSel();
								datagrid.datagrid('reload');
							}
							$.messager.alert("提示",msg);
						}
					});
					}
				}			
			} ]
			
		}).dialog('close'); 
		//其他操作  
		otherDialog = $('#otherDialog').show().dialog({
			modal : true,
			title : '终端使用详情',
			width:600,
			height:400,
			buttons : [ {
				text : '关闭',
				style:'text-align:center',
				handler : function() {
					$('#otherDialog').dialog('close');
				}
			} ],
			onClose:function(){
					otherForm.form('clear');
			}
			
		}).dialog('close');   
		
 	
		 $("#code").blur(function(){ 
		 	var code=$('#code').val();
               $.ajax({
               url:'${ctx}/terminalInfo/validateCode',
               data: "code="+code,
               cache : false,
               success : function(data) {
          			if(data!=null){
          				var msg="该名已被占用";
          				if(data.success==true){
          				$.messager.alert("提示",msg);
          				$("#model").textbox({disabled:true});
          			}
                   	}	
                   }
                 });
             
           });
           

  	});
  	 //查询
	function searchFun() {
		datagrid.datagrid('load', {
			code : $('#toolbar input[name=code]').val(),
			model : $('#toolbar input[name=model]').val(),
			terminalState : $('#toolbar input[name=terminalState]').val(),
			"castTimeBegin" :$("#castTimeBegin").datetimebox("getValue"),
			"castTimeEnd" :$("#castTimeEnd").datetimebox("getValue"),
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
	 /*清除所有选择项*/
    function clearAllSel(){
    	$("#datagrid").datagrid('clearSelections');
    }
	//增加
	function append(){
	
		terminalInfoDialog.dialog('open');
		terminalInfoForm.form('clear');
		addOrUpdate='add';
	}
	//修改
	function edit(index){
		addOrUpdate='update';
		$("#ts").combobox({ disabled:true });
		var row = datagrid.datagrid("getRows")[index];
		terminalInfoForm.form('load', {
			id : row.id,
			code : row.code,
			model:row.model,
			castTime1:row.castTime,
			terminalState:row.terminalState
		});
		if(row.org){
				organTree.combobox('setValue',row.org.id);
			}
		terminalInfoDialog.dialog('open');
	}
	//删除选项
	function remove(){
		var rows=datagrid.datagrid('getSelections');
		var ids = [];
		if(rows.length>0){
			$.messager.confirm('请确认','您要删除当前所选项？',function(isdel){
				if(isdel){
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${ctx}/terminalInfo/deleteById',
						data : {
							ids: ids
						},
						cache : false,
						success:function(data){
							var msg = "操作失败";
						if(data.success){
							msg = "操作成功";
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('load');
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
	//时间
	function timeInputBoxBindOpenPanel(id){
		var timeInput=$("#"+id).parent().children()[1];
		timeInput=$(timeInput).children()[0];
		$(timeInput).click(function(){
			$("#"+id).datetimebox("showPanel");
		});
	}
	//归还
	function comeBack(index){
		if(datagrid.datagrid("getRows")!=null){
		var row = datagrid.datagrid("getRows")[index];
			if(row!=null){
				$.ajax({
						url : '${ctx}/terminalUseInfo/comeBackTerminalById',
						data : {
							trminalId: row.id
						},
						cache : false,
						success:function(data){
							var msg = "操作失败";
						if(data.success){
							msg = "操作成功";
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('load');
						}
						$.messager.alert("提示",msg);
						}
					});
			}
		}
		
	}
	
	//停用
	function stopUse(index){
		if(datagrid.datagrid("getRows")!=null){
		var row = datagrid.datagrid("getRows")[index];
			if(row!=null){
				$.ajax({
						url : '${ctx}/terminalUseInfo/stopUseTerminalById',
						data : {
							trminalId: row.id
						},
						cache : false,
						success:function(data){
							var msg = "操作失败";
						if(data.success){
							msg = "操作成功";
							datagrid.datagrid('unselectAll');
							datagrid.datagrid('load');
						}
						$.messager.alert("提示",msg);
						}
					});
			}
		}
		
	}
	
	//分配
	function assign(index){
		assignDialog.dialog('open');
		var row = datagrid.datagrid("getRows")[index];
		assignForm.form('load', {
			terminalId : row.id,
			terminalCode : row.code
			
		});
		if(row.org){
				organTree.combobox('setValue',row.org.id);
		}
	}
	//其他操作
	function assign1(index){
		otherDialog.dialog('open');
		var row = datagrid.datagrid("getRows")[index];
		$.ajax({
					url : '${ctx}/terminalUseInfo/getTerminalUseInfo',
					data : {
						id: row.id
					},
					cache : false,
					success:function(data){
					if(data!=null){
						var terminalUseInfo=data.obj;
						otherForm.form('load', {
							terminalId : terminalUseInfo.terminalId,
							terminalCode : terminalUseInfo.terminalCode,
							terminalState: terminalUseInfo.terminalState,
							terminalUserId:terminalUseInfo.terminalUserId,
							terminalUserName:terminalUseInfo.terminalUserName,
							terminalUserPhone:terminalUseInfo.terminalUserPhone,
							terminalUserPhoneC:terminalUseInfo.terminalUserPhoneC,
							useTime1:terminalUseInfo.useTime,
							backTime1:terminalUseInfo.backTime,
							useAreaName:terminalUseInfo.useAreaName,
							useAdress:terminalUseInfo.useAdress,
							orgId:terminalUseInfo.org.id,
							terminalUserPhonec:terminalUseInfo.terminalUserPhonec,
							point1Longitude:terminalUseInfo.point1Longitude,
							point1Latitude:terminalUseInfo.point1Latitude,
							point2Longitude:terminalUseInfo.point2Longitude,
							point2Latitude:terminalUseInfo.point2Latitude,
							point3Longitude:terminalUseInfo.point3Longitude,
							point3Latitude:terminalUseInfo.point3Latitude,
							point4Longitude:terminalUseInfo.point4Longitude,
							point4Latitude:terminalUseInfo.point4Latitude,
							remarks:terminalUseInfo.remarks
							
						});
					}else{
						$.messager.alert("还未分配任务，请先分配任务")
					}
				}
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
							投用时间：
							<input name="castTimeBegin" id="castTimeBegin"   class="easyui-datetimebox" style="width: 175px; height:26px;" onfocus="foxDateTimeBox()" />
							<span class="span_line">一</span>
							<input name="castTimeEnd" id="castTimeEnd"  editable="false" class="easyui-datetimebox" style="width:175px; height:26px;" onfocus="foxDateTimeBox()" />
						</td>
						
						<td>
							运营商：
							<input id="orgIdForSearch" name="orgIdForSearch" class="easyui-combobox" style="width:175px;height:26px;"   
        					data-options="valueField:'id',textField:'orgName',url:'${ctx}/terminalInfo/findOrgByCurrentUser'"/>
						</td>
					</tr>
					<tr>
					<td>
							终端编码：
							<input name="code" class="easyui-textbox" style="height:26px;"/>
							终端型号：
							<input name="model" class="easyui-textbox" style="height:26px;"/>
						</td>
						<td>
							终端状态：
							<select name="terminalState" id="terminalState" class="easyui-combobox" style="height:26px;width:175px;">
								<option value=null select="selected"></option>
								<option value="1">停用</option>
								<option value="2">未分配</option>
								<option value="3">已分配</option>
								<option value="4">未归还</option>
							</select>
						</td>
						<td>
							<#if GeneralMethod.checkButton(requestURI,"icon-search")>
								<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
							</#if>
								<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
							<#if GeneralMethod.checkButton(requestURI,"icon-add")>
								<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();"  href="javascript:void(0);">增 加</a> 
							</#if>
							<#if GeneralMethod.checkButton(requestURI,"icon-remove")><a class="easyui-linkbutton" iconCls="icon-remove" onclick="remove();"  href="javascript:void(0);">批量删除</a></#if>
						</td>
					</tr>
				</table>
		</div>
		<table id="datagrid"></table>
	</div>
		<div id="terminalInfoDialog" style="display: none;">
		<form id="terminalInfoForm" method="post">
			<input type="hidden" name="id" id="id"/>
			<table class="basic basic_dialog">
				<tr>
					<th width="30%">终端编码:</th>
					<td><input name="code" id="code" class="easyui-validatebox " style="width:160px; height:20px;"  required="true" /></td>
				</tr>
				<tr>
					<th width="85">终端型号:</th>
					<td><input name="model" id="model" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" /></td>
				</tr>
				<tr>
					<th width="85">投用时间:</th>
					<td><input name="castTime1" id="castTime1"  editable="false" class="easyui-datetimebox" style="width:175px;height:26px;" onfocus="foxDateTimeBox()" />  </td>
				</tr>
				<tr>
					<th >终端所属机构：</th> 
					<td>
						
						
					<input id="orgId" name="orgId" style="width:170px; height:26px;" required="true"/>  
					
					</td>
				</tr>
				<tr >	
					<th>终端状态：</th>
					<td>
							<select name="terminalState" id="ts" class="easyui-combobox">
								<option value="1">停用</option>
								<option value="2">未分配</option>
								<option value="3">已分配</option>
								<option value="4">未归还</option>
							</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!--分配弹窗-->
	<div id="assignDialog" style="display: none;overflow: hidden;">
		<form id="assignForm" method="post">
			
			<table class="basic basic_dialog">
				<tr>
					<!--终端ID隐藏-->
					<input type="hidden" name="terminalId" id="terminalId"  />
					<input type="hidden" name="terminalState" id="terminalState" value="3" />
					<th width="80">终端编号:</th>
					<td><input name="terminalCode" id="terminalCode" class="easyui-textbox validatebox " style="width:160px; height:26px;"  readonly="true" /></td>
					<th width="80">终端卡号:</th>
					<td><input name="terminalUserPhoneC" id="terminalUserPhoneC" class="easyui-textbox validatebox " style="width:160px;height:26px;" required="true" /></td>
				</tr>
				<tr>
					
					<th width="80">使用详细地址:</th>
					<td><input name="useAdress" id="useAdress" class="easyui-textbox validatebox " style="width:160px; height:26px;"   /></td>
					<th width="80">预计归还时间:</th>
					<td><input name="backTime1" id="backTime1" editable="false" class="easyui-datetimebox" style="width:160px;height:26px;"  required="true" /></td>
				</tr>

				<tr>

					<th >终端所属机构:</th> 
					<td><select id="orgId" name="orgId" style="width:160px; height:26px;" required="true" readonly="true"></select></td>
					<th width="80">领用人:</th>
					<td>
					<input name="terminalUserId" id="terminalUserId"   style="width:170px; height:26px;display:none;"  required="true" />
					<input name="terminalUserName" id="terminalUserName" class="easyui-textbox validatebox " style="width:160px; height:26px;"  required="true" />
					</td>
				</tr>
				<tr>
					<th width="80">联系电话:</th>
					<td><input name="terminalUserPhone" id="terminalUserPhone" class="easyui-textbox validatebox " style="width:160px; height:26px;"  readonly="true" /></td>
					<th></th>
					<td></td>
				</tr>

				<tr>
					<th width="80">备注:</th>
					<td colspan="3">
						<input id="remarks" name="remarks" class="easyui-textbox" data-options="multiline:true" style=" width:475px;height:60px"></input>
					</td>
				</tr>
			<!--	
				<tr>
					<th width="80">定点1经度:</th>
					<td><input name="point1Longitude" id="point1Longitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" readonly="true" /></td>
					<th width="80">定点1纬度:</th>
					<td><input name="point1Latitude" id="point1Latitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true"  readonly="true" /></td>
					<td colspan="2" rowspan="4" style="vertical-align: top;">
						<a class="easyui-linkbutton"  onclick="openMap();"  href="javascript:void(0);">采集经纬</a> 
					</td>
				</tr>
				<tr>
					<th width="80">定点2经度:</th>
					<td><input name="point2Longitude" id="point2Longitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true"  readonly="true" /></td>
					<th width="80">定点2纬度:</th>
					<td><input name="point2Latitude" id="point2Latitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true"  readonly="true" /></td>
				</tr>
				<tr>
					<th width="80">定点3经度:</th>
					<td><input name="point3Longitude" id="point3Longitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true"  readonly="true" /></td>
					<th width="80">定点3纬度:</th>
					<td><input name="point3Latitude" id="point3Latitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true"  readonly="true" /></td>
				</tr>
				<tr>
					<th width="80">定点4经度:</th>
					<td><input name="point4Longitude" id="point4Longitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true"  readonly="true" /></td>
					<th width="80">定点4纬度:</th>
					<td><input name="point4Latitude" id="point4Latitude" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true"  readonly="true" /></td>
				</tr>
			-->	
			</table>
		</form>
	</div>
	<!--使用详情查看-->
	<div id="otherDialog" style="display: none;overflow: hidden;">
		<form id="otherForm" method="post">
			<input type="hidden" name="id" id="id"/>
			<table class="basic basic_dialog">
				<tr>
					<th width="80">终端ID:</th>
					<td><input name="terminalId" id="terminalId" editable="false" class="easyui-textbox validatebox " style="width:160px; height:26px;"   /></td>
					<th width="80">终端编号:</th>
					<td><input name="terminalCode" id="terminalCode" editable="false" class="easyui-textbox validatebox " style="width:160px; height:26px;"  /></td>
					
				</tr>
				<tr>
					<th width="80">领用人:</th>
					<td>
					<input name="terminalUserId" id="terminalUserId"  editable="false"  style="width:160px; height:26px;display:none;" />
					<input name="terminalUserName" id="terminalUserName" editable="false" class="easyui-textbox validatebox " style="width:160px; height:26px;"  />
					</td>
					<th width="80">联系电话:</th>
					<td><input name="terminalUserPhone" id="terminalUserPhone"  editable="false" class="easyui-textbox validatebox " style="width:160px; height:26px;" /></td>

				</tr>
				    <th width="80">终端卡号:</th>
					<td><input name="terminalUserPhoneC" id="terminalUserPhoneC" editable="false" class="easyui-textbox validatebox "  style="width:160px;height:26px;"  /></td>
					<th width="80">使用地编号:</th>
					<td><input name="UseAreaCode" id="UseAreaCode"  editable="false" class="easyui-textbox validatebox "  style="width:160px;height:26px;" /></td>
				</tr>
				<tr>
					<th width="80">使用区域:</th>
					<td>
					<input name="useAreaName" id="useAreaName" editable="false" class="easyui-textbox validatebox " style="width:160px; height:26px;"  />
				<!--	
					<input name="point1Longitude" id="point1Longitude"  style="width:175px; height:26px;display:none;"  />
                    <input name="point1Latitude" id="point1Latitude"  style="width:175px; height:26px; display:none;"   />
					<input name="point2Longitude" id="point2Longitude" style="width:175px; height:26px; display:none;"   />
					<input name="point2Latitude" id="point2Latitude"  style="width:175px; height:26px; display:none;" />
					<input name="point3Longitude" id="point3Longitude"  style="width:175px; height:26px; display:none;"   />
					<input name="point3Latitude" id="point3Latitude"  style="width:175px; height:26px; display:none;"  />
					<input name="point4Longitude" id="point4Longitude"  style="width:175px; height:26px; display:none;"  />
					<input name="point4Latitude" id="point4Latitude"  style="width:175px; height:26px; display:none;" />
				-->
					<th width="80">使用地点:</th>
					<td><input name="useAdress" id="useAdress" editable="false" class="easyui-textbox validatebox " style="width:160px; height:26px;"  /></td>
				
					</td>
				</tr>
				<tr>
					<th width="80">预计归还时间:</th>
					<td><input name="useTime1" id="useTime" editable="false" editable="false" class="easyui-datetimebox" style="width:160px;height:26px;" readonly="true"  /></td>
					<th width="80">归还时间:</th>
					<td><input name="backTime1" id="backTime"e ditable="false"  editable="false" class="easyui-datetimebox" style="width:160px;height:26px;" readonly="true"  /></td>
				</tr>
				<tr>
					<th width="80">终端状态:</th>
					<td>
						<select id="terminalState" class="easyui-combobox" name="terminalState" style="width:160px; height:26px;"  readonly="true" >   
						    <option value="1">停用</option>   
						    <option value="2">未分配</option>   
						    <option value="3">已分配</option>   
						    <option value="4">未归还</option>   
						</select>
					</td>
					<th >终端所属机构：</th> 
					<td><select id="orgId" name="orgId" editable="false" style="width:160px; height:26px;" readonly="true"></select></td>
				</tr>
				<tr>
					<th width="80">备注:</th>
					<td colspan="3">
						<input id="remarks" name="remarks" class="easyui-textbox" data-options="multiline:true" style=" width:475px;height:60px" readonly="true"></input>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>