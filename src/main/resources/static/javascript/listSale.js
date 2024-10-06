// /purchase/listSale

function fncGetSaleList(page) {
	
	console.log("fncGetSaleList(page) : "+page);
	
	if (page != null && page != 0) {
		$("#page2").val(page);
	}

	$("form").submit();

}


$(function() {
	
	$(".dlvyInfo span").click(function() {
		var tranNo = $(this).data("tranno");
		console.log("updateTranCode - tranNo : "+tranNo);
		
		if ($(this).text()=="배송하기") {
			linkTo("/purchase/updateTranCode?tranCode=3&tranNo="+tranNo);
		}
	});
	
	
	$(".getPurchase").click(function() {
		var tranNo = $(this).data("tranno");
		
		console.log("listSale - tranNo : "+tranNo);
		console.log($(this).text());
		
		linkTo("/purchase/getPurchase?tranNo="+tranNo);
	});
	
	
//	$("#salePaging a").on('click', function() {
//		var id = $(this).attr("id");
//		var page = $(this).data("page");
//		
//		switch (id) {
//			case "firstPage":
//				fncGetSaleList(1);
//				break;
//			
//			case "prevPage":
//				fncGetSaleList(page);
//				break;
//				
//			case "goPage":
//				fncGetSaleList(page);
//				break;
//				
//			case "nextPage":
//				fncGetSaleList(page);
//				break;
//			
//			case "lastPage":
//				fncGetSaleList(page);
//				break;
//		}
//		
//	});

	pageNavigate("salePaging", fncGetSaleList);
	
});