function history(){
	popWin = window.open("/product/history", "popWin", 
						"left=300, top=200, width=400, height=500, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
		
	
//==> jQuery ���� �߰��� �κ�
$(function() {
	
	// .on�� ���ؼ� ���� �̺�Ʈ�� ��� .on({"event": funtion(){},})
	$(".Depth03").on({
		// ���콺������ css ����
		"mouseover": function() {
			$(this).css({"cursor" :"pointer", "color":"blue", "font-weight":"bold"});
		},
		
		"mouseleave": function() {
			$(this).css({"color":"black", "font-weight":"normal"});
		},
		
		"click" : function() {
			// /\s/g ����ǥ������ ����� ��� ���鿡 ���� �˻��� �̷��
			var text = $(this).text().replace(/\s/g, "");
			
			console.log("Ŭ���� text= "+text);
			
			switch (text) {
				case "����������ȸ":
					linkTo("/user/getUser?userId="+userId);
					break;
					
				case "ȸ��������ȸ":
					linkTo("/user/listUser");
					break;
				
				case "�ǸŻ�ǰ���":
					linkTo("/product/addProduct");
					break;
					
				case "�ǸŻ�ǰ����":
					linkTo("/product/listProduct?menu=manage");
					break;
					
				case "��ǰ�˻�":
					linkTo("/product/listProduct?menu=search");
					break;
					
				case "�����̷���ȸ":
					linkTo("/purchase/listPurchase");
					break;
				
				case "�ֱٺ���ǰ":
					history();
					break;
				
				case "TODO":
					linkTo("/todo.jsp");
					break;
			}

			console.log("==============================");
		}
		
		
	});
	
});	