// /purchase/listPurchase

function fncUpdateTranCode(tranNo, tranCode) {
	if(confirm("배송정보를 변경하시겠습니까?\n\n배송중 → 배송완료")) {
		linkTo("/purchase/updateTranCode?tranNo="+tranNo+"&tranCode="+tranCode);
	}
}

$(function() {
	
	$("form").attr("action", "/purchase/listPurchase").attr("method", "post");
	
	$(".getPurchase").on('click', function() {
		var tranNo = $(this).data("tranno");
		console.log("listPurchase - getPurchase - tranNo : "+tranNo);
		
		linkTo("/purchase/getPurchase?tranNo="+tranNo);
	});
	
	$(".updateTranCode").on('click', function() {
		var tranNo = $(this).data("tranno");
		var tranCode = $(this).data("trancode");
		
		fncUpdateTranCode(tranNo, tranCode);
	});
	
});

