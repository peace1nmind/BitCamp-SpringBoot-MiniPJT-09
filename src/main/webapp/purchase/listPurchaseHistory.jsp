<%-- 구매완료목록 화면 --%>
<%-- listPurchaseHistory.do --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<html>

	<head>
	
		<title>배송완료목록 조회</title>
	
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
		<link rel="stylesheet" href="/css/commonCSS.css" type="text/css">
		
		<script src="https://code.jquery.com/jquery-2.2.4.js" 
				integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" 
				crossorigin="anonymous"></script>
		
		<script type="text/javascript" src="/javascript/paging.js"></script>
		<script type="text/javascript" src="/javascript/listPurchaseHistory.js"></script>
		
	</head>
	
	<body bgcolor="#ffffff" text="#000000">
	
		<div style="width: 98%; margin-left: 10px;">
				
			<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
			
				<tr>
					<td colspan="11">전체 ${historyMap.count } 건수, 현재 ${historyPaging.currentPage } 페이지</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="50">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="100">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="100">받는사람</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">전화번호</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">구매일자</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">배송현황</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">정보수정</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				
				<c:forEach var="purchase" items="${historyMap.list }" varStatus="status">
					<tr class="ct_list_pop">
						<td align="center">
							${status.count }
						</td>
						
						<td></td>
						<%-- 상품명 --%>
						<td align="left">
<%-- 							<a href="/purchase/getPurchase?tranNo=${purchase.tranNo }"> --%>
<%-- 								${purchase.purchaseProd.prodName } --%>
<!-- 							</a> -->
							<span class="getPurchase" data-tranno="${purchase.tranNo }">${purchase.purchaseProd.prodName }</span>
						</td>
						
						<td></td>
						
						<%-- 받는사람 --%>
						<td align="left">
							${purchase.receiverName }
						</td>
						
						<td></td>
						
						<%-- 전화번호 --%>
						<td align="left">
							${purchase.receiverPhone }
						</td>
						
						<td></td>
						
						<%-- 구매일자 --%>
						<td align="left">
							${purchase.orderDate }
						</td>
						
						<td></td>
						
						<%-- 배송현황 --%>
						<td align="left">
							${tranCodeMap[purchase.tranCode] }
						</td>
						
						<td></td>
						
						<%-- 정보수정(구매확정) --%>
						<td align="left">
						
							<c:if test="${purchase.tranCode=='4' }">
<%-- 								<a href="/purchase/updateTranCode?tranNo=${purchase.tranNo}&tranCode=5"> --%>
<!-- 									구매확정 -->
<!-- 								</a> -->
								<span class="updateTranCode" data-tranno="${purchase.tranNo }" data-trancode="5">구매확정</span>
							</c:if>
							
						</td>
					</tr>
					
				</c:forEach>
				
				
				<c:if test="${historyPaging.total == 0 }">
					<tr class="ct_list_pop">
						<table>
							<tr>
								<td></td>
								<td align="center">
									<h3>배송완료 이력이 없습니다.</h3>
								</td>
							</tr>
						</table>
					</tr>
				</c:if>

			</table>
			
			<input type="hidden" name="historyPage" value="${historyPaging.currentPage}">
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;" id="historyPaging">

				<tr>
					<td align="center">
					
<!-- 						<a href="javascript:fncGetHistoryList(1)" -->
<%-- 							${(historyPaging.left)? "":"style='visibility:hidden'" }> --%>
						<a id="firstPage"
							${(historyPaging.left)? "":"style='visibility:hidden'" }>
							<span>◀</span>
						</a>
	
						&nbsp;
						
<%-- 						<a href="javascript:fncGetHistoryList('${historyPaging.start - 1 }')" --%>
<%-- 							${(historyPaging.left)? "":"style='visibility:hidden'" }> --%>
						<a id ="prevPage" data-page="${historyPaging.start - 1 }" 
							${(historyPaging.left)? "":"style='visibility:hidden'" }> 
							<span>이전</span>
						</a>
	
						&nbsp;&nbsp;
	
						<c:forEach begin="${historyPaging.start }" end="${historyPaging.end }" varStatus="status">
							
<%-- 							<a href="javascript:fncGetHistoryList('${status.count }')"  --%>
<%-- 								${(historyPaging.currentPage==status.count)? "style='font-weight: bold; font-size: 15px'" : "" }> --%>
							<a id="goPage" data-page="${status.count }"
								${(historyPaging.currentPage==status.count)? "style='font-weight: bold; font-size: 15px'" : "" }>
								<span>${status.count }</span>
							</a>
	
						</c:forEach>
	
						&nbsp;&nbsp;
						
<%-- 						<a href="javascript:fncGetHistoryList('${historyPaging.end + 1 }')"  --%>
<%-- 							${(historyPaging.right)? "":"style='visibility:hidden'" }>> --%>
						<a id="nextPage" data-page="${historyPaging.end + 1 }"
							${(historyPaging.right)? "":"style='visibility:hidden'" }> 
							<span>다음</span>
						</a>
	
						&nbsp;
						
<%-- 						<a href="javascript:fncGetHistoryList('${historyPaging.totalPage }')" --%>
<%-- 							${(historyPaging.right)? "":"style='visibility:hidden'" }> --%>
						<a id="lastPage" data-page="${historyPaging.totalPage }"
							${(historyPaging.right)? "":"style='visibility:hidden'" }> 
							<span>▶</span>
						</a>
						
					</td>
				</tr>
	
			</table>
		
		<!--  페이지 Navigator 끝 -->
		
		</div>

		<br><br>
		
	</body>
</html>
