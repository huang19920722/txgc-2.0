<!--top-text-->
<#if currentUser??>
<script type="text/javascript">
	$(function(){
    	    var tabSide = $('#tabSide');
    	    tabSide.on('click',function(e){e.stopPropagation();})
    	    .find('>dl').on('click',function(){
    	    	tabSide.find('>ul').show();
    	    });
    	    $(document).on('click',function(){tabSide.find('>ul').hide();})
    	    
    	    var tabCourse = $('#tab-course');
    	    tabCourse.on('click',function(e){e.stopPropagation();});
    	    tabCourse.on('click',function(){
    	    	<#--$("#top-course").show();-->
    	    	layerSubmit({
    	    		url:"${ctx}/course/front/allCourseMajor",
    	    		success:function(datas){
    	    			var html = "";
    	    			$.each(datas,function(index,data){
    	    				html += "<li><a id='"+data.id+"'  title='"+data.name+"' href='${ctx}/course/front/findCourse?id="+data.id+"'>"+data.name+"</a></li>";
    	    			});
    	    			$("#allCourseType").html(html);
    	    		}
    	    	});
    	    	
    	    });
    	    $(document).on('click',function(){$("#top-course").hide();});
    	});
  function changeMajorTop(majorId){
     var url="${ctx}/course/front/findCourse?majorId="+majorId;
     window.location.href=url;
  }

</script>
<div id="top-info">
	<div class="cbody top-text">
      <div class="top-left fl">
          <a href="${ctx}"><img  class="fl logo" src="${static}/images/logo_1.png" /></a>
          <dl class="fl"  id="tab-course" style="cursor: pointer;"> 
            <dt class="category-icon"><a href="${ctx}/course/front/new/allCourse">课程类别</a></dt><#--新加的连接 以后要改删除 并去掉下面的注释-->
              <dd></dd>
        </dl>
          <dl class="fl">
              <dt class="teacher-icon"><a href="${ctx}/teacher/front/list">教师风采</a></dt>
              <dd></dd>
          </dl>
		  <dl class="fl">
              <dt class="home-icon"><a href="${ctx}">返回首页</a></dt>
              <dd></dd>
          </dl>
      </div>
      <div class="top-right fr" id="tabSide">
       	<dl class="fl">
        	<dt>
  			<img class="t-face"  id="t-face" src="${(currentUser.img??&&currentUser.img!="")?string(uploadUrl+(currentUser.img!''),static+'/images/head-icon.png')}"/>
        	&nbsp;&nbsp;&nbsp;&nbsp;<span id="currUserName">${(currentUser.nickName)!''}</span>&nbsp;&nbsp;&nbsp;&nbsp;<img  src="${static}/images/dropdown.png" width="13" height="8" onclick="showMenu()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dt>
            <dd></dd>
        </dl>
        <ul class="top-tab" id="topMenu" style="display:none;">
          <!-- <li><a href="/pdsci/inx/edu">回到首页</a></li> -->
         <#if currentUser.type="${UserTypeEnum.Student.id}">
         <li><a href="${ctx}/student/front/main">个人中心</a></li>
         <#else>
         <li><a href="${ctx}/manage/index">个人中心</a></li>
         </#if>
          <li><a href="${ctx}/user/front/logout">退出</a></li> 
        </ul>
      </div>
   </div>   
</div>
	<#else>
	<link rel="stylesheet" type="text/css" href="${static}/css/index1.css"/>
<div class="top_meun">
  <div class="top_meun_mid">
	 <div class="m_r" id="dlq">
        <a href="javascript:void(0);" onclick="showLogin();" class="dl" id="dlbtn">登录</a>
        <a href="javascript:void(0);" onclick="showRegisterUser();" class="zc">注册</a>
      </div>
   </div>
</div>
<script>
   var index;
   $(function(){
			  $(".textbox1").focus(function(){
									 $(this).css("color","#4AB3AC");
									 });
			  $(".textbox1").blur(function(){
									 $(this).css("color","#cbcbcb");
									 });
			 $(".i_kcbox").mouseover(function(){
									   $(this).css("background","#E8F8FF");
									   $(this).find(".kc_img").attr("class","kc_img1");
									 });
			  $(".i_kcbox").mouseleave(function(){
									   $(this).css("background","#FFF");
									   $(this).find(".kc_img1").attr("class","kc_img");
									 });
			 
			 tc_wz("dlkbox");
			 //$("#slide_x").cxSlide({plus:true,minus:true});
			  })
   
    function tc_wz(id){
		tcHeight=-$("#"+id).height()/2; 
		tcWidth=-$("#"+id).width()/2; 
		if($("#"+id).height()>$(window).height())
		{
			
			$("#"+id).css("cssText","position:absolute!important;margin-top:0px;top:100px;display:none;");
			$("#"+id).css("margin-left",tcWidth);
		}
		else
		{
		 $("#"+id).css("margin-top",tcHeight);
		 $("#"+id).css("margin-left",tcWidth);
		}
			
  }
  function showLogin(){
  	$("#loginForm").css("display","block");
  	changeVerifyCode();
  	index=layer.open({
			 	type:1,
			 	content:$("#dlkbox"),
			 	closeBtn: false
			 });
  }
  function closeLogin(){
  	//layer.close(index);
  	$("#loginForm")[0].reset();
  	//直接隐藏
  	$("#loginForm").css("display","none");
  	$("#dlkbox").css("display","none");
  	$(".layui-layer-shade").each(function(){
  		var idNum = $(this).attr("id");
  		idNum = idNum.substring(idNum.indexOf("layui-layer-shade")+17);
  		layer.close(idNum);
  	});
  }
  function changeVerifyCode(){
  	$("#verifyCode").attr("src","${ctx}/verifyCode?t="+(new Date()).valueOf());
  }
  function topLogin(){
    var form = $("#loginForm");
    var $errorMsg = $("#errorMsg"); 
	    if($.trim($("#loginAcct").val())==""){
	    	$errorMsg.text("用户名不能为空！");
	    	return;
	    }
	    if($.trim($("#password").val())==""){
	    	$errorMsg.text("密码不能为空！");
	    	return;
	    }
	    if($.trim($("#validatacode").val())==""){
	    	$errorMsg.text("验证码不能为空！");
	    	return;
	    }
		
		layerSubmit({
			url : '${ctx}/user/front/valLogin',
			data : form.serialize(),
			cache : false,
			success : function(data){
				if(data.success==true){
					//window.location.reload(true);
					if(data.obj.type=='${UserTypeEnum.Student.getId()}'){//学生
						window.location.href="${ctx}/student/front/main";
					}else{//其他
						window.location.href="${ctx}/manage/index";
					}
				}else{
					changeVerifyCode();
					
					$("#errorMsg").text(data.msg);
				}
			},
		});
	
}

	//注册
	function showRegisterUser(){
		window.open("${ctx}/user/front/regUser");
	}

	function keyLogin(){
		 if (event.keyCode==13)  
		  $("#dlk_btn").click(); 
	}
	
</script>

<form id="loginForm">
<div class="dlkbox" id="dlkbox" style="display:none;">
   <div class="title1">
     <span  class="s1">平台登录</span>
     <div class="shut1"><a href="javascript:void(0);" onclick="closeLogin()" id="shut_dlk"></a></div>
   </div>
   <div class="nr">
      <table  border="0" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td><input name="loginAcct" id="loginAcct" type="text"  class="textbox1" placeholder="用户名"/></td>
        </tr>
        <tr>
          <td>
          <input name="password" id="password" type="password"  class="textbox1" style="font-family:微软雅黑;" placeholder="密&#12288;码" />
          </td>
        </tr>
        <tr>
          <td>
            <input id="validatacode" name="validatacode" type="text"  class="textbox1 kd120" placeholder="验证码" />
             <div class="yzm_img"><a href="javascript:void(0);" onclick="changeVerifyCode();" title="点击更换验证码"><img id="verifyCode" src="${ctx}/verifyCode" width="118" height="45" alt="验证码" /></a></div>
			 <div class="yzm_wz"><a href="javascript:void(0);" onclick="changeVerifyCode();">换一张</a></div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="btnbox">
              <a href="javascript:void(0);" onclick="topLogin();" id="dlk_btn" class="dl">登&nbsp;&nbsp;录</a>
              <a href="#" class="mm">忘记密码</a>
            </div>
          </td>
        </tr>
        <tr>
          <td class="st2" id="errorMsg"></td>
        </tr>
    </table>

   </div>
</div>
</form>
<div style="padding-top:30px;"></div>      
</#if>
