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
	getBulletins();
}

function getBulletins()
{
	var url = BASE_URL+"bulletin/queryBulletinByContent";
	var args = {"time":new Date()};
	var html = "";
	$.post(url,args,function(data)
	{
		if (data.errcode==0) 
		{
			for(i in data.result)
			{
				var json = data.result[i];
				html += "<tr>";
				html += "<td>"+json.id+"</td>";
				html += "<td>"+json.content+"</td>";
				html += "<td>"+json.bulletinTime+"</td>";
				html += "<td><div class='btn-group'><button type='button' class='btn btn-default' data-content='"+json.content+"' data-id='"+json.id+"' onclick='updateButton(this)' data-toggle='modal' data-target='#editMessage'>编辑</button>" +
						"<button type='button' data-id='"+json.id+"' onclick='deleteBulletins(this)' class='btn btn-default'>删除</button></div></td>";
				html += "</tr>";
			}
			$("#data_table").append(html);
		}else
		{
			alert("系统异常");
		}
	});
}

function updateButton(b)
{
	var id = b.getAttribute("data-id");
	$("#update_button").attr("data-id",id);
	
	var content = b.getAttribute("data-content");
	$("#message").attr("placeholder",content);
}

function updateBulletins(b)
{
	var content = $("#message").val();
	var id = b.getAttribute("data-id");
	var url = BASE_URL+"bulletin/updateBulletin";
	args = {"id":id,"content":content};
	$.post(url,args,function(data)
	{
		if(data.errcode==0)
		{
			alert("修改成功");
			location.href = "../admin/admin-message.html";
		}else
		{
			alert("系统异常");
		}
	});
}

function deleteBulletins(b)
{
	var flag = confirm("是否要删除该公告？");
	if (flag)
	{
		var id = b.getAttribute("data-id");
		var url = BASE_URL+"bulletin/deleteBulletin";
		args = {"id":id};
		$.post(url,args,function(data)
		{
			if (data.errcode==0)
			{
				alert("删除成功");
				location.href = "../admin/admin-message.html";
			}else
			{
				alert("系统异常");
			}
		});
	}
}

function insertBulletins()
{
	var content = $("#insert_message").val();
	var url = BASE_URL+"bulletin/insertBulletin";
	var args = {"content":content};
	$.post(url,args,function(data)
	{
		if(data.errcode == 0)
		{
			alert("插入成功");
			location.href = "../admin/admin-message.html";
		}else
		{
			alert("系统异常");
		}
	});
}