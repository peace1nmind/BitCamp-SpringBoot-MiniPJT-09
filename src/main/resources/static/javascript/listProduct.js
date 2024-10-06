// product/listProduct

function fncGetProductList(){
	var bigger = document.detailForm.searchPriceBigger;
	var less = document.detailForm.searchPriceLess;
	if (bigger.value - less.value > 0 && bigger.value - less.value != bigger.value) {
		alert("가격 범위가 올바르지 않습니다");
		document.detailForm.searchPriceBigger.focus();
		document.detailForm.searchPriceBigger.setSelectionRange(document.detailForm.searchPriceBigger.value.length, document.detailForm.searchPriceBigger.value.length);
	} else {
		$("#page1").val(1);
		document.detailForm.submit();
	}
}


$(function() {
	
	var menu = $("input[name='menu'").val();
		
	$("form").attr("action", "/product/listProduct").attr("method", "post");
	
	
	// 가격 정렬 함수
	$("#orderByPrice").click(function() {
		var desc = $("input[name='desc']").val();
		desc = (desc==null || desc=="false")? false: true;

		$("#page1").val(1);
		$("#page2").val(2);
		
		var orderBy = $("input[name='orderBy']").val();
		
		if (orderBy == null || orderBy == '') {
			$("input[name='orderBy']").val("price");
		}
		
		$("input[name='desc']").val(!desc);
		$("form").submit();
	});
	
	
	// getProduct
	$(".ct_list_pop span[data-prodno]:nth-child(2n+1)").click(function() {
		var prodNo = $(this).data("prodno");
		
		console.log("listProduct - prodNo : "+prodNo);
		var navi = (menu !=null && menu == "search")? "getProduct" : "updateProduct";
		linkTo("/product/"+navi+"?menu=search&prodNo="+prodNo);
	});
	
	
	// 토글을 눌러서 상품정보 간편보기 (Ajax 사용)
	$(".ct_list_pop span[data-prodno]:nth-child(2n+2)").click(function() {
		var prodNo = $(this).data("prodno");
		
		$.ajax({
			url : "/product/json/getProduct",
			method : "GET",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			data : "prodNo="+prodNo,
			dataType : "json",
			success : function(data) {

				console.log("ajax success");
				
				var product = data.product;
				console.log(JSON.stringify(product));
				var display = "<table class='toggle' border='1'>"+
								"<tr>"+
									"<td rowspan='5'><img src='/images/uploadFiles/"+product.fileName+"' width='100' height='100'/></td>"+
									"<td>상품명</td>"+
									"<td>"+product.prodName+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td>상품상세정보</td>"+
									"<td>"+((product.prodDetail == null)? "&nbsp;": product.prodDetail)+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td>제조일자</td>"+
									"<td>"+toDateType(product.manuDate)+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td>가격</td>"+
									"<td>"+(product.price).toLocaleString()+"&nbsp;원</td>"+
								"</tr>"+
								"<tr>"+
									"<td>등록일자</td>"+
									"<td>"+((product.regDate == null)? "&nbsp;" : product.regDate)+"</td>"+
								"</tr>"+
							"</table>";

				$("table.toggle").remove();
				
				$("#"+product.prodNo+" td").append($(display));
							
			}
			
		});
		
	});
	
	
	// 검색버튼 함수
	$(".ct_btn01").click(function() {
		if ($(this).text().trim() == "검색"){
			fncGetProductList();
		}
	});
	
	
	// 엔터 검색
	$(document).on("keydown", function(event) {
		if (event.key == 'Enter') {
			fncGetProductList();
		}
	});
	
});
