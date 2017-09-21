<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>插入索引-身份证MD5</title>
</head>
	<body>
		<form  method="post" action="insertIndexMd5.do"  enctype="multipart/form-data">
			<input id="file" name="file" type="file" /><br>
			<label>公司ID：&nbsp</label><input id="companyId" name="companyId" type="text" /> <br> 
			<label>公司编码：</label><input id="companyCode" name="companyCode" type="text" /> <br>
			<label>是否上线 :</label><select name="isOnline">
										<option value='0' selected>未上线</option>
										<option value='1'>已上线</option>
								  </select> <br>
			<input type="submit" value="upload">
		</form>
	</body>
</html>