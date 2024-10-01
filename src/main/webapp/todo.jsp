<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<html>

	<head>
		<meta charset="EUC-KR">
		<title>해야할 코딩</title>
		
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
			        
			        // text에 √가 포함되어 있으면 글자색을 회색으로 변경
			        if (text.includes("√")) {
			            item.style.color = "gray";
			        }
			    });
			});
		</script>
		
	</head>
	
	<body>
		
	<!-- 	<ol></ol> : 숫자 -->
	<!-- 	<ul></ul> : - -->
		<br><br>
		
		<table id="header">
			<tr>
				<td>
					<h3>해야할 코딩 목록</h3>
				</td>
			</tr>
			
<!-- 			<tr> -->
<!-- 				<td> -->
<!-- 					<input type="text" name ="todo">&ensp;<input type="button" value="추가"> -->
<!-- 				</td> -->
<!-- 			</tr> -->
		</table>
		
		<br><br>
		
		<ul>
			<li>product, purchase에 대한 intercepter 만들기</li>
			<li>√ Spring MVC 파일 업로드</li>
			<li>08 리펙토링 RestController 완성하기</li>
			<li>
				10 리펙토링 Ajax 적용
				<ul>
					<li>list 내용 주고 받기</li>
					<li>무한 스크롤 (추가기능)</li>
					<li>product, purchase list에서 ▼ 버튼 누르면 내용 펼치기</li>
				</ul>
			</li>
		</ul>
		
		<table class='toggle' border='1'>
			<tr>
				<td rowspan='5'><img src='/images/uploadFiles/product.fileName' width='100' height='100'/></td>
				<td>상품명</td>
				<td>product.prodName</td>
			</tr>
			<tr>
				<td>상품상세정보</td>
				<td>product.prodDetail</td>
			</tr>
			<tr>
				<td>제조일자</td>
				<td>product.manuDate</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>(product.price).toLocaleString()&nbsp;원</td>
			</tr>
			<tr>
				<td>등록일자</td>
				<td>product.regDate</td>
			</tr>
		</table>
		
	</body>
	
</html>