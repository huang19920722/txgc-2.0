<!DOCTYPE html >
<html>
	<head>
		<title></title>
		<#include "/inc/meta.ftl"/>
		<#include "/inc/easyui.ftl"/>
		<link rel="stylesheet" href="${static}/js/zTree/zTreeStyle/zTreeStyle.css" type="text/css">
		<style type="text/css">
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
		</style>
		<script type="text/javascript" src="${static}/js/zTree/jquery.ztree.all-3.5.min.js"></script>
		<#assign requestURI = springMacroRequestContext.getRequestUri() />
		<script type="text/javascript">
			var expandAllFlg = true;
			var $addProDialog=null;
			var $addProForm=null;
			var $addProGroupDialog=null;
			var $addProGroupForm=null;
			$(function(){
				buildRtuModelZtree();
			});
			function buildRtuModelZtree(){
				//初始化模板Ztree #rtuProTree
				var setting = {  
				    isSimpleData : true,//数据是否采用简单 Array 格式，默认false  
				    treeNodeKey : "id",//在isSimpleData格式下，当前节点id属性  
				    showLine : true, 
				    checkable : true,
				    check: {
				    	chkStyle:"checkbox",
						enable: false,
						dblClickExpand: false
					},view: {
						fontCss: getFontCss,
						addDiyDom: addDiyDom
					},callback: {
						onClick:zTreeOnCheck
					}              
				}; 
				 $.ajax({  
			        async : false,  
			        cache:false,  
			        type: 'POST',
			        data:{
						pid:2
			        },  
			        dataType : "JSON",  
			        url: '${ctx}/rtuController/buildRtuModelZtree',//请求的action路径  
			        error: function () {
			            alert('请求失败');  
			        },  
			        success:function(data){ //请求成功后处理函数。 
			        	var treeNodes =data.obj;   //把后台封装好的简单Json格式赋给treeNodes  
						$.fn.zTree.init($("#rtuProTree"), setting, treeNodes);
						var treeObj = $.fn.zTree.getZTreeObj("rtuProTree");	
						//treeObj.expandAll(true);
						var nodes=treeObj.getNodes();
						showNodeInfo(nodes[0]);
			        }
				});
				$("#expandOrCollapseAllBtn002").bind("click", {type:"expandOrCollapse"}, expandNode);
			}
			//modelZTREE事件
			function zTreeOnCheck(event, treeId, treeNode) {
					showNodeInfo(treeNode);
			}
			function getFontCss(treeId, treeNode) {
				return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
			}
			function expandNode(e) {
				var zTree = $.fn.zTree.getZTreeObj("rtuProTree"),
				type = e.data.type,
				nodes = zTree.getSelectedNodes();
				if(type == "expandAll") {
					zTree.expandAll(true);
				}else if (type == "collapseAll") {
					zTree.expandAll(false);
				}else if(type == "expandOrCollapse") {
					zTree.expandAll(expandAllFlg);
					expandAllFlg = !expandAllFlg;
				}else if (type == "checkAllTrueOrFalse") {
					zTree.checkAllNodes(checkAllTrueOrFalseFlg);
					checkAllTrueOrFalseFlg = !checkAllTrueOrFalseFlg;
				}else{
					if(type.indexOf("All")<0 && nodes.length == 0) {
						alert("请先选择一个父节点");
					}
					var callbackFlag = $("#callbackTrigger").attr("checked");
					for(var i=0, l=nodes.length; i<l; i++) {
						zTree.setting.view.fontCss = {};
						if(type == "expand") {
							zTree.expandNode(nodes[i], true, null, null, callbackFlag);
						}else if(type == "collapse") {
							zTree.expandNode(nodes[i], false, null, null, callbackFlag);
						}else if(type == "toggle") {
							zTree.expandNode(nodes[i], null, null, null, callbackFlag);
						}else if(type == "expandSon") {
							zTree.expandNode(nodes[i], true, true, null, callbackFlag);
						}else if(type == "collapseSon") {
							zTree.expandNode(nodes[i], false, true, null, callbackFlag);
						}
					}
				}
			}
			function addDiyDom(treeId, treeNode) {
				var editStr ="";
				var aObj = $("#" + treeNode.tId + "_a");
				if(treeNode.addFlag==1 ||treeNode.addFlag=="1" ){
					editStr+="<a style='line-height:14px;padding-left:10px;color:#1c72c3;'   id='diyBtn1_" +treeNode.tId+ "' onclick='addRtuPro(\""+treeNode.id+"\",\""+treeNode.name+"\",\""+treeNode.basicInfo.propType+"\");return false;'><span class='l-btn-left'><span class='l-btn-text icon-add' style='padding-left: 20px;'>新增节点</span></span></a>";
					editStr+="<a style='line-height:14px;padding-left:10px;color:#1c72c3;'   id='diyBtn1_" +treeNode.tId+ "' onclick='deleteRtuPro(\""+treeNode.id+"\");return false;'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
				}else if(( (treeNode.addFlag==2 ||treeNode.addFlag=="2")&& (treeNode.level==0 ||treeNode.level=="0")) ){
					editStr="<a style='line-height:14px;padding-left:10px;color:#1c72c3;'   id='diyBtn1_" +treeNode.tId+ "' onclick='addRtuProGroup(\""+treeNode.id+"\",\""+treeNode.name+"\",\""+treeNode.basicInfo.propType+"\");return false;'><span class='l-btn-left'><span class='l-btn-text icon-add' style='padding-left: 20px;'>新增节点</span></span></a>";
				}else{
					editStr="<a style='line-height:14px;padding-left:10px;color:#1c72c3;'   id='diyBtn1_" +treeNode.tId+ "' onclick='deleteRtuPro(\""+treeNode.id+"\");return false;'><span class='l-btn-left'><span class='l-btn-text icon-remove' style='padding-left: 20px;'>删除</span></span></a>";
				}
				aObj.after(editStr);
			}
			function showNodeInfo(treeNode){
				$("#showProDialog").show();
				$("#rtuProName001").val(treeNode.basicInfo.propDesc);
				$("#rtuProKey001").val(treeNode.basicInfo.propKey);
				$("#rtuProType001").val(treeNode.basicInfo.propType);
				$("#rtuProVal001").val(treeNode.basicInfo.propDefault);
				$("#rtuProPar001").val(treeNode.pname);
				$("#rtuProOrder001").val(treeNode.basicInfo.propOrder);
				var isRelateRefer=treeNode.basicInfo.isRelateRefer;
				if("Y"==isRelateRefer){
					if(null!=treeNode.basicInfo.listRtuRefer&&treeNode.basicInfo.listRtuRefer.length>0){
						//$("#rrtr001").css("display","");
						$("#rrtr002").css("display","");
						var rrHtml="<table class='basic' style='width:100%;margin:0;padding:0;'>";
					 		rrHtml+="<tr>";
					 			rrHtml+="<th style='text-align:center;'>属性</th>";
					 			rrHtml+="<th style='text-align:center;'>值</th>";
					 		rrHtml+="</tr>";
						for(var i=0;i<treeNode.basicInfo.listRtuRefer.length;i++){
							rrHtml+="<tr>";
					 			rrHtml+="<td class='slaveTd' style='text-align:center;'>"+treeNode.basicInfo.listRtuRefer[i].referDesc+"</td>";
					 			rrHtml+="<td class='slaveTd' style='text-align:center;'>"+treeNode.basicInfo.listRtuRefer[i].referKey+"</td>";
					 		rrHtml+="</tr>";
						}
						rrHtml+="</table>";
						$("#rrtd001").html(rrHtml);
					}else{
						$("#rrtr002").css("display","none");
					}
				}else{
					$("#rrtr002").css("display","none");
				}
			}
			function deleteRtuPro(id){
				//alert(id);
				if(!id){
					myMessage("删除失败！！！");
					return;
				}
				$.messager.confirm('提示','确定删除该节点？',function(r){   
					if (r){
						$.ajax({
							url : "${ctx}/rtuController/deleteRtuPro/" + id,
							cache : false,
							success : function(data) {
								var msg = "操作失败";
								if (data.success) {
									msg = "操作成功";
									buildRtuModelZtree();
								}
							}
						});
					}
				});
			}
			/*提示消息弹出框函数*/
			function myMessage(mg)
			{
			   $.messager.alert('提示',mg);
			}
			function addRtuPro(pid,pname,protype){
				if($addProForm!=null){
					$addProForm=null;
				}
				$addProForm = $('#addProForm').form();
				if($addProDialog!=null){
					$addProDialog=null;
				}
				$addProDialog=$("#addProDialog").show().dialog({
					modal : true,
					title : '配置信息',
					width:650,
					height:450,
					buttons : [
						{
							text : "保存",
							style : "text-align : center",
							handler : function() {
								savePro();
							}
						},
						{
							text : '关闭',
							style:'text-align:center',
							handler : function() {
								$addProDialog.dialog('close');
							}
						}
					],
					onClose:function(){
						
					}
				}).dialog('close');
				$("#rtuProName002").textbox('setValue','');
				$("#rtuProKey002").textbox('setValue','');
				$("#rtuProVal002").textbox('setValue','');
				$("#rtuProOrder002").numberbox('setValue','');
				if($("#rtuProRefer002").prop("checked")){
					$("#rtuProRefer002").prop("checked",false);
					addExtendPro($("#rtuProRefer002"));		
				}
				$("#rtuProType002").textbox('setValue',protype);
				$("#rtuProPar002").textbox('setValue',pname);
				$("#rtuProParId002").val(pid);
				$addProDialog.dialog('open');
			}
			function addRtuProGroup(pid,pname,protype){
				if($addProGroupForm!=null){
					$addProGroupForm=null;
				}
				$addProGroupForm = $('#addProGroupForm').form();
				if($addProGroupDialog!=null){
					$addProGroupDialog=null;
				}
				$addProGroupDialog=$("#addProGroupDialog").show().dialog({
					modal : true,
					title : '配置信息',
					width:450,
					height:250,
					buttons : [
						{
							text : "保存",
							style : "text-align : center",
							handler : function() {
								saveProGroup();
							}
						},
						{
							text : '关闭',
							style:'text-align:center',
							handler : function() {
								$addProGroupDialog.dialog('close');
							}
						}
					],
					onClose:function(){
						
					}
				}).dialog('close');
				$("#rtuProName003").textbox('setValue','');
				$("#rtuProOrder003").numberbox('setValue','');
				$("#rtuProPar003").textbox('setValue',pname);
				$("#rtuProParId003").val(pid);
				$addProGroupDialog.dialog('open');
			}
			function addExtendPro(obj){
				var checked=$(obj).prop("checked");
				if(checked){
					$("#relateReferDiv").css("display","block");
				}else{
					var arr=$("tr[name='addretr']");
					if(null!=arr && arr.length>0){
						for(var i=0;i<arr.length;i++){
							$(arr[i]).remove();
						}
					}
					$("#relateReferDiv").css("display","none");
				}
			}
			function addReferTr(){
				var rehtml="";
				rehtml+="<tr name='addretr'>";
					rehtml+="<td class='slaveTd' style='text-align:center;'>";
						rehtml+="<input type='text' class='easyui-textbox rightInput validatebox' required='true'  name='referDesc'/>";
					rehtml+="</td style='text-align:center;'>";
					rehtml+="<td class='slaveTd' style='text-align:center;'>";
						rehtml+="<input type='text' class='easyui-textbox rightInput validatebox' required='true'  name='referKey'/>";
					rehtml+="</td>";
					rehtml+="<td class='slaveTd' style='text-align:center;'>";
						rehtml+="<label><a onclick='deleteReferTr(this);' href='javascript:void(0);' style='cursor:pointer;width:120px;margin-left:10px;height:30px;'><image style='height:20px;width:20px;line-height:30px;vertical-align:middle;' src='${static}/js/jquery-easyui/1.4.4/themes/icons/btn-cancel.png'/><label style='width:60px;height:30px;line-height:30px;vertical-align:middle;margin-left:5px;cursor:pointer;'>删除</label></a></label>";
					rehtml+="</td>";
				rehtml+="</tr>";
				$("#referTab").append(rehtml);
			}
			function deleteReferTr(obj){
				$(obj).parent().parent().parent().remove();
			}
			function savePro(){
				if($addProForm.form('validate')){
					var chkval="";
					var referDesc="";
					var referKey="";
					if($("#rtuProRefer002").prop("checked")){
						chkval=$("#rtuProRefer002").val();
						var arr1=$("input[name='referDesc']");
						var arr2=$("input[name='referKey']");
						if(null!=arr1 && arr1.length>0){
							for(var j=0;j<arr1.length;j++){
								referDesc+=","+$(arr1[j]).val();
							}
						}
						if(null!=arr2 && arr2.length>0){
							for(var k=0;k<arr2.length;k++){
								referKey+=","+$(arr2[k]).val();
							}
						}
						referDesc=referDesc.substring(1);
						referKey=referKey.substring(1);
					}else{
						chkval="N";
					}
					var formData={
						"propDesc":$("#rtuProName002").val(),
						"propKey":$("#rtuProKey002").val(),
						"propType":$("#rtuProType002").val(),
						"propDefault":$("#rtuProVal002").val(),
						"pid":$("#rtuProParId002").val(),
						"propOrder":$("#rtuProOrder002").val(),
						"isRelateRefer":chkval,
						"referDesc":referDesc,
						"referKey":referKey
					};
					$.ajax({
							url:'${ctx}/rtuController/addPro', 
							data:JSON.stringify(formData),
							contentType:"application/json",
				 			success:function(result){
								if (result.success) {
									if(result.msg){
										if(result.msg==1 || result.msg=="1"){
											$.messager.alert('提示',"对不起，设置的默认值已被使用，请重新设置！");
										} else if(result.msg==2 || result.msg=="2"){
											$.messager.alert('提示',"对不起，设置的勾选项有误，请重新设置！");
										} else if(result.msg==3 || result.msg=="3"){
											$.messager.alert('提示',"保存成功！");
											$addProDialog.dialog('close');
											buildRtuModelZtree();
										}else{
											$.messager.alert('提示',"保存失败！");
										}
									}
								}else{
									if(result.msg){
										$.messager.alert('提示',"保存失败！");
									}else{
										$.messager.alert('提示',"保存失败！");
									}
								}
							}
						});
				}
			}
			function saveProGroup(){
				if($addProGroupForm.form('validate')){
					var formData={
						"propDesc":$("#rtuProName003").val(),
						"pid":$("#rtuProParId003").val(),
						"propType":$("#rtuProName003").val(),
						"propOrder":$("#rtuProOrder003").val()
					};
					$.ajax({
							url:'${ctx}/rtuController/addProGroup', 
							data:JSON.stringify(formData),
							contentType:"application/json",
				 			success:function(result){
								if (result.success) {
									if(result.msg){
										if(result.msg==3 || result.msg=="3"){
											$.messager.alert('提示',"保存成功！");
											$addProGroupDialog.dialog('close');
											buildRtuModelZtree();
										}else{
											$.messager.alert('提示',"保存失败！");
										}
									}
								}else{
									if(result.msg){
										$.messager.alert('提示',"保存失败！");
									}else{
										$.messager.alert('提示',"保存失败！");
									}
								}
							}
						});
				}
			}
		</script>
	</head>
	<body class="easyui-layout" fit="true">
		<div region="center" border="false" style="overflow-x: hidden;">
			<div style="width:100%">
				<div id="optionDiv" style="float:left;width:30%;">
					<label style="margin-left:8%;">[<a id="expandOrCollapseAllBtn002" style="cursor:pointer;" title="展开/折叠全部资源" onclick="return false;">展开/折叠</a>]</label>
					<ul id="rtuProTree" class="ztree" style="margin-left:5%;width:50%;"></ul>
				</div>
				<div style="float:left;width:60%;height:100%;">
					<div id="showProDialog" style="display: none;overflow-x:hidden;">
						<fieldset>
							<legend>配置详情</legend>
							<table class="basic" style="width:100%;">
								<tr>
									<th>上级节点：</th>
									<td><input id="rtuProPar001" name="rtuProPar"  class="rightInput" readOnly="readOnly" style="width:170px; height:26px;" /></td>
								</tr>
								<tr>
									<th width="85">组别：</th>
									<td><input id="rtuProType001" name="rtuProType"  class="rightInput" readOnly="readOnly" style="width:170px; height:26px;" /></td>
								</tr>
								<tr>
									<th width="85">配置名称：</th>
									<td><input id="rtuProName001" name="rtuProName"  class="rightInput" readOnly="readOnly" style="width:170px; height:26px;" required="true" /></td>
								</tr>
								<tr>
									<th>key值：</th>
									<td><input id="rtuProKey001" name="rtuProKey"  class="rightInput" readOnly="readOnly" style="width:170px; height:26px;" required="true" /></td>
								</tr>
								<tr>
									<th>默认值：</th>
									<td><input id="rtuProVal001" name="rtuProVal"  class="rightInput" readOnly="readOnly" style="width:170px; height:26px;" /></td>
								</tr>
								<tr>
									<th>排序：</th>
									<td><input id="rtuProOrder001" name="rtuProOrder"  class="rightInput" readOnly="readOnly" style="width:170px; height:26px;" /></td>
								</tr>
								<tr id="rrtr002" style="display:none;">
									<th>勾选属性：</th>
									<td id="rrtd001" style="text-align:center;margin:0;padding:0;">
																			
									</td>
								</tr>
							</table>
						</fieldset>
					</div>
				</div>
			<div>
			<!--新增节点弹窗-->
			<div id="addProDialog" style="display: none;overflow-x:hidden;">
				<form id="addProForm" method="post">
					<table class="basic" style="width:100%;">
						<tr>
							<th>上级节点：</th>
							<td>
								<input id="rtuProPar002" name="rtuProPar"  class="easyui-textbox rightInput" readOnly="readOnly" style="width:170px; height:26px;" />
								<input type="hidden" id="rtuProParId002" name="pid" />
							</td>
						</tr>
						<tr>
							<th width="85">组别：</th>
							<td><input id="rtuProType002" name="propType"  class="easyui-textbox rightInput" readOnly="readOnly" style="width:170px; height:26px;" /></td>
						</tr>
						<tr>
							<th width="85">配置名称：</th>
							<td><input id="rtuProName002" name="propDesc"  class="easyui-textbox rightInput validatebox" required="true" style="width:170px; height:26px;" required="true" /></td>
						</tr>
						<tr>
							<th>key值：</th>
							<td><input id="rtuProKey002" name="propKey"  class="easyui-textbox rightInput validatebox" required="true"  style="width:170px; height:26px;" required="true" /></td>
						</tr>
						<tr>
							<th>默认值：</th>
							<td><input id="rtuProVal002" name="propDefault"  class="easyui-textbox rightInput validatebox" required="true" style="width:170px; height:26px;" /></td>
						</tr>
						<tr>
							<th>排序：</th>
							<td><input id="rtuProOrder002" name="propOrder"  class="easyui-numberbox rightInput validatebox" required="true" style="width:170px; height:26px;" /></td>
						</tr>
						<tr>
							<th>勾选属性</th>
							<td>
								<input type="checkbox" id="rtuProRefer002" onClick="addExtendPro(this);"  name="isRelateRefer" value="Y" style="margin-left:10px;width:15px;height:15px;vertical-align:middle;"/>
								<label style="color:red;">(温馨提示：需要设置多选属性的请打√)</label>									
							</td>
						</tr>
					</table>
					<div id="relateReferDiv" style="display:none;">
						<div>
							<a class="easyui-linkbutton" style="margin-left:5px;margin-top:5px;" iconCls="icon-add"  onclick="addReferTr();" href="javascript:void(0);">新增勾选项</a>
						</div>
						<div>
							<table id="referTab" class="basic basic_dialog" style="margin-top:5px;">
								<tr>
									<th width:"20%" style="text-align:center;">属性</th>
									<th style="text-align:center;">值</th>
									<th style="text-align:center;">操作</th>
								</tr>
								<!--<tr>
									<td class="slaveTd" style="text-align:center;">
										<input type="text" class="rightInput" name="referDesc"/>
									</td style="text-align:center;">
									<td class="slaveTd" style="text-align:center;">
										<input type="text" class="rightInput" name="referKey"/>
									</td>
									<td class="slaveTd" style="text-align:center;">
										<a  onClick="javascript:deleteReferTr(this);">删除</>
									</td>
								</tr>-->
							</table>
						</div>
					</div>
				</form>
			</div>
			<!--添加组别dialog-->
			<div id="addProGroupDialog" style="display: none;overflow-x:hidden;">
				<form id="addProGroupForm" method="post">
					<table class="basic" style="width:100%;">
						<tr>
							<th>上级节点：</th>
							<td>
								<input id="rtuProPar003" name="rtuProPar"  class="easyui-textbox rightInput" readOnly="readOnly" style="width:170px; height:26px;" />
								<input type="hidden" id="rtuProParId003" name="pid" />
							</td>
						</tr>
						<tr>
							<th width="85">配置名称：</th>
							<td><input id="rtuProName003" name="propDesc"  class="easyui-textbox rightInput validatebox" required="true" style="width:170px; height:26px;" required="true" /></td>
						</tr>
						<tr>
							<th>排序：</th>
							<td><input id="rtuProOrder003" name="propOrder"  class="easyui-numberbox rightInput validatebox" required="true" style="width:170px; height:26px;" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>		