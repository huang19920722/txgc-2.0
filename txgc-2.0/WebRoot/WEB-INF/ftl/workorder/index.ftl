<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${webSiteName}</title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>

<#assign requestURI = springMacroRequestContext.getRequestUri() />
<style>
th{
	font-size:14px;
}
 table#workOrderInfoTab tr th{
		width:10%;
	}
table#workOrderInfoTab tr td{
		width:15%;
}

 table#collectInfoTab tr th{
		width:10%;
	}
table#collectInfoTab tr td{
		width:15%;
}


</style>
<script>
	var $datagrid;//工单datagrid
	
	var $communityDatagrid;//小区datagrid
	var $communityDialog;//小区dialog
	
	var $terminalDatagrid;//终端datagrid
	var $terminalDialog;//终端Dialog
	
	var $orgCombotree;//机构树
	
	var $orderDefDialog;//工单自定义列弹窗
	var $orderTree;//工单自定义列树
	
	var $auditDialog;//审核弹窗
	
	var aduFlag=true;//审核标志
	
	var $userDefDialog;//同步数据弹窗界面
	
	var $workOrderParaTree;//同步数据tree
	var appNode="";//节点名称
	var workOrderId="";//当前操作的工单id
	
	var $workOrderDetailDialog;//工单详情列表
	
	var ctx="${ctx}";//请求地址变量
	
	$(function(){
	//	$("#networkType").combobox('')
	
	
		//工单列表datagrid
		$datagrid=$("#datagrid").datagrid({
		 	url : '${ctx}/workOrder/workOrderGrid',
			toolbar : '#toolbar',
			title : '',
			singleSelect:true, 
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
				field:'id',
				title:'',
				align:'center',
				checkbox : true,
				width:50
			},{
				field:'workOrderNum',
				title:'工单编号',
				align:'center',
				width:110
			}] ],
			columns:[[{
				field:'orderState',
				title:'工单状态',
				align:'center',
				width:60,
				formatter:function(value,rowData,rowIndex){
					switch(parseInt(value)){
						case 0:
						return "已下达";
						case 1:
						return "已签收";
						case 2:
						return "已回传";
						case 3:
						return "待审核";
						case 4:
						return "已完成";
						default:
						return "待下达";	
					}
				}
			}
			,{
				field:'createTime',
				title:'创建时间',
				align:'center',
				sortable:true,
				width:140
			},{
				field:'completeTime',
				title:'完成日期',
				align:'center',
				sortable:true,
				width:140
			},{
				field:'timeLimit',
				title:'预计完成日期',
				align:'center',
				sortable:true,
				width:140
			},{
				field:'issuedWay',
				title:'创建方式',
				align:'center',
				width:70,
				formatter:function(value,rowData,rowIndex){
				
					switch(parseInt(value)){
						case 0:
						return "系统";
						case 1:
						return "个人";
						default:
						return "";	
					}
				}
			},{
				field:'explainState',
				title:'审核状态',
				align:'center',
				width:80,
				formatter:function(value,rowData,rowIndex){
					switch(parseInt(value)){
						case 1:
						return "通过";
						case 2:
						return "不通过";
						default:
						return "";	
					}
				}
			},{
				field:'networkStandard',
				title:'网络制式',
				align:'center',
				width:60
			},{
				field:'terminalcode',
				title:'终端编号',
				align:'center',
				width:100,
				formatter:function(value,rowData,rowIndex){
					if(rowData.terminal !=undefined && rowData.terminal != null ){
						return rowData.terminal.code;
					}
				}
			},{
				field:'terminalterminalUsername',
				title:'受理人',
				align:'center',
				width:60,
				formatter:function(value,rowData,rowIndex){
				if(rowData.terminal != undefined && rowData.terminal != null 
					&& rowData.terminal.terminalUser!=undefined && rowData.terminal.terminalUser!=null  ){
						return rowData.terminal.terminalUser.name;
					}
					
				}
			},{
				field:'1',
				title:'操作',
				align:'left',
				width:200,
				formatter:function(value,rec,i){
					var btnHtml="";
					var str=rec.terminalState;
					<!--<#if GeneralMethod.checkButton(requestURI,"icon-edit")>
						/*
						if(rec.orderState== null || parseInt(rec.orderState) == 0){
							btnHtml="<a href='javascript:edit("+i+");' plain='true'  iconcls='icon-edit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-edit' style='padding-left: 20px;'>编辑</span></span></a>";						
						}
						*/
					</#if>
					-->
					<#if GeneralMethod.checkButton(requestURI,"icon-remove")>
						if(rec.orderState== null || parseInt(rec.orderState) == 0){
							btnHtml+="<a href='javascript:remove("+i+");' plain='true'  iconcls='icon-remove' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
						}
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-redo")>			
						btnHtml+="<a href='javascript:detail("+i+");' plain='true'  iconcls='icon-redo' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-redo' style='padding-left: 20px;'>详情</span></span></a>";
					</#if>
					<#if GeneralMethod.checkButton(requestURI,"icon-audit")>	
						if(parseInt(rec.orderState)==3){
							btnHtml+="<a href='javascript:auditEvent("+i+");' plain='true'  iconcls='icon-audit' class='easyui-linkbutton l-btn l-btn-plain'><span class='l-btn-left'><span class='l-btn-text icon-audit' style='padding-left: 20px;'>审核</span></span></a>";
						}	
					</#if>

					return btnHtml;
					
				}
			}]]
		});
});

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



</script>

<script src="${static}/js/workorder/workorder.js" charset="UTF-8" type="text/javascript"></script>

</head>
<body>

	<div id="toolbar" class="search_table">
		<table>
		<tr>
				<td style="width:250px;">
					工单状态：
					<select id="orderState" name="orderState"   style="width:170px;height:26px;"  class="txtbox1 easyui-combobox" >
						<option  value="">请选择</option>
						<option  value="0">已下达</option>
						<option  value="1">已签收</option>
						<option  value="2">已回传</option>
						<option  value="3">待审核</option>
						<option  value="4">已完成</option>
					</select>
				</td>
				<td style="width:250px;">
					审核状态：
				<select id="explainState" name="explainState" style="width:170px;height:26px;" class="txtbox1 easyui-combobox" >
							<option  value="">请选择</option>
							<option  value="1">通过</option>
							<option  value="2">不通过</option>
						</select>
				
				</td>
				<td style="width:250px;">
					网络制式：
						<select id="networkStandard" name="networkStandard" style="width:170px;height:26px;"  class="txtbox1 easyui-combobox" >
							<option  value="">请选择</option>
							<option  value="2g">2g</option>
							<option  value="3g">3g</option>
							<option  value="4g">4g</option>
						</select>
				</td>
				<td style="width:250px;">
					创建方式：
						<select id="issuedWay" name="issuedWay" style="width:170px;height:26px;"  class="txtbox1 easyui-combobox" >
							<option  value="">请选择</option>
							<option  value="0">系统</option>
							<option  value="1">个人</option>
						</select>
				</td>
				
			</tr>
			<tr>
				<td>
				工单编号：
					<input id="workOrderNum" name="workOrderNum" class="easyui-textbox" style="height:26px;"/>
				</td>
				<td>
				受理人员：
					<input id="acceptUser" name="acceptUser" class="easyui-textbox" style="height:26px;"/>
				</td>
				<td>
					创建人员：
					<input id="creatUser" name="creatUser" class="easyui-textbox" style="height:26px;"/>
				</td>
				<td>
					终端编号：
					<input id="terminalCodeVal" name="terminalCodeVal" class="easyui-textbox" style="height:26px;"/>
				</td>
			</tr>
			<tr>
				<td>
					<a class="easyui-linkbutton" onclick="createOrder()">创建工单</a>
				</td>
				<td></td>
				<td></td>
				<td style="text-align:right;">
					<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchFun();" href="javascript:void(0);">查 找</a>
					<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearFun();" href="javascript:void(0);">清 空</a>
				</td>
				
			</tr>
		</table>
		
	</div>
	<div id="datagrid"></div>
	
	
	<div id="dialog" style="display: none;overflow-x:hidden;">
		<form id="workOrderForm">
			<table class="basic basic_dialog">
				<tr><th style="width:40%" >网络制式：</th><td>
				<select id="networkType" class="easyui-combobox" data-options="
				onChange:function(newValue, oldValue){
					$('#cname').textbox('setValue','');
					$('#wparameterId').val('');
				}
				" name="networkType" style="width:170px; height:26px;">   
				    <option value="2g" selected="selected">2G</option>   
				    <option value="3g">3G</option>   
				    <option value="4g">4G</option>   
				</select> 
				</td></tr>
				<tr><th>小区名称：</th>
					<td>
						<input id="cname" name="cname" class="easyui-textbox" readonly="readonly" required="true" style="width:170px; height:26px;"/>
						<input id="wparameterId" name="wparameterId" type="hidden"  />
						<a id="commmunityBtn" class="easyui-linkbutton" onclick="selCommunity()" >选择小区</a>
					</td>
				</tr>
				<tr><th>
				受理机构：</th><td><input id="orgName" name="orgName"  required="true" style="width:170px; height:26px;"/>
				<input id="orgId" name="orgId" type="hidden"  />
				
				</td></tr>
				<tr><th>终端编号：</th><td>
				<input id="terminalCode" name="terminalCode" class="easyui-textbox" readonly="readonly" required="true" style="width:170px; height:26px;"/>
				<input id="terminalId" name="terminalId" type="hidden">
				<a class="easyui-linkbutton" onclick="selTerminal()" >选择终端</a>
				</td></tr>
				<tr><th>人员：</th><td>
				<input id="terminalUser" name="terminalUser" class="easyui-textbox" readonly="readonly" required="true" style="width:170px; height:26px;"/>
				</td></tr>
				<tr><th>预计完成时间：</th><td>
				<input id="timeLimit" name="timeLimit" required="true" class="easyui-datetimebox combo-f datetimebox-f" editable="false" style="width:170px; height:26px;"/>
				</td></tr>
				<tr><th>备注：</th><td rowspan="2"><textarea id="remark" name="remark" class="easyui-textarea" rows="3" cols="21" ></textarea></td></tr>
				<tr></tr>
				<tr></tr>
				<tr>
				<th>采集项如下：</th>
				<td><a class="easyui-linkbutton" onclick="selCollectPara()" >选择工单采集项</a></td>
				</tr>
				<tr>
				<td rowspan="2" colspan="2">
					<input id="paraObjsText" name="paraObjsText" type="hidden" >
					<div id="showParaDiv"></div>
				</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 小区弹窗 -->
	<div id="communityDialog" style="display: none;overflow-x:hidden;">
	<div id="ctoolbar" class="search_table">
			<table>
				<tr>
					<td colspan="3">
						小区名称：
						<input id="communityName" name="communityName" class="easyui-textbox" style="height:26px;width:120px;"/>
						LAC:
						<input id="lac" name="lac" class="easyui-textbox" style="height:26px;width:120px;"/>
						Ci：
						<input id="ci" name="ci" class="easyui-textbox" style="height:26px;width:120px;"/>
					
					</td>
					<td>
						<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchComFun();" href="javascript:void(0);">查 找</a>
						<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearComFun();" href="javascript:void(0);">清 空</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="communityDatagrid"></table>
	</div>
	
	<!-- 终端设备弹窗 -->
	<div id="terminalDialog" style="display: none;overflow-x:hidden;">
	<div id="ttoolbar" class="search_table">
			<table>
				<tr>
					<td>
						终端编号：
						<input id="code" name="code" class="easyui-textbox" style="height:26px;width:120px;"/>
					</td>
					<td>
						<a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchTerFun();" href="javascript:void(0);">查 找</a>
						<a class="easyui-linkbutton" iconCls="icon-empty"  onclick="clearTerFun();" href="javascript:void(0);">清 空</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="terminalDatagrid"></table>
	</div>
	
	<!--工单下发自定义列-->
	<div id="orderDefDialog" style="display: none;overflow-x:hidden;">
		<div id="orderTree"></div>
	</div>
	
	<!--审核弹窗-->
	<div id="auditDialog" style="display: none;overflow-x:hidden;">
		<div region="center" border="false" style="overflow: hidden;">
	
			<div id="atoolbar" class="search_table">
				<table style="width:100%;">
				<!--标准工参模块-->
					<tr>
						<th rowspan="2"  colspan="2"> 标 准 <br/> 工 参 <br/> 信 息 </th>
						<td>天线挂高:
							<span id="antennaHangHigh"></span>
						</td>
						<td>电子下倾角:
							<span id="electronicUnderAngle"></span>
						</td>
						<td>方位角:
							<span id="orientationAngle"></span>
						</td>
						<td>频段:
							<span id="spectrum"></span>
						</td>
						<td>海拔高度:
							<span id="altitude"></span>
						</td>
						<td>经度:
							<span id="longitude"></span>
						</td>
						<td>纬度:
							<span id="latitude"></span>
						</td>
					</tr>

					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr></tr>
					<!--采集数据模块-->
					<tr>
						<th rowspan="2"  colspan="2"> 采 集 <br/> 工 参 <br/> 信 息</th>
						<td>天线挂高:
							<span id="antennaHangHighC"></span>
						</td>
						<td>电子下倾角:
							<span id="electronicUnderAngleC"></span>
						</td>
						<td>方位角:
							<span id="orientationAngleC"></span>
						</td>
						<td>频段:
							<span id="spectrumC"></span>
						</td>
						<td>海拔高度:
							<span id="altitudeC"></span>
						</td>
						<td>经度:
							<span id="longitudeC"></span>
						</td>
						<td>纬度:
						<span id="latitudeC"></span>
						</td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr></tr>
					<!--采集工参附件-->
					<tr>
						<th rowspan="2"  colspan="2"> 采 集 <br/> 工 参 <br/> 附 件 </th>
						<td rowspan="2"><a href="javascript:void(0)" onclick="showImage(0)">基站全景</a> </td>
						<td rowspan="2"><a href="javascript:void(0)" onclick="showImage(1)">基站入口 </a></td>
						<td rowspan="2"><a href="javascript:void(0)" onclick="showImage(2)">屋顶全貌 </a></td>
						<td rowspan="2"><a href="javascript:void(0)" onclick="showImage(3)">异常场景</a></td>
						<td rowspan="2"><a href="javascript:void(0)" onclick="showImage(4)">测试基站-HLHA </a></td>
						<td rowspan="2"><a href="javascript:void(0)" onclick="showImage(5)">测试基站-HLHB </a></td>
						<td rowspan="2"><a href="javascript:void(0)" onclick="showImage(6)">测试基站-HLHC </a></td>
					</tr>
					<tr>
					</tr>
	
					<!--工单处理意见-->
					<tr>
						<th rowspan="3"  colspan="2"> 
							工 参 <br/> 处 理 <br/> 备 注
						</th>
						<td rowspan="3"  colspan="7"> 
							<!--<input id="remarks" name="remarks" class="easyui-textbox" data-options="multiline:true" style=" width:98%;height:100%;"></input>-->
							<textarea id="remarks" name="remarks" class="easyui-textarea" rows="3" cols="115" style="resize:none;" ></textarea>
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
					</tr>
					<!--按钮模块-->
					<tr>
						<td rowspan="2"  colspan="9" style="text-align:center;">
							<!--<input type="button" onclick="" value="上一条"/> -->
							<input type="button" onclick="passSuccess(1)" value="通过"/> 
							<input type="button" onclick="passSuccess(2)" value="不通过"/> 
							<!--
							<input type="button" onclick="toCollect()" value="返回上一页"/> 
							<input type="button" onclick="nextAudit()" value="下一条"/> 
							-->
							
						 </td>					
					</tr>
					<tr>		
					</tr>
				</table>
			</div>
			
		</div>
	</div>
	
	<!-- 选择自定义列同步数据 -->
	<div id="userDefDialog" style="display: none;overflow-x:hidden;">
		<div id="workOrderParaTree"></div>
	</div>
	<!-- 图片信息展示框 -->
	<div id="showImageDialog" title="图片信息" style="display: none;overflow-x:hidden;width:800px;height:300px;">
		<form id="showImageForm" method="post">
			<input type="hidden" name="id" id="id"/>
			<table id="pic" class="basic basic_dialog">
			</table>
		</form>
	</div>
	
	<!-- 工单详情 -->
	<div id="workOrderDetailDialog" style="display: none;overflow-x:hidden;">
		<div id="gdxx">
			<div style="text-align:center;height:30px;line-height:30px;">工单信息</div>
			<table id="workOrderInfoTab" class="basic basic_dialog">
				<tr>
					<th>工单编号：</th>
					<td id="workOrderNumY">1</td>
					<th>工单状态：</th>
					<td id="orderStateY">2</td>
					<th>预计完成时间：</th>
					<td id="timeLimitY">3</td>
					<th>完成日期：</th>
					<td id="completeTimeY">3</td>

				</tr>
				<tr>
					<th>创建方式：</th>
					<td id="issuedWayY">1</td>
					<th>创建人员：</th>
					<td id="userNameY"></td>
					<th>创建时间：</th>
					<td id="createTimeY">3</td>
					<th>创建机构：</th>
					<td id="orgNameY">3</td>
				</tr>
				<tr>
					<th>审核状态：</th>
					<td id="explainStateY">2</td>
					<th>审核时间：</th>
					<td id="explainTimeY">3</td>
					<th>审核人员：</th>
					<td id="explainUserY">1</td>
					<th>网络制式：</th>
					<td id="networkStandardY">3</td>

				</tr>	
				<tr>
					<th>终端编号：</th>
					<td id="terminalCodeY"></td>
					<th>终端型号：</th>
					<td id="terminalModelY"></td>
					<th></th>
					<td></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>下发备注：</th>
					<td id="issuedRemarkY" colspan="7">2</td>
				</tr>
				<tr>
					<th>审核备注：</th>
					<td id="explainRemarkY" colspan="7">3</td>
				</tr>	
				<tr>
					<th>采集项目：</th>
				<td id="collectParaY" colspan="7">3</td>
				</tr>
			</table>
		</div>
		
		<div id="cjxx">
			<div style="text-align:center;height:30px;line-height:30px;">采集信息</div>
			<table id="collectInfoTab" class="basic basic_dialog">
				<tr>
					<th style="width:10%" >网络制式：</th>
					<td>1</td>
					<th>网络制式：</th>
					<td>2</td>
					<th>网络制式：</th>
					<td>3</td>
					<th>网络制式：</th>
					<td>3</td>

				</tr>	
			</table>
		</div>
	</div>
	
</body>

</html>