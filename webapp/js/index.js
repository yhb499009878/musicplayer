$(function(){
//初始化页面
var musics=[];
if(musics==""){
	if(sessionStorage.getItem("musics") == undefined||sessionStorage.getItem("musics")==null){
		queryMusics();
	}else{
		var musicsArray=JSON.parse(sessionStorage.getItem("musics"));
		appendMusic(musicsArray);
	}
}
queryBulletin();//初始化公告


// 查询歌曲榜单
function queryMusics(){
	$.post("manager/song/querySongBySongNameAndSingerName",function(data) {
		if(data.errcode=='0'){
			sessionStorage.setItem("musics", JSON.stringify(data.result));
			appendMusic(data.result);
		}else{
			alert("未查到排行榜歌曲");
		}
	});
}

// 把排行榜数据添加到页面上
function appendMusic(musicsArray){
	var tableContent="";
	var musics=musicsArray;
	for(i=0;i<musics.length;i++){
		tableContent = tableContent + "<tr><td>"+musics[i].songName+"</td><td>"+musics[i].singerName+"</td><td class='pull-right'><i class='fa fa-fw fa-play' onclick='playThisMusic("+musics[i].id+")'></i></td></tr>";
	}
	$("#hotMusicList").append(tableContent);//热歌榜
	$("#newMusicList").append(tableContent);//新歌榜
	$("#popularList").append(tableContent);//流行榜
	$("#classicalList").append(tableContent);//经典老歌榜
	$("#networkList").append(tableContent);//网络歌曲榜
	$("#originalList").append(tableContent);//原创榜
}

//查询公告
function queryBulletin(){
	$.post("manager/bulletin/queryBulletinByContent","",function(data) {
		if(data.errcode=='0'){
			var builletinContent="";
			for(i=0;i< data.result.length;i++){
				builletinContent += "<tr><td>"+data.result[i].content +"</td></tr>";
			}
			$("#builletin").append(builletinContent);
		}else{
			alert("未查询到公告");
		}
	});
}

});

//播放这首歌
function playThisMusic(musicId){
	var playHistory=new Array();
	if(sessionStorage.getItem("history") == undefined||sessionStorage.getItem("history")==null){
		playHistory[0]= musicId;
		sessionStorage.setItem("history",JSON.stringify(playHistory));
		location.href="player.html";
	}else{
		playHistory=JSON.parse(sessionStorage.getItem("history"));
		for(index in playHistory){
		  if(playHistory[index]==musicId){
			  playHistory.splice(index);
		  }
		  playHistory.unshift(musicId);
		  sessionStorage.setItem("history",JSON.stringify(playHistory));
		  location.href="player.html";
		}
	}
}


