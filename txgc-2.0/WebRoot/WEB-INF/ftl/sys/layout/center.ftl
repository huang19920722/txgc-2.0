<#include "/inc/meta.ftl"/>
<script type="text/javascript" charset="UTF-8">
	var centerTabs;
		$(function() {
			centerTabs = $('#centerTabs').tabs({
				border : false,
				fit : true
		});
		var src = '${ctx}/main/home';
		addTabFun({src:src,title:"首页",closable:false});
		tabContextMenu();
	});
	var workOrder = {
		currentOrders:[],
		workOrders:[]
	}
	
	function addTabFun(opts) {
		var title = opts.title;
		var src = opts.src;
		if (centerTabs.tabs('exists', title)) {//选中并刷新
			centerTabs.tabs('select', title);
			refreshTab(centerTabs,src);
			parent.$.messager.progress('close');
		}else{//新增
			var tContent = createFrame(src);
			var options = {
				title : title,
				content : tContent,
				closable : true,
				iconCls : ''
			};
			centerTabs.tabs('add', options);
		}
		bindTabEvent();
	}
	
	function createFrame(url) {
		var s = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>';
		return s;
	}
	
	function bindTabEvent() {
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children(".tabs-title").text();
			if(subtitle!="首页"){
				centerTabs.tabs('close',subtitle);
			}
		})
		/*为选项卡绑定右键*/
		$(".tabs-inner").bind('contextmenu',function(e){
			var subtitle =$(this).children(".tabs-title").text();
			if(subtitle=="首页"){
				$('#indexMenu').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}else{
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
				$('#mm').data("currtab",subtitle);
			}
			centerTabs.tabs('select',subtitle);
			return false;
		});
	} 
	//绑定右键菜单事件
	function tabContextMenu() {
		//刷新
		$('.mm-tabupdate').click(function(){
			refreshCurrentTab(centerTabs);
		})
		//关闭当前
		$('#mm-tabclose').click(function(){
			var currtab_title = $('#mm').data("currtab");
			if(currtab_title!="首页"){
				centerTabs.tabs('close',currtab_title);
			}
		})
		//全部关闭
		$('#mm-tabcloseall').click(function(){
			$('.tabs-inner span').each(function(i,n){
				var t = $(n).text();
				if(t!="首页"){
					centerTabs.tabs('close',t);
				}
			});
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function(){
			var prevall = $('.tabs-selected').prevAll();
			var nextall = $('.tabs-selected').nextAll();		
			if(prevall.length>0){
				prevall.each(function(i,n){
					var t=$('a:eq(0) span',$(n)).text();
					if(t!="首页"){
						centerTabs.tabs('close',t);
					}
				});
			}
			if(nextall.length>0) {
				nextall.each(function(i,n){
					var t=$('a:eq(0) span',$(n)).text();
					if(t!="首页"){
						centerTabs.tabs('close',t);
					}
				});
			}
			return false;
		});
	
		//退出
		$("#mm-exit").click(function(){
			$('#mm').menu('hide');
		})
		$("#im-exit").click(function(){
			$('#indexMenu').menu('hide');
		})
	}
	/*刷新当前tab*/
	function refreshCurrentTab(centerTabs){
		refreshTab(centerTabs,null);
	}
	function refreshTab(centerTabs,url){
		var currTab = centerTabs.tabs('getSelected');
		if(!url){
			url = $(currTab.panel('options').content).attr('src');
		}
		centerTabs.tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		});
	}
	/**关闭当前页面打开新的页面*/
	function closeThisTab(openTitle,src) {
		var currTab = centerTabs.tabs('getSelected');
		var cloesTitle = currTab.panel('options').title;
		if (centerTabs.tabs('select', cloesTitle)) {
			centerTabs.tabs('close', cloesTitle);
		}
		addTabFun({title:openTitle,src:src});
	}
	function getCurrentTabTitle(){
		var currTab = centerTabs.tabs('getSelected');
		return currTab.panel('options').title;
	}
</script>
<div id="centerTabs"></div>
<div id="mm" class="easyui-menu" style="width:120px;">
	<div class="mm-tabupdate">刷新</div>
	<div class="menu-sep"></div>
	<div id="mm-tabclose">关闭</div>
	<div id="mm-tabcloseother">关闭其他</div>
	<div id="mm-tabcloseall">关闭全部</div>
	<div class="menu-sep"></div>
	<div id="mm-exit">退出</div>
</div>
<div id="indexMenu" class="easyui-menu" style="width:120px;">
	<div class="mm-tabupdate">刷新</div>
	<div id="im-exit">退出</div>
</div>