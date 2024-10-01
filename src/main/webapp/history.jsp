<%@page import="com.model2.mvc.service.domain.Product" %>
<%@page import="com.model2.mvc.service.TranCodeMapper"%>
<%@page import="java.util.Map"%>
<%@page import="com.model2.mvc.service.product.impl.ProductServiceImpl"%>
<%@page import="com.model2.mvc.service.product.ProductService"%>
<%@ page contentType="text/html; charset=EUC-KR" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	
		<title>��� ��ǰ ����</title>
	
	</head>
	
	<body>
	
		<center><h2>[ �ֱ� �� ��ǰ ]</h2></center>
		
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
			<th>��ǰ��</th>
			<th>����</th>
			<th style="width: 70px;">�������</th>
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
		<p>�ֱ� ��ȸ�� ��ǰ�� �����ϴ�.</p>
	</c:if>	

	
		</table>
		</center>
	
	
	</body>
</html>
