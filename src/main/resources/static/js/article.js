function comment() {
    var articleId = $("#articleId").val();
    var content = $("#content").val();
    var userId = $("#userId").val();
    if(content == "") {
        alert("评论内容不能为空！");
        return false;
    }
    $.ajax({
        type: "post",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify({
            "articleId": articleId,
            "content": content,
            "commentator": userId
        }),
        success:function(result){
            window.location.reload();
        }

    })
}

function  delComment(id) {
    var articleId = $("#articleId").val();
    if(confirm("确定要这条评论删除吗？")) {
        $.ajax({
            type:"post",
            url:"/comment/del",
            data: {
                "id": id,
                "articleId": articleId
            },
            success:function(result) {
                window.location.reload();
            }
        })
    }

}

window.onload=function() {
    var count = $("#textCount");
    var content = $("#content");

    $(content).bind('input', function(){
        count.html(255 - content.val().length);
    });

}