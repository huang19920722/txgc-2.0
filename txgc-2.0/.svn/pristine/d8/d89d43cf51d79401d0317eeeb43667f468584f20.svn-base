<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var terminalUserDialog;
  	var terminalUserForm;
  	var addOrUpdate;
  	var organTree;
	$(function() {
		terminalUserForm = $('#terminalUserForm').form();
		organTree=$("[name=orgId]").combobox({    
		    url:'${ctx}/terminalInfo/findOrgByCurrentUser',    
		    valueField:'id',
		    textField:'orgName'   
		});
		datagrid = $('#datagrid').datagrid({
			url : '${ctx}/terminalUser/datagrid',
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
				title : '终端使用者ID',
				width : 150,
				checkbox : true
			}] ],
			columns : [ [ {
				field : 'name',
				title : '使用者',
				width :80
			}, {
				field : 'idcard',
				title : '身份证号',
				width : 250
			}, {
				field : 'phone',
				title : '联系电话',
				width : 150
			}, {
				field : 'org',
				title : '运营商',
				width : 100,
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
				width : 150,
				formatter:function(value,rec,i){
					var btnHtml="";
					<#if GeneralMethod.checkButton(requestURI,"icon-edit")>
						btnHtml="<a href='javascript:edit("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
						btnHtml+="<a href='javascript:remove("+i+");' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
					</#if>
					return btnHtml;
				}
			} ] ]
		});
		
	var urls='';
		terminalUserDialog = $('#terminalUserDialog').show().dialog({
			modal : true,
			title : '终端使用者信息',
			width:310,
			height:300,
			buttons : [ {
				text : '确定',
				style:'text-align:center',
				handler : function() {
					if(terminalUserForm.form('validate')){
					if(addOrUpdate=='update'){
							urls="${ctx}/terminalUser/updateTerminalUser";
						}
						if(addOrUpdate=='add'){
							urls="${ctx}/terminalUser/addTerminalUser";
						}
					$.ajax({
						url : urls,
						data : terminalUserForm.serialize(),
						cache : false,
						success : function(data) {
							var msg = "操作失败";
							if(data.success){
								msg = "操作成功";
								terminalUserForm.form('clear');
								$('#terminalUserDialog').dialog('close');
								
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
$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次输入的密码不同，请确认后再输入！'   
    },
    Mobile: {
        validator: function (value) {  
            var reg = /^1[3|4|5|8|9]\d{9}$/;  
            return reg.test(value);  
        },  
        message: '请输入正确的手机号码！'  
    },
     idcard : {// 验证身份证 
        validator : function(value) { 
            return /^\d{18}(\d{2}[A-Za-z0-9])?$/i.test(value); 
        }, 
        message : '身份证号码格式不正确，请重新输入！' 
    },
     name: {
               validator: function (value) {  
                   return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);  
               },  
               message: '含有非法字符，请输入英文或中文名'  
           }
}); 
	});
	 /*清除所有选择项*/
    function clearAllSel(){
    	$("#datagrid").datagrid('clearSelections');
    }
    //查询
	function searchFun() {
		datagrid.datagrid('load', {
			name : $('#toolbar input[name=name]').val(),
			IDCard : $('#toolbar input[name=IDCard]').val(),
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
	
//增加
	function append(){
		terminalUserDialog.dialog('open');
		terminalUserForm.form('clear');
		addOrUpdate='add';
	}
	//修改
	function edit(index){
		addOrUpdate='update';
		var row = datagrid.datagrid("getRows")[index];
		terminalUserForm.form('load', {
			id : row.id,
			name : row.name,
			IDCard:row.idcard,
			phone:row.phone,
			password:row.password,
			password1:row.password
		});
		if(row.org){
				organTree.combobox('setValue',row.org.id);
			}
		terminalUserDialog.dialog('open');
	}
	
	function remove(index){
		var row=datagrid.datagrid('getSelections')[index];
		if(rows.id!=null || rows.id != undefined){
			$.ajax({
						url : '${ctx}/terminalUser/deleteById',
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
		}else{
			$.messager.alert("提示","选择你要删除的行");
		}
	}
	
	//删除选项
	function removeAll(){
		var rows=datagrid.datagrid('getSelections');
		var ids = [];
		if(rows.length>0){
			$.messager.confirm('请确认','您要删除当前所选项？',function(isdel){
				if(isdel){
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${ctx}/terminalUser/deleteById',
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
	//查看密码
	function showPassword(){
	
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
							<input name="name" class="easyui-textbox" style="height:26px;"/>
						</td>
						<td>
							身份证号：
							<input name="IDCard" class="easyui-textbox" style="height:26px;"/>
						</td>
						<td>
							运营商:
							<input id="orgIdForSearch" name="orgIdForSearch" class="easyui-combobox" style="width:175px;height:26px"   
        					data-options="valueField:'id',textField:'orgName',url:'${ctx}/terminalInfo/findOrgByCurrentUser'"/>
						</td>
						<td>
							<#if GeneralMethod.checkButton(requestURI,"icon-search")>
								<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
							</#if>
								<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
							<#if GeneralMethod.checkButton(requestURI,"icon-add")>
								<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();"  href="javascript:void(0);">增 加</a> 
							</#if>
							<#if GeneralMethod.checkButton(requestURI,"icon-remove")><a class="easyui-linkbutton" iconCls="icon-remove" onclick="removeAll();"  href="javascript:void(0);">批量删除</a></#if>
						</td>
					</tr>
				</table>
		</div>
		<table id="datagrid"></table>
	</div>
	<div id="terminalUserDialog" style="display: none;overflow: hidden;">
		<form id="terminalUserForm" method="post">
			<input type="hidden" name="id" id="id"/>
			<table class="basic basic_dialog">
				<tr>
					<th width="85">姓名:</th>
					<td><input name="name" id="name" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" validType="name"/></td>
				</tr>
				<tr>
					<th width="85">身份证号:</th>
					<td><input name="IDCard" id="IDCard" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" validType="idcard"/></td>
				</tr>
				<tr>
					<th width="85">联系电话:</th>
					<td><input name="phone" id="phone" class="easyui-textbox validatebox " style="width:175px; height:26px;"  required="true" validType="Mobile"/></td>
				</tr>
				<tr>
					<th width="85">密码:</th>
					<td>
						<input id="password" name="password" type="text" class="easyui-validatebox" required="true" validType="length[6,15]" /> 
					</td>
				</tr>
				<tr id="tp">
					<th>确认密码：</th>
					<td><input id="password1" name="password1" type="text" class="easyui-validatebox" required="true"  validType="equals['#password']" /> </td>
				</tr>
				<tr>
					<th >运营商：</th> 
					<td><select id="orgId" name="orgId" style="width:170px; height:26px;" required="true"></select></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>