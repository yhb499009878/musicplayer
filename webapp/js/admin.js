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
	
	getUsers();
}

function getUsers()
{
	var url =BASE_URL+"user/getUsers";
	var args = {"time":new Date()}
	var html = "";
	$.post(url,args,function(data){
		for(i in data)
		{
			var json = data[i];
			html += "<tr>";
			html += "<td>"+json.id+"</td>";
			html += "<td>"+json.username+"</td>";
			html += "<td><div class='btn-group'><button data-id='"+json.id+"' onclick='delete_user(this)' type='button' class='btn btn-default'>删除</button></div></td>";
		}
		$("#data_table").append(html);
	});
}

//用户登出接口
$("#li_signout").click(function() {
	var flag = confirm("管理员是否要退出？");
	if (flag) {
		var url = BASE_URL+"admin/userLogout";
		var args = {};
		$.post(url, args, function(data) {
			if (data.errcode == 0) {
				sessionStorage.removeItem("lastname");
				location.href = "../login.html";
			} else {
				alert("系统异常");
			}
		});
	}
	return false;
});

function delete_user(b)
{
	var flag = confirm("是否删除该会员？");
	if (flag)
	{
		var id = b.getAttribute("data-id");
		var url = BASE_URL+"user/deleteUser";
		args = {"id":id};
		$.post(url,args,function(data)
		{
			if(data.errcode==0)
			{
				alert("删除成功");
				location.href = "../admin/admin-user.html";
			}
		});
	}
}