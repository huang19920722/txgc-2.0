<script type="text/javascript" charset="UTF-8">
!function(dc){
	function defineColumns(appNode,noCheckBox){
		var arr = [[]];
		$.ajax({
		  url:'${ctx}/workOrder/defineColumns',
		  data:{appNode:appNode},
		  async:false,
		  success:function(result){
		  	if(!noCheckBox){
		  		arr[0].push({
		  			field:'id',
		  			checkbox:true
		  		});
	  		}
		  	for(i in result){
		  		var o = result[i];
		  		if(o.checked){
			  		arr[0].push({
			  			field:o.id.replace(/_([a-z])/g,function(a,b){return b.toUpperCase();}),
			  			title:o.text,
			  			width:100,
			  			align:'center'
			  		});
		  		}
		  	}
		  	
			  	//strat:末尾固定给一个操作查看按钮
			  	arr[0].push({
				  			field:'1',
				  			title:'操作',
				  			width:100,
				  			align:'center',
				  			formatter:function(value,row){
				  			//console.info(row);
				  			var btnHtmls="";
				  			//var btnHtmls="<a href ='javascript:collectInfoDetail("+row.id+")' iconcls='icon-search' class='easyui-linkbutton l-btn l-btn-plain' plain='true'><span class='l-btn-left'><span class='l-btn-text icon-search' style='padding-left: 20px;'>详情</span></span></a>|";
				  				btnHtmls+="<a href ='javascript:collectInfoImages(\""+row.name+"\")' iconcls='icon-search' class='easyui-linkbutton l-btn l-btn-plain' plain='true'><span class='l-btn-left'><span class='l-btn-text icon-search' style='padding-left: 20px;'>图片详情</span></span></a>";
				  			return btnHtmls;
				  			}
				 });

		  		//end
		  }
		});
		
		
		
		return arr;
	}
	function showDefineDialog(appNode){
		$("#dcDialog").dialog({
			title:"自定义列",
			width:500,
			height:300,
			idField:name,
			onBeforeOpen:function(){
				$('#dcTree').tree({    
				    url:'${ctx}/workOrder/defineColumns',
				    queryParams:{
						appNode:appNode
					},
					checkbox:true
				});  
			},
			buttons:[		
			{
				text:"全部选中",
				handler:function(){
					var nodes = $('#dcTree').tree('getChecked', 'unchecked');	// 获取未选择节点
					if(nodes){
						$.each(nodes,function(index,node){
							$('#dcTree').tree('check',node.target);
						});
					}
				}
			},{
				text:"全部取消",
				handler:function(){
					var nodes = $('#dcTree').tree('getChecked');	// get checked nodes
					if(nodes){
						$.each(nodes,function(index,node){
							$('#dcTree').tree('uncheck',node.target);
						});
					}
				}
			},{
				text:" 保  存 ",
				handler:function(){
					$.post(
						'${ctx}/workOrder/saveColumns',
						{appNode:appNode,content:function(){
						    var rows = $("#dcTree").tree("getChecked");
						    var showColumns = [];
			              	for(i in rows){
			                	showColumns.push(rows[i].id);
			              	}
					    	return showColumns.join(",");
					  	}},
						function(r){
							if(r.success){
								myMessage('保存成功',function(){
									$("#datagrid").datagrid({columns:defineColumns(appNode)});
									$("#dcDialog").dialog('close');
								});
							}
						}
					);
				}
			},{
				text:" 取  消 ",
				handler:function(){
					$("#dcDialog").dialog("close");
				}
			}]
		});
	}
	window.dc={
		defineColumns:defineColumns,
		showDefineDialog:showDefineDialog
	};
}();
</script>
<div id="dcDialog">
	<div id="dcTree"></div>
</div>