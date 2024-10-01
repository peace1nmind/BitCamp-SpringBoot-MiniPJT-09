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
	
	$("#orderByPrice").click(function() {
		var desc = $("input[name='desc']").val();
		desc = (desc==null || desc=="false")? false: true;

		$("#page1").val(1);
		$("#page2").val(2);
		$("input[name='orderBy']").val("price");
		$("input[name='desc']").val(!desc);
		$("form").submit();
	});
	
	$(".ct_list_pop span").click(function() {
		var prodNo = $(this).data("prodno");
		
		if ($(this).data("menu") != undefined) {
			menu = $(this).data("menu");
		}
		
		console.log("listProduct - prodNo : "+prodNo);
		var navi = (menu !=null && menu == "search")? "getProduct" : "updateProduct";
		linkTo("/product/"+navi+"?menu=search&prodNo="+prodNo);
	});
	
	$(".ct_btn01").click(function() {
		if ($(this).text().trim() == "검색"){
			fncGetProductList();
		}
	});
	
});
