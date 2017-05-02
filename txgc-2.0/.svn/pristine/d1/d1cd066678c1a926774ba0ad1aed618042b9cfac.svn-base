    <%@page import="java.io.File"%>
        <%@page import="java.util.Properties"%>
        <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
        <%@ page import="com.adtech.sys.common.Uploader" %>
        <%@ page import="java.io.FileInputStream" %>


            <%
    //request.setCharacterEncoding( Uploader.ENCODEING );
    //response.setCharacterEncoding( Uploader.ENCODEING );
    response.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String upUrl = "";
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
    String currentPath = request.getRequestURI().replace( request.getContextPath(), "" );

    File currentFile = new File( currentPath );

    currentPath = currentFile.getParent() + File.separator;

    //加载配置文件
    String propertiesPath = request.getSession().getServletContext().getRealPath( currentPath + "config.properties" );
    Properties properties = new Properties();
    try {
        properties.load( new FileInputStream( propertiesPath ) );
    } catch ( Exception e ) {
        //加载失败的处理
        e.printStackTrace();
    }
    
    
    Uploader up = new Uploader(request);
    //up.setSavePath("upload"); //保存路径
    //up.setSavePath("files"); //保存路径
    up.setSavePath("../../../../../../HqgcMoocImg/files"); //保存路径
    
    String[] fileType = {".rar" , ".xls" , ".xlsx" , ".doc" , ".docx" , ".zip" , ".pdf" , ".txt" , ".swf", ".wmv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".mov", ".wmv", ".mp4"};  //允许的文件类型
    up.setAllowFiles(fileType);
    up.setMaxSize(500 * 1024);        //允许的文件最大尺寸，单位KB
    up.upload();
    
    if(up.getUrl().indexOf("HqgcMoocImg") > -1){
    	upUrl = basePath + up.getUrl().substring(up.getUrl().indexOf("HqgcMoocImg"));
    }
    //response.getWriter().print("{'url':'"+up.getUrl()+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");
    response.getWriter().print("{'url':'"+upUrl+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");

%>
