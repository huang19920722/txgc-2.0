<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var userDialog;
	var userForm;
	var organTree;
	var roleTree;
	var roleIds=[];
	$(function(){
		timeInputBoxBindOpenPanel("birthday");
		userForm = $('#userForm').form();
		organTree=$('[name=orgId]').combotree({
			url : '${ctx}/user/findOrgTreeByLoginUser',
			animate : false,
			lines : !sy.isLessThanIe8(),
			width:170,
			panelWidth:200,
			onLoadSuccess : function(node, data) {
				var t = $(this);
				if (data) {
					$(data).each(function(index, d) {
						if (this.state == 'closed') {
							t.tree('expandAll');
						}
					});
				}
			},
			onChange:function(newValue, oldValue){
				var url=$('#roleId').combotree("options").url;
				if(newValue){
					if(url.indexOf("?") != -1){
						url=url.split("?")[0];
						url=url+"?orgId="+newValue;
					}else{
						url=url+"?orgId="+newValue;
					}
					//$('#roleId').combotree("reload",url);
				}
			}
		});
		
		userDialog = $('#userDialog').show().dialog({
			modal : true,
			title : '用户信息',
			width:360,
			height:440,
			buttons : [ {
				text : '确定',
				style:'text-align:center',
				handler : function() {
					if(userForm.form('validate')){
						//alert($("#userId").val());
						var formData={
								"id":$("#userId").val(),
								"loginAcct":$("#loginAcct").val(),
								"sex":$("[name='sex']:checked").val(),
								"birthday":$("#birthday").datetimebox("getValue"),
								"officePhone":$("#officePhone").val(),
								"mobilePhone":$("#mobilePhone").val(),
								"fax":$("#fax").val(),
								"email":$("#email").val(),
								"qq":$("#qq").val(),
								"nickName":$("#nickName").val()
							};
						var organization={"id":organTree.combotree("getValue")}
						var roles=[];
						var ids=roleTree.combotree("getValues");
						for(var i=0;i<ids.length;i++){
							roles.push({"id":ids[i]});
						}
						formData.organization=organization;
						formData.roles=roles;
						var url="${ctx}/user/add";
						var msg="新增";
						//alert($("#userId").val());
						if ($("#userId").val()) {
							url='${ctx}/user/edit';
							//formData.id=$('[name=id]').val();
							msg="修改";
						}else{
							if($("#password").val()){
								formData.password=$("#password").val();
							}
						}
						$.ajax({
							url:url, 
							data:JSON.stringify(formData),
							contentType:"application/json",
				 			success:function(result){
								if (result.success) {
									userDialog.dialog('close');
									$.messager.alert('用户'+msg,"保存成功");
									datagrid.datagrid('reload');
								}else{
									if(result.msg){
										$.messager.alert('用户'+msg,result.msg);
									}else{
										$.messager.alert('用户'+msg,"保存失败");
									}
								}
							}
						});
					}
				}
			} ]
		}).dialog('close');
		
		datagrid = $('#datagrid').datagrid({
			url : '${ctx}/user/datagrid',
			rownumbers:true,
			toolbar : '#toolbar',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			border:1,
			pageList : [5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'id',
			frozenColumns : [ [ {
				title : 'id',
				field : 'id',
				width : 50,
				checkbox : true
			}, {
				field : 'loginAcct',
				title : '登录帐号',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ 
				 {
					field : 'nickName',
					title : '昵称',
					width : 80,
					sortable : true
				},
				 {
					field : 'officePhone',
					title : '固定电话',
					width : 80,
					sortable : true
				},
				 {
					field : 'mobilePhone',
					title : '移动电话',
					width : 80,
					sortable : true
				},
				 {
					field : 'fax',
					title : '传真',
					width : 80,
					sortable : true
				},
				 {
					field : 'email',
					title : '电子邮箱',
					width : 100,
					sortable : true
				},
				{
				field : 'roles',
				title : '所属角色',
				width : 200,
				formatter:function(value,row){
					var result="";
					if(value){
						for(var i=0;i<value.length;i++){
							result+=value[i].text+",";
						}
						result=result.substring(0,result.length-1)
					}
					return result;
				}
			}, {
				field : 'organization',
				title : '所属机构',
				width : 100,
				formatter:function(value,row){
					if(value){
						return value.orgName;
					}
					return "";
				}
			}, {
				field : 'roleId',
				title : '所属角色',
				width : 200,
				hidden : true
			},{
				title : '操作',
				field : '1',
				width : 100,
				formatter:function(value,rec,i){
					var btnHtml="";
					<#if GeneralMethod.checkButton(requestURI,"icon-edit")>
						btnHtml="<a href='javascript:edit("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
						btnHtml+="<a href='javascript:removeUser();' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
					</#if>
					return btnHtml;
				}
			} ] ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				$(this).datagrid('selectRow', rowIndex);
			}
		});
	});
	function timeInputBoxBindOpenPanel(id){
		var timeInput=$("#"+id).parent().children()[1];
		timeInput=$(timeInput).children()[0];
		$(timeInput).click(function(){
			$("#"+id).datetimebox("showPanel");
		});
	}
	function initRoleTree(){
		roleTree=$('#roleId').combotree({
			url : '${ctx}/user/tree',
			animate : false,
			lines : true,
			checkbox : true,
			panelHeight:110,
			multiple : true,
			onLoadSuccess : function(node, data) {
				$("#roleId").combotree("clear");
				//设置角色选中
				if(roleIds.length>0){
					for(var i=0;i<roleIds.length;i++){
						for(var j=0;j<data.length;j++){
							if(data[j].id==roleIds[i]){
								$('#roleId').combotree("setValue",roleIds[i]);
							}else{
								
							}
						}
					}
				}
			}
		});
	}
	function append() {
		userDialog.dialog('open');
		userForm.form('clear');
		initRoleTree();
		$("#sex003").attr("checked",true);
		$("[name=loginAcct]").removeAttr("readonly");
		$("[name=password]").removeAttr("disabled");
		$("[name=password1]").removeAttr("disabled");
		
	}

	function edit(index) {
	        var rows = $("#datagrid").datagrid("getRows")[index];
			userForm.find('[name=loginAcct]').attr('readonly', 'true');
			userDialog.dialog('open');
			userForm.form('clear');
			userForm.form('load', {
				id : rows.id,
				loginAcct : rows.loginAcct,
				sex:rows.sex,
				fax:rows.fax,
				officePhone:rows.officePhone,
				mobilePhone:rows.mobilePhone,
				email:rows.email,
				birthday:rows.birthday,
				nickName:rows.nickName,
				qq:rows.qq
			});
			$("#password").attr("disabled","true");
			$("#password1").attr("disabled","true");
			initRoleTree();
			var n =organTree.combotree("tree").tree("getNode",rows.id);
			if(rows.organization){
				organTree.combotree('setValue',rows.organization.id);
			}
			roleIds=[];
			if(rows.roles){
				for(var i=0;i<rows.roles.length;i++){
					roleIds.push(rows.roles[i].id);
				}
			}
			if(roleIds){
				$('#roleId').combotree("setValues",roleIds);
			}
	}
	function removeUser() {
		var ids = [];
		var rows = datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${ctx}/user/del',
						data :JSON.stringify(ids),
						cache : false,
						dataType : "json",
						contentType:"application/json",
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
			loginAcct : $('#toolbar input[name=loginAcct]').val()
		});
	}
	function clearFun() {
		$('#toolbar input').val('');
		datagrid.datagrid('load', {});
	}
	/*提示消息弹出框函数*/
	function myMessage(mg)
	{
	   $.messager.alert('提示',mg);
	}
	
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="search_table">
				<table>
					<tr>
						<td>
							用户名称：
							<input name="loginAcct" class="easyui-textbox" style="height:26px;"/>
						</td>
						<td>
							<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
							<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
							<#if GeneralMethod.checkButton(requestURI,"icon-add")>
								<a class="easyui-linkbutton" iconCls="icon-add" onclick="append();"  href="javascript:void(0);">增 加</a> 
							</#if>
							<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
								<a class="easyui-linkbutton" iconCls="icon-remove" onclick="removeUser();"   href="javascript:void(0);">删 除</a>
							</#if>
							<a class="easyui-linkbutton" iconCls="icon-add"   href="javascript:addChart();">图例</a>
						</td>
					</tr>
				</table>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="userDialog" style="display: none;overflow-x:hidden;">
		<form id="userForm" method="post">
			<table class="basic basic_dialog">
				<tr style="display:none;">
					<th width="80">用户ID</th>
					<td><input name="id" id="userId" type="hidden" /></td>
				</tr>
				<tr>
					<th width="85">登录名称：</th>
					<td><input id="loginAcct" name="loginAcct"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" required="true" validType="length[3,32]" /></td>
				</tr>
				<tr>
					<th>登录密码：</th>
					<td><input id="password" type="password" name="password"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" validType="length[6,32]" /></td>
				</tr>
				<tr>
					<th>确认密码：</th>
					<td><input id="password1" type="password" name="password1"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" validType="eqPassword[password]"/></td>
				</tr>
				<tr>
					<th>姓&#12288;&#12288;名：</th>
					<td><input id="nickName" name="nickName"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" required="true" validType="length[1,20]"/></td>
				</tr>
				<tr>
					<th>性&#12288;&#12288;别：</th>
					<td>
						<input name="sex" id="sex001"  type="radio" value="male" style="width:15px;"/>男
						<input name="sex" id="sex002" type="radio" value="female" style="width:15px;"/>女
						<input name="sex" id="sex003" type="radio" value="private" style="width:15px;" />保密
					</td>
				</tr>
				<tr>
					<th>生&#12288;&#12288;日：</th>
					<td><input id="birthday" name="birthday"  style="width:170px; height:26px;" class="easyui-datetimebox" /></td>
				</tr>
				<tr>
					<th>固定电话：</th>
					<td><input id="officePhone" name="officePhone"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" validType="phone"/></td>
				</tr>
				<tr>
					<th>移动电话：</th>
					<td><input id="mobilePhone" name="mobilePhone"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" validType="mobile"/></td>
				</tr>
				<tr>
					<th>传&#12288;&#12288;真：</th>
					<td><input id="fax" name="fax"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" validType="phone" /></td>
				</tr>
				<tr>
					<th>电子邮箱：</th>
					<td><input id="email" name="email"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" validType="email" /></td>
				</tr>
				<tr>
					<th>q&#12288;&#12288;q：</th>
					<td><input id="qq" name="qq"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" validType="qq"/></td>
				</tr>
				<tr>
					<th >所属机构：</th>
					<td><select id="orgId" name="orgId" style="width:170px; height:26px;" required="true"></select></td>
				</tr>
				<tr>
					<th >所属角色：</th>
					<td><select id="roleId" name="roles.id" style="width:170px; height:26px;" required="true"></select></td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>