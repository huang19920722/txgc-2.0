<#include "/inc/meta.ftl"/>
<script type="text/javascript" charset="UTF-8">
	function logout(b) {
		$.post('${ctx}/main/logout', function() {
			/*if (b) {
					location.replace(sy.bp()+'/toLogin');
			} else {
				location.replace(sy.bp()+'/toLogin');
			}*/
			if (b) {
					location.replace(sy.bp()+'/');
			} else {
				location.replace(sy.bp()+'/');
			}
		});
	}

	function userInfo() {
		addTabFun({
			src : '${ctx}/main/managerInfo',
			title : '个人中心'
		});
	}
	function toIndex(){
		window.location.href="${ctx}/";
	}
	function toEditPassword(){
		addTabFun({
			src : '${ctx}/main/managerInfo',
			title : '个人中心'
		});
	}
</script>
<div class="left_logo">
<img src="${static}/images/b_logo.png" width="550" height="90">
</div>

<div class="header_right">
      <div class="ntxt001" style="text-align:left;">
        <div class="nbox1">机构名：${(Session.currentUser.organization.orgName)!''} </div>
        <div class="nbox1">用户名：${(Session.currentUser.nickName)!''}</div>
      </div>
	  
	  <div class="iconbox">
	    
		<a href="javascript:toIndex();">
		  <div class="imgbox"><img src="${static}/images/b_icon01.png" width="30" height="30" /></div>
		  <div class="txt">返回首页</div>
		</a>
		
		<a href="javascript:userInfo();">
		  <div class="imgbox"><img src="${static}/images/b_icon02.png" width="30" height="30" /></div>
		  <div class="txt">个人中心</div>
		</a>
		
		<a href="javascript:logout(true);">
		  <div class="imgbox"><img src="${static}/images/b_icon03.png" width="30" height="30" /></div>
		  <div class="txt">退出系统</div>
		</a>
		
	  </div>
      
      
      
    </div>
	
	
<!--
<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help" style="color:#FFFFFF">控制面板</a><a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back" style="color:#FFFFFF">注销</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div style = "background: url("../images/menu_select_bg.jpg") repeat-x scroll 0 0 transparent" onclick="userInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 100px;">
			<div onclick="sy.changeTheme('default');">default</div>
			<div onclick="sy.changeTheme('gray');">gray</div>
			<div onclick="sy.changeTheme('cupertino');">cupertino</div>
			<div onclick="sy.changeTheme('dark-hive');">dark-hive</div>
			<div onclick="sy.changeTheme('pepper-grinder');">pepper-grinder</div>
			<div onclick="sy.changeTheme('sunny');">sunny</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="logout(true);">退出系统</div>
</div>
-->