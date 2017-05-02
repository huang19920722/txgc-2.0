<div class="top_meun">
  <div class="top_meun_mid">
       <#if Session["currentUser"]??>
        <div class="m_r" id="dlh">
        <span>欢迎您，${Session['currentUser'].nickName!''}</span>          
        <a href=" <#if currentUser.type="${UserTypeEnum.Student.id}">  ${ctx}/student/front/main <#else> ${ctx}/manage/index  </#if> ">
           <div class="icon"><img src="${static}/images/index/icon2.png" width="16" height="16" /></div>
           <div class="txt">个人中心</div>
        </a> 
        <a href="#">
          <div class="icon"><img src="${static}/images/index/icon1.png" width="16" height="16" /></div>
           <div class="txt">新消息(<span>3</span>)</div>
        </a> 
        <a href="${ctx}/user/front/logout">
		   <div class="icon"><img src="${static}/images/index/zx_icon.png" width="16" height="16" /></div>
           <div class="txt">注销</div>
		</a>
      </div>
      <#else>
      <div class="m_r" id="dlq">
        <a href="javascript:void(0);" onclick="showLogin();" class="dl" id="dlbtn">登录</a>
        <a href="javascript:void(0);" onclick="showRegisterUser();" class="zc">注册</a>
      </div>
      </#if>
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
				console.info(data);
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


<div class="top">
   <div class="top_mid">
      <div class="logo"><a href="#"><img src="${static}/images/index/logo.png" width="262" height="58" /></a></div>
      <div class="nav">
          <a href="${ctx}" <#if index_head?? && index_head == 'sy01'> class="checked" </#if> >首页</a> 
          <a <#if !currentUser??>href="javascript:void(0);" onclick="showLogin();" <#else> href="${ctx}/student/front/main" </#if>  <#if index_head?? && index_head == 'xyxh02'> class="checked" </#if> >学员学习</a> 
          <a <#if !currentUser??>href="javascript:void(0);" onclick="showLogin();" <#else> href="${ctx}/student/front/main" </#if>  <#if index_head?? && index_head == 'xxjl03'> class="checked" </#if> >学习交流</a> 
          <a href="${ctx}/course/front/new/allCourse" <#if index_head?? && index_head == 'tjkc04'> class="checked" </#if> >推荐课程</a> 
          <a href="${ctx}/course/front/new/allCourse" <#if index_head?? && index_head == 'jckt05'> class="checked" </#if> >精彩课堂</a> 
          <a <#if !currentUser??>href="javascript:void(0);" onclick="showLogin();" <#else> href="${ctx}/student/front/main" </#if> <#if index_head?? && index_head == 'zxxx06'> class="checked" </#if> >在线学习</a>
      </div>
   </div>
</div>



<form id="loginForm">
<div class="dlkbox" id="dlkbox" style="display:none;">
   <div class="title1">
     <span  class="s1">平台登录</span>
     <div class="shut1"><a href="javascript:void(0);" onclick="closeLogin()" id="shut_dlk"></a></div>
   </div>
   <div class="nr">
      <table  border="0" cellpadding="0" cellspacing="0" class="table1">
      <#--
        <tr>
          <td class="st1">
              <input name="type" type="radio" value="student"  class="radio1"  checked="checked"/>
                <span class="rd"> 学生登录</span>
              <input name="type" type="radio" value="teacher" class="radio1" />
                <span  class="rd">教师登录</span>
          </td>
        </tr>-->
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

<form id="regForm" name="regForm" style="position: relative;" method="post" class="regsiter ft">
<div class="dlkbox" id="registerWindow" style="display:none;">
  <div class="title1">
     <span  class="s1">平台注册</span>
     <div class="shut1"><a href="javascript:void(0);" onclick="closeLogin()" id="shut_dlk"></a></div>
   </div>
   <div class="nr">
      <table  border="0" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td class="st1">
              <input name="type" type="radio" value="Student"  class="radio1"  checked="checked"/>
                <span class="rd">学生注册</span>
              <input name="type" type="radio" value="Teacher" class="radio1" />
                <span  class="rd">教师注册</span>
          </td>
        </tr>
        <tr>
          <td><input type="text" class="textbox1 validate[required,custom[email],funcCall[checkEmail]]" style="width:342px;" name="email" id="email" placeholder="邮箱" /></td>
        </tr>
        <tr>
          <td><input type="password" class="textbox1 validate[required,minSize[6],maxSize[16]]" style="width:342px;" name="password" id="password1" placeholder="密码（6-16位字母、数字或符号的组合）" /></td>
        </tr>
        <tr>
          <td><input type="password" class="textbox1 validate[required,minSize[6],maxSize[16]],equals[password1]]" style="width:342px;" name="passwd2" id="passwd2" placeholder="确认密码（6-16位字母、数字或符号的组合）"/></td>
        </tr>	
        <tr>
          <td>
         	<input id="verifyCode" name="verifyCode" type="text"  class="validate[required] textbox1 kd120" placeholder="验证码" />
             <div class="yzm_img"><a href="javascript:void(0);" onclick="changeVerifyCodeReg();" title="点击更换验证码"><img id="verifyImage" src="${ctx}/verifyCode" width="118" height="45" style="margin-top:5px; float: left;" /></a></div>
			 <div class="yzm_wz"><a href="javascript:void(0)" onclick="changeVerifyCodeReg();"><font>换一张<br/></font></a></div>
          </td>
        </tr>
        <tr>
          <td>
           <button class="btn_y" type="button" onclick="postForm();" style="width:370px;height:70px;border-radius:5px;-webkit-border-radius:5px;-moz-border-radius: 5px;font-family:'微软雅黑';background:#f7a947;border:none;color:#fff;cursor:pointer;font-size:34px;font-weight:normal;">同意协议并注册</button>
          </td>
        </tr>
        <tr>
          <td><font color="#cbcbcb">如已有帐户，请</font><a href="${ctx}/user/front/toLogin"><font  color="#308de3">&nbsp;登录</a></font>&#12288;</td>
        </tr>
    </table>
   </div>
 </div>
</form>
 