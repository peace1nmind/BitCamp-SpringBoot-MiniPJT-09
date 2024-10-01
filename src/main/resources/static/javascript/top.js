// /layout/top.jsp

$(function() {
				
	$("#logInOut span").on({
		'click': function() {
	
			var text = $(this).text().replace(/\s/g, "");
			
			console.log("로그인/로그아웃= "+text);
			
			switch(text) {
				
			case "login":
				$(window.parent.frames["rightFrame"].document.location).attr("href", "/user/login");
				break;
			
			case "logout":
				if (confirm("로그아웃 하시겠습니까?")) {
					$(window.parent.document.location).attr("href","/user/logout");
				}
				
				break;
			}
		}

	});
	
});