<%-- 구매상세조회 수정 후 화면 --%>
<%-- getPurchase.jsp include할것 --%>
<%@page import="com.model2.mvc.service.domain.Purchase" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
	Purchase purchase = (Purchase) request.getAttribute("purchase");
	request.setAttribute("purchase", purchase);
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>수정된 구매정보</title>
	</head>
	
	<body>
		
		<jsp:include page="/purchase/getPurchase.jsp"></jsp:include>
		
	</body>
	
</html>
