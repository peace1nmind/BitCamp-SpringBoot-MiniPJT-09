// /product/addProduct.jsp

$(function() {
	
	// �������� YYYY-MM-DD �������� ����
	var manuDate = $("#productManuDate").text().replace(/\s/g, "");
	console.log("manuDate : "+manuDate);
	$("#productManuDate").text(toDateType(manuDate));
	
	// ��ǰ����ȸ�� ��ư
	$(".getProduct .ct_btn01").on('click', function() {
		var text = $(this).text().replace(/\s/g, "");
		
		switch(text) {
			case "����":
				linkTo("/product/updateProduct?prodNo="+prodNo);
				break;
			
			case "����":
				linkTo("/purchase/addPurchase?prodNo="+prodNo);
				break;
				
			case "����":
				history.back();
				break;
		}
		
	});
	
	// �ǸŻ�ǰ��� ���� ��ư
	$('.addProduct .ct_btn01').on('click', function() {
		var text = $(this).text().replace(/\s/g, "");
		
		switch(text) {
			case "Ȯ��":
				linkTo("/product/listProduct?menu=manage");
				break;
			
			case "�߰����":
				linkTo("/product/addProduct");
				break;
		}
		
	});
	
});
