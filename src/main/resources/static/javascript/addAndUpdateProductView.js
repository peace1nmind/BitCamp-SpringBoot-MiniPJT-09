// /product/addProductView

function productCheck(fnc){
	
	//Form 유효성 검증
 	var name = $("input[name='prodName']").val();
	var detail = $("input[name='prodDetail']").val();
	var manuDate = $("input[name='manuDate']").val();
	var price = $("input[name='price']").val();

	if(name == null || name.length<1){
		alert("상품명은 반드시 입력하여야 합니다.");
		return;
	}
	if(detail == null || detail.length<1){
		alert("상품상세정보는 반드시 입력하여야 합니다.");
		return;
	}
	if(manuDate == null || manuDate.length<1){
		alert("제조일자는 반드시 입력하셔야 합니다.");
		return;
	}
	if(price == null || price.length<1){
		alert("가격은 반드시 입력하셔야 합니다.");
		return;
	}

	switch(fnc) {
		case "add":
			$("form").attr("action", "/product/addProduct");
			break;
		
		case "update":
			$("form").attr("action", "/product/updateProduct");
			break;
	}
	
	console.log("action : "+$("form").attr("action"));
	
	$("form").submit();
}


function resetData(){
	$("form")[0].reset();
}


$(function() {
	
	var fnc = $("input[name='fnc']").val();
	console.log("fnc : "+fnc);
	
	$("input[name='manuDate']").val(toDateType($("input[name='manuDate']").val()));
	
	$("#calendar").on('click', function() {
		show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value);
	});
	
	$("input[name='manuDate']").on('focus', function() {
		$("input[name='manuDate']").blur();
		show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value);
	});
	
	$(".ct_btn01").on('click', function() {
		var text = $(this).text().replace(/\s/g, "");
		
		switch(text) {
			case "등록":
				productCheck(fnc);
				break;
			
			case "수정":
				productCheck(fnc);
				break;
			
			case "초기화":
				resetData();
				break;
				
			case "이전":
				history.back();
				break;
		}
	});
	
});
