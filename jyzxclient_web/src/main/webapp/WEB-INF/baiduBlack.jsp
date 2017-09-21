<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js "></script>
<html>
<body>

	<h2 align="center">查询百度黑名单</h2>
	<div align="center">
		<label>姓名：&nbsp</label><input id="realName" name="realName" type="text" /> <br> 
		<label>身份证号：</label><input id="idCard" name="idCard" type="text" /> 
		<br><button  onclick="search()" >查询</button> <br>
	<c:if test="${isData==true}">
		<br><table>
			<tr>
				<td colspan="3">姓名：${realName}</td>
				<td colspan="3">身份证号：${idCard}</td>
			</tr>
			<tr>
				<c:if test="${!(result.blackLevel eq '-9999')}">
					<td colspan="2">黑名单等级：${result.blackLevel}</td>
					<td colspan="2">加黑原因编码：${result.blackReason}</td>
				</c:if>
				<c:if test="${result.blackLevel eq '-9999''}">
					<td colspan="4">未命中黑名单</td>
				</c:if>
			</tr>

		</table>
	</c:if>
	</div>
</body>
<script type="text/javascript">
function search(){
	var realName = $("#realName").val();
	var idCard = $("#idCard").val();
	
	if(realName==""){
		alert("请输入姓名！");
		return ;
	}	
	if(idCard==""){
		alert("身份证号！");
		return ;
	}
	window.location.href =  "bdb.do?realName="+realName+"&idCard="+idCard;
}
</script>
</html>
