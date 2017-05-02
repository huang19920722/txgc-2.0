<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#include "/inc/defineColumnsCollectInfo.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />
<script type="text/javascript" charset="UTF-8">
	var $collectInfoDialog;//采集信息详情弹窗
	var $collectImagesDialog;//采集信息图片弹窗
	//采集信息详情
	function collectInfoDetail(collectInfoId){
		//初始化采集信息详情弹窗
		$collectInfoDialog=$("#collectInfoDialog").show().dialog({
				modal : true,
				title : '采集信息详情',
				width:1000,
				height:450,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$collectInfoDialog.dialog('close');
					}
				}]	
		}).dialog('close');
		$collectInfoDialog.dialog('open');
		
	}

	//采集信息的图片信息
	function collectInfoImages(workParamId){
		if(workParamId == "null"){
			alert("数据异常");
			return;
		}
	
		//初始化采集信息图片界面
		$collectImagesDialog=$("#collectImagesDialog").show().dialog({
				modal : true,
				title : '采集信息图片详情',
				width:600,
				height:450,
				buttons : [ {
					text : '关闭',
					style:'text-align:center',
					handler : function() {
						$collectImagesDialog.dialog('close');
					}
				}],onClose:function(){
					$("#collectImagesDialog").empty();
				}
		}).dialog('close');
		
		//查询采集信息的图片信息
		$.ajax({
				url : '${ctx}/collectInfo/queryCollectInfoImagesT',
				data : {
					workParamId:workParamId
				},
				type:'POST',
				cache : false,
				success:function(data){
					if(data.success){
						if(data.obj!=null && data.obj.imageTypeList !=null && data.obj.imageTypeList.length>0  && data.obj.imageList != null && data.obj.imageList.length>0 ){
							var typeStr="";
							$.each(data.obj.imageTypeList,function(i,objType){
								typeStr+="<div><div style='width:100%;height:20px;text-align:center;'>"+imageTypeChange(objType)+"</div>";
								typeStr+="<div id='"+objType+"' style='width:100%;'>";
								
								typeStr+="</div></div>";

							});
							$("#collectImagesDialog").append(typeStr);
							$.each(data.obj.imageList,function(i,objImage){
								if(objImage.picType != null && objImage.picType != undefined){
									var htmlStr="";
									htmlStr+="<image src='${ctx}/uploadImages/"+objImage.url+"'/><br/>";
									$("#"+objImage.picType).append(htmlStr)
								}
							});
							
							$collectImagesDialog.dialog('open');
						}else{
							alert("未找到相关图片！");
						}

					}else{
						alert("查询图片失败");
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
	
	/*查找方法*/
	function searchFun() {
		$("#datagrid").datagrid('load', {
			reportTimeStart : $('#toolbar input[name=reportTimeStart]').val(),
			reportTimeEnd : $('#toolbar input[name=reportTimeEnd]').val(),
			antennaManufacturer : $('#toolbar input[name=antennaManufacturer]').val(),
			communityName : $('#toolbar input[name=communityName]').val()
		});
	}
 /*工具栏里面类容清空*/
	function clearFun() {
		$('#toolbar input').val('');
		$("#datagrid").datagrid('load', {});
		datagrid.datagrid('unselectAll');
	
	}
	
	//人工审核数据
	function personAudit(){
	
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

	
</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="search_table">
			<table>
				<tr>
					<td>
						上报时间：
						<input name="reportTimeStart" class="easyui-datetimebox combo-f datetimebox-f" style="height:26px;" editable=false/>
						到
						<input name="reportTimeEnd" class="easyui-datetimebox combo-f datetimebox-f" style="height:26px;" editable=false/>
					</td>
					<td>
						无线设备厂家：
						<!--<input name="antennaManufacturer" class="easyui-combobox" style="height:26px;"/>-->
						<select style="width:170px;height:26px;"  class="txtbox1 easyui-combobox" onChange="unitChange()" id="antennaManufacturer" name="antennaManufacturer">
								<option  value="">请选择</option>
								<#if wxcssb??>
									<#list wxcssb?keys as sb>
										<option value="${(wxcssb[sb])!''}">${(wxcssb[sb])!''}</option>
									</#list>
								</#if>
							</select>
					</td>
					<td>
						小区名称：
						<input name="communityName" class="easyui-textbox" style="height:26px;"/>
					</td>
					<td>
						<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
						<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
					</td>
				</tr>
				<tr>
					<td>

					<!--	
						<a class="easyui-linkbutton" onclick="personAudit()" href="javascript:void(0);">审核数据</a>
				-->
					<a class="easyui-linkbutton" onclick="dc.showDefineDialog('1479625859857')">自定义列</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="datagrid"></table>
	</div>
	
	<!-- 采集信息详情弹窗-->
	<div id="collectInfoDialog" style="display: none;overflow-x:hidden;">
	</div>
	<!-- 采集信息图片弹窗-->
	<div id="collectImagesDialog" style="display: none;overflow-x:hidden;">

	</div>
	
	
<script>
	$(function(){
		$("#datagrid").datagrid({
			url:'${ctx}/collectInfo/collectInfoDataGrid',
			fitColumns:false,
			fit:true,
			singleSelect:true, 
			toolbar:'#toolbar',
			pagination:true,
			columns:dc.defineColumns('1479625859857')
		});
	});
</script>
</body>
</html>