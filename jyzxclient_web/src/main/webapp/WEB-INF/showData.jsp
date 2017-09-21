<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dt" uri="/WEB-INF/datetag.tld"%>
<html>
<head>
<title>翔哥无敌</title>
</head>
<body>
	<table border="10" style="border-color: #663333 " > 
		<tr align="center">
			<td width="200px">序号</td>
			<td width="200px">借款类型</td>
			<td width="200px">借款状态</td>
			<td width="200px">借款金额</td>
			<td width="200px">合同日期</td>
			<td width="200px">批贷期数</td>
			<td width="200px">还款状态</td>
			<td width="200px">欠款金额</td>
			<td width="200px">公司代码</td>
		</tr>
		<c:forEach varStatus="i" var="data"  items="${loanInfos}" >
			<tr align="center">
				<td width="200px">
					${i.count}
				</td>
				<td width="200px">
					<c:if test="${data.borrowType==0}">未知</c:if>
					<c:if test="${data.borrowType==1}">个人信贷</c:if>	
					<c:if test="${data.borrowType==2}">个人抵押</c:if>	
					<c:if test="${data.borrowType==3}">企业信贷</c:if>	
					<c:if test="${data.borrowType==4}">企业抵押</c:if>	
				</td>
				<td width="200px">
					<c:if test="${data.borrowState==0}">未知</c:if>
					<c:if test="${data.borrowState==1}">拒贷</c:if>
					<c:if test="${data.borrowState==2}">批贷已放款</c:if>
					<c:if test="${data.borrowState==3||data.borrowState==6}">待放款</c:if>
					<c:if test="${data.borrowState==4}">借款人放弃申请</c:if>
					<c:if test="${data.borrowState==5}">审核中</c:if>
				</td>
				<td width="200px">
					<c:if test="${not empty data.borrowAmount}">
						<c:choose>
							<c:when  test="${data.borrowAmount==-7}">1千以下</c:when>
							<c:when  test="${data.borrowAmount==-6}">1-2千</c:when>	
							<c:when  test="${data.borrowAmount==-5}">2-3千</c:when>	
							<c:when  test="${data.borrowAmount==-4}">3-4千</c:when>	
							<c:when  test="${data.borrowAmount==-3}">4-6千</c:when>	
							<c:when  test="${data.borrowAmount==-2}">6-8千</c:when>	
							<c:when  test="${data.borrowAmount==-1}">8千-1万</c:when>	
							<c:when  test="${data.borrowAmount==0}">未知</c:when>	
							<c:when  test="${data.borrowAmount==1}">1-2万</c:when>	
							<c:otherwise>${data.borrowAmount*2-2}-${data.borrowAmount*2}万</c:otherwise>
						</c:choose>	
					</c:if>
				</td>
				<td width="200px">
					<c:if test="${not empty data.contractDate}"><dt:date value="${data.contractDate.getTime()}" format="yyyy-MM-dd"></dt:date> </c:if>
				</td>
				<td width="200px">
					${data.loanPeriod}
				</td>
				<td width="200px">
					<c:if test="${data.repayState==0}">未知</c:if>
					<c:if test="${data.repayState==1}">正常</c:if>
					<c:if test="${data.repayState==2}">M1</c:if>
					<c:if test="${data.repayState==3}">M2</c:if>
					<c:if test="${data.repayState==4}">M3</c:if>
					<c:if test="${data.repayState==5}">M4</c:if>
					<c:if test="${data.repayState==6}">M5</c:if>
					<c:if test="${data.repayState==7}">M6</c:if>
					<c:if test="${data.repayState==8}">M6+</c:if>
					<c:if test="${data.repayState==9}">已还清</c:if>
				</td>
				<td width="200px">
					<c:if test="${not empty data.arrearsAmount&&data.arrearsAmount!=0}">${data.arrearsAmount/100000}元</c:if>
					<c:if test="${not empty data.arrearsAmount&&data.arrearsAmount==0}">0元</c:if>
				</td>
				<td width="200px">
					${data.companyCode}
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>