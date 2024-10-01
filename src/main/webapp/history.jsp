<%@page import="com.model2.mvc.service.domain.Product" %>
<%@page import="com.model2.mvc.service.TranCodeMapper"%>
<%@page import="java.util.Map"%>
<%@page import="com.model2.mvc.service.product.impl.ProductServiceImpl"%>
<%@page import="com.model2.mvc.service.product.ProductService"%>
<%@ page contentType="text/html; charset=EUC-KR" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	
		<title>열어본 상품 보기</title>
	
	</head>
	
	<body>
	
		<center><h2>[ 최근 본 상품 ]</h2></center>
		
	<br>
	
	<%
// 		request.setCharacterEncoding("euc-kr");
// 		response.setCharacterEncoding("euc-kr");
	%>

	<c:if test="${!empty list }">
	
		<center>
		<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>No.</th>
			<th>상품명</th>
			<th>가격</th>
			<th style="width: 70px;">현재상태</th>
		</tr>			
			
		<c:forEach var="product" items="${list }" end="5" varStatus="status">
			<tr>
				<td>${status.count }</td>
				<td><a href="/product/getProduct?prodNo=${product.prodNo }&menu=search"	target="rightFrame">${product.prodName }</a></td>
				<td>${product.price }</td>
				<td>${tranCodeMap[product.proTranCode] }</td>
			</tr>
		</c:forEach>

	</c:if>
		
	<c:if test="${empty list}">
		<p>최근 조회한 상품이 없습니다.</p>
	</c:if>	

	
		</table>
		</center>
	
	
	</body>
</html>
