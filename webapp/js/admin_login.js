window.onload = function()
{
	var start = document.cookie.indexOf(encodeURIComponent("normaluser"));
	var end = document.cookie.indexOf(';', start);
	if (end == -1) {
		end = document.cookie.length;
	}
	var cookieId = decodeURIComponent(document.cookie.substring(start
			+ "normaluser".length+1 , end));
	if (cookieId != null && cookieId!="\"\"") {
		$("#li_span_signin").text(sessionStorage.lastname).off('click', '**');
		$("#li_signout").attr("style", "display:inline");
	}
}