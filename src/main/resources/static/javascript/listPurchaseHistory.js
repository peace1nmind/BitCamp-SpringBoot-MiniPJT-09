// /purchse/listPurchaseHistory.jsp

function fncGetHistoryList(page) {
				
	if (page != null && page != 0) {
		$("input[name='historyPage']").val(page);
	}
	
	$("form").submit();
	
}

$(function() {
	
	pageNavigate("historyPaging", fncGetHistoryList);
	
});
