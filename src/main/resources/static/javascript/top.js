// /layout/top.jsp

$(function() {
				
	$("#logInOut span").on({
		'click': function() {
	
			var text = $(this).text().replace(/\s/g, "");
			
			console.log("�α���/�α׾ƿ�= "+text);
			
			switch(text) {
				
			case "login":
				$(window.parent.frames["rightFrame"].document.location).attr("href", "/user/login");
				break;
			
			case "logout":
				if (confirm("�α׾ƿ� �Ͻðڽ��ϱ�?")) {
					$(window.parent.document.location).attr("href","/user/logout");
				}
				
				break;
			}
		}

	});
	
});