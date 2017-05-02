<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<#include "/inc/meta.ftl"/>
<#include "/inc/easyui.ftl"/>
<#include "/inc/easyui-portal.ftl"/>
<script type="text/javascript" charset="UTF-8">
  /**
  var datagrid;
  function resultGrid(text,type){
  	dataGrid(type);
         	 $('#diagDate').dialog({   
					   				title: text,   
								    width: 800,   
								    height: 400,   
								    closed: false,   
								    cache: false,   
								    modal: true  
								});   
  					}

   function dataGrid(type){

   if(type==null || type==""){
    alert("对不起，传人参数错误!");
   }
   else{
	 datagrid = $('#datagrid').datagrid({
			url : 'portalController.ftl?queryNum&typeid='+type,
			toolbar : '#toolbar',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 10,
			pageList : [10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			fit : true,
			fitColumns : true,
			nowrap : false,
			border : false,
			idField : 'id',
			columns : [ [ 
			{
				field : 'persName',
				align:'center',
				title : '姓名',
				width : 50
			},
			 {
				field : 'sex',
				title : '性别',
				align:'center',
				width : 50
			},
			 {
				field : 'birth',
				title : '出生日期',
				align:'center',
				width : 80
			},
			{
				field : 'address',
				title : '所属区域',
				align:'center',
				width : 100
			}, 
			
			{
				field : 'personNum',
				title : '健康档案号',
				align:'center',
				width : 150
			}, 
			 {
				field : 'idCard',
				title : '身份证号',
				align:'center',
				width : 150
			},
			 {
				field : 'phone',
				title : '联系电话',
				align:'center',
				width : 100
			},
			
			 {
				field : 'dateTime',
				title : '随访时间',
				align:'center',
				width : 80
			},
			{
				field : 'caozuo',
				title : '操作',
				width :80,
				align:'center',
				formatter : function(value, rowData, rowIndex) {
				   var id=rowData.personNum;
				   var avar="<a class='easyui-linkbutton' iconCls='icon-remove' plain='true' href='personController.ftl?main&jkda="+id+"' style='text-decoration:none;'  target='_blank' >个人详情</a>";
				  return avar;
				}
			}
			
			
			
			] ],

		});
   }
   }
function searchFun() {
		datagrid.datagrid('load', {
			personNum : $('#toolbar input[name=personNum]').val(),
			persName : $('#toolbar input[name=persName]').val()
		});
	}

	var portal;
	var col;
	$(function() {


		col = $('#portal div').length;
		portal = $('#portal').portal({
			border : false,
			fit : true
		});
		$.ajax({
			url : 'portalController.ftl?show&sort=seq&order=asc',
			cache : false,
			dataType : "json",
			success : function(response) {
				if (response && response.rows) {
					var rows = response.rows;
					for ( var i = 0; i < rows.length; i++) {
						var src;
						if (/^\//.test(rows[i].src)) {
							//以"/"符号开头的,说明是本项目地址
							src = rows[i].src.substr(1);
						} else {
							src = rows[i].src;
						}
						var p = $('<div style="overflow:hidden;"/>').appendTo('body').panel({
							title : rows[i].title,
							content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>',
							height : rows[i].height,
							maximizable : rows[i].maximizable == true
					
						});
						portal.portal('add', {
							panel : p,
							columnIndex : i % col
						});
					}
					portal.portal('resize');
				}
			}
		});
	});
	
	
	var noticedatagrid;
	
	 function noticeGrid(id){ 
	   $('#noticeDate').dialog({   
					   				title: "公告",   
								    width: 800,   
								    height: 400,   
								    closed: false,   
								    cache: false,   
								    modal: true  
								}); 
			getnotice(id);					  
	 }
	 
	 function getnotice(id){		
	      $.ajax({
   	        url : 'noticeController.ftl?article',
   	        data : {id : id	},
    	    cache : false,
    		dataType : "text",
    		success : function(response) {    		    
    		     var json = eval('('+response+')');
    		     $('#title').text(json.notice.title);
    		     $('#content').empty(); 
    		     $('#content').append(json.notice.content);
    		     var noticefilelist=json.noticefilelist;
    		     if(noticefilelist.length>0)
    		    {
    		      var downfile=$('#downfile');
    		      downfile.empty(); 
    		      for(var i=0;i<noticefilelist.length;i++)
    		      {
    		       downfile.append("<tr><td width=\"20%\" height=\"30\" align=\"center\">附件</td><td width=\"60%\" align=\"left\">"+noticefilelist[i].filename+"</td><td width=\"20%\" align=\"center\"><a href=\"noticeController.ftl?download&filename="+noticefilelist[i].filename+"\" class=\"easyui-linkbutton\">下载</a></td></tr>");
    		      }
    		    }
    		}
    });

	 }
	 
	 function noticeMore() 
  	{  	     
  	   noticemoreGrid();
     	$('#diagNote').dialog({   
					   				title: "公告",   
								    width: 800,   
								    height: 400,   
								    closed: false,   
								    cache: false,   
								    modal: true  
								});   
  	}
  	
  	 function noticemoreGrid()
   {
  
  	noticedatagrid = $('#noticedatagrid').datagrid({
			url : 'noticeController.ftl?datagrid',
			toolbar : '',
			title : '',
			iconCls : 'icon-save',
			pagination : true,
			pageSize : 5,
			pageList : [11 ],
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
			},
			 {
				field : 'title',
				title : '标题',
				width : 400,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {		
				return "<a href=\"javascript:DataGr('"+rowData.id+"');\" style=\"font-size:12px;\">"+rowData.title+"</a>";
				}
			},
			 {
				field : 'status',
				title : '审核状态',
				width : 100,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
				   if(rowData.status =="0" || rowData.status == ""){
				    return "未通过";
				   }
				   else{
				    return "通过";
				   }	
				}				
			},{
				 field : 'promulgator',
				title : '发布者',
				width : 100,
				sortable : true				
			}, {
				field : 'releasetime',
				title : '发布时间',
				width : 200,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
				   if(rowData.releasetime == null || rowData.releasetime == ""){
				    return "";
				   }
				   else{
				   return new Date(rowData.releasetime).format('yyyy-MM-dd');
				   }	
				}
			}
			 ] ],			
			onLoadSuccess:function(data) {
				var btns = ["1128112611132","8a92a3b8-f5e0-0618-5d96-f3ac24dea83e","c8c94f38-d027-5ab9-620a-ebfcb8e681f7","fbfb538b-c790-2cf3-622b-55a62f3d9692"];
				permiss(btns,"yhgl");
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
  
   }
   ***/
</script>
</head>
<body class="easyui-layout" fit="true">
<div id = "diagDate">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;display: none;">
			<fieldset>
				<legend>筛选</legend>
				<table class="tableForm">
					<tr>
						<th>健康档案号</th>
						<td colspan="2"><input name="personNum" style="width: 150px;" />
						</td>
						<th>姓名</th>
						<td colspan="2"><input name="persName" style="width: 150px;" />
						</td>
						<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查找</a>
						</td>
					</tr>
				
				
				</table>
			</fieldset>
			
		</div>

     <table id="datagrid"></table>
	</div>



	<div region="center" style="overflow: hidden;" border="false">
		<div id="portal" style="position:relative; text-align:center;background:#f0f7fb;">
           <img src="${static}/images/home_bk.png" width="714" height="489">
		
		</div>
	</div>	
	
	
	<div id="noticeDate"  style="height: auto;display: block;">
	    <table width="100%" cellpadding="0" cellspacing="0">						
			<tr>							
					<td  colspan="3" width="100%" height="30" align="center" id="title" style="font-size:16px; font-weight:500; line-height:30px; padding-left:20px; padding-right:20px;"></td>
			</tr>
			<tr>							
					<td  colspan="3"  width="100%" align="left" id="content" style="font-size:12px; line-height:20px; padding-left:20px; padding-right:20px;"></td>
			</tr>	   	
		</table>
		<table  align="center"  width="80%" cellpadding="0" cellspacing="0" border="1" bordercolor="#999999" id="downfile" ></table>						
	</div>
	
	<div id ="diagNote">	
       <table id="noticedatagrid"></table>
    </div>
</body>
</html>