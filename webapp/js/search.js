$(function(){
	
	if(sessionStorage.getItem("keyword") == undefined||sessionStorage.getItem("keyword")==null){
		searchAllMusic();
	}else{
		var keyword = sessionStorage.getItem("keyword");
		searchMusicByKeyword(keyword);
	}
	
	//关键字搜歌曲
	function searchMusicByKeyword(keyword){
		var musicContent="<table class='table table-bordered'><tr><th>歌曲名</th><th>歌手名</th><th>操 作</th></tr>";
		$.ajax({
	        type: 'get',
	        url: "manager/song/querySongBySongNameAndSingerName",
	        data: {"songName":keyword,"singerName":""},
	        success: function(data) {
	            if(data.errcode == 0){
	            	for(var i=0;i<data.result.length;i++){
	            		musicContent += "<tr><td>"+data.result[i].songName+"</td><td>"+data.result[i].singerName+"</td><td><i class='fa fa-fw fa-play' onclick='playThisMusic("+data.result[i].id+")'></i><i class='fa fa-fw fa-download'></i></td></tr>"
	            	}
	            	musicContent +="</table>";
	            	$("#searchList").empty();
	                $("#searchList").append(musicContent);
	            }else{
	            	alert("没有查到相关音乐");
	            }
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