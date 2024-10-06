// paging에 관한 js

function pageNavigate(tableId, fnc) {
	
	$("#"+tableId+" a").on('click', function() {
		var id = $(this).attr("id");
		var page = $(this).data("page");
		
		switch (id) {
			case "firstPage":
				fnc(1);
				break;
			
			case "prevPage":
				fnc(page);
				break;
				
			case "goPage":
				fnc(page);
				break;
				
			case "nextPage":
				fnc(page);
				break;
			
			case "lastPage":
				fnc(page);
				break;
		}
		
	});
	
}