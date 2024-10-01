<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>

<title>구매완료 목록</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link rel="stylesheet" href="/css/commonCSS.css" type="text/css">

<script src="https://code.jquery.com/jquery-2.2.4.js"
	integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
	crossorigin="anonymous"></script>

<script type="text/javascript" src="/javascript/common.js"></script>
<script type="text/javascript" src="/javascript/listSale.js"></script>

</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<table width="100%" height="37" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
					width="15" height="37" /></td>
				<td background="/images/ct_ttl_img02.gif" width="100%"
					style="padding-left: 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">상품관리 (판매후)</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
					width="12" height="37" /></td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="margin-top: 10px;">

			<tr>
				<td colspan="11">전체 ${salePaging.total } 건수, 현재
					${salePaging.currentPage } 페이지</td>
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

			<c:forEach var="purchase" items="${saleMap.list }" varStatus="status">
				<tr class="ct_list_pop">
					<td align="center">${status.count }</td>

					<td></td>
					<%-- 상품명 --%>
					<td align="left">
						<span data-prodno="${purchase.purchaseProd.prodNo }" data-menu="search">${purchase.purchaseProd.prodName }</span>
					</td>

					<td></td>

					<%-- 받는사람 --%>
					<td align="left">${purchase.receiverName }</td>

					<td></td>

					<%-- 전화번호 --%>
					<td align="left">${purchase.receiverPhone }</td>

					<td></td>

					<%-- 구매일자 --%>
					<td align="left">${purchase.orderDate }</td>

					<td></td>

					<%-- 배송현황 --%>
					<td align="left">${tranCodeMap[purchase.tranCode] }</td>

					<td></td>

					<%-- 정보수정(배송하기) --%>
					<td align="left" class="dlvyInfo">
						<c:if test="${menu=='manage' && purchase.purchaseProd.proTranCode=='2' }">
							&nbsp;
							<span data-prodno="${purchase.purchaseProd.prodNo }">배송하기</span> 
						</c:if>
					</td>
				</tr>

			</c:forEach>

		</table>

		<c:if test="${salePaging.total == 0 }">
			<tr class="ct_list_pop">
				<table>
					<tr>
						<td></td>
						<td align="left"><c:if test="${!empty search.searchKeyword }">
								<h3>"${search.searchKeyword}"로 검색한 결과</h3>
							</c:if>
							<h3>배송할(구매완료 상태의) 상품이 없습니다.</h3></td>
					</tr>
				</table>
			</tr>
		</c:if>

		<input type="hidden" name="salePage" value="${salePaging.currentPage}"
			id="page2">

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="margin-top: 10px;">

			<tr>
				<td align="center"><a href="javascript:fncGetSaleList(1)"
					${(salePaging.left)? "":"style='visibility:hidden'" }> <span>◀</span>
				</a> &nbsp; <a
					href="javascript:fncGetSaleList('${salePaging.start - 1 }')"
					${(salePaging.left)? "":"style='visibility:hidden'" }> <span>이전</span>
				</a> &nbsp;&nbsp; <c:forEach begin="${salePaging.start }"
						end="${salePaging.end }" varStatus="status">

						<a href="javascript:fncGetSaleList('${status.count }')"
							${(salePaging.currentPage==status.count)? "style='font-weight: bold; font-size: 15px'" : "" }>
							${status.count } </a>

					</c:forEach> &nbsp;&nbsp; <a
					href="javascript:fncGetSaleList('${salePaging.end + 1 }')"
					${(salePaging.right)? "":"style='visibility:hidden'" }>> <span>다음</span>
				</a> &nbsp; <a
					href="javascript:fncGetSaleList('${salePaging.totalPage }')"
					${(salePaging.right)? "":"style='visibility:hidden'" }> <span>▶</span>
				</a></td>
			</tr>

		</table>

		<!--  페이지 Navigator 끝 -->
		<!-- 			</form> -->

	</div>

	<br>
	<br>

</body>
</html>
