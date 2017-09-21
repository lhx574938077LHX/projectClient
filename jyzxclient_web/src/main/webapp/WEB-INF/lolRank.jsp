<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>LOL Rank Search</title>
</head>
<body>
	<table border="10" style="border-color: #663333 " >
		<c:if test="${returnValue==true}">
			<tr align="center" >
				<td width="100">游戏名称</td>
				<td width="100">隐藏分</td>
				<td width="100">段位</td>
				<td width="100">S6定位赛段位</td>
				<td width="500">定位结果预测</td>			
			</tr>
			<tr align="center" >
				<td width="100">${name}</td>
				<td width="100">${rank}</td>
				<td width="100">${duanwei}</td>
				<td width="100">${rank6}</td>
				<td width="500">${matchstr}</td>			
			</tr>
		</c:if>
	</table>
</body>
</html>