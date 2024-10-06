// /purchase/listPurchase

function fncUpdateTranCode(tranNo, tranCode) {
	if(confirm("��������� �����Ͻðڽ��ϱ�?\n\n����� �� ��ۿϷ�")) {
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

