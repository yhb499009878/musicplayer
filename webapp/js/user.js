//页面加载完毕后加载-->查看是否有用户已经登录
$(function(){
	var start = document.cookie.indexOf("normaluser=");
	if (start != -1)
	{
		var end = document.cookie.indexOf(';', start);
		if (end == -1)
		{
			end = document.cookie.length;
		}
		var cookieId = decodeURIComponent(document.cookie.substring(start
				+ "normaluser=".length , end));
		if (cookieId != null && cookieId!="\"\"") {
			$("#li_signup").attr("style", "display:none");
			$("#li_span_signin").text(sessionStorage.lastname).off('click', '**');
			$("#li_signout").attr("style", "display:inline");
		}
	}
});
	

$(function() {
	// 普通用户登录接口
	$("#signin_button").click(function() {
		var username = $("#signin_username").val();
		var password = $("#signin_password").val();
		var url = BASE_URL+"admin/userLogin";
		var args = {
					"username" : username,
					"password" : password
					};
		$.post(url, args, function(data) {
			if (data.errcode == 0) {
				sessionStorage.setItem("lastname",username);
				$("#li_signup").attr("style", "display:none");
				$("#signin").fadeOut();
				$("div.modal-backdrop.fade.in").fadeOut();
				$("#li_span_signin").text(username).off('click', '**');
				$("#li_signout").attr("style", "display:inline");
			} else if(data.errcode == 110){
				alert("对不起...暂无用户信息");
				$("#signin").fadeOut();
				$("div.modal-backdrop.fade.in").fadeOut();
			}
		});
		return false;
	});

	// 用户登出接口
	$("#li_signout").click(function() {
		var url = BASE_URL+"admin/userLogout";
		var args = {};
		$.post(url, args, function(data) {
			if (data.errcode == 0) {
				alert("成功退出");
				sessionStorage.removeItem("lastname");
				$("#li_span_signin").text("登录").off('click', '**');
				$("#li_signup").attr("style", "display:inline");
				$("#li_signout").attr("style", "display:none");
			} else {
				alert("系统异常");
			}
		});
		return false;
	});
	
	//用户注册
	$("#signup_button").click(function() {
		var username = $("#signup_username").val();
		var password = $("#signup_password").val();
		var password2 = $("#signup_password2").val();
		if(password == password2)
		{
			var url = BASE_URL+"user/register";
			var args = {
					"username" : username,
					"password" : password
			};
			$.post(url, args, function(data) {
				if (data.errcode == 0) {
					sessionStorage.lastname = username;
					$("#li_signup").attr("style", "display:none");
					$("#signup").fadeOut();
					$("div.modal-backdrop.fade.in").fadeOut();
					$("#li_span_signin").text(username).off('click', '**');
					$("#li_signout").attr("style", "display:inline");
				}else if(data.errcode == 111) {
					alert("对不起...该用户名已经被占用");
				}
			});
		}else
		{
			alert("两次输入的密码不一致");
		}
		return false;
	});
	
	// 系统管理员用户登录接口
	$("#submit_login").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		var url = BASE_URL+"admin/userLogin";
		var args = {
			"username" : username,
			"password" : password
		};
		$.post(url, args, function(data) {
			if (data.errcode == 0) {
				sessionStorage.setItem("lastname",username);
				location.href = "admin/admin-user.html";
			} else if(data.errcode == 110){
				alert("对不起...暂无用户信息");
				$("#signin").fadeOut();
				$("div.modal-backdrop.fade.in").fadeOut();
			}
		});
		return false;
	});
});