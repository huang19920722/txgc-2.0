<script src="${static}/js/jquery.mCustomScrollbar.min.js" language="javascript"></script>
<script>
	$(document).ready(function(){
		 myrefresh() ;
		 
		        var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null;
		        //因360新版内核与Chrome 一样了 下面处理
		        if(isChrome && window.navigator.webkitPersistentStorage){
		        	//broName = 'Chrome';
		        }else{
		        	//broName = '360';  
		        	isChrome = false;
		        }
		        
				if (isChrome) {
				        //alert('Chrome');
				        $("#txDivId").css("display","none");
				}else{
					$("#txDivId").css("display","block");
				}
	});
	//是否显示提示
	$(window).scroll(function () {
	        var scrollTop = $(this).scrollTop();
	        var scrollHeight = $(document).height();
	        var windowHeight = $(this).height();
	        if(scrollTop > 0){
	        	$("#txDivId").css("display","none");
	        }else{
	        	//$("#txDivId").css("display","block");
	        }
        
	    });
    
	function myrefresh(){ 
		<#if Session["currentUser"]??>
		$.ajax({
			url : '${ctx}/front/news/answerCount',
			cache : false,
			dataType : "json",
			success : function(data) {
				if(data.success){
				var str=data.obj.split(",");
				
					$("#st1 span").text(str[0]); //未讀回復的消息
					$("#st2 span").text(str[1]); //課程通知公告
				   document.getElementById('answerCount').innerHTML=str[2];
			     }
			}
		}); 
		setTimeout('myrefresh()',600000); 
		</#if>
		//display:none;
	}
	function seach(){
		var name=$('#allSeach').val();
		window.open("${ctx}/front/news/seach/"+encodeURI(encodeURI(name)));
	}
	function noTaolun(){
		layerMsg("建设中，请进相应课程讨论区");
	}
</script>
<div id="txDivId" style="position:fixed;width:100%;height:30px;text-align:center;top:0px; z-index:999;display:none;" >
	<a href="${uploadUrl}files/20160517/ChromeStandalone_50.0.2661.102_Setup.exe" style="color:red;">为了您更好的体验，请点击安装谷歌浏览器</a>
</div>
<div class="top">
   <div class="top_mid">
   	
      <div class="logo"><a href="javascript:void(0);"><img src="${static}/images/index/logo.png" width="262" height="58" /></a></div>
      <div class="gr_box">
		<#if Session["currentUser"]??>
			<div class="messages" id="messages">
			  <div class="icon">
			   <a href="${ctx}/chapter/front/courseQuestionW" target="_blank">
				 <div class="sm" id="answerCount"></div>
			   </a>
			  </div>
			  <div class="xl" style="display:none;">
			    <div class="j"></div>
				<div class="lb">
				 <p><a href="${ctx}/chapter/front/courseQuestionW" target="_blank" class="st1" id="st1">未读的回复<span>0</span>条</a></p>
				 <#if currentUser.type="${UserTypeEnum.Student.id}">
				 	<p><a href="${ctx}/student/front/main" class="st1" id="st2">通知公告<span>0</span> 条</a></p>
				 </#if>
				 <p><a href="javascript:void(0);" class="st2" id="st3">未读的私信<span>0</span> 条</a></p>
				</div>
			  </div>
			</div>
		</#if>
        <div class="grzx">
		<#if Session["currentUser"]??>
			<div class="ydl" id="ydl">
				<div class="tx">
					<a href="<#if currentUser.type="${UserTypeEnum.Student.id}">  ${ctx}/student/front/main <#else> ${ctx}/manage/index  </#if> " > 
					<img src="${(currentUser.img??&&currentUser.img!="")?string(uploadUrl+(currentUser.img!''),static+'/images/head-icon.png')}" width="42" height="42" /></a></div>
				<div class="xl"  style="display:none;">
				  <a href=" <#if currentUser.type="${UserTypeEnum.Student.id}">  ${ctx}/student/front/main <#else> ${ctx}/manage/index  </#if>" class="st1">个人中心</a>
				  <a href="${ctx}/user/front/logout" class="st2">注销</a>
				</div>
			</div>
		<#else>
          <div class="wdl" >
			<a href="javascript:void(0);" onclick="showLogin();" id="dlbtn">登录</a>|
			<a href="javascript:void(0);" onclick="showRegisterUser();">注册</a>
          </div>
        </#if> 
        </div>
      </div>
      <div class="se_box">
       		<input name="allSeach" id="allSeach" type="text"  class="txtbox1"  value="<#if name??>${(name)!''}<#else>关键词</#if>" onblur="if(value=='') {value='关键词'}" onfocus="if(value=='关键词') {value=''}"/>
			<div class="btn1">
			  <a href="javascript:void(0);"  onclick="seach();"></a>
			</div>
	  </div>
      <div class="nav">
          <a href="${ctx}" class="checked">首 页</a> 
          <a  href="${ctx}/course/front/new/allCourse">资源中心</a> 
            <!-- target=" _blank" -->
		  <#if Session["currentUser"]??>
		  		<!--<a href="${ctx}/bbs/bbs/userloginact.jsp" target="_blank" >学习交流</a>-->
		  		<a href="javascript:void(0);" onclick="noTaolun();" target="_blank" >学习交流</a>
				<a href=" <#if currentUser.type="${UserTypeEnum.Student.id}">  ${ctx}/student/front/main <#else> ${ctx}/manage/index  </#if>" >个人中心</a> 
		  <#else>
			    <a href="javascript:void(0);" onclick="noTaolun();"  target="_blank">学习交流</a>
			    <!--<a href="${ctx}/front/news/loginAcct"   target="_blank">123</a> -->
			    <a href="javascript:void(0);" onclick="showLogin();" >个人中心</a>
		  </#if>
          <a href="javascript:void(0);">帮助</a> 
      </div>
   </div>
</div>

<script  >
 
   var indexLogo;
   $(function(){
				$("#ydl").mouseenter(function(){
			                           $(this).find(".xl").show();
									  
									 });
				$("#ydl").mouseleave(function(){
									    $(this).find(".xl").hide();
									  
									 });
			   $("#messages").mouseenter(function(){
			                           $(this).find(".xl").show();
									  
									 });
				$("#messages").mouseleave(function(){
									    $(this).find(".xl").hide();
									  
									 });
			 
			   $(".textbox1").focus(function(){
									 $(this).css("color","#4AB3AC");
									 });
			  $(".textbox1").blur(function(){
									 $(this).css("color","#cbcbcb");
									 });
			 $(".i_kcbox").mouseenter(function(){
			                           $(this).find(".shine").stop();
		                               $(this).find(".shine").css("background-position","-250px 0"); 
		                               $(this).find(".shine").animate({backgroundPositionX: '250px'},500);
									   $(this).css("background","#fff");
									  // $(this).find(".kc_img").attr("class","kc_img1");
									  
									 });
			  $(".i_kcbox").mouseleave(function(){
									  $(this).css("background","#fff");
									  // $(this).find(".kc_img1").attr("class","kc_img");
									 });
			  $("#ydl").mouseenter(function(){
			                           $(this).find(".xl").show();
									  
									 });
			  $("#ydl").mouseleave(function(){
									    $(this).find(".xl").hide();
									  
									 });
			  $("#classsx").mouseenter(function(){
			                           $(this).find(".xll").show();
									   $(this).find(".xz").addClass("checked");
									 });
			  $("#classsx").mouseleave(function(){
									    $(this).find(".xll").hide();
									    $(this).find(".xz").removeClass("checked");
									 });
			 tc_wz("dlkbox");
			  tabToggle();
			   tabTogglelog();
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
  	indexLogo=layer.open({
			 	type:1,
			 	content:$("#dlkbox"),
			 	closeBtn: false
			 });
	$("#loginAcct").focus();
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
  	var  d = new Date();
  	$("#verifyCode").attr("src","${ctx}/verifyCode?d="+d+parseInt(100*Math.random()));
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
					$errorMsg.text(data.msg);return;
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
	
	function login(){
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
				//console.info(data);
				if(data.success==true){
					if(data.obj.type=='${UserTypeEnum.Student.getId()}'){//学生
						window.location.href="${ctx}/student/front/main";
					}else{//其他
						window.location.href="${ctx}/manage/index";
					}
				}else{
					changeVerifyCode();
					//layerMsg(data.msg);
					$("#errorMsg").text(data.msg);
				}
			},
		});
	
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
              <a href="javascript:void(0);" onclick="login();" id="dlk_btn" class="dl">登&nbsp;&nbsp;录</a>
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

