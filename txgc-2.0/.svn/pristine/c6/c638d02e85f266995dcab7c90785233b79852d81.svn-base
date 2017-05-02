package com.yp.sys.interceptors;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.entity.Menu;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.service.menu.IMenuService;
import com.yp.sys.service.user.ILogService;
import com.yp.sys.util.RequestUtil;

/**   
 * 文件名称：权限拦截器 
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年4月29日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private ILogService logService;
	@Autowired
	private IMenuService menuService;


	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		String requestUri = RequestUtil.getRequestPath(request);// 用户访问的资源地址
		String requestUrl =request.getRequestURL().toString();
		UserBean currUser = GlobalContext.getCurrentUser();
		if (currUser == null) {// 没有登录系统，或登录超时
			if(RequestUtil.isAjaxRequest(request)){//ajax请求
				response.setHeader("sessionstatus", "timeout");
			}else{
				forward("您没有登录或登录超时，请重新登录！", "/error/timeout.jsp", request, response);
			}
			return false;
		}
		String [] us=requestUri.split("/");
		String requestPath="/"+us[1]+"/";
		if("/main/".equals(requestPath)||requestUri.contains("/manage/index")
				||requestUri.contains("/images/")||requestUri.contains("/files/")||requestUrl.contains("rtmp://")
				||requestUrl.contains("/bbs/") ){
			return true;
		}
		
		boolean f=false;
		//用户拥有的菜单权限
		List<Menu> menus = menuService.findMenuMapByUserId(GlobalContext.getCurrentUser().getId());
		for (Menu menu : menus) {
			String src=menu.getSrc();
			if (src!=null&&src.contains(requestPath)) {
				f=true;
				break;
			}
		}
		if(!f){
			if(RequestUtil.isAjaxRequest(request)){
				response.setHeader("permission", "No");
			}else{
				forward("您没有当前资源访问权限，请联系管理员！", "/error/noright.jsp", request, response);
			}
			logger.error("您没有当前资源访问权限，请联系管理员！");
			return f;
		}
			
		//判断用户访问权限结束
		return true;
	}

	private void forward(String msg, String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
