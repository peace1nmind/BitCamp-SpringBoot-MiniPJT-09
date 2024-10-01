<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="EUC-KR">
		<title>�ؾ��� �ڵ�</title>
		
		<style type="text/css">
			li {
				margin: 20px 0px;
			}
			
			#header {
				width: 100%;
				text-align: center;
			}
		</style>
		
		<script type="text/javascript">
			document.addEventListener("DOMContentLoaded", function() {
			    var listItems = document.querySelectorAll("ul li");
			    
			    listItems.forEach(function(item) {
			        var text = item.textContent.trim();
			        
			        // text�� � ���ԵǾ� ������ ���ڻ��� ȸ������ ����
			        if (text.includes("��")) {
			            item.style.color = "gray";
			        }
			    });
			});
		</script>
		
	</head>
	
	<body>
		
	<!-- 	<ol></ol> : ���� -->
	<!-- 	<ul></ul> : - -->
		<br><br>
		
		<table id="header">
			<tr>
				<td>
					<h3>�ؾ��� �ڵ� ���</h3>
				</td>
			</tr>
			
<!-- 			<tr> -->
<!-- 				<td> -->
<!-- 					<input type="text" name ="todo">&ensp;<input type="button" value="�߰�"> -->
<!-- 				</td> -->
<!-- 			</tr> -->
		</table>
		
		<br><br>
		
		<ul>
			<li>product, purchase�� ���� intercepter �����</li>
			<li>�� Spring MVC ���� ���ε�</li>
			<li>08 �����丵 RestController �ϼ��ϱ�</li>
			<li>
				10 �����丵 Ajax ����
				<ul>
					<li>list ���� �ְ� �ޱ�</li>
					<li>���� ��ũ�� (�߰����)</li>
					<li>product, purchase list���� �� ��ư ������ ���� ��ġ��</li>
				</ul>
			</li>
		</ul>
		
		<table class='toggle' border='1'>
			<tr>
				<td rowspan='5'><img src='/images/uploadFiles/product.fileName' width='100' height='100'/></td>
				<td>��ǰ��</td>
				<td>product.prodName</td>
			</tr>
			<tr>
				<td>��ǰ������</td>
				<td>product.prodDetail</td>
			</tr>
			<tr>
				<td>��������</td>
				<td>product.manuDate</td>
			</tr>
			<tr>
				<td>����</td>
				<td>(product.price).toLocaleString()&nbsp;��</td>
			</tr>
			<tr>
				<td>�������</td>
				<td>product.regDate</td>
			</tr>
		</table>
		
	</body>
	
</html>