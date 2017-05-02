
	//数据格式转换
	function cdf(dataVal){
		if(dataVal=="" || dataVal==null || dataVal==undefined ||dataVal=="null" || dataVal=="undefined"){
			return "";
		}
		return dataVal;
	}


	//查看详情界面	
	function detail(index){
		var row = $datagrid.datagrid("getRows")[index];
		workOrderId=row.id;
	
		if($workOrderDetailDialog == null){
			$workOrderDetailDialog=$("#workOrderDetailDialog").show().dialog({
					modal : true,
					title : '工单详情',
					width:1000,
					height:450,
					buttons : [ {
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$workOrderDetailDialog.dialog('close');
						}
					}]	
			}).dialog('close');
		}
		
		
		//查询所有信息，并初始化值
			$.ajax({
				url : ctx+'/workOrder/queryWorkOrderDtail',
				data : {
					workOrderId:workOrderId
				},
				type:'POST',
				cache : false,
				success:function(data){
					if(data.success== true && data.obj != null){
						var workOrderObj=data.obj.workOrderObj;
						if(workOrderObj!= null && workOrderObj != undefined){
							$("#workOrderNumY").text(cdf(workOrderObj.workOrderNum));
							if(workOrderObj.orderState!= null && workOrderObj.orderState!= undefined){
								if(parseInt(workOrderObj.orderState)==0){
									$("#orderStateY").text("已下达");
								}
								if(parseInt(workOrderObj.orderState)==1){
									$("#orderStateY").text("已签收");
								}
								if(parseInt(workOrderObj.orderState)==2){
									$("#orderStateY").text("已回传");
								}
								if(parseInt(workOrderObj.orderState)==3){
									$("#orderStateY").text("待审核");
								}
								if(parseInt(workOrderObj.orderState)==4){
									$("#orderStateY").text("已完成");
								}
								if(parseInt(workOrderObj.orderState)==5){
									$("#orderStateY").text("待下达");
								}
							}else{
								$("#orderStateY").text("");
							}
							
							$("#timeLimitY").text(cdf(workOrderObj.timeLimit));
							$("#completeTimeY").text(cdf(workOrderObj.completeTime));
							$("#networkStandardY").text(cdf(workOrderObj.networkStandard));
							if(workOrderObj.issuedWay!= null && workOrderObj.issuedWay!= undefined){
								if(parseInt(workOrderObj.issuedWay)==0){
									$("#issuedWayY").text("系统");
								}
								if(parseInt(workOrderObj.issuedWay)==1){
									$("#issuedWayY").text("个人");
								}
							}else{
								$("#issuedWayY").text("");
							}	
							if(workOrderObj.explainState!= null && workOrderObj.explainState!= undefined){
								if(parseInt(workOrderObj.explainState)==1){
									$("#explainStateY").text("通过");
								}
								if(parseInt(workOrderObj.explainState)==2){
									$("#explainStateY").text("不通过");
								}
							}else{
								$("#explainStateY").text("");
							}
							
							$("#explainTimeY").text(cdf(workOrderObj.explainTime));
							$("#createTimeY").text(cdf(workOrderObj.createTime));
							if(workOrderObj.org!=null && workOrderObj.org != undefined){
								$("#orgNameY").text(cdf(workOrderObj.org.orgName));
							}
							
							if(workOrderObj.explainUser!= null && workOrderObj.explainUser.nickName!= null && workOrderObj.explainUser.nickName!= undefined){
								$("#explainUserY").text(workOrderObj.explainUser.nickName);
							}else{
								$("#explainUserY").text("");
							}
							$("#issuedRemarkY").text(cdf(workOrderObj.issuedRemark));
							$("#explainRemarkY").text(cdf(workOrderObj.explainRemark));
							if(workOrderObj.user!=null && workOrderObj.user!=undefined){
								$("#userNameY").text(cdf(workOrderObj.user.nickName));
							}
						
							
							if(workOrderObj.terminal!=null && workOrderObj.terminal != undefined){
								$("#terminalModelY").text(cdf(workOrderObj.terminal.model));
								$("#terminalCodeY").text(cdf(workOrderObj.terminal.code));
							}else{
								$("#terminalModelY").text("");
								$("#terminalCodeY").text("");
							}
						//采集参数项目	
						$("#collectParaY").text(cdf(data.obj.collectPara));
							
							//调整采集信息
							var collectMap=data.obj.collectMap;
							
							if(data.obj.collectMapExist){
								var strHtml="";
								var ti=1;
								for(var key in collectMap){
									if(ti%4 ==1){
										strHtml+="<tr><th>"+key+"</th><td>"+collectMap[key]+"</td>";
									}else if(ti%4 ==0){
										strHtml+="<th>"+key+"</th><td>"+collectMap[key]+"</td></tr>";
									}else{
										strHtml+="<th>"+key+"</th><td>"+collectMap[key]+"</td>";
									}
									 ti++;
								}
								
								if(ti%4==2){
									strHtml+="<th></th><td></td><th></th><td></td><th></th><td></td></tr>"
								}else if(ti%4==3){
									strHtml+="<th></th><td></td><th></th><td></td></tr>"
								}else if(ti%4==0){
									strHtml+="<th></th><td></td></tr>"
								}
								
								$("#collectInfoTab").empty().append(strHtml);
								$("#cjxx").show();
							}else{
								$("#cjxx").hide();
							}
							//弹出
							$workOrderDetailDialog.dialog('open');
						}
					}
				}
			});	
			
	}	
			//创建工单按钮的点击事件	
			function createOrder(){
				$("#dialog").show().dialog({
					modal : true,
					title:"工单创建",
					width:900,
					height:450,
					buttons:[{
						text:"创建并下发",
						handler:function(){
							if($("#workOrderForm").form('validate')){
								var reUrl=ctx+"/workOrder/createOrder";
								$.post(
										reUrl,
									$("#workOrderForm").form().serialize(),
									function(r){
										if(r.success){
											myMessage(r.msg,function(){
												//clearWorkOrderDialog();
												$("#dialog").dialog("close");
												$datagrid.datagrid('load',{});
												
											});
										}
									}
								);
							}
						}
					},{
						text:"置空",
						handler:function(){
							clearWorkOrderDialog();
						}
					},{
						text:"取消",
						handler:function(){
							$("#dialog").dialog("close");
							
						}
					}],
					onClose:function(){
						clearWorkOrderDialog();
					}
				});

				/*	$orgCombotree=$("#orgName").combotree({
					    url:ctx+'/workOrder/findOrgTreeByLoginUser',
					    required: true    
					});
				*/	
					$orgCombotree=$("#orgName").combobox({    
					    url:ctx+'/terminalInfo/findOrgByCurrentUser',    
					    valueField:'id',    
					    textField:'orgName',
					    onChange:function(newValue, oldValue){
					    	if(newValue != oldValue){
								$("#terminalCode").textbox("setValue",'');
								$("#terminalId").val('');
								$("#terminalUser").textbox("setValue",'')
					    	}
					    }
					   
					});				
		}

		//选择小区
		function selCommunity(){
				var wlType=$("#networkType").combobox("getValue");					
			if($communityDatagrid != null){
				$communityDatagrid=null;
			}
				$communityDatagrid=$("#communityDatagrid").datagrid({
					url:ctx+'/workOrder/communityGrid?networkStandard='+wlType,
					fitColumns:true,
					fit:true,
					toolbar:'#ctoolbar',
					pagination:true,
					singleSelect:true, 
					frozenColumns : [[{
						title : '',
						field : 'id',
						width : 50,
						checkbox:true
						}]],
					columns:[[    
					        {
					        field:'lac',
					        title:'LAC',
					        width:100
					        },{
					        field:'ci',
					        title:'CI',
					        width:100
					        },{
					        field:'cname',
					        title:'小区名称',
					        width:100,
					        align:'right'}    
					    ]] 
					});
			
			if($communityDialog == null ){
				$communityDialog=$('#communityDialog').show().dialog({
					modal : true,
					title : '小区选择',
					width:800,
					height:400,
					buttons : [ {
						text : '确定',
						style:'text-align:center',
						handler : function() {
							if($communityDatagrid!=null){
								var row=$communityDatagrid.datagrid("getSelected");
								if(row != null){
									$("#cname").textbox("setValue",row.cname);
									$("#wparameterId").val(row.wparameterId);
									$('#communityDialog').dialog("close");
								}else{
									$.messager.alert('提示信息','请请勾选数据'); 
								}
							}
						}
					},{
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$('#communityDialog').dialog("close"); 
						}
					}]
				});
			
			}else{
				$communityDialog.dialog('open');	
			}

		}
	
	//选择终端设备
		function selTerminal(){
				var orgId=$orgCombotree.combobox('getValue');
				$("#orgId").val(orgId);
				if(orgId==undefined || orgId==null || orgId==""){
					$.messager.alert('提示信息','请先选择受理机构'); 
					return ;
				}			
				if($terminalDatagrid != null){
					$terminalDatagrid=null;
				}
				var turl=ctx+"/terminalInfo/datagrid?orgId="+orgId+"&terminalState=3"
				$terminalDatagrid=$("#terminalDatagrid").datagrid({
					url:turl,
					fitColumns:true,
					fit:true,
					toolbar:'#ttoolbar',
					pagination:true,
					singleSelect:true, 
					frozenColumns : [[{
						title : '',
						field : 'id',
						width : 50,
						checkbox:true
						}]],
					columns:[[    
					        {
					        field:'code',
					        title:'终端编号',
					        width:100
					        },{
					        field:'model',
					        title:'终端型号',
					        width:100
					        },{
					        field:'terminalState',
					        title:'终端状态',
					        width:100,
					        formatter:function(val,rec){ 
						        	switch(parseInt(val)){
						        		case 1:
						        		return "停用";
						        		case 2:
						        		return "未分配";
						        		case 3:
						        		return "已分配";
						        		case 4:
						        		return "未归还";
						        		default:
		                              	return "";
						        	}
								}
							},{
							 field:'userName',
					        title:'使用人名称',
					        width:100,
				        	 formatter:function(val,rec){ 
									return rec.terminalUser.name;
								}
							},{
							 field:'userPhone',
					        title:'使用人电话',
					        width:100,
				        	formatter:function(val,rec){ 
									return rec.terminalUser.phone;
								}
							}  
					    ]] 
					});
			
			if($terminalDialog == null ){
				$terminalDialog=$('#terminalDialog').show().dialog({
					modal : true,
					title : '终端选择',
					width:800,
					height:400,
					buttons : [ {
						text : '确定',
						style:'text-align:center',
						handler : function() {
							if($terminalDatagrid!=null){
								var row=$terminalDatagrid.datagrid("getSelected");
								if(row != null){
									$("#terminalCode").textbox("setValue",row.code);
									$("#terminalId").val(row.id);
									$("#terminalUser").textbox("setValue",row.terminalUser.name)
									$('#terminalDialog').dialog("close");
								}else{
									$.messager.alert('提示信息','请请勾选数据'); 
								}
							}
						}
					},{
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$('#terminalDialog').dialog("close"); 
						}
					}]
				});
			
			}else{
				$terminalDialog.dialog('open');	
			}
		}
	

		//删除工单
		function remove(index){
				var row = $datagrid.datagrid("getRows")[index];
				//workOrderId=row.id;
				
				$.ajax({
					url:ctx+"/workOrder/deleteWorkOrder",
					data:{
						workOrderId:row.id
					},
					cache : false,
					type:"post",
					success:function(data){
						if(data!=null && data.msg!= null){
							myMessage(data.msg);
							$datagrid.datagrid('reload');
						}else{
							myMessage("浏览器不支持，请更换浏览器再执行操作！");
						}
					}
				
				});
}	
	
		//下发工单自定义列事件
		function selCollectPara(){	
			if($orderTree == null){
				$orderTree=$('#orderTree').tree({    
				    url:ctx+'/workOrder/queryColletInfoWords',
				    queryParams:{},
					checkbox:true
				}); 	
			}
			if($orderDefDialog == null){
				$orderDefDialog=$("#orderDefDialog").show().dialog({
					modal : true,
					title:"自定义采集项",
					width:900,
					height:450,
					buttons:[{
						text:"确定",
						handler:function(){
							var hidStr="";//传递到后台的值
							var htmlStr="";//显示在界面的值
							var rows = $orderTree.tree("getChecked");
							if(rows != null){
								hidStr=JSON.stringify(rows);
								$.each(rows,function(i,obj){
									if(obj.text != ""){
										htmlStr+=obj.text+",";
									}
								});
								if(htmlStr.length>0){
									htmlStr=htmlStr.substring(0,htmlStr.length-1);
								}
							}
							//把需要传递的值隐藏在界面上
							$("#paraObjsText").val(hidStr);
							$("#showParaDiv").append(htmlStr);
							
							$orderDefDialog.dialog('close');
					    	
						}
					},{
						text:"取消",
						handler:function(){
							$orderDefDialog.dialog('close');
						}
					}]
				});
			}else{
				$orderDefDialog.dialog('open');
			}
		}


		//查询小区datagrid
		 function searchComFun(){
		 
		 	$communityDatagrid.datagrid('load',{
		 	cname:$("input[name='communityName']").val(),
		 	lac:$("input[name='lac']").val(),
		 	ci:$("input[name='ci']").val()
		 	});
		 }
		 
		 //清空查询小区datagrid查询条件
		  function clearComFun(){
		  		
		  		$("#communityName").textbox('setValue','');
		  		$("#ci").textbox('setValue','');
		  		$("#lac").textbox('setValue','');

				$communityDatagrid.datagrid('load',{});
		 }

		

		//查询终端设备datagrid
		 function searchTerFun(){
		  	$terminalDatagrid.datagrid('load',{
		 		code:$("input[name='code']").val()
		 	});
		 }
		 
		 //清空查询终端设备datagrid查询条件
		  function clearTerFun(){
		 		$("#code").textbox('setValue','')
				$terminalDatagrid.datagrid('load',{});
		 }

			//清空创建工单上面的所有值
			function clearWorkOrderDialog(){
				$("#cname").textbox('setValue','');
				$("#wparameterId").val('');
				$("#orgName").textbox('setValue','');
				$("#terminalCode").textbox('setValue','');
				$("#terminalUser").textbox('setValue','');
				$("#terminalId").val('');
				$("#timeLimit").textbox('setValue','');
				$("#remark").val('');
				$("#orgId").val('');
				$("#paraObjsText").val('');
				$("#showParaDiv").empty();
				$('#networkType').combobox('setValue','2g');
				$("#networkType").combobox('readonly',false);
		 		$("#commmunityBtn").show();
			}
			//工单条件查询
			function searchFun(){
				
				$datagrid.datagrid('load',{
			 		orderState:$("[name=orderState]").val(),
			 		explainState:$("[name=explainState]").val(),
			 		networkStandard:$("[name=networkStandard]").val(),
			 		issuedWay:$("[name=issuedWay]").val(),
			 		workOrderNum:$("[name=workOrderNum]").val(),
			 		terminalCode:$("[name=terminalCodeVal]").val(),
			 		terminalTerminalUserName:$("[name=acceptUser]").val(),
			 		userNickName:$("[name=creatUser]").val()
			 	});
			
			}
			//清空工单查询条件
			function clearFun(){
				$("#workOrderNum").textbox('setValue','')
				$("#terminalCodeVal").textbox('setValue','')
				$("#acceptUser").textbox('setValue','')
				$("#creatUser").textbox('setValue','')
				
				$("#orderState").combobox('setValue','');
				$("#explainState").combobox('setValue','');
				$("#networkStandard").combobox('setValue','');
				$("#issuedWay").combobox('setValue','');
				$datagrid.datagrid('load',{});
			}
			
			//审核事件
			function auditEvent(index){
				var row = $datagrid.datagrid("getRows")[index];
				workOrderId=row.id;
					if($auditDialog == null){
						$auditDialog=$("#auditDialog").show().dialog({
							modal : true,
							title:"审核工单",
							width:960,
							height:480,
							buttons:[{
								text:"关    闭",
								handler:function(){
									$auditDialog.dialog("close");
									//clearWorkOrderDialog();
									//clearAuditData();
									//$datagrid.datagrid('reload');
								}
							}],
							onClose:function(){
								//clearWorkOrderDialog();
								clearAuditData();
								$datagrid.datagrid('reload');
							}
						});
					}	
					loadAuditData(row.id);

			}

			//审核界面加载数据
			function loadAuditData(workOrderIdT){
						$.ajax({
								url : ctx+'/collectInfo/queryCollectInfoByWorkOrderId',
								data : {
									workOrderId:workOrderIdT
								},
								cache : false,
								success : function(data) {
									if(data.success && data.obj != null ){
										if(data.obj.standardInfo != null && data.obj.standardInfo != "null" && data.obj.standardInfo != undefined){
											var stand=data.obj.standardInfo;
											//临时赋值以防需要创建工单
											$("#commmunityBtn").hide();
											//$("#networkType").val("");//这是下拉选择框
											$("#cname").textbox('setValue',stand.communityName);
											$("#wparameterId").val(stand.id);
											
											$("#antennaHangHigh").text(stand.antennaHangHigh);
											$("#electronicUnderAngle").text(stand.electronicUnderAngle);
											$("#orientationAngle").text(stand.orientationAngle);
											$("#spectrum").text(stand.spectrum);
											$("#altitude").text(stand.altitude);
											$("#longitude").text(stand.longitude);
											$("#latitude").text(stand.latitude);
										}
										if(data.obj.collectInfo !=null && data.obj.collectInfo != undefined){
											var collectInfo=data.obj.collectInfo;
											
											$('#networkType').combobox('setValue','3g');
											$("#networkType").combobox('readonly',true);
											
											if(data.obj.collectInfo.collectCompare!=null && data.obj.collectInfo.collectCompare != undefined){
											
												var compareData=data.obj.collectInfo.collectCompare;
											
												$("#antennaHangHighC").html(showCompareResult(collectInfo.antennaHangHigh,compareData.antennaHangHigh,compareData.isExceptionOrientationAngle));
												$("#electronicUnderAngleC").html(showCompareResult(collectInfo.electronicUnderAngle,compareData.electronicUnderAngle,compareData.isExceptionUnderAngle));
												$("#orientationAngleC").html(showCompareResult(collectInfo.orientationAngle,compareData.orientationAngle,compareData.isExceptionOrientationAngle));
												$("#spectrumC").html(showCompareResult(collectInfo.spectrum,compareData.spectrum,compareData.isExceptionLatitude));
												$("#altitudeC").html(showCompareResult(collectInfo.altitude,compareData.altitude,compareData.isExceptionAltitude));
												$("#longitudeC").html(showCompareResult(collectInfo.longitude,compareData.longitude,compareData.isExceptionLongitude));
												$("#latitudeC").html(showCompareResult(collectInfo.latitude,compareData.latitude,compareData.isExceptionLatitude));
												
											}else{
												$("#antennaHangHighC").html(showCompareResult(collectInfo.antennaHangHigh,"",""));
												$("#electronicUnderAngleC").html(showCompareResult(collectInfo.electronicUnderAngle,"",""));
												$("#orientationAngleC").html(showCompareResult(collectInfo.orientationAngle,"",""));
												$("#spectrumC").html(showCompareResult(collectInfo.spectrum,"",""));
												$("#altitudeC").html(showCompareResult(collectInfo.altitude,"",""));
												$("#longitudeC").html(showCompareResult(collectInfo.longitude,"",""));
												$("#latitudeC").html(showCompareResult(collectInfo.latitude,"",""));
											}
										}
										
										//数据加载完成后，弹出界面
										$auditDialog.dialog('open');
									}
								}
							});	
						
					}
			
			//清空审核界面加载的所有数据
			function clearAuditData(){
				$("#remarks").val('');
				aduFlag=true;
				$("#atoolbar span").empty();
			}
			
			
			//采集信息展示
			function showCompareResult(collectdate,comparedata,colorFlag){
				var reStr="";
				if(collectdate==null|| collectdate==""){
					reStr="未采集";
				}else{
					if(comparedata==null || comparedata == ""){
						reStr=collectdate;
					}else{
						if(parseFloat(comparedata)>0){
							if(parseInt(colorFlag) == 1){
								reStr=collectdate+"<font color='red' >↑</font>"+comparedata;
							}else if(parseInt(colorFlag) == 0){
								reStr=collectdate+"<font color='green' >↑</font>"+comparedata;
							}
						}else if(parseFloat(comparedata)<0){
							if(parseInt(colorFlag) == 1){
								reStr=collectdate+"<font color='red' >↓</font>"+comparedata;
							}else if(parseInt(colorFlag) == 0){
								reStr=collectdate+"<font color='green' >↓</font>"+comparedata;
							}
						}else if(parseFloat(comparedata)==0){
							if(parseInt(colorFlag) == 1){
								reStr=collectdate+"<font color='red' >=</font>"+comparedata;
							}else if(parseInt(colorFlag) == 0){
								reStr=collectdate+"<font color='green' >=</font>"+comparedata;
							}
						}
					}
				}
				return reStr;
			}
			
			
			//审核1为审核通过2为审核不通过
			function passSuccess(numFlag){
			var contentTxt="";
				//验证当前这条数据是否已经审核过了
				if(!aduFlag){
					$.messager.alert("提示信息","该数据已经审核过了");
					return;
				}
				$.messager.confirm('确认','是否需要更新数据到工参信息列表？',function(p){    
				    if (p){ 
						$workOrderParaTree=$('#workOrderParaTree').tree({    
						    url:ctx+'/workOrder/findWorkOrderDefCols',
						    queryParams:{
								appNode:appNode,
								workOrderId:workOrderId
							},
							checkbox:true
						});  

						$userDefDialog = $('#userDefDialog').show().dialog({
									modal : true,
									title : '自定义列同步数据弹窗',
									width:600,
									height:300,
									buttons : [ {
										text : '确定',
										style:'text-align:center',
										handler : function() {
											var rows = $("#workOrderParaTree").tree("getChecked");								    var rows = $("#workOrderParaTree").tree("getChecked");
											contentTxt=JSON.stringify(rows);
										    $.post(
												ctx+'/workOrder/saveUpdateDefCols',
												{workOrderId:workOrderId,
												explainRemark:$("#remarks").val(),
												content:contentTxt
												},function(r){
													if(r.success){
														aduFlag=false;
														if(parseInt(numFlag)==1){
														//通过
															myMessage('审核完成',function(){
																$userDefDialog.dialog('close');
															});
														}else{
														//不通过
															$.messager.confirm('确认','审核已完成，是否需要对本小区再次下发工单？',function(tt){ 
																if(tt){
																	$userDefDialog.dialog('close');					
																	createOrder();
																}else{
																	$userDefDialog.dialog('close');
																}
															});
														}
													}
												});
										}
									}, {
										text : '关闭',
										style:'text-align:center',
										handler : function() {
											$userDefDialog.dialog('close');
										}
									}]
								});

				        
				    }else{
				    
					    $.post(
							ctx+'/workOrder/saveUpdateDefCols',
							{workOrderId:workOrderId,
							explainRemark:$("#remarks").val(),
							content:contentTxt
							},function(r){
								if(r.success){
									aduFlag=false;
									if(parseInt(numFlag)==1){
									//通过
										myMessage('审核完成',function(){
											$userDefDialog.dialog('close');
										});
									}else{
									//不通过
										$.messager.confirm('确认','审核已完成，是否需要对本小区再次下发工单？',function(tt){ 
											if(tt){					
												createOrder();
											}else{
												$userDefDialog.dialog('close');
											}
										});
									}
								}
							});
				    }    
				});

			}
			
			//查看图片
			function showImage(index){
				var $showImageDialog = $("#showImageDialog").show().dialog({
					modal : true,
					title : '图片详情',
					width:500,
					height:450,
					buttons : [ {
						text : '关闭',
						style:'text-align:center',
						handler : function() {
							$showImageDialog.dialog('close');
						}
					}],
					onClose:function(){
						$("#pic").empty();
					}
			}).dialog('close');
	
				
				
				
				
		    	if( $("table#pic tr").length!=0 ){
		    		$("#pic").empty();
		    	}
		    	$.ajax({
						url : ctx+'/collectInfo/findImageByType',
						data : {
						type: index,
						workOrderId:workOrderId
						},
						cache : false,
						success:function(data){
							if(data.success){
								var ima;
								var list=data.obj;
								for(var i=0;i<list.length;i++){
										ima=list[i];
									$("#pic").append("<tr>"+
													 "<td colspan='2'><image style='width:100%;' src='"+ctx+"/uploadImages/"+ima.url+"'/><td>"+
													 "</tr>"+
													 "<tr>"+
													 "<th>上传时间:</th>"+
													 "<td><input value='"+ ima.uploadTime+"' class='easyui-textbox' style='width:175px; height:26px;'  disabled='true' /></td>"+
													 "</tr>");
								}
								$('#showImageDialog').panel({title:"图片类型："+ imageTypeChange(ima.picType)});

							        $showImageDialog.dialog("open");

							}else{
								$.messager.alert("提示","没有图片信息！！");
							}
						}
				});
			}
			
			
			//图片类型
			function imageTypeChange(type){

				 if(parseInt(type) == 0 ){
				 
						return "基站全景";
					}else if(parseInt(type) == 1){
						return "基站入口";
					}else if(parseInt(type) == 2){
						return "屋顶全貌";
					}else if(parseInt(type) == 3){
						return "异常场景";
					}else if(parseInt(type) == 4){
						return "测试基站-HLHA";
					}else if(parseInt(type) == 5){
						return "测试基站-HLHB";
					}else if(parseInt(type) == 6){
						return "测试基站-HLHC";
					}else{
						return "异常图片类型";
					}
				
			}
