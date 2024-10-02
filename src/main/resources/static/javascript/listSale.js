// /purchase/listSale

function fncGetSaleList(page) {

	if (page != null && page != 0) {
		$("#page2").val(page);
	}

	$("form").submit();

}


$(function() {
	
	$(".dlvyInfo span").click(function() {
		var prodNo = $(this).data("prodno");
		console.log("listSale - prodNo : "+prodNo);
		
		if ($(this).text()=="배송하기") {
			linkTo("/purchase/updateTranCode?tranCode=3&prodNo="+prodNo);
		}
	});
	
	
	$(".ct_list_pop span[data-tranno]:nth-child(2n+1)").click(function() {
		var tranNo = $(this).data("tranno");
		
		console.log("listSale - tranNo : "+tranNo);
		
		linkTo("/purchase/getPurchase?tranNo="+tranNo);
	});
	
});