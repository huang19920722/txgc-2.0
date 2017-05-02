<!DOCTYPE html >
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#assign requestURI = springMacroRequestContext.getRequestUri() />



<script type="text/javascript" charset="UTF-8">
	var datagrid;
	var villageDialog;
	var villageForm;
	var organTree;
	var roleTree;
	var $mapDialog;//地图dialog对象
	$(function(){
		villageForm = $('#villageForm').form();
		villageDialog = $('#villageDialog').show().dialog({
			modal : true,
			title : '新增阀值',
			width:650,
			height:300,
			buttons : [ {
				text : '确定',
				style:'text-align:center',
				handler : function() {
					if(villageForm.form('validate')){
						var formData={
								"longitudeMin":$("#longitudeMin").val(),
								"longitudeMax":$("#longitudeMax").val(),
								"latitudeMin":$("#latitudeMin").val(),
								"latitudeMax":$("#latitudeMax").val(),
								"orientationAngleMin":$("#orientationAngleMin").val(),
								"orientationAngleMax":$("#orientationAngleMax").val(),
								"mechanicalUnderAngleMin":$("#mechanicalUnderAngleMin").val(),
								"mechanicalUnderAngleMax":$("#mechanicalUnderAngleMax").val(),
								"antennaHangHighMin":$("#antennaHangHighMin").val(),
								"antennaHangHighMax":$("#antennaHangHighMax").val(),
								"xqgc":$("#xqgc").val()
							}
						var villageUrl="${ctx}/params/vilageAddThr";
						$.ajax({
							url:villageUrl, 
							data:JSON.stringify(formData),
							contentType:"application/json",
				 			success:function(result){
								if (result.success) {
									villageDialog.dialog('close');
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
			url : '${ctx}/params/datagridThr',
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
				checkbox : false
			}] ],
			columns : [ [ 
				 {
					field : 'networkStandard',
					title : '网络类型',
					width : 180,
					sortable : true
				},
				{
					field : 'orgId',
					title : '运营商',
					width : 80,
					sortable : true
				},
				{
					field : 'longitudeMin',
					title : '经度最小阀值',
					width : 80,
					sortable : true
				},
				{
					field : 'longitudeMax',
					title : '经度最大阀值',
					width : 80,
					sortable : true
				},
				{
					field : 'latitudeMin',
					title : '纬度最小阀值',
					width : 80,
					sortable : true
				},
				{
					field : 'latitudeMax',
					title : '纬度最大阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'spectrum',
					title : '频段',
					width : 100,
					sortable : true
				},
				{
					field : 'electronicUnderAngleMin',
					title : '电子下倾角最小阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'electronicUnderAngleMax',
					title : '电子下倾角最大阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'orientationAngleMin',
					title : '方位角最小阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'orientationAngleMax',
					title : '方位角最大阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'antennaHangHighMin',
					title : '天线挂高最小阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'antennaHangHighMax',
					title : '天线挂高最大阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'altitudeMin',
					title : '海拔高度最小阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'altitudeMax',
					title : '海拔高度最大阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'recodeStatus',
					title : '状态',
					width : 100,
					sortable : true
				},
				{
					field : 'mechanicalUnderAngleMin',
					title : '机械下倾最小阀值',
					width : 100,
					sortable : true
				},
				{
					field : 'mechanicalUnderAngleMax',
					title : '机械下倾角最大阀值',
					width : 100,
					sortable : true
				} ] ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				$(this).datagrid('selectRow', rowIndex);
			}
		});
		
		
		//初始化地图dialog
		$mapDialog=$("#mapDialog").show().dialog({
			modal : true,
			title : '工参GPS',
			width:900,
			height:450,
			  closed: true 
		});
		
		
	});
	function village() {
		//villageDialog.dialog('open');
		//villageForm.form('clear');
	}

	function searchFun() {
		datagrid.datagrid('load', {
			startTime : $('#toolbar input[name=startTime]').val(),
			endTime : $('#toolbar input[name=endTime]').val(),
			wirelessEquipmentManufacturer : $('#toolbar input[name=wirelessEquipmentManufacturer]').val(),
			communityName : $('#toolbar input[name=communityName]').val()
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
				<!--<tr>
					<td>
						更新时间：
						<input name="startTime" class="easyui-datetimebox combo-f datetimebox-f" editable=false/>
						到
						<input name="endTime" class="easyui-datetimebox combo-f datetimebox-f" editable=false/>
					</td>
					<td>
						无线厂商设备：
						<select style="width:170px;"  class="txtbox1 easyui-combobox" onChange="unitChange()" id="wirelessEquipmentManufacturer" name="wirelessEquipmentManufacturer">
								<option  value="">请选择</option>
								<#if wxcssb??>
									<#list wxcssb?keys as sb>
										<option value="${(sb)!''}">${(wxcssb[sb])!''}</option>
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
				</tr>-->
				<!--<tr>
					<td>
						<a class="easyui-linkbutton" onclick="village();" href="javascript:void(0);">新增阀值</a>
						<a class="easyui-linkbutton" onclick="" href="javascript:void(0);">自定义列</a>
					</td>
				</tr>-->
			</table>
		</div>
		<table id="datagrid"></table>
	</div>
	<div id="villageDialog" style="display: none;overflow-x:hidden;">
		<form id="villageForm" method="post">
			<input type="hidden" id="xqgc" name="xqgc" value="" />
			<table class="basic basic_dialog">
				<tr>
					<td>阀值</td>
					<td>上限</td>
					<td>下限</td>
				</tr>
				<tr>
					<td>经度</td>
					<td><input id="longitudeMin" name="longitudeMin" /></td>
					<td><input id="longitudeMax" name="longitudeMax" /></td>
				</tr>
				<tr>
					<td>纬度</td>
					<td><input id="latitudeMin" name="latitudeMin" /></td>
					<td><input id="latitudeMax" name="latitudeMax" /></td>
				</tr>
				<tr>
					<td>方位角</td>
					<td><input id="orientationAngleMin" name="orientationAngleMin" /></td>
					<td><input id="orientationAngleMax" name="orientationAngleMax" /></td>
				</tr>
				<tr>
					<td>机械下倾角</td>
					<td><input id="mechanicalUnderAngleMin" name="mechanicalUnderAngleMin" /></td>
					<td><input id="mechanicalUnderAngleMax" name="mechanicalUnderAngleMax" /></td>
				</tr>
				<tr>
					<td>挂高</td>
					<td><input id="antennaHangHighMin" name="antennaHangHighMin" /></td>
					<td><input id="antennaHangHighMax" name="antennaHangHighMax" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<style type="text/css">
		#mapDiv {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<!-- 地图dialog-->
	<div id="mapDialog" >
		<div id="mapDiv"></div>
	</div>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=CSR6APuQxRR7pHFtPzwjgLtKGTKGQvrE"></script>	
	<script src="${static}/js/baidumap/map.js" charset="UTF-8" type="text/javascript"></script>
	<script type="text/javascript">
		/*展示工参信息在百度地图上的GPS*/
		function paramsGPS(){
			var rows = $('#datagrid').datagrid('getSelections');
			if(rows){
				$mapDialog.dialog('open');
				$.each(rows,function(index,value){
					var loadFlag=true;
					if(value.longitude != '' && value.latitude != '' && value.longitude != null && value.latitude != null){
						var point=new BMap.Point(value.longitude,value.latitude);
						if(loadFlag){
							map.setCenter(point);
							loadFlag=false;
						}
						var label = new BMap.Label("小区编号:"+value.communityScrambler,{offset:new BMap.Size(20,-10)});				
						addMarker(point,label);
					}
				});

			}else{
				myMessage("请先选中数据行！");
			}
			
		}
	</script>
</body>
</html>