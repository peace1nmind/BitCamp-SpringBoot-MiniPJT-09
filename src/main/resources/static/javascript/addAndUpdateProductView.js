// /product/addProductView

function productCheck(fnc){
	
	//Form ��ȿ�� ����
 	var name = $("input[name='prodName']").val();
	var detail = $("input[name='prodDetail']").val();
	var manuDate = $("input[name='manuDate']").val();
	var price = $("input[name='price']").val();

	if(name == null || name.length<1){
		alert("��ǰ���� �ݵ�� �Է��Ͽ��� �մϴ�.");
		return;
	}
	if(detail == null || detail.length<1){
		alert("��ǰ�������� �ݵ�� �Է��Ͽ��� �մϴ�.");
		return;
	}
	if(manuDate == null || manuDate.length<1){
		alert("�������ڴ� �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	if(price == null || price.length<1){
		alert("������ �ݵ�� �Է��ϼž� �մϴ�.");
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
			case "���":
				productCheck(fnc);
				break;
			
			case "����":
				productCheck(fnc);
				break;
			
			case "�ʱ�ȭ":
				resetData();
				break;
				
			case "����":
				history.back();
				break;
		}
	});
	
});
