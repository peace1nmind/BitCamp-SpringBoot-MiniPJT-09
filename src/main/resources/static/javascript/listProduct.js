// product/listProduct

function fncGetProductList(){
	var bigger = document.detailForm.searchPriceBigger;
	var less = document.detailForm.searchPriceLess;
	if (bigger.value - less.value > 0 && bigger.value - less.value != bigger.value) {
		alert("���� ������ �ùٸ��� �ʽ��ϴ�");
		document.detailForm.searchPriceBigger.focus();
		document.detailForm.searchPriceBigger.setSelectionRange(document.detailForm.searchPriceBigger.value.length, document.detailForm.searchPriceBigger.value.length);
	} else {
		$("#page1").val(1);
		document.detailForm.submit();
	}
}


$(function() {
	
	var menu = $("input[name='menu'").val();
		
	$("#productPrice span").each(function() {
		var price = Number($(this).text().trim()).toLocaleString();
		$(this).text(price);
	});
	
	$("form").attr("action", "/product/listProduct").attr("method", "post");
	
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
	
	$(".ct_list_pop span[data-prodno]").click(function() {
		var prodNo = $(this).data("prodno");
		
		if ($(this).data("menu") != undefined) {
			menu = $(this).data("menu");
		}
		
		console.log("listProduct - prodNo : "+prodNo);
		var navi = (menu !=null && menu == "search")? "getProduct" : "updateProduct";
		linkTo("/product/"+navi+"?menu=search&prodNo="+prodNo);
	});
	
	$(".ct_btn01").click(function() {
		if ($(this).text().trim() == "�˻�"){
			fncGetProductList();
		}
	});
	
});
