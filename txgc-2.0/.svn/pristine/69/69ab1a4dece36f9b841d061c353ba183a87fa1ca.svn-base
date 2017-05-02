
	var $orgTree;
	
	
	//加载机构树
	function loadOrgsTree(){
	
		
		var treeNodes;
		var roleId;
		//var node = datagrid.datagrid('getSelected');
		
		var settingOrg = {  
		    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
		    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
		    showLine : true, 
		    checkable : true,
		    check: {
				enable: true,
				dblClickExpand: false
			},view: {
				fontCss: getFontCss
			},callback: {
				onClick: onClickOrg
			}              
		};  
		settingOrg.check.chkboxType = { "Y" : "s", "N" : "s" };
  		//settingOrg.check.enable = false;
		var orgGetUrl=ctx+'/menu/getOrgsbyRoleId';
	    $.ajax({  
	        async : false,  
	        cache:false,  
	        type: 'POST',
	        data:{
	        	id:1,
	        	roleId:''
	        },  
	        dataType : "text",  
	        url: ctx+'/menu/getOrgsbyRoleId',//请求的action路径  
	        error: function () {
	            alert('请求失败');  
	        },  
	        success:function(data){ //请求成功后处理函数。    
	            treeNodes = eval('('+data+')');;   //把后台封装好的简单Json格式赋给treeNodes  
	            $.fn.zTree.init($("#orgTree"), settingOrg, treeNodes);
	        }  
	    });  
	  
	   var zTree = $.fn.zTree.getZTreeObj("orgTree");
	  	//var allNodes= zTree.getNodes();
	  	var allNodes = zTree.transformToArray(zTree.getNodes());

	   for (var i=0; i < allNodes.length; i++) {
			zTree.setChkDisabled(allNodes[i], true);
		}
	}
	
	function onClickOrg(e,treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree");
		zTree.expandNode(treeNode);
		
	}
	
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	
