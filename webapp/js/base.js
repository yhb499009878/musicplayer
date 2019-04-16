/**
 * Created by lei on 2017/4/16.
 */
var CONTEXT = "/musicplayer";
var BASE_URL = CONTEXT + "/manager/";
var FILE_UPLOAD_SIZE = 10*1024*1024; //上传资源的最大值

function join(array, separator) {
    if (array == null) {
        return null;
    }
    var arraySize = array.length;
    var buf = "";
    for (var i = 0; i < arraySize; i++) {
        if (i > 0) {
            buf += separator;
        }
        if (array[i] != null) {
            buf += array[i];
        }
    }
    return buf;
}

function upload(evt,rId,iId){
    var url = BASE_URL + "resource/uploadResource";
    var files = evt.files;
    var file = files[0];

    if(file){
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            type: 'POST',
            url: url,
            data: formData,
            async: true,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data) {
                var resourceInfo = data.result;
                if(data.errcode == 0){
                    if(resourceInfo){
                        var resourceId = resourceInfo.resourceId;
                        var filePath = resourceInfo.filePath;
                        $("#"+rId).val(resourceId);
                        if(iId){
                            $("#"+iId).val(filePath);
                        }
                    }
                }else{
                    alert(data.errmsg);
                }
            }
        });
    }
}

function checkUploadFileSize(size){
    if(parseInt(size) > FILE_UPLOAD_SIZE){
        alert("上传文件容量不能大于3M!请重新选择文件");
        return false;
    }else{
        return true;
    }
}

//退出
function logout(){
    $.ajax({
        type : 'get',
        url : BASE_URL + "admin/userLogout",
        success : function(data){
            if(data){
                window.location.href = CONTEXT + "index.html";
            }
        }
    });
}

function formatFileSize(byte){
    var sizeM = byte/(1024*1024);
    var sizeKB = byte/1024;
    if(sizeM.toFixed(1) > 0){
        return sizeM.toFixed(1) + "M";
    }else{
        return sizeKB.toFixed(1) + "KB";
    }
}

function modifyParameter(name,val){
    var url = window.location.search; //
    var theRequest = new Object();
    if (url.indexOf("?") >= 0) {
        var str = url.substr(1);
        var strs = str.split("&");
        var length = strs.length;
        for(var i = 0; i < length; i ++) {
            if(strs[i].split("=")[0] == name){
                val = encodeURI(encodeURI(val));
                strs[i] = name + "=" + val;
                break;
            }
        }
        if(i >= length){
            str += "&"+name+"="+encodeURI(encodeURI(val));
        }else{
            str = strs.join("&");
        }
        url  = "?" + str;
    }else{
        url = "?" + name + "=" + encodeURI(encodeURI(val));
    }
    return url;
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return "";
}