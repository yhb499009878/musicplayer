$(function(){
// 初始化音乐播放器
var audio = document.createElement("audio");
var music={};
if(sessionStorage.getItem("history") == undefined||sessionStorage.getItem("history")==null){
	audio.src="data/yanyuan.mp3";
	audio.play();
	showMusicLrc("data/yanyuan.lrc");
}else{
	var history = JSON.parse(sessionStorage.getItem("history"));
	var musicId = history[0];
	playMusicById(musicId);//播放音乐(ID)
	showHistory();//展示播放列表
}

//播放音乐(ID)
function playMusicById(musicId){
	$.ajax({
        type: 'get',
        url: "manager/song/querySongById",
        data: {"musicId":musicId},
        success: function(data) {
            if(data.errcode == 0){
            	audio.src=data.result.songResourceInfo.filePath;
            	$("#singerName").text(data.result.singerName);
            	$("#singerPohto").attr('src',data.result.posterResourceInfo.filePath);
    			audio.play();
    			showMusicLrc(data.result.lyricsResourceInfo.filePath);
            }else{
            	audio.src="data/yanyuan.mp3";
    			audio.play();
    			showMusicLrc("data/yanyuan.lrc");
            }
        }
    });
}

//展示播放列表
function showHistory(){
	var playHistory=JSON.parse(sessionStorage.getItem("history"));;
	var historyContent="";
	for(index in playHistory){
		$.ajax({
	        type: 'get',
	        url: "manager/song/querySongById",
	        async: false,
	        data: {"musicId":playHistory[index]},
	        success: function(data) {
	            if(data.errcode == 0){
	            	if(playHistory[index]!=data.result.id){
	            		historyContent += "<tr><td>"+data.result.songName+"</td><td class='pull-right'><i class='fa fa-fw fa-play' onclick='playThisMusic("+data.result.id+")'></i><i class='fa fa-fw fa-download'></i></td></tr>";
	            	}else{
	            		historyContent += "<tr><td>"+data.result.songName+"</td><td class='pull-right'><i class='fa fa-fw fa-pause' onclick='playThisMusic("+data.result.id+")'></i><a href='"+data.result.songResourceInfo.filePath+"' download='"+data.result.songName+".mp3'><i class='fa fa-fw fa-download'></a></i></td></tr>";
	            	}
	            }
	        }
	    });
	}
	$("#history").append(historyContent);
}

//播放
function play(musicfile){
	audio.pause();
	audio.src = musicfile;
	audio.play();
}
// 暂停
function pause(){
	audio.pause();
}
// 停止
function stop() {
	audio.currentTime = 0;
	audio.pause();
}

// 暂停当前播放的歌曲
$("#pausebutton").click(function(){
	audio.pause();
	$("#pausebutton").hide();
	$("#playbutton").show();
});

// 播放当前显示的歌曲
$("#playbutton").click(function(){
	audio.play();
	$("#playbutton").hide();
	$("#pausebutton").show();
});

// 播放这首歌
function playThisMusic(id,musicName,musicPath,lrcPath,singerName,singerPhoto){
	audio.src = musicPath;
	$("#singerName").text(singerName);
	$("#singerPohto").attr('src',singerPhoto);
	audio.play();
	showMusicLrc(lrcPath);
}

// 显示歌词
function showMusicLrc(lrcPath){
	
	$.ajax({url:lrcPath,dataType: 'text',success: function(data) {
			var pre = data.split("\[");
			var $p = $("<p>");
			for(var i=0;i< pre.length; i++){
				var s = pre[i];
				var  arr = s.split("\]");
				var lrcContent = arr[1] || "";
				$p.append(""+lrcContent+"</br>")
			}
			$p.append("</p>");
			$("#lrc").empty();
			$("#lrc").append($p);
		}
	});
}

// 推荐列表 播放《演员》 
$("#yanyuan").on("click", function(){
	playThisMusic('','演员','data/yanyuan.mp3','/data/yanyuan.irc','薛之谦','dist/img/xuezhiqian.jpg');
});

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