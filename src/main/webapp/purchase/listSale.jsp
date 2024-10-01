<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>

<title>���ſϷ� ���</title>

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
							<td width="93%" class="ct_ttl01">��ǰ���� (�Ǹ���)</td>
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
				<td colspan="11">��ü ${salePaging.total } �Ǽ�, ����
					${salePaging.currentPage } ������</td>
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

			<c:forEach var="purchase" items="${saleMap.list }" varStatus="status">
				<tr class="ct_list_pop">
					<td align="center">${status.count }</td>

					<td></td>
					<%-- ��ǰ�� --%>
					<td align="left">
						<span data-prodno="${purchase.purchaseProd.prodNo }" data-menu="search">${purchase.purchaseProd.prodName }</span>
					</td>

					<td></td>

					<%-- �޴»�� --%>
					<td align="left">${purchase.receiverName }</td>

					<td></td>

					<%-- ��ȭ��ȣ --%>
					<td align="left">${purchase.receiverPhone }</td>

					<td></td>

					<%-- �������� --%>
					<td align="left">${purchase.orderDate }</td>

					<td></td>

					<%-- �����Ȳ --%>
					<td align="left">${tranCodeMap[purchase.tranCode] }</td>

					<td></td>

					<%-- ��������(����ϱ�) --%>
					<td align="left" class="dlvyInfo">
						<c:if test="${menu=='manage' && purchase.purchaseProd.proTranCode=='2' }">
							&nbsp;
							<span data-prodno="${purchase.purchaseProd.prodNo }">����ϱ�</span> 
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
								<h3>"${search.searchKeyword}"�� �˻��� ���</h3>
							</c:if>
							<h3>�����(���ſϷ� ������) ��ǰ�� �����ϴ�.</h3></td>
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
					${(salePaging.left)? "":"style='visibility:hidden'" }> <span>��</span>
				</a> &nbsp; <a
					href="javascript:fncGetSaleList('${salePaging.start - 1 }')"
					${(salePaging.left)? "":"style='visibility:hidden'" }> <span>����</span>
				</a> &nbsp;&nbsp; <c:forEach begin="${salePaging.start }"
						end="${salePaging.end }" varStatus="status">

						<a href="javascript:fncGetSaleList('${status.count }')"
							${(salePaging.currentPage==status.count)? "style='font-weight: bold; font-size: 15px'" : "" }>
							${status.count } </a>

					</c:forEach> &nbsp;&nbsp; <a
					href="javascript:fncGetSaleList('${salePaging.end + 1 }')"
					${(salePaging.right)? "":"style='visibility:hidden'" }>> <span>����</span>
				</a> &nbsp; <a
					href="javascript:fncGetSaleList('${salePaging.totalPage }')"
					${(salePaging.right)? "":"style='visibility:hidden'" }> <span>��</span>
				</a></td>
			</tr>

		</table>

		<!--  ������ Navigator �� -->
		<!-- 			</form> -->

	</div>

	<br>
	<br>

</body>
</html>
