<%-- ���Ż���ȸ ���� �� ȭ�� --%>
<%-- getPurchase.jsp include�Ұ� --%>
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
		<title>������ ��������</title>
	</head>
	
	<body>
		
		<jsp:include page="/purchase/getPurchase.jsp"></jsp:include>
		
	</body>
	
</html>
