<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-2.2.4.js" 
				integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" 
				crossorigin="anonymous"></script>

<script type="text/javascript">
// <!--
	function fncGetList(page) {
		console.log("page= "+page);
		
		if (page != null && page != 0) {
			$("input[name='page']").val(page);
		}
		
		$("form").submit();
	}
// -->
</script>

<input type="hidden" name="isSale" value="${param.isSale }">

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
	
			<a href="javascript:fncGetList(1);" 
			${(paging.left)? "":"style='visibility:hidden'"}>
				<span>◀</span>
			</a>
			
			&nbsp;
			
			<a href="javascript:fncGetList('${paging.start - 1}')"; 
			${(paging.left)? "":"style='visibility:hidden'"}>
				<span>이전</span>
			</a>
			
			&nbsp;&nbsp;
			
	<c:forEach begin="${paging.start}" end="${paging.end}" var="i" >
			<a href="javascript:fncGetList('${i }');" 
			${(paging.currentPage==i)? "style='font-weight: bold; font-size: 15px'" : ""}>
				${i}
			</a>
	</c:forEach>
	
			&nbsp;&nbsp;
		
			
			<a href="javascript:fncGetList('${paging.end + 1}');" 
			${(paging.right)? "":"style='visibility:hidden'"}>
				<span>다음</span>
			</a>
			
			&nbsp;
			
			<a href="javascript:fncGetList('${paging.totalPage}');" 
			${(paging.right)? "":"style='visibility:hidden'"}>
				<span>▶</span>
			</a>
			

    	</td>
	</tr>
</table>