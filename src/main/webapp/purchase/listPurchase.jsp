<%-- ���Ÿ�� ȭ�� --%>
<%-- listPurchase.do --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<html>

	<head>
	
		<title>���Ÿ�� ��ȸ</title>
	
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
	
		<script type="text/javascript">
			function fncGetUserList() {
				document.detailForm.submit();
			}
			
		</script>
		
		<style>
	        a.disabled {
	            pointer-events: none; /* ��ũ Ŭ�� ��Ȱ��ȭ */
	            color: #FFFFFF; /* ��Ȱ��ȭ �� ��ũ�� ���� ���� */
	            text-decoration: none; /* ��ũ ���� ���� */
	            cursor: default; /* �⺻ Ŀ���� ���� */
	        }
	        
    	</style>
    	
    	<script src="https://code.jquery.com/jquery-2.2.4.js" 
				integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" 
				crossorigin="anonymous"></script>
    	
    	<script type="text/javascript" src="/javascript/common.js"></script>
    	
    	<script type="text/javascript">
//     	<!--	
    		function fncUpdateTranCode(tranNo, tranCode) {
    			if(confirm("��������� �����Ͻðڽ��ϱ�?\n\n����� �� ��ۿϷ�")) {
//     				window.location.href="/purchase/updateTranCode?tranNo="+tranNo+"&tranCode="+tranCode;
//     				$("location").attr("href", "/purchase/updateTranCode?tranNo="+tranNo+"&tranCode="+tranCode);
					linkTo("/purchase/updateTranCode?tranNo="+tranNo+"&tranCode="+tranCode);
    			}
    		}
    		
    		$("form").attr("action", "/product/listProduct").attr("method", "podst");
//     	-->
    	</script>
		
	</head>
	
	<body bgcolor="#ffffff" text="#000000">
		
		<form name="detailForm" action="/purchase/listPurchase" method="post">
			
			<div style="width: 98%; margin-left: 10px;">
			
				<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
					<tr>
						<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
						<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="93%" class="ct_ttl01">
										${user.userName } ���� ���Ÿ�� ��ȸ
									</td>
								</tr>
							</table>
						</td>
						<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
				
					<tr>
						<td colspan="11">��ü ${paging.total } �Ǽ�, ���� ${paging.currentPage } ������</td>
					</tr>
					<tr>
						<td class="ct_list_b" width="50">No</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="100">��ǰ��</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="100">�޴»��</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">��ȭ��ȣ</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">��������</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">�����Ȳ</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">��������</td>
					</tr>
					<tr>
						<td colspan="11" bgcolor="808285" height="1"></td>
					</tr>
					
					<c:forEach var="purchase" items="${map.list }" varStatus="status">
					
						<tr class="ct_list_pop">
							<td align="center">
								${status.count }
							</td>
							
							<td></td>
							<%-- ��ǰ�� --%>
							<td align="left">
								<a href="/purchase/getPurchase?tranNo=${purchase.tranNo }">
									${purchase.purchaseProd.prodName }
								</a>
							</td>
							
							<td></td>
							
							<%-- �޴»�� --%>
							<td align="left">
								${purchase.receiverName }
							</td>
							
							<td></td>
							
							<%-- ��ȭ��ȣ --%>
							<td align="left">
								${purchase.receiverPhone }
							</td>
							
							<td></td>
							
							<%-- �������� --%>
							<td align="left">
								${purchase.orderDate }
							</td>
							
							<td></td>
							
							<%-- �����Ȳ --%>
							<td align="left">
								${tranCodeMap[purchase.tranCode] }
							</td>
							
							<td></td>
							
							<%-- ��������(���Ȯ�ι�ư) --%>
							<td align="left">
							
								<c:choose>
								
									<c:when test="${purchase.tranCode == 3 }">
										<a href="javascript:fncUpdateTranCode(${purchase.tranNo}, 4)">���ǵ���</a>
									</c:when>
									
									<%-- �������� : ��ۿϷ����̸� ����� �����ϰԲ� **4=��ۿϷ� --%>
									<c:when test="${purchase.tranCode > 1 && purchase.tranCode < 4 }">
									
										<a href="/purchase/getPurchase?tranNo=${purchase.tranNo }">
											������� Ȯ��
										</a>
										
									</c:when>

								</c:choose>
								
							</td>
						</tr>
						
					</c:forEach>
					
					<c:if test="${paging.total == 0 }">
						<tr class="ct_list_pop">
							<table>
								<tr>
									<td></td>
									<td align="center">
										<h3>�����̷��� �����ϴ�.</h3>
									</td>
								</tr>
							</table>
						</tr>
					</c:if>
					
				</table>
			
			<input type="hidden" name="page" value="${paging.currentPage}">
			<jsp:include page="../common/pagingNavigator.jsp"></jsp:include>
		
		</div>
		
		<br><br>
		
		<%	System.out.println("\tinclude �߻�: listPurchaseHistory.jsp"); %>
		<%--<jsp:include page="listPurchaseHistory.jsp"></jsp:include>--%>
		<%@ include file="listPurchaseHistory.jsp" %>
		
		</form>
		
	</body>
</html>
