<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script>
		var basePath = '<%= basePath %>';
		</script>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge" >
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1">
	    <title>综合业务管理平台</title>
	    <script type="text/javascript" src="<%=basePath %>js/include-ext.js"></script>
	    <script type="text/javascript" src="<%=basePath %>js/config.js"></script>
	    <script type="text/javascript" src="<%=basePath %>app/app.js"></script>
	    
	    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/common.css"/>
	    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/icons.css"/>
	    <script type="text/javascript" src="<%=basePath %>js/utils.js"></script>
	    <script type="text/javascript" src="<%=basePath %>js/jquery/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="<%=basePath %>js/hash/md5-min.js"></script>
        <script type="text/javascript" src="<%=basePath %>js/hash/sha1-min.js"></script>
	    
	</head>
	<body>
	    
	</body>
</html>