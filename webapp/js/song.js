/**
 * Created by lei on 2017/4/16.
 */
$(function(){
    querySong();
})
function saveSong(){
    var songName = $("#isongName").val();
    var singerName = $("#isingerName").val();
    var posterResourceId = $("#iposterResourceId").val();
    var songResourceId = $("#isongResourceId").val();
    var lyricsResourceId = $("#ilyricsResourceId").val();

    $.ajax({
        type: 'get',
        url: BASE_URL + "song/insertSong",
        data: {"songName":songName,"singerName":singerName,"posterResourceId":posterResourceId,"songResourceId":songResourceId,"lyricsResourceId":lyricsResourceId},
        success: function(data) {
            if(data.errcode == 0){
                alert("操作成功");
                history.go(0);
            }else{
                alert(data.errmsg);
            }
        }
    });

}

function updateSong(){
    var songName = $("#songName").val();
    var singerName = $("#singerName").val();
    var posterResourceId = $("#posterResourceId").val();
    var songResourceId = $("#songResourceId").val();
    var lyricsResourceId = $("#lyricsResourceId").val();
    var id = $("#songId").val();
    $.ajax({
        type: 'get',
        url: BASE_URL + "song/updateSong",
        data: {"id":id,"songName":songName,"singerName":singerName,"posterResourceId":posterResourceId,"songResourceId":songResourceId,"lyricsResourceId":lyricsResourceId},
        success: function(data) {
            if(data.errcode == 0){
                alert("操作成功");
                history.go(0);
            }else{
                alert(data.errmsg);
            }
        }
    });

}

function initSong(data){
    $("#songBody").empty();
    var show_list = $tmpl$("song_template");
    if (data == null || data == "") {
        var $html = "";
        return $html;
    } else {
        $.each(data, function (i, item) {
            var id = item.id;
            var songName = item.songName;
            var singerName = item.singerName;
            var createTime = item.createTime;

            var tempItem = {
                id: id,
                songName: songName,
                singerName:singerName,
                createTime:createTime
            };

            var $html = $(show_list(tempItem));
            $("#updateSong",$html).on("click",function(){
                $("#songKey").val(item.id);
                $("#songName").val(item.songName);
                $("#singerName").val(item.singerName);
                $("#posterResourceId").val(item.posterResourceId);
                $("#songResourceId").val(item.songResourceId);
                $("#lyricsResourceId").val(item.lyricsResourceId);
                $("#songId").val(item.id);
            })

            $("#inserToSongList",$html).on("click",function(){
                $("#songKey").val(item.id);
                $.ajax({
                    type:"get",
                    url:BASE_URL + "songList/querySongListByNameAndDesc",
                    success:function(data){
                        if(data.errcode == 0){
                            if(data.result.length > 0){
                                $("#addToMusicList").modal('show');
                                initSongList(data.result);
                            }else{
                                alert("没有歌单");
                                $("#addToMusicList").modal('hide');
                            }
                        }
                    }
                })
            })
            $("#deleteSong",$html).on("click",function(){
                $.ajax({
                    type:"get",
                    url:BASE_URL + "song/deleteSong",
                    data:{"id":item.id},
                    success:function(data){
                        if(data.errcode == 0){
                            alert("操作成功");
                            history.go(0);
                        }
                    }
                })
            })
            $("#songBody").append($html);
        });
    }
}

function initSongList(result){
    for(var i=0; i<result.length; i++){
        var songListName = result[i].songListName;
        var songListId = result[i].id;
        var $songList = $('<option value="'+songListId+'">'+songListName+'</option>');
        $("#songListSelect").append($songList);
    }
}

function insertSongToSongList(){
    var songListId = $("#songListSelect").val();
    var songId = $("#songKey").val();
    $.ajax({
        type:"get",
        url:BASE_URL + "song/addToSongList",
        data:{"songId":songId,"songListId":songListId},
        success:function(data){
            if(data.errcode == 0){
                alert("操作成功");
                $("#addToMusicList").modal('hide');
            }
        }
    })
}

function querySong(){
    $.ajax({
        type: 'get',
        url: BASE_URL + "song/querySongBySongNameAndSingerName",
        data: {},
        success: function(data) {
            var result = data.result;
            if(data.errcode == 0){
                initSong(result);
            }else{
                alert(data.errmsg);
            }
        }
    });
}
