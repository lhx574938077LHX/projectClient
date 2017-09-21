<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dt" uri="/WEB-INF/datetag.tld"%>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js "></script>
<html>
<head>

<title>LOL</title>
</head>
<body>
	<h2 align="center">LOL基本资料查询</h2>
	<div align="center">
		<label >服务器名称：</label>
		<select type="select" name="server" id="serverName">
			<option value="电信一" selected="selected">艾欧尼亚  电信一区</option>
			<option value="电信二" >祖安  电信二区</option>
			<option value="电信三" >诺克萨斯  电信三区</option>
			<option value="电信四" >班德尔城  电信四区</option>
			<option value="电信五" >皮尔特沃夫  电信五区</option>
			<option value="电信六" >战争学院  电信六区</option>
			<option value="电信七" >巨神峰  电信七区</option>
			<option value="电信八" >雷瑟守备  电信八区</option>
			<option value="电信九" >裁决之地  电信九区</option>
			<option value="电信十" >黑色玫瑰  电信十区</option>
			<option value="电信十一" >暗影岛  电信十一区</option>
			<option value="电信十二" >钢铁烈阳  电信十二区</option>
			<option value="电信十三" >均衡教派  电信十三区</option>
			<option value="电信十四" >水晶之痕  电信十四区</option>
			<option value="电信十五" >影流  电信十五区</option>
			<option value="电信十六" >守望之海  电信十六区</option>
			<option value="电信十七" >征服之海  电信十七区</option>
			<option value="电信十八" >卡拉曼达  电信十八区</option>
			<option value="电信十九" >皮城警备  电信十九区</option>
			<option value="网通一" >比尔吉沃特 网通一区</option>
			<option value="网通二" >德玛西亚 网通二区</option>
			<option value="网通三" >弗雷尔卓德 网通三区</option>
			<option value="网通四" >无畏先锋 网通四区</option>
			<option value="网通五" >恕瑞玛 网通五区</option>
			<option value="网通六" >扭曲丛林 网通六区</option>
			<option value="网通七" >巨龙之巢 网通七区</option>
			<option value="教育一" >教育网专区  教育一区</option>
		</select>
		
		<label >游戏名称：</label>
		<input type="text" id="player" />
		<button  onclick="lolSearch()" >查询</button> <br>
		<c:if test="${message==true}">
			<br>
			<table>
				<tr>
					<td colspan="2">服务器名：${serverName}</td>
					<td colspan="2">游戏名称：${playerName}</td>
				</tr>
				<tr>
					<td rowspan="4">${portrait}</td>
					<tr>
						<td >战斗力：${zhandouli}</td>
						<c:if test="${tier==true}">
							<td>段位：${tierrank}</td>
						</c:if>						
					</tr>
					<tr>
						<td >等级：${level}</td>
						<c:if test="${tier==true}">
							<td>胜点：${league_points}</td>
						</c:if>
					</tr>
					<tr>
						<td >${good}</td>
						<c:if test="${tier==true}">
							<td>更新时间：${warzone_updated}</td>
						</c:if>
					</tr>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td colspan="4" >${Record}</td>
				</tr>
			</table>
			<br>
			<table>	
				<tr >
					<td colspan="4" >常用英雄</td>
				</tr>
				<c:forEach varStatus="i" var="data" items="${heroLoLList}">
					<tr >
						<td ><img  src="${data.getSrc()}"></td>
						<td>${data.getTitle()}</td>
					</tr>
				</c:forEach>
			</table>
			</table>
			<br>
			<table  border="10">	
				<tr >
					<td colspan="9" >英雄池</td>
				</tr>
				<tr>
					<td width="100">英雄名称</td>
					<td width="50">胜率</td>
					<td width="80">使用场次</td>
					<td width="140">场均[杀/死/助]</td>
					<td width="50">场均KDA</td>
					<td width="100">场均分钟输出</td>
					<td width="100">场均分钟经济</td>
					<td width="80">场均补兵</td>
					<td width="80">MVP次数</td>
				</tr>
				<c:if test="${UserHeroDetailList.size()>0}">
					<c:forEach varStatus="i" var="data" items="${UserHeroDetailList}" >
					<tr>
						<td width="100">${data.getChampionNameCN()}</td>
						<td width="50">${data.getWinRate()}%</td>
						<td width="80">${data.getMatchStat()}</td>
						<td width="140">${data.getAverageKDA()[0]}/${data.getAverageKDA()[1]}/${data.getAverageKDA()[2]}</td>
						<td width="50">${data.getAverageKDARating()}</td>
						<td width="100">${data.getAverageDamage()}</td>
						<td width="100">${data.getAverageEarn()}</td>
						<td width="80">${data.getAverageMinionsKilled()}</td>
						<td width="80">${data.getTotalMVP()}</td>
					</tr>					
					</c:forEach>
				</c:if>
			</table>
		</c:if>
	</div>

</body>
<script type="text/javascript">
	function lolSearch(){
		var serverName = $("#serverName").val();
		var player = $("#player").val();
		
		if(player==""){
			alert("请输入游戏名称！");
			return ;
		}
		window.location.href =  "lolDetailSearch.do?serverName="+serverName+"&playerName="+player;
	}
</script>
</html>