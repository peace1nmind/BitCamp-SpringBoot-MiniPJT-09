/* ���� JS */

function linkTo(location) {
	console.log("�̵��ϴ� ��ũ= "+location)
	$(window.parent.frames["rightFrame"].document.location).attr("href", location);
}

function toDateType(date) {
	
	if (date == null) {
		return "";
	}
	
	else {	
		if (!date.includes("-")) {
			return date.replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
			
		} else {
			return date;
			
		}
	}
}