<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<link rel="stylesheet" href="${static}/js/zTree/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
	.rightContent{
		height:40px;
		width:100%;
		float:left;
	}
	.rightContent1{
		height:40px;
		width:100%;
		float:left;
		border-bottom:1px dashed #dbdbdb;
	}
	.rightDiv1{
		float:left;
		width:20%;
		height:40px;
		background-color:#ECF2FA;
		text-align:center;
		vertical-align:middle;
	}
	.rightSpan{
		line-height:40px;
		height:40px;
		vertical-align:middle;
	}
	.rightDiv2{
		float:left;
		width:79%;
		height:40px;
		line-height:40px;
		display:inline;
	}
	.rightInput{
		width:200px;
		line-height:30px;
		margin-left:10px;
		margin-top:2px;
		margin-bottom:2px;
		border: 1px solid #AED0EA;
		height:30px;
		border-radius:5px 5px 5px 5px;
	}
	.slaveTd{
		border:1px solid #D0E3F2;
	}
	.dialog-button {
		padding: 5px;
		text-align: center;
	}
	.numberbox{
		margin-left:10px;
	}
</style>
<script type="text/javascript" src="${static}/js/zTree/jquery.ztree.all-3.5.min.js"></script>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var $rtuInfoDatagrid;//基础信息
	var $configDialog;//配置信息弹窗
	var $rtuConfigDatagrid;//配置信息datagrid
	var $addConfigDialog;//新增配置信息的弹窗
	var $lookConfigDialog;//查看配置信息的弹窗
	var $addSlaveConfigDialog;//新增采集器配置弹窗
	var $editSlaveConfigDialog;//编辑采集器配置弹窗
	var $lookSlaveConfigDialog;//查看采集器配置弹窗
	var $addTranslateConfigDialog;//新增传输配置弹窗
	var RTUForm;
	var RTUDialog;//新增RTU信息的弹窗
	var $addRtuConfigDialog;//新增RTU配置弹窗
	var $slaveInfoDatagrid;//采集器Datagrid
	var $refreshDialog;//消息弹窗
	var $slaveDialog;//slave基础信息的增加或修改弹窗
	
	$(function(){
		$rtuInfoDatagrid=$("#rtuInfoDatagrid").datagrid({
			url:'${ctx}/rtuController/rtuBaseDataGrid',
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
					field : 'rtuid',
					title : 'RTUID',
					width : 50,
					align:'center',
					formatter:function(value,rec,i){
						return rec.id;
					}
				},{
					field : 'rtuName',
					title : 'RTU名称',
					align:'center',
					width : 100
				},{
					field : 'rtuAdress',
					title : 'RTU地址',
					width : 50,
					align:'center'
				},{
					field : 'cState',
					title : '状态',
					align:'center',
					width : 50,
					formatter:function(value,rec,i){
						if(parseInt(rec.cState) == 0){
							return "待确认";
						}else if(parseInt(rec.cState) == 1){
							return "已确认";
						}else if(parseInt(rec.cState) == 2){
							return "已下发";
						}
					}
				},{
					field : 'nickName',
					title : '创建人',
					align:'center',
					width : 100,
					 formatter: function(value,row,index){
					 	if(row.createUser!=null && row.createUser.nickName != null){
					 		return row.createUser.nickName;
					 	}
					 }
				},{
					field : 'createTime',
					title : '创建时间',
					width : 100,
					align:'center',
					sortable : true
				},{
					title : '操作',
					field : '1',
					width:200,
					align:'center',
					formatter:function(value,rec,i){
						var btnHtml="";
							btnHtml="<a href='javascript:edit("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";
							btnHtml+="<a href='javascript:remove("+rec.id+");' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
							if(parseInt(rec.cState) == 0){
								btnHtml+="<a href='javascript:confirmSend("+rec.id+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>确认下发</span></span></a>";
							}
							btnHtml+="<a href='javascript:queryConfig("+rec.id+");' plain='true'  iconcls='icon-tip' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-tip' style='padding-left: 20px;'>配置信息</span></span></a>";
						return btnHtml;
					}
			} ]]
		});
		
		RTUForm = $("#RTUForm").form();
		RTUDialog = $("#RTUDialog").show().dialog({
			modal : true,
			title : "RTU信息",
			width:360,
			height:200,
			buttons : [{
				text : "关闭",
				style : "text-align : center",
				handler : function() {
					RTUDialog.dialog("close");
				}
			},{
				text : "保存",
				style : "text-align : center",
				handler : function() {
					$.ajax({
						url : "${ctx}/rtuController/addOrUpdateRTUBase",
						data : RTUForm.serialize(),
						cache : false,
						success : function(data) {
							var msg = "操作失败";
							if (data.success) {
								msg = "操作成功";
								RTUForm.form("clear");
								RTUDialog.dialog("close");
								$rtuInfoDatagrid.datagrid("clearSelections");
								$rtuInfoDatagrid.datagrid("reload");
							}
							$.messager.alert("提示",msg);
						}
					});
				}
			}],
			onClose : function() {
				//清除填写的数值
				RTUForm.form("clear");
			}
		}).dialog("close");
	});

	/*提示消息弹出框函数*/
	function myMessage(mg)
	{
	   $.messager.alert('提示',mg);
	}
	function add() {
		
		RTUDialog.dialog('open');
	}
	
	function edit(index) {
		var row = $rtuInfoDatagrid.datagrid("getRows")[index];
		RTUForm.form("load",{
			id : row.id,
			rtuName : row.rtuName,
			rtuAdress : row.rtuAdress
		});
		RTUDialog.dialog("open");
	}
	
	function remove(id) {
		$.messager.confirm('提示','确定删除该条记录？',function(r){   
			if (r){
				$.ajax({
					url : "${ctx}/rtuController/deleteRTUBase/" + id,
					cache : false,
					success : function(data) {
						var msg = "操作失败";
						if (data.success) {
							msg = "操作成功";
							$rtuInfoDatagrid.datagrid("clearSelections");
							$rtuInfoDatagrid.datagrid("reload");
						}
					}
				});
			}
		});
	}

	function searchFun() {
		$("#rtuInfoDatagrid").datagrid('load', {
			rtuName : $('#toolbar input[name=rtuName]').val(),
			rtuAdress : $('#toolbar input[name=rtuAdress]').val()
		});
	}
	
	/*工具栏里面类容清空*/
	function clearFun() {
		$('#toolbar input').val('');
		$("#rtuInfoDatagrid").datagrid('load', {});
		$rtuInfoDatagrid.datagrid('unselectAll');
	
	}
	
	//查询RTU配置信息
	function queryConfig(rtuId){
		//初始化弹窗
		if($configDialog != null){
			$configDialog=null;
		}
			$configDialog=$("#configDialog").show().dialog({
					modal : true,
					title : '配置信息列表',
					width:1000,
					height:450,
					buttons : [ {
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$configDialog.dialog('close');
						}
					}],
					onClose:function(){
						$rtuInfoDatagrid.datagrid('reload');
					}
			}).dialog('close');
		
		
		$rtuConfigDatagrid = null;
		if($rtuConfigDatagrid == null){
			$rtuConfigDatagrid=$("#rtuConfigDatagrid").datagrid({
					url:'${ctx}/rtuController/rtuConfigDataGrid?rtuId='+rtuId,
					fitColumns:true,
					fit:true,
					toolbar:'#configToolbar',
					pagination:true,
					pageSize : 20,
					singleSelect:true, 
					frozenColumns : [[{
						title : '',
						field : 'id',
						width : 50,
						checkbox:true
						}]],
					columns:[[{
					        field:'nickName',
					        title:'创建人',
					        align:'center',
					        width:100,
						  	formatter: function(value,row,index){
						  		if(row.createUser!=null && row.createUser.nickName != null){
							  		return row.createUser.nickName;
							  		}
						  		}
					        },{
						        field:'createTime',
						        title:'创建时间',
						        width:100,
						        align:'center',
						        sortable : true
					        },{
								title : '下发时间',
								field : 'issueTime',
								width : 100,
								align:'center',
								sortable : true
							},{
								title : '操作',
								field : '1',
								align:'center',
								width : 200,
								formatter:function(value,rec,i){
									var btnHtml="";		
										btnHtml+="<a href='javascript:queryCollect("+rec.cid+");' plain='true'  iconcls='icon-tip' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-tip' style='padding-left: 20px;'>采集详情</span></span></a>";
										btnHtml+="<a href='javascript:editConfigFun("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>复制</span></span></a>";
										btnHtml+="<a href='javascript:lookConfigFun("+i+");' plain='true'  iconcls='icon-search' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-search' style='padding-left: 20px;'>配置详情</span></span></a>";
										btnHtml+="<a href='javascript:removeConfigFun("+i+");' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
									return btnHtml;
								}
							}   
					    ]]
					});
		}
		
		$configDialog.dialog('open');
	}
	
	/*配置弹窗的查询按钮事件*/
	function searchConfigFun() {
		$("#rtuConfigDatagrid").datagrid('load', {
			createTimeStart : $('#configToolbar input[name=createTimeStart]').val(),
			createTimeEnd : $('#configToolbar input[name=createTimeEnd]').val()
		});
	}
 /*工具栏里面类容清空*/
	function clearConfigFun() {
		$('#configToolbar input').val('');
		$("#rtuConfigDatagrid").datagrid('load', {});
		$rtuConfigDatagrid.datagrid('unselectAll');
	}
	
	/*通过配置ID查询采集信息*/
	function queryCollect(configId){
		window.parent.addTabFun({
			title : 'RTU采集信息列表',
			src : '${ctx}/rtuController/toRtuCollectInfo?configId='+configId
		})
	}
	/*打开新增配置弹窗*/
	function addConfigFun(){
		var row=$rtuInfoDatagrid.datagrid('getSelected');
		if(row == null){
			alert("请先选中一条数据");
			return;
		}
		if($addConfigDialog != null){
			$addConfigDialog=null;
		}
			$addConfigDialog=$("#addConfigDialog").show().dialog({
					modal : true,
					title : '配置信息',
					width:900,
					height:480,
					buttons : [ {
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$addConfigDialog.dialog('close');
						}
					},{
						text : '保存配置',
						style:'text-align:center',
						handler : function() {
							saveConfigInfo();	
						}
					}],
					onClose:function(){
						//清空弹窗里面的数据信息
						$("#slaveTab tr:not(:first)").remove();
        				$("#translateParentDiv").empty();
					}
			}).dialog('close');
		
		$addConfigDialog.dialog('open');
	}
	/*编辑配置信息*/
	function editConfigFun(index){
		var row=$rtuConfigDatagrid.datagrid("getRows")[index];
		if(row == null){
			alert("请先选中一条数据");
			return;
		}
		//alert(configId);
		if($addConfigDialog != null){
			$addConfigDialog==null;
		}
		$addConfigDialog=$("#addConfigDialog").show().dialog({
				modal : true,
				title : '配置信息',
				width:900,
				height:480,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$addConfigDialog.dialog('close');
					}
				},{
					text : '保存配置',
					style:'text-align:center',
					handler : function() {
						saveConfigInfo();	
					}
				}],
				onClose:function(){
					//清空弹窗里面的数据信息
					$("#slaveTab tr:not(:first)").remove();
					//$("#slaveTab").empty();
        			$("#translateParentDiv").empty();
				}
		}).dialog('close');
		var configId=row.cid;
		var pubDelay=row.pubDelay;
		var heartDelay=row.heartDelay;
		$("#configId001").val(configId);
		$("#confHeartDelay001").numberbox('setValue',heartDelay);
		$("#confPubDelay001").numberbox('setValue',pubDelay);
			
		findSlaveTrans(configId);
		
	}
	/*查看配置信息*/
	function lookConfigFun(index){
		var row=$rtuConfigDatagrid.datagrid("getRows")[index];
		if(row == null){
			alert("请先选中一条数据");
			return;
		}
		//alert(configId);
		if($lookConfigDialog != null){
			$lookConfigDialog==null;
		}
		$lookConfigDialog=$("#lookConfigDialog").show().dialog({
				modal : true,
				title : '配置信息',
				width:900,
				height:480,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$lookConfigDialog.dialog('close');
					}
				}],
				onClose:function(){
					//清空弹窗里面的数据信息
					//$("#lookSlaveTab").empty();
					$("#lookSlaveTab tr:not(:first)").remove();
        			$("#lookTranslateParentDiv").empty();
				}
		}).dialog('close');
		var configId=row.cid;
		var pubDelay=row.pubDelay;
		var heartDelay=row.heartDelay;
		//$("#confHeartDelay002").numberbox('setValue',heartDelay);
		$("#confHeartDelay002").val(heartDelay);
		//$("#confPubDelay002").numberbox('setValue',pubDelay);
		$("#confPubDelay002").val(pubDelay);
		lookSlaveTrans(configId);
	}
	function removeConfigFun(index){
		var row=$rtuConfigDatagrid.datagrid("getRows")[index];
		if(row == null){
			myMessage("请先选中一条数据");
			return;
		}
		var configStatus=row.configStatus;
		var configId=row.cid;
		if(configStatus==1){
			$.messager.confirm('询问', '您确定要删除所选配置信息？', function(b) {
				if (b) {
					$.ajax({  
				        async:false,  
				        cache:false,  
				        type: 'POST',
				        data:{
							configId:configId
				        },  
				        dataType : "json",  
				        url: '${ctx}/rtuController/deleteConfigFun',
				        error: function () {
				            myMessage('请求失败');
				        },  
				        success:function(data){ 
				        	if(data.success){
				        		$rtuConfigDatagrid.datagrid('reload');
				        	}else{
				        		myMessage("对不起，操作失败，请联系管理员！！！");
				        	}
				        }  
				    });
				}
			});
		}else{
			myMessage("对不起，不能进行删除操作！！！");
		}
	}
	function findSlaveTrans(configId){
		if(configId==null || configId==""){
			return;
		}
		//async:false,
		$.ajax({  
	        cache:false,  
	        type: 'POST',
	        beforeSend:function(){
				if($refreshDialog!=null){
					$refreshDialog=null;
				}
				$refreshDialog=$("#refreshDialog").show().dialog({
					modal : true,
					title : '提示',
					width:300,
					closable: false,
					height:120,
					buttons : [ ],
					onClose:function(){
						//清空弹窗里面的数据信息
					}
				});
				$refreshDialog.dialog('open');
			},
	        data:{
				configId:configId
	        },  
	        dataType : "json",  
	        url: '${ctx}/rtuController/findSlaveTrans',
	        error: function () {
	        	$refreshDialog.dialog('close');
	            alert('请求失败');
	        },  
	        success:function(data){ 
				var tableHtmlStr="";
				var arr=$("tr[name='slavetr']");
				if(null!=arr && arr.length>0){
					for(var k=0;k<arr.length;k++){
						$(arr[k]).remove();
					}
				}
				if(null!=data && null!=data.obj&& null!=data.obj.slaveList&& data.obj.slaveList.length>0){
					for(var n=0;n<data.obj.slaveList.length;n++){
						tableHtmlStr+="<tr name='slavetr'  id='"+data.obj.slaveList[n].id+"'>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveList[n].slaveName+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveList[n].slaveId+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveList[n].slaveOrder+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;border-right:1px solid #D0E3F2;'>";
        						tableHtmlStr+="<label><a onclick='editSlaveConfigFun("+data.obj.slaveList[n].id+");' href='javascript:void(0);' style='width:120px;height:30px;'><image style='height:20px;width:20px;line-height:30px;vertical-align:middle;' src='${static}/js/jquery-easyui/1.4.4/themes/icons/update.gif'/><label style='width:60px;height:30px;line-height:30px;vertical-align:middle;margin-left:5px;'>复制</label></a></label>";
        						tableHtmlStr+="<label><a onclick='deleteSlaveFun("+data.obj.slaveList[n].id+");' href='javascript:void(0);' style='width:120px;margin-left:10px;height:30px;'><image style='height:20px;width:20px;line-height:30px;vertical-align:middle;' src='${static}/js/jquery-easyui/1.4.4/themes/icons/btn-cancel.png'/><label style='width:60px;height:30px;line-height:30px;vertical-align:middle;margin-left:5px;cursor:pointer;'>删除</label></a></label>";
        						tableHtmlStr+="<input type='hidden' name='slaveId' value='"+data.obj.slaveList[n].id+"'>";
        					tableHtmlStr+="</td>";
        				tableHtmlStr+="</tr>";
					}
				}
        		$("#slaveTab").append(tableHtmlStr);
        		$("#translateParentDiv").empty();
        		if(null!=data && null!=data.obj&& null!=data.obj.transList&& data.obj.transList.length>0){
					var porpertyObjArray=[];
					for(var l=0;l<data.obj.transList.length;l++){
						//treeIds.push(data.obj.transList[l].treeId);
						var porpertyObj=new Object();
						porpertyObj.treeLevel=data.obj.transList[l].treeLevel;
						porpertyObj.treeId=data.obj.transList[l].treeId;
						porpertyObj.pid=data.obj.transList[l].pid;
						porpertyObj.treeName=data.obj.transList[l].treeName;
						//htmlStr="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
						var inputName=data.obj.transList[l].propertyKey;//$(inputObj).attr("name");
						var inputValue=data.obj.transList[l].propertyVal;//$(inputObj).val();
						var textName=data.obj.transList[l].propertyName;//$($(contentDiv).find("span[name='"+inputName+"']")[0]).text();
						porpertyObj.proName=textName;
						porpertyObj.proKey=inputName;
						porpertyObj.proVal=inputValue;
						porpertyObj.proType=data.obj.transList[l].propertyType;//$(inputObj).attr("type");
						porpertyObj.checkType="false";
						porpertyObjArray.push(porpertyObj);
						if(l==data.obj.transList.length-1){
							addPorpertyObjToDiv2(data.obj.transList[l],1);
						}else{
							addPorpertyObjToDiv2(data.obj.transList[l],2);
						}
					}
					$("input[name=translatePropVal]").val(JSON.stringify(porpertyObjArray));
        		}
        		if($refreshDialog){
        			$refreshDialog.dialog('close');
        		}
        		if($addConfigDialog){
        			$addConfigDialog.dialog('open');
        		}
	        }  
	    });
	}
	function lookSlaveTrans(configId){
		if(configId==null || configId==""){
			return;
		}
		$.ajax({  
	        async:false,  
	        cache:false,  
	        type: 'POST',
	        data:{
				configId:configId
	        },  
	        dataType : "json",  
	        url: '${ctx}/rtuController/lookSlaveTrans',
	        error: function () {
	            alert('请求失败');
	        },  
	        success:function(data){ 
				var tableHtmlStr="";
				var arr=$("tr[name='lookslavetr']");
				if(null!=arr && arr.length>0){
					for(var k=0;k<arr.length;k++){
						$(arr[k]).remove();
					}
				}
			
				if(null!=data && null!=data.obj && null!=data.obj.slaveList&& data.obj.slaveList.length>0){
					for(var n=0;n<data.obj.slaveList.length;n++){
						tableHtmlStr+="<tr name='lookslavetr'  id='"+data.obj.slaveList[n].id+"'>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveList[n].slaveName+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveList[n].slaveId+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveList[n].slaveOrder+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;border-right:1px solid #D0E3F2;'>";
        						tableHtmlStr+="<label><a onclick='lookSlaveConfigFun("+data.obj.slaveList[n].id+");' href='javascript:void(0);' style='width:120px;height:40px;cursor:pointer;'><image style='height:20px;width:20px;line-height:30px;vertical-align:middle;' src='${static}/js/jquery-easyui/1.4.4/themes/icons/search.png'/><label style='width:60px;height:30px;line-height:30px;vertical-align:middle;margin-left:5px;cursor:pointer;'>详情</label></a></label>";
        					tableHtmlStr+="</td>";
        				tableHtmlStr+="</tr>";
					}
				}
        		$("#lookSlaveTab").append(tableHtmlStr);
        		$("#lookTranslateParentDiv").empty();
        		if(null!=data && null!=data.obj && null!=data.obj.transList&& data.obj.transList.length>0){
					var porpertyObjArray=[];
					for(var l=0;l<data.obj.transList.length;l++){
						//treeIds.push(data.obj.transList[l].treeId);
						var porpertyObj=new Object();
						porpertyObj.treeLevel=data.obj.transList[l].treeLevel;
						porpertyObj.treeId=data.obj.transList[l].treeId;
						porpertyObj.pid=data.obj.transList[l].pid;
						porpertyObj.treeName=data.obj.transList[l].treeName;
						//htmlStr="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
						var inputName=data.obj.transList[l].propertyKey;//$(inputObj).attr("name");
						var inputValue=data.obj.transList[l].propertyVal;//$(inputObj).val();
						var textName=data.obj.transList[l].propertyName;//$($(contentDiv).find("span[name='"+inputName+"']")[0]).text();
						porpertyObj.proName=textName;
						porpertyObj.proKey=inputName;
						porpertyObj.proVal=inputValue;
						porpertyObj.proType=data.obj.transList[l].propertyType;//$(inputObj).attr("type");
						porpertyObj.checkType="false";
						porpertyObjArray.push(porpertyObj);
						if(l==data.obj.transList.length-1){
							lookPorpertyObjToDiv2(data.obj.transList[l],1);
						}else{
							lookPorpertyObjToDiv2(data.obj.transList[l],2);
						}
					}
        		}
        		if($lookConfigDialog){
					$lookConfigDialog.dialog('open');
				}
	        }  
	    });
	}
    function uniqueArray(arr) {   
    	var result = [], isRepeated;   
    	for (var i = 0, len = arr.length; i < len; i++) {     
    		isRepeated = false;
    		for (var j = 0, len = result.length; j < len; j++) {
    			if (arr[i] == result[j]) {
    		    	isRepeated = true;
    		        break;
    			}
    		} 
    		if (!isRepeated) {
    			result.push(arr[i]);
    	    }   
    	}   
    	return result;
	} 
	
	function findTransInfo(configId){
		if(configId==null || configId==""){
			return;
		}
		$.ajax({  
	        async:false,  
	        cache:false,  
	        type: 'POST',
	        data:{
				configId:configId
	        },  
	        dataType : "json",  
	        url: '${ctx}/rtuController/findTransInfo',
	        error: function () {
	            alert('请求失败');
	        },  
	        success:function(data){ 
				if(null!=data&&null!=data.obj&&data.obj.length>0){
					
				}
	        }  
	    });
	}
	//保存配置信息
	function saveConfigInfo(){
		var slaveIds="";
		$.each($("input[name=slaveId]"),function(index,obj){
			if(index == 0){
				slaveIds+=$(obj).val();
			}else{
				slaveIds+=","+$(obj).val();
			}
		});
		var row=$rtuInfoDatagrid.datagrid('getSelected');
	    $.ajax({  
	        async:false,  
	        cache:false,  
	        type: 'POST',
	        data:{
				rtuId:row.id,
				pubDelay:$("input[name=confPubDelay]").val(),
				heartDelay:$("input[name=confHeartDelay]").val(),
				slaveIds:slaveIds,
				translateContent:$("input[name=translatePropVal]").val()
	        },  
	        dataType : "json",  
	        url: '${ctx}/rtuController/addRtuConfig',
	        error: function () {
	            alert('请求失败');  
	        },  
	        success:function(data){ 
				if($addConfigDialog){
					$addConfigDialog.dialog('close');
				}
				$rtuConfigDatagrid.datagrid('reload');
	        }  
	    });
 
	}
	
	
	/*打开新增采集器弹窗*/
	function addSlaveConfigFun(){
		if($addSlaveConfigDialog!=null){
			$addSlaveConfigDialog=null;
		}
		$addSlaveConfigDialog=$("#addSlaveConfigDialog").show().dialog({
				modal : true,
				title : '采集器配置信息',
				width:1000,
				height:480,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$addSlaveConfigDialog.dialog('close');
					}
				},{
					text : '确认添加',
					style:'text-align:center',
					handler : function() {
						makeSureAddSlave(1);
					}
				}],
				onClose:function(){
					//清空弹窗里面的数据信息
					$("#div2Page").empty();
					
				}
		}).dialog('close');
		
		
		//初始化模板Ztree #modoelTree
			var setting = {  
		    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
		    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
		    showLine : true, 
		    checkable : true,
		    check: {
		    	chkStyle:"checkbox",
				enable: true,
				dblClickExpand: false
			},view: {
				fontCss: getFontCss
			},callback: {
				onCheck:zTreeOnCheck
			}              
		};  
	
		//onCheck:onCheckedOrg
		//onClick:onClickOwnOrg,
		//setting.check.chkboxType = { "Y" : "s", "N" : "s" };
  		//settingOwnOrg.check.enable = false;
  		//注roleId为用户ID
	    $.ajax({  
	        async : false,  
	        cache:false,  
	        type: 'POST',
	        data:{
				pid:2
	        },  
	        dataType : "JSON",  
	        url: '${ctx}/rtuController/createRtuModelZtree',//请求的action路径  
	        error: function () {
	            alert('请求失败');  
	        },  
	        success:function(data){ //请求成功后处理函数。 
	           var treeNodes =data.obj;   //把后台封装好的简单Json格式赋给treeNodes  
				$.fn.zTree.init($("#modoelTree"), setting, treeNodes);	
				var treeObj = $.fn.zTree.getZTreeObj("modoelTree");	
				treeObj.expandAll(true);		
	        }  
	    });  

		$addSlaveConfigDialog.dialog('open');
	}
	//新增RTU初始化配置
	function addRtuConfigFun(){
		var row=$rtuInfoDatagrid.datagrid('getSelected');
		if(row == null){
			alert("请先选中一条数据");
			return;
		}
		if($addRtuConfigDialog != null){
			$addRtuConfigDialog=null;
		}
		$addRtuConfigDialog=$("#addRtuConfigDialog").show().dialog({
					modal : true,
					title : '采集器信息',
					width:1000,
					height:480,
					buttons : [ {
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$addRtuConfigDialog.dialog('close');
						}
					}],
					onClose:function(){
						//清空弹窗里面的数据信息
						$rtuInfoDatagrid.datagrid('reload');
					}

		}).dialog('close');
		
		
		//初始化datagrid
		if($slaveInfoDatagrid != null){
			$slaveInfoDatagrid=null;
		}

		$slaveInfoDatagrid=$("#slaveInfoDatagrid").datagrid({
			url:'${ctx}/rtuController/rtuSlaveBaseInfoDataGrid?rtuId='+row.id,
			singleSelect:true,
			idField : 'id',
			toolbar:'#slaveInfoToolbar',
			rownumbers:true,
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
			frozenColumns : [[ {
				title : 'id',
				field : 'id',
				width : 50,
				checkbox : true
			}]],
			columns : [ [ 
				 {
					field : 'name',
					title : '名称',
					width : 200		
				},{
					field : 'num',
					title : '序号',
					width : 200
				},{
					field : 'type',
					title : '类型',
					width : 200
				},{
					field : 'sid',
					title : 'sid',
					width : 200
				},{
					field : 'cmd',
					title : 'cmd',
					width : 200
				},{
					title : '操作',
					field : '1',
					width :200,
					formatter:function(value,rec,i){
						var btnHtml="";
							btnHtml="<a href='javascript:addSlaveInfo("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";
							btnHtml+="<a href='javascript:deleteRtuSlave("+rec.id+");' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
						return btnHtml;
					}
			} ]]
		});
		



		$addRtuConfigDialog.dialog('open');
	}
	
	
	
	/*打开编辑采集器弹窗*/
	function editSlaveConfigFun(slaveId){
		//alert(slaveId);
		if(null!=slaveId && ""!=slaveId){
			if($editSlaveConfigDialog!=null){
				$editSlaveConfigDialog=null;
			}
			$editSlaveConfigDialog=$("#editSlaveConfigDialog").show().dialog({
				modal : true,
				title : '采集器配置信息',
				width:1000,
				height:480,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$("#editDiv2Page").html("");
						$editSlaveConfigDialog.dialog('close');
					}
				},{
					text : '确认添加',
					style:'text-align:center',
					handler : function() {
						makeSureAddSlave(2);
					}
				}],
				onClose:function(){
					//清空弹窗里面的数据信息
					$("#editDiv2Page").html("");
				}
			}).dialog('close');
			
			//初始化模板Ztree #modoelTree
			var setting = {  
			    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
			    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
			    showLine : true, 
			    checkable : true,
			    check: {
			    	chkStyle:"checkbox",
					enable: true,
					dblClickExpand: false
				},view: {
					fontCss: getFontCss
				},callback: {
					onCheck:editzTreeOnCheck
				}              
			};
			//onCheck:onCheckedOrg
			//onClick:onClickOwnOrg,
			//setting.check.chkboxType = { "Y" : "s", "N" : "s" };
	  		//settingOwnOrg.check.enable = false;
	  		//注roleId为用户ID
	  		$.ajax({  
		        async : false,  
		        cache:false,  
		        type: 'POST',
		        data:{
					pid:2,
					slaveId:slaveId
		        },  
		        dataType : "JSON",  
		        url: '${ctx}/rtuController/editRtuModelZtree',//请求的action路径  
		        error: function () {
		            alert('请求失败');  
		        },  
		        success:function(data){ //请求成功后处理函数。 
		           var treeNodes =data.obj;   //把后台封装好的简单Json格式赋给treeNodes  
					$.fn.zTree.init($("#editModoelTree"), setting, treeNodes);	
					var treeObj = $.fn.zTree.getZTreeObj("editModoelTree");	
					treeObj.expandAll(true);
					$("#editDiv2Page").html("");
					//获取树选中的节点
					var nodes = treeObj.getCheckedNodes(true);
					if(null!=nodes && nodes.length>0){
						for(var k=0;k<nodes.length;k++){
							editCheckTreeNode(nodes[k]);
						}
					}
					//获取树所有节点
					//var nodes2=treeObj.getNodes();
					//var nodes_array = treeObj.transformToArray (nodes2);
					
					
		        }  
		    });
		    $editSlaveConfigDialog.dialog('open');
		}
	}
	function zTreeBeforeCheck(treeId, treeNode) {
	    return false;
	};
	function lookSlaveConfigFun(slaveId){
		//alert(slaveId);
		if(null!=slaveId && ""!=slaveId){
			if($lookSlaveConfigDialog!=null){
				$lookSlaveConfigDialog=null;
			}
			$lookSlaveConfigDialog=$("#lookSlaveConfigDialog").show().dialog({
				modal : true,
				title : '采集器配置信息',
				width:1000,
				height:480,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$lookSlaveConfigDialog.dialog('close');
					}
				}],
				onClose:function(){
					//清空弹窗里面的数据信息
					$("#lookDiv2Page").empty();
				}
			}).dialog('close');
			
			//初始化模板Ztree #modoelTree
			var setting = {  
			    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
			    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
			    showLine : true, 
			    checkable : true,
			    check: {
			    	chkStyle:"checkbox",
					enable: true,
					dblClickExpand: false
				},view: {
					fontCss: getFontCss
				},callback: {
					//onCheck:editzTreeOnCheck
					beforeCheck: zTreeBeforeCheck
				}              
			};
			//onCheck:onCheckedOrg
			//onClick:onClickOwnOrg,
			//setting.check.chkboxType = { "Y" : "s", "N" : "s" };
	  		//settingOwnOrg.check.enable = false;
	  		//注roleId为用户ID
	  		$.ajax({  
		        async : false,  
		        cache:false,  
		        type: 'POST',
		        data:{
					pid:2,
					slaveId:slaveId
		        },  
		        dataType : "JSON",  
		        url: '${ctx}/rtuController/editRtuModelZtree',//请求的action路径  
		        error: function () {
		            alert('请求失败');  
		        },  
		        success:function(data){ //请求成功后处理函数。 
		           var treeNodes =data.obj;   //把后台封装好的简单Json格式赋给treeNodes  
					$.fn.zTree.init($("#lookEditModoelTree"), setting, treeNodes);	
					var treeObj = $.fn.zTree.getZTreeObj("lookEditModoelTree");	
					treeObj.expandAll(true);
					//获取树选中的节点
					var nodes = treeObj.getCheckedNodes(true);
					if(null!=nodes && nodes.length>0){
						for(var k=0;k<nodes.length;k++){
							lookCheckTreeNode(nodes[k]);
						}
					}
					//获取树所有节点
					//var nodes2=treeObj.getNodes();
					//var nodes_array = treeObj.transformToArray (nodes2);
		        }  
		    });
		    $lookSlaveConfigDialog.dialog('open');
		}
	}
	/*打开新增传输配置弹窗*/
	function addTranslateConfigFun(){
		var translatePropVal=$("input[name=translatePropVal]").val();
		var json1=null;
		if(null!=translatePropVal && ""!=translatePropVal){
			translatePropVal="{result:"+translatePropVal+"}";
			json1=eval("("+translatePropVal+")");
		}
		makeSureAddTranslate(2,json1);
		if($addTranslateConfigDialog!=null){
			$addTranslateConfigDialog=null;
		}
		$addTranslateConfigDialog=$("#addTranslateConfigDialog").show().dialog({
				modal : true,
				title : '传输配置信息',
				width:1000,
				height:480,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$addTranslateConfigDialog.dialog('close');
					}
				},{
					text : '保存',
					style:'text-align:center',
					handler : function() {
						makeSureAddTranslate(1,null);
					}
				}],
				onClose:function(){
					//清空弹窗里面的数据信息
					$("#div3Page").empty();
					
				}
		}).dialog('close');
		
		
		//初始化模板Ztree #modoelTree
			var setting = {  
		    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
		    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
		    showLine : true, 
		    checkable : true,
		    check: {
		    	chkStyle:"checkbox",
				enable: true,
				dblClickExpand: false
			},view: {
				fontCss: getFontCss
			},callback: {
				onCheck:translateTreeOnCheck
			}              
		};  
	
		//onCheck:onCheckedOrg
		//onClick:onClickOwnOrg,
		//setting.check.chkboxType = { "Y" : "s", "N" : "s" };
  		//settingOwnOrg.check.enable = false;
  		//注roleId为用户ID
	    $.ajax({  
	        async : false,  
	        cache:false,  
	        type: 'POST',
	        data:{
				pid:3
	        },  
	        dataType : "JSON",  
	        url: '${ctx}/rtuController/createRtuModelZtree',//请求的action路径  
	        error: function () {
	            alert('请求失败');  
	        },  
	        success:function(data){ //请求成功后处理函数。 
	           var treeNodes =data.obj;   //把后台封装好的简单Json格式赋给treeNodes  
				$.fn.zTree.init($("#translateTree"), setting, treeNodes);
				var treeObj = $.fn.zTree.getZTreeObj("translateTree");	
				treeObj.expandAll(true);
				//获取树所有节点，有勾选的项就设置选中状态
				var nodes2=treeObj.getNodes();
				var nodes_array = treeObj.transformToArray (nodes2);
				var	 arrtree=new Array();
				var num=0;
				for(var i=0;i<nodes_array.length;i++){
					var node1=nodes_array[i];
					if(null!=json1&& null!=json1.result && json1.result.length>0){
						for(var k=0;k<json1.result.length;k++){
							if(json1.result[k].treeId==node1.id){
								//node1.checked=true;
								//treeObj.updateNode(node1);
								treeObj.checkNode(node1, true, true);
								var json2=null;
								var translatePropVal=$("input[name=translatePropVal]").val();
								if(null!=translatePropVal && ""!=translatePropVal){
									translatePropVal="{result:"+translatePropVal+"}";
									json2=eval("("+translatePropVal+")");
								}
								editTranslateContent(node1,json2);
								break;
							}
						}
					}
				}
	        }  
	    });  

		$addTranslateConfigDialog.dialog('open');
	}
	
	
		//点击传输配置弹窗的确认按钮事件	
		function makeSureAddTranslate(flag,jsonstr){
			if(flag==1 || flag=="1"){
				$("#translateParentDiv").empty();
				var zTree = $.fn.zTree.getZTreeObj("translateTree");
				var allCheckedNodes = null!=zTree?zTree.getCheckedNodes(true):null;
				
				var porpertyObjArray=[];
				if(allCheckedNodes!=null && allCheckedNodes.length>0){
					$.each(allCheckedNodes,function(index,node){
						var parentDiv= $("div[name="+node.id+"]");
						if(parentDiv != null){				
							var contentDiv=parentDiv.find("div[name=transcontent]");
							if(contentDiv != null && contentDiv.length>0){
								var arrcd=$(contentDiv).find("input");
								if(null!=arrcd && arrcd.length>0){
									$.each(arrcd,function(index,inputObj){
										var porpertyObj=new Object();
										porpertyObj.treeLevel=node.level;
										porpertyObj.treeId=node.id;
										porpertyObj.pid=node.pid;
										porpertyObj.treeName=node.name;
										var inputName= $(inputObj).attr("name");
										var inputValue=$(inputObj).val();
										var textName=$($(contentDiv).find("span[name='"+inputName+"']")[0]).text();
										porpertyObj.proName=textName;
										porpertyObj.proKey=inputName;
										porpertyObj.proVal=inputValue;
										porpertyObj.proType=$(inputObj).attr("type");
										porpertyObj.checkType="false";
										porpertyObjArray.push(porpertyObj);
										if(index==arrcd.length-1){
											addPorpertyObjToDiv(porpertyObj,1);
										}else{
											addPorpertyObjToDiv(porpertyObj,2);
										}
									});
								}
							}
						}	
					});
				}
				if($addTranslateConfigDialog){
					$addTranslateConfigDialog.dialog('close');
				}
			}else if(flag==2 || flag=="2"){
				var porpertyObjArray=[];
				var contentDiv=$("div[name=transcontent]");
				if(contentDiv != null && contentDiv.length>0){
					for(var j=0;j<contentDiv.length;j++){
						var arrcd=$(contentDiv[j]).find("input");
						if(null!=arrcd && arrcd.length>0){
							$.each(arrcd,function(index,inputObj){
								var porpertyObj=new Object();
								var inputName= $(inputObj).attr("name");
								var treeId=$(contentDiv[j]).parent().parent().attr("id");
								if(null!=jsonstr && null!=jsonstr.result&& jsonstr.result.length>0){
									for(var k=0;k<jsonstr.result.length;k++){
										if(jsonstr.result[k].proKey==inputName && jsonstr.result[k].treeId==treeId){
											porpertyObj.treeLevel=jsonstr.result[k].treeLevel;
											porpertyObj.treeId=jsonstr.result[k].treeId;
											porpertyObj.pid=jsonstr.result[k].pid;
											porpertyObj.treeName=jsonstr.result[k].treeName;
											break;
										}
									}
								}
								var inputValue=$(inputObj).val();
								var textName=$($(contentDiv).find("span[name='"+inputName+"']")[0]).text();
								porpertyObj.proName=textName;
								porpertyObj.proKey=inputName;
								porpertyObj.proVal=inputValue;
								porpertyObj.proType=$(inputObj).attr("type");
								porpertyObj.checkType="false";
								porpertyObjArray.push(porpertyObj);
								if(index==arrcd.length-1){
									addPorpertyObjToDiv(porpertyObj,1);
								}else{
									addPorpertyObjToDiv(porpertyObj,2);
								}
							});
						}
					}
				}
			}
			$("input[name=translatePropVal]").val(JSON.stringify(porpertyObjArray));
		}
		
		//添加单个对象进入div
		function addPorpertyObjToDiv(obj,flag){
			if($("#"+obj.treeId).length > 0){
				var htmlStr="";
				if(flag==1|| flag=="1"){
					htmlStr="<div class='rightContent'><div class='rightDiv1'><span name='"+obj.proKey+"' class='rightSpan'>"+obj.proName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.proKey+"' value='"+obj.proVal+"' class='rightInput'/></div></div>";
				}else if(flag==2|| flag=="2"){
					htmlStr="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.proKey+"' class='rightSpan'>"+obj.proName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.proKey+"' value='"+obj.proVal+"' class='rightInput'/></div></div>";
				}
				var tagertObj=$("#"+obj.treeId).find("div[name=transcontent]");
				if(tagertObj != undefined && tagertObj != null && tagertObj.length > 0){
					$(tagertObj[0]).append(htmlStr);
				}
			}else{
				var htmlStr="";
				//htmlStr+="<div id='"+obj.treeId+"'><div name='title'>"+obj.treeName+"</div><div name='content'>";
				htmlStr+="<div id='"+obj.treeId+"'><fieldset><legend>"+obj.treeName+"</legend><div name='transcontent'>";
				if(flag==1|| flag=="1"){
					htmlStr+="<div class='rightContent'><div class='rightDiv1'><span name='"+obj.proKey+"' class='rightSpan'>"+obj.proName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.proKey+"' value='"+obj.proVal+"' class='rightInput'/></div></div>";
				}else if(flag==2|| flag=="2"){
					htmlStr+="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.proKey+"' class='rightSpan'>"+obj.proName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.proKey+"' value='"+obj.proVal+"' class='rightInput'/></div></div>";
				}
				htmlStr+="</fieldset></div>";
				$("#translateParentDiv").append(htmlStr);
			}
		}
		//添加单个对象进入div
		function addPorpertyObjToDiv2(obj,flag){
			if($("#"+obj.treeId).length > 0){
				var htmlStr="";
				if(flag==1|| flag=="1"){
					htmlStr="<div class='rightContent'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}else if(flag==2|| flag=="2"){
					htmlStr="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}
				var tagertObj=$("#"+obj.treeId).find("div[name=transcontent]");
				if(tagertObj != undefined && tagertObj != null && tagertObj.length > 0){
					$(tagertObj[0]).append(htmlStr);
				}
			}else{
				var htmlStr="";
				//htmlStr+="<div id='"+obj.treeId+"'><div name='title'>"+obj.treeName+"</div><div name='content'>";
				htmlStr+="<div id='"+obj.treeId+"'><fieldset><legend>"+obj.treeName+"</legend><div name='transcontent'>";
				if(flag==1|| flag=="1"){
					htmlStr+="<div class='rightContent'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}else if(flag==2|| flag=="2"){
					htmlStr+="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}
				htmlStr+="</fieldset></div>";
				$("#translateParentDiv").append(htmlStr);
			}
		}
		//添加单个对象进入div
		function lookPorpertyObjToDiv2(obj,flag){
			if($("#"+obj.treeId).length > 0){
				var htmlStr="";
				if(flag==1|| flag=="1"){
					htmlStr="<div class='rightContent'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}else if(flag==2|| flag=="2"){
					htmlStr="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}
				var tagertObj=$("#"+obj.treeId).find("div[name=looktranscontent]");
				if(tagertObj != undefined && tagertObj != null && tagertObj.length > 0){
					$(tagertObj[0]).append(htmlStr);
				}
			}else{
				var htmlStr="";
				//htmlStr+="<div id='"+obj.treeId+"'><div name='title'>"+obj.treeName+"</div><div name='content'>";
				htmlStr+="<div id='"+obj.treeId+"'><fieldset><legend>"+obj.treeName+"</legend><div name='looktranscontent'>";
				if(flag==1|| flag=="1"){
					htmlStr+="<div class='rightContent'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}else if(flag==2|| flag=="2"){
					htmlStr+="<div class='rightContent1'><div class='rightDiv1'><span name='"+obj.propertyKey+"' class='rightSpan'>"+obj.propertyName+"</span></div><div class='rightDiv2'><input type='text' name='"+obj.propertyKey+"' value='"+obj.propertyVal+"' class='rightInput'/></div></div>";
				}
				htmlStr+="</fieldset></div>";
				
				$("#lookTranslateParentDiv").append(htmlStr);
			}
		}
		//translateTree事件
		function translateTreeOnCheck(event, treeId, treeNode){
			editTranslateContent(treeNode,null);
		}
		function editTranslateContent(treeNode,jsonstr){
			if(treeNode.checked){
					$.ajax({
				 	url:'${ctx}/rtuController/querySlaveBaseProperty',
				 	data:{
						pid:treeNode.id
			        }, 
					type: 'POST',
			        dataType : "json",  
			        success:function(data){
			        	if(data != null && data.obj != null && data.obj.length > 0){
			        		//var strHTML="<div name='"+treeNode.id+"'><div name='title'>"+treeNode.name+"</div><div name='content'>";
			        		var strHTML="<div name='"+treeNode.id+"'><fieldset><legend>"+treeNode.name+"</legend><div name='transcontent'>";
			        		if(data.obj.length==1){
			        			var proObj=data.obj[0];
			        			var ky=proObj.propKey;
			        			var kyval=proObj.propDefault;
			        			var pid=proObj.pid;
			        			if(null!=jsonstr && null!=jsonstr.result && jsonstr.result.length>0){
			        				for(var m=0;m<jsonstr.result.length;m++){
			        					if(ky==jsonstr.result[m].proKey && pid==jsonstr.result[m].treeId){
			        						kyval=jsonstr.result[m].proVal;
			        						break;
			        					}
			        				}
			        			}
			        			if(proObj.isRelateRefer == "Y"){
			        				var checkboxHtml="";
			        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
			        					for(var m=0;m<proObj.listRtuRefer.length;m++){
			        						var referObject= proObj.listRtuRefer[m];
			        						checkboxHtml+="<input type='checkbox' name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"<br>";
			        					}
			        				}
			        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div>"+checkboxHtml+"</div></div>";
			        			}else{
			        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div><input type='text' name='"+proObj.propKey+"' value='"+kyval+"' class='rightInput'/></div></div>";
			        			}
			        		}else{
			        			for(var k=0;k<data.obj.length;k++){
				        			var proObj=data.obj[k];
				        			var ky=proObj.propKey;
				        			var kyval=proObj.propDefault;
				        			var pid=proObj.pid;
				        			if(null!=jsonstr && null!=jsonstr.result && jsonstr.result.length>0){
				        				for(var m=0;m<jsonstr.result.length;m++){
				        					if(ky==jsonstr.result[m].proKey && pid==jsonstr.result[m].treeId){
				        						kyval=jsonstr.result[m].proVal;
				        						break;
				        					}
				        				}
				        			}
				        			if(proObj.isRelateRefer == "Y"){
				        				var checkboxHtml="";
				        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
				        					for(var m=0;m<proObj.listRtuRefer.length;m++){
				        						var referObject= proObj.listRtuRefer[m];
				        						checkboxHtml+="<input type='checkbox' name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"<br>";
				        					}
				        				}
				        				if(k==data.obj.length-1){
				        					strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div>"+checkboxHtml+"</div></div>";
				        				}else{
				        					strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div>"+checkboxHtml+"</div></div>";
				        				}
				        				
				        			}else{
				        				if(k==data.obj.length-1){
				        					strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div><input type='text' name='"+proObj.propKey+"' value='"+kyval+"' class='rightInput'/></div></div>";
				        				}else{
				        					strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div><input type='text' name='"+proObj.propKey+"' value='"+kyval+"' class='rightInput'/></div></div>";
				        				}
				        			}
				        		}
			        		}
			        		strHTML+="</fieldset></div>";
			        		$("#div3Page").append(strHTML);
			        	}
			
			        } 
				});
			
			}else{
				$("div[name="+treeNode.id+"]").remove();
		   		if(parseInt(treeNode.level) == 0){
					if(treeNode!=null && treeNode.children!=null && treeNode.children.length>0){
						$.each(treeNode.children,function(index,childNode){
							$("div[name="+childNode.id+"]").remove();
						});
					}
				}
			}
		}
		
		//modelZTREE事件
		function zTreeOnCheck(event, treeId, treeNode) {
				checkTreeNode(treeNode);
		}
		function checkTreeNode(treeNode){
			
		//选中树节点的事件
			if(treeNode.checked){
				var url="${ctx}/rtuController/querySlaveBaseProperty";
				if(parseInt(treeNode.level) == 0){
					if(treeNode!=null && treeNode.children!=null && treeNode.children.length>0){
						$.each(treeNode.children,function(index,childNode){
							queryTreeNextNodes("${ctx}/rtuController/querySlaveBaseProperty",childNode);
						});
					}else{
						url="${ctx}/rtuController/querySlaveBaseProperty";
						queryTreeNextNodes(url,treeNode);
					}
				}else{
					queryTreeNextNodes(url,treeNode);
				}
		   }else{
		   	//取消选中树节点事件
		   	$("div[name="+treeNode.id+"]").remove();
		   		if(parseInt(treeNode.level) == 0){
					if(treeNode!=null && treeNode.children!=null && treeNode.children.length>0){
						$.each(treeNode.children,function(index,childNode){
							$("div[name="+childNode.id+"]").remove();
						});
					}
				}
		   	
		   }
		}
	//editModelZTREE事件
	function editzTreeOnCheck(event, treeId, treeNode) {
		editCheckTreeNode(treeNode);
	}
	
	function editCheckTreeNode(treeNode){
		//选中树节点的事件
		if(treeNode.checked){
			var url="${ctx}/rtuController/editSlaveBaseProperty";
			if(parseInt(treeNode.level) == 0){
				if(treeNode!=null && treeNode.children!=null && treeNode.children.length>0){
					$.each(treeNode.children,function(index,childNode){
						editTreeNextNodes("${ctx}/rtuController/editSlaveBaseProperty",childNode);
					});
				}else{
					url="${ctx}/rtuController/editSlaveBaseProperty";
					editTreeNextNodes(url,treeNode);
				}
			}else{
				editTreeNextNodes(url,treeNode);
			}
	   }else{
	   	//取消选中树节点事件
	   	$("div[name="+treeNode.id+"]").remove();
	   		if(parseInt(treeNode.level) == 0){
				if(treeNode!=null && treeNode.children!=null && treeNode.children.length>0){
					$.each(treeNode.children,function(index,childNode){
						$("div[name="+childNode.id+"]").remove();
					});
				}
			}
	   	
	   }
	}
	function lookCheckTreeNode(treeNode){
		$("#lookDiv2Page").empty();
		//选中树节点的事件
		if(treeNode.checked){
			var url="${ctx}/rtuController/editSlaveBaseProperty";
			if(parseInt(treeNode.level) == 0){
				if(treeNode!=null && treeNode.children!=null && treeNode.children.length>0){
					$.each(treeNode.children,function(index,childNode){
						lookTreeNextNodes("${ctx}/rtuController/editSlaveBaseProperty",childNode);
					});
				}else{
					url="${ctx}/rtuController/editSlaveBaseProperty";
					lookTreeNextNodes(url,treeNode);
				}
			}else{
				lookTreeNextNodes(url,treeNode);
			}
	   }
	}
	function queryTreeNextNodes(url,treeNode){
			//选中数据项
			$.ajax({
			 	url:url,
			 	data:{
					pid:treeNode.id
		        }, 
				type: 'POST',
		        dataType : "json",  
		        success:function(data){
		        	if(data != null && data.obj != null && data.obj.length > 0){
		        		//var strHTML="<div name='"+treeNode.id+"'><div name='title'>"+treeNode.name+"</div><div name='content'>";
		        		var strHTML="<div name='"+treeNode.id+"'><fieldset><legend>"+treeNode.name+"</legend><div name='content'  >";
		        		/*for(var k=0;k<data.obj.length;k++){
		        			var proObj=data.obj[k];
		        			if(proObj.isRelateRefer == "Y"){
		        				var checkboxHtml="";
		        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
		        					for(var m=0;m<proObj.listRtuRefer.length;m++){
		        						var referObject= proObj.listRtuRefer[m];
		        						checkboxHtml+="<input type='checkbox' name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"<br>";
		        					}
		        				}
		        				strHTML+="<span name='"+proObj.propKey+"'>"+proObj.propDesc+"</span>:"+checkboxHtml;
		        			}else{
		        				strHTML+="<span name='"+proObj.propKey+"'>"+proObj.propDesc+"</span>:<input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' style='width:120px;'/><br/>";
		        			}
		        		}*/
		        		if(data.obj.length==1){
		        			var proObj=data.obj[0];
		        			if(proObj.isRelateRefer == "Y"){
		        				var checkboxHtml="";
		        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
		        					for(var m=0;m<proObj.listRtuRefer.length;m++){
		        						var referObject= proObj.listRtuRefer[m];
		        						checkboxHtml+="<input type='checkbox' style='width:30px;'  name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
		        					}
		        				}
		        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
		        			}else{
		        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput' /></div></div>";
		        			}
		        		}else if(data.obj.length>1){
		        			for(var k=0;k<data.obj.length;k++){
			        			var proObj=data.obj[k];
			        			if(proObj.isRelateRefer == "Y"){
			        				var checkboxHtml="";
			        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
			        					for(var m=0;m<proObj.listRtuRefer.length;m++){
			        						var referObject= proObj.listRtuRefer[m];
			        						checkboxHtml+="<input type='checkbox' style='width:30px;'  name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
			        					}
			        				}
			        				if(k==data.obj.length-1){
			        					strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
			        				}else{
			        					strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
			        				}
			        			}else{
			        				if(k==data.obj.length-1){
			        					if(proObj.propKey=="slave_index" || proObj.propKey=="slave_sid" || proObj.propKey=="reg_id" || proObj.propKey=="reg_rel_address" || proObj.propKey=="reg_type"){
			        						strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='easyui-numberbox rightInput' style='height:32px;line-height:32px;' data-options='min:0,precision:0'/></div></div>";
			        					}else{
			        						strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput'/></div></div>";
			        					}
			        				}else{
			        					if(proObj.propKey=="slave_index" || proObj.propKey=="slave_sid" || proObj.propKey=="reg_id" || proObj.propKey=="reg_rel_address" || proObj.propKey=="reg_type"){
			        						strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='easyui-numberbox rightInput' style='height:32px;line-height:32px;' data-options='min:0,precision:0'/></div></div>";
			        					}else{
			        						strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput'/></div></div>";
			        					}
			        				}
			        			}
			        		}
		        		}
		        		strHTML+="</div></fieldset></div>";
		        		var treeObj = $.fn.zTree.getZTreeObj("modoelTree");	
						//获取树所有节点
						var curnode=0;
						var arr=[];
						var nodes2=treeObj.getNodes();
						var nodes_array = treeObj.transformToArray (nodes2);
						//获取树选中的节点
						var nodes = treeObj.getCheckedNodes(true);
						var divArr=$("#div2Page").children("div");
						var divName=[];
						if(divArr!=null && divArr.length>0){
							for(var l=0;l<divArr.length;l++){
								divName.push($(divArr[l]).attr("name"));
						 	}
							var checkArr=[];
							if(null!=divName && divName.length>0){
								for(var i=0;i<divName.length;i++){
									for(var j=0;j<nodes.length;j++){
										if(parseInt(nodes[j].id)==parseInt(divName[i])){
											checkArr.push(nodes[j]);
										}
									}
								}
								if(null!=nodes_array&& nodes_array.length>0){
									for(var k=0;k<nodes_array.length;k++){
										if(nodes_array[k].id==treeNode.id){
											curnode=k;
										}
									}
									if(null!=checkArr && checkArr.length>0){
										for(var n=0;n<checkArr.length;n++){
											for(var m=0;m<nodes_array.length;m++){
												if(checkArr[n].id==nodes_array[m].id){
													arr.push(m);
												}
											}
											
										}
									}
									var minindex=0;
									var maxindex=0;
									if(null!=arr && arr.length>0){
										minindex=arr[0];
										maxindex=arr[0];
										for(var j=1;j<arr.length;j++){
											if(arr[j]<=minindex){
												minindex=arr[j];
											}
											if(arr[j]>=maxindex){
												maxindex=arr[j];
											}
										}
									}
									var treeId="";
									if(curnode<minindex){
										treeId=nodes_array[minindex].id;
										$("div[name="+treeId+"]").before(strHTML);
									}else if(curnode>minindex && curnode<maxindex){
										arr.sort(function(a, b) {
										  return Math.abs(a-curnode)-Math.abs(b-curnode);
										})[0];
										var ins=arr[0];
										if(curnode<=ins){
											treeId=nodes_array[ins].id;
											$("div[name="+treeId+"]").before(strHTML);
										}else{
											treeId=nodes_array[ins].id;
											$("div[name="+treeId+"]").after(strHTML);
										}
									}else if(curnode>maxindex){
										treeId=nodes_array[maxindex].id;
										$("div[name="+treeId+"]").after(strHTML);
									}
								}
							}
						}else{
							$("#div2Page").append(strHTML);
						}
		        	}
		        } 
			});
	}
	
	//下发
	function confirmSend(rtuId){
		$.ajax({
		 	url:'${ctx}/rtuController/updateRtuState',
		 	data:{
				rtuId:rtuId
	        }, 
			type: 'POST',
	        dataType : "json",  
	        success:function(data){
	        	if(data!=null){
	        	   myMessage(data.msg);
	        	}
	        	$rtuInfoDatagrid.datagrid('reload');
	        },error:function(){
	        	   myMessage("系统在执行过程中出现了错误，请再次执行");
	        }
		
		});
	}
	
	
	function editTreeNextNodes(url,treeNode){
		//选中数据项
		$.ajax({
		 	url:url,
		 	data:{
				pid:treeNode.id,
				slaveId:treeNode.slaveId
	        }, 
			type: 'POST',
	        dataType : "json",  
	        success:function(data){
	        	if(data != null && data.obj != null && data.obj.length > 0){
	        		//var strHTML="<div name='"+treeNode.id+"'><div name='title'>"+treeNode.name+"</div><div name='content'>";
	        		var strHTML="<div name='"+treeNode.id+"'><fieldset><legend>"+treeNode.name+"</legend><div name='content'  >";
	        		if(data.obj.length==1){
	        			var proObj=data.obj[0];
	        			if(proObj.isRelateRefer == "Y"){
	        				var checkboxHtml="";
	        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
	        					for(var m=0;m<proObj.listRtuRefer.length;m++){
	        						var referObject= proObj.listRtuRefer[m];
	        						var flag=referObject.flag;
	        						if(flag==1 || flag=="1"){
	        							checkboxHtml+="<input type='checkbox' style='width:30px;' checked='checked' name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
	        						}else if(flag==2 || flag=="2"){
	        							checkboxHtml+="<input type='checkbox' style='width:30px;'  name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
	        						}
	        					}
	        				}
	        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
	        			}else{
	        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput' /></div></div>";
	        			}
	        		}else if(data.obj.length>1){
	        			for(var k=0;k<data.obj.length;k++){
		        			var proObj=data.obj[k];
		        			if(proObj.isRelateRefer == "Y"){
		        				var checkboxHtml="";
		        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
		        					for(var m=0;m<proObj.listRtuRefer.length;m++){
		        						var referObject= proObj.listRtuRefer[m];
		        						var flag=referObject.flag;
		        						if(flag==1 || flag=="1"){
		        							checkboxHtml+="<input type='checkbox' style='width:30px;' checked='checked' name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
		        						}else if(flag==2 || flag=="2"){
		        							checkboxHtml+="<input type='checkbox' style='width:30px;'  name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
		        						}
		        					}
		        				}
		        				if(k==data.obj.length-1){
		        					strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
		        				}else{
		        					strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
		        				}
		        			}else{
		        				if(k==data.obj.length-1){
		        					if(proObj.propKey=="slave_index" || proObj.propKey=="slave_sid" || proObj.propKey=="reg_id" || proObj.propKey=="reg_rel_address" || proObj.propKey=="reg_type"){
		        						strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='easyui-numberbox rightInput' style='height:32px;line-height:32px;' data-options='min:0,precision:0'/></div></div>";
		        					}else{
		        						strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput'/></div></div>";
		        					}
		        				}else{
		        					if(proObj.propKey=="slave_index" || proObj.propKey=="slave_sid" || proObj.propKey=="reg_id" || proObj.propKey=="reg_rel_address" || proObj.propKey=="reg_type"){
		        						strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='easyui-numberbox rightInput' style='height:32px;line-height:32px;' data-options='min:0,precision:0'/></div></div>";
		        					}else{
		        						strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput'/></div></div>";
		        					}
		        				}
		        			}
		        		}
	        		}
	        		strHTML+="</div></fieldset></div>";
	        		var treeObj = $.fn.zTree.getZTreeObj("editModoelTree");	
					//获取树所有节点
					var curnode=0;
					var arr=[];
					var nodes2=treeObj.getNodes();
					var nodes_array = treeObj.transformToArray (nodes2);
					//获取树选中的节点
					var nodes = treeObj.getCheckedNodes(true);
					var divArr=$("#editDiv2Page").children("div");
					var divName=[];
					if(divArr!=null && divArr.length>0){
						for(var l=0;l<divArr.length;l++){
							divName.push($(divArr[l]).attr("name"));
						}
						var checkArr=[];
						if(null!=divName && divName.length>0){
							for(var i=0;i<divName.length;i++){
								for(var j=0;j<nodes.length;j++){
									if(parseInt(nodes[j].id)==parseInt(divName[i])){
										checkArr.push(nodes[j]);
									}
								}
							}
							if(null!=nodes_array&& nodes_array.length>0){
								for(var k=0;k<nodes_array.length;k++){
									if(nodes_array[k].id==treeNode.id){
										curnode=k;
									}
								}
								if(null!=checkArr && checkArr.length>0){
									for(var n=0;n<checkArr.length;n++){
										for(var m=0;m<nodes_array.length;m++){
											if(checkArr[n].id==nodes_array[m].id){
												arr.push(m);
											}
										}
										
									}
								}
								var minindex=0;
								var maxindex=0;
								if(null!=arr && arr.length>0){
									minindex=arr[0];
									maxindex=arr[0];
									for(var j=1;j<arr.length;j++){
										if(arr[j]<=minindex){
											minindex=arr[j];
										}
										if(arr[j]>=maxindex){
											maxindex=arr[j];
										}
									}
								}
								var treeId="";
								if(curnode<minindex){
									treeId=nodes_array[minindex].id;
									$("div[name="+treeId+"]").before(strHTML);
								}else if(curnode>minindex && curnode<maxindex){
									arr.sort(function(a, b) {
									  return Math.abs(a-curnode)-Math.abs(b-curnode);
									})[0];
									var ins=arr[0];
									if(curnode<=ins){
										treeId=nodes_array[ins].id;
										$("div[name="+treeId+"]").before(strHTML);
									}else{
										treeId=nodes_array[ins].id;
										$("div[name="+treeId+"]").after(strHTML);
									}
								}else if(curnode>maxindex){
									treeId=nodes_array[maxindex].id;
									$("div[name="+treeId+"]").after(strHTML);
								}
							}
						}
					}else{
						$("#editDiv2Page").append(strHTML);
						return;
					}
	        	}
	        } 
		});
	}
	function lookTreeNextNodes(url,treeNode){
		//选中数据项
		$.ajax({
		 	url:url,
		 	data:{
				pid:treeNode.id,
				slaveId:treeNode.slaveId
	        }, 
			type: 'POST',
	        dataType : "json",  
	        success:function(data){
	        	if(data != null && data.obj != null && data.obj.length > 0){
	        		//var strHTML="<div name='"+treeNode.id+"'><div name='title'>"+treeNode.name+"</div><div name='content'>";
	        		var strHTML="<div name='"+treeNode.id+"'><fieldset><legend>"+treeNode.name+"</legend><div name='lookContent'  >";
	        		if(data.obj.length==1){
	        			var proObj=data.obj[0];
	        			if(proObj.isRelateRefer == "Y"){
	        				var checkboxHtml="";
	        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
	        					for(var m=0;m<proObj.listRtuRefer.length;m++){
	        						var referObject= proObj.listRtuRefer[m];
	        						var flag=referObject.flag;
	        						if(flag==1 || flag=="1"){
	        							checkboxHtml+="<input type='checkbox' style='width:30px;' checked='checked' name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
	        						}else if(flag==2 || flag=="2"){
	        							checkboxHtml+="<input type='checkbox' style='width:30px;'  name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
	        						}
	        					}
	        				}
	        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
	        			}else{
	        				strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput' /></div></div>";
	        			}
	        		}else if(data.obj.length>1){
	        			for(var k=0;k<data.obj.length;k++){
		        			var proObj=data.obj[k];
		        			if(proObj.isRelateRefer == "Y"){
		        				var checkboxHtml="";
		        				if(proObj.listRtuRefer !=null && proObj.listRtuRefer.length > 0){
		        					for(var m=0;m<proObj.listRtuRefer.length;m++){
		        						var referObject= proObj.listRtuRefer[m];
		        						var flag=referObject.flag;
		        						if(flag==1 || flag=="1"){
		        							checkboxHtml+="<input type='checkbox' style='width:30px;' checked='checked' onclick='return false;' name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
		        						}else if(flag==2 || flag=="2"){
		        							checkboxHtml+="<input type='checkbox' style='width:30px;' onclick='return false;'  name='"+proObj.propKey+"' value='"+referObject.referKey+"'/>"+referObject.referDesc+"";
		        						}
		        					}
		        				}
		        				if(k==data.obj.length-1){
		        					strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
		        				}else{
		        					strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'>"+checkboxHtml+"</div></div>";
		        				}
		        			}else{
		        				if(k==data.obj.length-1){
		        					strHTML+="<div class='rightContent'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' readOnly='readOnly' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput'/></div></div>";
		        				}else{
		        					strHTML+="<div class='rightContent1'><div class='rightDiv1'><span name='"+proObj.propKey+"' class='rightSpan'>"+proObj.propDesc+"</span></div><div class='rightDiv2'><input type='text' readOnly='readOnly' name='"+proObj.propKey+"' value='"+proObj.propDefault+"' class='rightInput'/></div></div>";
		        				}
		        			}
		        		}
	        		}
	        		strHTML+="</fieldset></div>";
					$("#lookDiv2Page").append(strHTML);
	        	}
	        } 
		});
	}
	/*确认添加采集器*/
	function makeSureAddSlave(flag){
		var zTree=null;
		if(flag==1 || flag=="1"){
			zTree = $.fn.zTree.getZTreeObj("modoelTree");
		}else if(flag==2 || flag=="2"){
			zTree = $.fn.zTree.getZTreeObj("editModoelTree");
		}
		var allCheckedNodes = zTree.getCheckedNodes(true);
		
		var porpertyObjArray=[];
		$.each(allCheckedNodes,function(index,node){
			var parentDiv= $("div[name="+node.id+"]");
			if(parentDiv != null){				
				var contentDiv=parentDiv.find("div[name=content]");
				if(contentDiv != null){
					$.each($(contentDiv).find("input"),function(index,inputObj){
						var porpertyObj=new Object();
						porpertyObj.treeLevel=node.level;
						porpertyObj.treeId=node.id;
						porpertyObj.pid=node.pid;
						porpertyObj.treeName=node.name;
						var inputName= $(inputObj).attr("name");
						var inputValue=$(inputObj).val();
						var textName=$($(contentDiv).find("span[name='"+inputName+"']")[0]).text();
						porpertyObj.proName=textName;
						porpertyObj.proKey=inputName;
						porpertyObj.proVal=inputValue;
						porpertyObj.proType=$(inputObj).attr("type");
						porpertyObj.checkType="false";
						//类型为文本的
						if($(inputObj).attr("type") == "text"){
							
						}
						
						//类型为复选框的
						if($(inputObj).attr("type") == "checkbox" ){
							if($(inputObj).attr('checked')=="checked"){
								porpertyObj.checkType="true";
							}
							
						}
						porpertyObjArray.push(porpertyObj);
					});
				}
			}
				
		});
		ajaxSaveSlave(JSON.stringify(porpertyObjArray));
	}
	
	function ajaxSaveSlave(saveStr){
		$.ajax({
			 	url:'${ctx}/rtuController/ajaxSaveSlave',
			 	data:{
					content:saveStr
		        }, 
				type: 'POST',
		        dataType : "json",  
		        success:function(data){
		        	if(data != null && data.obj != null){
		        		var tableHtmlStr="";
		        	   	tableHtmlStr+="<tr name='slavetr'  id='"+data.obj.id+"'>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveName+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveId+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;'>"+data.obj.slaveOrder+"</td>";
							tableHtmlStr+="<td class='slaveTd' style='text-align:center;border-right:1px solid #D0E3F2;'>";
        						tableHtmlStr+="<label><a onclick='editSlaveConfigFun("+data.obj.id+");' href='javascript:void(0);' style='width:120px;height:30px;'><image style='height:20px;width:20px;line-height:30px;vertical-align:middle;' src='${static}/js/jquery-easyui/1.4.4/themes/icons/update.gif'/><label style='width:60px;height:30px;line-height:30px;vertical-align:middle;margin-left:5px;'>复制</label></a></label>";
        						tableHtmlStr+="<label><a onclick='deleteSlaveFun("+data.obj.id+");' href='javascript:void(0);' style='width:120px;margin-left:10px;height:30px;'><image style='height:20px;width:20px;line-height:30px;vertical-align:middle;' src='${static}/js/jquery-easyui/1.4.4/themes/icons/btn-cancel.png'/><label style='width:60px;height:30px;line-height:30px;vertical-align:middle;margin-left:5px;'>删除</label></a></label>";
        						tableHtmlStr+="<input type='hidden' name='slaveId' value='"+data.obj.id+"'>";
        					tableHtmlStr+="</td>";
        				tableHtmlStr+="</tr>";
		        	   $("#slaveTab").append(tableHtmlStr);
		        	   if($addSlaveConfigDialog){
		        	   	 $addSlaveConfigDialog.dialog('close');
		        	   }
		        	   if($editSlaveConfigDialog){
		        	   	 $editSlaveConfigDialog.dialog('close');
		        	   }
		        	  
		        	}else{
		        		myMessage("添加失败");
		        	}
		        }
		     });
	}
	function deleteSlaveFun(slaveId){
		if(null==slaveId || ""==slaveId){
			myMessage("删除失败");
			return;
		}
		$.ajax({
		 	url:'${ctx}/rtuController/deleteSlaveInfo',
		 	data:{
				slaveId:slaveId
	        }, 
			type: 'POST',
	        dataType : "json",  
	        success:function(data){
	        	if(data != null && data.obj != null){
	        	  var arr=$("tr[name='slavetr']");
	        	  if(null!=arr && arr.length>0){
	        	  	for(var i=0;i<arr.length;i++){
	        	  		if($(arr[i]).attr("id")==data.obj){
	        	  			$(arr[i]).remove();
	        	  			break;
	        	  		}
	        	  	}
	        	  }
	        	}else{
	        		myMessage("删除失败");
	        	}
	        }
	     });
	}
	
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	
	//增加slave或者编辑的弹窗
	function addSlaveInfo(index){
		var row=$rtuInfoDatagrid.datagrid('getSelected');
		if(row == null){
			alert("请先选中一条模组数据");
			return;
		}

		 if ($slaveDialog == null){
			$slaveDialog=$("#slaveDialog").show().dialog({
					modal : true,
					title : '采集器配置信息',
					width:650,
					height:350,
					buttons : [ {
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$slaveDialog.dialog('close');
						}
					},{
						text : '保存',
						style:'text-align:center',
						handler : function() {
							saveOrUpdateSlaveInfo();
						}
					}],
					onClose:function(){
						//清空弹窗里面的数据信息
						//$("#slaveInfoForm input").val("");
						$("#slaveInfoForm").form('clear');
						$slaveInfoDatagrid.datagrid('reload');
					}
			}).dialog('close');
		 }
		
			if(index != null && index !=undefined){
				var slaveObj=$slaveInfoDatagrid.datagrid('getRows')[index];
				if(slaveObj == null){
					return;
				}
			
				//slaveObj
				$('#slaveInfoForm').form('load',{
					name:slaveObj.name,
					num:slaveObj.num,
					type:slaveObj.type,
					start:slaveObj.start,
					sid:slaveObj.sid,
					cmd:slaveObj.cmd,
					readsNum:slaveObj.readsNum,
					connModel:slaveObj.connModel,
					id:slaveObj.id,
					delayTime:slaveObj.rtu.delayTime,
					reserveColumn:slaveObj.rtu.reserveColumn
				});
		}else{
			$("#delayTimeT").textbox('setValue',row.delayTime);
			$("#reserveColumnT").textbox('setValue',row.reserveColumn);
		}
		$("#slaveInfoForm input[name=rtuId]").val(row.id);
		
		$slaveDialog.dialog('open');
	}
	//新增或更新采集信息
	function saveOrUpdateSlaveInfo(){
		$.ajax({
			url:'${ctx}/rtuController/saveRtuSlaveBaseInfo',
			 	data:$("#slaveInfoForm").serialize(), 
				type: 'POST',
		        dataType : "json",  
		        success:function(data){
		        	if(data!=null){
		        		myMessage(data.msg);
		        	}
		        	$slaveDialog.dialog('close');	
		        },error:function(){
		        	myMessage("系统在执行过程中出现了错误，请再次执行");
		        }
		});
	}

	function  deleteRtuSlave(id){
		$.messager.confirm('提示','确定删除该条记录？',function(r){   
			if (r){
				$.ajax({
				url:'${ctx}/rtuController/deleteSlaveBase',
				 	data:{
				 	id:id
				 	},
					type: 'POST',
			        dataType : "json",  
			        success:function(data){
			        	if(data!=null){
			        		myMessage(data.msg);
			        	}
			        	$slaveInfoDatagrid.datagrid('reload');
			        },error:function(){
			        	myMessage("系统在执行过程中出现了错误，请再次执行");
			        }
				});
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
						RTU名称：
						<input name="rtuName" class="easyui-textbox" style="height:26px;"/>
						RTU地址：
						<input name="rtuAdress" class="easyui-textbox" style="height:26px;"/>
					</td>

					<td>
						<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
						<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
					</td>
				</tr>
				<tr>
					<td>

					<a class="easyui-linkbutton" iconCls="icon-add" onclick="add()" href="javascript:void(0);">新增RTU</a>
					<a class="easyui-linkbutton" iconCls="icon-add"  onclick="addConfigFun();" href="javascript:void(0);">新增配置</a>
					<a class="easyui-linkbutton" iconCls="icon-add"  onclick="addRtuConfigFun();" href="javascript:void(0);">RTU初始配置</a>
					</td>
				</tr>

			</table>
		</div>
		<table id="rtuInfoDatagrid"></table>
	</div>
	
	<!--RTU弹窗-->
	<div id="RTUDialog" style="display: none;overflow-x:hidden;">
		<form id="RTUForm" method="post">
			
			<table class="basic basic_dialog">
				<tr>
					<th width="85">RTUid：</th>
					<td><input id="id" name="id"  class=" easyui-textbox  validatebox "   style="width:170px; height:26px;"  required="true"/></td>
				</tr>
				<tr>
					<th width="85">RTU名称：</th>
					<td><input id="rtuName" name="rtuName"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" required="true" /></td>
				</tr>
				<tr>
					<th>RTU地址：</th>
					<td><input id="rtuAdress" name="rtuAdress"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" required="true" /></td>
				</tr>
				<!--
				<tr>
					<th>备用字段：</th>
					<td><input id="reserveColumn" name="reserveColumn"  class="easyui-textbox validatebox "  style="width:170px; height:26px;" /></td>
				</tr>-->
			</table>
		</form>
	</div>
	
	<!--RTU配置弹窗-->
	<div id="configDialog" style="display: none;overflow-x:hidden;">
		<div id="configToolbar" class="search_table">
			<table>
				<tr>
					<td>
						创建时间：<input id="createTimeStart" name="createTimeStart" required="true" class="easyui-datetimebox combo-f datetimebox-f" editable="false" style="width:170px; height:26px;"/>
						至<input id="createTimeEnd" name="createTimeEnd" required="true" class="easyui-datetimebox combo-f datetimebox-f" editable="false" style="width:170px; height:26px;"/>
					</td>
					<td>
						<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchConfigFun();" href="javascript:void(0);">查 找</a>
						<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearConfigFun();" href="javascript:void(0);">清 空</a>
						<a class="easyui-linkbutton" iconCls="icon-add"  onclick="addConfigFun();" href="javascript:void(0);">新增配置</a>
						<!--<a class="easyui-linkbutton" iconCls="icon-add"  onclick="addSlaveConfigFun();" href="javascript:void(0);">新增采集器配置</a>-->

						
						
					</td>
				</tr>
			</table>
		</div>
		<table id="rtuConfigDatagrid"></table>
	</div>
	
	<!--新增配置窗口-->
	<div id="addConfigDialog" style="display: none;overflow-x:hidden;">
		<div id="cofigDivParent" style="width:100%;">
			<fieldset>
				<legend>基本信息</legend>
				<div id="cofigDiv1" style="width:100%;">
					<div class="rightContent1" style="display:none;"><div class="rightDiv1"><span class="rightSpan">心跳延时</span></div><div class="rightDiv2"><input class="rightInput" type="hidden" id="configId001" name="configId"/></div></div>
					<div class="rightContent1"><div class="rightDiv1"><span class="rightSpan">心跳延时</span></div><div class="rightDiv2"><input class="easyui-numberbox rightInput" style="height:32px;line-height:32px;" type="text" id="confHeartDelay001" data-options="min:0,precision:0" name="confHeartDelay"/></div></div>
					<div class="rightContent"><div class="rightDiv1"><span class="rightSpan">上传延时</span></div><div class="rightDiv2"><input class="easyui-numberbox rightInput" style="height:32px;line-height:32px;" data-options="min:0,precision:0" type="text" id="confPubDelay001"  name="confPubDelay"/></div></div>
				</div>
			</fieldset>
			<!--采集器列表-->
			<fieldset>
				<legend>采集器配置信息</legend>
				<div id="cofigDiv2" style="width:100%;">
					<span><a class="easyui-linkbutton" iconCls="icon-add"  onclick="addSlaveConfigFun();" href="javascript:void(0);">采集器配置</a></span>
					<!--<span><a class="easyui-linkbutton" iconCls="icon-edit"  onclick="editSlaveConfigFun(1489571574946);" href="javascript:void(0);">编辑采集器配置</a></span>-->
					<table id="slaveTab" class="basic basic_dialog">
						<tr>
							<th width:"20%" style="text-align:center;">采集器名称</th>
							<th style="text-align:center;">采集器sid</th>
							<th style="text-align:center;">采集器序号</th>
							<th style="text-align:center;">操作</th>
						</tr>
					</table>
				</div>
			</fieldset>
			<!--传输配置属性列表-->
			<fieldset>
				<legend>传输配置信息</legend>
				<div id="cofigDiv3" style="width:100%;">
					<span><a class="easyui-linkbutton" iconCls="icon-add"  onclick="addTranslateConfigFun();" href="javascript:void(0);">传输配置</a></span>
					<input type="hidden" name="translatePropVal"/>
					<div id="translateParentDiv">
					</div>
				</div>
			</fieldset>
		</div>
	</div>
	<div id="lookConfigDialog" style="display: none;overflow-x:hidden;">
		<div id="lookCofigDivParent" style="width:100%;">
			<fieldset>
				<legend>基本信息</legend>
				<div id="lookCofigDiv1" style="width:100%;">
					<div class="rightContent1"><div class="rightDiv1"><span class="rightSpan">心跳延时</span></div><div class="rightDiv2"><input class="rightInput" type="text" id="confHeartDelay002" name="confHeartDelay002"/></div></div>
					<div class="rightContent"><div class="rightDiv1"><span class="rightSpan">上传延时</span></div><div class="rightDiv2"><input class="rightInput" type="text" id="confPubDelay002" name="confPubDelay002"/></div></div>
				</div>
			</fieldset>
			<!--采集器列表-->
			<fieldset>
				<legend>采集器配置信息</legend>
				<div id="lookCofigDiv2" style="width:100%;">
					<table id="lookSlaveTab" class="basic basic_dialog">
						<tr>
							<th width:"20%" style="text-align:center;">采集器名称</th>
							<th style="text-align:center;">采集器sid</th>
							<th style="text-align:center;">采集器序号</th>
							<th style="text-align:center;">操作</th>
						</tr>
					</table>
				</div>
			</fieldset>
			<!--传输配置属性列表-->
			<fieldset>
				<legend>传输配置信息</legend>
				<div id="lookCofigDiv3"  style="width:100%;">
					<div id="lookTranslateParentDiv">
					</div>
				</div>
			</fieldset>
		</div>
	</div>
	<!--新增采集器配置窗口-->
	<div id="addSlaveConfigDialog" style="display: none;overflow-x:hidden;">
		<div id="div2" style="width:100%;">
			<div style="width:28%;float:left;">
				<ul id="modoelTree" class="ztree" ></ul>
			</div>
			<div id="div2Page" style="width:68%;float:left;">
			
			</div>
		</div>
	</div>
	<!--编辑采集器配置信息窗口-->
	<div id="editSlaveConfigDialog" style="display: none;overflow-x:hidden;">
		<div id="div2" style="width:100%;">
			<div style="width:28%;float:left;">
				<ul id="editModoelTree" class="ztree" ></ul>
			</div>
			<div id="editDiv2Page" style="width:68%;float:left;">
			
			</div>
		</div>
	</div>
	<!--查看采集器配置信息窗口-->
	<div id="lookSlaveConfigDialog" style="display: none;overflow-x:hidden;">
		<div id="lookdiv2" style="width:100%;">
			<div style="width:28%;float:left;">
				<ul id="lookEditModoelTree" class="ztree" ></ul>
			</div>
			<div id="lookDiv2Page" style="width:68%;float:left;">
			
			</div>
		</div>
	</div>
	<!--动态刷新dialog-->
	<div id="refreshDialog" style="display: none;overflow-x:hidden;vertical-align:middle;">
		<img src="${static}/images/refresh.gif" style='line-height:30px;display:block;float:left;margin-top:10px;'/><label style='line-height:30px;float:left;margin-top:10px;'>页面正在加载中，请稍后。。。</label>
	</div>
	<!--新增传输数据项窗口-->
	<div id="addTranslateConfigDialog" style="display: none;overflow-x:hidden;">
		<div id="div3" style="width:100%;">
			<div style="width:28%;float:left;">
				<ul id="translateTree" class="ztree" ></ul>
			</div>
			<div id="div3Page" style="width:68%;float:left;">
			</div>
		</div>
	</div>
	
		<!--Slave基础列表配置窗口-->
	<div id="addRtuConfigDialog" style="display: none;overflow-x:hidden;">
		<div id="slaveInfoToolbar" class="search_table">
			<table>
				<tr>
					<td>
					<a class="easyui-linkbutton" iconCls="icon-add"  onclick="addSlaveInfo();" href="javascript:void(0);">新增采集器配置</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="slaveInfoDatagrid"></table>
	</div>
	<div id="box"></div>
	<!-- slave新增和编辑弹窗 -->
	<div id="slaveDialog" style="display: none;overflow-x:hidden;">
		<form id="slaveInfoForm">
		<input name="rtuId" type="hidden"/>
		<input name="id" type="hidden"/>
	  <fieldset>
   			 <legend>公有属性</legend>
			<table  class="basic basic_dialog">
				<tr>
					<th style="width:80px;">延时：</th>
					<td><input type="text" id="delayTimeT" name="delayTime" class="easyui-textbox" style="height:26px;width:176px;"/></td>
					<th style="width:80px;">预留字段：</th>
					<td><input type="text" id="reserveColumnT" name="reserveColumn" class="easyui-textbox" style="height:26px;width:176px;"/></td>
				</tr>
			</table>
		 </fieldset>
		 <fieldset>
   			 <legend>采集器属性</legend> 	
			<table id="slaveInfoTab" class="basic basic_dialog">
				<tr>
					<th style="width:80px;">名称：</th>
					<td><input type="text" name="name" class="easyui-textbox" style="height:26px;width:176px;"/></td>
					<th style="width:80px;">序号：</th>
					<td><input type="text" name="num" class="easyui-textbox" style="height:26px;width:176px;"/></td>
				</tr>
				<tr>
					<th>类型：</th>
					<td><input type="text" name="type" class="easyui-textbox" style="height:26px;width:176px;"/></td>
					<th>开始地址：</th>
					<td><input type="text" name="start" class="easyui-textbox" style="height:26px;width:176px;"/></td>
				</tr>
				<tr>
					<th>sid：</th>
					<td><input type="text" name="sid" class="easyui-textbox" style="height:26px;width:176px;"/></td>
					<th>cmd：</th>
					<td><input type="text" name="cmd" class="easyui-textbox" style="height:26px;width:176px;"/></td>
				</tr>

				<tr>
					<th>读取数量：</th>
					<td><input type="text" name="readsNum" class="easyui-textbox" style="height:26px;width:176px;"/></td>
					<th>连接方式：</th>
					<td><input type="text" name="connModel" class="easyui-textbox" style="height:26px;width:176px;"/></td>

				</tr>

			</table>
		</fieldset>	
			</form>
		</div>
	
</body>
</html>