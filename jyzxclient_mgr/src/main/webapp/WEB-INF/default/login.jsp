<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>综合业务管理平台</title>
	    <script type="text/javascript" src="<%=basePath %>js/include-ext.js"></script>
   	    <script type="text/javascript" src="<%=basePath %>js/utils.js"></script>
		<script type="text/javascript">
	        Ext.onReady(function () {
		        Ext.Loader.addClassPathMappings({
	        		  "App": "app"
	        	});
	        	
	            Ext.create("App.view.LoginWindow", { autoShow: true });
	        });
	    </script>
</head>
<body style="overflow:hidden;">
    <div id="spanButtonPlaceHolder"></div>
</body>
</html>