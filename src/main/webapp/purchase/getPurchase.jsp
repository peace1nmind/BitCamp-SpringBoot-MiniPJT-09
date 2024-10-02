<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
	
		<title>���Ż���ȸ</title>
		
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
		
		<script src="https://code.jquery.com/jquery-2.2.4.js" ></script>
		<script type="text/javascript" src="/javascript/common.js"></script>
	
	</head>
	
	<body bgcolor="#ffffff" text="#000000">
	
		<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15" height="37">
					<img src="/images/ct_ttl_img01.gif"	width="15" height="37"/>
				</td>
				<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">���Ż���ȸ</td>
							<td width="20%" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37">
					<img src="/images/ct_ttl_img03.gif"	width="12" height="37"/>
				</td>
			</tr>
		</table>
		
		<%-- ����Ұ��׸� --%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					��ǰ��ȣ <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="105">
								${purchase.purchaseProd.prodNo }
							</td>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					��ǰ�� <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<a href="/product/getProduct?prodNo=${purchase.purchaseProd.prodNo }">
					${purchase.purchaseProd.prodName }</a>
				</td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					�����ھ��̵� <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${purchase.buyer.userId }</td>
			</tr>
			
			<%-- ���氡���׸�--%>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
		
			<tr>
				<td width="104" class="ct_write">���Ź��</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					${(!empty purchase.paymentOption && purchase.paymentOption=='1')? "���ݱ���" : "�ſ뱸��" }
				</td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					�����ݾ� <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01" id="productPrice"><span>${purchase.purchaseProd.price }</span> ��</td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">�������̸�</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${purchase.receiverName }</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">�����ڿ���ó</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${purchase.receiverPhone }</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">�������ּ�</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${purchase.dlvyAddr }
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">���ſ�û����</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${purchase.dlvyRequest }</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">��������</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${purchase.dlvyDate }</td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
		
			<tr>
				<td width="104" class="ct_write">
					�ֹ��� <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${purchase.orderDate }</td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
			<tr>
				<td width="53%"></td>
				<td align="right">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<c:if test="${user.role == 'user' && purchase.tranCode < 3 }">
								<td width="17" height="23">
									<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
								</td>
							
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
									<a href="/purchase/updatePurchase?tranNo=${purchase.tranNo }">����</a>
								</td>
								<td width="14" height="23">
									<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
								</td>
							</c:if>
							
							<td width="30"></td>
							
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
								<a href="${(user.role == 'user')? '/purchase/listPurchase' : '/product/listProduct?menu=manage' }">Ȯ��</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif"width="14" height="23"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
	</body>
	
</html>
