/**
 * Created by lei on 2017/4/16.
 */
$(function(){
    querySongList();
})

function saveSongList(){
    var songListName = $("#isongListName").val();
    var songListDesc = $("#isongListDesc").val();
    var coverResourceId = $("#icoverResourceId").val();

    $.ajax({
        type: 'get',
        url: BASE_URL + "songList/insertSongList",
        data: {"songListName":songListName,"songListDesc":songListDesc,"coverResourceId":coverResourceId},
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

function updateSongList(){
    var id = $("#songListKey").val();
    var songListName = $("#songListName").val();
    var songListDesc = $("#songListDesc").val();
    var coverResourceId = $("#coverResourceId").val();
    $.ajax({
        type: 'get',
        url: BASE_URL + "songList/updateSongList",
        data: {"id":id,"songListName":songListName,"songListDesc":songListDesc,"coverResourceId":coverResourceId},
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

function initSongList(data){
    $("#songListBody").empty();
    var show_list = $tmpl$("songList_template");
    if (data == null || data == "") {
        var $html = "";
        return $html;
    } else {
        $.each(data, function (i, item) {
            var id = item.id;
            var songListName = item.songListName;
            var songListDesc = item.songListDesc;
            var createTime = item.createTime;

            var tempItem = {
                id: id,
                songListName: songListName,
                songListDesc:songListDesc,
                createTime:createTime
            };

            var $html = $(show_list(tempItem));
            $("#updateSongList",$html).on("click",function(){
                $("#songListKey").val(item.id);
                $("#songListName").val(item.songListName);
                $("#songListDesc").val(item.songListDesc);
            })

            $("#deleteSongList",$html).on("click",function(){
                $.ajax({
                    type:"get",
                    url:BASE_URL + "songList/deleteSongList",
                    data:{"id":item.id},
                    success:function(data){
                        if(data.errcode == 0){
                            alert("操作成功");
                            history.go(0);
                        }
                    }
                })
            })
            $("#songListBody").append($html);
        });
    }
}

function querySongList(){
    $.ajax({
        type: 'get',
        url: BASE_URL + "songList/querySongListByNameAndDesc",
        data: {},
        success: function(data) {
            var result = data.result;
            if(data.errcode == 0){
                initSongList(result);
            }else{
                alert(data.errmsg);
            }
        }
    });
}