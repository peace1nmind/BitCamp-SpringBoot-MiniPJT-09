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
		
	$("form").attr("action", "/product/listProduct").attr("method", "post");
	
	
	// ���� ���� �Լ�
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
	
	
	// getProduct
	$(".ct_list_pop span[data-prodno]:nth-child(2n+1)").click(function() {
		var prodNo = $(this).data("prodno");
		
		console.log("listProduct - prodNo : "+prodNo);
		var navi = (menu !=null && menu == "search")? "getProduct" : "updateProduct";
		linkTo("/product/"+navi+"?menu=search&prodNo="+prodNo);
	});
	
	
	// ����� ������ ��ǰ���� ������ (Ajax ���)
	$(".ct_list_pop span[data-prodno]:nth-child(2n+2)").click(function() {
		var prodNo = $(this).data("prodno");
		
		$.ajax({
			url : "/product/json/getProduct",
			method : "GET",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			data : "prodNo="+prodNo,
			dataType : "json",
			success : function(data) {

				console.log("ajax success");
				
				var product = data.product;
				console.log(JSON.stringify(product));
				var display = "<table class='toggle' border='1'>"+
								"<tr>"+
									"<td rowspan='5'><img src='/images/uploadFiles/"+product.fileName+"' width='100' height='100'/></td>"+
									"<td>��ǰ��</td>"+
									"<td>"+product.prodName+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td>��ǰ������</td>"+
									"<td>"+((product.prodDetail == null)? "&nbsp;": product.prodDetail)+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td>��������</td>"+
									"<td>"+toDateType(product.manuDate)+"</td>"+
								"</tr>"+
								"<tr>"+
									"<td>����</td>"+
									"<td>"+(product.price).toLocaleString()+"&nbsp;��</td>"+
								"</tr>"+
								"<tr>"+
									"<td>�������</td>"+
									"<td>"+((product.regDate == null)? "&nbsp;" : product.regDate)+"</td>"+
								"</tr>"+
							"</table>";

				$("table.toggle").remove();
				
				$("#"+product.prodNo+" td").append($(display));
							
			}
			
		});
		
	});
	
	
	// �˻���ư �Լ�
	$(".ct_btn01").click(function() {
		if ($(this).text().trim() == "�˻�"){
			fncGetProductList();
		}
	});
	
	
	// ���� �˻�
	$(document).on("keydown", function(event) {
		if (event.key == 'Enter') {
			fncGetProductList();
		}
	});
	
});
