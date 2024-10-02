function history(){
	popWin = window.open("/product/history", "popWin", 
						"left=300, top=200, width=400, height=500, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
		
	
//==> jQuery 적용 추가된 부분
$(function() {
	
	// .on을 통해서 여러 이벤트를 등록 .on({"event": funtion(){},})
	$(".Depth03").on({
		// 마우스포인터 css 적용
		"mouseover": function() {
			$(this).css({"cursor" :"pointer", "color":"blue", "font-weight":"bold"});
		},
		
		"mouseleave": function() {
			$(this).css({"color":"black", "font-weight":"normal"});
		},
		
		"click" : function() {
			// /\s/g 정규표현식을 써야지 모든 공백에 대한 검색이 이루엊
			var text = $(this).text().replace(/\s/g, "");
			
			console.log("클릭한 text= "+text);
			
			switch (text) {
				case "개인정보조회":
					linkTo("/user/getUser?userId="+userId);
					break;
					
				case "회원정보조회":
					linkTo("/user/listUser");
					break;
				
				case "판매상품등록":
					linkTo("/product/addProduct");
					break;
					
				case "판매상품관리":
					linkTo("/product/listProduct?menu=manage");
					break;
					
				case "상품검색":
					linkTo("/product/listProduct?menu=search");
					break;
					
				case "구매이력조회":
					linkTo("/purchase/listPurchase");
					break;
				
				case "최근본상품":
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