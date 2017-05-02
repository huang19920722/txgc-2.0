package com.yp.sys.freemarker.view;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import com.yp.sys.common.GeneralMethod;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.enums.UserTypeEnum;
import com.yp.sys.util.FreemarkerUtil;



/**
 * 扩展默认的FreeMarkerView，并加入了自定义的指令
 * 自定义指令包括如下：json、
 * 
 * @author Matt.U
 *
 */
public class ExFreeMarkerView extends FreeMarkerView
{
private static final Logger logger = Logger.getLogger(ExFreeMarkerView.class);
	
	private static final String KEY_CONTEXT_PATH = "ctx";
	private static final String KEY_STATIC_RESOURCE = "static";
	private static final String KEY_UPLOAD_URL = "uploadUrl";
	
	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception
	{
		String contextPath = request.getContextPath();
		String staticResourcePath = contextPath + "/static"; //TODO 以后需要增加对该参数的设置
		model.put(KEY_CONTEXT_PATH, contextPath);
		if (logger.isDebugEnabled())
			logger.debug("Init freeMarker vars: ${ctx}=" + contextPath);
		
		Set<?> vars = getConfiguration().getSharedVariableNames();
		if (!vars.contains(KEY_STATIC_RESOURCE))
		{
			model.put(KEY_STATIC_RESOURCE, staticResourcePath);
			if (logger.isDebugEnabled())
				logger.debug("Init freeMarker vars: ${static}=" + staticResourcePath);
		}
		/*从upload.properties 读取上传文件访问根路径*/
		ResourceBundle bundle = ResourceBundle.getBundle("upload");
		String uploadUrl = bundle.getString("upload.url");
		model.put(KEY_UPLOAD_URL, uploadUrl);
		/*通用的静态方法、枚举*/
		model.put("GeneralMethod", FreemarkerUtil.getStaticModel(GeneralMethod.class));
		model.put("UserTypeEnum", FreemarkerUtil.getStaticModel(UserTypeEnum.class));
		model.put("GlobalConstant", FreemarkerUtil.getStaticModel(GlobalConstant.class));
		super.exposeHelpers(model, request);
	}
	
	@Override
	public boolean checkResource(Locale locale) throws Exception
	{
		return super.checkResource(locale);
	}

}
