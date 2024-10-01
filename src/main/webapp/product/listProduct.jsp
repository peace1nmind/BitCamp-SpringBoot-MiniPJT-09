<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>

<html>

	<head>
	
		<title>${title}</title>
		
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
		<link rel="stylesheet" href="/css/commonCSS.css" type="text/css">
		
		<script src="https://code.jquery.com/jquery-2.2.4.js" 
				integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" 
				crossorigin="anonymous"></script>
		
		<script type="text/javascript" src="/javascript/common.js"></script>
		<script type="text/javascript" src="/javascript/listProduct.js"></script>
		
		<style type="text/css">
			span[data-prodno], #orderByPrice:hover {cursor: pointer;}
		</style>
		
	</head>

	<body bgcolor="#ffffff" text="#000000">
		<form name="detailForm">
			
			<div style="width:98%; margin-left:10px;">

				<input type="hidden" name="menu" value="${menu }">
				<input type="hidden" name="orderBy" value="${search.orderBy }">
				<input type="hidden" name="desc" value="${search.desc }">
			
				<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
					<tr>
						<td width="15" height="37">
							<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
						</td>
						<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="93%" class="ct_ttl01">
									
											${title}
									
									</td>
								</tr>
							</table>
						</td>
						<td width="12" height="37">
							<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
						</td>
					</tr>
				</table>
				
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
					<tr>
						<td align="right">
							<select name="searchCondition" class="ct_input_g" style="width:80px">
								
								<option value="0" ${(!empty search.searchCondition && search.searchCondition=='0')? "selected":""}>
									상품번호
								</option>
								<option value="1" ${(!empty search.searchCondition && search.searchCondition=='1')? "selected":""}>
									상품명
								</option>
								<option value="2" ${(!empty search.searchCondition && search.searchCondition=='2')? "selected":""}>
									상품설명
								</option>
								
							</select>
							
							<input type="text" name="searchKeyword" value="${search.searchKeyword}" 
									class="ct_input_g" style="width:200px; height:19px" />
							
						</td>
						
						<td align="right" width="70">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="17" height="23">
										<img src="/images/ct_btnbg01.gif" width="17" height="23">
									</td>
									<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
										검색
									</td>
									<td width="14" height="23">
										<img src="/images/ct_btnbg03.gif" width="14" height="23">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
					<tr>
						<td align="right">
							<input type="number" name="searchPriceBigger" value="${search.searchPriceBigger}" 
									class="ct_input_g" style="width:200px; height:19px" />
							~
							<input type="number" name="searchPriceLess" value="${search.searchPriceLess}" 
									class="ct_input_g" style="width:200px; height:19px" />
							
						</td>
						
						<td align="right" width="70">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="17" height="23">
										<img src="/images/ct_btnbg01.gif" width="17" height="23">
									</td>
									<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
										검색
									</td>
									<td width="14" height="23">
										<img src="/images/ct_btnbg03.gif" width="14" height="23">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
					<tr>
						<td colspan="11" >전체 ${paging.total} 건수, 현재 ${paging.currentPage} 페이지</td>
					</tr>
					<tr>
						<td class="ct_list_b" width="100">No</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="150">상품명</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="150">
							가격&nbsp;
							<span id="orderByPrice">↑↓</span>
						<td class="ct_line02"></td>
						<td class="ct_list_b">등록일</td>	
						<td class="ct_line02"></td>
						<td class="ct_list_b">현재상태</td>	
					</tr>
					<tr>
						<td colspan="11" bgcolor="808285" height="1"></td>
					</tr>
					
					<c:forEach items="${map.list}" var="product" varStatus="status">
					
						<tr class="ct_list_pop">
							<td align="center">${status.count}</td>
							<td></td>
									
							<td align="left" id="prodName">
								<span data-prodno="${product.prodNo }">${product.prodName}</span>&ensp;&ensp;<span data-prodno="${product.prodNo }">▼</span>
							</td>
							
							<td></td>
							<td align="left" id="productPrice"><span>${product.price}</span> 원</td>
							<td></td>
							<td align="left">${product.regDate}</td>
							<td></td>
							
							<td align="left">
								${tranCodeMap[product.proTranCode]}
							</td>
		
						</tr>
						
						<tr id="${product.prodNo }"><td colspan="10"></td></tr>
						
						<tr>
							<td colspan="11" bgcolor="D6D7D6" height="1"></td>
						</tr>	
						
					</c:forEach>
					
				</table>
				
				<c:if test="${paging.total == 0 }">
					<tr class="ct_list_pop">
						<table>
							<tr>
								<td></td>
								<td align="left">
									<c:if test="${!empty search.searchKeyword }">
										<h3>"${search.searchKeyword}"로 검색한 결과</h3>
									</c:if>
									<h3>판매중인 상품이 없습니다.</h3>
								</td>
							</tr>
						</table>
					</tr>
				</c:if>
				
				<input type="hidden" name="page" value="${paging.currentPage}" id="page1">
	<%-- 			<jsp:include page="../common/pagingNavigator.jsp"></jsp:include> --%>
				<%@ include file="../common/pagingNavigator.jsp" %>
			
			</div>

		<br><br>
		
		<c:if test="${menu=='manage'}">
			<%	System.out.println("\tinclude 발생: listSale.jsp"); %>
			<jsp:include page="/purchase/listSale.jsp"></jsp:include>
<%-- 			<%@ include file="../purchase/listSale.jsp" %> --%>
		</c:if>
		
		</form>
		
	</body>
</html>
