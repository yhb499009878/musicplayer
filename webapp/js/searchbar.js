$(function(){

	//搜索框
	$("#searchbar").blur(function(){
		var keyword=$("#searchbar").val();
		if(keyword!=null&&keyword!=""){
			sessionStorage.setItem("keyword",keyword);
			location.href="search.html";
		}
		
	});
	
});