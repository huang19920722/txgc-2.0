<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>

<#assign requestURI = springMacroRequestContext.getRequestUri() />



<script type="text/javascript" charset="UTF-8">

	var $userDefDialog;//同步数据弹窗界面
	var $orderDefDialog;//下发工单弹窗界面
	var workOrderId=${collecteInfo.workOrder.id};//工单id
	var appNode;//节点名称
	var $decTree;//同步数据tree
	var $orderTree;//下发工单数据tree
	var aduFlag=true;//审核标志
	//跳转回上采集信息界面
	function toCollect(){
		window.location.href="${ctx}/collectInfo/toCollectInfo";
	}
	//下一条审核
	function nextAudit(){
	
		$.ajax({
			url:"${ctx}/collectInfo/queryAuditCount",
			data:{},
			dataType:"Json",
			type:"POST",
			success:function(data){
				if(data!= null && data.obj!= null && data.obj>0 && data.success){
					window.location.href="${ctx}/collectInfo/toPersonAudit"; 
				}else{
					$.messager.alert("提示信息","您能审核的数据为0条");
				}
			}
		});
	}
	
	//通过
	function passSuccess(){
		//验证当前这条数据是否已经审核过了
		if(!aduFlag){
			$.messager.alert("提示信息","该数据已经审核过了");
			return;
		}
		$.messager.confirm('确认','是否需要更新数据到工参信息列表？',function(r){    
		    if (r){    
		       if($userDefDialog == null || $userDefDialog== undefined ){
		       		$userDefDialog = $('#userDefDialog').show().dialog({
							modal : true,
							title : '自定义列同步数据弹窗',
							width:600,
							height:300,
							buttons : [ {
								text : '确定',
								style:'text-align:center',
								handler : function() {
									$decTree.tree();
									$.post(
										'${ctx}/workOrder/saveOrderDefCols',
										{workOrderId:workOrderId,
										explainRemark:$("#remarks").val(),
										content:function(){
										    var rows = $("#dcTree").tree("getChecked");
									    	return JSON.stringify(rows);
									  	}},function(r){
											if(r.success){
												aduFlag=false;
												myMessage('保存成功',function(){
													$userDefDialog.dialog('close');
													nextAudit();
												});
											}
										}
									);
								}
							}, {
								text : '关闭',
								style:'text-align:center',
								handler : function() {
									$userDefDialog.dialog('close');
								}
							}],
							onBeforeOpen:function(){
								$decTree=$('#dcTree').tree({    
									    url:'${ctx}/workOrder/findWorkOrderDefCols',
									    queryParams:{
											appNode:appNode,
											workOrderId:workOrderId
										},
										checkbox:true
									});  
								}
						});
		       	}else{
		       		$userDefDialog.dialog('open');
		       	}
		        
		    }    
		});

	}
	
	//审核不通过
	function passFail(){
		//验证当前这条数据是否已经审核过了
		if(!aduFlag){
			$.messager.alert("提示信息","该数据已经审核过了");
			return;
		}
		$.messager.confirm('确认','是否需要更新部分数据到工参信息列表？',function(r){    
		    if (r){    
		       if($userDefDialog == null || $userDefDialog== undefined ){
		       		$userDefDialog = $('#userDefDialog').show().dialog({
							modal : true,
							title : '自定义列同步数据弹窗',
							width:600,
							height:300,
							buttons : [ {
								text : '确定',
								style:'text-align:center',
								handler : function() {
									$decTree.tree()
									$.post(
										'${ctx}/workOrder/saveUpdateDefCols',
										{workOrderId:workOrderId,
										explainRemark:$("#remarks").val(),
										content:function(){
										    var rows = $("#dcTree").tree("getChecked");
									    	return JSON.stringify(rows);
									  	}},function(r){
											if(r.success){
												aduFlag=false;
												//myMessage('保存成功',function(){
												//	$userDefDialog.dialog('close');
												//	nextAudit();
												//	提示用户是否需要再次下发工单
												//});
												
												
												
											}
										}
									);
								}
							}, {
								text : '关闭',
								style:'text-align:center',
								handler : function() {
									$userDefDialog.dialog('close');
								}
							}],
							onBeforeOpen:function(){
								$decTree=$('#dcTree').tree({    
									    url:'${ctx}/workOrder/findWorkOrderDefCols',
									    queryParams:{
											appNode:appNode,
											workOrderId:workOrderId
										},
										checkbox:true
									});  
								}
						});
		       	}else{
		       		$userDefDialog.dialog('open');
		       	}
		        
		    }else{//第一层弹窗（是否同步数据）[取消事件]
		    
		    	$.messager.confirm('确认','是否需要对本小区重新下发工单？',function(u){
		    		if(u){
		    			
		    		}
		    	
		    	});   
		    
		    }  
		});
		
	}
	
	//下发工单
	function issuedWorkOrder(){
		if($orderDefDialog == null || $orderDefDialog== undefined ){
		       		$orderDefDialog = $('#orderDefDialog').show().dialog({
							modal : true,
							title : '自定义列工单下发弹窗',
							width:600,
							height:300,
							buttons : [ {
								text : '确定',
								style:'text-align:center',
								handler : function() {
									$decTree.tree();
									$.post(
										'${ctx}/workOrder/saveUpdateDefCols',
										{workOrderId:workOrderId,
										explainRemark:$("#remarks").val(),
										content:function(){
										    var rows = $("#orderTree").tree("getChecked");
									    	return JSON.stringify(rows);
									  	}},function(r){
											if(r.success){
												aduFlag=false;
												myMessage('保存成功',function(){
													$userDefDialog.dialog('close');
													nextAudit();
												});
											}
										}
									);
								}
							}, {
								text : '关闭',
								style:'text-align:center',
								handler : function() {
									$userDefDialog.dialog('close');
								}
							}],
							onBeforeOpen:function(){
								$orderTree=$('#orderTree').tree({    
									    url:'${ctx}/workOrder/queryColletInfoWords',
									    queryParams:{},
										checkbox:true
									});  
								}
						});
		       	}else{
		       		$orderDefDialog.dialog('open');
		       	}
	}
	
	//查看图片
	function showImage(index){
		var $showImageDialog = $("#showImageDialog");
    try{
        $showImageDialog.dialog("open");
    }catch(ex){
        $showImageDialog.show().dialog({
            closed : false
        });
    }	
    	if( $("table#pic tr").length!=0 ){
    		$("#pic").empty();
    	}
    	$.ajax({
				url : '${ctx}/collectInfo/findImageByType',
				data : {
				type: index
				},
				cache : false,
				success:function(data){
					if(data.success){
						var ima
						var list=data.obj;
						for(var i=0;i<list.length;i++){
								ima=list[i];
							$("#pic").append("<tr>"+
											 "<td><image style='width:100px;height:100px;' src='"+ima.url+"'/><td>"+
											 "</tr>"+
											 "<tr>"+
											 "<th>上传时间:</th>"+
											 "<td><input value='"+ ima.uploadTime+"' class='easyui-textbox' style='width:175px; height:26px;'  editable='false' /></td>"+
											 "</tr>");
											
						}
						$('#showImageDialog').panel({title:"图片类型："+ ima.picType});
					}else{
					$.messager.alert("提示","没有图片信息！！");
					}
				}
		});
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">

		<div id="toolbar" class="search_table">
			<table style="width:100%;">
			<!--标准工参模块-->
				<tr>
					<th rowspan="3"  colspan="2"> 标 准 <br/> 工 参 <br/> 信 息 </th>
					<td>天线挂高:${standardInfo.antennaHangHigh!''}
					</td>
					<td>电子下倾角:${standardInfo.electronicUnderAngle!''}</td>
					<td>方位角:${standardInfo.orientationAngle!''}</td>
					<td>频段:${standardInfo.spectrum!''}</td>
					<td>海拔高度:${standardInfo.altitude!''}</td>
					<td>经度:${standardInfo.longitude!''}</td>
					<td>纬度:${standardInfo.latitude!''}</td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>

				<!--采集数据模块-->
				<tr>
					<th rowspan="3"  colspan="2"> 采 集 <br/> 工 参 <br/> 信 息</th>
					<td>天线挂高:${collecteInfo.antennaHangHigh!''}
						<#if collecteInfo.collectCompare?exists>
							<#if (collecteInfo.collectCompare.antennaHangHigh?number > 0) >
								↑
							<#elseif (collecteInfo.collectCompare.antennaHangHigh?number > 0) >
								↓
							<#else>
								|
							</#if>
							${collecteInfo.collectCompare.antennaHangHigh!''}
						</#if>
					</td>
					<td>电子下倾角:${collecteInfo.electronicUnderAngle!''}
						<#if collecteInfo.collectCompare?exists>
							<#if (collecteInfo.collectCompare.electronicUnderAngle?number > 0) >
								↑
							<#elseif (collecteInfo.collectCompare.electronicUnderAngle?number > 0) >
								↓
							<#else>
								|
							</#if>
							${collecteInfo.collectCompare.electronicUnderAngle!''}
						</#if>
					</td>
					<td>方位角:${collecteInfo.orientationAngle!''}
						<#if collecteInfo.collectCompare?exists>
							<#if (collecteInfo.collectCompare.orientationAngle?number > 0) >
								↑
							<#elseif (collecteInfo.collectCompare.orientationAngle?number > 0) >
								↓
							<#else>
								|
							</#if>
							${collecteInfo.collectCompare.orientationAngle!''}
						</#if>
					</td>
					<td>频段:${collecteInfo.spectrum!''}
						<#if collecteInfo.collectCompare?exists>
							<#if (collecteInfo.collectCompare.spectrum?number > 0) >
								↑
							<#elseif (collecteInfo.collectCompare.spectrum?number > 0) >
								↓
							<#else>
								|
							</#if>
							${collecteInfo.collectCompare.spectrum!''}
						</#if>
					
					</td>
					<td>海拔高度:${collecteInfo.altitude!''}
						<#if collecteInfo.collectCompare?exists>
							<#if (collecteInfo.collectCompare.altitude?number > 0) >
								↑
							<#elseif (collecteInfo.collectCompare.altitude?number > 0) >
								↓
							<#else>
								|
							</#if>
							${collecteInfo.collectCompare.altitude!''}
						</#if>
					</td>
					<td>经度:${collecteInfo.longitude!''}
						<#if collecteInfo.collectCompare?exists>
							<#if (collecteInfo.collectCompare.longitude?number > 0) >
								↑
							<#elseif (collecteInfo.collectCompare.longitude?number > 0) >
								↓
							<#else>
								|
							</#if>
							${collecteInfo.collectCompare.longitude!''}
						</#if>
					</td>
					<td>纬度:${collecteInfo.latitude!''}
						<#if collecteInfo.collectCompare?exists>
							<#if (collecteInfo.collectCompare.latitude?number > 0) >
								↑
							<#elseif (collecteInfo.collectCompare.latitude?number > 0) >
								↓
							<#else>
								|
							</#if>
							${collecteInfo.collectCompare.latitude!''}
						</#if>
					</td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>

				<!--采集工参附件-->
				<tr>
					<th rowspan="3"  colspan="2"> 采 集 <br/> 工 参 <br/> 附 件 </th>
					<td rowspan="3"><a href="javascript:void(0)" onclick="showImage(1)">第一个图片块</a> </td>
					<td rowspan="3"><a href="javascript:void(0)" onclick="showImage(2)">第二个图片块 </a></td>
					<td rowspan="3"><a href="javascript:void(0)" onclick="showImage(3)">第三个图片块 </a></td>
					<td rowspan="3"><a href="javascript:void(0)" onclick="showImage(4)">第四个图片块 </a></td>
					<td rowspan="3"><a href="javascript:void(0)" onclick="showImage(5)">第五个图片块 </a></td>
					<td rowspan="3"><a href="javascript:void(0)" onclick="showImage(6)">第六个图片块 </a></td>
					<td rowspan="3"><a href="javascript:void(0)" onclick="showImage(7)">第七个图片块 </a></td>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>

				<!--工单处理意见-->
				<tr>
					<th rowspan="3"  colspan="2"> 
						工 参 <br/> 处 理 <br/> 备 注
					</th>
					<td rowspan="3"  colspan="7"> 
						<input id="remarks" name="remarks" class="easyui-textbox" data-options="multiline:true" style=" width:98%;height:100%;"></input>
					</td>
										
					
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>

				<!--按钮模块-->
				<tr>
					<td rowspan="2"  colspan="9">
						<!--<input type="button" onclick="" value="上一条"/> -->
						<input type="button" onclick="passSuccess()" value="通过"/> 
						<input type="button" onclick="passFail()" value="不通过"/> 
						<input type="button" onclick="toCollect()" value="返回上一页"/> 
						<input type="button" onclick="nextAudit()" value="下一条"/> 
						
					 </td>
					
				</tr>
				<tr>
					
				</tr>
				

			
			</table>
		</div>
		
	</div>
	<!-- 选择自定义列同步数据 -->
	<div id="userDefDialog" style="display: none;overflow-x:hidden;">
		<div id="dcTree"></div>
	</div>
	<!--选择自定义列下发工单-->
	<div id="orderDefDialog" style="display: none;overflow-x:hidden;">
		<div id="orderTree"></div>
	</div>
	<!-- 图片信息展示框 -->
	<div id="showImageDialog" title="图片信息" style="display: none;overflow-x:hidden;width:300px;height:300px;">
		<form id="showImageForm" method="post">
			<input type="hidden" name="id" id="id"/>
			<table id="pic" class="basic basic_dialog">
			</table>
		</form>
	</div>
</body>
</html>