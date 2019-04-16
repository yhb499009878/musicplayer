$(function(){
	
	if(sessionStorage.getItem("list") == undefined||sessionStorage.getItem("list")==null){
		queryMusicList();
	}else{
		var musicList = JSON.parse(sessionStorage.getItem("list"));
		showMusicList(musicList);
	}
	
	//查询歌曲列表
	function queryMusicList(){
		$.ajax({
	        type: 'get',
	        url: "manager/songList/querySongList",
	        data: {},
	        success: function(data) {
	            if(data.errcode == 0){
	            	var musicList = data.result;
	            	sessionStorage.setItem("list",JSON.stringify(musicList));
	            	showMusicList(musicList);
	            }else{
	            	alert("歌单查询异常");
	            }
	        }
	    });
	}
	
	//展示歌单
	function showMusicList(musicList){
		var musicListContent="";
		for(var i=0;i<musicList.length;i++){ 
			musicListContent += "<div class='col-sm-3' onclick='showMusic("+musicList[i].id+")'><img class='img-responsive' src='"+musicList[i].coverResourceInfo.filePath+"' alt='Photo'><div class='text-center'>"+musicList[i].songListName+"</div></div>";
		} 
		$("#musicList").append(musicListContent);
	}

});

//展示歌单中的歌曲
function showMusic(musicListId){
	var musicContent="<table class='table table-bordered'><tr><td>歌曲名</td><td>歌手名</td><td>操 作</td></tr>";
	$.ajax({
        type: 'get',
        url: "manager/song/querySongByMusicListId",
        data: {"musicListId":musicListId},
        success: function(data) {
            if(data.errcode == 0){
            	for(var i=0;i<data.result.length;i++){
            		musicContent += "<tr><td>"+data.result[i].songName+"</td><td>"+data.result[i].singerName+"</td><td><i class='fa fa-fw fa-play' onclick='playThisMusic("+data.result[i].id+")'></i><i class='fa fa-fw fa-download'></i></td></tr>"
            	}
            	musicContent +="</table>";
            	$("#musicList").empty();
                $("#musicList").append(musicContent);
            }else{
            	alert("歌曲查询异常");
            }
        }   
    });
}

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