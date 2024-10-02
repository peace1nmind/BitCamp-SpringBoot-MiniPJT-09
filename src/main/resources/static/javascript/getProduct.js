// /product/addProduct.jsp

$(function() {
	
	// 제조일자 YYYY-MM-DD 형식으로 변경
	var manuDate = $("#productManuDate").text().replace(/\s/g, "");
	console.log("manuDate : "+manuDate);
	$("#productManuDate").text(toDateType(manuDate));
	
	// 상품상세조회의 버튼
	$(".getProduct .ct_btn01").on('click', function() {
		var text = $(this).text().replace(/\s/g, "");
		
		switch(text) {
			case "수정":
				linkTo("/product/updateProduct?prodNo="+prodNo);
				break;
			
			case "구매":
				linkTo("/purchase/addPurchase?prodNo="+prodNo);
				break;
				
			case "이전":
				history.back();
				break;
		}
		
	});
	
	// 판매상품등록 후의 버튼
	$('.addProduct .ct_btn01').on('click', function() {
		var text = $(this).text().replace(/\s/g, "");
		
		switch(text) {
			case "확인":
				linkTo("/product/listProduct?menu=manage");
				break;
			
			case "추가등록":
				linkTo("/product/addProduct");
				break;
		}
		
	});
	
});
