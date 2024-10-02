// /purchase/listSale

function fncGetSaleList(page) {

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
	
});