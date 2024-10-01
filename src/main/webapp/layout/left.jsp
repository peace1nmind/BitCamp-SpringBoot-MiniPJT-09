<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="EUC-KR">
	
	<title>Model2 MVC Shop</title>

	<link href="/css/left.css" rel="stylesheet" type="text/css">
	
	<!-- CDN(Content Delivery Network) ȣ��Ʈ ��� -->
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="/javascript/common.js"></script>
	<script type="text/javascript" src="/javascript/left.js"></script>
	
	<script type="text/javascript">
		var userId = "${user.userId}";
	</script>
		 
 </head>

 <body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >

 <table width="159" border="0" cellspacing="0" cellpadding="0">

<!--menu 01 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159" >	
			<tr>
				<c:if test="${ !empty user }">
					<tr>
						<td class="Depth03">
<%-- 							<a href="/user/getUser?userId=${user.userId}" target="rightFrame">����������ȸ</a>	 --%>
							����������ȸ
						</td>
					</tr>
				</c:if>
			
				<c:if test="${user.role == 'admin'}">
					<tr>
						<td class="Depth03" >
<!-- 							<a href="/user/listUser" target="rightFrame">ȸ��������ȸ</a>	 -->
							ȸ��������ȸ
						</td>
					</tr>
				</c:if>
			
				<tr>
					<td class="DepthEnd">&nbsp;</td>
				</tr>
		</table>
	</td>
</tr>

<!--menu 02 line-->
<c:if test="${user.role == 'admin'}">
	<tr>
		<td valign="top"> 
			<table  border="0" cellspacing="0" cellpadding="0" width="159">
				<tr>
					<td class="Depth03">
<!-- 						<a href="/product/addProduct" target="rightFrame">�ǸŻ�ǰ���</a> -->
						�ǸŻ�ǰ���
					</td>
				</tr>
				<tr>
					<td class="Depth03">
<!-- 						<a href="/product/listProduct?menu=manage"  target="rightFrame">�ǸŻ�ǰ����</a> -->
						�ǸŻ�ǰ����
					</td>
				</tr>
				<tr>
					<td class="DepthEnd">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</c:if>

<!--menu 03 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<tr>
				<td class="Depth03">
<!-- 					<a href="/product/listProduct?menu=search" target="rightFrame">�� ǰ �� ��</a> -->
					�� ǰ �� ��
				</td>
			</tr>
			
			<c:if test="${ !empty user && user.role == 'user'}">
			<tr>
				<td class="Depth03">
<!-- 					<a href="/purchase/listPurchase"  target="rightFrame">�����̷���ȸ</a> -->
					�����̷���ȸ
				</td>
			</tr>
			</c:if>
			
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
<!-- 					<a href="javascript:history()">�ֱ� �� ��ǰ</a> -->
					�ֱ� �� ��ǰ
				</td>
			</tr>
		</table>
	</td>
</tr>

<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>

<!--menu 04 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<tr>
				<td class="Depth03">
					TODO
				</td>
			</tr>
			
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			
		</table>
	</td>
</tr>

</table>

</body>

</html>